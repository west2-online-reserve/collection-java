class NoWantedAnimal extends BaseException{

    public NoWantedAnimal() {
    }

    public NoWantedAnimal(String message) {
        super(message);
    }

    public NoWantedAnimal(String message, Throwable cause) {
        super(message, cause);
    }

    public NoWantedAnimal(Throwable cause) {
        super(cause);
    }

    public NoWantedAnimal(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "无该顾客所想要的动物！";
    }
}

