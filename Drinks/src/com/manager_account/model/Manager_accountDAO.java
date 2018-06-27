package com.manager_account.model;

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

public class Manager_accountDAO implements Manager_accountDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Manager_account (man_acc_id,accpw,man_acc_status,emp_name,emp_img,emp_email) VALUES ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0'), ?, 0, ?, ? ,?  )";

	private static final String UPDATE = "UPDATE Manager_account set accpw=?, man_acc_status=?, emp_name=?, emp_img=? ,emp_email=? where man_acc_id = ?";
	private static final String UPDATE_STATUS = "UPDATE manager_account SET man_acc_status=? where man_acc_id=?";
	
	private static final String DELETE = "DELETE FROM Manager_account where man_acc_id = ?";

	private static final String GET_ONE_STMT = "SELECT man_acc_id,accpw,man_acc_status,emp_name,emp_img,emp_email FROM Manager_account where man_acc_id = ?";

	private static final String GET_ONE_STMT_name = "SELECT man_acc_id,accpw,man_acc_status,emp_name,emp_img,emp_email FROM Manager_account where emp_name = ?";

	private static final String GET_ALL_STMT = "SELECT man_acc_id,accpw,man_acc_status,emp_name,emp_img,emp_email FROM Manager_account order by man_acc_id";

	private static final String GET_ALLOUT_STMT = "SELECT * FROM MANAGER_ACCOUNT where AUTHORITY_ID='AC000002' order by man_acc_id";

	private static final String FIND_BY_ID_PWD = "SELECT * FROM manager_account WHERE man_acc_id = ? AND accpw = ?";
	
	@Override
	public void insert(Manager_accountVO manager_accountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, manager_accountVO.getAccpw());
			pstmt.setString(2, manager_accountVO.getEmp_name());
			pstmt.setBytes(3, manager_accountVO.getEmp_img());
			pstmt.setString(4, manager_accountVO.getEmp_email());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Manager_accountVO manager_accountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, manager_accountVO.getAccpw());
			pstmt.setInt(2, manager_accountVO.getMan_acc_status());
			pstmt.setString(3, manager_accountVO.getEmp_name());
			pstmt.setBytes(4, manager_accountVO.getEmp_img());
			pstmt.setString(5, manager_accountVO.getEmp_email());
			pstmt.setString(6, manager_accountVO.getMan_acc_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Manager_accountVO findByPrimaryKey(String man_acc_id) {
		Manager_accountVO manager_accountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, man_acc_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				manager_accountVO = new Manager_accountVO();

				manager_accountVO.setMan_acc_id(rs.getString("man_acc_id"));
				manager_accountVO.setAccpw(rs.getString("accpw"));
				manager_accountVO.setMan_acc_status(rs.getInt("man_acc_status"));
				manager_accountVO.setEmp_name(rs.getString("emp_name"));
				manager_accountVO.setEmp_img(rs.getBytes("emp_img"));
				manager_accountVO.setEmp_email(rs.getString("emp_email"));
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
		return manager_accountVO;
	}

	@Override
	public Manager_accountVO findByPrimaryKey_name(String emp_name) {
		Manager_accountVO manager_accountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_name);
			pstmt.setString(1, emp_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				manager_accountVO = new Manager_accountVO();

				manager_accountVO.setMan_acc_id(rs.getString("man_acc_id"));
				manager_accountVO.setAccpw(rs.getString("accpw"));
				manager_accountVO.setMan_acc_status(rs.getInt("man_acc_status"));
				manager_accountVO.setEmp_name(rs.getString("emp_name"));
				manager_accountVO.setEmp_img(rs.getBytes("emp_img"));
				manager_accountVO.setEmp_email(rs.getString("emp_email"));
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
		return manager_accountVO;
	}

	@Override
	public List<Manager_accountVO> getAll() {
		List<Manager_accountVO> list = new ArrayList<Manager_accountVO>();
		Manager_accountVO manager_accountVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				manager_accountVO = new Manager_accountVO();
				manager_accountVO.setMan_acc_id(rs.getString("man_acc_id"));
				manager_accountVO.setAccpw(rs.getString("accpw"));
				manager_accountVO.setMan_acc_status(rs.getInt("man_acc_status"));
				manager_accountVO.setEmp_name(rs.getString("emp_name"));
				manager_accountVO.setEmp_img(rs.getBytes("emp_img"));
				manager_accountVO.setEmp_email(rs.getString("emp_email"));
				list.add(manager_accountVO);
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
		return list;
	}

	@Override
	public List<Manager_accountVO> getOutAll() {
		List<Manager_accountVO> list = new ArrayList<Manager_accountVO>();
		Manager_accountVO manager_accountVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLOUT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				manager_accountVO = new Manager_accountVO();
				manager_accountVO.setMan_acc_id(rs.getString("man_acc_id"));
				manager_accountVO.setAccpw(rs.getString("accpw"));
				manager_accountVO.setMan_acc_status(rs.getInt("man_acc_status"));
				manager_accountVO.setEmp_name(rs.getString("emp_name"));
				manager_accountVO.setEmp_img(rs.getBytes("emp_img"));
				manager_accountVO.setEmp_email(rs.getString("emp_email"));
				list.add(manager_accountVO);
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
		return list;
	
	}

	@Override
	public boolean isManager(String man_acc_id, String accpw) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean isManager = false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FIND_BY_ID_PWD);
			ps.setString(1, man_acc_id);
			ps.setString(2, accpw);
			ResultSet rs = ps.executeQuery();
			isManager = rs.next();
			return isManager;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isManager;
	}

	@Override
	public boolean update(String man_acc_id, Integer man_acc_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean update = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1,man_acc_status );
			pstmt.setString(2,man_acc_id);
	
			int count = pstmt.executeUpdate();
			update = count > 0 ? true : false; 
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
		return update;
	}
}
