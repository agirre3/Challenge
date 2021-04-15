package Vehicle;

public abstract class Vehicle {

	private int numBastidor, numAsientos, precio;
	private String colour, matricula;
	private Serie serie;

	public Vehicle(int numBastidor, String colour, String matricula, int numAsientos, int precio, Serie serie) {
		this.numBastidor = numBastidor;
		this.colour = colour;
		this.numAsientos = numAsientos;
		this.precio = precio;
		this.serie = serie;
		this.matricula = matricula;
	}
	
	public int getNumBastidor() {
		return numBastidor;
	}
	
}