package com.kh.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.RestaurantOrderDao;
import com.kh.model.vo.OrderInfo;
import com.kh.model.vo.RestaurantMenuList;



public class RestaurantOrderService {

public int addOrder(OrderInfo o, int category) {
		
		Connection conn = getConnection();
		int result = 0;
		int menuCheck = new RestaurantOrderDao().menuCheck(o, conn, category);
		
		if (menuCheck == 1 || menuCheck == 2 || menuCheck == 3 || menuCheck == 4) {
			
			result = new RestaurantOrderDao().addOrder(o, conn);
			commit(conn);
		} else if (menuCheck == 5) {
			result = 5;
		} else if (menuCheck == 6) {
			result = 6;
		} else if (menuCheck == 7) {
			result = 7;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<RestaurantMenuList> selectAll(){
		
		Connection conn = getConnection();

		ArrayList<RestaurantMenuList> list = new RestaurantOrderDao().selectAll(conn);
		
		close(conn);
		
		
		return list;
	}
	

	public ArrayList<RestaurantMenuList> selectByMenuName(String keyword){
		
		Connection conn = getConnection();
		
		ArrayList<RestaurantMenuList> list = new RestaurantOrderDao().selectByMenuName(conn, keyword);
		
		close(conn);
		
		return list;
	}
	
		
	public int selectTaste(String taste){
	        
	        Connection conn = getConnection();
	        
	        int flag = new RestaurantOrderDao().selectTaste(conn, taste);
	        
	        close(conn);
	        
	        return flag;
	        
	    }
	    
	    public ArrayList<RestaurantMenuList> searchTaste(String taste){
	        
	        Connection conn = getConnection();
	        
	        ArrayList<RestaurantMenuList> list = new RestaurantOrderDao().searchTaste(conn, taste);
	        
	        close(conn);
	        
	        return list;
	        
	    }
	    
	    public int cancelOrder(int orderNo) {
	        
	        Connection conn = getConnection();
	        int result = new RestaurantOrderDao().cancelOrder(orderNo, conn);
	        
	        if (result > 0) {
	            commit(conn);
	        } else {
	            rollback(conn);
	        }
	        
	        close(conn);
	        
	        return result;
	        
	    }
	
	    public RestaurantMenuList getLastNo(String category) {
			
			Connection conn = getConnection();
			
			RestaurantMenuList r = new RestaurantOrderDao().getLastNo(conn, category);
			
			close(conn);
			
			return r;
		}

	    public int insertMenu(String menuno, String menu, int price, String category, String taste) {
			
			Connection conn = getConnection();
			
			int result = new RestaurantOrderDao().insertMenu(conn, menuno, menu, price, category, taste);
			
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}

	    
	    
	    
	    
	    
	    
	    
	    
	public int adminDeleteMenu(String menu) {
		
		Connection conn = getConnection();
		int result = new RestaurantOrderDao().adminDeleteMenu(conn, menu);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
