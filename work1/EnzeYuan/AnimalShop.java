package petStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public interface AnimalShop {
	public default void boughtAnimal(MyAnimalShop mas,  Animal a) {
		
		  String[] arr = a.getClass().getName().split("\\.");
		//  System.out.println(Arrays.toString(arr));
		  String name = arr[arr.length-1];
	
			mas.getAnimalList().add(a);
			
			if("Cat".equalsIgnoreCase(name))  {
				if(mas.getCash()>200) mas.setCash((mas.getCash()-200));				
				else throw new InsufficientBalanceException();
			}
			else if("Rabbit".equalsIgnoreCase(name)) {
				if(mas.getCash()>50) mas.setCash((mas.getCash()-50));
				else throw new InsufficientBalanceException();
			}
			else {
				if(mas.getCash()>100) mas.setCash((mas.getCash()-100));
				else throw new InsufficientBalanceException();
			}
		
	}
	
	public abstract void service(Customer c) ;
	public default void checkCash(MyAnimalShop mas,LocalDate ld) {
		int res=0;
		for(Customer c : mas.getCustomerList()) {
			if(c.getLastTime().isEqual(ld)) res+=c.getPay();
		}
		System.out.println(ld+" earn :"+res);
	}
}
