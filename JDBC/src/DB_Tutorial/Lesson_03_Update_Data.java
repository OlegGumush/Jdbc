package DB_Tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Update data.
 * @author oleg
 *
 */
public class Lesson_03_Update_Data {

	static final String connection = "jdbc:mysql://localhost:3306/demodb?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) {
		
		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);
			//Create statment
			java.sql.Statement myStmt = myConn.createStatement();
			//Execute SQL query
			String query = "update student set private_name='Oleg' , last_name='Gumush' where id_student=319638490";
			int rowsAffect = myStmt.executeUpdate(query);
			
			System.out.println("Rows affected: "+rowsAffect);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
