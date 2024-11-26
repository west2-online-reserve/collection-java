package petStore;

public class Rabbit extends Animal{
	public Rabbit() {
		super();
		this.setName("Rabbit");
		this.setPrice(50);
	}
	public Rabbit(String name,int age,String gender) {
		super(name,age,gender,50);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("["+this.getName()+"-"+this.getAge()+"-"
				+"-"+this.getPrice()+"-"
				+this.getGender()+"]");
	}
	@Override
	public void setPrice(double price) {
		super.setPrice(50);
	}
}
