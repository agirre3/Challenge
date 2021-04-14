package Vehicle;

import java.util.HashMap;

public class Truck extends Vehicle{
	
	private int carga;
	private char tipoMercancia;
	private HashMap<String, Vehicle> serieListTruck = new HashMap<String, Vehicle>();
	
	public Truck(int numBastidor, String colour, int numAsientos, int precio, Serie serie, int carga, char tipoMercacia) {
		super(numBastidor, colour, numAsientos, precio, serie);
		this.carga = carga;
		this.tipoMercancia = tipoMercacia;
		Truck t = new Truck(numBastidor, colour, numAsientos, precio, serie, carga, tipoMercancia);
	}

	public int getCarga() {
		return carga;
	}

	public char getTipoMercancia() {
		return tipoMercancia;
	}
}
