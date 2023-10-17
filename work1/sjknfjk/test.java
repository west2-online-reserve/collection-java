package work1;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Booth booth1 = new Booth(122,"张三",777,false);
	        Booth booth2 = new Booth(177,"李四",2330,true);

	        Booth.purchase(booth2,1111);
	        Booth.purchase(booth1,1111);
	        Booth.purchase(booth1,-1111);
	        Booth.purchase(booth1,111);

	        booth2.restock(1111);
	        booth2.restock(-111);
	        booth2.restock(111);

	        Booth[] booths ={booth1,booth2};
	        Booth.closeBooths(booths);
	}

}
