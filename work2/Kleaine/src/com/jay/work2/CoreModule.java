    package com.jay.work2;

    import com.google.gson.Gson;

    import java.io.File;
    import java.util.*;

    /**
     * CoreModule
     */
    public class CoreModule {

        private final List<Lib.Player> cachedPlayers;
        private final Map<String, Lib.EventResult> eventCache = new HashMap<>();
        private final Gson gson = new Gson();

        public CoreModule() {
            // 构造时只加载一次 athletes.json
            List<Lib.Player> players;
            try {
                players = Lib.loadPlayersFromJson("data/athletes.json");
                if (players == null) {
                    players = new ArrayList<>();
                }
                players.sort(Comparator.comparing(Lib.Player::getCountry).thenComparing(Lib.Player::getFullName));
            } catch (Exception e) {
                players = new ArrayList<>();
            }
            this.cachedPlayers = players;
        }

        /**
         * 返回所有选手信息
         */
        public String getAllPlayersInfo() {
            StringBuilder sb = new StringBuilder();
            if (cachedPlayers == null || cachedPlayers.isEmpty()) {
                sb.append("N/A\n-----\n");
                return sb.toString();
            }

            for (Lib.Player p : cachedPlayers) {
                String fullName = (p.getFullName() == null || p.getFullName().isEmpty()) ? "N/A" : p.getFullName();
                String gender = (p.getGender() == null || p.getGender().isEmpty()) ? "N/A" : p.getGender();
                String country = (p.getCountry() == null || p.getCountry().isEmpty()) ? "N/A" : p.getCountry();

                sb.append("Full Name:").append(fullName).append("\n");
                sb.append("Gender:").append(gender).append("\n");
                sb.append("Country:").append(country).append("\n");

                sb.append("-----\n");
            }

            return sb.toString();
        }


        /**
         * 按事件名加载 EventResult
         * eventName
         */
        public Lib.EventResult loadEventResultOnce(String eventName) {
            if (eventCache.containsKey(eventName)) {
                return eventCache.get(eventName);
            }

            // 在 data/ 目录中查找忽略大小写的文件名匹配
            File dir = new File("data");
            if (!dir.exists() || !dir.isDirectory()) {
                return null;
            }

            String desired = eventName + ".json";
            File matched = null;
            for (File f : dir.listFiles()) {
                if (f.isFile() && f.getName().equalsIgnoreCase(desired)) {
                    matched = f;
                    break;
                }
            }
            if (matched == null) {
                return null;
            }

            try {
                String json = Lib.readFile(matched.getPath());
                Lib.EventResult er = gson.fromJson(json, Lib.EventResult.class);

                // 对（Heats[0].Results）TotalPoints 降序排序
                if (er != null && er.getHeats() != null && !er.getHeats().isEmpty()) {
                    Lib.Heat h = er.getHeats().get(0);
                    if (h != null && h.getResults() != null) {
                        h.getResults().sort(Comparator.comparing(Lib.PlayerResult::getTotalPoints).reversed());
                    }
                }
                eventCache.put(eventName, er);
                return er;
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 处理 result 指令
         */
        public String getEventResult(String command) {
            if (command == null) {
                return "";
            }

            String rawCmd = command.trim();
            String standardCmd = rawCmd.replaceAll("\\s+", " ");
            if (!rawCmd.equals(standardCmd)) {
                return "N/A";
            }

            String[] parts = standardCmd.split(" ");
            boolean isDetail = false;
            String targetEvent = "";

            if (parts.length >= 4) {
                if (parts.length >= 5 && "detail".equals(parts[parts.length - 1])) {
                    isDetail = true;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < parts.length - 1; i++) {
                        sb.append(parts[i]).append(" ");
                    }
                    targetEvent = sb.toString().trim();
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < parts.length; i++) {
                        sb.append(parts[i]).append(" ");
                    }
                    targetEvent = sb.toString().trim();
                }
            } else {
                return "N/A";
            }

            if (!Lib.VALID_EVENTS.contains(targetEvent)) {
                return "N/A";
            }

            Lib.EventResult er = loadEventResultOnce(targetEvent);
            if (er == null || er.getHeats() == null || er.getHeats().isEmpty()) {
                return "N/A";
            }

            Lib.Heat firstHeat = er.getHeats().get(0);
            if (firstHeat == null || firstHeat.getResults() == null || firstHeat.getResults().isEmpty()) {
                return "N/A";
            }

            StringBuilder out = new StringBuilder();
            for (Lib.PlayerResult pr : firstHeat.getResults()) {
                // 获取姓名 & 国籍：优先使用 Competitors（同步双人），否则尝试PlayerResult本身字段
                String nameStr = "";
                String natStr = "";

                if (pr.getCompetitors() != null && !pr.getCompetitors().isEmpty()) {
                    StringBuilder nmb = new StringBuilder();
                    StringBuilder cty = new StringBuilder();
                    for (Lib.Competitor c : pr.getCompetitors()) {
                        String last = c.getLastName() == null ? "" : c.getLastName();
                        String first = c.getFirstName() == null ? "" : c.getFirstName();
                        nmb.append(last).append(" ").append(first).append(" / ");
                        cty.append(c.getNAT() == null ? "" : c.getNAT()).append(" / ");
                    }
                    // 去掉末尾 " / "
                    if (nmb.length() >= 3) {
                        nameStr = nmb.substring(0, nmb.length() - 3);
                    } else {
                        nameStr = nmb.toString().trim();
                    }
                    if (cty.length() >= 3) {
                        natStr = cty.substring(0, cty.length() - 3);
                    } else {
                        natStr = cty.toString().trim();
                    }
                } else {
                    // 单人
                    try {
                        String last = pr.getLastName() == null ? "" : pr.getLastName();
                        String first = pr.getFirstName() == null ? "" : pr.getFirstName();
                        nameStr = (last + " " + first).trim();
                        natStr = pr.getNAT() == null ? "" : pr.getNAT();
                    } catch (Throwable t) {
                        // 若没有这些字段，跳过该条结果
                        continue;
                    }
                }

                if (nameStr.isEmpty()) {
                    continue;
                }

                out.append("Full Name:").append(nameStr).append("\n");
                out.append("Rank:").append(pr.getRank()).append("\n");
                out.append("Country:").append(natStr).append("\n");
                out.append("Total Points:").append(String.format("%.2f", pr.getTotalPoints())).append("\n");

                if (isDetail && pr.getDives() != null && !pr.getDives().isEmpty()) {
                    out.append("----- Dive Details -----\n");
                    for (Lib.DiveDetail d : pr.getDives()) {
                        out.append("Dive ").append(d.getDiveOrder()).append("\n");
                        out.append("  Action:").append(d.getDiveNo() == null ? "" : d.getDiveNo()).append("\n");
                        out.append("  Difficulty:").append(d.getDD()).append("\n");
                        out.append("  Dive Points:").append(String.format("%.2f", d.getDivePoints())).append("\n");
                    }
                }
                out.append("-----\n");
            }

            return out.toString();
        }
    }
