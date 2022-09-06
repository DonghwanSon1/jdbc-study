package com.kh.model.vo;

public class Product {
	
	private String product_ID;
	private String product_Name;
	private int price;
	private String description;
	private int stock;
	
	
	public Product() {}


	public Product(String product_ID, String product_Name, int price, String description, int stock) {
		super();
		this.product_ID = product_ID;
		this.product_Name = product_Name;
		this.price = price;
		this.description = description;
		this.stock = stock;
	}


	public String getProduct_ID() {
		return product_ID;
	}


	public void setProduct_ID(String product_ID) {
		this.product_ID = product_ID;
	}


	public String getProduct_Name() {
		return product_Name;
	}


	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "제품 => [ 상품아이디 : " + product_ID + ", 상품명 : " + product_Name + ", 상품 가격 : " + price
				+ ", 상품상세정보 : " + description + ", 재고 : " + stock + " ] ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
