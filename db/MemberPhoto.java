import java.sql.*;
import java.io.*;

class MemberPhoto {

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
			  
			     String sql = "UPDATE MEMBER SET MEM_PIC=? WHERE MEM_ID =?"; 
						  
         	        
              try {
				  
                con = DriverManager.getConnection(url, userid, passwd);
        	  
		     	     pstmt = con.prepareStatement(sql);			
		
			
			 for(int m=1;m<11;m++){
		                            
                File file = new File("C:/CA101G2DB/m"+m+".jpg");  
			    is = new FileInputStream(file);
                pstmt.setBinaryStream(1,is,is.available());
		        if(m<10){
				 pstmt.setString(2,"M00000"+m);	
		  	    }else{
                pstmt.setString(2,"M0000"+m);	
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