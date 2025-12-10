package core.argument;

import lombok.Data;

import java.util.List;
@Data
public class Arguments {
    public static final String[] SPORTS = new String[]{"platform","springboard","synchronised"};
    List<Argument> arguments;

    public Arguments(List<Argument> arguments) {
        this.arguments = arguments;
    }
}
