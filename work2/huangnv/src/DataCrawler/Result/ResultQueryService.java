package DataCrawler.Result;

import Common.QueryService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultQueryService implements QueryService {
    private final ResultCrawler crawler = new ResultCrawler();
    private final ResultFormatter formatter = new ResultFormatter();
    private final Object mapLock = new Object();
    private volatile Map<String, String> eventIdMap;
    private final Map<String, String> eventJsonCache = new ConcurrentHashMap<>();

    @Override
    public String query(String target, String option) {
        String eventKey = target.trim();
        String eventJson = eventJsonCache.get(eventKey);
        if (eventJson == null) {
            try {
                String eventId = getEventId(eventKey);
                eventJson = crawler.fetchEventJsonByDisciplineId(eventId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            eventJsonCache.put(eventKey, eventJson);
        }
        return formatter.format(eventJson, option);
    }

    private String getEventId(String eventKey) throws IOException {
        Map<String, String> map = ensureEventIdMap();
        return map.get(eventKey);
    }

    private Map<String, String> ensureEventIdMap() throws IOException {
        Map<String, String> map = eventIdMap;
        if (map != null) {
            return map;
        }
        synchronized (mapLock) {
            if (eventIdMap == null) {
                eventIdMap = buildEventIdMap();
            }
            return eventIdMap;
        }
    }

    private Map<String, String> buildEventIdMap() throws IOException {
        return crawler.fetchEventIdMap();
    }
}
