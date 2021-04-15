package Vehicle;

import java.util.HashMap;

public class Car extends Vehicle{
	
	private int numPuertas;
	private int capacidadMaletero;
	private HashMap<String, Vehicle> serieListCar = new HashMap<String, Vehicle>();

	public int getNumPuertas() {
		return numPuertas;
	}

	public int getCapacidadMaletero() {
		return capacidadMaletero;
	}

	public Car(int numBastidor, String colour, String matricula, int numAsientos, int precio, Serie serie, int numPuertas, int capacidadMaletero) {
		super(numBastidor, colour, matricula, numAsientos, precio, serie);
		this.numPuertas = numPuertas;
		this.capacidadMaletero = capacidadMaletero;
	}
}
