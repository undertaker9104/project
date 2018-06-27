package com.ordermaster.model;

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

import com.discount.model.DiscountService;
import com.discount.model.DiscountVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.orderdetail.model.OrderDetailVo;
import com.orderdetail.model.OrderDetailService;
import com.ordermaster.model.OrderMasterVo;
import com.orderdetail.model.OrderDetailDao;
import com.shoppingcart.model.ShoppingCartVO;
import com.manager_account.model.*;



public class OrderMasterDao implements OrderMasterDao_interface {
	private static final String FIND_BY_MEM = "SELECT * FROM order_master WHERE mem_id = ? order by ORD_ID desc";
	private static final String FIND_BY_PK = "SELECT * FROM order_master WHERE ord_id = ?";
	private static final String FIND_BY_TAKE = "SELECT * FROM order_master WHERE ship_option ='0' order by ORD_ID desc";
	private static final String FIND_BY_OUT = "SELECT * FROM order_master WHERE ship_option ='1' order by ORD_ID desc ";
	private static final String GET_ALL = "SELECT * FROM order_master order by ORD_ID desc ";
	private static final String INSERT_STMT = "INSERT INTO order_master(ORD_ID, MEM_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS) "
			                                  + "VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),?, ?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE order_master SET ord_status = ? WHERE ord_id = ?";
	
	private static final String GET_BY_MEMID = "SELECT * FROM order_master WHERE mem_id = ? ORDER BY ord_id DESC";
	private static final String GET_BY_MANID = "SELECT * FROM order_master WHERE man_acc_id=? and ship_option=1 order BY ord_id DESC";
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
	public List<OrderMasterVo> getAll() {
		List<OrderMasterVo> orderMasterlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderMasterVo orderMasterVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				orderMasterVo = new OrderMasterVo();
				orderMasterVo.setOrd_id(rs.getString("ord_id"));
				orderMasterVo.setMem_id(rs.getString("mem_id"));
				orderMasterVo.setMan_acc_id(rs.getString("man_acc_id"));
				orderMasterVo.setGrou(rs.getInt("grou"));
				orderMasterVo.setOute_add(rs.getString("oute_add"));
		 	    orderMasterVo.setShip_option(rs.getInt("ship_option"));
				orderMasterVo.setOrd_status(rs.getInt("ord_status"));
				orderMasterlist.add(orderMasterVo);
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
		return orderMasterlist;
	}

	@Override
	public String addWithOrderItem(OrderMasterVo orderMasterVo, List<ShoppingCartVO> orderItemList) {
		Connection con = null;
		PreparedStatement psmt = null;
		String next_orderid = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "ord_id" };
			psmt = con.prepareStatement(INSERT_STMT, cols);
			psmt.setString(1, orderMasterVo.getMem_id());
			psmt.setInt(2, orderMasterVo.getGrou());
			psmt.setString(3, orderMasterVo.getOute_add());
			psmt.setInt(4, orderMasterVo.getShip_option());
			psmt.setInt(5, orderMasterVo.getOrd_status());
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderid = rs.getString(1);
				
			} else {
				
			}
			rs.close();

			OrderDetailVo orderDetailVo = new OrderDetailVo();
			OrderDetailDao dao = new OrderDetailDao();

			for (int i = 0; i < orderItemList.size(); i++) {
				ShoppingCartVO order = (ShoppingCartVO) orderItemList.get(i);
				orderDetailVo.setOrd_id(next_orderid);
				orderDetailVo.setProduct_id(order.getAddProduct_id());
				orderDetailVo.setMem_id(order.getMem_id());
				orderDetailVo.setIce_id(order.getIce_id());
				orderDetailVo.setSweet_id(order.getSweet_id());
				orderDetailVo.setOrd_price(order.getProduct_price());
				orderDetailVo.setTol_cup(order.getQuantity());
				dao.addWithOrderMaster(orderDetailVo, con);
			}
			con.commit();
			con.setAutoCommit(true);

			

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-���蕭嚙�-OrderItem");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		return next_orderid;
	}

	@Override
	public OrderMasterVo findById(String orderid) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderMasterVo orderMasterVo = null;
 	try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_PK);
			psmt.setString(1, orderid);
			rs = psmt.executeQuery();
			while (rs.next()) {
     			orderMasterVo = new OrderMasterVo();
				orderMasterVo.setOrd_id(rs.getString("ord_id"));
				orderMasterVo.setMem_id(rs.getString("mem_id"));
				orderMasterVo.setMan_acc_id(rs.getString("man_acc_id"));
				orderMasterVo.setGrou(rs.getInt("grou"));
				orderMasterVo.setOute_add(rs.getString("oute_add"));
				orderMasterVo.setShip_option(rs.getInt("ship_option"));
				orderMasterVo.setOrd_status(rs.getInt("ord_status"));
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
		return orderMasterVo;
	}
	

	@Override
	public void update(OrderMasterVo masterVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setInt(1, masterVo.getOrd_status());
			psmt.setString(2, masterVo.getOrd_id());
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
	public List<OrderMasterVo> getAllByMen(String mem_id) {
		List<OrderMasterVo> orderMasterlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderMasterVo orderMasterVo = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_MEM);
			psmt.setString(1, mem_id);
	 	    rs = psmt.executeQuery();
		    while (rs.next()) {
				orderMasterVo = new OrderMasterVo();
				orderMasterVo.setOrd_id(rs.getString("ord_id"));
				orderMasterVo.setMem_id(rs.getString("mem_id"));
				orderMasterVo.setMan_acc_id(rs.getString("man_acc_id"));
				orderMasterVo.setGrou(rs.getInt("grou"));
				orderMasterVo.setOute_add(rs.getString("oute_add"));
				orderMasterVo.setShip_option(rs.getInt("ship_option"));
				orderMasterVo.setOrd_status(rs.getInt("ord_status"));
				orderMasterlist.add(orderMasterVo);
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
		return orderMasterlist;
	}

	@Override
	public List<OrderMasterVo> getAllByTake() {
		List<OrderMasterVo> orderMasterlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderMasterVo orderMasterVo = null;
		try {
		    con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_TAKE);
	 	 	rs = psmt.executeQuery();
			while (rs.next()) {
				orderMasterVo = new OrderMasterVo();
				orderMasterVo.setOrd_id(rs.getString("ord_id"));
				orderMasterVo.setMem_id(rs.getString("mem_id"));
				orderMasterVo.setMan_acc_id(rs.getString("man_acc_id"));
				orderMasterVo.setGrou(rs.getInt("grou"));
				orderMasterVo.setOute_add(rs.getString("oute_add"));
		 	    orderMasterVo.setShip_option(rs.getInt("ship_option"));
				orderMasterVo.setOrd_status(rs.getInt("ord_status"));
				orderMasterlist.add(orderMasterVo);
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
		return orderMasterlist;
	}

	@Override
	public List<OrderMasterVo> getAllByOue() {
		List<OrderMasterVo> orderMasterlist = new ArrayList<>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderMasterVo orderMasterVo = null;
		try {
		    con = ds.getConnection();
			psmt = con.prepareStatement(FIND_BY_OUT);
	 	 	rs = psmt.executeQuery();
			while (rs.next()) {
				orderMasterVo = new OrderMasterVo();
				orderMasterVo.setOrd_id(rs.getString("ord_id"));
				orderMasterVo.setMem_id(rs.getString("mem_id"));
				orderMasterVo.setMan_acc_id(rs.getString("man_acc_id"));
				orderMasterVo.setGrou(rs.getInt("grou"));
				orderMasterVo.setOute_add(rs.getString("oute_add"));
		 	    orderMasterVo.setShip_option(rs.getInt("ship_option"));
				orderMasterVo.setOrd_status(rs.getInt("ord_status"));
				orderMasterlist.add(orderMasterVo);
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
		return orderMasterlist;
	}

	@Override
	public void disom(String SQL) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(SQL);
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
	public void shipom(String SQL) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(SQL);
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
	public void doneom(String SQL) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(SQL);
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
	public List<OrderMasterVo> getAll_By_MemId(String mem_id) {
		List<OrderMasterVo> orderList = new ArrayList<>();
		OrderMasterVo order_masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(GET_BY_MEMID);
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order_masterVO = new OrderMasterVo();
				order_masterVO.setOrd_id(rs.getString(1));
				order_masterVO.setMem_id(rs.getString(2));
				order_masterVO.setMan_acc_id(rs.getString(3));
				order_masterVO.setGrou(rs.getInt(4));
				order_masterVO.setOute_add(rs.getString(5));
				order_masterVO.setShip_option(rs.getInt(6));
				order_masterVO.setOrd_status(rs.getInt(7));
				System.out.println("order_masterVO:"+order_masterVO);
				orderList.add(order_masterVO);
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
		System.out.println("DAO_getAll_orderList:"+orderList);
		return orderList;
	}

	@Override
	public List<OrderMasterVo> getAll_By_ManId(String man_acc_id) {
		List<OrderMasterVo> orderList = new ArrayList<>();
		OrderMasterVo order_masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MANID);
			pstmt.setString(1,man_acc_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order_masterVO = new OrderMasterVo();
				order_masterVO.setOrd_id(rs.getString(1));
				order_masterVO.setMem_id(rs.getString(2));
				order_masterVO.setMan_acc_id(rs.getString(3));
				order_masterVO.setGrou(rs.getInt(4));
				order_masterVO.setOute_add(rs.getString(5));
				order_masterVO.setShip_option(rs.getInt(6));
				order_masterVO.setOrd_status(rs.getInt(7));
				orderList.add(order_masterVO);
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
		return orderList;
	}

	@Override
	public String add(OrderMasterVo order_masterVO, List<OrderDetailVo> orderDetailList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_orderid = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = { "ord_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, order_masterVO.getMem_id());
			pstmt.setInt(2, order_masterVO.getGrou());
			pstmt.setString(3, order_masterVO.getOute_add());
			pstmt.setInt(4, order_masterVO.getShip_option());
			pstmt.setInt(5, order_masterVO.getOrd_status());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderid = rs.getString(1);
				System.out.println(" " + next_orderid + "");
			} else {
				System.out.println("");
			}
			rs.close();
			
			OrderDetailService orderDetailSvc = new OrderDetailService();
			System.out.println("orderItemList.size()-A= "
					+ orderDetailList.size());
			double sum = 0;
			int cup=0;
			for (OrderDetailVo orderDetail : orderDetailList) {
				sum+=orderDetail.getOrd_price();
				cup+=orderDetail.getTol_cup();
				orderDetail.setOrd_id(new String(next_orderid));
				orderDetailSvc.addWithOrderMaster(orderDetail, con);
			}
			
			DiscountService disSvc = new DiscountService();
			List<DiscountVO> discountList = disSvc.getAll();
			int dis_cup = discountList.get(0).getDis_cup();
	        double dis_cup_rate = discountList.get(0).getDis_cup_rate();
	        int dis_price = discountList.get(0).getDis_price();
	        double dis_price_rate = discountList.get(0).getDis_price_rate();
	        sum = sum >= dis_price? sum*dis_price_rate : sum*1.0;
	        sum = cup >= dis_cup ? sum*dis_cup_rate : sum*1.0;
			
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getImg(order_masterVO.getMem_id());
			int mem_point = memberVO.getMem_point()-((int)sum);
			System.out.println("mem_point:"+ mem_point);
			memberSvc.update(order_masterVO.getMem_id(), mem_point, con);
	
			con.commit();
			con.setAutoCommit(true);
			System.out.println("orderItemList.size()-B= "
					+ orderDetailList.size());

		} catch (SQLException se) {
			if (con != null) {
				try {
				
					System.err.print("Transaction is being ");
					System.err.println("rolled back-OrderMater");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
		return new String(next_orderid);
	}

	@Override
	public boolean update(String ord_id, Integer ord_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, ord_status);
			pstmt.setString(2, ord_id);

			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return isUpdated;
	}
}


