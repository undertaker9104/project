package com.ordermaster.model;

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
import com.orderdetail.model.OrderDetailDao;
import com.orderdetail.model.OrderDetailVo;
import com.shoppingcart.model.ShoppingCartVO;


public class OrderMasterDao implements OrderMasterDao_interface {
	private static final String FIND_BY_MEM = "SELECT * FROM order_master WHERE mem_id = ? order by ORD_ID desc";
	private static final String FIND_BY_PK = "SELECT * FROM order_master WHERE ord_id = ?";
	private static final String FIND_BY_TAKE = "SELECT * FROM order_master WHERE ship_option ='0' order by ORD_ID desc";
	private static final String FIND_BY_OUT = "SELECT * FROM order_master WHERE ship_option ='1' order by ORD_ID desc ";
	private static final String GET_ALL = "SELECT * FROM order_master order by ORD_ID desc ";
	private static final String INSERT_STMT = "INSERT INTO order_master(ORD_ID, MEM_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS) "
			                                  + "VALUES(to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),?, ?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE order_master SET ord_status = ? WHERE ord_id = ?";
			
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
					System.err.println("rolled back-嚙踐��-OrderItem");
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
}


