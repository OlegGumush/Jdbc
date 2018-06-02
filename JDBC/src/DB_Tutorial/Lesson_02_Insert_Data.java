package DB_Tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Insert Data
 * @author oleg
 *
 */
public class Lesson_02_Insert_Data {
	
	static final String connection = "jdbc:mysql://localhost:3306/demodb?autoReconnect=true&useSSL=false";
	
	public static void main(String[] args) {
		
		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,"root","ironia123");
			//Create statement
			java.sql.Statement myStmt = myConn.createStatement();
			//Execute SQL query
			String query = "insert into student (id_student , private_name , last_name) values ('319638492', 'Eilon' , 'Mishaeli')";
			myStmt.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
