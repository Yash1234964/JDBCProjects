package JDBCBlobDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

public class ImageRetrieval {

	private static final String IOUtil = null;
	static Connection connection=null;
	static PreparedStatement pstmt=null;
	static ResultSet resultSet=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			connection=PropertiesPractise.jdbcConnectionUtil();
			
			String sqlQuery ="Select * from persons";
		    pstmt=connection.prepareStatement(sqlQuery);
			resultSet=pstmt.executeQuery();
			
			
			
			if(resultSet.next()) {
				int id=resultSet.getInt(1); 
				String name=resultSet.getString(2);
				InputStream is= resultSet.getBinaryStream(3);
				String url="E:\\";
				File file =new File(url,"copied.jpeg");
				FileOutputStream fos = new FileOutputStream(file);
				
				IOUtils.copy(is, fos);
				
//				int i=is.read();
//				
//				while(i!=-1) {
//					fos.write(i);
//					i=is.read();
//					
//				}
//				
				System.out.println(id+"\t"+name+"\t"+file.getAbsolutePath());
				
			}else {
				System.out.println("Data not available");
			}
			
			
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				PropertiesPractise.jdbcStopLeak(connection, pstmt, resultSet);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
