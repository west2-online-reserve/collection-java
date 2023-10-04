class StoreNotOpen extends BaseException{
    public StoreNotOpen() {
    }

    public StoreNotOpen(String message) {
        super(message);
    }

    public StoreNotOpen(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNotOpen(Throwable cause) {
        super(cause);
    }

    public StoreNotOpen(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "当前店铺没有营业！";
    }
}