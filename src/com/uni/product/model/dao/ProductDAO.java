package com.uni.product.model.dao;

import static com.uni.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


import com.uni.product.model.dto.Product;
import com.uni.product.model.exception.ProductException;
import com.uni.product.model.vo.ProductIO;

public class ProductDAO {
	
	//기본생성자 작성 전에 sql구문을 읽어올 프로퍼티즈 참조변수를 선언
	private Properties prop = null;
		
	//외부에서 .properties파일을 읽어와서 prop참조변수에 저장
		
	public ProductDAO() {			
		
		try {
			prop = new Properties();
			prop.load(new FileReader("resources/query.properties")); //.properties파일을 읽는다!
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
	
	public ArrayList<Product> selectAll(Connection conn) throws ProductException{
		
		ArrayList<Product> list = null;		
		Statement stmt = null;
		ResultSet rset = null;
		
		//쿼리 프로퍼티즈에 미리 등록해놓은 selectAll 쿼리를 읽는다.		
		String sql = prop.getProperty("selectAll");
		
		
		
		try {		
			
			 // 쿼리문을 실행할 statement객체 생성
	         stmt = conn.createStatement();

	         // 쿼리문 전송, 실행결과를 ResultSet으로 받기
	         rset = stmt.executeQuery(sql);

	         //전송받은값 리스트에 넣기
	         list = new ArrayList<Product>();
	         while (rset.next()) {

	        	Product p = new Product();
				
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setpName(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));		
				
							
				list.add(p);   
			
	         }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("selectAll 에러 : " + e.getMessage());
		}finally {			
			
			//import static com.uni.common.JDBCTemplate.*;
			//임포트 해줄때 JDBC 템플렛의 스테틱 메소드를 바로 호출해서 사용 할 수 있도록 해준다			
			
			close(rset);					
			close(stmt);					
		
		}		
		//리스트를 호출한 곳으로 보내준다
		return list;
		
	}
	

	public ArrayList<Product> selectName(Connection conn, String productName) throws ProductException {
		// TODO Auto-generated method stub
		
		

		ArrayList<Product> list = null;
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//쿼리 프로퍼티즈에 미리 등록해놓은 selectName 쿼리를 읽는다.
		String sql =prop.getProperty("selectName");;
		
		
		try {
			
			
			// 쿼리문을 실행할 statment 객체 생성			
			pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, "%" + productName + "%");
			//쿼리문 전송 실행 결과를 결과셋으로 받기
			rset = pstmt.executeQuery();
		
			
			//받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Product>();
			
			while(rset.next()) {
				Product p = new Product();
				
				/*PRODUCT_ID
				P_NAME
				PRICE
				DESCRIPTION
				STOCK*/
				
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setpName(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));		
				
							
				list.add(p);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("selectName 에러 : " + e.getMessage());

		}finally {	
		
				close(rset);	
				close(conn);		
			
		}
		
		
		return list;
		
	}

	public int insertProduct(Connection conn, Product p) throws ProductException {
		// TODO Auto-generated method stub
		
		
		int result = 0;		
		PreparedStatement pstmt = null;		
		
		//쿼리 프로퍼티즈에 미리 등록해놓은 insertMember 쿼리를 읽는다.
		String sql = prop.getProperty("insertProduct");	
		
		try {
			
			//쿼리문을 실행할 statment 객체 생성
			
			pstmt = conn.prepareStatement(sql);	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());			
			
			
			result = pstmt.executeUpdate(); //처리한 행의 갯수 리턴
			
		} catch (Exception e) {
			throw new ProductException("insertProduct 에러 : " + e.getMessage());
		}finally {			
			close(pstmt);
		
		}		
		
	return result;		
		
	}

	public int updateProduct(Connection conn, Product p) throws ProductException {
		// TODO Auto-generated method stub
		
		int result = 0;			
		PreparedStatement pstmt = null;		
		
		//쿼리 프로퍼티즈에 미리 등록해놓은 updateProduct 쿼리를 읽는다.
		String sql = prop.getProperty("updateProduct");	
		
		
		try {
			
			//쿼리문을 실행할 statment 객체 생성
			
			pstmt = conn.prepareStatement(sql);				
			conn.setAutoCommit(false);
			
			
			//UPDATE PRODUCT_STOCK SET P_NAME=?, PRICE=?, DESCRIPTION=?, STOCK=? WHERE PRODUCT_ID=?
			
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock());			
			pstmt.setString(5, p.getProductId());
			
			
			result = pstmt.executeUpdate(); //처리한 행의 갯수 리턴		
		
			
		
		} catch (Exception e) {
			throw new ProductException("updateProduct 에러 : " + e.getMessage());
		}finally {					
			
			close(pstmt);			
		}	
			
		return result;	
		
		
		
	}

	public int deleteProduct(Connection conn, String productId) throws ProductException{
		// TODO Auto-generated method stub
		
		int result = 0;		
		
		PreparedStatement pstmt = null;		
		String sql = prop.getProperty("deleteProduct");		
		System.out.println(sql);			    
				
		try {			
			
			pstmt = conn.prepareStatement(sql);				
			conn.setAutoCommit(false);			
			pstmt.setString(1, productId);			
			
			result = pstmt.executeUpdate();		
			System.out.println(result);				
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("deleteProduct 에러 : " + e.getMessage());
		}finally {									
			close(pstmt);			
		}		
			
		return result;	
		
	}

	public ArrayList<ProductIO> selectAllStoredAndReleased(Connection conn) throws ProductException {
		// TODO Auto-generated method stub
		
		
		ArrayList<ProductIO> list = null;		
		Statement stmt = null;
		ResultSet rset = null;
		
				
		String sql = prop.getProperty("selectAllSAR");	
		
		try {	
			
	         stmt = conn.createStatement();	        
	         rset = stmt.executeQuery(sql);         
	         list = new ArrayList<ProductIO>();
	         
	         while (rset.next()) {

	        	ProductIO pio = new ProductIO();
				
	        	pio.setIoNum(rset.getInt("IO_NUM"));
				pio.setProductId(rset.getString("PRODUCT_ID"));
				pio.setpName(rset.getString("P_NAME"));
				pio.setIoDate(rset.getString("IO_DATE"));
				pio.setAmount(rset.getInt("AMOUNT"));
				pio.setStatus(rset.getString("STATUS"));
					
				
							
				list.add(pio);   
			
	         }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("selectAllSAR 에러 : " + e.getMessage());
		}finally {			
			
			close(rset);					
			close(stmt);					
		
		}		
		
		return list;		
		
	}

	public ArrayList<ProductIO> selectStoredList(Connection conn) throws ProductException{
		// TODO Auto-generated method stub
		
		ArrayList<ProductIO> list = null;		
		Statement stmt = null;
		ResultSet rset = null;
		
				
		String sql = prop.getProperty("selectStoredList");	
		
		try {	
			
	         stmt = conn.createStatement();	        
	         rset = stmt.executeQuery(sql);         
	         list = new ArrayList<ProductIO>();
	         
	         while (rset.next()) {

	        	ProductIO pio = new ProductIO();
				
	        	pio.setIoNum(rset.getInt("IO_NUM"));
				pio.setProductId(rset.getString("PRODUCT_ID"));
				pio.setpName(rset.getString("P_NAME"));
				pio.setIoDate(rset.getString("IO_DATE"));
				pio.setAmount(rset.getInt("AMOUNT"));
				pio.setStatus(rset.getString("STATUS"));
					
				
							
				list.add(pio);   
			
	         }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("selectStoredList 에러 : " + e.getMessage());
		}finally {			
			
			close(rset);					
			close(stmt);					
		
		}		
		
		return list;		
	}

	public ArrayList<ProductIO> selectReleasedList(Connection conn) throws ProductException {
		// TODO Auto-generated method stub
		ArrayList<ProductIO> list = null;		
		Statement stmt = null;
		ResultSet rset = null;
		
				
		String sql = prop.getProperty("selectReleasedList");	
		
		try {	
			
	         stmt = conn.createStatement();	        
	         rset = stmt.executeQuery(sql);         
	         list = new ArrayList<ProductIO>();
	         
	         while (rset.next()) {

	        	ProductIO pio = new ProductIO();
				
	        	pio.setIoNum(rset.getInt("IO_NUM"));
				pio.setProductId(rset.getString("PRODUCT_ID"));
				pio.setpName(rset.getString("P_NAME"));
				pio.setIoDate(rset.getString("IO_DATE"));
				pio.setAmount(rset.getInt("AMOUNT"));
				pio.setStatus(rset.getString("STATUS"));
					
				
							
				list.add(pio);   
			
	         }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("selectReleasedList 에러 : " + e.getMessage());
		}finally {			
			
			close(rset);					
			close(stmt);					
		
		}		
		
		return list;		
	}

	public int productStored(Connection conn, ProductIO pio) throws ProductException{
		// TODO Auto-generated method stub
		

		int result = 0;		
		PreparedStatement pstmt = null;		
		
		
		String sql = prop.getProperty("productStored");	
		
		try {			
			//INSERT INTO PRODUCT_IO VALUES (SEQ_IO_NUM.NEXTVAL, ?, DEFAULT, ?, '입고');
			pstmt = conn.prepareStatement(sql);	
			conn.setAutoCommit(false);
			
			
			pstmt.setString(1, pio.getProductId());
			pstmt.setInt(2, pio.getAmount());			
			
			result = pstmt.executeUpdate(); //처리한 행의 갯수 리턴
			
		} catch (Exception e) {
			throw new ProductException("productStored 에러 : " + e.getMessage());
		}finally {			
			close(pstmt);
		
		}		
		
		return result;		
		
		
		
	}

	public int productReleased(Connection conn, ProductIO pio) throws ProductException {
		// TODO Auto-generated method stub
		

		int result = 0;		
		PreparedStatement pstmt = null;		
		
		
		String sql = prop.getProperty("productReleased");	
		
		try {			
			
			//INSERT INTO PRODUCT_IO VALUES (SEQ_IO_NUM.NEXTVAL, ?, DEFAULT, ?, '출고');
			pstmt = conn.prepareStatement(sql);	
			conn.setAutoCommit(false);
			
			pstmt.setString(1, pio.getProductId());
			pstmt.setInt(2, pio.getAmount());	
			
			
			
			result = pstmt.executeUpdate(); //처리한 행의 갯수 리턴
		
		} catch (Exception e) {
			throw new ProductException("productReleased 에러 : " + e.getMessage());
		}finally {			
			close(pstmt);
		
		}		
		
		return result;		
		
		
	}

	

	
	
	
}
