package JDBCProjects;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JDBCProject1 {
        static Connection connection=null;
        static PreparedStatement pstmt=null;
        static ResultSet resultSet =null;
        static Scanner sc=null;
        
        static java.sql.Date sqlDob=null;
        static java.sql.Date sqlDom=null;
        
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
		connection =PropertiesPractise.jdbcConnectionUtil();
		Scanner sc= new Scanner(System.in);
		
		System.out.println("enter username");
		String name=sc.next();
		
		
		System.out.println("Enter dob (MM-dd-yyyy)");
		String dob=sc.next();
		
		
		System.out.println("Enter dom (yyyy-MM-dd) ");
		String dom=sc.next();
		
		
		
		
		if(dob !=null) {
		
			SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date uDate=sdf.parse(dob);
			long values=uDate.getTime();
			sqlDob=new java.sql.Date(values);
			
		}
		
		
		
		if(dom!=null) {
			sqlDom=  java.sql.Date.valueOf(dom);
//			SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
//			java.util.Date uDate=sdf.parse(dom);
//			long values=uDate.getTime();
//			sqlDom=new java.sql.Date(values);
		}
		
		
		String sqlInsertQuery="Insert into users(name,dob,dom) values(?,?,?)";
		
		if(connection !=null)
			pstmt=connection.prepareStatement(sqlInsertQuery);
		     
		System.out.println(sqlDob+" "+sqlDom);
		if(pstmt!=null) {
			pstmt.setString(1,name);
			pstmt.setDate(2, sqlDob);
			pstmt.setDate(3, sqlDom);
		}
		
		int keyCtr=pstmt.executeUpdate();
		
		System.out.println("Number of rows updated :: "+keyCtr);
		
		}
		catch(IOException |SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	      PropertiesPractise.jdbcStopLeak(connection,null,null);
		}
	}

}
