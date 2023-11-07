public class Dog extends Animal{
	private boolean isVaccineInjected;
	public Dog(String name, int age, String gender, boolean isVaccineInjected) {
		super(name, age, gender, 100);
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + ",isVaccineInjected=" + isVaccineInjected + "]";
	}

	public boolean isVaccineInjected() {
		return isVaccineInjected;
	}

	public void setVaccineInjected(boolean isVaccineInjected) {
		this.isVaccineInjected = isVaccineInjected;
	}
	
}
