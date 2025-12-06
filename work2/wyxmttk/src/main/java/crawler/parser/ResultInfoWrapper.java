package crawler.parser;

import lombok.Data;

import java.util.Map;

@Data
public class ResultInfoWrapper {
    public static final String FINAL_SCORE="Final Score";
    public static final String SEMIFINAL_SCORE="Semifinal Score";
    public static final String PRELIMINARY_SCORE="Preliminary Score";

    private String fullName;
    //stageName - info
    private Map<String,StageResultInfo> stageResultInfoMap;

    public ResultInfoWrapper(String fullName, Map<String, StageResultInfo> stageResultInfoMap) {
        this.fullName = fullName;
        this.stageResultInfoMap = stageResultInfoMap;
    }

    public ResultInfoWrapper() {
    }

    @Data
    public static class StageResultInfo{
        private int rank;
        private String scoreInfo;

        public StageResultInfo(int rank, String scoreInfo){
            this.rank = rank;
            this.scoreInfo = scoreInfo;
        }

        public StageResultInfo(){}

    }
}
