package com.int_exc.model;

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



public class Int_excDAO implements Int_exc_interface {
		
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
			"INSERT INTO Int_exc (int_exc_rec_id,mem_id,int_exc_date,integral,int_status) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),?, ?, ?)";
	
	private static final String UPDATE = 
			"UPDATE Int_exc set mem_id=?, int_exc_date=?, integral=? where int_exc_rec_id = ?";
	
	private static final String DELETE = 
			"DELETE FROM Int_exc where int_exc_rec_id = ?";
	
	private static final String GET_ONE_STMT = 
			"SELECT int_exc_rec_id,mem_id,to_char(int_exc_date,'yyyy-mm-dd') int_exc_date,integral FROM Int_exc where int_exc_rec_id= ?";
	
	private static final String GET_ONE_STMT_Mem_id = 
			"SELECT int_exc_rec_id,mem_id,to_char(int_exc_date,'yyyy-mm-dd') int_exc_date,integral FROM Int_exc where mem_id= ?";
	
	private static final String GET_ALL_STMT = 
			"SELECT int_exc_rec_id,mem_id,to_char(int_exc_date,'yyyy-mm-dd') int_exc_date,integral FROM Int_exc order by int_exc_rec_id";
	
	@Override
	public void insert(Int_excVO int_excVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, int_excVO.getMem_id());
			pstmt.setDate(2, int_excVO.getInt_exc_date());
			pstmt.setInt(3, int_excVO.getIntegral());
			

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
	public void update(Int_excVO int_excVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, int_excVO.getMem_id());
			pstmt.setDate(2, int_excVO.getInt_exc_date());
			pstmt.setInt(3, int_excVO.getIntegral());
			pstmt.setString(5, int_excVO.getInt_exc_rec_id());
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
	public void delete(String int_exc_rec_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, int_exc_rec_id);

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
	public Int_excVO findByPrimaryKeyInt_exc_rec_id(String int_exc_rec_id) {
		Int_excVO int_excVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, int_exc_rec_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				int_excVO = new Int_excVO();
				int_excVO.setInt_exc_rec_id(rs.getString("int_exc_rec_id"));
				int_excVO.setMem_id(rs.getString("mem_id"));
				int_excVO.setInt_exc_date(rs.getDate("int_exc_date"));
				int_excVO.setIntegral(rs.getInt("integral"));
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
		return int_excVO;
	}

	@Override
	public List<Int_excVO> getAll() {
		List<Int_excVO> list = new ArrayList<Int_excVO>();
		Int_excVO int_excVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				int_excVO = new Int_excVO();
				int_excVO.setInt_exc_rec_id(rs.getString("int_exc_rec_id"));
				int_excVO.setMem_id(rs.getString("mem_id"));
				int_excVO.setInt_exc_date(rs.getDate("int_exc_date"));
				int_excVO.setIntegral(rs.getInt("integral"));
				list.add(int_excVO); // Store the row in the list
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
	public Int_excVO findByPrimaryKeyMem_id(String mem_id) {
		Int_excVO int_excVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_Mem_id);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				int_excVO = new Int_excVO();
				int_excVO.setInt_exc_rec_id(rs.getString("int_exc_rec_id"));
				int_excVO.setMem_id(rs.getString("mem_id"));
				int_excVO.setInt_exc_date(rs.getDate("int_exc_date"));
				int_excVO.setIntegral(rs.getInt("integral"));
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
		return int_excVO;
	}



}
