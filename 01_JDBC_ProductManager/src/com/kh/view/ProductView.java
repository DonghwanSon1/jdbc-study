package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductView {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("------ 제품 관리 프로그램 ------");
			System.out.println("1. 상품 전체 조회 하기 ");
			System.out.println("2. 상품 추가 하기 ");
			System.out.println("3. 상품명 검색 하기 (상품 이름으로 키워드 검색) ");
			System.out.println("4. 상품 정보 수정 하기 (상품 id로 조회하고 수정) ");
			System.out.println("5. 상품 삭제 하기 (상품 id로 조회해서 삭제) ");
			System.out.println("0. 프로그램 종료하기 ");
			System.out.println("이용할 메뉴 선택 > ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			
			case 1 :
				selectAll();
				break;
			case 2 :
				insertProduct();
				break;
			case 3 :
				selectByProductName();
				break;
			case 4 :
				updateProduct();
				break;
			case 5 :
				deleteProduct();
				break;
			case 0 : 
				System.out.println("프로그램 종료합니다.");
				return;
			default : 
				System.out.println("이상한거 누르지말아주세요!");
			
			}
			
			
			
			
			
			
		}
	}
	
	
	public void selectAll() {
		
		System.out.println("---- 회원 전체 조회 ----");
		
		
		pc.selectAll();
	}
	
	
	 public void insertProduct() {
		 
		 System.out.println("---- 제품 추가 ----");
		 System.out.println("상품 아이디 > ");
		 String product_ID = sc.nextLine();
		 System.out.println("상품명 > ");
		 String product_Name = sc.nextLine();
		 System.out.println("상품 가격 > ");
		 int price = sc.nextInt();
		 sc.nextLine();
		 System.out.println("상품 상세 정보 > ");
		 String description = sc.nextLine();
		 System.out.println("재고 > ");
		 int stock = sc.nextInt();
		 sc.nextLine();
		 
		 pc.insertProduct(product_ID, product_Name, price, description, stock);
		 
	 }
	
	 
	 public void selectByProductName() {
		 System.out.println("---- 상품명 검색 ----");
		 System.out.print("상품 이름 > ");
		 String keyword = sc.nextLine();
		 
		 pc.selectByProductName(keyword);
	 }
	 
	 
	 public void updateProduct() {
		 System.out.println("---- 상품 정보 수정 ----");
		 System.out.println("상품 id > ");
		 String product_ID = sc.nextLine();
		 
		 System.out.println("바꿀 상품명 > ");
		 String product_Name = sc.nextLine();
		 System.out.println("바꿀 가격 > ");
		 int price = sc.nextInt();
		 sc.nextLine();
		 System.out.println("바꿀 상품 상세 정보 > ");
		 String description = sc.nextLine();
		 System.out.println("바꿀 상품 재고 수 > ");
		 int stock = sc.nextInt();
		 
		 pc.updateProduct(product_ID, product_Name, price, description, stock);
	 }
	 
	 
	 public void deleteProduct() {
		 System.out.println("---- 상품 삭제 ----");
		 System.out.print("상품 id > ");
		 String product_ID = sc.nextLine();
		 
		 pc.deleteProduct(product_ID);
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void displaySuccess(String message) {
			System.out.println("\n서비스 요청 성공!! -> "  + message);
		}
		
		public void displayFail(String message) {
			System.out.println("\n서비스 요청 실패!! -> " + message);
		}
	
	public void displayNodata(String message) {
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Product> list) {
		System.out.println("\n조회된 데이터는 " + list.size() + "건 입니다.\n");
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "번째 제품 : " + list.get(i));
		}
	}
	
	
}
