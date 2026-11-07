package scraper.get;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebScraperTest {

    @Test
    void readsResponseBodyAndSendsBrowserHeaders() throws Exception {
        AtomicReference<String> userAgent = new AtomicReference<>();
        AtomicReference<String> referer = new AtomicReference<>();
        AtomicReference<String> origin = new AtomicReference<>();
        HttpServer server = localServer(userAgent, referer, origin);
        HttpClient originalClient = WebScraper.CLIENT;

        try {
            server.start();
            WebScraper.CLIENT = HttpClient.newHttpClient();
            String url = "http://127.0.0.1:" + server.getAddress().getPort() + "/data";

            String body = new WebScraper().get_json(url);

            assertEquals("{\"result\":\"成功\"}", body);
            assertTrue(userAgent.get().startsWith("Mozilla/5.0"));
            assertEquals("https://www.worldaquatics.com/", referer.get());
            assertEquals("https://www.worldaquatics.com/", origin.get());
        } finally {
            WebScraper.CLIENT = originalClient;
            server.stop(0);
        }
    }

    @Test
    void rejectsInvalidUrlBeforeSendingRequest() {
        assertThrows(RuntimeException.class, () -> new WebScraper().get_json("not a uri"));
    }

    private static HttpServer localServer(
            AtomicReference<String> userAgent,
            AtomicReference<String> referer,
            AtomicReference<String> origin
    ) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/data", exchange -> {
            userAgent.set(exchange.getRequestHeaders().getFirst("User-Agent"));
            referer.set(exchange.getRequestHeaders().getFirst("Referer"));
            origin.set(exchange.getRequestHeaders().getFirst("Origin"));
            byte[] body = "{\"result\":\"成功\"}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(body);
            }
        });
        return server;
    }
}
