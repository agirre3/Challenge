package Vehicle;

public class Purchase {

	//private 
	
	public void sell() {
		System.out.println("Option 1: Car."
						+ "\nOption 2: Truck.");
		int option = Console.readInt();
		checkOption(option);
		if (option == 1) {
			
		}
	}
	
	private void checkOption(int option) {
		while (option < 1 && option > 2) {
			System.out.println("Incorrect option.");
			System.out.println("Option 1: Car."
					+ "\nOption 2: Truck.");
			option = Console.readInt();
		}
	}
	
	private void sellCar() {
		
	}
}
