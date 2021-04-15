package Vehicle;

import java.util.HashMap;

public class Serie {

	private String marca, modelo, a�o_fabricacion;
	private static int idSerie = 0;
	private HashMap<Integer, String[]> serie = new HashMap<Integer, String[]>();
	String[] serieType = new String[3];
	
	public Serie(String marca, String modelo, String a�o_fabricacion) {
		this.modelo = modelo;
		this.marca = marca;
		this.a�o_fabricacion = a�o_fabricacion;
		serieType[0] = marca;
		serieType[1] = modelo;
		serieType[2] = a�o_fabricacion;
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
		return "Id Serie: " + idSerie + "\nBrand: " + marca + "\nModel: " + modelo + "\nFabrication Year: " + a�o_fabricacion;
	}
}
