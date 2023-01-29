package JDBCClobPractise;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

public class PDFInsertion {

	
	static Connection connection=null;
	static PreparedStatement pstmt=null;
	static ResultSet resultSet=null;
	static Scanner sc=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			connection=PropertiesPractise.jdbcConnectionUtil();
			sc=new Scanner(System.in);
			String sqlQuery ="INSERT INTO cities (name,history) values (?,?)";
			
			System.out.println("Enter city name");
			String name=sc.next();
		    
			
			System.out.println("Enter history");
			String his=sc.next();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlQuery);
			
			pstmt.setString(1, name);
			pstmt.setCharacterStream(2,new FileReader(new File(his)));
			
			int ctr=pstmt.executeUpdate();
			
			System.out.println("Rows updated :: "+ctr);
			
		}
		catch(Exception e) {
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
