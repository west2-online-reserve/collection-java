package top.yangguangmc.dwasearch;

import java.util.ArrayList;
import java.util.List;

public class IODataSet {
    private final List<String> instructions = new ArrayList<>();
    private final List<Output> outputs = new ArrayList<>();

    public void addInstruction(String instruction) {
        instructions.add(instruction);
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public IODataSet println(String outputLine, Object... args) {
        if (outputs.isEmpty()) {
            nextOutputSection();
        }
        outputs.get(outputs.size() - 1).getLines().add(outputLine.formatted(args));
        return this;
    }

    public void nextOutputSection() {
        outputs.add(new Output());
    }

    public String getOutputs() {
        StringBuilder sb = new StringBuilder();
        for (Output output : outputs) {
            for (String line : output.getLines()) {
                sb.append(line).append("\n");
            }
            if (!output.getLines().isEmpty()) {
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }

    private static class Output {
        private final List<String> lines = new ArrayList<>(3);

        public List<String> getLines() {
            return lines;
        }
    }
}
