package com.group_track.model;

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

public class Group_trackDAO implements Group_trackDAO_interface{

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
			"INSERT INTO GROUP_TRACK (mem_id,grou_id) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mem_id,grou_id FROM group_track";
		private static final String GET_ONE_STMT_mem_id= 
			"SELECT mem_id,grou_id FROM group_track where mem_id=?";
		private static final String GET_ONE_STMT_grou_id = 
			"SELECT mem_id,grou_id FROM group_track where grou_id=?";
		private static final String DELETE = 
			"DELETE FROM GROUP_TRACK where mem_id = ? and grou_id = ?";


		@Override
		public void insert(Group_trackVO group_trackVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, group_trackVO.getMem_id());
				pstmt.setString(2, group_trackVO.getGrou_id());
				
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
		public void delete(String mem_id,String grou_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mem_id);
				pstmt.setString(2, grou_id);

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
		public List<Group_trackVO> findByMem_id(String mem_id) {
			List<Group_trackVO> list = new ArrayList<Group_trackVO>();
			Group_trackVO group_trackVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_mem_id);

				pstmt.setString(1, mem_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					group_trackVO = new Group_trackVO();
					group_trackVO.setMem_id(rs.getString("mem_id"));
					group_trackVO.setGrou_id(rs.getString("grou_id"));
					list.add(group_trackVO);
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
		public List<Group_trackVO> findByGrou_id(String grou_id) {
			List<Group_trackVO> list = new ArrayList<Group_trackVO>();
			Group_trackVO group_trackVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_grou_id);

				pstmt.setString(1, grou_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					group_trackVO = new Group_trackVO();
					group_trackVO.setMem_id(rs.getString("mem_id"));
					group_trackVO.setGrou_id(rs.getString("grou_id"));
					list.add(group_trackVO);
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
		public List<Group_trackVO> getAll() {
			List<Group_trackVO> list1 = new ArrayList<Group_trackVO>();
			Group_trackVO group_trackVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// group_trackVO �]�٬� Domain objects
					group_trackVO = new Group_trackVO();
					group_trackVO.setMem_id(rs.getString("mem_id"));
					group_trackVO.setGrou_id(rs.getString("Grou_id"));
					list1.add(group_trackVO); // Store the row in the list
				}

				// Handle any driver errors
			}catch (SQLException se) {
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
			return list1;
		}

}
