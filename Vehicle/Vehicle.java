package Vehicle;

public abstract class Vehicle {

	private int numBastidor, numAsientos, precio;
	private String colour;
	private Serie serie;

	public Vehicle(int numBastidor, String colour, int numAsientos, int precio, Serie serie) {
		this.numBastidor = numBastidor;
		this.colour = colour;
		this.numAsientos = numAsientos;
		this.precio = precio;
		this.serie = serie;
	}
	
	public int getNumBastidor() {
		return numBastidor;
	}
	
}