package bonus;

public class Bonus {
	public static void main(String [] args) {
		System.out.println(checkValid("2154697@aa.com"));
	}
//	public static void method1(int[] arr1,int []arr2) {
//		Thread t1 = new Thread();
//		Thread t2 = new Thread();
//		
//	}
	public static boolean checkValid(String s) {
		String regex ="\\w+@\\w+(\\.\\w)?\\.com";
		return s.matches(regex);
	}
}
