public class BoothTest {
	public static void main(String []args) {
		Booth booths1 = new Booth(2,"林程辉",200,false);
		Booth booths2 = new Booth(3,"林毅晨",300,true);
		Booth.purchase(booths1,-1);
		booths1.restock(300);
		Booth[] boosts = new Booth[2];
		boosts[0] = booths1;
		boosts[1] = booths2;
		Booth.closeBooths(boosts);
	}
}
