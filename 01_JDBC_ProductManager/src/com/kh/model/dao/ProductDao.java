package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Product;

public class ProductDao {

	/**
	 * 검색하는 SQL 관련 메서드
	 * @return
	 */
	public ArrayList<Product> selectAll() {
		// 결과값이 한행이 아닐거 같아 ArrayList로 받아줄 리스트 및 JDBC관련 필요한 객체 선언
		ArrayList<Product> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM PRODUCT";
		
		try {// JDBC 드라이버 등록, connection, statement 객체 생성, sql문 받고 실행해서 결과값 받기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
												, "JDBC"
												, "JDBC");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			// 한행씩 보고 그 행이 있으면 true인 next()를 이용해서 생성한 객체에 한행씩 담고, 그 담은 객체에서 리스트로 추가(전달)해서 담기 
			while(rset.next()) {
				
				Product p = new Product();
				
				p.setProduct_ID(rset.getString("PRODUCT_ID"));
				p.setProduct_Name(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));
				
				list.add(p);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 사용한 역순으로 닫기
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 리스트로 받은 값들을 전달해주기 위해 리턴
		return list;
	}
	
	/**
	 * 추가하는 SQL 관련 메서드
	 * @param p
	 * @return
	 */
	public int insertProduct(Product p) {
		// 결과값 int로 받아줄 변수 및 JDBC관련 필요한 객체 선언
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// 완성된 SQL문 INSERT문 작성해서 String에 담기
		String sql = "INSERT INTO PRODUCT VALUES("
					+ "'" + p.getProduct_ID() + "', "
					+ "'" + p.getProduct_Name() + "', "
					+ 		p.getPrice() + ", "
					+ "'" + p.getDescription() + "', "
					+		p.getStock() + ")";
		
		try {
			// JDBC 드라이버 등록, connection, statement 객체 생성, sql문 받고 실행해서 결과값 받기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
												"JDBC",
												"JDBC");
			
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			// 결과값이 int로 받기 때문에 실행하면 1씩 증가해서 0보다 크면 성공했다는 의미 따라서 commit하고 false면 rollback해줌
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 생성한 역순으로 닫아주기
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 마지막으로 int 결과값 리턴
		return result;
	}
	
	/**
	 * 이름으로 상품 찾는 SQL 관련 메서드
	 * @param keyword
	 * @return
	 */
	public ArrayList<Product> selectByProductName(String keyword){
		// 결과값이 한행이 아닐거 같아 ArrayList로 받아줄 리스트 및 JDBC관련 필요한 객체 선언
		ArrayList<Product> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 완성된 SQL문 SELECT문 작성해서 String에 담기
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME = '" + keyword + "'";
		
		try {
			// JDBC 드라이버 등록, connection, statement 객체 생성, sql문 받고 실행해서 결과값 받기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			// 한행씩 보고 있으면 true인 next()를 이용해서 생성한 리스트에 SQL결과값 담기
			while(rset.next()) {
				
				list.add(new Product(rset.getString("PRODUCT_ID")
									,rset.getString("PRODUCT_NAME")
									,rset.getInt("PRICE")
									,rset.getString("DESCRIPTION")
									,rset.getInt("STOCK")));
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 사용한 객체 역순으로 닫기
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 마지막으로 결과값 받은 리스트 리턴하기
		return list;
	}
	
	
	/**
	 * 상품 정보 수정하는 SQL 관련 메서드
	 * @param p
	 * @return
	 */
	public int updateProduct(Product p) {
		// 결과값 int로 받아줄 변수 및 JDBC관련 필요한 객체 선언
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// UPDATE문 완성된 상태로 String에 담기
		String sql = "UPDATE PRODUCT SET "
					 + "PRODUCT_NAME = " + "'" + p.getProduct_Name() + "', "
					 + "PRICE = " + p.getPrice() + ", "
					 + "DESCRIPTION = " + "'" + p.getDescription() + "', "
					 + "STOCK = " + p.getStock() 
					 + " WHERE PRODUCT_ID = " + "'" + p.getProduct_ID() +"'";
		
		try {
			// JDBC 드라이버 등록, connection, statement 객체 생성, sql문 받고 실행해서 결과값 받기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC","JDBC");
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			// 결과값이 int로 받기 때문에 실행하면 1씩 증가해서 0보다 크면 성공했다는 의미 따라서 commit하고 false면 rollback해줌
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { // 역순으로 썼던 객체들 닫아줌
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 마지막으로 결과값 리턴
		return result;
	}
	
	
	/**
	 * 상품 삭제하는 SQL 관련 메서드
	 * @param product_ID
	 * @return
	 */
	public int deleteProduct(String product_ID) {
		// 결과값 int로 받아줄 변수 및 JDBC관련 필요한 객체 선언
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// SQL DELETE문 작성해서 스트링 변수에 담기
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = '" + product_ID + "'";
		
		try {
			// JDBC 드라이버 등록, connection, statement 객체 생성, sql문 받고 실행해서 결과값 받기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			// 결과값이 int로 받기 때문에 실행하면 1씩 증가해서 0보다 크면 성공했다는 의미 따라서 commit하고 false면 rollback해줌
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 순서대로 객체 닫아줌
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 마지막으로 결과값 리턴
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
