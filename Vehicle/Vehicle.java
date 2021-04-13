package Vehicle;

public abstract class Vehicle {

	private int numBastidor, numAsientos, precio, serie;
	private String colour;
	
	public Vehicle(int numBastidor, String colour, int numAsientos, int precio, int serie) {
		this.numBastidor = numBastidor;
		this.colour = colour;
		this.numAsientos = numAsientos;
		this.precio = precio;
		this.serie = serie;
	}
	
}
