package west2;

public class ChineseFarmDog extends Animal{
	private boolean isVaccineInjected;
	public ChineseFarmDog(String name, int age, String Gender, boolean isVaccineInjected) {
		super(name, age, 100.0, Gender);
		this. isVaccineInjected=isVaccineInjected;
	}

	@Override
	public String toString() {
		return "ChineseFarmDog{" +
	               "name='" + name + '\'' +
	               ", age=" + age +
	               ", Gender='" + Gender + '\'' +
	               ", price=" + price +
	               ", isVaccineInjected=" + isVaccineInjected +
	               '}';
		
	}
		
}
