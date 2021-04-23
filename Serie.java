package G4Challenge;

/*A class to built series for cars and trucks*/

public class Serie {

	private String marca, modelo;
	int anio_fabricacion;
	
	String[] serieType = new String[3];
	
	public Serie(String marca, String modelo, int anio_fabricacion) {
		this.modelo = modelo;
		this.marca = marca;
		this.anio_fabricacion = anio_fabricacion;
		
	}
	
	
	
	
	public String getMarca() {
		return marca;
	}




	public String getModelo() {
		return modelo;
	}




	public int getAnio_fabricacion() {
		return anio_fabricacion;
	}




	public String[] getSerieType() {
		return serieType;
	}




	public String toString() {
		return "\nBrand: " + marca + "\nModel: " + modelo + "\nFabrication Year: " + anio_fabricacion;
	}
}
