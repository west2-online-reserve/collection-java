import java.time.LocalDate;
import java.util.ArrayList;
public class MyAnimalShop implements AnimalShop{
	private double balance;
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private boolean isOpened;
		private double dailyProfit = 0;
		public MyAnimalShop(double balance, boolean isOpened) {
		super();
		this.balance = balance;
		this.isOpened = isOpened;
	}
	@Override
	public void buyAnimal(Animal animal) {
		try{
			if(balance >= animal.price) {
				balance -= animal.price;
				animals.add(animal);
				System.out.println("成功购入" + animal.name + ",花费" + animal.price + "元，剩余余额" + balance + "元");
				dailyProfit -= animal.price;
			}else {
				throw new InsufficientBalanceException("余额不足");
			}
		}catch(InsufficientBalanceException ibe) {
			System.out.println(ibe.toString());
		}
	}
	@Override
	public void entertainCustomer(Customer customer) {
		try{
			if(!isOpened) {
				System.out.println("这个宠物店已歇业");
				return;
			}
			if(animals.size() != 0) {
				boolean isNew = true;
				for (Customer i : customers) {
					if(customer.getName() == i.getName()) {
						isNew = false;
					}
				}
				if(isNew) {
					customers.add(customer);
				}
				customer.setTime(customer.getTime() + 1);
                customer.setLastArrivalTime(LocalDate.now());
				int num = (int) (Math.random() * animals.size());
				balance += animals.get(num).price + 50;
				dailyProfit += animals.get(num).price + 50;//售出价格比进货价格统一高出50元
				System.out.println("成功售出" + animals.get(num).name + ",获得" + (animals.get(num).price + 50) + "元，剩余余额" + balance + "元");
				System.out.println("售出动物信息：" + animals.get(num).toString());
				animals.remove(num);
			}else {
				throw new AnimalNotFountException("店内无宠物可售出");
			}
		}catch(AnimalNotFountException anfe) {
			System.out.println(anfe.toString());
		}
	}
	@Override
	public void close() {
		System.out.println("今日顾客：");
		for (Customer i : customers) {
			System.out.println(i.toString());
		}
		System.out.println("今日利润：" + dailyProfit);
	}
	
}
