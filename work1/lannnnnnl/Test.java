
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Booth B1 = new Booth(101, "java", 1000, false);
		Booth B2 = new Booth(102, "python", 300, false);
		Booth B3 = new Booth(103, "C++", 200, true);
		System.out.println(B1.toString());
		B1.purchase(B1,500);
		B2.purchase(B2,100);
		B3.purchase(B3,300);
		Booth []booths= {B1 ,B2,B3};
		Booth.closeBooths(booths);
		B1.restock(300);
		B2.restock(100);
		System.out.println(B2.toString());
		B3.restock(-1);
		System.out.println(B1.toString());
	}

}

