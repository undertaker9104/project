package com.aboutus.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AboutUsDAO implements AboutUsDAO_interface{
	
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
		"INSERT INTO about_us (about_id,about_des,about_time,about_phone,about_add) VALUES ('A'||LPAD(to_char(ABOUT_US_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM about_us order by about_id";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM about_us where about_id = ?";
	private static final String DELETE = 
		"DELETE FROM about_us where about_id = ?";
	private static final String UPDATE = 
		"UPDATE about_us set about_des=?, about_time=?, about_phone=?, about_add=? where about_id = ?";
		
	@Override
	public void insert(AboutUsVO aboutusVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, aboutusVO.getAbout_des());
			pstmt.setString(2, aboutusVO.getAbout_time());
			pstmt.setString(3, aboutusVO.getAbout_phone());
			pstmt.setString(4, aboutusVO.getAbout_add());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(AboutUsVO aboutusVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
						
			pstmt.setString(1, aboutusVO.getAbout_des());
			pstmt.setString(2, aboutusVO.getAbout_time());
			pstmt.setString(3, aboutusVO.getAbout_phone());
			pstmt.setString(4, aboutusVO.getAbout_add());
			pstmt.setString(5, aboutusVO.getAbout_id());
			
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
	public void delete(String about_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, about_id);

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
	public AboutUsVO findByPrimaryKey(String about_id) {
		
		AboutUsVO aboutusVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, about_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				aboutusVO = new AboutUsVO();		
				
				aboutusVO.setAbout_id(rs.getString("about_id"));
				aboutusVO.setAbout_des(rs.getString("about_des"));
				aboutusVO.setAbout_time(rs.getString("about_time"));
				aboutusVO.setAbout_phone(rs.getString("about_phone"));
				aboutusVO.setAbout_add(rs.getString("about_add"));
				
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
		return aboutusVO;
	}
	
	@Override
	public List<AboutUsVO> getAll() {
		List<AboutUsVO> list = new ArrayList<AboutUsVO>();
		AboutUsVO aboutusVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				aboutusVO = new AboutUsVO();		
				aboutusVO.setAbout_id(rs.getString("about_id"));
				aboutusVO.setAbout_des(rs.getString("about_des"));
				aboutusVO.setAbout_time(rs.getString("about_time"));
				aboutusVO.setAbout_phone(rs.getString("about_phone"));
				aboutusVO.setAbout_add(rs.getString("about_add"));
				list.add(aboutusVO); // Store the row in the list
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
