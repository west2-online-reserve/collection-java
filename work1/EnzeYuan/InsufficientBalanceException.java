package petStore;

public class InsufficientBalanceException extends RuntimeException{
	public InsufficientBalanceException() {
		super();
	}
	public InsufficientBalanceException(String s) {
		super(s);
	}
}
