package Vehicle;

public class Truck extends Vehicle{
	
	private String carga;
	private char tipoMercancia;
	
	public Truck(int numBastidor, String colour, int numAsientos, int precio, int serie, String carga, char tipoMercacia) {
		super(numBastidor, colour, numAsientos, precio, serie);
		this.carga = carga;
		this.tipoMercancia = tipoMercacia;
	}
}
