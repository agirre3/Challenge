package Vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sale {

	private List<Vehicle> vehicleList= new ArrayList<Vehicle>();
	
	public void buyCar() {
		int i = 0;
		if (i < vehicleList.size()) {
			while (i < vehicleList.size()) {
				System.out.print("Car chassis number: ");
				int numBastidor = Console.readInt();
				System.out.println();
				System.out.print("Car´s colour: ");
				String colour = Console.readString();
				System.out.println();
				System.out.print("Number plate: ");
				String matricula = Console.readString();
				System.out.println();
				System.out.print("Seats number: ");
				int numAsientos = Console.readInt();
				System.out.println();
				System.out.print("Price: ");
				int precio = Console.readInt();
				System.out.println();
				System.out.print("Brand: ");
				String brand = Console.readString();
				System.out.println();
				System.out.print("Model: ");
				String model = Console.readString();
				System.out.println();
				System.out.print("Year: ");
				String year = Console.readString();
				Serie serie = new Serie(brand, model, year);
				System.out.println();
				System.out.print("Doors: ");
				int numPuertas = Console.readInt();
				System.out.println();
				System.out.print("Porter capacity: ");
				int capacidadMaletero = Console.readInt();
				Car c = new Car(numBastidor, colour, matricula, numAsientos, precio, serie, numPuertas, capacidadMaletero);
				if (existsCar(c)) {
					sellCar(c);						
				}
				else {
					System.out.println("There is no car with those features");
				}
			}
		}
		else {
			System.out.println("There are no vehicles in stock");
		}
	}
	
	public void buyTruck() {
		int i = 0;
		if (i < vehicleList.size()) {
			while (i < vehicleList.size()) {
				System.out.print("Truck chassis number: ");
				int numBastidor = Console.readInt();
				System.out.println();
				System.out.print("Truck´s colour: ");
				String colour = Console.readString();
				System.out.println();
				System.out.print("Number plate: ");
				String matricula = Console.readString();
				System.out.println();
				System.out.print("Seats number: ");
				int numAsientos = Console.readInt();
				System.out.println();
				System.out.print("Price: ");
				int precio = Console.readInt();
				System.out.println();
				System.out.print("Brand: ");
				String brand = Console.readString();
				System.out.println();
				System.out.print("Model: ");
				String model = Console.readString();
				System.out.println();
				System.out.print("Year: ");
				String year = Console.readString();
				Serie serie = new Serie(brand, model, year);
				System.out.println();
				System.out.print("Load: ");
				int carga = Console.readInt();
				System.out.println();
				System.out.print("Merchandise type: ");
				char tipoMercancia = Console.readChar();
				Truck t = new Truck(numBastidor, colour, matricula, numAsientos, precio, serie, carga, tipoMercancia);
				if (existsTruck(t)) {
					sellTruck(t);						
				}
				else {
					System.out.println("There is no truck with those features");
				}
			}
		}
		else {
			System.out.println("There are no vehicles in stock");
		}
	}
	
	private boolean existsCar(Car car) {
		boolean condition = false;
		if (vehicleList.contains(car)) {
			condition = true;
		}
		else {
			condition = false;
		}
		return condition;
	}
	
	private boolean sellCar(Car car) {
		boolean condition = false;
		int counter = 0;
		Iterator<Vehicle> it = vehicleList.iterator();
		while (it.hasNext()) {
			if (vehicleList.contains(car) == false) {
				counter++;
			}
		}
		if (counter != vehicleList.size()) {
			condition = true;
			vehicleList.remove(car);
		}
		else {
			condition = false;
		}
		return condition;
	}

	private boolean existsTruck(Truck truck) {
		boolean condition = false;
		if (vehicleList.contains(truck)) {
			condition = true;
		}
		else {
			condition = false;
		}
		return condition;
	}
	
	private boolean sellTruck(Truck truck) {
		boolean condition = false;
		int counter = 0;
		Iterator<Vehicle> it = vehicleList.iterator();
		while (it.hasNext()) {
			if (vehicleList.contains(truck) == false) {
				counter++;
			}
		}
		if (counter != vehicleList.size()) {
			condition = true;
			vehicleList.remove(truck);
		}
		else {
			condition = false;
		}
		return condition;
	}

	

}
