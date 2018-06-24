package com.manager_account_authority.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Manager_account_authorityDAO implements Manager_account_authority_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Manager_account_authority (authority_id,man_acc_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT authority_id,man_acc_id FROM Manager_account_authority order by man_acc_id";
	private static final String GET_ONE_STMT = "SELECT authority_id,man_acc_id FROM Manager_account_authority where authority_id = ?";
	private static final String GET_ONE_STMT_ID = "SELECT authority_id , man_acc_id FROM Manager_account_authority where man_acc_id = ?";
	private static final String GET_ONE_STMT_authID = "SELECT authority_id , man_acc_id FROM Manager_account_authority where authority_id = ?";
	private static final String DELETE = "DELETE FROM Manager_account_authority where man_acc_id = ?";
	private static final String UPDATE = "UPDATE Manager_account_authority set man_acc_id=? where authority_id = ?";
	private static final String GET_ONE_OUT_ID = "SELECT authority_id, man_acc_id FROM Manager_account_authority where authority_id='AC000002' and man_acc_id = ?";
	

	@Override
	public void insert(Manager_account_authorityVO manager_account_authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, manager_account_authorityVO.getAuthority_id());
			pstmt.setString(2, manager_account_authorityVO.getMan_acc_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Manager_account_authorityVO manager_account_authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, manager_account_authorityVO.getMan_acc_id());
			pstmt.setString(2, manager_account_authorityVO.getAuthority_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String man_acc_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, man_acc_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Manager_account_authorityVO findByPrimaryKey(String authority_id) {

		Manager_account_authorityVO manager_account_authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, authority_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				manager_account_authorityVO = new Manager_account_authorityVO();
				manager_account_authorityVO.setAuthority_id(rs.getString("authority_id"));
				manager_account_authorityVO.setMan_acc_id(rs.getString("man_acc_id"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return manager_account_authorityVO;
	}

	@Override
	public List<Manager_account_authorityVO> getAll() {
		List<Manager_account_authorityVO> list = new ArrayList<Manager_account_authorityVO>();
		Manager_account_authorityVO manager_account_authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				manager_account_authorityVO = new Manager_account_authorityVO();
				manager_account_authorityVO.setAuthority_id(rs.getString("authority_id"));
				manager_account_authorityVO.setMan_acc_id(rs.getString("man_acc_id"));
				list.add(manager_account_authorityVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<String> findByPrimaryKey_empID(String man_acc_id) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);
			pstmt.setString(1, man_acc_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getString("authority_id"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<String> findByPrimaryKey_authID(String authority_id) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_authID);
			pstmt.setString(1, authority_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getString("man_acc_id"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Manager_account_authorityVO findByPrimaryKey_OUT(String man_acc_id) {
		Manager_account_authorityVO manager_account_authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);
			pstmt.setString(1, man_acc_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				manager_account_authorityVO = new Manager_account_authorityVO();
				manager_account_authorityVO.setAuthority_id(rs.getString("authority_id"));
				manager_account_authorityVO.setMan_acc_id(rs.getString("man_acc_id"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return manager_account_authorityVO;
	}

}
