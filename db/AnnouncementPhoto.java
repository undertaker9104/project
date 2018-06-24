import java.sql.*;
import java.io.*;

class AnnouncementPhoto{

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
	
   	     String sql = "UPDATE ANNOUNCEMENT SET ANN_IMG =? WHERE ANN_ID =?"; 
			  
         	        
              try {
				  
                con = DriverManager.getConnection(url, userid, passwd);
  			
				        pstmt = con.prepareStatement(sql);	

							
             for(int a=1;a<6;a++){
	                           
                File file = new File("C:/CA101G2DB/a"+a+".jpg");  
			  
                is = new FileInputStream(file);
       
                pstmt.setBinaryStream(1,is,is.available());
				
				if(a<10){
				pstmt.setString(2,"AI00000"+a);	
		  	   }else{
                pstmt.setString(2,"AI0000"+a);	
			   }			
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