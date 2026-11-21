package user.lib;

import java.util.ArrayList;

public class CommandParam {
    private final ArrayList<String> args;

    public CommandParam(ArrayList<String> args) {
        this.args = args;
    }

    public CommandParam(CommandParam param,int begin,int end) {
        this.args = new ArrayList<String>(param.args.subList(begin, end));
    }

    public CommandParam(CommandParam param,int begin) {
        // 除去第一个元素后的参数列表
        this.args = new ArrayList<String>(param.args.subList(begin, param.args.size()));
    }

    public String getFirstArg() {
        return args.getFirst();
    }

    public String getArg(int index) {
        return args.get(index);
    }

    // 几个参数
    public int size() {
        return args.size();
    }

    public boolean isEmpty() {
        return args.isEmpty();
    }


    /**
     * 将以 - 或 -- 为开头的参数视为功能参数
     * 将原参数以功能参数为分隔界切片，每个切片加上切片前的功能参数一起构造成新的参数对象 CommandParam，并依次组成列表返回
     * 最开头的切片前没有功能参数，直接构造参数对象即可
     * 例如：
     * men 10m platform --detail -f output.txt
     * -> men 10m platform
     *    --detail
     *    -f output.txt
     *
     * 以 this 为原数据，不修改原数据做切片组成 List 返回即可
     *
     * @return 切片后的参数对象列表，原参数为空时返回空列表
     */
    public ArrayList<CommandParam> paramSlice() {
        ArrayList<CommandParam> paramSlices = new ArrayList<>(args.size());
        if (args.isEmpty()) {
            return paramSlices;
        }

        // 功能参数是切片的起点，最开头的切片不含功能参数
        int sliceBegin = 0;
        for (int index = 1; index < args.size(); index++) {
            String arg = args.get(index);
            if (arg != null && arg.startsWith("-")) {
                paramSlices.add(new CommandParam(this, sliceBegin, index));
                sliceBegin = index;
            }
        }
        // 最后一个功能参数之后的参数同样要构成一个切片
        paramSlices.add(new CommandParam(this, sliceBegin, args.size()));

        return paramSlices;
    }
}
