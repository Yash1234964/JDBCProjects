package TransactionDemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionPractise {

	
	static Connection connection=null;
	static Statement statement=null;
	static ResultSet resultSet=null;
	static Scanner sc=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		try {
			connection = PropertiesPractise.jdbcConnectionUtil();
			
			String sqlQuery="Select * from accountmanagement";
			
			statement = connection.createStatement();
			
			resultSet=statement.executeQuery(sqlQuery);
			
			if(resultSet!=null) {
				
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1)+'\t'+resultSet.getInt(2));
				}
			}
			connection.setAutoCommit(false);
		
			
			String sqlQuery1="update accountmanagement set balance=balance+1000 where name='Sachin'";
			String sqlQuery2="update accountmanagement set balance=balance-1000 where name='Dhoni'";
	
			statement.executeUpdate(sqlQuery1);
			statement.executeUpdate(sqlQuery2);

			
			System.out.println("Enter verification [yes/no]");
			sc=new Scanner(System.in);
			String flag=sc.next();
			
			if(flag.equalsIgnoreCase("yes"))
				connection.commit();
			else
				connection.rollback();
			
			String sqlQuery3="Select * from accountmanagement";
			ResultSet rs=statement.executeQuery(sqlQuery3);
			
                  if(rs!=null) {
				
				while(rs.next()) {
					System.out.println(rs.getString(1)+'\t'+rs.getInt(2));
				}
			}
			
		}
		
		catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				PropertiesPractise.jdbcStopLeak(connection,  statement, resultSet);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
