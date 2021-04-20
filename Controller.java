package G4Challenge;

public class Controller {

	private Menu menu;
	private Connector accessDB;

	private static final int PURCHASE_CAR = 1, PURCHASE_TRUCK = 2, SALE = 3, STOCK = 4, MODIFY_SETTINGS_CAR = 5,
			MODIFY_SETTINGS_TRUCK = 6, QUERY_TWODATES = 7, XML = 8;

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
				int year = menu.getYear();

				while (brand == null || model == null || year <= 0) {
					brand = menu.getBrand();
					model = menu.getModel();
					year = menu.getYear();
				}

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
				int year = menu.getYear();

				while (brand == null || model == null || year <= 0) {
					brand = menu.getBrand();
					model = menu.getModel();
					year = menu.getYear();
				}

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
				String number = menu.getVehicleNumOfBastidor();
				if (typeOfvehicle == 1) {
					if (accessDB.existsCar(number) == true) {
						try {
							accessDB.deleteVehicle(typeOfvehicle, number);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						menu.vehicleNotExists();
					}
				} else {
					if (accessDB.existsTruck(number) == true) {
						try {
							accessDB.deleteVehicle(typeOfvehicle, number);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						menu.vehicleNotExists();
					}
				}
			} else if (option == STOCK) {
				accessDB.showStock();
			
			} else if (option == MODIFY_SETTINGS_CAR) {

				String numOfBastidor = menu.getVehicleNumOfBastidor();
				if (accessDB.existsCar(numOfBastidor) == true) {
					int attributeToChange = menu.showMenu2();
					String newData = "";
					int newData2 = 0;

					if (attributeToChange == 1 || attributeToChange == 2 || attributeToChange == 3) {
						newData = menu.getStringCarModification(attributeToChange);
						boolean result = accessDB.updateCar(attributeToChange, numOfBastidor, newData);
						menu.getResultOfOperation(result);
					} else if (attributeToChange == 4 || attributeToChange == 5 || attributeToChange == 6
							|| attributeToChange == 7) {

						newData2 = menu.getIntCarModification(attributeToChange);
						boolean result = accessDB.updateCar(attributeToChange, numOfBastidor, newData2);
						menu.getResultOfOperation(result);
					} else {
						menu.exit();
					}
				} else {
					menu.vehicleNotExists();
				}

			} else if (option == MODIFY_SETTINGS_TRUCK) {
				String numOfBastidor = menu.getVehicleNumOfBastidor();
				if (accessDB.existsTruck(numOfBastidor) == true) {
					int attributeToChange = menu.showMenu3();
					String newData = "";
					int newData2 = 0;

					if (attributeToChange == 1 || attributeToChange == 2 || attributeToChange == 3
							|| attributeToChange == 4) {
						newData = menu.getStringTruckModification(attributeToChange);
						boolean result = accessDB.updateTruck(attributeToChange, numOfBastidor, newData);
						menu.getResultOfOperation(result);
					} else if (attributeToChange == 5 || attributeToChange == 6 || attributeToChange == 7) {
						newData2 = menu.getIntTruckModification(attributeToChange);
						boolean result = accessDB.updateTruck(attributeToChange, numOfBastidor, newData2);
						menu.getResultOfOperation(result);
					} else {
						menu.exit();
					}
				} else {
					menu.vehicleNotExists();
				}
			}

			else if (option == QUERY_TWODATES) {
				String date1 = menu.getADate();
				String date2 = menu.getADate();
				accessDB.queryTwoDates(date1, date2);

			} else {
				menu.exit();
			}
			option = Console.readInt();

		} while (option >= PURCHASE_CAR && option <= MODIFY_SETTINGS_TRUCK);

	}

}
