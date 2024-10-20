package petStore;

public class Cat extends Animal{
	public Cat() {
		super();
		this.setName("Cat");
		this.setPrice(200);
	}
	public Cat(String name,int age,String gender) {
		super(name,age,gender,200);
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
		super.setPrice(200);
	}
}
