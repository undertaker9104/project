	package com.group_art.model;

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

import com.addgroup.model.AddGroupService;
import com.ordermaster.model.OrderMasterDao;
import com.ordermaster.model.OrderMasterDao_interface;
import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVo;

public class Group_ArtDAO implements Group_ArtDAO_interface{
	// 銝���蝔�葉,�������澈 ,��銝��ataSource��
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA101G2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		private static final String INSERT_STMT = 
				"INSERT INTO GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_IMG,ART_NAME,GROU_PRICE,GROU_STATUS) VALUES ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),?,to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.CURRVAL), 6, '0'), ?, ?, ?, ?, ?, ?, '0')";
		private static final String GET_ALL_STMT = 
				"SELECT GROU_ID,MEM_ID,ORD_iD,SHIP_LOCAT,SEND_LOCAT,TO_CHAR(EXP_DATE,'YYYY-MM-DD hh24:mi:ss') EXP_DATE,ART_IMG,ART_NAME,GROU_PRICE,GROU_STATUS FROM GROUP_ART WHERE GROU_STATUS=0 ORDER BY GROU_ID DESC";
		private static final String GET_ALL_STMT_For_TRACK = 
				"SELECT * FROM GROUP_ART";
		private static final String DELETE = 
				"UPDATE GROUP_ART SET GROU_STATUS=2 WHERE GROU_ID=? ";
		private static final String UPDATE =
				"UPDATE GROUP_ART SET SHIP_LOCAT=?,SEND_LOCAT=?,EXP_DATE=?,ART_IMG=?,ART_NAME=?,GROU_PRICE=? WHERE GROU_ID = ?";
		private static final String GET_ALL_MEM =
				"SELECT GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,TO_CHAR(EXP_DATE,'YYYY-MM-DD hh24:mi:ss') EXP_DATE,ART_IMG,ART_NAME,GROU_PRICE,GROU_STATUS FROM GROUP_ART WHERE MEM_ID=? AND GROU_STATUS=0 OR GROU_STATUS=1";
		private static final String GET_ONE_ART =
				"SELECT GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,TO_CHAR(EXP_DATE,'YYYY-MM-DD hh24:mi:ss') EXP_DATE,ART_IMG,ART_NAME,GROU_PRICE,GROU_STATUS FROM GROUP_ART WHERE GROU_ID=?";
		private static final String GET_ALLBY_SR =
				"SELECT * FROM GROUP_ART WHERE ART_NAME LIKE ? AND GROU_STATUS=0";
		private static final String INSERT_WITH_ORDER = "INSERT INTO order_master(ORD_ID, MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS) "
				+ "VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),?, ?,?,?,?,?)";
		private static final String UPDATE_ORDER_STMT = "UPDATE order_master SET OUTE_ADD = ?,SHIP_OPTION=? WHERE ord_id = ?";
		private static final String INSERT_GROUP = "INSERT INTO addgroup (mem_id,grou_id) VALUES (?,'GA'||LPAD(to_char(group_art_seq.CURRVAL), 6, '0'))";

		@Override
	public void insert(Group_ArtVO group_ArtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		OrderMasterVo orderMasterVo = null;
		OrderMasterDao_interface dao = null;
		try {

			con = ds.getConnection();
			
			//新增一筆訂單資料
			orderMasterVo = new OrderMasterVo();
			if(group_ArtVO.getShip_locat() == ""){
				orderMasterVo.setGrou(1);
				orderMasterVo.setOrd_status(0);
				orderMasterVo.setOute_add("");
				orderMasterVo.setShip_option(0);;
				orderMasterVo.setMem_id(group_ArtVO.getMem_id());
				orderMasterVo.setMan_acc_id(null);
			}else{
				orderMasterVo.setGrou(1);
				orderMasterVo.setOrd_status(0);
				orderMasterVo.setOute_add(group_ArtVO.getShip_locat());
				orderMasterVo.setShip_option(1);;
				orderMasterVo.setMem_id(group_ArtVO.getMem_id());
				orderMasterVo.setMan_acc_id(null);
			}
			//新增訂單
			pstmt = con.prepareStatement(INSERT_WITH_ORDER);
			pstmt.setString(1, orderMasterVo.getMem_id());
			pstmt.setString(2, orderMasterVo.getMan_acc_id());
			pstmt.setInt(3, orderMasterVo.getGrou());
			pstmt.setString(4, orderMasterVo.getOute_add());
			pstmt.setInt(5, orderMasterVo.getShip_option());
			pstmt.setInt(6, orderMasterVo.getOrd_status());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, group_ArtVO.getMem_id());
			pstmt.setString(2, group_ArtVO.getShip_locat());
			pstmt.setString(3, group_ArtVO.getSend_locat());
			pstmt.setTimestamp(4, group_ArtVO.getExp_date());
			pstmt.setBytes(5, group_ArtVO.getArt_img());
			pstmt.setString(6, group_ArtVO.getArt_name());
			pstmt.setInt(7, group_ArtVO.getGrou_price());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(INSERT_GROUP);
			pstmt.setString(1, group_ArtVO.getMem_id());
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
	public void update(Group_ArtVO group_ArtVO) {
		Connection con = null;
		OrderMasterVo orderMasterVo = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			orderMasterVo = new OrderMasterVo();
			if(group_ArtVO.getShip_locat() == ""){
				orderMasterVo.setOute_add("");
				orderMasterVo.setShip_option(0);;
			}else{
				orderMasterVo.setOute_add(group_ArtVO.getShip_locat());
				orderMasterVo.setShip_option(1);;
			}
			
			//更改訂單主檔
			pstmt = con.prepareStatement(UPDATE_ORDER_STMT);
			pstmt.setString(1, orderMasterVo.getOute_add());
			pstmt.setInt(2, orderMasterVo.getShip_option());
			pstmt.setString(3, group_ArtVO.getOrd_id());
			pstmt.executeUpdate();
			pstmt.close();
			
			
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, group_ArtVO.getShip_locat());
			pstmt.setString(2, group_ArtVO.getSend_locat());
			pstmt.setTimestamp(3, group_ArtVO.getExp_date());
			pstmt.setBytes(4, group_ArtVO.getArt_img());
			pstmt.setString(5, group_ArtVO.getArt_name());
			pstmt.setInt(6, group_ArtVO.getGrou_price());
			pstmt.setString(7, group_ArtVO.getGrou_id());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String grou_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, grou_id);
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database err occured ." + se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	

	@Override
	public List<Group_ArtVO> getAll() {
		List<Group_ArtVO> list = new ArrayList<Group_ArtVO>();
		Group_ArtVO group_ArtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
              
			while (rs.next()) {
				// group_ArtVO 銋迂� Domain objects
				
				group_ArtVO = new Group_ArtVO();
				group_ArtVO.setGrou_id(rs.getString("grou_id"));
				group_ArtVO.setMem_id(rs.getString("mem_id"));
				group_ArtVO.setOrd_id(rs.getString("ord_id"));
				group_ArtVO.setShip_locat(rs.getString("ship_locat"));
				group_ArtVO.setSend_locat(rs.getString("send_locat"));
				group_ArtVO.setExp_date(rs.getTimestamp("exp_date"));
				group_ArtVO.setArt_img(rs.getBytes("art_img"));
				group_ArtVO.setArt_name(rs.getString("art_name"));
				group_ArtVO.setGrou_price(rs.getInt("grou_price"));
				group_ArtVO.setGrou_status(rs.getInt("grou_status"));
				
				list.add(group_ArtVO); // Store the row in the vector
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
	public List<Group_ArtVO> getAllForTrack() {
		List<Group_ArtVO> list = new ArrayList<Group_ArtVO>();
		Group_ArtVO group_ArtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_For_TRACK);
			rs = pstmt.executeQuery();
              
			while (rs.next()) {
				// group_ArtVO 銋迂� Domain objects
				
				group_ArtVO = new Group_ArtVO();
				group_ArtVO.setGrou_id(rs.getString("grou_id"));
				group_ArtVO.setMem_id(rs.getString("mem_id"));
				group_ArtVO.setOrd_id(rs.getString("ord_id"));
				group_ArtVO.setShip_locat(rs.getString("ship_locat"));
				group_ArtVO.setSend_locat(rs.getString("send_locat"));
				group_ArtVO.setExp_date(rs.getTimestamp("exp_date"));
				group_ArtVO.setArt_img(rs.getBytes("art_img"));
				group_ArtVO.setArt_name(rs.getString("art_name"));
				group_ArtVO.setGrou_price(rs.getInt("grou_price"));
				group_ArtVO.setGrou_status(rs.getInt("grou_status"));
				
				list.add(group_ArtVO); // Store the row in the vector
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
	public List<Group_ArtVO> findByMemId(String mem_id) {
		List<Group_ArtVO> list = new ArrayList<Group_ArtVO>();
		Group_ArtVO group_ArtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEM);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// group_ArtVO 銋迂� Domain objects
				group_ArtVO = new Group_ArtVO();
				group_ArtVO.setGrou_id(rs.getString("grou_id"));
				group_ArtVO.setMem_id(rs.getString("mem_id"));
				group_ArtVO.setOrd_id(rs.getString("ord_id"));
				group_ArtVO.setShip_locat(rs.getString("ship_locat"));
				group_ArtVO.setSend_locat(rs.getString("send_locat"));
				group_ArtVO.setExp_date(rs.getTimestamp("exp_date"));
				group_ArtVO.setArt_img(rs.getBytes("art_img"));
				group_ArtVO.setArt_name(rs.getString("art_name"));
				group_ArtVO.setGrou_price(rs.getInt("grou_price"));
				group_ArtVO.setGrou_status(rs.getInt("grou_status"));
				
				list.add(group_ArtVO); // Store the row in the vector
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public Group_ArtVO getOneArt(String grou_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Group_ArtVO group_ArtVO = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ART);
			pstmt.setString(1, grou_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				group_ArtVO = new Group_ArtVO();
				group_ArtVO.setGrou_id(rs.getString("grou_id"));
				group_ArtVO.setMem_id(rs.getString("mem_id"));
				group_ArtVO.setOrd_id(rs.getString("ord_id"));
				group_ArtVO.setShip_locat(rs.getString("ship_locat"));
				group_ArtVO.setSend_locat(rs.getString("send_locat"));
				group_ArtVO.setExp_date(rs.getTimestamp("exp_date"));
				group_ArtVO.setArt_name(rs.getString("art_name"));
				group_ArtVO.setGrou_price(rs.getInt("grou_price"));
				group_ArtVO.setArt_img(rs.getBytes("art_img"));
				group_ArtVO.setGrou_status(rs.getInt("grou_status"));
				
			}
			
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return group_ArtVO;
	}
	
	public List<Group_ArtVO> getAllBySr(String art_name){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Group_ArtVO> list = new ArrayList<Group_ArtVO>();
		Group_ArtVO group_ArtVO = null;
		try{
			con = ds.getConnection();
			System.out.println(art_name);
			pstmt = con.prepareStatement(GET_ALLBY_SR);
			pstmt.setString(1, "%" + art_name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				group_ArtVO = new Group_ArtVO();
				group_ArtVO.setGrou_id(rs.getString("grou_id"));
				group_ArtVO.setMem_id(rs.getString("mem_id"));
				group_ArtVO.setOrd_id(rs.getString("ord_id"));
				group_ArtVO.setShip_locat(rs.getString("ship_locat"));
				group_ArtVO.setSend_locat(rs.getString("send_locat"));
				group_ArtVO.setExp_date(rs.getTimestamp("exp_date"));
				group_ArtVO.setArt_img(rs.getBytes("art_img"));
				group_ArtVO.setArt_name(rs.getString("art_name"));
				group_ArtVO.setGrou_price(rs.getInt("grou_price"));
				group_ArtVO.setGrou_status(rs.getInt("grou_status"));
				
				list.add(group_ArtVO); // Store the row in the vector
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	

}
