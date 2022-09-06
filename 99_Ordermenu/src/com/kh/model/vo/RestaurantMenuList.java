package com.kh.model.vo;

public class RestaurantMenuList {
	
//	MENUNO	NUMBER
//	MENU	VARCHAR2(15 BYTE)
//	PRICE	NUMBER
//	CATEGORY	VARCHAR2(15 BYTE)
//	TASTE	VARCHAR2(15 BYTE)
	
	private String menuNo;
	private String menu;
	private int price;
	private String category;
	private String taste;
	
	// 기본생성자
	public RestaurantMenuList() {
		super();
	}
	
	// 시퀀스를 뺀 매개변수 생성자
	public RestaurantMenuList(String menu, int price, String category, String taste) {
		super();
		this.menu = menu;
		this.price = price;
		this.category = category;
		this.taste = taste;
	}
	
	// 모든 필드를 포함하는 매개변수 생성자
	public RestaurantMenuList(String menuNo, String menu, int price, String category, String taste) {
		super();
		this.menuNo = menuNo;
		this.menu = menu;
		this.price = price;
		this.category = category;
		this.taste = taste;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	@Override
	public String toString() {
		return "RestaurantMenuList [메뉴 번호 : " + menuNo + ", 메뉴 :" + menu + ", 가격 : " + price + ", 카테고리 :" + category
				+ ", 맛 : " + taste + "]" ;
		}

	

}