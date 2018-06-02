package DB_Tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Lesson_08_Metadata {
	static final String connection = "jdbc:mysql://localhost:3306/less_06_store_procedures?autoReconnect=true&useSSL=false";
	static final String user = "root";
	static final String password = "ironia123";

	
	public static void main(String[] args) {

		try {
			//Get a connection to database
			Connection myConn = DriverManager.getConnection(connection,user,password);

			java.sql.DatabaseMetaData data = myConn.getMetaData();
			
			System.out.println("prodact version " + data.getDatabaseProductVersion());
			System.out.println("prodact name " + data.getDatabaseProductName());

			System.out.println("JDBC driver name " + data.getDriverName());
			System.out.println("prodact name " + data.getDriverVersion());
		
			
			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String columnNamePattern = null;
			String [] types = null;
			
			System.out.println("\nList of tables");
			System.out.println("--------------");

			ResultSet myRst = data.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(myRst.next()){
				System.out.println(myRst.getString("TABLE_NAME"));
			}
			
			System.out.println("\nList of columns");
			System.out.println("--------------");
			
			myRst = data.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			while(myRst.next()){
				System.out.println(myRst.getString("COLUMN_NAME"));
			}
			
			
			Statement st = myConn.createStatement();
			ResultSet result = st.executeQuery("select id,last_name,first_name,salary from employees");
			ResultSetMetaData setMetaData = result.getMetaData();
			
			int columns = setMetaData.getColumnCount();
			
			for (int i = 1; i <= columns; i++) {
				System.out.println(setMetaData.getColumnName(i));
				System.out.println(setMetaData.getColumnTypeName(i));
				System.out.println(setMetaData.isNullable(i));
				System.out.println(setMetaData.isAutoIncrement(i));
				System.out.println();
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
