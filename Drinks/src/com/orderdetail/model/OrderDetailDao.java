package com.orderdetail.model;

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

import com.group_order_detail.model.Group_order_detailVO;





public class OrderDetailDao implements OrderDetailDao_interface {
	
	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL(ORD_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID,ORD_PRICE ,TOL_CUP)"
			+ "VALUES(?, ?, ?,?,?,?,?)";
	private static final String INSERT = "INSERT INTO ORDER_DETAIL(ORD_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID,ORD_PRICE ,TOL_CUP)"
			+ "VALUES(?, ?, ?,?,?,?,?)";
	private static final String FIND_BY_PK = "SELECT * FROM ORDER_DETAIL WHERE ORD_ID = ?";
	private static final String ADD = "INSERT INTO ORDER_DETAIL(ORD_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID,ORD_PRICE ,TOL_CUP)"
			+ "VALUES(?, ?,?, ?,?,?,?)";
	
	
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
	public List<OrderDetailVo> findByOrderId(String ord_id) {
		List<OrderDetailVo> orderDetaillist = new ArrayList<>();
		OrderDetailVo orderDetailVo  = null;		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, ord_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				orderDetailVo = new OrderDetailVo();
				orderDetailVo.setOrd_id(ord_id);
				orderDetailVo.setProduct_id(rs.getString("product_id"));
				orderDetailVo.setIce_id(rs.getString("ice_id"));
				orderDetailVo.setSweet_id(rs.getString("sweet_id"));
				orderDetailVo.setOrd_price(rs.getInt("ord_price"));	
				orderDetailVo.setTol_cup(rs.getInt("tol_cup"));
				orderDetaillist.add(orderDetailVo);
	    	}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return orderDetaillist;
	}

	@Override
	public void addWithOrderMaster(OrderDetailVo orderItem, Connection con) {
		PreparedStatement psmt = null;

		try {

			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, orderItem.getOrd_id());
			psmt.setString(2, orderItem.getMem_id());
			psmt.setString(3, orderItem.getProduct_id());
			psmt.setString(4, orderItem.getIce_id());
			psmt.setString(5, orderItem.getSweet_id());
			psmt.setDouble(6, orderItem.getOrd_price());
			psmt.setInt(7, orderItem.getTol_cup());
			psmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-�-OrderMaster");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		}

	@Override
	public void insert(OrderDetailVo orderdetailVo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);	
		    pstmt.setString(1, orderdetailVo.getOrd_id());
			pstmt.setString(2, orderdetailVo.getMem_id());
			pstmt.setString(3, orderdetailVo.getProduct_id());
			pstmt.setString(4, orderdetailVo.getIce_id());
			pstmt.setString(5, orderdetailVo.getSweet_id());			
			pstmt.setInt(6, orderdetailVo.getOrd_price());
			pstmt.setInt(7, orderdetailVo.getTol_cup());
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
	public void add(List<Group_order_detailVO> orderdetailList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD);
			for (Group_order_detailVO list : orderdetailList) {
				pstmt.setString(1,list.getOrd_id());
				pstmt.setString(2,list.getMem_id());
				pstmt.setString(3,list.getProduct_id());
				pstmt.setString(4,list.getIce_id());
				pstmt.setString(5,list.getSweet_id());
				pstmt.setInt(6,list.getGroup_ord_price());
				pstmt.setInt(7,list.getGroup_tol_cup());				
				pstmt.executeUpdate();
							
			}
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
