package G4Challenge;

/*The father of the two types of object that we have: trucks and cars*/

public abstract class Vehicle {

	private int numAsientos, precio;
	private String colour, matricula, numBastidor;
	private int serie;

	public Vehicle(String numBastidor, String matricula, String colour, int numAsientos, int precio, int serie) {
		this.numBastidor = numBastidor;
		this.matricula = matricula;
		this.colour = colour;
		this.numAsientos = numAsientos;
		this.precio = precio;
		this.serie = serie;
		
	}
	
	public int getSerie() {
		return serie;
	}

	public String getNumBastidor() {
		return numBastidor;
	}

	public int getNumAsientos() {
		return numAsientos;
	}

	public int getPrecio() {
		return precio;
	}

	public String getColour() {
		return colour;
	}

	public String getMatricula() {
		return matricula;
	}


	public void setNumAsientos(int numAsientos) {
		this.numAsientos = numAsientos;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNumBastidor(String numBastidor) {
		this.numBastidor = numBastidor;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	
	
}