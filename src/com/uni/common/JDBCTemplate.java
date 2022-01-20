package com.uni.common;





import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTemplate {	
	
	private static Connection conn = null;
	
	//DB연결을 위해 공용 커넥션 객체를 반환해주는 메소드
	
	public static Connection getConnection() {
		
		
		
	
		
		  if(conn == null) {
			  
			  /* 이전 프로 젝트에서는 
				 * JDBC 드라이버 로드 , db 연결을 위한 정보 (url, id, password )를 직접 코드에 작성 (정적코딩)
				 * --> 추후 DB 자체 변경또는 연결정보가 변경되는 경우 코드를 직접 수정하고 다시 컴파일 해야함
				 * --> 유지 보수에 불편 
				 * 
				 * 별도의 Properies 파일을 만들어 프로그램실행시 동적으로 DB 연결 정보를 불러올수있도록 진행
				 * 
				 */

				
						
						//외부에서 정보를 읽어와 저장할 priperties 객체 선언 및 생성
						
						Properties prop = new Properties();
						
						//드라이버 프로퍼디스 파일을 읽을것임
						
						
						
							try {
								prop.load(new FileReader("resources/driver.properties"));
								Class.forName(prop.getProperty("driver"));
								conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
								conn.setAutoCommit(false);
								
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							

							
					
						
						
					
			
		  }
		  
		return conn;
	}
	
	
	
	//열어놨던 Connection을 닫는 메소드 이다. 스테틱으로 선언 했기 때문에 편하게 사용가능하다.
	public static void close(Connection conn) {
		
		//Connection가 널이 아니고 닫히지 않았을때 닫는다.
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//열어놨던 Statement을 닫는 메소드 이다. 스테틱으로 선언 했기 때문에 편하게 사용가능하다.
	public static void close(Statement stmt) {
		
		//stmt가 널이 아니고 닫히지 않았을때 닫는다.
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//열어놨던 ResultSet을 닫는 메소드 이다. 스테틱으로 선언 했기 때문에 편하게 사용가능하다.
	public static void close(ResultSet rest) {
		//rest가 널이 아니고 닫히지 않았을때 닫는다.
		try {
			if(rest != null && !rest.isClosed()) {
				rest.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
