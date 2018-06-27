
package com.productclass.model;

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


public class ProductClassDao implements ProductClassDao_interface {

	private static final String INSERT_PSMT = "INSERT INTO PRODUCT_ClASS(PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) VALUES('PC'||LPAD(to_char(product_class_seq.NEXTVAL), 6, '0'),?,?)";
	private static final String GET_ALL_FRONT = "SELECT * FROM PRODUCT_ClASS WHERE PRODUCT_CL_STATUS=1 ORDER BY PRODUCT_CL_ID ";
	private static final String GET_ALL = "SELECT * FROM PRODUCT_ClASS where PRODUCT_CL_STATUS=1 ORDER BY PRODUCT_CL_ID  desc";
	private static final String UPDATE_STMT = "UPDATE PRODUCT_ClASS SET PRODUCT_CL_NAME = ?  WHERE PRODUCT_CL_ID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT_ClASS WHERE PRODUCT_CL_ID = ?";
	private static final String UPDATE_UPLOAD_STMT = "UPDATE PRODUCT_ClASS SET PRODUCT_CL_STATUS=? WHERE PRODUCT_CL_ID = ?";
	private static final String GET_UNLOAD = "SELECT * FROM  PRODUCT_ClASS where PRODUCT_CL_STATUS=0 ORDER BY PRODUCT_CL_ID desc";
	private static final String DOWN_STMT = "UPDATE PRODUCT_ClASS SET PRODUCT_CL_STATUS=?  WHERE PRODUCT_CL_ID = ?";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	Connection con = null;
	PreparedStatement psmt = null;

	public void add(ProductClassVo productClassVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);
			psmt.setString(1, productClassVo.getProduct_cl_name());
			psmt.setInt(2, productClassVo.getProduct_cl_status());
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
	public List<ProductClassVo> getAll() {
		List<ProductClassVo> productClasslist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductClassVo productClassVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productClassVo = new ProductClassVo();
				productClassVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productClassVo.setProduct_cl_name(rs.getString("product_cl_name"));
				productClassVo.setProduct_cl_status(rs.getInt("product_cl_status"));
				productClasslist.add(productClassVo);
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
		return productClasslist;
	}

	@Override
	public void update(ProductClassVo productClassVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setString(1, productClassVo.getProduct_cl_name());
			psmt.setString(3, productClassVo.getProduct_cl_id());
		
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
	public ProductClassVo findByPK(String pro_cls_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductClassVo proclstVo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, pro_cls_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				proclstVo = new ProductClassVo();
				proclstVo.setProduct_cl_id(rs.getString("product_cl_id"));
				proclstVo.setProduct_cl_name(rs.getString("product_cl_name"));
				proclstVo.setProduct_cl_status(rs.getInt("product_cl_status"));
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
		return proclstVo;
	}

	@Override
	public List<ProductClassVo> getAllFront() {
		List<ProductClassVo> productClasslist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductClassVo productClassVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_FRONT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productClassVo = new ProductClassVo();
				productClassVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productClassVo.setProduct_cl_name(rs.getString("product_cl_name"));
				productClassVo.setProduct_cl_status(rs.getInt("product_cl_status"));
				productClasslist.add(productClassVo);
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
		return productClasslist;
	}

	@Override
	public List<ProductClassVo> getAllUnload() {
		List<ProductClassVo> productClasslist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductClassVo productClassVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_UNLOAD);
			rs = psmt.executeQuery();
		      while (rs.next()) {
				productClassVo = new ProductClassVo();
				productClassVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productClassVo.setProduct_cl_name(rs.getString("product_cl_name"));
				productClassVo.setProduct_cl_status(rs.getInt("product_cl_status"));
				productClasslist.add(productClassVo);
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
		return productClasslist;
	}

	@Override
	public void upload(ProductClassVo producclassVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_UPLOAD_STMT);
		    psmt.setInt(1, producclassVo.getProduct_cl_status());
			psmt.setString(2, producclassVo.getProduct_cl_id());
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
	public void down(ProductClassVo producclassVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DOWN_STMT);
			psmt.setInt(1, producclassVo.getProduct_cl_status());
			psmt.setString(2, producclassVo.getProduct_cl_id());
	
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

}
