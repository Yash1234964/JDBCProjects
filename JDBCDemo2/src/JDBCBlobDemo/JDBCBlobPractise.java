package JDBCBlobDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCBlobPractise {
static Connection connection =null;
static PreparedStatement pstmt=null;
static ResultSet resultSet=null;
static Scanner sc=null;
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		try {
		connection=PropertiesPractise.jdbcConnectionUtil();
		 sc=new Scanner(System.in);
		 
		 String sqlQuery = "INSERT INTO persons (name,image) values(?,?)";
		 
		 
		 System.out.println("Enter your name");
		 String name=sc.next();
		 
		 System.out.println("Enter your image location");
		 String imageLoc=sc.next();
		 
		 pstmt=connection.prepareStatement(sqlQuery);
		 
		 if(pstmt!=null) {
			 pstmt.setString(1,name);
			 pstmt.setBinaryStream(2,new FileInputStream(new File(imageLoc)));
		 }
		 int rowUpdated=pstmt.executeUpdate();
		 System.out.println("Number of rows updated :: "+rowUpdated);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				PropertiesPractise.jdbcStopLeak(connection, pstmt, resultSet);
				sc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}

}
