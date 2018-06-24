package com.deposit_records.model;

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



public class Deposit_recordsDAO implements Deposit_records_interface {
	
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
			"INSERT INTO Deposit_records (dep_rec_id,mem_id,dep_cash,dep_suss_date) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),?,?,?)";

	private static final String UPDATE = 
			"UPDATE Deposit_records set mem_id=?, dep_cash=?, dep_suss_date=? where dep_rec_id = ?";
	
	private static final String DELETE = 
			"DELETE FROM Deposit_records where dep_rec_id = ?";
	
	private static final String GET_ONE_STMT_Bydep_rec_id = 
			"SELECT dep_rec_id,mem_id,dep_cash,to_char(dep_suss_date,'yyyy-mm-dd') dep_suss_date FROM Deposit_records where dep_rec_id = ?";
	
	private static final String GET_ONE_STMT_Bymem_id = 
			"SELECT dep_rec_id,mem_id,dep_cash,to_char(dep_suss_date,'yyyy-mm-dd') dep_suss_date FROM Deposit_records where mem_id = ?";
	
	private static final String GET_ALL_STMT = 
			"SELECT dep_rec_id,mem_id,dep_cash,to_char(dep_suss_date,'yyyy-mm-dd') dep_suss_date FROM Deposit_records order by dep_rec_id";
	private static final String GET_ALL_STMT_MemId = 
			"SELECT dep_rec_id,mem_id,dep_cash,to_char(dep_suss_date,'yyyy-mm-dd') dep_suss_date FROM Deposit_records order by mem_id";
	private static final String GET_ONE_STMT_mem_id2= 
			"SELECT dep_rec_id,mem_id,dep_cash,to_char(dep_suss_date,'yyyy-mm-dd') dep_suss_date FROM Deposit_records where mem_id=?";
	
	@Override
	public void insert(Deposit_recordsVO deposit_recordsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, deposit_recordsVO.getMem_id());
			pstmt.setInt(2, deposit_recordsVO.getDep_cash());
			pstmt.setDate(3, deposit_recordsVO.getDep_suss_date());


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
	public void update(Deposit_recordsVO deposit_recordsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, deposit_recordsVO.getMem_id());
			pstmt.setInt(2, deposit_recordsVO.getDep_cash());
			pstmt.setDate(3, deposit_recordsVO.getDep_suss_date());
			pstmt.setString(4, deposit_recordsVO.getDep_rec_id());
			

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
	public void delete(String dep_rec_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dep_rec_id);

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
	public Deposit_recordsVO findByPrimaryKey(String dep_rec_id) {
		Deposit_recordsVO deposit_recordsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_Bydep_rec_id);

			pstmt.setString(1, dep_rec_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]붙О Domain objects
				deposit_recordsVO = new Deposit_recordsVO();
				deposit_recordsVO.setDep_rec_id(rs.getString("dep_rec_id"));
				deposit_recordsVO.setMem_id(rs.getString("mem_id"));
				deposit_recordsVO.setDep_cash(rs.getInt("dep_cash"));
				deposit_recordsVO.setDep_suss_date(rs.getDate("dep_suss_date"));
			
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
		return deposit_recordsVO;
	}
	
	public Deposit_recordsVO findByPrimaryKey_MemId(String mem_id) {
		Deposit_recordsVO deposit_recordsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_Bymem_id);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]붙О Domain objects
				deposit_recordsVO = new Deposit_recordsVO();
				deposit_recordsVO.setDep_rec_id(rs.getString("dep_rec_id"));
				deposit_recordsVO.setMem_id(rs.getString("mem_id"));
				deposit_recordsVO.setDep_cash(rs.getInt("dep_cash"));
				deposit_recordsVO.setDep_suss_date(rs.getDate("dep_suss_date"));
			
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
		return deposit_recordsVO;
	}

	@Override
	public List<Deposit_recordsVO> getAll() {
		List<Deposit_recordsVO> list = new ArrayList<Deposit_recordsVO>();
		Deposit_recordsVO deposit_recordsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]붙О Domain objects
				deposit_recordsVO = new Deposit_recordsVO();
				deposit_recordsVO.setDep_rec_id(rs.getString("dep_rec_id"));
				deposit_recordsVO.setMem_id(rs.getString("mem_id"));
				deposit_recordsVO.setDep_cash(rs.getInt("dep_cash"));
				deposit_recordsVO.setDep_suss_date(rs.getDate("dep_suss_date"));
				list.add(deposit_recordsVO); // Store the row in the list
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
	public List<Deposit_recordsVO> getAll_MemId() {
		List<Deposit_recordsVO> list = new ArrayList<Deposit_recordsVO>();
		Deposit_recordsVO deposit_recordsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_MemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]붙О Domain objects
				deposit_recordsVO = new Deposit_recordsVO();
				deposit_recordsVO.setDep_rec_id(rs.getString("dep_rec_id"));
				deposit_recordsVO.setMem_id(rs.getString("mem_id"));
				deposit_recordsVO.setDep_cash(rs.getInt("dep_cash"));
				deposit_recordsVO.setDep_suss_date(rs.getDate("dep_suss_date"));
				list.add(deposit_recordsVO); // Store the row in the list
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
	public List<Deposit_recordsVO> findByPrimaryKey_MemIdList(String mem_id) {
		List<Deposit_recordsVO> list = new ArrayList<Deposit_recordsVO>();
		Deposit_recordsVO deposit_recordsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_mem_id2);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]붙О Domain objects
				deposit_recordsVO = new Deposit_recordsVO();
				deposit_recordsVO.setDep_rec_id(rs.getString("dep_rec_id"));
				deposit_recordsVO.setMem_id(rs.getString("mem_id"));
				deposit_recordsVO.setDep_cash(rs.getInt("dep_cash"));
				deposit_recordsVO.setDep_suss_date(rs.getDate("dep_suss_date"));
				list.add(deposit_recordsVO);
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
		System.out.println("111111111111111");
		return list;
	}

}
