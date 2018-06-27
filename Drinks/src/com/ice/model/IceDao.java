package com.ice.model;
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

public class IceDao implements IceDao_interface {

	private static final String INSERT_PSMT = "INSERT INTO ICE(ice_id,ice_des) VALUES('I'||LPAD(to_char(product_seq.NEXTVAL), 6, '0'),?)";
	private static final String FIND_BY_PK = "SELECT * FROM ICE WHERE ice_id = ?";
	private static final String GET_ALL = "SELECT * FROM  ICE ORDER BY ice_id desc";
	private static final String UPDATE_STMT = "UPDATE ICE SET ice_des = ? WHERE ice_id = ?";	

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
	public void add(IceVo iceVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);
			psmt.setString(1, iceVo.getIce_des());
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
	public void update(IceVo iceVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setString(1, iceVo.getIce_des());
			psmt.setString(2, iceVo.getIce_id());
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
	public IceVo findByPK(String ice_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		IceVo iceVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, ice_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				iceVo = new IceVo();
				iceVo.setIce_id(rs.getString("ice_id"));
				iceVo.setIce_des(rs.getString("ice_des"));
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
		return iceVo;
	}

	@Override
	public List<IceVo> getAll() {
		List<IceVo> icelist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		IceVo iceVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				iceVo = new IceVo();
				iceVo.setIce_id(rs.getString("ice_id"));
				iceVo.setIce_des(rs.getString("ice_des"));
				icelist.add(iceVo);
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
		return icelist;
	}

}
