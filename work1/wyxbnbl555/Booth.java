package study;

public class Booth {
	private long id;
	private String name;
	private int total;
	private boolean isClosed;
	 
	public Booth() {
		
	}
	public Booth(long id, String name, int total, boolean isClosed) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
		this.isClosed = isClosed;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public  int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	public static void purchase(Booth merchant,int num) {
		boolean pass = true;
		if(merchant.isClosed()) {
			pass = false;
			System.out.println("商家不能处于休摊整改状态");
		}else if(num>merchant.getTotal()) {
			pass = false;
			System.out.println("购买西瓜数不能大于在售西瓜数");
		}else if(num<0) {
			System.out.println("购买的西瓜数必须为正数");
		}else {
			System.out.println("交易成功");
			merchant.setTotal(merchant.getTotal()-num);
		}
		
	}
	
	public void restock(int num1) {
		if(num1<=200) {
			System.out.println("进货成功");
		}else if(num1>200) {
			System.out.println("进货失败");
		}
	}
	
	public static void closeBooths(Booth[] booths) {
		for(int i=0;i<booths.length;i++) {
			if(booths[i].isClosed()) {
				System.out.println(booths[i].toString());
			}else {
				booths[i].setClosed(true);
			}
		}
	}
	
	public String toString() {
		return "watermelon [id=" + id + ", name=" + name + ", total=" + total + ", isClosed=" + isClosed + "]";
	}
	

}
