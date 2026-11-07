package core.exceptions;

// about data.json
public class DataPathException extends ReadJsonException {
    public DataPathException(String info) {
        super(info);
    }
}
