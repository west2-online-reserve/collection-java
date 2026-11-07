package org.example;

import java.util.List;

public class QueryEngine {
    public String runQuery(List<String> cmdList, Data data) {
        StringBuilder Content = new StringBuilder();
        for(String s:cmdList){
            String[] parts = s.split(" ",2);
            if(parts.length==0){ continue; }
            if(parts[0].equals("players") && parts.length==1){
                Content.append(data.getPlayersContent());
            }else if(parts[0].equals("result")){
                if(parts.length!=2){
                    Content.append("N/A\n-----\n");
                    continue;
                }
                switch(parts[1]){
                    case "women 3m springboard" ->{
                        Content.append(data.getFinalsContent(0));
                    }
                    case "women 10m platform" ->{
                        Content.append(data.getFinalsContent(1));
                    }
                    case "women 3m synchronised" ->{
                        Content.append(data.getFinalsContent(2));
                    }
                    case "women 10m synchronised" ->{
                        Content.append(data.getFinalsContent(3));
                    }
                    case "men 3m springboard" ->{
                        Content.append(data.getFinalsContent(4));
                    }
                    case "men 10m platform" ->{
                        Content.append(data.getFinalsContent(5));
                    }
                    case "men 3m synchronised" ->{
                        Content.append(data.getFinalsContent(6));
                    }
                    case "men 10m synchronised" ->{
                        Content.append(data.getFinalsContent(7));
                    }
                    default ->{
                        Content.append("N/A\n-----\n");
                        continue;
                    }
                }
            }else{
                Content.append("Error\n-----\n");
                continue;
            }
        }
        return Content.toString();
    }
}
