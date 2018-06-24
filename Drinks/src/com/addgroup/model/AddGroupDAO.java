package com.addgroup.model;

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

public class AddGroupDAO implements AddGroupDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_BY_MEM_ID = "SELECT DISTINCT MEM_ID,GROU_ID FROM ADDGROUP WHERE MEM_ID=?";
	private static final String GET_MEM_ID = "SELECT  mem_id FROM addgroup where grou_id =? and mem_id=?";;
	private static final String INSERT = "INSERT INTO addgroup (mem_id,grou_id) VALUES (?,?)";
	private static final String LEAVE = 
			"DELETE FROM ADDGROUP where grou_id =? and mem_id =? ";

	@Override
	public List<AddGroupVO> findByMemid(String mem_id) {
		List<AddGroupVO> list = new ArrayList<AddGroupVO>();
		AddGroupVO addgroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_ID);
			pstmt.setString(1,mem_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				addgroupVO = new AddGroupVO();
				addgroupVO.setGrou_id(rs.getString("grou_id"));
				addgroupVO.setMem_id(mem_id);
				list.add(addgroupVO);
			}
			
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
		return list;
	}

	@Override
	public AddGroupVO addMem_id(String grou_id , String mem_id) {
		
		AddGroupVO addgroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ID);
			pstmt.setString(1, grou_id);
			pstmt.setString(2, mem_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				addgroupVO = new AddGroupVO();
				addgroupVO.setMem_id(rs.getString("mem_id"));

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
			return addgroupVO;

	}

	@Override
	public void insert(AddGroupVO AddGroupVO) {
		
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT);

				pstmt.setString(1, AddGroupVO.getMem_id());
				pstmt.setString(2, AddGroupVO.getGrou_id());
				
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
	public void leave(AddGroupVO AddGroupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
         
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LEAVE);

			
			pstmt.setString(1, AddGroupVO.getGrou_id());
			pstmt.setString(2, AddGroupVO.getMem_id());
			
			
		
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
	
}
