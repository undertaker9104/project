package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDao implements ProductDao_interface {

	private static final String INSERT_PSMT = "INSERT INTO PRODUCT(product_id,product_cl_id, product_name, product_price,product_des,product_img,product_status) VALUES('P'||LPAD(to_char(product_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?)";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE product_id = ?";
	private static final String GET_ALL = "SELECT * FROM  PRODUCT where product_status=1 ORDER BY product_id desc ";
	private static final String UPDATE_STMT = "UPDATE PRODUCT SET product_cl_id = ?, product_name = ?, product_price = ?, product_des = ?  WHERE product_id = ?";
	private static final String UPDATE_PHOTO_STMT = "UPDATE PRODUCT SET  product_img = ? WHERE product_id = ?";
	private static final String FONT_GET_ALL = "SELECT * FROM  PRODUCT WHERE product_status='1' ORDER BY product_id ";
	private static final String GET_ALL_BY_CLASS = "SELECT * FROM  PRODUCT WHERE product_status='1' and product_cl_id= ? ORDER BY product_id desc";
	private static final String RANK ="select product_id ,sum(tol_cup) from order_detail group by product_id   order by   sum(tol_cup) desc" ;
	private static final String UPDATE_UPLOAD_STMT = "UPDATE PRODUCT SET product_status=? WHERE product_id = ?";
	private static final String GET_UNLOAD = "SELECT * FROM  PRODUCT where product_status=0 ORDER BY product_id desc";
	private static final String UPDATE_DOWN_STMT = "UPDATE PRODUCT SET product_status=? WHERE product_id = ?";
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
	public void add(ProductVo productVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);

			psmt.setString(1, productVo.getProduct_cl_id());
			psmt.setString(2, productVo.getProduct_name());
			psmt.setDouble(3, productVo.getProduct_price());
			psmt.setString(4, productVo.getProduct_des());
			psmt.setBytes(5, productVo.getProduct_img());
			psmt.setInt(6, productVo.getProduct_status());
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
	public void update(ProductVo productVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setString(1, productVo.getProduct_cl_id());
			psmt.setString(2, productVo.getProduct_name());
			psmt.setInt(3, productVo.getProduct_price());
			psmt.setString(4, productVo.getProduct_des());
			psmt.setString(5, productVo.getProduct_id());
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
	public void updatePhoto(ProductVo productVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_PHOTO_STMT);
			psmt.setBytes(1, productVo.getProduct_img());
			psmt.setString(2, productVo.getProduct_id());
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
	public ProductVo findByPK(String product_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, product_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
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
		return productVo;
	}

	@Override
	public List<ProductVo> getAll() {
		List<ProductVo> productlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
				productlist.add(productVo);
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
		return productlist;
	}

	@Override
	public List<ProductVo> getFontAll() {
		List<ProductVo> productlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(FONT_GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
				productlist.add(productVo);
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
		return productlist;
	}

	@Override
	public List<ProductVo> getBuyAll() {
		List<ProductVo> buytlist = new Vector<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(FONT_GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
				buytlist.add(productVo);
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
		return buytlist;
	}

	@Override
	public List<ProductVo> getAllByClass(String procls) {
	    List<ProductVo> productclslist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_BY_CLASS);
			psmt.setString(1, procls);
			rs = psmt.executeQuery();
			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
				productclslist.add(productVo);
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
		return productclslist;
	}

	@Override
	public List<ProductVo> getrank() {
		List<ProductVo> ranklist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo rankVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(RANK);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rankVo = new ProductVo();
				rankVo.setProduct_id(rs.getString("product_id"));
				ranklist.add(rankVo);
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
		return ranklist;
	}

	@Override
	public List<ProductVo> getAllUnload() {
		List<ProductVo> productlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_UNLOAD);
			rs = psmt.executeQuery();

			while (rs.next()) {
				productVo = new ProductVo();
				productVo.setProduct_id(rs.getString("product_id"));
				productVo.setProduct_cl_id(rs.getString("product_cl_id"));
				productVo.setProduct_name(rs.getString("product_name"));
				productVo.setProduct_price(rs.getInt("product_price"));
				productVo.setProduct_des(rs.getString("product_des"));
				productVo.setProduct_img(rs.getBytes("product_img"));
				productVo.setProduct_status(rs.getInt("product_status"));
				productlist.add(productVo);
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
		return productlist;
	}

	@Override
	public void upload(ProductVo productVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_UPLOAD_STMT);
			psmt.setInt(1, productVo.getProduct_status());
			psmt.setString(2, productVo.getProduct_id());
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
	public void down(ProductVo productVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_DOWN_STMT);
			psmt.setInt(1, productVo.getProduct_status());
			psmt.setString(2, productVo.getProduct_id());
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

}
