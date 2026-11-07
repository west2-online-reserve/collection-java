package org.west2.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Fetches OMEGA's public result feed and reduces it to the assignment's JSON snapshot. */
public final class FetchData {
    public static final String SOURCE =
            "https://www.omegatiming.com/File/00011A0102FFFFFFFFFFFFFFFFFFFFC0.xml";
    public static final String ENTRY_SOURCE =
            "https://www.omegatiming.com/File/00011A0102FFFFFFFFFFFFFFFFFFFF02.pdf";
    private static final Pattern ENTRY_LINE = Pattern.compile(
            "^([A-Z]{3})(.+?) +\\d{1,2} [A-Z]{3} 20\\d{2} .+([FM])$");
    private static final Map<String, String> EVENT_CODES = Map.of(
            "F3OF", "women 3m springboard",
            "F10OF", "women 10m platform",
            "F3SYNOF", "women 3m synchronised",
            "F10SYNOF", "women 10m synchronised",
            "M3OF", "men 3m springboard",
            "M10OF", "men 10m platform",
            "M3SYNOF", "men 3m synchronised",
            "M10SYNOF", "men 10m synchronised");
    private static final Map<String, String> COUNTRIES = Map.ofEntries(
            Map.entry("AUS", "Australia"), Map.entry("CAN", "Canada"),
            Map.entry("CHN", "China"), Map.entry("COL", "Colombia"),
            Map.entry("ESP", "Spain"), Map.entry("FRA", "France"),
            Map.entry("GBR", "Great Britain"), Map.entry("GER", "Germany"),
            Map.entry("ITA", "Italy"), Map.entry("JPN", "Japan"),
            Map.entry("MEX", "Mexico"), Map.entry("PRK", "North Korea"),
            Map.entry("RUS", "Russia"), Map.entry("SUI", "Switzerland"),
            Map.entry("UKR", "Ukraine"), Map.entry("USA", "United States"));

    private FetchData() {}

    public static void main(String[] args) throws Exception {
        if (args.length != 0 && (args.length != 3 || !args[0].equals("--files"))) {
            System.err.println("用法: java -cp target/DWASearch.jar org.west2.learning.FetchData [--files 成绩.xml 名单.pdf]");
            System.exit(2);
        }
        byte[] xml = args.length == 0 ? download(SOURCE) : Files.readAllBytes(Path.of(args[1]));
        byte[] pdf = args.length == 0 ? download(ENTRY_SOURCE) : Files.readAllBytes(Path.of(args[2]));
        ContestData data = parse(xml, pdf, Instant.now().toString());
        Path destination = Path.of("data.json");
        new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValue(destination.toFile(), data);
        System.out.println("已生成 " + destination.toAbsolutePath() + "，选手 "
                + data.players().size() + " 人，决赛 " + data.events().size() + " 项。");
    }

    private static byte[] download(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15))
                .version(HttpClient.Version.HTTP_1_1).followRedirects(HttpClient.Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(40))
                .header("User-Agent", "DWASearch-learning/1.0")
                .header("Accept", "application/xml, text/xml, application/pdf")
                .GET().build();
        IOException last = null;
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
                if (response.statusCode() == 200) return response.body();
                if (response.statusCode() == 403 || response.statusCode() == 429) {
                    throw new IOException("官网返回 HTTP " + response.statusCode() + "；请停止请求并检查访问限制");
                }
                last = new IOException("官网返回 HTTP " + response.statusCode());
            } catch (IOException exception) {
                last = exception;
            }
            if (attempt == 1) Thread.sleep(2000);
        }
        throw last;
    }

    public static ContestData parse(byte[] xml, byte[] pdf, String capturedAt) throws IOException {
        final Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml));
        } catch (ParserConfigurationException | SAXException exception) {
            throw new IOException("官方 XML 无法解析", exception);
        }
        Element root = document.getDocumentElement();
        if (!"diving".equals(root.getTagName())
                || !text(root, "title").contains("Super Final")
                || !"2026-05-01".equals(text(root, "startdate"))) {
            throw new IOException("XML 不是 2026 北京跳水世界杯超级总决赛");
        }

        Map<String, ContestData.Player> players = new LinkedHashMap<>();
        Map<String, List<ContestData.Result>> events = new LinkedHashMap<>();
        for (Element event : children(root, "event")) {
            String key = EVENT_CODES.get(event.getAttribute("name"));
            Element divers = child(event, "divers");
            if (divers == null) continue;
            List<ContestData.Result> results = new ArrayList<>();
            for (Element diver : children(divers, "diver")) {
                addPlayer(players, diver, "1");
                if (!text(diver, "id2").isBlank()) addPlayer(players, diver, "2");
                if (key != null && !text(diver, "rank").isBlank()) {
                    String name = fullName(diver, "1");
                    if (!text(diver, "id2").isBlank()) name += " / " + fullName(diver, "2");
                    List<String> scores = new ArrayList<>();
                    Element dives = child(diver, "results");
                    if (dives == null) throw new IOException("缺少逐跳成绩: " + name);
                    for (Element dive : children(dives, "dive")) scores.add(text(dive, "points"));
                    try {
                        results.add(new ContestData.Result(name,
                                Integer.parseInt(text(diver, "rank")), scores, text(diver, "score")));
                    } catch (NumberFormatException exception) {
                        throw new IOException("排名无效: " + name, exception);
                    }
                }
            }
            if (key != null) events.put(key, results);
        }
        addEntryList(players, pdf);
        ContestData data = new ContestData(SOURCE + " ; " + ENTRY_SOURCE,
                capturedAt, new ArrayList<>(players.values()), events);
        DataLoader.validate(data);
        return data;
    }

    private static void addEntryList(Map<String, ContestData.Player> players, byte[] pdf) throws IOException {
        String content;
        try (PDDocument document = Loader.loadPDF(pdf)) {
            content = new PDFTextStripper().getText(document);
        }
        if (!content.contains("Entry List by Name") || !content.contains("Super Final 2026")) {
            throw new IOException("PDF 不是本届官方报名名单");
        }
        Map<String, ContestData.Player> byName = new LinkedHashMap<>();
        for (ContestData.Player player : players.values()) byName.put(normalize(player.fullName()), player);
        int rosterCount = 0;
        for (String line : content.split("\\R")) {
            Matcher match = ENTRY_LINE.matcher(line.strip());
            if (!match.matches()) continue;
            rosterCount++;
            String country = COUNTRIES.get(match.group(1));
            if (country == null) throw new IOException("报名名单有未知国家代码: " + match.group(1));
            String name = match.group(2).trim();
            String gender = match.group(3).equals("F") ? "Female" : "Male";
            ContestData.Player existing = byName.get(normalize(name));
            if (existing == null) {
                String surname = name.split(" ", 2)[0].toUpperCase(Locale.ROOT);
                ContestData.Player player = new ContestData.Player(name, surname, gender, country);
                byName.put(normalize(name), player);
                players.put("pdf:" + name, player);
            } else if (!existing.gender().equals(gender) || !existing.country().equals(country)) {
                throw new IOException("报名名单与成绩 XML 的选手信息冲突: " + name);
            }
        }
        if (rosterCount != 89 || players.size() != rosterCount) {
            throw new IOException("报名名单应有 89 人，实际读取 " + rosterCount
                    + " 行，合并后 " + players.size() + " 人；请检查 PDF 格式");
        }
    }

    private static String normalize(String name) {
        return Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").replaceAll("[^A-Za-z]", "")
                .toLowerCase(Locale.ROOT);
    }

    private static void addPlayer(Map<String, ContestData.Player> players, Element diver, String suffix)
            throws IOException {
        String id = text(diver, "id" + suffix);
        String countryCode = text(diver, "nation" + suffix);
        String country = COUNTRIES.get(countryCode);
        if (id.isBlank() || country == null) throw new IOException("选手 ID 或国家代码缺失: " + countryCode);
        String gender = switch (text(diver, "gender" + suffix)) {
            case "F" -> "Female";
            case "M" -> "Male";
            default -> throw new IOException("选手性别缺失: " + id);
        };
        String lastName = text(diver, "surname" + suffix);
        ContestData.Player player = new ContestData.Player(fullName(diver, suffix),
                lastName, gender, country);
        ContestData.Player existing = players.putIfAbsent(id, player);
        if (existing != null && !existing.equals(player)) {
            throw new IOException("同一选手在官方 XML 中有冲突记录: " + id);
        }
    }

    private static String fullName(Element diver, String suffix) {
        String surname = text(diver, "surname" + suffix);
        String given = text(diver, "name" + suffix).toLowerCase(Locale.ROOT);
        StringBuilder title = new StringBuilder();
        boolean upper = true;
        for (char c : given.toCharArray()) {
            title.append(upper ? Character.toUpperCase(c) : c);
            upper = c == ' ' || c == '-';
        }
        return surname + " " + title;
    }

    private static Element child(Element parent, String name) {
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element element && name.equals(element.getTagName())) return element;
        }
        return null;
    }

    private static List<Element> children(Element parent, String name) {
        List<Element> result = new ArrayList<>();
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element element && name.equals(element.getTagName())) result.add(element);
        }
        return result;
    }

    private static String text(Element parent, String name) {
        Element child = child(parent, name);
        return child == null ? "" : child.getTextContent().trim();
    }
}

