package watermelon;

import java.util.Scanner;
  
class Stand{
	private long ID;
	private String name;
	private static int quantity;
	private static boolean isclosed;
	private static int length; 
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
		if(need>quantity) {
			System.out.println("存货不足!");
		}//购买数大于存货以及购买数小于零的情况
		else if(need<0) {
			System.out.println("购买数小于零!");
		}
		else if(isclosed==true) {
			System.out.println("西瓜摊正在歇业!");
		}//西瓜摊歇业的情况  
		else {
			quantity=quantity-need;
		}//购买成功的情况
		return quantity;
	}
	public void restock(int stocking) {//定义实例方法进货
		if(stocking>200) {
			System.out.println("进货失败!");
		}//进货量过多的情况
		else {
			quantity=quantity+stocking;
		}//进货成功的情况
	}
	public static boolean closeStands(Stand[] Stands){
		for(int i=0;i<Stands.length;i++) {
			if(Stands[i].getIsclosed()==true) {
				System.out.println(Stands[i]);
			}
			else if(Stands[i].getIsclosed()==false) {
				Stands[i].setIsclosed(true);
			}
		}
		return isclosed;
		
	} //closeStands方法令摊位歇业整改
	public String toString() {
		return "摊号 :"+ID+" |摊主姓名:"+name+" |剩余西瓜数 :"+quantity;
	}//重写的toString()方法输出摊位的所有内容
}
