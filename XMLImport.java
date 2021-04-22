package XML;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import G4Challenge.Car;
import G4Challenge.Connector;
import G4Challenge.Truck;

public class XMLImport {

	private Connector conn;
	SAXBuilder SAX = new SAXBuilder();

	private org.jdom2.Document doc;

	ResultSet rs;
	Element element;

	public boolean inportation() {

		try {

			File file = new File("F:\\RETO WORKSPACE\\G4CHALLENGE\\src\\XML");

			doc = SAX.build(file);

			element = doc.getRootElement();

			List<Element> list = element.getChildren("serie");

			for (int count = 0; count < list.size(); count++) {
				Element idSerie = list.get(count);

				String marca = idSerie.getChildText("Marca");
				String modelo = idSerie.getChildText("Modelo");
				String anio = idSerie.getChildText("Anio_publicacion");

				int year = Integer.parseInt(anio);

				conn.saveSerie(marca, modelo, year);
				
				return true;
			}

			list = element.getChildren("coches");

			for (int i = 0; i < list.size(); i++) {
				Element coche = list.get(i);

				List<Element> carValues = coche.getChildren("coche");

				for (int count = 0; count < carValues.size(); count++) {
					Element campo = carValues.get(count);

					String numBastidor = campo.getChildText("numBastidor");
					String matricula = campo.getChildText("matricula");
					String colour = campo.getChildText("colour");
					String numPuertas = campo.getChildText("numPuertas");
					String capacidadMaletero = campo.getChildText("capacidadMaletero");
					String numAsientos = campo.getChildText("numAsientos");
					String precio = campo.getChildText("precio");
					String idSerie = campo.getChildText("serie");

					int numPuertas1 = Integer.parseInt(numPuertas);
					int capacidadMaletero1 = Integer.parseInt(capacidadMaletero);
					int numAsientos1 = Integer.parseInt(numAsientos);
					int precio1 = Integer.parseInt(precio);
					int serieId = Integer.parseInt(idSerie);

					Car c1 = new Car(numBastidor, matricula, colour, numPuertas1, capacidadMaletero1, numAsientos1,
							precio1, serieId);
					conn.saveCar(c1);
					return true;
				}
			}

			list = element.getChildren("camiones");

			for (int i = 0; i < list.size(); i++) {
				Element camion = list.get(i);

				List<Element> truckValues = camion.getChildren("camion");

				for (int count = 0; count < truckValues.size(); count++) {
					Element campo = truckValues.get(count);

					String numBastidor = campo.getChildText("numBastidor");
					String matricula = campo.getChildText("matricula");
					String colour = campo.getChildText("colour");
					String carga = campo.getChildText("carga");
					String tipoMercancia = campo.getChildText("tipoMercancia");
					String numAsientos = campo.getChildText("numAsientos");
					String precio = campo.getChildText("precio");
					String idSerie = campo.getChildText("idSerie");

					int numAsientos1 = Integer.parseInt(numAsientos);
					int precio1 = Integer.parseInt(precio);
					int serieId = Integer.parseInt(idSerie);
					int carga1 = Integer.parseInt(carga);
					char tipoMercancia1 = tipoMercancia.charAt(0);

					Truck t1 = new Truck(numBastidor, matricula, colour, numAsientos1, precio1, serieId, carga1,
							tipoMercancia1);
					conn.saveTruck(t1);
					return true;
				}
			}

		} catch (JDOMException e) {
			System.out.println("Fichero XML not valid");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File valid");
			e.printStackTrace();
		}
		return false;
	}
}
