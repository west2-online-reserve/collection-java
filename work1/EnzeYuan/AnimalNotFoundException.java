package petStore;

public class AnimalNotFoundException extends RuntimeException{
	public AnimalNotFoundException() {
		super();
	}
	public AnimalNotFoundException(String s) {
		super(s);
	}
}
