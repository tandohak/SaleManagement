package kr.or.dgit.SaleManagement.jdbc_setting.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static final DBCon instance = new DBCon();
	private Connection connection;
	
	private DBCon(){
		//Properties properties = getProperties("conf.properties");
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false","root","rootroot");
		} catch (SQLException e){
			System.err.printf("%s - %s\n", e.getErrorCode(), e.getMessage());
		}
	}
	/*private Properties getProperties(String propertiesPath) {
		Properties properties = new Properties();
		InputStream is = ClassLoader.getSystemResourceAsStream(propertiesPath);
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}*/

	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public static DBCon getInstance() {
		return instance;
	}
}
