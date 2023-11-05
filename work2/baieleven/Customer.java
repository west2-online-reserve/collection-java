import java.time.LocalDate;

public class Customer {
	private String name;
	private int time;
	private LocalDate lastArrivalTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public LocalDate getLastArrivalTime() {
		return lastArrivalTime;
	}
	public void setLastArrivalTime(LocalDate lastArrivalTime) {
		this.lastArrivalTime = lastArrivalTime;
	}
	
	
	public Customer(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", time=" + time + ", lastArrivalTime=" + lastArrivalTime + "]";
	}
}
