package G4Challenge;



public class Serie {

	private String marca, modelo;
	int anio_fabricacion;
	
	String[] serieType = new String[3];
	
	public Serie(String marca, String modelo, int anio_fabricacion) {
		this.modelo = modelo;
		this.marca = marca;
		this.anio_fabricacion = anio_fabricacion;
		
	}
	
	
	public String toString() {
		return "\nBrand: " + marca + "\nModel: " + modelo + "\nFabrication Year: " + anio_fabricacion;
	}
}
