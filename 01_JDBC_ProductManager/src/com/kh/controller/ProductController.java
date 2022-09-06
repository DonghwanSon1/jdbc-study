package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.view.ProductView;

public class ProductController {


	/**
	 * 전체 상품을 검색하는 메서드
	 */
	public void selectAll() {
		// Dao로가서 selectAll 메서드로 이동 결과가 하나의 행이 아니기 때문에 ArrayList로 받을려고 list생성
		ArrayList<Product> list = new ProductDao().selectAll();
		
		// 리스트가 비워있으면 아무것도 없다는 뜻이기 때문에 true면 조회결과 없음 false면 list 출력하는 메서드 실행
		if(list.isEmpty()) {
			new ProductView().displayNodata("조회결과 없습니다!");
		} else {
			new ProductView().displayList(list);
		}
	}
	
	/**
	 * 상품 추가하는 메서드
	 * @param product_ID 상품아이디
	 * @param product_Name 상품명
	 * @param price 가격
	 * @param description 상품 설명 
	 * @param stock 재고
	 */
	public void insertProduct(String product_ID, String product_Name, int price, String description, int stock ) {
		// 추가할려는 매개변수들을 받아 새로운 객체에 담아줘야 되서 객체생성
		Product p = new Product(product_ID, product_Name, price, description, stock);
		
		// Dao 클래스로 가서 insertProduct로 가서 객체 p를 넘겨주는 동시에 result값에 넣는다. (결과값이 int여서 실행하면 1씩 증가하기 때문)
		int result = new ProductDao().insertProduct(p);
		
		// 만약 결과값이 0 이상이면 Dao클래스에서 성공적으로 실행했다는 의미 (인트로 받기때문에) true면 성공 false면 실패
		if(result > 0 ) {
			new ProductView().displaySuccess("상품 추가 성공");
		} else {
			new ProductView().displayFail("상품 추가 실패..");
		}
		
		
	}
	
	/**
	 * 상품 이름으로 검색하는 메서드
	 * @param keyword 상품 이름
	 */
	public void selectByProductName(String keyword) {
		// Dao클래스로 가서 selectByProductName메서드로 이동후 매개변수 keyword를 준다. 상품이름을 조회해서 결과값을 주는건데
		// 상품이름이 SQL에서 유니크로 주지 않았기 때문에 동일 이름이 있을수 있어 리스트로 받게함
		ArrayList<Product> list = new ProductDao().selectByProductName(keyword);
		
		// 리스트가 비워있으면 keyword로 조회한 상품이름이 같은게 없다는 의미 false면 리스트 출력
		if(list.isEmpty()) {
			new ProductView().displayFail(keyword + "의 검색 결과 없습니다.");
		} else {
			new ProductView().displayList(list);
		}
	}
	
	/**
	 * 상품 정보 수정하는 메서드
	 * @param product_ID 상품아이디 같은걸로 하기 위함
	 * @param product_Name 상품 이름 변경
	 * @param price 가격 변경
	 * @param description 설명 변경
	 * @param stock 재고 변경
	 */
	public void updateProduct(String product_ID, String product_Name, int price, String description, int stock) {
		
		// 위에 매개변수들을 담아줄 객체 생성
		Product p = new Product();
		// 매개변수로 받은 값으로 수정을 하기 위해 새로 set해서 p에 담는다. id는 id로 조회할려고 하는것
		p.setProduct_ID(product_ID);
		p.setProduct_Name(product_Name);
		p.setPrice(price);
		p.setDescription(description);
		p.setStock(stock);
		
		// Dao 클래스로 가서 updateProduct로 가서 객체 p를 넘겨주는 동시에 result값에 넣는다. (결과값이 int여서 실행하면 1씩 증가하기 때문)
		int result = new ProductDao().updateProduct(p);
		
		// 만약 결과값이 0 이상이면 Dao클래스에서 성공적으로 실행했다는 의미 (인트로 받기때문에) true면 성공 false면 실패
		if(result > 0) {
			new ProductView().displaySuccess("상품 정보 수정 했습니다.!");
		} else {
			new ProductView().displayFail("상품 정보 수정하지 못했습니다");
		}
	}
	
	/**
	 * 상품 삭제하는 메서드
	 * @param product_ID 아이디로 찾아 삭제함
	 */
	public void deleteProduct(String product_ID) {
	
		// Dao 클래스로 가서 deleteProduct로 가서 product_ID 매개변수를 넘겨주는 동시에 result값에 넣는다. (결과값이 int여서 실행하면 1씩 증가하기 때문)
		int result = new ProductDao().deleteProduct(product_ID);
		
		// 만약 결과값이 0 이상이면 Dao클래스에서 성공적으로 실행했다는 의미 (인트로 받기때문에) true면 성공 false면 실패
		if(result > 0) {
			new ProductView().displaySuccess(product_ID + "의 상품을 삭제 하였습니다.");
		} else {
			new ProductView().displayFail("상품 삭제를 실패했습니다.");
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
