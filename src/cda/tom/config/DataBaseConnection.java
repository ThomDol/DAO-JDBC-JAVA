package cda.tom.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static String url = "jdbc:mysql://localhost/sdbm";
	private static String user="root";
	private static String password="";
	private static Connection connection;



	public static Connection getInstance() throws ClassNotFoundException  {
		
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
               
        return connection;    
    }    
		
		


}
