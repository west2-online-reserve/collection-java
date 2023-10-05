package work1;

public class Booth {
	private long id;
	private String name;
	private int total;
	private boolean isClose;
	
	public Booth() {
		
	}
    public Booth(long id,String name,int total,boolean isClose) {
		this.id=id;
		this.name=name;
		this.total=total;
		this.isClose=isClose;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isClose() {
		return isClose;
	}
	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}
	
	public static void purchase(Booth booth,int sum) {
		if(sum>0 && !booth.isClose && sum<booth.total) {
			booth.total-=sum;
			System.out.println("Purchase Success");
			System.out.println(booth.toString());
		}else {
			System.out.println("Purchase Fail");
			System.out.println(booth.toString());
		}
	}
	
	public static void closeBooths(Booth[]booths) {
		for(int i=0;i<booths.length;i++) {
			if(booths[i].isClose) {
				System.out.println(booths[i].toString());
			}else {
				booths[i].isClose=true;
			}
		}
	}
	
	public void restock(int stock) {
		if(stock<200&&stock>0) {
			this.total+=stock;
		}else {
			System.out.println("Stock Fail");
		}
	}
	
	@Override
	public String toString() {
		return "Stall [id=" + id + ", name=" + name + ", total=" + total + ", isClose=" + isClose + "]";
	}
	
}
