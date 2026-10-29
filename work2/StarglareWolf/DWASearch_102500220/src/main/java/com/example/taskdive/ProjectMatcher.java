package com.example.taskdive;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

import java.util.*;

public class ProjectMatcher {
    private static final List<String>VALID_PROJECTS = Arrays.asList(
            "players",
            "Men's 10m Platform",
            "Men's 3m Springboard",
            "Women's 10m Platform",
            "Women's 3m Springboard",
            "Men's 10m Synchronised",
            "Mixed 3m & 10m Team",
            "Men's 3m Synchronised",
            "Women's 10m Synchronised",
            "Women's 3m Synchronised"
    );

    public static String getProjectName(int index){
        return VALID_PROJECTS.get(index);
    }

    public static String matchDiscipline(String SearchName) {

        String ContrastSearchName = SearchName.toLowerCase().replace(" ", "").replace("'s", "");
        JaroWinklerSimilarity distance = new JaroWinklerSimilarity();

        double maxSimilarity = 0.0d;
        String goalInput = null;
        for (String project : VALID_PROJECTS){
            String ContrastProject = project.toLowerCase().replace(" ", "").replace("'s", "");
            double similarity = distance.apply(ContrastSearchName, "result" + ContrastProject);
            if (ContrastProject.equals(VALID_PROJECTS.getFirst())) {
                similarity = distance.apply(ContrastSearchName, ContrastProject);
            }
            if (similarity > maxSimilarity){
                maxSimilarity = similarity;
                goalInput = project;
            }
        }


        if (maxSimilarity > 0.75 && maxSimilarity < 0.95){
            System.out.println("Did you mean -- " + goalInput + " ?");
            return goalInput;
        }
        else if (maxSimilarity >= 0.95)
        {
            return goalInput;
        }
        else {
            System.out.println("Do not match any task, Error!\n-----");
            return null;
        }
    }
}
