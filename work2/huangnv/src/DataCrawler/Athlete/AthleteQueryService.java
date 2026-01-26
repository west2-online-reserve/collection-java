package DataCrawler.Athlete;

import Common.QueryService;

public class AthleteQueryService implements QueryService {
    private final AthleteCrawler crawler = new AthleteCrawler();
    private final AthleteFormatter formatter = new AthleteFormatter();

    @Override
    public String query(String target, String option) {
        String json;
        try {
            json = crawler.fetch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return formatter.format(json, "");
    }
}
