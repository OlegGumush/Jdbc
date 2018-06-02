package DB_Tutorial;

import java.sql.*;


/**
 *  how to connect to sql Db.
 * @author oleg
 *
 */
public class Lesson_01_Connect_To_MySQL_Database {
	
	static final String connection = "jdbc:mysql://localhost:3306/MyDB,root,root123";
	
	public static void main(String[] args) {
		
		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection);
			//Create statment
			java.sql.Statement myStmt = myConn.createStatement();
			//Execute SQL query
			ResultSet myResult = myStmt.executeQuery("select * from student");
			//Process the result set
			while(myResult.next()){
				System.out.println(myResult.getString("private_name") + "," + myResult.getString("last_name"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
