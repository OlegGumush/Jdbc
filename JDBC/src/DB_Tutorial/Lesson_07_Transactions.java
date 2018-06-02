package DB_Tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Lesson_07_Transactions {
	static final String connection = "jdbc:mysql://localhost:3306/less_06_store_procedures?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	
	public static void main(String[] args) {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);
			
			//set commit to false for transactions , turn off auto commit
			myConn.setAutoCommit(false);
						
			//Create statement
			Statement myStmt = myConn.createStatement();
			myStmt.executeUpdate("delete from employees where department='HR'");
			myStmt.executeUpdate("update employees set salary=20000 where department='Engineering'");
			
			boolean ok = askUrerIfOkToSave();
						
			if(ok){
				myConn.commit();
				System.out.println("transaction commited");
			}else{
				myConn.rollback();
				System.out.println("transaction rollback");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}


	private static boolean askUrerIfOkToSave() {
		// TODO Auto-generated method stub
		return true;
	}
}
