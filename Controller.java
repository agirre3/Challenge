package G4Challenge;

public class Controller {

	private Menu menu;
	private Connector accessDB;

	private static final int PURCHASE_CAR = 1, PURCHASE_TRUCK = 2, SALE = 3, STOCK = 4, HISTORIC = 5,
			MODIFY_SETTINGS_CAR = 6, MODIFY_SETTINGS_TRUCK = 7, QUERY_TWODATES = 8, EXIT = 9;

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

				int iDSerie = accessDB.saveSerie(brand, model, year);

				Car car1 = menu.newCar();
				car1.setSerie(iDSerie);

				try {
					accessDB.saveCar(car1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (option == PURCHASE_TRUCK) {
				String brand = menu.getBrand();
				String model = menu.getModel();
				String year = menu.getYear();

				int iDSerie = accessDB.saveSerie(brand, model, year);

				Truck truck1 = menu.newTruck();
				truck1.setSerie(iDSerie);

				try {
					accessDB.saveTruck(truck1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (option == SALE) {
				int typeOfvehicle = menu.choose();
				try {
					accessDB.deleteVehicle(menu.getNumOfBastidor());
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (option == STOCK) {
				// new Purchase().sell();
			} else if (option == HISTORIC) {
				// new Purchase().sell();
			} else if (option == MODIFY_SETTINGS_CAR) {
				/*String modification = menu.modifyCar();
				if(accessDB.existsCar(modification) == true) {
					menu.showCar(modification);
				}
				else {
					//el coche que has introducido no existe.Agur.Opción 9
					option = 9;
				}*/
				String numOfBastidor = menu.getNumOfBastidor();
				if(accessDB.existsCar(numOfBastidor) == true) {
					int attributeToChange = menu.showMenu2();
					String newData = "";
					
					if(attributeToChange == 1 || attributeToChange == 2 || attributeToChange == 3) {
						if(attributeToChange == 1){
							newData = menu.getStringCarModification(attributeToChange);
						}
						else if(attributeToChange == 2) {
							newData = menu.getStringCarModification(attributeToChange);
						}
						else if(attributeToChange == 3) {
							newData = menu.getStringCarModification(attributeToChange);
						}
						accessDB.updateCar(attributeToChange, numOfBastidor, newData);
					}
					else if(attributeToChange == 4 || attributeToChange == 5 || attributeToChange == 6 	|| attributeToChange == 7) {
						int newData2 = 0;
						
						if(attributeToChange == 4){
							newData2 = menu.getIntCarModification(attributeToChange);
						}
						else if(attributeToChange == 5){
							newData2 = menu.getIntCarModification(attributeToChange);
						}
						else if(attributeToChange == 6){
							newData2 = menu.getIntCarModification(attributeToChange);
						}
						else if(attributeToChange == 7){
							newData2 = menu.getIntCarModification(attributeToChange);
							
						}
					}
					else {
						System.out.println("exit");
						//esto no se puede hacer
					}
				}
				else{
					//The car doesn't exists
				}

			} else if (option == MODIFY_SETTINGS_TRUCK) {
				// new Purchase().sell();
			}

			else if (option == QUERY_TWODATES) {
				// new Purchase().sell();
			} else {
				System.out.println("Bye.");
			}
			option = Console.readInt();

		} while (option >= PURCHASE_CAR && option <= MODIFY_SETTINGS_TRUCK);

	}

}
