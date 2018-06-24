import java.sql.*;
import java.io.*;

class GroupArtPhoto {

        static {
            try {
                 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        public static void main(String argv[]) {
              Connection con = null;
              PreparedStatement pstmt = null;
              InputStream  is = null;
		   
              String url = "jdbc:oracle:thin:@localhost:1521:XE";
              String userid = "CA101G2";
              String passwd = "CA101G2";
			  
							  
			  String sql = "UPDATE GROUP_ART SET ART_IMG=? WHERE GROU_ID =?"; 
			  
         	        
              try {
				  
                con = DriverManager.getConnection(url, userid, passwd);
  			
				        pstmt = con.prepareStatement(sql);	

							
             for(int g=1;g<9;g++){
	                           
                File file = new File("C:/CA101G2DB/GA"+g+".jpg");  
			  
                is = new FileInputStream(file);
       
                pstmt.setBinaryStream(1,is,is.available());
				
                pstmt.setString(2,"GA00000"+g);
							
	             pstmt.addBatch();
				
			     }
				 
			         	pstmt.executeBatch();
				
				       
                is.close();
		               
	              pstmt.close();
              
                System.out.println("圖片已經修改存入.. ");
              
              } catch (Exception e) {
                    e.printStackTrace();
              } finally {
                    try {
                      con.close();
                    } catch (SQLException e) {
                    }
             }
      }
}