package XML;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import G4Challenge.Connector;

public class XMLExport {
	
	
	private Connector conn;
	
	
	public XMLExport() {
		conn = new Connector();
	}
	
	public boolean exportation() {
	
	ResultSet rs;
	
	Element concesionario = new Element("concesionario");
	Document doc = new Document(concesionario);
	
	Element vehicles = new Element("vehicles");
	
	
		Element series = new Element("series");
		concesionario.addContent(series);

		rs = conn.getSerieData();
		
			try {
				while (rs.next()) {
					
					Element serie = new Element("serie");
					
					Element idSerie = new Element("idSerie");
					idSerie.setText(rs.getString(1));
					
					Element marca = new Element("marca");
					marca.setText(rs.getString(2));
					
					Element modelo = new Element("modelo");
					modelo.setText(rs.getString(3));
					
					Element anio_fabricacion = new Element("anio_fabricacion");
					anio_fabricacion.setText(rs.getString(4));
					
					serie.addContent(idSerie);
					serie.addContent(marca);
					serie.addContent(modelo);
					serie.addContent(anio_fabricacion);
					
					vehicles.addContent(serie);
				}
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	
	
	Element car = new Element("car");
	
	rs = conn.getCarData();
	
	try {
		while(rs.next()) {
			
			Element numBastidor = new Element("numBastidor");
			Element matricula = new Element("matricula");
			Element colour = new Element("colour");
			Element numAsientos = new Element("numAsientos");
			Element numPuertas = new Element("numPuertas");
			Element capacidadMaletero = new Element("capacidadMaletero");
			Element precio = new Element("precio");
			Element serie = new Element("serie");
			
			
			numBastidor.setText(rs.getString(1));
			matricula.setText(rs.getString(2));
			colour.setText(rs.getString(3));
			numAsientos.setText(rs.getString(4));
			numPuertas.setText(rs.getString(5));
			capacidadMaletero.setText(rs.getString(6));
			precio.setText(rs.getString(7));
			serie.setText(rs.getString(8));
			
			car.addContent(numBastidor);
			car.addContent(matricula);
			car.addContent(colour);
			car.addContent(numAsientos);
			car.addContent(numPuertas);
			car.addContent(capacidadMaletero);
			car.addContent(precio);
			car.addContent(serie);
			
			vehicles.addContent(car);
		}
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	Element truck = new Element("truck");
	rs = conn.getTruckData();
	
	try {
		while(rs.next()) {
			Element numBastidor = new Element("numBastidor");
			Element matricula = new Element("matricula");
			Element colour = new Element("colour");
			Element numAsientos = new Element("numAsientos");
			Element precio = new Element("precio");
			Element serie = new Element("serie");
			Element carga = new Element("carga");
			Element tipoMercancia = new Element("tipoMercancia");
			
			truck.addContent(numBastidor);
			truck.addContent(matricula);
			truck.addContent(colour);
			truck.addContent(numAsientos);
			truck.addContent(carga);
			truck.addContent(tipoMercancia);
			truck.addContent(precio);
			truck.addContent(serie);
			
			vehicles.addContent(truck);
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	XMLOutputter xml = new XMLOutputter();
	xml.setFormat(Format.getPrettyFormat());
	try {
		xml.output(doc, new FileWriter("concesionario.xml"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return false;
}

}
