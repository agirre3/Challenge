package G4Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;


public class Connector {

	private static Connection conn;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "team4";
	private static final String password = "team4";
	private static final String url = "jdbc:mysql://10.14.1.15:3306/concesionario";

	public Connector() {

		try {
			System.out.println("Connecting...");
			Class.forName(driver);
			System.out.println("Connecting...");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				System.out.println("Connecting...");
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

				if (brand.equals(brand2)) {
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

				if (model.equals(model2)) {
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

		String query = "SELECT * FROM STOCK WHERE numBastidor = ?";

		ResultSet rs;
		String numBastidor2 = "";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, numBastidor);
			rs = stmt.executeQuery();
			while(rs.next()) {
				numBastidor2 = rs.getString(1);
			}
		

			if (numBastidor.equals(numBastidor2)) {
				return false;
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
					System.out.println("Insertado correctamente");
				return true;
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("No se ha insertado nada");

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
				return false;
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
/*funciona??*/		stmt.setString(5, truck1.getTipoMercancia() + "");
					stmt.setInt(6, truck1.getNumAsientos());
					stmt.setInt(7, truck1.getPrecio());
					stmt.setInt(8, truck1.getSerie());

					int value = stmt.executeUpdate();

					// quitarlo una vez probado
					if (value > 0)
						System.out.println("Insertado correctamente");
					return true;
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("No se ha insertado nada");

			return false;
		}

	

	public void deleteVehicle(int typeOfvehicle, String numBastidor) {
		//necesito algo más?
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
				System.out.println("Eliminado correctamente");
			else {
				System.out.println("No se ha eliminado nada");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public boolean updateCar(int option, String numOfbastidor, String newValue) {

		String query = "";
		
		if (option == 1) {
			query = "update numBastidor from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setString(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 2) {
			query = "update matricula from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setString(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			query = "update numBastidor from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setString(1, newValue);
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
			query = "update numAsientos from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setInt(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(option == 5) {
			query = "update precio from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setInt(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(option == 6){
			query = "update numPuertas from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setInt(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			query = "update capacidadMaletero from Car where numOfbastidor = ?";
			
			try (PreparedStatement stmt = conn.prepareStatement(query);) {

				stmt.setInt(1, newValue);
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


	
	/*
	 * public static void main(String[] args) {
	 * 
	 * Connector cn = new Connector();
	 * 
	 * Statement st; ResultSet rs;
	 * 
	 * 
	 * try { st = cn.connector.createStatement(); rs =
	 * st.executeQuery("select * from stock");
	 * 
	 * System.out.println("numBastidor" + "\t" + "colour" + "\t"+ "matricula");
	 * while(rs.next()) {
	 * 
	 * System.out.println(rs.getString("numBastidor") + "\t"+ rs.getString("colour")
	 * + "\t"+ rs.getString("matricula")); } cn.connector.close(); }
	 * 
	 * }
	 */
}
