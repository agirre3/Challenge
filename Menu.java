package G4Challenge;


public class Menu {

	public void showMenu() {
		System.out.println("Option 1: Buy a car." + "\nOption 2: Buy a truck." + "\nOption 3: Sell a car/truck."
				+ "\nOption 4: Show on the screen the stock" + "\nOption 5: Modify setting of a car" + "\nOption 6: Modify setting of a truck"
				+ "\nOption 7: Show on screen the sales between two dates" + "\nOption 8: XML" + "\nOption 9:Exit.");

	}

	private void checkOption(int option) {
		while (option < 1 && option > 9) {
			System.out.println("Incorrect option.");
			System.out.println("Option 1: Buy a car." + "\nOption 2: Buy a truck." + "\nOption 3: Sell a car/truck."
					+ "\nOption 4: Show on the screen the stock" + "\nOption 5: Modify setting of a car" + "\nOption 6: Modify setting of a truck"
					+ "\nOption 7: Show on screen the sales between two dates" + "\nOption 8: XML" + "\nOption 9:Exit.");
			option = Console.readInt();
		}
	}

	public int recogerUserOption() {
		int option = Console.readInt();
		checkOption(option);
		return option;
	}

	public String getBrand() {
		System.out.println("We're going to built the SERIE of the car");
		System.out.println("Give me the brand");
		String marca = Console.readString();
		return marca;
	}

	public String getModel() {

		System.out.println("Give me the model");
		String model = Console.readString();

		return model;
	}

	public int getYear() {

		System.out.println("Give me the year of fabrication. 4 characters, please");
		int year = Console.readInt();
		while (year > 2021 || year < 1990) {
			System.out.println("Wrong year. Please, give me the 4 characters of the year");
			year = Console.readInt();
		}
		return year;

	}

	public Car newCar() {

		System.out.println("Give me doors number (3 or 5)");
		int numPuertas = Console.readInt();

		while (numPuertas != 3 && numPuertas != 5) {
			System.out.println("Wrong number of doors. 3 or 5");
			numPuertas = Console.readInt();
		}

		System.out.println("Give me seat number (between 4 and 9)");
		int numAsientos = Console.readInt();

		while (numAsientos < 4 || numAsientos > 9) {
			System.out.println("Wrong number of doors. between 4 - 9");
			numAsientos = Console.readInt();
		}

		System.out.println("Give the capacity of the porter (between 100-2000)");
		int capacidadMaletero = Console.readInt();

		while (capacidadMaletero < 100 && capacidadMaletero > 2000) {
			System.out.println("Wrong capacity of the porter (between 100-2000)");
			capacidadMaletero = Console.readInt();
		}

		System.out.println(
				"Give me the colour of the car: blue, red, green, yellow, grey, black, brown, white, orange or pink");
		String colour = Console.readString().toLowerCase();
		while (!colour.equals("blue") && !colour.equals("red") && !colour.equals("green") && !colour.equals("yellow")
				&& !colour.equals("grey") && !colour.equals("black") && !colour.equals("brown")
				&& !colour.equals("white") && !colour.equals("orange") && !colour.equals("pink")) {
			System.out.println(
					"Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
			colour = Console.readString().toLowerCase();
		}

		System.out.println("Give me the price of the car (less than 200.000)");
		int price = Console.readInt();
		while (price > 200000) {
			System.out.println("Wrong price. Give me a price under 200.000");
		}

		System.out.println("Give me the number of bastidor. 17 characters");
		String numBastidor = Console.readString();
		while (numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}

		System.out.println("Give me the license plate. 7 characters maximum");
		String matricula = Console.readString().toUpperCase();

		while (matricula.length() < 7 || matricula.length() > 7) {
			System.out.println("Wrong number of license plate. 7 characters");
			matricula = Console.readString().toUpperCase();
		}

		
		Car car = new Car(numBastidor, colour, matricula, numAsientos, price, -1, numPuertas, capacidadMaletero);
				
		return car;

	}

	public Truck newTruck() {

		System.out.println("Give me the container's load limit. Maximum 100.000");
		int carga = Console.readInt();

		while (carga < 0 && carga > 100000) {
			System.out.println("Wrong container's load limit. Maximum 100.000");
			carga = Console.readInt();
		}

		System.out.println("Give me seat number (2 or 3)");
		int numAsientos = Console.readInt();

		while (numAsientos != 2 && numAsientos != 3) {
			System.out.println("Wrong number of doors. 2 or 3");
			numAsientos = Console.readInt();
		}

		System.out.println("Give the type of merchandise (one character: G, A or P)");
		char tipoMercancia = Console.readChar();
		while (tipoMercancia != 'G' && tipoMercancia != 'g' && tipoMercancia != 'A' && tipoMercancia != 'a'
				&& tipoMercancia != 'p' && tipoMercancia != 'P') {
			System.out.println("Wrong type of merchandise (one character: G, A or P)");
			tipoMercancia = Console.readChar();
		}

		System.out.println("Give me the colour of the car: blue, red, green, yellow, grey, black, brown, white, orange or pink");
		String colour = Console.readString().toLowerCase();
		while (!colour.equals("blue") && !colour.equals("red") && !colour.equals("green") && !colour.equals("yellow")  && !colour.equals("grey") 
				&& !colour.equals("black")  && !colour.equals("brown")  && !colour.equals("white" ) && !colour.equals("orange") 
				&& !colour.equals("pink")) {
			System.out.println(
					"Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
			colour = Console.readString().toLowerCase();
		}

		System.out.println("Give me the price of the truck (less than 200.000)");
		int price = Console.readInt();
		while (price > 200000) {
			System.out.println("Wrong price. Give me a price under 200.000");
		}

		System.out.println("Give me the number of bastidor. 17 characters");
		String numBastidor = Console.readString();
		while (numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}

		System.out.println("Give me the license plate. 7 characters maximum");
		String matricula = Console.readString().toUpperCase();

		while (matricula.length() < 7 || matricula.length() > 7) {
			System.out.println("Wrong number of license plate. 7 characters");
			matricula = Console.readString().toUpperCase();
		}

		Truck truck = new Truck(numBastidor, matricula, colour, numAsientos, price, -1, carga, tipoMercancia);

		return truck;

	}

	public int showMenu2() {
		System.out.println("What do you want to change? Choose an option");
		System.out.println("Option 1: numBastidor" + 
		"\nOption 2: colour" + "\nOption 3: matricula."
				+ "\nOption 4: numAsientos" + "\nOption 5: price" + "\nOption 6: numPuertas"
				+ "\nOption 7: capacidadMaletero" + "\nOption 8: Exit.");
		int option = Console.readInt();
		checkOption(option);

		return option;

	}

	private void checkOption2(int option) {
		while (option < 1 && option > 8) {
			System.out.println("Incorrect option.");
			System.out.println("Option 1: numBastidor" 
					+ "\nOption 2: colour" 
					+ "\nOption 3: matricula."
					+ "\nOption 4: numAsientos" 
					+ "\nOption 5: price" 
					+ "\nOption 6: number of doors"
					+ "\nOption 7: capacity of the porter" 
					+ "\nOption 8: Exit.");
			option = Console.readInt();
		}
	}

	public String getStringCarModification(int attributeToChange) {
		
		if(attributeToChange == 1) {
			System.out.println("Give me the new number of bastidor  of the car");
			String numBastidor = Console.readString();
			while (numBastidor.length() < 17 || numBastidor.length() > 17) {
				System.out.println("Wrong number of bastidor. 17 characters");
				numBastidor = Console.readString();
			}
			return numBastidor;
		}
		else if(attributeToChange == 2) {
			System.out.println("Give me the new colour of the car:blue, red, green, yellow, grey, black, brown, white, orange or pink");
			String colour = Console.readString().toLowerCase();
			while (!colour.equals("blue") && !colour.equals("red") && !colour.equals("green") && !colour.equals("yellow")  && !colour.equals("grey") 
					&& !colour.equals("black")  && !colour.equals("brown")  && !colour.equals("white" ) && !colour.equals("orange") 
					&& !colour.equals("pink")) {
				System.out.println(
						"Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
				colour = Console.readString().toLowerCase();
			}
			return colour;
		}
		else {
			System.out.println("Give me the new matricula");
			String matricula = Console.readString();
			while (matricula.length() < 7 || matricula.length() > 7) {
				System.out.println("Wrong number of license plate. 7 characters");
				matricula = Console.readString();
			}
			return matricula;
		}
		
	}

	public int getIntCarModification(int attributeToChange) {
		
		if(attributeToChange == 4) {
			System.out.println("Give me seat number (between 4 and 9)");
			int numAsientos = Console.readInt();
			
			while (numAsientos < 4 || numAsientos > 9) {
				System.out.println("Wrong number of seats. (between 4 and 9)");
				numAsientos = Console.readInt();
			}	
			return numAsientos;
		}
			
		else if(attributeToChange == 5) {
			System.out.println("Give me the new price of the car (less than 200.000)");
				int price = Console.readInt();
				while (price > 200000) {
					System.out.println("Wrong price. Give me a price under 200.000");
				}
			
			return price;
		}
		
		else if(attributeToChange == 6) {
			System.out.println("Give me the new doors number (3 or 5)");
			int numPuertas = Console.readInt();

			while (numPuertas != 3 && numPuertas != 5) {
				System.out.println("Wrong number of doors. 3 or 5");
				numPuertas = Console.readInt();
			}
			return numPuertas;
		}
		else {
			System.out.println("Give me the new capacity of the porter(between 100 and 2000)");
			int capacidadMaletero = Console.readInt();

			while (capacidadMaletero < 100 && capacidadMaletero > 2000) {
				System.out.println("Wrong capacity of the porter (between 100-2000)");
				capacidadMaletero = Console.readInt();
			}
				return capacidadMaletero;
		}
	}

	
	public String getStringTruckModification(int attributeToChange) {
		
		if(attributeToChange == 1) {
			System.out.println("Give me the new number of bastidor of the car");
			String numBastidor = Console.readString();
			while (numBastidor.length() < 17 || numBastidor.length() > 17) {
				System.out.println("Wrong number of bastidor. 17 characters");
				numBastidor = Console.readString();
			}
			return numBastidor;
		}
		else if(attributeToChange == 2) {
			System.out.println("Give me the new colour of the truck:blue, red, green, yellow, grey, black, brown, white, orange or pink");
			String colour = Console.readString().toLowerCase();
			while (!colour.equals("blue") && !colour.equals("red") && !colour.equals("green") && !colour.equals("yellow")  && !colour.equals("grey") 
					&& !colour.equals("black")  && !colour.equals("brown")  && !colour.equals("white" ) && !colour.equals("orange") 
					&& !colour.equals("pink")) {
				System.out.println(
						"Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
				colour = Console.readString().toLowerCase();
			}
			return colour;
		}
		else if(attributeToChange == 3){
			System.out.println("Give me the new matricula");
			String matricula = Console.readString();
			while (matricula.length() < 7 || matricula.length() > 7) {
				System.out.println("Wrong number of license plate. 7 characters");
				matricula = Console.readString();
			}
			return matricula;
		}
		else{
			System.out.println("Give the type of merchandise (one character: G, A or P)");
			char tipoMercancia = Console.readChar();
			while (tipoMercancia != 'G' && tipoMercancia != 'g' && tipoMercancia != 'A' && tipoMercancia != 'a'
					&& tipoMercancia != 'p' && tipoMercancia != 'P') {
				System.out.println("Wrong type of merchandise (one character: G, A or P)");
				tipoMercancia = Console.readChar();
			}
			return tipoMercancia + "";
		}
		
	}

	public int getIntTruckModification(int attributeToChange) {
				
		if(attributeToChange == 5) {
			System.out.println("Give me the container's load limit. Maximum 100.000");
			int carga = Console.readInt();

			while (carga < 0 && carga > 100000) {
				System.out.println("Wrong container's load limit. Maximum 100.000");
				carga = Console.readInt();
			}
			return carga;
		}
		else if(attributeToChange == 6) {
			System.out.println("Give me seat number (2 or 3)");
			int numAsientos = Console.readInt();
			
			while (numAsientos !=2 && numAsientos !=3) {
				System.out.println("Wrong number of seats. (2 or 3)");
				numAsientos = Console.readInt();
			}	
			return numAsientos;
		}
			
		else {
			System.out.println("Give me the new price of the car (less than 200.000)");
				int price = Console.readInt();
				while (price > 200000) {
					System.out.println("Wrong price. Give me a price under 200.000");
				}
			
			return price;
		}
		
	}
	

	public String getVehicleNumOfBastidor() {
		System.out.println("What vehicle do you want to change/delete? Give me the numBastidor");
		String numBastidor = Console.readString();
		while (numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}
		return numBastidor;
	}
	

 
	public void getResultOfOperation(boolean result){
		
		if(result == true) {
			System.out.println("Operation done");
		}
		else {
			System.out.println("Operation not done");
		}
		
	}
	
	public int showMenu3() {
		System.out.println("What do you want to change? Choose an option");
		System.out.println("Option 1: numBastidor" + 
							"\nOption 2: colour" 
							+ "\nOption 3: matricula."
						+ "\nOption 4: tipoMercancia"
						+ "\nOption 5: carga" 
						+ "\nOption 6: numAsientos" 
						+ "\nOption 7: precio" + "\nOption 8: Exit.");
		int option = Console.readInt();
		checkOption(option);

		return option;

	}
	

	public int choose() {

		System.out.println("The vehicle is a car (option 1) or a truck (option 2)? Give me the option, please");
		int option = Console.readInt();

		while (option != 1 && option != 2) {
			System.out.println("Wrong option. Choose an option: car (option 1), truck (option 2)");
			option = Console.readInt();
		}

		return option;
	} 
	
	public void vehicleNotExists() {
		System.out.println("Your vehicle doesn't exists");
	}
	
	public String getADate() {
		
		System.out.println("Give me a date in this format YYYY-MM-DD");
		String date = Console.readString();
		
		return date;
	}
	
	public int chooseXML() {
		
		System.out.println("You have chosen XML option. Do you want to export (option 1) or import(option 2)?");
		int option = Console.readInt();
		while(option != 1 && option != 2) {
			System.out.println("Wrong option. Export XML --> 1, Import XML --> 2");
			option = Console.readInt();
		}
		return option;
		
	}
	public void operationDone() {
		
		System.out.println("The operation is done");
	}
	
	public void operationNotDone() {
		
		System.out.println("ERROR: The operation is not done. Repeat the operation");
	}

	public void exit() {
		System.out.println("You choose exit. Bye");

	}
	
	public String getUbicationFile() {
		System.out.println("Give me file's ubication");
		String ubication = Console.readString();
		return ubication;
	}
	
}
