package watermelon;

import java.util.Scanner;
  
public class Watermalon {
	public static void main(String[]args) {
		Stand watermalon=new Stand (0, null, 0,true);//定义西瓜摊
		Scanner scanner = new Scanner(System.in);
		System.out.print("摊号: ");
		int ID =scanner.nextInt();
		watermalon.setID(ID);
		System.out.print("摊主姓名: ");
		String name=scanner.next();
		watermalon.setName(name);
		System.out.print("在售西瓜数: ");
		Scanner scanner1 = new Scanner(System.in);
		int quantity=scanner1.nextInt();
		watermalon.setQuantity(quantity);
		System.out.print("是否修摊整改?: ");
		boolean isclosed=scanner1.nextBoolean();
		watermalon.setIsclosed(isclosed);//输入西瓜摊的全部信息
		watermalon.restock(0);//调用实例方法进货
		watermalon.purchase(0);//调用静态方法够买
		watermalon.closebooths();//调用静态方法使店铺歇业整改
		System.out.println(watermalon.toString());
	}
}  
class Stand{
	private long ID;
	private String name;
	private static int quantity;
	private static boolean isclosed; 
	//以上皆为私有变量
	public Stand(long ID,String name,int quantity,boolean isClosed) {
		this.ID=ID;
		this.name=name;
		this.quantity=quantity;
		this.isclosed=isclosed;
	}
	public int getID() {
		return (int) ID;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public boolean getIsclosed() {
		return  getIsclosed();
	}
	public void setIsclosed( boolean isclosed) {
		this.isclosed=isclosed;
	}//相应的get和set方法
	public static int purchase(int need) {//定义静态方法购买
		System.out.print("购买西瓜数量 : ");
		Scanner scanner=new Scanner(System.in);
		int need1=scanner.nextInt();
		need=need1;
		if(need>quantity&&need<0) {
			System.out.println("存货不足或购买数小于零!");
		}//购买数大于存货以及购买数小于零的情况
		else if(isclosed==true) {
			System.out.println("西瓜摊正在歇业!");
		}//西瓜摊歇业的情况  
		else {
			quantity=quantity-need;
		}//购买成功的情况
		return quantity;
	}
	public void restock(int stocking) {//定义实例方法进货
		Scanner scanner = new Scanner(System.in);
		System.out.print("需要进货的西瓜量 : ");
		int stocking1 =scanner.nextInt();
		stocking=stocking1;
		if(stocking>200) {
			System.out.println("进货失败!");
		}//进货量过多的情况
		else {
			quantity=quantity+stocking;
		}//进货成功的情况
	}
	public static  boolean closebooths(){
		boolean isclosed=true;
		return isclosed;
	} //closebooths方法令摊位歇业整改
	public String toString() {
		return "摊号 :"+ID+" |摊主姓名:"+name+" |剩余西瓜数 :"+quantity;
	}//重写的toString()方法输出摊位的所有内容
}
