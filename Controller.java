package G4Challenge;

public class Controller {

	private Menu menu;
	private Connector accessDB;
	
	private static final int PURCHASE_CAR = 1, PURCHASE_TRUCK = 2, SALE = 3,
			STOCK = 4, HISTORIC = 5, MODIFY_SETTINGS_CAR = 6, MODIFY_SETTINGS_TRUCK = 7,
			EXIT = 8;
	
	public Controller(Menu menu, Connector accessDB) {
		
		this.menu = menu;
		this.accessDB = accessDB;
		
	}
		
	public void run() {
		
		int option = 0;
		do {
			menu.showMenu();
			option = menu.recogerUserOption();
			if (option == PURCHASE_CAR) {
				
				String brand = menu.getBrand();
				String model = menu.getModel();
				String year = menu.getYear();
				
				int serie = accessDB.saveSerie(brand, model, year);
				
				Car car1 = menu.newCar();
				
				try {
					accessDB.saveCar(car1);				
				}
				catch(Exception e) {
		    		e.printStackTrace();
		    	}
			}
			else if (option == PURCHASE_TRUCK){
				Truck truck1 = menu.newTruck();
				try {
					accessDB.saveTruck(truck1);				
				}
				catch(Exception e) {
		    		e.printStackTrace();
		    	}
			}
			else if (option == SALE) {
				int typeOfvehicle = menu.choose();
				try {
				accessDB.deleteVehicle(typeOfvehicle, menu.numOfBastidor());
				}
				catch(Exception e) {
		    		e.printStackTrace();
		    	}

				
			}
			else if (option == STOCK) {
				//new Purchase().sell();
			}
			else if (option == HISTORIC) {
				//new Purchase().sell();
			}
			else if (option == MODIFY_SETTINGS_CAR) {
				int attributeToChange = menu.showMenu2();
				menu.modifyCar(attributeToChange);
				
				
				
				
				//new Purchase().sell();
			}
			else if (option == MODIFY_SETTINGS_TRUCK) {
				//new Purchase().sell();
			}
			else {
				System.out.println("Bye.");
			}
			option = Console.readInt();
			
		}while (option >= PURCHASE_CAR && option <= MODIFY_SETTINGS_TRUCK);
			
	
		
		
	}
	
	
}
