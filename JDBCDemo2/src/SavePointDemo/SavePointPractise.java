package SavePointDemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointPractise {
    
	
	static Connection connection=null;
	static Statement statement=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			connection=PropertiesPractise.jdbcConnectionUtil();
			statement=connection.createStatement();
			connection.setAutoCommit(false);
			
			System.out.println("Transaction Started");
			
			statement.executeUpdate("Insert into politicians (name,party) values ('Modi','BJP')");
			statement.executeUpdate("Insert into politicians (name,party) values ('KCR','TRS')");
			
			
			Savepoint sp = connection.setSavepoint();
			
			
			statement.executeUpdate("Insert into politicians (name,party) values ('Siddu','BJP')");
			
			connection.rollback(sp);
			connection.commit();
			System.out.println("Transaction Ended");
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				PropertiesPractise.jdbcStopLeak(connection, statement, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
