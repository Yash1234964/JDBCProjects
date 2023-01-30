package JDBCCallableStatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.CallableStatement;

public class CallableStatementPractise {
         
	static Connection connection = null;
	static CallableStatement ctmt=null;
	static ResultSet resultSet=null;
    static Scanner sc=null;	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		try {
			connection=PropertiesPractise.jdbcConnectionUtil();
			sc=new Scanner(System.in);
			
			
			
			System.out.println("Enter id");
			Integer id=sc.nextInt();
			
			String storedProcedureCall ="{CALL P_GET_PRODUCT_DETAIL_BY_ID(?,?,?,?)}";
			
			ctmt=connection.prepareCall(storedProcedureCall);
			
			ctmt.setInt(1,id);
			
			ctmt.registerOutParameter(2,Types.VARCHAR);
			ctmt.registerOutParameter(3,Types.INTEGER);
			ctmt.registerOutParameter(4,Types.INTEGER);
             
			ctmt.execute();
			
			if(ctmt!=null) {
			System.out.println("Name of Products "+ctmt.getString(2));
			System.out.println("Name of Products "+ctmt.getInt(3));
			System.out.println("Name of Products "+ctmt.getInt(4));
			}
			
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
			try {
				PropertiesPractise.jdbcStopLeak(connection, ctmt, resultSet);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
