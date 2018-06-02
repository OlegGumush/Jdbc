package DB_Tutorial;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Lesson_06_Store_Procedures_IN_01 {

	static final String connection = "jdbc:mysql://localhost:3306/less_06_store_procedures?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;
			
			//Create statement
			CallableStatement myStmt = myConn.prepareCall("{call increase_salaries_for_department(? , ?)}");
			myStmt.setString(1,theDepartment);
			myStmt.setDouble(2,theIncreaseAmount);
			myStmt.execute();
							
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
