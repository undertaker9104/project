import java.sql.*;
import java.io.*;

class ProductPhoto{

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
	
   	     String sql = "UPDATE PRODUCT SET PRODUCT_IMG=? WHERE PRODUCT_ID =?"; 
			  
         	        
              try {
				  
                con = DriverManager.getConnection(url, userid, passwd);
  			
				        pstmt = con.prepareStatement(sql);	

							
             for(int p=1;p<33;p++){
	                           
                File file = new File("C:/CA101G2DB/p"+p+".png");  
			  
                is = new FileInputStream(file);
       
                pstmt.setBinaryStream(1,is,is.available());
				
				if(p<10){
				pstmt.setString(2,"P00000"+p);	
		  	   }else{
                pstmt.setString(2,"P0000"+p);	
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