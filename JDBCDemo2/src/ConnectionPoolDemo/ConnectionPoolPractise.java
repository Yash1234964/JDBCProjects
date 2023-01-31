package ConnectionPoolDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;


public class ConnectionPoolPractise {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql:///practise1");
		dataSource.setUser("root");
        dataSource.setPassword("yashr8143");
        
        Connection connection;
		try {
			connection = dataSource.getConnection();
	
			Statement statement=connection.createStatement();
			ResultSet rs= statement.executeQuery("select id,name,age,address from employees");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
}
