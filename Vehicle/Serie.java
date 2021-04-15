package Vehicle;

import java.util.HashMap;

public class Serie {

	private String marca, modelo, año_fabricacion;
	private static int idSerie = 0;
	private HashMap<Integer, String[]> serie = new HashMap<Integer, String[]>();
	String[] serieType = new String[3];
	
	public Serie(String marca, String modelo, String año_fabricacion) {
		this.modelo = modelo;
		this.marca = marca;
		this.año_fabricacion = año_fabricacion;
		serieType[0] = marca;
		serieType[1] = modelo;
		serieType[2] = año_fabricacion;
		if (idSerie == 0 || !exists()) {
			idSerie++;
		}
		serie.put(idSerie, serieType);
	}
	
	public boolean exists() {
		boolean condition = false;
		serie.containsValue(serieType);
		return condition;
	}
	
	public String toString() {
		return "Id Serie: " + idSerie + "\nBrand: " + marca + "\nModel: " + modelo + "\nFabrication Year: " + año_fabricacion;
	}
}
