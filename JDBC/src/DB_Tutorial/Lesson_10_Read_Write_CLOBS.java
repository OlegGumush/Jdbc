package DB_Tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Lesson_10_Read_Write_CLOBS {
	static final String connection = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) throws IOException {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			//Create statement
			PreparedStatement myStmt = myConn.prepareStatement("update employees set resume=? where email='john.doe@foo.com'");
			
			File f = new File("C:\\Program Files\\Eclipse\\WorkSpace\\DB\\sample_resume.pdf");
			long len = f.length();
			Reader input= (Reader) new BufferedReader(new FileReader(f));
			myStmt.setCharacterStream(1,input,(int)len);
			
			
			myStmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
