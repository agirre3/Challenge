package Vehicle;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Connector {

	public static void main(String[] args) {
		try {
			Class.forName("co.mysqul.jdbc.Driver");
			DriverManager.getConnection("djdbc:mysql://localhost:3306/Nombrebasededator", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load the controller");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println();
		}

	}

}
