package com.group_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.group_art.model.Group_ArtVO;


public class Group_order_detailDAO implements Group_order_detailDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	    
	    private static final String INSERT_STMT = 
			"INSERT INTO GROUP_ORDER_DETAIL (grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup) VALUES (?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup FROM GROUP_ORDER_DETAIL order by grou_id";
		private static final String GET_ONE_STMT_grou_id = 
			"SELECT grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup FROM GROUP_ORDER_DETAIL where grou_id = ?";
		private static final String GET_ONE_MEM_ID = 
				"SELECT grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup FROM GROUP_ORDER_DETAIL where mem_id = ?";
		private static final String DELETE = 
			"DELETE FROM GROUP_ORDER_DETAIL where grou_id =? and mem_id =? and product_id =? and sweet_id =? and ice_id =?";
		private static final String DELETE_BY_MEM_ID = 
			"DELETE FROM GROUP_ORDER_DETAIL where mem_id =?";
		private static final String UPDATE = 
			"UPDATE GROUP_ORDER_DETAIL set group_ord_price = ? , group_tol_cup=? "
			+ "where grou_id = ? and mem_id = ? and product_id = ? and sweet_id =? and ice_id=? and ord_id=?";
		private static final String GET_ONE =
			"SELECT grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup FROM GROUP_ORDER_DETAIL where grou_id = ?";
		private static final String GET_MEM =
				"SELECT DISTINCT mem_id FROM GROUP_ORDER_DETAIL where grou_id = ?";
		private static final String GET_THREE_INFO =
				"SELECT GROU_ID , COUNT(DISTINCT MEM_ID) AS MEM_ID,(SELECT SUM(GROUP_ORD_PRICE) FROM (SELECT GROUP_ORD_PRICE * GROUP_TOL_CUP AS GROUP_ORD_PRICE FROM GROUP_ORDER_DETAIL WHERE GROU_ID=?)) AS GROUP_ORD_PRICE,SUM(GROUP_TOL_CUP) AS GROUP_TOL_CUP "
				+"FROM GROUP_ORDER_DETAIL WHERE GROU_ID=? GROUP BY GROU_ID";
		private static final String GET_ONE_MEM_PRICE = 
				"SELECT group_ord_price,group_tol_cup FROM GROUP_ORDER_DETAIL where mem_id = ? and grou_id = ? ";
		private static final String GET_MEM_ID = 
				"SELECT distinct mem_id FROM GROUP_ORDER_DETAIL where grou_id = ?";
		@Override
		public void insert(Group_order_detailVO group_order_detailVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, group_order_detailVO.getGrou_id());
				pstmt.setString(2, group_order_detailVO.getMem_id());
				pstmt.setString(3, group_order_detailVO.getProduct_id());
				pstmt.setString(4, group_order_detailVO.getSweet_id());
				pstmt.setString(5, group_order_detailVO.getIce_id());
				pstmt.setString(6, group_order_detailVO.getOrd_id());
				pstmt.setDouble(7, group_order_detailVO.getGroup_ord_price());
				pstmt.setInt(8, group_order_detailVO.getGroup_tol_cup());


				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public void update(Group_order_detailVO group_order_detailVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);


				pstmt.setDouble(1, group_order_detailVO.getGroup_ord_price());
				pstmt.setInt(2, group_order_detailVO.getGroup_tol_cup());
				pstmt.setString(3, group_order_detailVO.getGrou_id());
				pstmt.setString(4, group_order_detailVO.getMem_id());
				pstmt.setString(5, group_order_detailVO.getProduct_id());
				pstmt.setString(6, group_order_detailVO.getSweet_id());
				pstmt.setString(7, group_order_detailVO.getIce_id());
				pstmt.setString(8, group_order_detailVO.getOrd_id());
			

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public void delete(String grou_id,String mem_id,String product_id,String sweet_id,String ice_id,String ord_id) {

			Connection con = null;
			PreparedStatement pstmt = null;
             
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, grou_id);
				pstmt.setString(2, mem_id);
				pstmt.setString(3, product_id);
				pstmt.setString(4, sweet_id);
				pstmt.setString(5, ice_id);
				
			
				pstmt.executeUpdate();
				 System.out.println(1);

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public List<Group_order_detailVO> findByGrou_id(String grou_id) {
			List<Group_order_detailVO> list = new ArrayList<Group_order_detailVO>();
			Group_order_detailVO group_order_detailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_grou_id);

				pstmt.setString(1, grou_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// group_order_detailVO 嚙踝蕭謕�蕭嚙踐���摩� Domain objects
					group_order_detailVO = new Group_order_detailVO(grou_id);
					group_order_detailVO.setGrou_id(rs.getString("grou_id"));
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					group_order_detailVO.setProduct_id(rs.getString("product_id"));
					group_order_detailVO.setSweet_id(rs.getString("sweet_id"));
					group_order_detailVO.setIce_id(rs.getString("ice_id"));
					group_order_detailVO.setOrd_id(rs.getString("ord_id"));
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));
					list.add(group_order_detailVO);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

		@Override
		public List<Group_order_detailVO> getAll() {
			List<Group_order_detailVO> list = new ArrayList<Group_order_detailVO>();
			Group_order_detailVO group_order_detailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// group_order_detailVO 嚙踝蕭謕�蕭嚙踐���摩� Domain objects
					group_order_detailVO = new Group_order_detailVO();
					group_order_detailVO.setGrou_id(rs.getString("grou_id"));
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					group_order_detailVO.setProduct_id(rs.getString("product_id"));
					group_order_detailVO.setSweet_id(rs.getString("sweet_id"));
					group_order_detailVO.setIce_id(rs.getString("ice_id"));
					group_order_detailVO.setOrd_id(rs.getString("ord_id"));
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));
					list.add(group_order_detailVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

		@Override
		public Group_order_detailVO findGrou_id(String grou_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Group_order_detailVO group_order_detailVO = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE);
				pstmt.setString(1, grou_id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					group_order_detailVO = new Group_order_detailVO();
					group_order_detailVO.setGrou_id(rs.getString("grou_id"));
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					group_order_detailVO.setProduct_id(rs.getString("product_id"));
					group_order_detailVO.setSweet_id(rs.getString("sweet_id"));
					group_order_detailVO.setIce_id(rs.getString("ice_id"));
					group_order_detailVO.setOrd_id(rs.getString("ord_id"));
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));
					
				}
				
				
			}catch(SQLException se){
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally{
				if(pstmt != null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(con != null){
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return group_order_detailVO;
		}

		@Override
		public void deleteByMem_id(String mem_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
             
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_BY_MEM_ID);

				pstmt.setString(1, mem_id);

				pstmt.executeUpdate();
				

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public Group_order_detailVO findMem_id(String mem_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Group_order_detailVO group_order_detailVO = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MEM_ID);
				pstmt.setString(1, mem_id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					group_order_detailVO = new Group_order_detailVO();
					group_order_detailVO.setGrou_id(rs.getString("grou_id"));
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					group_order_detailVO.setProduct_id(rs.getString("product_id"));
					group_order_detailVO.setSweet_id(rs.getString("sweet_id"));
					group_order_detailVO.setIce_id(rs.getString("ice_id"));
					group_order_detailVO.setOrd_id(rs.getString("ord_id"));
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));
					
				}
				
				
			}catch(SQLException se){
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally{
				if(pstmt != null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(con != null){
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return group_order_detailVO;
		}
	
		@Override
		public List<Group_order_detailVO> findMem(String grou_id) {
			List<Group_order_detailVO> list = new ArrayList<Group_order_detailVO>();
			Group_order_detailVO group_order_detailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEM);
				pstmt.setString(1, grou_id);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					group_order_detailVO = new Group_order_detailVO(grou_id);
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					list.add(group_order_detailVO);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		
		@Override
		public Group_order_detailVO getThreeInfo(String grou_id) {
			Group_order_detailVO group_order_detailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_THREE_INFO);
				pstmt.setString(1, grou_id);
				pstmt.setString(2, grou_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// group_order_detailVO ��里��甽蝛輯 Domain objects
					group_order_detailVO = new Group_order_detailVO();
					group_order_detailVO.setGrou_id(grou_id);
					group_order_detailVO.setMem_id(rs.getString("mem_id"));
					group_order_detailVO.setProduct_id(null);
					group_order_detailVO.setSweet_id(null);
					group_order_detailVO.setIce_id(null);
					group_order_detailVO.setOrd_id(null);
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return group_order_detailVO;
		}

		@Override
		public List<Group_order_detailVO> findMemPrice(String mem_id, String grou_id) {
			List<Group_order_detailVO> list = new ArrayList<Group_order_detailVO>();
			Group_order_detailVO group_order_detailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_MEM_PRICE);
					pstmt.setString(1, mem_id);
					pstmt.setString(2, grou_id);
					rs = pstmt.executeQuery();
				while (rs.next()) {
					
					group_order_detailVO = new Group_order_detailVO();
					group_order_detailVO.setGroup_ord_price(rs.getInt("group_ord_price"));
					group_order_detailVO.setGroup_tol_cup(rs.getInt("group_tol_cup"));

					list.add(group_order_detailVO);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

		@Override
		public List<Group_order_detailVO> everymem_id(String grou_id) {
			List<Group_order_detailVO> list = new ArrayList<Group_order_detailVO>();
			Group_order_detailVO group_order_detailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEM_ID);

				pstmt.setString(1, grou_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// group_order_detailVO 嚙踝蕭謕�蕭嚙踐���摩� Domain objects
					group_order_detailVO = new Group_order_detailVO(grou_id);
					group_order_detailVO.setMem_id(rs.getString("mem_id"));

					list.add(group_order_detailVO);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		

		
			
		
}