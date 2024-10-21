package petStore;

public class ZhonghuatianyuanDog extends Animal{
	private boolean isVaccineInjected;
	public ZhonghuatianyuanDog() {
		super();
		this.setName("ZhonghuatianyuanDog");
		this.setPrice(100);
		this.isVaccineInjected = false;	
	}
	public ZhonghuatianyuanDog(String name,int age,String gender,boolean isVaccineInjected)  {
		super(name,age,gender,100);
		this.isVaccineInjected = isVaccineInjected;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("["+this.getName()+"-"+this.getAge()+"-"
				+"-"+this.getPrice()+"-"
				+this.getGender()+"-"+this.isVaccineInjected+"]");
	}
	@Override
	public void setPrice(double price) {
		super.setPrice(100);
	}
}
