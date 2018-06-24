package com.point_exc_cash.model;

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

import com.mem.model.MemberVO;




public class Point_exc_cashDAO implements Point_exc_cash_interface {
	
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
			"INSERT INTO Point_exc_cash (exc_rec_id,mem_id,exc_date,exc_cash,bank_acc,req_status) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, 0)";
	
	private static final String UPDATE = 
			"UPDATE Point_exc_cash set req_status=? where exc_rec_id = ?";
	
	private static final String DELETE = "DELETE FROM Point_exc_cash where exc_rec_id = ?";
	
	private static final String GET_ONE_STMT = 
			"SELECT exc_rec_id,mem_id,to_char(exc_date,'yyyy-mm-dd') exc_date,exc_cash,bank_acc,req_status FROM Point_exc_cash where mem_id = ?";
	private static final String GET_ONE_STMT_exc_rec_id = 
			"SELECT exc_rec_id,mem_id,to_char(exc_date,'yyyy-mm-dd') exc_date,exc_cash,bank_acc,req_status FROM Point_exc_cash where exc_rec_id = ?";
	
	private static final String GET_ALL_STMT = 
			"SELECT exc_rec_id,mem_id,to_char(exc_date,'yyyy-mm-dd') exc_date,exc_cash,bank_acc,req_status FROM Point_exc_cash order by exc_rec_id";
	
	@Override
	public void insert(Point_exc_cashVO point_exc_cashVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, point_exc_cashVO.getMem_id());
			pstmt.setDate(2, point_exc_cashVO.getExc_date());
			pstmt.setInt(3, point_exc_cashVO.getExc_cash());
			pstmt.setInt(4, point_exc_cashVO.getBank_acc());
			pstmt.executeUpdate();

		} catch (SQLException se) {
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
	public void update(Point_exc_cashVO point_exc_cashVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, point_exc_cashVO.getReq_status());
			pstmt.setString(2, point_exc_cashVO.getExc_rec_id());
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
	public void delete(String exc_rec_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, exc_rec_id);

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
	public List<Point_exc_cashVO> findByPrimaryKey(String mem_id) {
		List<Point_exc_cashVO> list = new ArrayList<Point_exc_cashVO>();
		Point_exc_cashVO point_exc_cashVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
				list.add(point_exc_cashVO); // Store the row in the list
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
	public List<Point_exc_cashVO> getAll() {
	
		List<Point_exc_cashVO> list = new ArrayList<Point_exc_cashVO>();
		Point_exc_cashVO point_exc_cashVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
				list.add(point_exc_cashVO); // Store the row in the list
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
	public Point_exc_cashVO findByPrimaryKeyVO(String mem_id) {
		Point_exc_cashVO point_exc_cashVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
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
		return point_exc_cashVO;
	}

	@Override
	public Point_exc_cashVO findByPrimaryKeyVO_exc_rec_id(String exc_rec_id) {
		Point_exc_cashVO point_exc_cashVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_exc_rec_id);
			pstmt.setString(1, exc_rec_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
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
		return point_exc_cashVO;
	}

	@Override
	public List<Point_exc_cashVO> getAll_Moderated() {
		List<Point_exc_cashVO> list = new ArrayList<Point_exc_cashVO>();
		Point_exc_cashVO point_exc_cashVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
				if(rs.getInt("req_status")==0){
				list.add(point_exc_cashVO); // Store the row in the list
				}
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
	public List<Point_exc_cashVO> getAll_Complete() {
		List<Point_exc_cashVO> list = new ArrayList<Point_exc_cashVO>();
		Point_exc_cashVO point_exc_cashVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
				point_exc_cashVO = new Point_exc_cashVO();
				point_exc_cashVO.setExc_rec_id(rs.getString("exc_rec_id"));
				point_exc_cashVO.setMem_id(rs.getString("mem_id"));
				point_exc_cashVO.setExc_date(rs.getDate("exc_date"));
				point_exc_cashVO.setExc_cash(rs.getInt("exc_cash"));
				point_exc_cashVO.setBank_acc(rs.getInt("bank_acc"));
				point_exc_cashVO.setReq_status(rs.getInt("req_status"));
				if(rs.getInt("req_status")==1){
				list.add(point_exc_cashVO); // Store the row in the list
				}
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
