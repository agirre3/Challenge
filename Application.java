package G4Challenge;

public class Application {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		Connector accessBD = new Connector();
		Controller control = new Controller(menu, accessBD);

		control.run();
		
	}

}
