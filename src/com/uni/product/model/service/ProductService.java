package com.uni.product.model.service;

import static com.uni.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.ArrayList;


import com.uni.product.model.dao.ProductDAO;
import com.uni.product.model.dto.Product;
import com.uni.product.model.exception.ProductException;
import com.uni.product.model.vo.ProductIO;

public class ProductService {

	
	public ArrayList<Product> selectAll() throws ProductException{
		// TODO Auto-generated method stub
		
		
		Connection conn = getConnection();		
		ArrayList<Product> list = new ProductDAO().selectAll(conn);		
		
		return list;		
		
	}
	
	
	public ArrayList<Product> selectName(String productName) throws ProductException{
		// TODO Auto-generated method stub
		
		
		Connection conn = getConnection();
		
		//MemberDAO() 클래스의 selectOne(conn, memberName)메소드로 커넥션과 멤버 아이디값을 보내서 받은 값을 리스트에 넣는다
		ArrayList<Product> list = new ProductDAO().selectName(conn, productName);
		//소환한 곳으로 리스트를 보내준다~!!!=>멤버 컨트롤러클래스에서 받아서 쓸 수 있음
		return list;
		
		
	}


	public int insertProduct(Product p) throws ProductException{
		// TODO Auto-generated method stub
		
		
		Connection conn = getConnection();
		
		
		int result = new ProductDAO().insertProduct(conn, p);
		
		//결과값이 0이상이라면 커밋한다!
		if(result>0) commit(conn);
		//암것도 없다면 롤백한다.
		else rollback(conn);
		
		
		return result;	
		
	}


	public int updateProduct(Product p) throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();
		
		int result = new ProductDAO().updateProduct(conn, p);		
		
		if(result>0) commit(conn);
		else rollback(conn);		
		
		return result;		
		
	}


	public void exitProgram() {
		// TODO Auto-generated method stub
		close(getConnection());
	}


	public int deleteProduct(String productId) throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();		
		int result = new ProductDAO().deleteProduct(conn, productId);
		
		
		if(result>0) commit(conn);
		else rollback(conn);		
		
		return result;
	}


	public ArrayList<ProductIO> selectAllStoredAndReleased() throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();		
		ArrayList<ProductIO> list = new ProductDAO().selectAllStoredAndReleased(conn);		
		
		return list;		
		
	}


	public ArrayList<ProductIO> selectStoredList() throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();		
		ArrayList<ProductIO> list = new ProductDAO().selectStoredList(conn);		
		
		return list;	
		
	}


	public ArrayList<ProductIO> selectReleasedList() throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();		
		ArrayList<ProductIO> list = new ProductDAO().selectReleasedList(conn);		
		
		return list;	
		
	}


	public int productStored(ProductIO sAmount) throws ProductException{
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();
		
		int result = new ProductDAO().productStored(conn, sAmount);		
		
		if(result>0) commit(conn);
		else rollback(conn);		
		
		return result;		
		
		
		
	}


	public int productReleased(ProductIO rAmount) throws ProductException{
		// TODO Auto-generated method stub
		
		
		Connection conn = getConnection();
		
		int result = new ProductDAO().productReleased(conn, rAmount);		
		
		if(result>0) commit(conn);
		else rollback(conn);		
		
		return result;	
		
		
	}





	


}
