package G4Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;



public class Connector {

	private static Connection conn;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "team4";
	private static final String password = "team4";
	private static final String url = "jdbc:mysql://127.0.0.1/concesionario";
			//"jdbc:mysql://10.14.1.15:3306/concesionario";

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

				int value = stmt.executeUpdate();

				// quitarlo una vez probado
				if (value > 0)
					System.out.println("Operation done");
				return true;
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Operation not done");

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

					// quitarlo una vez probado
					if (value > 0)
						System.out.println("Operation done");
					return true;
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Operation not done");

			return false;
		}

	

	public void deleteVehicle(int typeOfvehicle, String numBastidor) {
		
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

			// quitarlo una vez probado
			if (value > 0)
				System.out.println("Operation done");
			else {
				System.out.println("Error: Operation not done");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
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
				System.out.print(name + "  ");
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
				String repainted = rs.getString(15);
				
	
					System.out.println(vehicleType + "\t\t" + date + "\t"+ time  + "\t"+ accion + "\t"
					+ numBastidor + "\t"+  matricula + "\t"+  colour + "\t"+ carga + "\t" + tipoMercancia + "\t" 
					+ numPuertas + "\t"+  capacidadMaletero + "\t"+  numAsientos + "\t"+  precio + "\t"
					+ idSerie + "\t"+ repainted);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void showStock() {
		String query = "";
		ResultSet rs;
		ResultSetMetaData rsmd;
		
			query = "SELECT * FROM STOCK, SERIE where stock.idSerie = serie.idSerie";
		
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			rs = stmt.executeQuery();
			
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int column = 1;
			
			while(column <= numberOfColumns) {
				String name = rsmd.getColumnName(column);
				System.out.print(name + "  ");
				column++;
			}
			System.out.println("");
			while(rs.next()) {
				String vehicleType = rs.getString(1);
				String numBastidor = rs.getString(2);
				String matricula = rs.getString(3);
				String colour = rs.getString(4);
				int carga = rs.getInt(5);
				String tipoMercancia = rs.getString(6);
				int numAsientos = rs.getInt(7);
				int numPuertas = rs.getInt(8);
				int capacidadMaletero = rs.getInt(9);
				int precio = rs.getInt(10);
				int idSerie = rs.getInt(11);
				String repainted = rs.getString(12);
				int idSerie2 = rs.getInt(13);
				String brand = rs.getString(14);
				String model = rs.getString(15);
				int year = rs.getInt(16);
				
				
					System.out.println(vehicleType + "\t" + numBastidor + " " +  matricula + "    "+ 
					colour + "\t" + carga + "\t" + tipoMercancia + "\t\t" + numAsientos + "\t\t" + numPuertas + "\t"+  capacidadMaletero
					+ "\t\t" +  precio + "\t" + idSerie + "\t\t"+ repainted + "\t"+ idSerie2 + "\t" + brand + "\t" + model + "\t" + year);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}



	