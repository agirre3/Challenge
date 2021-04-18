package G4Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Connector {

	
	private static Connection connector;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "team4";
    private static final String password = "team4"; 
    private static final String url = "jdbc:mysql://10.14.1.15:3306/concesionario"; 
       
    public Connector() {

        try {
            System.out.println("Connecting...");
            Class.forName(driver);
            System.out.println("Connecting...");
            connector = DriverManager.getConnection(url, user, password);
            if (connector != null) {
                System.out.println("Connecting...");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Wrong conection" + e);
        }
    }
    
    
    
    public int exists(String brand, String model, String year) {

    	String query = "SELECT * FROM SERIE WHERE numBastidor = ?" ;
    	int idSerie = 0;
    	int counter = 0;
		
		try {
			PreparedStatement stmt = connector.prepareStatement(query);
			ResultSet rs;
			
			rs = stmt.executeQuery(query);
			
			while (rs.next() && counter == 0){
	             String brand2 = rs.getString (2);
	             String model2 = rs.getString(3);
	             String year2 = rs.getString(4);

	             if (brand.equals(brand2) && model.equals(model2) && year.equals(year2)) {
	                	idSerie = rs.getInt(1);
	                	counter++;
	             }
	             else {
	            	 Serie serie = new Serie(brand, model, year);
	            	 idSerie = rs.getInt(1);
	             }
			}
			
		}
		
	    catch (Exception e) {
	       e.printStackTrace();
	    }
	    		
			return idSerie;
		}
		
   
		 /*public boolean existsCar(String numBastidor) {

		    	String query = "SELECT * FROM SERIE WHERE numBastidor = ?" ;
		    }*/

    
    public void saveCar(Car car1) {
    	
    	String insertCar = "INSERT INTO STOCK VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connector.prepareStatement(insertCar);
						
			stmt.setString(2,car1.getNumBastidor());
			stmt.setString(3,car1.getMatricula());
			stmt.setString(4,car1.getColour());
			stmt.setInt(7,car1.getNumAsientos());
			stmt.setInt(8,car1.getNumPuertas());
			stmt.setInt(9,car1.getCapacidadMaletero());
			stmt.setInt(10,car1.getPrecio());
			stmt.setInt(11,connector.saveSerie());
			
			int value = stmt.executeUpdate();
			
			//quitarlo una vez probado
			if (value > 0)
		           System.out.println("Insertado correctamente"); 
		}
		
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
	    
    public void saveTruck(Truck truck1) {
    	
    	String insertCar = "INSERT INTO STOCK VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connector.prepareStatement(insertCar);
			
			stmt.setString(2,truck1.getNumBastidor());
			stmt.setString(3,truck1.getMatricula());
			stmt.setString(4,truck1.getColour());
			stmt.setInt(5,truck1.getCarga());
	/*???*/	stmt.setString(6,truck1.getTipoMercancia() + "");
			stmt.setInt(7,truck1.getNumAsientos());
			stmt.setInt(10,truck1.getPrecio());
			//stmt.setInt(11,truck1.getIdSerie());
			
			int value = stmt.executeUpdate();
			
			//quitarlo una vez probado
			if (value > 0)
		           System.out.println("Insertado correctamente"); 
		}
		
		catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
    
    public void deleteVehicle(int option, String numBastidor) {
    	//CAMBIAR NOMBRE DE LA TABLA X LA VERDADERA
    	String deleteVehicle = "";
    	if(option == 1) {
    		deleteVehicle = "DELETE FROM STOCK where numBastidor = ?"; 
    	}
    	else {
    		deleteVehicle = "DELETE FROM STOCK where numBastidor = ?";
    	}
    	
    	try {
			PreparedStatement stmt = connector.prepareStatement(deleteVehicle);
				
			int value = stmt.executeUpdate();
			
			//quitarlo una vez probado
			if (value > 0)
		           System.out.println("Insertado correctamente"); 
		}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
	public static void main(String[] args) {
	
		/*Connector cn = new Connector();
		
		Statement st;
		ResultSet rs;
	
		
		try {
			st = cn.connector.createStatement();
			rs = st.executeQuery("select * from stock");
			
			System.out.println("numBastidor" + "\t" + "colour" +  "\t"+ "matricula");
			while(rs.next()) {
				
				System.out.println(rs.getString("numBastidor") + "\t"+  rs.getString("colour") +  "\t"+ rs.getString("matricula"));
			}
			cn.connector.close();
		}*/
		
		
	}
}
