package G4Challenge;


//import java.util.HashMap;

public class Car extends Vehicle{
	
	private int numPuertas;
	private int capacidadMaletero;
	//private HashMap<String, Vehicle> serieListCar = new HashMap<String, Vehicle>();

	public Car(String numBastidor, String matricula, String colour,int numAsientos, int precio, int serie, int numPuertas, int capacidadMaletero) {
		super(numBastidor, colour, matricula, numAsientos, precio, serie);
		this.numPuertas = numPuertas;
		this.capacidadMaletero = capacidadMaletero;
	}

	
	public int getNumPuertas() {
		return numPuertas;
	}

	public int getCapacidadMaletero() {
		return capacidadMaletero;
	}
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	public void setCapacidadMaletero(int capacidadMaletero) {
		this.capacidadMaletero = capacidadMaletero;
	}
	
	
	
}
