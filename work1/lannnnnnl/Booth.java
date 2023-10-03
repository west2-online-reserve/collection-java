public class Booth {
	private long id;
	private String name;
	private int total;
	private boolean isClosed;
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
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
		this.name = name;
        this.total = total;
        this.isClosed = isClosed;
	}
	 public String toString(){
			return  "ID: " + id + "\n" +
	                "NAME: " + name + "\n" +
	                "Number of watermelon " + total + "\n"+
	                "Closed ? "+ isClosed +"\n";
	 }
	 public static void purchase(Booth marchant, int shop ) {
		 if(shop>marchant.getTotal()) {
			 System.out.println("供应不足，麻烦少买一点");
		 }
		 if(shop<marchant.getTotal()) {
			 marchant.setTotal(marchant.getTotal()-shop);
			 System.out.println("购买成功，欢迎下次光临");
		 }
	 }
	 public  void restock(int num) {
		 if(num>200) {
			 System.out.println("进货量过大");
		 }
		 else if(num<0) {
			 System.out.println("无效数据");
		 }
		 else {
			 System.out.println("进货成功");
			 this.total = total + num;
		 }
	 }
	 public static void closeBooths(Booth[] booths){
		 for(int i =0;i<booths.length;i++) {
			 if(booths[i].isClosed()) {
				 System.out.println(booths[i].toString());
			 }
			 else if(!booths[i].isClosed()) {
				 booths[i].setClosed(true);
			 }
		 }
	 }
}
