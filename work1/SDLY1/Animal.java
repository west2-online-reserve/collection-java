package west2;

public abstract class Animal {
	protected String name;
	protected int age;
	protected double price;
	protected String Gender;

	public Animal(String name, int age, double price, String Gender) {
		this.name = name;
		this.age = age;
		this.price = price;
		this.Gender = Gender;
	}

	public abstract String toString();

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
}
