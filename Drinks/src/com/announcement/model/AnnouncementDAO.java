package com.announcement.model;

import java.util.*;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AnnouncementDAO implements AnnouncementDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	private static final String INSERT_STMT = "INSERT INTO ANNOUNCEMENT (ann_id,ANN_TITLE,ann_des,ANN_DATE,ann_img, ANN_STATUS) VALUES ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ANNOUNCEMENT order by ann_id";
	private static final String GET_ALL_STMT_DESC = "SELECT * FROM ANNOUNCEMENT order by ann_id desc";
	private static final String GET_ALL_STMT_AD = "SELECT * FROM  ANNOUNCEMENT WHERE ANN_STATUS='1' ORDER BY ANN_DATE DESC , ANN_ID DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM ANNOUNCEMENT where ann_id = ?";
	private static final String DELETE = "DELETE FROM ANNOUNCEMENT where ann_id = ?";
	private static final String UPDATE = "UPDATE ANNOUNCEMENT set ANN_TITLE=?, ann_des=?, ANN_DATE=?, ann_img=?, ANN_STATUS=? where ann_id = ?";
	private static final String SELECT_PIC = "SELECT ANN_IMG FROM ANNOUNCEMENT where ANN_ID = ? ";
		
		@Override
		public void insert(AnnouncementVO announcementVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, announcementVO.getAnn_title());
				pstmt.setString(2, announcementVO.getAnn_des());
				pstmt.setDate(3, announcementVO.getAnn_date());
				pstmt.setBytes(4, announcementVO.getAnn_img());
				pstmt.setInt(5, announcementVO.getAnn_status());
				
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
		public void update(AnnouncementVO announcementVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, announcementVO.getAnn_title());
				pstmt.setString(2, announcementVO.getAnn_des());
				pstmt.setDate(3, announcementVO.getAnn_date());
				pstmt.setBytes(4, announcementVO.getAnn_img());
				pstmt.setInt(5, announcementVO.getAnn_status());
				pstmt.setString(6, announcementVO.getAnn_id());

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
		public void delete(String ann_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, ann_id);

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
		public AnnouncementVO findByPrimaryKey(String ann_id) {

			AnnouncementVO announcementVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, ann_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					announcementVO = new AnnouncementVO();
					announcementVO.setAnn_id(rs.getString("ann_id"));
					announcementVO.setAnn_title(rs.getString("ann_title"));
					announcementVO.setAnn_des(rs.getString("ann_des"));
					announcementVO.setAnn_date(rs.getDate("ann_date"));
					announcementVO.setAnn_img(rs.getBytes("ann_img"));
					announcementVO.setAnn_status(rs.getInt("ann_status"));
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
			return announcementVO;
		}
		
		@Override
		public List<AnnouncementVO> getAll() {
			List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
			AnnouncementVO announcementVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					announcementVO = new AnnouncementVO();
					announcementVO.setAnn_id(rs.getString("ann_id"));
					announcementVO.setAnn_title(rs.getString("ann_title"));
					announcementVO.setAnn_des(rs.getString("ann_des"));
					announcementVO.setAnn_date(rs.getDate("ann_date"));
					announcementVO.setAnn_img(rs.getBytes("ann_img"));	
					announcementVO.setAnn_status(rs.getInt("ann_status"));
					list.add(announcementVO); // Store the row in the list
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
		public List<AnnouncementVO> getAllDesc() {
			List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
			AnnouncementVO announcementVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_DESC);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					announcementVO = new AnnouncementVO();
					announcementVO.setAnn_id(rs.getString("ann_id"));
					announcementVO.setAnn_title(rs.getString("ann_title"));
					announcementVO.setAnn_des(rs.getString("ann_des"));
					announcementVO.setAnn_date(rs.getDate("ann_date"));
					announcementVO.setAnn_img(rs.getBytes("ann_img"));
					announcementVO.setAnn_status(rs.getInt("ann_status"));
					list.add(announcementVO); // Store the row in the list
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
		public List<AnnouncementVO> getAllAd() {
			List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
			AnnouncementVO announcementVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_AD);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					announcementVO = new AnnouncementVO();
					announcementVO.setAnn_id(rs.getString("ann_id"));
					announcementVO.setAnn_title(rs.getString("ann_title"));
					announcementVO.setAnn_des(rs.getString("ann_des"));
					announcementVO.setAnn_date(rs.getDate("ann_date"));
					announcementVO.setAnn_img(rs.getBytes("ann_img"));
					announcementVO.setAnn_status(rs.getInt("ann_status"));
					list.add(announcementVO); // Store the row in the list
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
