/**
 * @author admin
 */
public class AnimalNotFoundException extends RuntimeException {
    private String warning = "暂无动物可买";

    @Override
    public String toString() {
        return warning;
    }
}
