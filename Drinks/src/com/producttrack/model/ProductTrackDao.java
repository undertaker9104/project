package com.producttrack.model;

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


public class ProductTrackDao implements ProductTrackDao_interface {

	private static final String INSERT_PSMT = "INSERT INTO PRODUCT_TRACK(mem_id,product_id) VALUES(?,?)";
	private static final String FIND_BY_PK_MEN = "SELECT * FROM PRODUCT_TRACK WHERE mem_id = ?";
	private static final String GET_ALL = "SELECT * FROM  PRODUCT_TRACK ORDER BY product_id";
	private static final String GET_MYALL = "SELECT * FROM  PRODUCT_TRACK WHERE mem_id = ? ORDER BY product_id";
	private static final String DELETE_STMT = "DELETE PRODUCT_TRACK   WHERE mem_id = ? and product_id = ?";

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
	public void add(ProductTrackVo trackVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);
			psmt.setString(1, trackVo.getMem_id());
			psmt.setString(2, trackVo.getProduct_id());
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
	public List<ProductTrackVo> getAll() {
		List<ProductTrackVo> tracklist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductTrackVo trackVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				trackVo = new ProductTrackVo();
				trackVo.setMem_id(rs.getString("mem_id"));
				trackVo.setProduct_id(rs.getString("product_id"));
			 	tracklist.add(trackVo);
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
		return tracklist;
	}

	@Override
	public void delete(String mem_id, String pro_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE_STMT);
			psmt.setString(1, mem_id);
			psmt.setString(2, pro_id);
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
	public ProductTrackVo findByPK(String mem_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductTrackVo trackVo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK_MEN);
			psmt.setString(1,mem_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				trackVo = new ProductTrackVo();
				trackVo.setMem_id(rs.getString("mem_id"));
				trackVo.setProduct_id(rs.getString("product_id"));
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
		return trackVo;

	}

	@Override
	public List<ProductTrackVo> getMyAll(String mem_id) {
		List<ProductTrackVo> tracklist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductTrackVo trackVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_MYALL);
			psmt.setString(1, mem_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				trackVo = new ProductTrackVo();
				trackVo.setMem_id(rs.getString("mem_id"));
				trackVo.setProduct_id(rs.getString("product_id"));
			 	tracklist.add(trackVo);
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
		return tracklist;
	}

}
