package G4Challenge;

import java.util.HashMap;

public class Truck extends Vehicle{
	
	private int carga;
	private char tipoMercancia;
	//private HashMap<String, Vehicle> serieListTruck = new HashMap<String, Vehicle>();
	
	public Truck(String numBastidor, String matricula, String colour,int numAsientos, int precio, int serie, int carga, char tipoMercacia) {
		super(numBastidor, matricula, colour, numAsientos, precio, serie);
		this.carga = carga;
		this.tipoMercancia = tipoMercacia;
	}

	public String getNumBastidor() {
		return super.getNumBastidor();
	}
	
	public String getColour() {
		return super.getColour();
	}
	
	public String getMatricula() {
		return super.getMatricula();
	}

		
	
	public int getCarga() {
		return carga;
	}

	public char getTipoMercancia() {
		return tipoMercancia;
	}
}
