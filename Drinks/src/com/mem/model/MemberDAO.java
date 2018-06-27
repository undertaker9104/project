package com.mem.model;

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

public class MemberDAO implements Member_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO member (mem_id,mem_name,mem_email,mem_pwd,mem_sex,mem_birth,mem_phone,mem_ads,mem_point,mem_int,mem_acc_status,mem_pic,mem_qrcode) VALUES ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,0,0,'0',?,?)";
	private static final String GET_ALL_STMT = "SELECT mem_id,mem_name,mem_email,mem_pwd,mem_sex,to_char(mem_birth,'yyyy-mm-dd') mem_birth,mem_phone,mem_ads,mem_point,mem_int,mem_acc_status,mem_pic FROM member order by mem_id";
	private static final String GET_ONE_STMT = "SELECT mem_id,mem_name,mem_email,mem_pwd,mem_sex,to_char(mem_birth,'yyyy-mm-dd') mem_birth,mem_phone,mem_ads,mem_point,mem_int,mem_acc_status,mem_pic,mem_qrcode FROM member where mem_email =?";
	private static final String GET_ONE_STMT_id = "SELECT mem_id,mem_name,mem_email,mem_pwd,mem_sex,to_char(mem_birth,'yyyy-mm-dd') mem_birth,mem_phone,mem_ads,mem_point,mem_int,mem_acc_status,mem_pic ,mem_qrcode FROM member where mem_id =?";
	private static final String DELETE = "DELETE FROM member where mem_id = ?";
	private static final String UPDATE = "UPDATE member set  mem_pwd=?, mem_phone=?, mem_ads=? , mem_pic=? where mem_email = ?";
	private static final String UPDATE_Manager = "UPDATE member set  mem_point=?, mem_int=?, mem_acc_status=? , mem_pic=? where mem_id = ?";
	private static final String UPDATE_Point = "UPDATE member set  mem_point=? where mem_id = ?";
	private static final String GET_ONE = "SELECT  mem_id,mem_email FROM member where mem_id =?";
	private static final String GET_IMG = "SELECT  mem_name,mem_pic,mem_point FROM member where mem_id =?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM member WHERE mem_email = ? AND mem_pwd = ?";
	@Override
	public void insert(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, MemberVO.getMem_name());
			pstmt.setString(2, MemberVO.getMem_email());
			pstmt.setString(3, MemberVO.getMem_pwd());
			pstmt.setString(4, MemberVO.getMem_sex());
			pstmt.setDate(5, MemberVO.getMem_birth());
			pstmt.setString(6, MemberVO.getMem_phone());
			pstmt.setString(7, MemberVO.getMem_ads());
			pstmt.setBytes(8, MemberVO.getMem_pic());
			pstmt.setBytes(9, MemberVO.getMem_qrcode());
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
	public void update(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, MemberVO.getMem_pwd());
			pstmt.setString(2, MemberVO.getMem_phone());
			pstmt.setString(3, MemberVO.getMem_ads());
			pstmt.setBytes(4, MemberVO.getMem_pic());
			pstmt.setString(5, MemberVO.getMem_email());
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mem_id);
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
	public MemberVO findByPrimaryKey(String mem_email) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMem_id(rs.getString("mem_id"));
				MemberVO.setMem_name(rs.getString("mem_name"));
				MemberVO.setMem_email(rs.getString("mem_email"));
				MemberVO.setMem_pwd(rs.getString("mem_pwd"));
				MemberVO.setMem_sex(rs.getString("mem_sex"));
				MemberVO.setMem_birth(rs.getDate("mem_birth"));
				MemberVO.setMem_phone(rs.getString("mem_phone"));
				MemberVO.setMem_ads(rs.getString("mem_ads"));
				MemberVO.setMem_point(rs.getInt("mem_point"));
				MemberVO.setMem_int(rs.getInt("mem_int"));
				MemberVO.setMem_acc_status(rs.getInt("mem_acc_status"));
				MemberVO.setMem_pic(rs.getBytes("mem_pic"));
				MemberVO.setMem_qrcode(rs.getBytes("mem_qrcode"));
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
		return MemberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMem_id(rs.getString("mem_id"));
				MemberVO.setMem_name(rs.getString("mem_name"));
				MemberVO.setMem_email(rs.getString("mem_email"));
				MemberVO.setMem_pwd(rs.getString("mem_pwd"));
				MemberVO.setMem_sex(rs.getString("mem_sex"));
				MemberVO.setMem_birth(rs.getDate("mem_birth"));
				MemberVO.setMem_phone(rs.getString("mem_phone"));
				MemberVO.setMem_ads(rs.getString("mem_ads"));
				MemberVO.setMem_point(rs.getInt("mem_point"));
				MemberVO.setMem_int(rs.getInt("mem_int"));
				MemberVO.setMem_acc_status(rs.getInt("mem_acc_status"));
				MemberVO.setMem_pic(rs.getBytes("mem_pic"));
				list.add(MemberVO);
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
		return list;
	}

	@Override
	public MemberVO findByPrimaryKey_id(String mem_id) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_id);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMem_id(rs.getString("mem_id"));
				MemberVO.setMem_name(rs.getString("mem_name"));
				MemberVO.setMem_email(rs.getString("mem_email"));
				MemberVO.setMem_pwd(rs.getString("mem_pwd"));
				MemberVO.setMem_sex(rs.getString("mem_sex"));
				MemberVO.setMem_birth(rs.getDate("mem_birth"));
				MemberVO.setMem_phone(rs.getString("mem_phone"));
				MemberVO.setMem_ads(rs.getString("mem_ads"));
				MemberVO.setMem_point(rs.getInt("mem_point"));
				MemberVO.setMem_int(rs.getInt("mem_int"));
				MemberVO.setMem_acc_status(rs.getInt("mem_acc_status"));
				MemberVO.setMem_pic(rs.getBytes("mem_pic"));
				MemberVO.setMem_qrcode(rs.getBytes("mem_qrcode"));
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
		return MemberVO;
	}

	@Override
	public void update_Manager(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Manager);

			pstmt.setInt(1, memberVO.getMem_point());
			pstmt.setInt(2, memberVO.getMem_int());
			pstmt.setInt(3, memberVO.getMem_acc_status());
			pstmt.setBytes(4, memberVO.getMem_pic());
			pstmt.setString(5, memberVO.getMem_id());
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
	public void update_Point(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Point);

			pstmt.setInt(1, memberVO.getMem_point());
			pstmt.setString(2, memberVO.getMem_id());
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
	
	public MemberVO findMem_id(String mem_id) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMem_id(rs.getString("mem_id"));
				MemberVO.setMem_email(rs.getString("mem_email"));
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
		return MemberVO;
	}

	@Override
	public MemberVO findimg(String mem_id) {
			MemberVO MemberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_IMG);
				pstmt.setString(1, mem_id);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberVO = new MemberVO();
					MemberVO.setMem_name(rs.getString("mem_name"));
					MemberVO.setMem_pic(rs.getBytes("mem_pic"));
					MemberVO.setMem_point(rs.getInt("mem_point"));
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
			return MemberVO;
		
	}

	@Override
	public void update(String mem_id, Integer mem_point, Connection con) {
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL,USER,
//					PASSWORD);
			pstmt = con.prepareStatement(UPDATE_Point);

			pstmt.setInt(1, mem_point);
			pstmt.setString(2, mem_id);

			pstmt.executeUpdate();
			// Handle any driver errors
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.閮剖����xception�����atch��憛
					System.err.print("Transaction is being ");
					System.err.println("rolled back-�-member_update");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
			
		}
		
	}

	@Override
	public boolean isMember(String mem_email, String mem_pwd) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean isMember = false;
		try {
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL,USER,
//					PASSWORD);
			ps = con.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, mem_email);
			ps.setString(2, mem_pwd);
			ResultSet rs = ps.executeQuery();
			isMember = rs.next();
			return isMember;
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;
	}
}
