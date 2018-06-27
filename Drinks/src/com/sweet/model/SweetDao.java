package com.sweet.model;

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

public class SweetDao implements SweetDao_interface {

	private static final String INSERT_PSMT = "INSERT INTO SWEET(sweet_id,sweet_des) VALUES('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),?)";
	private static final String FIND_BY_PK = "SELECT * FROM SWEET WHERE sweet_id = ?";
	private static final String GET_ALL = "SELECT * FROM  SWEET ORDER BY sweet_id desc";
	private static final String UPDATE_STMT = "UPDATE SWEET SET sweet_des = ? WHERE sweet_id = ?";
	

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(SweetVo sweetVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);
			psmt.setString(1, sweetVo.getSweet_des());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(SweetVo sweetVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setString(1, sweetVo.getSweet_des());
			psmt.setString(2, sweetVo.getSweet_id());
			psmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public SweetVo findByPK(String sweet_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		SweetVo sweetVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, sweet_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				sweetVo = new SweetVo();
				sweetVo.setSweet_id(rs.getString("sweet_id"));
				sweetVo.setSweet_des(rs.getString("sweet_des"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sweetVo;
	}

	@Override
	public List<SweetVo> getAll() {
		List<SweetVo> sweetlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		SweetVo sweetVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				sweetVo = new SweetVo();
				sweetVo.setSweet_id(rs.getString("sweet_id"));
				sweetVo.setSweet_des(rs.getString("sweet_des"));
				sweetlist.add(sweetVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sweetlist;
	}

}
