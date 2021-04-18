package G4Challenge;

//import java.util.HashMap;

public class Serie {

	private String marca, modelo, anio_fabricacion;
	//private static int idSerie = 0;
	//private HashMap<Integer, String[]> serie = new HashMap<Integer, String[]>();
	String[] serieType = new String[3];
	
	public Serie(String marca, String modelo, String anio_fabricacion) {
		this.modelo = modelo;
		this.marca = marca;
		this.anio_fabricacion = anio_fabricacion;
		serieType[0] = marca.toLowerCase();
		serieType[1] = modelo.toLowerCase();
		serieType[2] = anio_fabricacion;
		/*if (idSerie == 0 || !exists()) {
			idSerie++;
		}
		serie.put(idSerie, serieType);*/
	}
	
	/*public boolean exists() {
		
		if(serie.containsValue(serieType) == false) {
			return false;
		}
		return true;
	}*/

	
	public String toString() {
		return "\nBrand: " + marca + "\nModel: " + modelo + "\nFabrication Year: " + anio_fabricacion;
	}
}
