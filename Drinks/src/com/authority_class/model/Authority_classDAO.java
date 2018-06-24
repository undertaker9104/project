package com.authority_class.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.manager_account.model.Manager_accountVO;

public class Authority_classDAO implements Authority_classDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO authority_class (authority_id,authority_des) VALUES ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0'),?)";
	private static final String DELETE_EMPs = "DELETE FROM Manager_account where authority_id = ?";
	private static final String DELETE_Authority = "DELETE FROM Authority_class where authority_id = ?";
	private static final String GET_ONE_STMT = "SELECT authority_id , authority_des FROM Authority_class where authority_id = ?";
	private static final String GET_ALL_STMT = "SELECT authority_id , authority_des FROM Authority_class";
	private static final String GET_Emps_ByDeptno_STMT = "SELECT man_acc_id,accpw,man_acc_status, emp_name,emp_img FROM Manager_account where deptno = ? order by man_acc_id";
	
	
	
	
	
	@Override
	public void insert(Authority_classVO authority_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authority_classVO.getAuthority_des());
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
	public void delete(String authority_id) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_EMPs);
			pstmt.setString(1, authority_id);
			updateCount_EMPs = pstmt.executeUpdate();
			// 再刪除部門
			pstmt = con.prepareStatement(DELETE_Authority);
			pstmt.setString(1, authority_id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除權限編號" + authority_id + "時,共有員工" + updateCount_EMPs
					+ "人同時被刪除");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Authority_classVO findByPrimaryKey(String authority_id) {

		Authority_classVO authority_classVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, authority_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				authority_classVO = new Authority_classVO();
				authority_classVO.setAuthority_id(rs.getString("authority_id"));
				authority_classVO.setAuthority_des(rs.getString("authority_des"));
				
			}

			// Handle any SQL errors
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
		return authority_classVO;
	}

	@Override
	public List<Authority_classVO> getAll() {
		List<Authority_classVO> list = new ArrayList<Authority_classVO>();
		Authority_classVO authority_classVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authority_classVO = new Authority_classVO();
				authority_classVO.setAuthority_id(rs.getString("authority_id"));
				authority_classVO.setAuthority_des(rs.getString("authority_des"));
				list.add(authority_classVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
