package DB_Tutorial;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Lesson_06_Store_Procedures_OUT_02 {
	static final String connection = "jdbc:mysql://localhost:3306/less_06_store_procedures?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			String theDepartment = "Engineering";
			
			//Create statement
			CallableStatement myStmt = myConn.prepareCall("{call greet_the_department(?)}");
			
			//OUT parameters
			myStmt.registerOutParameter(1, Types.VARCHAR);
			myStmt.setString(1,theDepartment);
			myStmt.execute();
						
			String theResult = myStmt.getNString(1);
			System.out.println("Result " + theResult);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
