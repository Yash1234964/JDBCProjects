package TransactionDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;
public class PropertiesPractise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static Connection jdbcConnectionUtil() throws IOException, SQLException {
		FileInputStream fis=new FileInputStream("app.properties");
		Properties properties = new Properties();
		properties.load(fis);

		 String url = properties.getProperty("url");
		
		 String username=properties.getProperty("username");
		 String password=properties.getProperty("password");
		
		Connection connection =DriverManager.getConnection(url,username,password);
		
		return connection;
	}
	
	static void jdbcStopLeak(Connection connection,Statement statement,ResultSet resultSet) throws Exception {
		if(connection !=null)
			connection.close();
		
		if(statement!=null)
		  statement.close();
		
		if(resultSet != null)
			resultSet.close();
		
		
	}

}
