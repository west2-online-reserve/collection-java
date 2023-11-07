interface Exception {
    class AnimalNotFoundException extends RuntimeException {
        public AnimalNotFoundException(String message) {
            super(message);
        }
    }

    class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

}
