package com.uni.product.view;

import java.util.ArrayList;
import java.util.Scanner;


import com.uni.product.controller.ProductController;
import com.uni.product.model.dto.Product;
import com.uni.product.model.vo.ProductIO;



public class ProductMenu {
	
	private static Scanner sc = new Scanner(System.in);
	
	private ProductController pController = new ProductController();
	
	public void mainMenu() {
		
		int choice;
		
		do {
			
			System.out.println("*****상품관리프로그램******");
			System.out.println("1. 상품 전체 조회");
			System.out.println("2. 상품 추가");			
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제 ");
			System.out.println("5. 상품 검색");
			System.out.println("6. 상품 입출고 메뉴");			
			System.out.println("9. 프로그램 종료하기 ");
		
			System.out.println("번호 선택 : ");
			
			choice = sc.nextInt();
			
			switch(choice){
			
			case 1 :
				//상품 전체 조회
	            pController.selectAll();
				break;
			
			case 2: 
				//상품 추가
				pController.insertProduct(inputProduct());				
	            break;
		
			case 3 :				
				//상품 수정
				System.out.println("수정하고싶은 상품>>");
				pController.updateProduct(updateProduct());
				break;
				
			case 4 :
				//상품 삭제
				System.out.println("삭제하고싶은 상품>>");
				pController.deleteProduct(inputProductId());
				break;
				
			case 5 :
				//상품검색
				System.out.println("검색하고싶은 상품>>");
				pController.selectName(inputProductName());
				break;				
			
			case 6 :
				//상품 입출고
				ProductStatusSubMenu();
				break;				
		
			case 9 :
				
				System.out.println("정말로 끝내시겠습니까?(y/n)");
				if('y'==sc.next().toLowerCase().charAt(0)) {
					System.out.println("프로그램을 종료합니다. 감사합니다.");
					pController.exitProgram();
					return;
				}else {
					continue;
				}
				
			default :		
				
				System.out.println("번호를 잘못 입력 하셨습니다. 다시 입력하세요.");
				continue;
			}
			
			
		}while(true);
		
		
	}

	
	public void ProductStatusSubMenu() {
		
		int choice;
		
		do {
			
			System.out.println("*****상품 입출고 메뉴******");
			System.out.println("1. 전체 입출고 내역 조회");
			System.out.println("2. 입고 내역 조회");			
			System.out.println("3. 출고 내역 조회");
			System.out.println("4. 상품 입고");
			System.out.println("5. 상품 출고");					
			System.out.println("9. 메인 메뉴로 돌아가기 ");
		
			System.out.println("번호 선택 : ");
			
			choice = sc.nextInt();
			
			switch(choice){
			
			case 1 :
				//1. 전체 입출고 내역 조회
				pController. selectAllStoredAndReleased();				
				break;
			
			case 2:
				//2.입고 내역 조회
				pController. selectStoredList();
	            break;
		
			case 3 :
				//3.출고 내역 조회
				pController. selectReleasedList();
				break;
				
			case 4 :
				//4.상품입고
				System.out.println("입고할 상품>>");
				pController. productStored(inputStored());
				break;
				
			case 5 :
				//5.상품출고
				System.out.println("출고할 상품>>");
				pController. productReleased(inputReleased());
				break;			
				
			case 9 :
				
				System.out.println("메인 메뉴로 돌아갑니다. ");				
				//메인메뉴로 돌아감		
				return;			
			
			default :
				System.out.println("번호를 잘못 입력하였습니다.");
				continue;
			}			
			
		}while(true);
		
		
	}
	
	
	
	private ProductIO inputStored() {
		// TODO Auto-generated method stub
		
		ProductIO pio = new ProductIO();
		
		
		System.out.println("상품 아이디 : ");
		pio.setProductId(sc.next());
		
		System.out.println("입고 수량 : ");
		pio.setAmount(sc.nextInt());
		
		
		return pio;
	}
	
	
	private ProductIO inputReleased() {
		// TODO Auto-generated method stub
		
		ProductIO pio = new ProductIO();
		Product p = new Product();
		
		System.out.println("상품 아이디 : ");
		pio.setProductId(sc.next());
		
		System.out.println("출고 수량 : ");
		pio.setAmount(sc.nextInt());
		
		//출고수량이 재고보다 많을경우
		if(pio.getAmount()>p.getStock()) {
			//우선 추가는 안되게 리턴 null로 보내줌
			System.out.println("서비스 요청 실패 : 출고하고자 하는 제품의 재고수량이 부족합니다.");
			return null;
		}else {
			return pio;
		}	
		
		
	}





	private Product updateProduct() {
		// TODO Auto-generated method stub
		
		Product p = new Product();
		
		p.setProductId(inputProductId());
		
		System.out.println("수정할 제품 정보를 입력하세요 >>");
		System.out.println("상품명 : ");
		p.setpName(sc.next());
		
		System.out.println("가격 : ");
		p.setPrice(sc.nextInt());
		
		System.out.println("부가설명 : ");
		sc.nextLine();
		p.setDescription(sc.nextLine());
		
		System.out.println("재고수량 : ");
		p.setStock(sc.nextInt());
		
		return p;
		
	}





	private Product inputProduct() {
		// TODO Auto-generated method stub
	
		
		Product p = new Product();
		
		System.out.println("새로 추가할 제품 정보를 입력하세요 >>");
		System.out.println("상품 ID : ");
		p.setProductId(sc.next());
	
		System.out.println("상품명 : ");
		p.setpName(sc.next());
		
		System.out.println("가격 : ");
		p.setPrice(sc.nextInt());		
		
		System.out.println("부가설명 : ");
		p.setDescription(sc.next());
		
		//재고수량은 디폴트값으로 넣기? 첫 수량은 0(디폴드값)으로 들어가게 쿼리수정함
		//System.out.println("재고수량 : ");
		//p.setStock(sc.nextInt());
		
		
		return p;
		
	}



	
	
	

	
	
	//상품 이름 입력받는 메소드
	private String inputProductName() {
		// TODO Auto-generated method stub
		
		System.out.println("상품 이름 : ");		
		return sc.next();
	}
	
	//상품 아이디 입력받는 메소드
	private String inputProductId() {
		// TODO Auto-generated method stub
		
		System.out.println("상품 ID : ");
		return sc.next();
	}
	
	

	//실패했을 때 나타나는 문구
	public void displayError(String message) {
		// TODO Auto-generated method stub
		
		System.out.println("서비스 요청 처리 실패 : "+ message);
		
		
	}

	//데이터 없을 때 출력하는 메소드
	public void dispayNoData() {
		// TODO Auto-generated method stub
		System.out.println("조회된 데이터가 없습니다.");
	}


	//전제상품정보 출력 메소드
	public void displayProductList(ArrayList<Product> list) {
		// TODO Auto-generated method stub
		
		
		System.out.println("\n전체 상품 정보는 다음과 같습니다.");
		System.out.println("\n==========상품리스트===============");
		System.out.println("\n상품ID\t상품명\t가격\t부가설명\t재고수량");
		System.out.println("------------------------------------");

		for(Product p : list) {
			System.out.println(p);
		}
		
	}
	
	
	
	public void displayProductNameList(ArrayList<Product> list) {
		// TODO Auto-generated method stub
		
		System.out.println("\n검색된 상품 정보는 다음과 같습니다.");
		System.out.println("\n==========상품리스트===============");
		System.out.println("\n상품ID\t상품명\t가격\t부가설명\t재고수량");
		System.out.println("-------------------------------------------------");

		for(Product p : list) {
			System.out.println(p);
		}
		
		
	}


	public void dispaySuccess(String message) {
		// TODO Auto-generated method stub
		
		System.out.println("서비스 요청 결과 : " + message);
		
	}

	//전체 입출고 내역 출력하는 메소드 
	public void displaySARList(ArrayList<ProductIO> list) {
		// TODO Auto-generated method stub
				
		
		System.out.println("\n===============입출고 리스트=========================");
		System.out.println("\n입출고번호\t상품ID\t상품명\t입출고일\t입출고수량\t입출고상태");
		System.out.println("---------------------------------------------------");

		for(ProductIO p : list) {
			System.out.println(p);
		}
		
		
	}

	public void displayList(ArrayList<ProductIO> list) {
		// TODO Auto-generated method stub
				
		
		System.out.println("\n===============입출고 리스트=========================");
		System.out.println("\n입출고번호\t상품ID\t상품명\t입출고일\t입출고수량\t입출고상태");
		System.out.println("---------------------------------------------------");

		for(ProductIO p : list) {
			System.out.println(p);
		}
		
		
	}
	
	
	public void displayStoredList(ArrayList<ProductIO> list) {
		// TODO Auto-generated method stub
				
		
		System.out.println("\n===============입고 리스트=========================");
		System.out.println("\n입출고번호\t상품ID\t상품명\t입고일\t입고수량\t입출고상태");
		System.out.println("---------------------------------------------------");

		for(ProductIO p : list) {
			System.out.println(p);
		}
		
		
	}

	
	public void displayReleasedList(ArrayList<ProductIO> list) {
		// TODO Auto-generated method stub
				
		
		System.out.println("\n===============출고 리스트=========================");
		System.out.println("\n입출고번호\t상품ID\t상품명\t출고일\t출고수량\t입출고상태");
		System.out.println("---------------------------------------------------");

		for(ProductIO p : list) {
			System.out.println(p);
		}
		
		
	}

	
}
