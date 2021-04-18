package G4Challenge;

import java.sql.SQLException;

public class Menu {

	
	public void showMenu() {
		System.out.println("Option 1: Buy a car."
						+ "\nOption 2: Buy a truck."
						+ "\nOption 3: Sell a car/truck."
						+ "\nOption 4: Show on the screen the stock"
						+ "\nOption 5: Show on the screen the historic"
						+ "\nOption 6: Modify setting of a car"
						+ "\nOption 7: Modify setting of a truck"
						+ "\nOption 8: Exit.");
	
	}
	
	private void checkOption(int option) {
		while (option < 1 && option > 8) {
			System.out.println("Incorrect option.");
			System.out.println("Option 1: Buy a car."
					+ "\nOption 2: Buy a truck."
					+ "\nOption 3: Sell a car/truck."
					+ "\nOption 4: Show on the screen the stock"
					+ "\nOption 5: Show on the screen the historic"
					+ "\nOption 6: Modify setting of a car"
					+ "\nOption 7: Modify setting of a truck"
					+ "\nOption 8: Exit.");
			option = Console.readInt();
		}
	}
	
	public int recogerUserOption() {
		int option = Console.readInt();
		checkOption(option);
		return option;
	}
	
	public String getBrand() {
	
		System.out.println("Give me the brand");
		String marca = Console.readString();
		return marca;
	}
	
	public String getModel() {
		
		System.out.println("Give me the model");
		String model = Console.readString();
		
		return model;
	}
	
	public String getYear() {
		
	System.out.println("Give me the year of fabrication. 4 characters, please");
	String year = Console.readString();
	while(year.length() != 4) {
		System.out.println("Wrong year. Please, give me the 4 characters of the year");
		year = Console.readString();
	}
		
		return year;
	
	}
	
	
	
	public Car newCar() {
		
		System.out.println("Give me doors number (3 or 5)");
		int numPuertas = Console.readInt();
		
		while(numPuertas != 3 && numPuertas != 5) {	
			System.out.println("Wrong number of doors. 3 or 5");
			numPuertas = Console.readInt();
		}
		
		System.out.println("Give me seat number (4,5 or 9)");
		int numAsientos = Console.readInt();
		
		while(numAsientos < 4 && numAsientos > 9) {	
			System.out.println("Wrong number of doors. between 4 - 9");
			numAsientos = Console.readInt();
		}
		
		System.out.println("Give the capacity of the porter (between 100-2000)");
		int capacidadMaletero = Console.readInt();
		
		while(capacidadMaletero < 100 && capacidadMaletero > 2000) {
			System.out.println("Wrong capacity of the porter (between 100-2000)");
			capacidadMaletero = Console.readInt();
		}
	
		System.out.println("Give me the colour of the car: blue, red, green, yellow, grey, black, brown, white, orange or pink");
		String colour = Console.readString().toLowerCase();
		while(!colour.equals("blue") && !colour.equals("red") && !colour.equals("green") && !colour.equals("yellow") && !colour.equals("grey") && !colour.equals("black") && !colour.equals("brown")  && colour.equals("white")
				&& !colour.equals("orange")  && !colour.equals("pink")) {
			System.out.println("Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
			colour = Console.readString().toLowerCase();
		}
		
		System.out.println("Give me the price of the car (less than 200.000");
		int price = Console.readInt();
		while(price > 200000) {
			System.out.println("Wrong price. Give me a price under 200.000");
		}
		
		System.out.println("Give me the number of bastidor. 17 characters");
		String numBastidor = Console.readString();
		while(numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}
		
		System.out.println("Give me the license plate. 7 characters maximum");
		String matricula = Console.readString().toUpperCase();
		
		while(matricula.length() < 7 || matricula.length() > 7) {
			System.out.println("Wrong number of license plate. 7 characters");
		}
		
		int serie = serie();
		
		Car car = new Car(numBastidor, colour, matricula, numAsientos, price, serie, numPuertas, capacidadMaletero);
		
		return car;
		
	}
	
	
	public Truck newTruck() {
		
		System.out.println("Give me the container's load limit. Maximum 100.000");
		int carga = Console.readInt();
		
		while(carga < 0 && carga > 100000) {
			System.out.println("Wrong container's load limit. Maximum 100.000");
			carga = Console.readInt();
		}
		
		System.out.println("Give me seat number (2 or 3)");
		int numAsientos = Console.readInt();
		
		while(numAsientos != 2 && numAsientos != 3) {	
			System.out.println("Wrong number of doors. 2 or 3");
			numAsientos = Console.readInt();
		}
		
		System.out.println("Give the type of merchandise (one character: G, A or P)");
		char tipoMercancia = Console.readChar();
		while(tipoMercancia != 'G' && tipoMercancia != 'g' && tipoMercancia != 'A'
				&& tipoMercancia != 'A' &&  tipoMercancia != 'p' && tipoMercancia != 'P') {
			System.out.println("Wrong type of merchandise (one character: G, A or P)");
		}
	
		System.out.println("Give me the colour of the car: blue, red, green, yellow, grey, black, brown, white, orange or pink");
		String colour = Console.readString().toLowerCase();
		while(colour != "blue" && colour != "red" && colour != "green" && colour != "yellow" && colour != "grey" && colour != "black" && colour != "brown" && colour != "white"
				&& colour != "orange" && colour != "pink") {
			System.out.println("Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
			colour = Console.readString().toLowerCase();
		}
		
		System.out.println("Give me the price of the car (less than 200.000)");
		int price = Console.readInt();
		while(price > 200000) {
			System.out.println("Wrong price. Give me a price under 200.000");
		}
		
		System.out.println("Give me the number of bastidor. 17 characters");
		String numBastidor = Console.readString();
		while(numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}
		
		System.out.println("Give me the license plate. 7 characters maximum");
		String matricula = Console.readString().toUpperCase();
		
		while(matricula.length() < 7 || matricula.length() > 7) {
			System.out.println("Wrong number of license plate. 7 characters");
		}
		
		int serie = serie();
		
		Truck truck = new Truck(numBastidor, colour, matricula, numAsientos, price, serie, carga, tipoMercancia);
		
		return truck;
		
	}
	
	public int showMenu2() {
		System.out.println("What do you want to change? Choose an option");
		System.out.println("Option 1: numBastidor"
						+ "\nOption 2: colour"
						+ "\nOption 3: matricula."
						+ "\nOption 4: numAsientos"
						+ "\nOption 5: price"
						+ "\nOption 6: numPuertas"
						+ "\nOption 7: capacidadMaletero"
						+ "\nOption 8: Exit.");
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
					+ "\nOption 6: numPuertas"
					+ "\nOption 7: capacidadMaletero"
					+ "\nOption 8: Exit.");
			option = Console.readInt();
		}
	}	
	
	public void modifyCar(int option) {
		System.out.println("What car do you want to modify? Give me the numBastidor");
		String numBastidor = Console.readString();
		while(numBastidor.length() < 17 || numBastidor.length() > 17) {
			System.out.println("Wrong number of bastidor. 17 characters");
			numBastidor = Console.readString();
		}
		
		
	}
	
	/*public 
		
		int option = recogerUserOption2();
		
		if (option == 1) {
			System.out.println("Give me the new number of bastidor. 17 characters");
			numBastidor = Console.readString();
			while(numBastidor.length() < 17 || numBastidor.length() > 17) {
				System.out.println("Wrong number of bastidor. 17 characters");
				numBastidor = Console.readString();
			}
			
		}
		else if (option == 2){
			System.out.println("Give me the new colour of the car: blue, red, green, yellow, grey, black, brown, white, orange or pink");
			String colour = Console.readString().toLowerCase();
			while(colour != "blue" && colour != "red" && colour != "green" && colour != "yellow" && colour != "grey" && colour != "black" && colour != "brown" && colour != "white"
					&& colour != "orange" && colour != "pink") {
				System.out.println("Wrong colour. Give me a correct colour: blue, red, green, yellow, grey, black, brown, white, orange or pink");
				colour = Console.readString().toLowerCase();
			}
		}
		else if (option == 3) {
			System.out.println("Give me the license plate. 7 characters maximum");
		String matricula = Console.readString().toUpperCase();
		
		while(matricula.length() < 7 || matricula.length() > 7) {
			System.out.println("Wrong number of license plate. 7 characters");
		}
		}
		else if (option == 4) {
			System.out.println("Give me the new seat number (2 or 3)");
			int numAsientos = Console.readInt();
			
			while(numAsientos != 2 && numAsientos != 3) {	
				System.out.println("Wrong number of doors. 2 or 3");
				numAsientos = Console.readInt();
			}
		}
		else if (option == 5) {
			System.out.println("Give me the new price of the car (less than 200.000)");
			int price = Console.readInt();
			while(price > 200000) {
				System.out.println("Wrong price. Give me a price under 200.000");
			}
		}
		else if (option == 6) {
			System.out.println("Give me the new doors number (3 or 5");
			int numPuertas = Console.readInt();
			
			while(numPuertas != 3 && numPuertas != 5) {	
				System.out.println("Wrong number of doors. 3 or 5");
				numPuertas = Console.readInt();
			}
		}
		else if (option == 7) {
			System.out.println("Give the capacity of the porter (between 100-2000)");
			int capacidadMaletero = Console.readInt();
			
			while(capacidadMaletero < 100 && capacidadMaletero > 2000) {
				System.out.println("Wrong capacity of the porter (between 100-2000)");
				capacidadMaletero = Console.readInt();
			}
		}
		
		else {
			System.out.println("Bye.");
		}
		
		System.out.println("Correct");
	}*/
	
	public int choose() {
		
		System.out.println("The vehicle is a car (option 1) or a truck (option 2)?"
				+ " Give me the option, please");
		int option = Console.readInt();
				
		while (option != 1 && option !=2) {
			System.out.println("Wrong option. Choose an option: car (option 1), truck (option 2)");
			option = Console.readInt();
		}
		
		return option;
	}
	
	public String numOfBastidor() {
		
		System.out.println("Please, give me the number of bastidor of the car");
		String numOfBastidor = Console.readString();
		return numOfBastidor;
	}
	
}
