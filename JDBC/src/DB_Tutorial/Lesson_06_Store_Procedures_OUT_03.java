package DB_Tutorial;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Lesson_06_Store_Procedures_OUT_03 {
	static final String connection = "jdbc:mysql://localhost:3306/less_06_store_procedures?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			String theDepartment = "Engineering";
			
			//Create statement
			CallableStatement myStmt = myConn.prepareCall("{call get_count_for_department(? , ?)}");
			
			//OUT parameters
			myStmt.registerOutParameter(2, Types.INTEGER);
			myStmt.setString(1,theDepartment);
			myStmt.execute();
						
			//number of Engineers
			int theCount = myStmt.getInt(2);
			
			System.out.println("Result " + theCount);
			
			
			//number of employees
			CallableStatement myStmt1 = myConn.prepareCall("{call get_employees_for_department(? , ?)}");
			myStmt1.setString(1, theDepartment);
			myStmt1.execute();
					
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
