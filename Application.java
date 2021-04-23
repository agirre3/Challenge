package G4Challenge;

/*This class is used to run the application*/

public class Application {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		Connector accessBD = new Connector();
		XMLExport exporter = new XMLExport();
		XMLImport importer = new XMLImport();
		Controller control = new Controller(menu, accessBD, exporter, importer);

		control.run();
		
	}

}
