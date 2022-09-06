package com.kh.view;


import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.RestaurantOrderController;
import com.kh.model.vo.RestaurantMenuList;



// View : 사용자가 보게될 시각적인 요소(화면)(입력 및 출력)
/**
 * @author SON
 *
 */
/**
 * @author SON
 *
 */
public class RestaurantOrderView {
	private Scanner sc = new Scanner(System.in);
	private RestaurantOrderController roc = new RestaurantOrderController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("====== 메인 메뉴 ======");
			
			System.out.println("1. 주문하기");
			System.out.println("2. 음식 전체 조회");
			System.out.println("3. 음식 검색");
			System.out.println("4. 음식 맛 검색~~");
			System.out.println("5. 주문 취소");
			System.out.println("9. 관리자 메뉴");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("\n메뉴 번호를 입력하세요.");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1 :
				categoryChoice();
				break;
			case 2 :
				selectAll();
				break;
			case 3 :
				selectByMenuName();
				break;
			case 4 :
				searchTaste();
				break;
			case 5 :
				cancelOrder();
				break;
			case 9 :
				adminMenu();
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("다시 입력하세요.");
			}
		}
	}

    public void categoryChoice() {
    while (true) {
        System.out.println("\n주문하실 카테고리를 선택해주세요.\n");
        System.out.println("1. 한식");
        System.out.println("2. 중식");
        System.out.println("3. 일식");
        System.out.print("카테고리 번호를 입력해주세요 > ");
        int category = sc.nextInt();
        
        if (0 < category && category < 4) {
            addOrder(category);
            return;
        } else {
            System.out.println("\n카테고리 번호를 잘못 입력하셨습니다.\n다시 입력해주세요.");
        }
    }
}

public void addOrder(int category) {
    
    System.out.println("\n주문정보를 입력해주세요.\n");
    System.out.print("주문자명 입력 > ");
    sc.nextLine();
    String userName = sc.nextLine();
    System.out.print("메뉴 입력 > ");
    String menu = sc.nextLine();
    System.out.print("휴대폰 번호 입력 > ");
    String phone = sc.nextLine();
    System.out.print("배송지 입력 > ");
    String address = sc.nextLine();
    
    roc.addOrder(userName, menu, phone, address, category);
        
}
	
	public void selectAll() {
		System.out.println("---- 메뉴 전체 조회 ----");
		
		roc.selectAll();
	}
	
	public void selectByMenuName() {
		
		System.out.println("---- 회원 이름 키워드 검색 ----");
		System.out.print("메뉴 이름 키워드 검색 > ");
		String keyword = sc.nextLine();
		
		roc.selectByMenuName(keyword);
	}
	
	public void searchTaste() {
	        
	        System.out.println("=== 맛 검색 메뉴입니다 ===");
	        System.out.println("무슨 맛 먹조 (달다, 맵다, 짜다, 고소하다) : ");
	        String taste = sc.nextLine();
	        
	        roc.searchTaste(taste);
	        
	    }
	
    public void cancelOrder() {
        
        System.out.println("\n취소할 주문번호를 입력해주세요.\n");
        System.out.print("주문번호 입력 > ");
        int orderNo = sc.nextInt();
        
        roc.cancelOrder(orderNo);
        
    }
    
	
	

	
	public void adminMenu() {
		while(true) {
		System.out.println("------ 관리자 메뉴 ------");
		System.out.println("1. 메뉴 추가 ");
		System.out.println("2. 메뉴 삭제 ");
		System.out.println("0. 메인 메뉴로 이동");
		
		System.out.println("메뉴 선택 > ");
		int num = sc.nextInt();
		sc.nextLine();
		
		switch (num) {
		case 1 :
			insertMenu();
			break;
		case 2 :
			adminDeleteMenu();
			break;
		case 0 :
			System.out.println("메인메뉴로 넘어가겠습니다!");
			return;
		default:
			System.out.println("다시 입력하세요.");
			
		}
		
		}
	}
	
	private void insertMenu() {
		System.out.println("===== 메뉴 추가하기 =====");
		
		
		System.out.println("카테고리 입력 > ");
		String category = sc.nextLine();

		String lastNO = roc.getLastno(category);
		System.out.println("현재 " + category+ "메뉴의 마지막 번호는 " + lastNO + "입니다.");
		
		System.out.println("메뉴번호 입력 > ");
		String menuno = sc.nextLine();
		System.out.println("메뉴명 입력 > ");
		String menu = sc.nextLine();
		System.out.println("가격 입력 > ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.println("맛 입력(맵다, 짜다, 달다, 고소하다) > ");
		String taste = sc.nextLine();
		
		roc.insertMenu(menuno, menu, price, category, taste);
	}

	
	
	
	
	public void adminDeleteMenu() {
		System.out.println("---- 메뉴 삭제 ----");
		
		System.out.println("삭제하고 싶은 메뉴 이름 > ");
		String menu = sc.nextLine();
		
		roc.adminDeleteMenu(menu);
		
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------------
	// 서비스 요청 처리 후 사용자가 보게 될 응답화면
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공!! -> "  + message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패!! -> " + message);
	}
	
	public void displayNodata(String message) {
		System.out.println(message);
	}
	
	public void displayList(ArrayList<RestaurantMenuList> list) {
		System.out.println("\n조회된 데이터는 " + list.size() + "건 입니다.\n");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void displayOne(RestaurantMenuList m) {
		System.out.println("\n조회된 결과는 다음과 같습니다.\n");
		System.out.println(m);
	}
	
	
	
	
	
	
	
	
	
	

	

}
