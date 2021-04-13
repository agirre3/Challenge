package Vehicle;

public class Car extends Vehicle{
	
	private int numPuertas, capacidadMaletero;

	public Car(int numBastidor, String colour, int numAsientos, int precio, int serie, int numPuertas, int capacidadMaletero) {
		super(numBastidor, colour, numAsientos, precio, serie);
		this.numPuertas = numPuertas;
		this.capacidadMaletero = capacidadMaletero;
	}
}
