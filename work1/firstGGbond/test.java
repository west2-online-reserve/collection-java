
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Booth B1 = new Booth(832202204, "Wangyu", 1000, false);
		System.out.println(B1.toString());
		B1.purchase(B1,500);
		Booth []booths= {B1};
		closeBooths(booths);
		B1.restock(300);
		B1.restock(100);
		System.out.println(B1.toString());
	}public static void closeBooths(Booth[] booths){
		 for(int i =0;i<booths.length;i++) {
			 booths[i].setClosed(true);
			 System.out.println(booths[i].toString());
		 }
	 }

}
