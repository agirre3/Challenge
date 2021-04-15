package Vehicle;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Connector {

	public static void main(String[] args) {
		try {
			Class.forName("co.mysqul.jdbc.Driver");
			DriverManager.getConnection("djdbc:mysql://localhost:3306/base_de_datos_del_concesionario", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load the controller");
		} catch (SQLException e) {
			System.out.println();
		}

	}

}
