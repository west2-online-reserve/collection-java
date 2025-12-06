package crawler.parser;

import crawler.DataCrawler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultInfoParser extends AbstractResultInfoParser{

    @Override
    public String parse(String rawData) {
        List<ResultInfoWrapper> resultInfoWrappers = parseInitially(rawData, false);

        resultInfoWrappers = resultInfoWrappers.stream()
                .filter(wrapper -> wrapper.getStageResultInfoMap().get(ResultInfoWrapper.FINAL_SCORE) != null)
                .collect(Collectors.toList());

        resultInfoWrappers.sort(Comparator.comparing(o-> o.getStageResultInfoMap().get(ResultInfoWrapper.FINAL_SCORE).getRank()));

        //估算每条结果约150字符
        int estimatedSize = resultInfoWrappers.size() * 150;
        StringBuilder data = new StringBuilder(estimatedSize);

        for (ResultInfoWrapper resultInfoWrapper : resultInfoWrappers) {
            ResultInfoWrapper.StageResultInfo finalStageResultInfo = resultInfoWrapper.getStageResultInfoMap().get(ResultInfoWrapper.FINAL_SCORE);
            data.append("Full Name:");
            data.append(resultInfoWrapper.getFullName());
            data.append("\nRank:");
            data.append(finalStageResultInfo.getRank());
            data.append("\nScore:");
            data.append(finalStageResultInfo.getScoreInfo());
            data.append(DataCrawler.SEPARATOR);
        }

        return data.toString();
    }

}
