package com.discount.model;

import java.util.*;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DiscountDAO implements DiscountDAO_interface{
	
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	private static final String INSERT_STMT = "INSERT INTO DISCOUNT(DIS_ID ,DIS_DES,DIS_CUP,DIS_CUP_RATE,DIS_PRICE,DIS_PRICE_RATE) VALUES ('D'||LPAD(to_char(DISCOUNT_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM DISCOUNT order by dis_id";
	private static final String GET_ONE_STMT = "SELECT * FROM discount where dis_id = ?";
	private static final String DELETE = "DELETE FROM discount where dis_id = ?";
	private static final String UPDATE = "UPDATE DISCOUNT SET DIS_DES=?,DIS_CUP=?, DIS_CUP_RATE=?, DIS_PRICE=?, DIS_PRICE_RATE=? where DIS_ID = ?";
		
		@Override
		public void insert(DiscountVO discountVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, discountVO.getDis_des());
				pstmt.setInt(2, discountVO.getDis_cup());
				pstmt.setDouble(3, discountVO.getDis_cup_rate());
				pstmt.setInt(4, discountVO.getDis_price());
				pstmt.setDouble(5, discountVO.getDis_price_rate());

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
		public void update(DiscountVO discountVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, discountVO.getDis_des());
				pstmt.setInt(2, discountVO.getDis_cup());
				pstmt.setDouble(3, discountVO.getDis_cup_rate());
				pstmt.setInt(4, discountVO.getDis_price());
				pstmt.setDouble(5, discountVO.getDis_price_rate());
				pstmt.setString(6, discountVO.getDis_id());

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
		public void delete(String dis_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, dis_id);

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
		public DiscountVO findByPrimaryKey(String dis_id) {

			DiscountVO discountVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, dis_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					discountVO = new DiscountVO();
					discountVO.setDis_id(rs.getString("dis_id"));
					discountVO.setDis_des(rs.getString("dis_des"));
					discountVO.setDis_cup(rs.getInt("dis_cup"));
					discountVO.setDis_cup_rate(rs.getDouble("dis_cup_rate"));
					discountVO.setDis_price(rs.getInt("dis_price"));
					discountVO.setDis_price_rate(rs.getDouble("dis_price_rate"));

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
			return discountVO;
		}
		
		@Override
		public List<DiscountVO> getAll() {
			List<DiscountVO> list = new ArrayList<DiscountVO>();
			DiscountVO discountVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					discountVO = new DiscountVO();
					discountVO.setDis_id(rs.getString("dis_id"));
					discountVO.setDis_des(rs.getString("dis_des"));
					discountVO.setDis_cup(rs.getInt("dis_cup"));
					discountVO.setDis_cup_rate(rs.getDouble("dis_cup_rate"));
					discountVO.setDis_price(rs.getInt("dis_price"));
					discountVO.setDis_price_rate(rs.getDouble("dis_price_rate"));
					list.add(discountVO); // Store the row in the list
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
