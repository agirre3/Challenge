package G4Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;


public class Connector {

	private static Connection conn;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "team4";
	private static final String password = "team4";
	private static final String url = "jdbc:mysql://10.14.1.15:3306/concesionario";
			//"jdbc:mysql://127.0.0.1/concesionario";
	
	//

	public Connector() {

		try {
			System.out.println("Connecting...");
			Class.forName(driver);
			System.out.println("Connecting...");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				System.out.println("Connected");
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Wrong conection" + e);
		}
	}

	public boolean queryBrand(String brand) {
		String query = "SELECT * FROM SERIE WHERE marca  = ?";
		int counter = 0;

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			ResultSet rs;
			
			stmt.setString(1, brand);
			
			rs = stmt.executeQuery();

			while (rs.next() && counter == 0) {
				String brand2 = rs.getString(2);

				if (brand.equalsIgnoreCase(brand2)) {
					counter++;
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean queryModel(String model) {
		String query = "SELECT * FROM SERIE WHERE modelo  = ?";
		int counter = 0;

		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			ResultSet rs;
			stmt.setString(1, model);

			rs = stmt.executeQuery();

			while (rs.next() && counter == 0) {
				String model2 = rs.getString(3);

				if (model.equalsIgnoreCase(model2)) {
					counter++;
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean queryYear(int year) {
		String query = "SELECT * FROM SERIE WHERE anio_fabricacion  = ?";
		int counter = 0;

		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			ResultSet rs;
			stmt.setInt(1, year);
			rs = stmt.executeQuery();

			while (rs.next() && counter == 0) {
				int year2 = rs.getInt(4);

				if (year == year2) {
					counter++;
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int saveSerie(String brand, String model, int year) {
		
		String query = "select * from serie where marca = ? and modelo = ? and anio_fabricacion = ?";
		String insertSQL = "insert into serie (marca, modelo, anio_fabricacion) values (?,?,?)";
		ResultSet rs = null;
		
		int idSerie = 0;
		int counter = 0;
		
		if (queryBrand(brand) == true && queryModel(model) == true && queryYear(year) == true) {
			try (PreparedStatement stmt = conn.prepareStatement(query)){
				stmt.setString(1, brand);
				stmt.setString(2, model);
				stmt.setInt(3, year);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					idSerie = rs.getInt(1);
				}
				
				return idSerie;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		} 
		else {

			try {
				Serie serie = new Serie(brand, model, year);
				PreparedStatement stmt = conn.prepareStatement(insertSQL);
				
				stmt.setString(1, brand);
				stmt.setString(2, model);
				stmt.setInt(3, year);

				stmt.executeUpdate();
				
				PreparedStatement stmt2 = conn.prepareStatement(query);
				stmt2.setString(1,brand);
				stmt2.setString(2,model);
				stmt2.setInt(3,year);
				
				rs = stmt2.executeQuery();
				
				while(rs.next()) {
					idSerie = rs.getInt(1);
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idSerie;
		
		
	}

	public boolean existsCar(String numBastidor) {

		String query = "SELECT * FROM Car WHERE numBastidor = ?";

		ResultSet rs;
		String numBastidor2 = "";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, numBastidor);
			rs = stmt.executeQuery();
			while(rs.next()) {
				numBastidor2 = rs.getString(1);
			}

			if (numBastidor.equals(numBastidor2)) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean saveCar(Car car1) {

		String insertCar = "INSERT INTO Car VALUES (?,?,?,?,?,?,?,?)";

		if (existsCar(car1.getNumBastidor()) == false) {
			try (PreparedStatement stmt = conn.prepareStatement(insertCar)) {

				stmt.setString(1, car1.getNumBastidor());
				stmt.setString(2, car1.getMatricula());
				stmt.setString(3, car1.getColour());
				stmt.setInt(4, car1.getNumPuertas());
				stmt.setInt(5, car1.getCapacidadMaletero());
				stmt.setInt(6, car1.getNumAsientos());
				stmt.setInt(7, car1.getPrecio());
				stmt.setInt(8, car1.getSerie());

				stmt.executeUpdate();

				return true;
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return false;
	}

	public boolean existsTruck(String numBastidor) {

		String query = "SELECT * FROM Truck WHERE numBastidor = ?";

		ResultSet rs;
		String numBastidor2 = "";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, numBastidor);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				numBastidor2 = rs.getString(1);
			}

			if (numBastidor.equals(numBastidor2)) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	
	public boolean saveTruck(Truck truck1) {

			String insertCar = "INSERT INTO Truck VALUES (?,?,?,?,?,?,?,?)";

			if (existsCar(truck1.getNumBastidor()) == false) {
				try (PreparedStatement stmt = conn.prepareStatement(insertCar)) {

					stmt.setString(1, truck1.getNumBastidor());
					stmt.setString(2, truck1.getMatricula());
					stmt.setString(3, truck1.getColour());
					stmt.setInt(4, truck1.getCarga());
					stmt.setString(5, truck1.getTipoMercancia() + "");
					stmt.setInt(6, truck1.getNumAsientos());
					stmt.setInt(7, truck1.getPrecio());
					stmt.setInt(8, truck1.getSerie());

					int value = stmt.executeUpdate();

					if(value > 0) {
						return true;
					}
					
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}

	

	public boolean deleteVehicle(int typeOfvehicle, String numBastidor) {
		
		String deleteVehicle = "";

		if(typeOfvehicle == 1) {
			deleteVehicle = "DELETE FROM Car where numBastidor = ?";
		}
		else {
			deleteVehicle = "DELETE FROM Truck where numBastidor = ?";
		}
		

		try (PreparedStatement stmt = conn.prepareStatement(deleteVehicle)) {

			stmt.setString(1, numBastidor);
			int value = stmt.executeUpdate();

			if (value > 0)
				return true;
			

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean updateCar(int option, String numOfbastidor, String newValue) {

		String query = "";
		
		if (option == 1) {
			query = "update Car set numBastidor = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 2) {
			query = "update Car set colour = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			query = "update Car set matricula = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateCar(int option, String numOfbastidor, int newValue) {

		String query = "";
		
		if (option == 4) {
			query = "update Car set numAsientos = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 5) {
			query = "update Car set precio = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(option == 6){
			query = "update Car set numPuertas = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 7){
			query = "update Car set capacidadMaletero = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateTruck(int option, String numOfbastidor, String newValue) {

		String query = "";
		if (option == 1) {
			query = "update Truck set numBastidor = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 2) {
			query = "update Truck set colour = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				
				stmt.executeUpdate();
				return true;		
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(option == 3){
			query = "update Truck set matricula = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			query = "update Truck set tipoMercancia = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean updateTruck(int option, String numOfbastidor, int newValue) {

		String query = "";
		
		if (option == 5) {
			query = "update Truck set carga = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 6) {
			query = "update Truck set numAsientos = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(option == 7){
			query = "update Truck set precio = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 7){
			query = "update Car set capacidadMaletero = ? where numBastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setInt(1, newValue);
				stmt.setString(2, numOfbastidor);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public void queryTwoDates(String date1, String date2) {
		String query = "";
		ResultSet rs;
		ResultSetMetaData rsmd;
		
			query = "SELECT * FROM HISTORIC WHERE FECHA BETWEEN ? AND ? and accion = 'SELL'";
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, date1);
			stmt.setString(2, date2);
			rs = stmt.executeQuery();
			
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int column = 1;
			
			while(column <= numberOfColumns) {
				String name = rsmd.getColumnName(column);
				System.out.print(name + "\t");
				column++;
			}
			System.out.println("");
			while(rs.next()) {
				String vehicleType = rs.getString(1);
				String date = rs.getString(2);
				String time = rs.getString(3);
				String accion = rs.getString(4);
				String numBastidor = rs.getString(5);
				String matricula = rs.getString(6);
				String colour = rs.getString(7);
				int carga = rs.getInt(8);
				String tipoMercancia = rs.getString(9);
				int numAsientos = rs.getInt(10);
				int numPuertas = rs.getInt(11);
				int capacidadMaletero = rs.getInt(12);
				int precio = rs.getInt(13);
				int idSerie = rs.getInt(14);
				String repintado = rs.getString(15);
				
	
					System.out.println(vehicleType + "\t" + date + "  " + time  + "\t"+ accion + "  "
					+ numBastidor + "  "+  matricula + "\t"+  colour + "\t"+ carga + "\t" + tipoMercancia + "\t\t"
					+  numAsientos + "\t\t" + numPuertas + "\t\t"+  capacidadMaletero + "\t\t\t"+  precio + "\t"
					+ idSerie + "\t" + repintado);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void showStock() {
		String query = "";
		
		ResultSetMetaData rsmd;
		
			query = "SELECT * FROM CAR, SERIE where car.idSerie = serie.idSerie";
	
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			ResultSet rs = stmt.executeQuery();
			
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int column = 1;
			
			while(column <= numberOfColumns) {
				String name = rsmd.getColumnName(column);
				if(column != 9) {
					System.out.print(name + "\t");
				}
				column++;
			}
			System.out.println("CARS");
			while(rs.next()) {

				String numBastidor = rs.getString(1);
				String matricula = rs.getString(2);
				String colour = rs.getString(3);	
				int numPuertas = rs.getInt(4);
				int capacidadMaletero = rs.getInt(5);
				int numAsientos = rs.getInt(6);	
				int precio = rs.getInt(7);
				int idSerie = rs.getInt(8);
				String brand = rs.getString(10);
				String model = rs.getString(11);
				int year = rs.getInt(12);
				
				
					System.out.println(numBastidor + " " +  matricula + "\t" + colour + "\t\t" + numPuertas + "\t\t"
					+ capacidadMaletero + "\t\t" + numAsientos  + "\t\t" +  precio + "\t" + idSerie + "\t"
					+ brand + "\t" + model + "\t" + year);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		query = "SELECT * FROM TRUCK, SERIE where truck.idSerie = serie.idSerie";
		
		String st = "";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			ResultSet rs = stmt.executeQuery();
			
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int column = 1;
			
			while(column <= numberOfColumns) {
				String name = rsmd.getColumnName(column);
				if(column != 9) {
					System.out.print(name + "\t");
				}
				
				column++;
			}
			System.out.println("\t\tTRUCKS");
			while(rs.next()) {
				
				String numBastidor = rs.getString(1);
				String matricula = rs.getString(2);
				String colour = rs.getString(3);
				int carga = rs.getInt(4);
				String tipoMercancia = rs.getString(5);
				int numAsientos = rs.getInt(6);
				int precio = rs.getInt(7);
				int idSerie = rs.getInt(8);
				String brand = rs.getString(10);
				String model = rs.getString(11);
				int year = rs.getInt(12);
				
				
					System.out.println(numBastidor + "\t" +  matricula + "\t" + colour + "\t" + carga +
					"\t" + tipoMercancia + "\t\t" + numAsientos + "\t\t" +  precio + "\t" + idSerie 
					+ "\t" + brand + "\t" + model + "\t" + year);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public List<Serie> getSerieData() {
		
		String query = "select * from serie";
		
		ResultSet rs;
		
		List<Serie> series = new ArrayList<Serie>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				Serie s = new Serie(rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
				series.add(s);
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return series;
	}
	
	public List<Car> getCarData() {
		
		String query = "select * from car";
		List<Car> cars = new ArrayList<Car>();
		
		ResultSet rs;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			

			while (rs.next()) {
				Car c1 = new Car(rs.getString(1), rs.getString(2), rs.getString(3),Integer.parseInt(rs.getString(4)), 
						Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),
						Integer.parseInt(rs.getString(7)));
				cars.add(c1);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}

	public List<Truck> getTruckData() {
	
		String query = "select * from truck";
		List<Truck> trucks = new ArrayList<Truck>();
		
		ResultSet rs;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			String s = rs.getString(8);
			while (rs.next()) {
				Truck t1 = new Truck(rs.getString(1), rs.getString(2), rs.getString(3),Integer.parseInt(rs.getString(4)), 
						Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),
						s.charAt(0));
				trucks.add(t1);
			}
			
			return trucks;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
}



	