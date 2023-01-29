package JDBCClobPractise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PDFRetrieval {
     
	static Connection connection=null;
	static PreparedStatement pstmt=null;
	static ResultSet resultSet =null;
	static Scanner sc=null;
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		try {
			
			
			
			connection=PropertiesPractise.jdbcConnectionUtil();
			sc=new Scanner(System.in);
			
			System.out.println("Enter id");
			int id=sc.nextInt();
			
			
			
			String sqlSelectQuery="select id,name,history from cities where id=?";
//			String sqlSelectQuery="select * from cities";
			
			pstmt=connection.prepareStatement(sqlSelectQuery);
			if(pstmt!=null) {
			pstmt.setInt(1, id);
			resultSet=pstmt.executeQuery();
			}
			
			if(resultSet.next()) {
			int id1=resultSet.getInt(1);
			String name=resultSet.getString(2);
			Reader reader=resultSet.getCharacterStream(3);
			String loc="E:\\";
			File file=new File(loc,"retrivedFile");
			FileWriter writer=new FileWriter(file);
			IOUtils.copy(reader,writer);
			
			System.out.println(id1+"\t"+name+"\t"+file.getAbsolutePath());
			
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e)
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
