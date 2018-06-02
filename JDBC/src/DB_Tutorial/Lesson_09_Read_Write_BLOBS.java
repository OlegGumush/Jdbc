package DB_Tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lesson_09_Read_Write_BLOBS {
	static final String connection = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	public static void main(String[] args) throws IOException {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			
			//Create statement
			PreparedStatement myStmt = myConn.prepareStatement("update employees set resume=? where email='john.doe@foo.com'");
			
			File f = new File("sample_resume.pdf");
			FileInputStream input=null;

			input = new FileInputStream(f);
			myStmt.setBinaryStream(1,input);
			myStmt.executeUpdate();
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Statement stt = myConn.createStatement();
			ResultSet rlt = stt.executeQuery("select resume from employees where email='john.doe@foo.com'");
			
			File newFile = new File("resume_from_db.pdf");
			FileOutputStream out = new FileOutputStream(newFile);
			
			while(rlt.next()){
				InputStream in = rlt.getBinaryStream("resume");
				byte[] buffer = new byte[1024];
				try {
					while(in.read(buffer) >0){
						out.write(buffer);
					}
					in.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(newFile.getAbsolutePath());
			out.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
