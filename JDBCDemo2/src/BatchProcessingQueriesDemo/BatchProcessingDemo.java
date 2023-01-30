package BatchProcessingQueriesDemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchProcessingDemo {

	
	static Connection connection=null;
	static PreparedStatement pstmt=null;
	static Scanner sc=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         try {
			connection=PropertiesPractise.jdbcConnectionUtil();
             sc=new Scanner(System.in);
             
             String executeQuery ="INSERT INTO employees(name,age,address) values (?,?,?)";
             pstmt=connection.prepareStatement(executeQuery);
             
             if(pstmt!=null) {
            	 
            	 while(true) {
            		 System.out.println("Enter name");
            	     String name=sc.next();
            	     System.out.println("Enter age");
            	     int age=sc.nextInt();
            	     System.out.println("Enter address");
            	     String address=sc.next();
            	     
            	     pstmt.setString(1,name);
            	     pstmt.setInt(2,age);
            	     pstmt.setString(3,address);
            	     pstmt.addBatch();
            	     
            	     
            	     System.out.println("Enter more [YES/NO]");
            	     String choice=sc.next();
            	     
            	     if(choice.equalsIgnoreCase("no"))
                      break;
            	 }            	     
             }
			
			pstmt.executeBatch();
			// TODO Auto-generated catch block
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				PropertiesPractise.jdbcStopLeak(connection, pstmt, null);
				sc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
         
	}

}
