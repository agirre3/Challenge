package G4Challenge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import G4Challenge.Car;
import G4Challenge.Connector;
import G4Challenge.Serie;
import G4Challenge.Truck;

/*This class is used for export database to a XML file*/

public class XMLExport {

	private Connector conn;

	public XMLExport() {
		conn = new Connector();
	}

	public boolean exportation() {

	
		Element concesionario = new Element("concesionario");
		Document doc = new Document(concesionario);

		Element vehicles = new Element("vehicles");

		Element serie = new Element("serie");
		concesionario.addContent(serie);

		List<Serie> series = conn.getSerieData();

		for (Serie s : series) {

			Element marca = new Element("marca");
			marca.setText(s.getMarca());

			Element modelo = new Element("modelo");
			modelo.setText(s.getModelo());

			Element anio_fabricacion = new Element("anio_fabricacion");
			anio_fabricacion.setText("" + s.getAnio_fabricacion());

			serie.addContent(marca);
			serie.addContent(modelo);
			serie.addContent(anio_fabricacion);

			vehicles.addContent(serie);
		}

		Element car = new Element("car");

			List<Car> cars = conn.getCarData();
			
			for (Car c1 : cars) {

				Element numBastidor = new Element("numBastidor");
				numBastidor.setText(c1.getNumBastidor());
				Element matricula = new Element("matricula");
				matricula.setText(c1.getMatricula());
				Element colour = new Element("colour");
				colour.setText(c1.getColour());
				Element numAsientos = new Element("numAsientos");
				numAsientos.setText(""+ c1.getNumAsientos());
				Element numPuertas = new Element("numPuertas");
				numPuertas.setText("" + c1.getNumPuertas());
				Element capacidadMaletero = new Element("capacidadMaletero");
				capacidadMaletero.setText("" + c1.getCapacidadMaletero());
				Element precio = new Element("precio");
				precio.setText("" + c1.getPrecio());
				Element idSerie = new Element("idSerie");
				precio.setText("" + c1.getSerie());

				car.addContent(numBastidor);
				car.addContent(matricula);
				car.addContent(colour);
				car.addContent(numAsientos);
				car.addContent(numPuertas);
				car.addContent(capacidadMaletero);
				car.addContent(precio);
				car.addContent(idSerie);

				vehicles.addContent(car);
			}

		Element truck = new Element("truck");
		List<Truck> trucks = conn.getTruckData();

			for (Truck t1 : trucks) {

				Element numBastidor1 = new Element("numBastidor");
				numBastidor1.setText(t1.getNumBastidor());
				Element matricula1 = new Element("matricula");
				matricula1.setText(t1.getMatricula());
				Element colour1 = new Element("colour");
				colour1.setText(t1.getColour());
				Element numAsientos1 = new Element("numAsientos");
				numAsientos1.setText(""+ t1.getNumAsientos());
				Element precio1 = new Element("precio");
				precio1.setText("" + t1.getPrecio());
				Element carga = new Element("carga");
				carga.setText("" + t1.getCarga());
				Element tipoMercancia = new Element("tipoMercancia");
				tipoMercancia.setText("" + t1.getTipoMercancia());
				Element idserie = new Element("idSerie");
				idserie.setText("" + t1.getSerie());
				truck.addContent(numBastidor1);
				truck.addContent(matricula1);
				truck.addContent(colour1);
				truck.addContent(numAsientos1);
				truck.addContent(carga);
				truck.addContent(tipoMercancia);
				truck.addContent(precio1);
				truck.addContent(idserie);

				vehicles.addContent(truck);
	

		XMLOutputter xml = new XMLOutputter();
		xml.setFormat(Format.getPrettyFormat());
		
		try {
			xml.output(doc, new FileWriter("C:\\Users\\ik012982i9\\Desktop\\concesionario.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			return true;
	}
}
