package com.dwasearch.query.service;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandProcessor {
    private final CoreModule coreModule;
    private final Writer writer;
    private static final Map<String, String> StandardNameMap;
    static {
        StandardNameMap = new HashMap<>();
        StandardNameMap.put("women 3m springboard", "Women's 3m Springboard");
        StandardNameMap.put("women 10m platform",  "Women's 10m Platform");
        StandardNameMap.put("women 3m synchronised", "Women's 3m Synchronised");
        StandardNameMap.put("women 10m synchronised", "Women's 10m Synchronised");
        StandardNameMap.put("men 3m springboard", "Men's 3m Springboard");
        StandardNameMap.put("men 10m platform", "Men's 10m Platform");
        StandardNameMap.put("men 3m synchronised",  "Men's 3m Synchronised");
        StandardNameMap.put("men 10m synchronised", "Men's 10m Synchronised");
    }

    public CommandProcessor(CoreModule coreModule, Writer writer) {
        this.coreModule = coreModule;
        this.writer = writer;
    }

    //接收来自程序入口的命令集，并直接调用CoreModule类的输出方法
    public void process(List<String> commandLines) throws IOException {
        for (String commandLine : commandLines) {
            if (commandLine.isBlank()) {
                continue;
            }
            //将一整行命令切分为两部分来处理
            String[] parts = commandLine.split("\\s+", 2);
            String cmd = parts[0];
            String arg = parts.length > 1 ? parts[1] : "";

            if (cmd.equals("players") && arg.isEmpty()) {
                coreModule.outputPlayers(writer);
            }
            else if (cmd.equals("result")) {
                if (StandardNameMap.containsKey(arg)) {
                    coreModule.outputResults(StandardNameMap.get(arg), writer);
                }
                else {
                    coreModule.outputWrongMessages("N/A", writer);
                }
            }
            else {
                coreModule.outputWrongMessages("Error", writer);
            }
        }
    }
}
