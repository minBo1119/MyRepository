package com.uni.product.controller;

import java.util.ArrayList;


import com.uni.product.model.dto.Product;
import com.uni.product.model.exception.ProductException;
import com.uni.product.model.service.ProductService;
import com.uni.product.model.vo.ProductIO;
import com.uni.product.view.ProductMenu;



public class ProductController {

	//전체목록조회
	public void selectAll() {
		// TODO Auto-generated method stub
		ProductMenu menu = new ProductMenu();
		
		//ProductService()클래스의 selectAll()메소드에서 받은 값을 리스트에 넣는다.
		ArrayList<Product> list;
		try {
			list = new ProductService().selectAll();	
		
			//리스트에 뭐가 있으면 리스트를 ProductMenu 클래스의 전체회원 디스플레이리스트 메소드로 보내서 출력한다.
			if(!list.isEmpty()) {
				menu.displayProductList(list);
			
			//목록이 없으면 에러메시지를 출력하는 디스플레이 에러 메시지로 String 타입 메세지를 보내서 출력한다.
			}else{
				menu.dispayNoData();		
			}
		
		}catch (ProductException e) {
			menu.displayError("제품전체 조회 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}
	
	//상품이름으로 조회
	public void selectName(String productName) {
		// TODO Auto-generated method stub
		
		ProductMenu menu = new ProductMenu();
		
		
		
		ArrayList<Product> list;
		try {
			list = new ProductService().selectName(productName);				
			
			if(!list.isEmpty()) {
				menu.displayProductNameList(list);			
			}else{
				menu.dispayNoData();
			}	
			
		} catch (ProductException e) {
			menu.displayError("제품명 조회 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
	}


	public void insertProduct(Product p) {
		// TODO Auto-generated method stub
		
		
		int result;
		try {
			result = new ProductService().insertProduct(p);
			
			//결과값이 1이상 (0보다클때) 회원가입 성공이라는 문구를 dispaySuccess 메소드로 보내서 출력한다.
			if (result > 0) {
				new ProductMenu().dispaySuccess("제품등록 성공");					
			
			}			
			
		} catch (ProductException e) {
			new ProductMenu().displayError("제품 추가 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
		
	}

	public void updateProduct(Product p) {
		// TODO Auto-generated method stub
		
		
		int result;
		try {
			result = new ProductService().updateProduct(p);	
			
			
			if (result>0) {
				new ProductMenu().dispaySuccess("상품수정 성공!");				
			}		
			
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 수정 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}		
		
	}

	public void exitProgram() {
		// TODO Auto-generated method stub
		
		new ProductService().exitProgram();
		
	}

	public void deleteProduct(String ProductId) {
		// TODO Auto-generated method stub
		
				
		int result;
		try {
			result = new ProductService().deleteProduct(ProductId);
			
			if ( result>0 ) {
				new ProductMenu().dispaySuccess("상품 삭제 성공");
			}
		
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 삭제 실패");
			System.out.println(e.getMessage());
		}
		
		
	}

	public void selectAllStoredAndReleased() {
		// TODO Auto-generated method stub
		
		ProductMenu menu = new ProductMenu();
			
		
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectAllStoredAndReleased();			
			
			if(!list.isEmpty()) {
				menu.displaySARList(list);		
			}else{
				menu.dispayNoData();		
			}
		
		}catch (ProductException e) {
			menu.displayError("전체 입출고 내역 조회 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	
		
	}

	public void selectStoredList() {
		// TODO Auto-generated method stub
		ProductMenu menu = new ProductMenu();
			
		
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectStoredList();			
			
			if(!list.isEmpty()) {
				menu.displayStoredList(list);		
			}else{
				menu.dispayNoData();		
			}
		
		}catch (ProductException e) {
			menu.displayError("입고 내역 조회 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}	
		
		
	}

	public void selectReleasedList() {
		// TODO Auto-generated method stub
		
		ProductMenu menu = new ProductMenu();			
		
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectReleasedList();			
			
			if(!list.isEmpty()) {
				menu.displayReleasedList(list);		
			}else{
				menu.dispayNoData();		
			}
		
		}catch (ProductException e) {
			menu.displayError("출고 내역 조회 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
		
		
	}

	public void productStored(ProductIO sAmount) {
		// TODO Auto-generated method stub
		
		
		int result;
		try {
			result = new ProductService().productStored(sAmount);	
			
			
			if (result>0) {
				new ProductMenu().dispaySuccess("상품 입고 성공");				
			}		
			
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 입고 실패. 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}	
		
		
		
	}

	public void productReleased(ProductIO rAmount) {
		// TODO Auto-generated method stub		
		
		
		int result;
		try {
			result = new ProductService().productReleased(rAmount);	
			
			
			if (result>0) {
				new ProductMenu().dispaySuccess("상품 출고 성공");				
			}		
			
	
		}catch (ProductException e) {
			
			new ProductMenu().displayError("상품 출고 실패. 관리자에게 문의하세요");
			
		}	
		
		
		
	}








}
