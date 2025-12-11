package crawler.parser;

import crawler.DataCrawler;

import java.util.Arrays;
import java.util.List;

public class ResultInfoDetailParser extends AbstractResultInfoParser{
    @Override
    public String parse(String rawData) {
        List<ResultInfoWrapper> resultInfoWrappers = parseInitially(rawData, true);
        //估算每条结果约300字符
        int estimatedSize = resultInfoWrappers.size() * 300;
        StringBuilder data = new StringBuilder(estimatedSize);
        for (ResultInfoWrapper resultInfoWrapper : resultInfoWrappers) {
            List<ResultInfoWrapper.StageResultInfo> stageResultInfoList= Arrays.asList(
                    resultInfoWrapper.getStageResultInfoMap().get(ResultInfoWrapper.PRELIMINARY_SCORE),
                    resultInfoWrapper.getStageResultInfoMap().get(ResultInfoWrapper.SEMIFINAL_SCORE),
                    resultInfoWrapper.getStageResultInfoMap().get(ResultInfoWrapper.FINAL_SCORE)
            );
            data.append("Full Name:");
            data.append(resultInfoWrapper.getFullName());
            data.append("\nRank:");
            for(int i = 0; i < stageResultInfoList.size(); i++){
                ResultInfoWrapper.StageResultInfo stageResultInfo = stageResultInfoList.get(i);
                String rank="*";
                if(stageResultInfo != null){
                    rank= String.valueOf(stageResultInfo.getRank());
                }
                String suffix= i == stageResultInfoList.size()-1 ? "\n": " | ";
                data.append(rank).append(suffix);
            }

            for(int i = 0; i < stageResultInfoList.size(); i++){
                ResultInfoWrapper.StageResultInfo stageResultInfo = stageResultInfoList.get(i);
                String scoreInfo="*";
                if(stageResultInfo != null){
                    scoreInfo= stageResultInfo.getScoreInfo();
                }
                String prefix=switch (i){
                    case 0 -> ResultInfoWrapper.PRELIMINARY_SCORE;
                    case 1 -> ResultInfoWrapper.SEMIFINAL_SCORE;
                    case 2 -> ResultInfoWrapper.FINAL_SCORE;
                    default -> throw new IllegalStateException("invalid stage");
                };
                data.append(prefix).append(":").append(scoreInfo);
                if(i != stageResultInfoList.size()-1){
                    data.append("\n");
                }
            }
            data.append(DataCrawler.SEPARATOR);
        }

        return data.toString();
    }
}
