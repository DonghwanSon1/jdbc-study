package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

// Controller : View를 통해서 요청 기능을 처리하는 담당
//				해당 메서드로 전달된 데이터를 가공처리 한 후 Dao메서드를 호출
// 				Dao로부터 반환받은 결과에 따라서 사용자가 보게될 View(응답화면)을 결정 (View 메서드 호출)

public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, 
							String gender, int age, String email, String phone, 
							String address, String hobby) {
	
		// 1. 전달된 데이터들을 Member 객체에 담기 => 가공처리
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. Service의 insertMember를 호출
		int result = new MemberService().insertMember(m);
		
		// 3. 결과값에 따른 출력화면
		if(result > 0) {
			new MemberView().displaySuccess("성공적으로 회원 추가했습니다.");
		} else {
			new MemberView().displayFail("회원 추가를 실패했습니다..");
		}
		
	}
	

	public void selectAll() {
		// SELECT -> Result -> ArrayList
		
		ArrayList<Member> list = new MemberService().selectAll();
		
		// 조회결과가 있는지 없는지 판단 후 사용자가 보게 될 View화면 지정
		if(list.isEmpty()) {
			new MemberView().displayNodata("조회 실패했습니다.");
		} else {
			new MemberView().displayList(list);
		}
		
	}
	

	public void selectByUserId(String userId) {
		Member m = new MemberService().selectByUserId(userId);
		
		if(m.getUserId() == null) {
			new MemberView().displayFail(userId + "의 검색결과가 없습니다.");
		} else {
			new MemberView().displayOne(m);
		}
		
	}
	
	
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			new MemberView().displayFail("조회 실패했습니다.");
		} else {
			new MemberView().displayList(list);
		}
	}
	

	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		
		int result = new MemberService().updateMember(m);
		
		if(result > 0) {
			new MemberView().displaySuccess("정보 수정 성공했습니다.");
		} else {
			new MemberView().displayFail("정보 수정 실패했습니다.");
		}
		
	}
	
	
	public void deleteUserId(String userId) {
		
		int result = new MemberService().deleteUserId(userId);
		
		if(result > 0) {
			new MemberView().displaySuccess("회원 삭제 성공했습니다.");
		} else {
			new MemberView().displayFail("회원 삭제 실패했습니다.");
		}
		
	}
	
	
	
	
	
	

}
