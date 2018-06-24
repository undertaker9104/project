package com.msgboard.model;

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

public class MsgBoardDao implements MsgBoard_interface {

	private static final String INSERT_PSMT = "INSERT INTO MSG_BOARD(msg_board_id,mem_id,product_id,msg_des,msg_status) "
			                                   + " VALUES('MS'||LPAD(to_char(msg_seq.NEXTVAL), 6, '0'),?,?,?,?)";
	private static final String FIND_BY_PK = "SELECT * FROM MSG_BOARD WHERE msg_board_id = ?";
	private static final String GET_ALL = "SELECT * FROM  MSG_BOARD ORDER BY msg_board_id";
	private static final String GET_FrontALL = "SELECT * FROM  MSG_BOARD where msg_status=0 ORDER BY msg_board_id";
	private static final String UPDATE_STMT = "UPDATE MSG_BOARD SET msg_status = ? WHERE msg_board_id = ?";

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
	public List<MsgBoardVo> getAll() {
		List<MsgBoardVo> msglist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MsgBoardVo msgVo = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				msgVo = new MsgBoardVo();
				msgVo.setMsg_board_id(rs.getString("msg_board_id"));
				msgVo.setMem_id(rs.getString("mem_id"));
				msgVo.setProduct_id(rs.getString("product_id"));
				msgVo.setMsg_des(rs.getString("msg_des"));
				msgVo.setMsg_status(rs.getInt("msg_status"));
				msglist.add(msgVo);
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
		return msglist;
	}

	@Override
	public void add(MsgBoardVo msgVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_PSMT);
			psmt.setString(1, msgVo.getMem_id());
			psmt.setString(2, msgVo.getProduct_id());
			psmt.setString(3, msgVo.getMsg_des());
			psmt.setInt(4, msgVo.getMsg_status());
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
	public void update(MsgBoardVo msgVo) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setInt(1, msgVo.getMsg_status());
			psmt.setString(2, msgVo.getMsg_board_id());
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
	public MsgBoardVo findByPK(String msg_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MsgBoardVo msgVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, msg_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				msgVo = new MsgBoardVo();
				msgVo.setMsg_board_id(rs.getString("msg_board_id"));
				msgVo.setMem_id(rs.getString("mem_id"));
				msgVo.setProduct_id(rs.getString("product_id"));
				msgVo.setMsg_des(rs.getString("msg_des"));
				msgVo.setMsg_status(rs.getInt("msg_status"));
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
		return msgVo;
	}

	@Override
	public List<MsgBoardVo> getFrontAll() {
		List<MsgBoardVo> msglist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MsgBoardVo msgVo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_FrontALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				msgVo = new MsgBoardVo();
				msgVo.setMsg_board_id(rs.getString("msg_board_id"));
				msgVo.setMem_id(rs.getString("mem_id"));
				msgVo.setProduct_id(rs.getString("product_id"));
				msgVo.setMsg_des(rs.getString("msg_des"));
				msgVo.setMsg_status(rs.getInt("msg_status"));
				msglist.add(msgVo);
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
		return msglist;
	}

	
}
