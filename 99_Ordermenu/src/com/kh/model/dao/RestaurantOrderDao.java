package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.OrderInfo;
import com.kh.model.vo.RestaurantMenuList;


public class RestaurantOrderDao {
	

    public int addOrder(OrderInfo o, Connection conn) {
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        // INSERT INTO ORDERINFO 
        // VALUES (SEQ_ORDER.NEXTVAL, 'userName', 'menu', 'phone', 'address', DEFAULT);
        
        String sql = "INSERT INTO ORDERINFO "
                        + "VALUES (SEQ_ORDER.NEXTVAL, ?, ?, ?, ?, DEFAULT)";
        
        try {
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, o.getUserName());
            pstmt.setString(2, o.getMenu());
            pstmt.setString(3, o.getPhone());
            pstmt.setString(4, o.getAddress());
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
        
    }
	
	
	
	
	
	public ArrayList<RestaurantMenuList> selectAll(Connection conn){
		

		ArrayList<RestaurantMenuList> list = new ArrayList<>();
		
	
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		
		String sql = "SELECT * FROM MENULIST ORDER BY MENUNO ASC";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new RestaurantMenuList(
									rset.getString("MENUNO")
								   ,rset.getString("MENU")
								   ,rset.getInt("PRICE")
								   ,rset.getString("CATEGORY")
								   ,rset.getString("TASTE")));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	

	
	public ArrayList<RestaurantMenuList> selectByMenuName(Connection conn, String keyword){
		ArrayList<RestaurantMenuList> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MENULIST WHERE MENU LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new RestaurantMenuList(rset.getString("MENUNO")
												,rset.getString("MENU")
												,rset.getInt("PRICE")
												,rset.getString("CATEGORY")
												,rset.getString("TASTE")));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
public ArrayList<RestaurantMenuList> searchTaste(Connection conn, String taste){
        
        ArrayList<RestaurantMenuList> list = new ArrayList<>();
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = "SELECT * FROM MENULIST WHERE TASTE = ?";
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, taste);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                list.add(new RestaurantMenuList(rset.getString("MENUNO")
                                               ,rset.getString("MENU")
                                               ,rset.getInt("PRICE")
                                               ,rset.getString("CATEGORY")
                                               ,rset.getString("TASTE")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
        
    }
    
    public int selectTaste(Connection conn, String taste) {
        
        int flag = 0;
        PreparedStatement pstmt = null;
        
        String sql = "SELECT * FROM MENULIST WHERE TASTE = ?";
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, taste);
            
            flag = pstmt.executeQuery().next()? 1 : 0;
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return flag;
        
    }
    
    public int cancelOrder(int orderNo, Connection conn) {
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        // DELETE ORDERINFO 
        // WHERE ORDERNO = 'orderNo';
        
        String sql = "DELETE ORDERINFO "
                        + "WHERE ORDERNO = ?";
        
        try {
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNo);
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
        
    }
    
    
	public RestaurantMenuList getLastNo(Connection conn, String category) {
			
			PreparedStatement pstmt = null;
			ResultSet rSet = null;
			RestaurantMenuList r = null;
	
			String sql = "SELECT MAX(MENUNO) FROM MENULIST WHERE CATEGORY = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category);
				
				rSet = pstmt.executeQuery();
				
				if (rSet.next()) {
					r = new RestaurantMenuList();
					r.setMenuNo(rSet.getString("MAX(MENUNO)"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rSet);
				close(pstmt);
			}
			
			return r;
		}

    
	
	public int insertMenu(Connection conn, String menuno, String menu, int price, String category, String taste) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String sql = "INSERT INTO MENULIST VALUES(?, ?, ?, ?, ?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, menuno);
				pstmt.setString(2, menu);
				pstmt.setInt(3, price);
				pstmt.setString(4, category);
				pstmt.setString(5, taste);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

    
    
    
    
    
    
	public int adminDeleteMenu(Connection conn, String menu) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MENULIST WHERE MENU = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
public int menuCheck(OrderInfo o, Connection conn, int category) {
		
		int result = 0;
		String cc = null;
		String nullCheck = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		if (category == 1) {
			cc = "K";
		} else if (category == 2) {
			cc = "C";
		} else if (category == 3) {
			cc = "J";
		}
		
		// SELECT MENUNO MENU
		// FROM MENULIST
		// WHERE MENU = 'menu'
		String menuCheck = "SELECT MENUNO, MENU "
							+ "FROM MENULIST "
							+ "WHERE MENU = ?";
		
		try {
			
			pstmt = conn.prepareStatement(menuCheck);
			pstmt.setString(1, o.getMenu());
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				nullCheck = rset.getString("MENU");
			}
			
			if (nullCheck != null) {
				result = 1;
				if (rset.getString("MENUNO").contains("K")) {
					if (cc.contains("K")) {
						result = 2;
					} else {
						result = 5;
					}
				} else if (rset.getString("MENUNO").contains("C")) {
					if (cc.contains("C")) {
						result = 3;
					} else {
						result = 6;
					}
				} else if (rset.getString("MENUNO").contains("J")) {
					if (cc.contains("J")) {
						result = 4;
					} else {
						result = 7;
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
