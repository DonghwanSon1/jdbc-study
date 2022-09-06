package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.RestaurantOrderService;
import com.kh.model.vo.OrderInfo;
import com.kh.model.vo.RestaurantMenuList;
import com.kh.view.RestaurantOrderView;



public class RestaurantOrderController {


    public void addOrder(String userName, String menu, String phone, String address, int category) {
        
        OrderInfo o = new OrderInfo(userName, menu, phone, address);
        
        int result = new RestaurantOrderService().addOrder(o, category);
        
        if (result == 1) {
            new RestaurantOrderView().displaySuccess("주문 접수 성공");
        } else if (result == 2) {
            new RestaurantOrderView().displayFail("한식 메뉴만 주문 가능합니다.");
        } else if (result == 3) {
            new RestaurantOrderView().displayFail("중식 메뉴만 주문 가능합니다.");
        } else if (result == 4) {
            new RestaurantOrderView().displayFail("일식 메뉴만 주문 가능합니다.");
        } else {
            new RestaurantOrderView().displayFail("등록되지 않은 메뉴는 주문이 불가합니다.");
        }
    }
	

	public void selectAll() {

		
		ArrayList<RestaurantMenuList> list = new RestaurantOrderService().selectAll();
		

		if(list.isEmpty()) {
			new RestaurantOrderView().displayNodata("조회 실패했습니다.");
		} else {
			new RestaurantOrderView().displayList(list);
		}
		
	}
	
	
	public void selectByMenuName(String keyword) {
		ArrayList<RestaurantMenuList> list = new RestaurantOrderService().selectByMenuName(keyword);
		
		if(list.isEmpty()) {
			new RestaurantOrderView().displayFail("조회 실패했습니다.");
		} else {
			new RestaurantOrderView().displayList(list);
		}
	}
		
	public void searchTaste(String taste) {
	        
	        int flag = new RestaurantOrderService().selectTaste(taste);
	        
	        if(flag == 0) {
	            new RestaurantOrderView().displayFail("해당 맛이 없어요~ 추가 예정입니다!");
	            return;
	        }
	        
	        
	        ArrayList<RestaurantMenuList> list = new ArrayList<>();
	        
	        list = new RestaurantOrderService().searchTaste(taste);
	        
	        if(list.isEmpty()) {
	            new RestaurantOrderView().displayFail("검색 실패!");
	        } else {
	            new RestaurantOrderView().displayList(list);
	        }
	        
	    }
	
	
    public void cancelOrder(int orderNo) {
        
        int result = new RestaurantOrderService().cancelOrder(orderNo);
        
        if (result > 0) {
            new RestaurantOrderView().displaySuccess("주문 취소 성공");
        } else {
            new RestaurantOrderView().displayFail("주문 취소 실패");
        }
    }

	public String getLastno(String category) {
			
			RestaurantMenuList r = new RestaurantOrderService().getLastNo(category);
			
			return r.getMenuNo();
		}
	
		
	public void insertMenu(String menuno, String menu, int price, String category, String taste) {
		
		int result = new RestaurantOrderService().insertMenu(menuno, menu, price, category, taste);
		
		if (result > 0) {
			new RestaurantOrderView().displaySuccess("메뉴 추가 성공");
		} else {
			new RestaurantOrderView().displayFail("메뉴 추가 실패");
		}
		
	}

	
	
	public void adminDeleteMenu(String menu) {
		
		int result = new RestaurantOrderService().adminDeleteMenu(menu);
		
		if(result > 0) {
			new RestaurantOrderView().displaySuccess("메뉴 삭제 성공했습니다.");
		} else {
			new RestaurantOrderView().displayFail("메뉴 삭제 실패했습니다.");
		}	
	}
	
	
	
	
	

}
