package com.uni.product.model.vo;

import java.io.Serializable;

public class ProductIO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4581275146818219177L;

	
	
	private int ioNum;
	private String productId;
	private String pName;
	private String ioDate;
	private int amount;
	private String status;
	
	public ProductIO() {
		// TODO Auto-generated constructor stub
	}

	public ProductIO(int ioNum, String productId, String pName, String ioDate, int amount, String status) {
		super();
		this.ioNum = ioNum;
		this.productId = productId;
		this.pName = pName;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}

	
	public int getIoNum() {
		return ioNum;
	}
	public void setIoNum(int ioNum) {
		this.ioNum = ioNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getIoDate() {
		return ioDate;
	}
	public void setIoDate(String ioDate) {
		this.ioDate = ioDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return ioNum + ",    " + productId + ",  " + pName + ",  " + ioDate
				+ ",  " + amount + ",  " + status;
	}
	
	
	
}
