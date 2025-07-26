package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection 
{
	
	public static Connection getConnection() throws SQLException {
		//String url ="jdbc:sqlserver://127.0.0.1:1435;databaseName=Northwind;integratedSecurity=true;TrustServerCertificate=True;Trusted_Connection=true";
		String url ="jdbc:sqlserver://127.0.0.1:1435;databaseName=Northwind;TrustServerCertificate=True;";
		String username="sa";
		String pwd="Vietnam@123";
		return DriverManager.getConnection(url,username,pwd);
		}

}
