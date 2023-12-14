public class Bird extends Animal{

	public Bird(String name, int age, String gender) {
		super(name, age, gender, 300);
	}

	@Override
	public String toString() {
		return "Bird [name=" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + "]";
	}

	
}
