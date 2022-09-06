package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
public static Connection getConnection() {
		
		// Connection 객체를 담을 그릇 생성
		Connection conn = null;
		
		// 1, 2) 연결시키기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}


	public static void close(Connection conn) {
	
		try {
			// 주의사항!
			// 만약에 conn이 null이라면 NullpointerException이 발생할수 있음
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void close(Statement stmt) {
			
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}

	public static void close(ResultSet rset) {
		try {
			if(rset!= null && !rset.isClosed()) {
				rset.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
			conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
			conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}









}
