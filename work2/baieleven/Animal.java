public abstract class Animal {
	protected String name;
	protected int age;
	protected String gender;
	protected double price;
	public Animal(String name, int age, String gender, double price) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.price = price;
	}
	@Override
	public abstract String toString();
	
}
