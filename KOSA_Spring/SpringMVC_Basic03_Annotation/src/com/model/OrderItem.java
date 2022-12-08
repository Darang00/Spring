package com.model;

import lombok.Data;

//상품 테이블이 있다고 가정
//1:1 mapping

@Data
public class OrderItem {
	private int itemid;
	private int number;
	private String remark;
	
}
