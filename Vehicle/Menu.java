package Vehicle;

import java.util.Date;

public class Menu {

	private static final int SALE_CAR = 1, SALE_TRUCK = 2, PURCHASE = 3, EXIT = 4 ;
	
	public void menu() {
		System.out.println("Option 1: Buy a car."
						+ "\nOption 2: Buy a truck."
						+ "\nOption 3: Sell a car/truck."
						+ "\nOption 4: Exit.");
		int option = Console.readInt();
		checkOption(option);
		do {			
			if (option == SALE_CAR) {
				new Sale().buyCar();			
			}
			else if (option == SALE_TRUCK){
				new Sale().buyTruck();		
			}
			else if (option == PURCHASE) {
				new Purchase.sellVehicle();
			}
			else {
				System.out.println("Bye.");
			}
			option = Console.readInt();
			checkOption(option);
		}while (option >= SALE_CAR && option <= PURCHASE);
	}
	
	private void checkOption(int option) {
		while (option < 1 && option > 4) {
			System.out.println("Incorrect option.");
			System.out.println("Option 1: Buy a car."
					+ "\nOption 2: Buy a truck."
					+ "\nOption 3: Sell a car/truck."
					+ "\nOption 4: Exit.");
			option = Console.readInt();
		}
	}
}
