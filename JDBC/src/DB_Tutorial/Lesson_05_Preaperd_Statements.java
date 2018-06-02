package DB_Tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Lesson_05_Preaperd_Statements {
	
	static final String connection = "jdbc:mysql://localhost:3306/less_05_prepare_statements?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) {
		
		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);
			
			//Create statment
			PreparedStatement myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?");
			//set values
			myStmt.setDouble(1 , 80000);
			myStmt.setString(2 , "Legal");
			
			//get result
			ResultSet myRs = myStmt.executeQuery();
			
			display(myRs);		
			
			//Reuse
			myStmt.setDouble(1 , 25000);
			myStmt.setString(2 , "HR");
			
			myRs = myStmt.executeQuery();
			display(myRs);		
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	private static void display(ResultSet myRs) throws SQLException {
		ResultSetMetaData rsmd = myRs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		while (myRs.next()) {
			//Print one row          
			for(int i = 1 ; i <= columnsNumber; i++){
				System.out.print(myRs.getString(i) + " "); //Print one element of a row
			}
			System.out.println();//Move to the next line to print the next row.           
		}
		System.out.println();
	}
}
