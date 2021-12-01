package database;

import java.sql.Connection;
import java.sql.DriverManager;

// classe de conexão com o banco de dados.

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
	}
	
	
	
	public static Connection getConnection() throws Exception{
		try {
			String driver  = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/estacionamento";
			String username = "root";
			String password = "072211";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
			
		} catch(Exception e){System.out.println(e);}
		
		 
		return null;
	}

}
