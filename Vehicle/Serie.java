package Vehicle;

import java.util.HashMap;

public class Serie {

	private String brand, model;
	private int year;
	private static int idSerie = 0;
	private HashMap<Integer, String[]> serie = new HashMap<Integer, String[]>();
	
	public Serie(String brand, String model, int year) {
		this.model = model;
		this.brand = brand;
		this.year = year;
		idSerie++;
	}
	
	public boolean exists(String brand, String model, int year) {
		boolean condition = false;
		return condition;
	}
	
	public String toString() {
		return "Brand: " + brand + "\nModel: " + model + "\nFabrication Year: " + year;
	}
}
