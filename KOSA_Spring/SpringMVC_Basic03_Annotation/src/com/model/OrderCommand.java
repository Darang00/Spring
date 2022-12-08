package com.model;

import java.util.List;

/*
 주문서 클래스
 하나의 주문은 여러개의 상품을 가질 수 있다
 
 board : reply
 하나의 게시글은 여러개의 댓글이 달릴 수 있다.
 
 하나의 은행은 여러개의 계좌를 가질 수 있다.
 
 */
public class OrderCommand {
	private List<OrderItem> orderItem; //key point
	
	public List<OrderItem> getOrderItam(){
		return this.orderItem;
	}
	public void setOrderitem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}

/*
 주문 발생
 OrderCommand command = new OrderCommand();
 
 List<OrderItem> itemlist = new ArrayList<>();
 itemlist.add(new OrderItem(1, 10, "파손주의"))
 itemlist.add(new OrderItem(10, 1, "리모컨은 별도 구매"))
 
 command.setOrderItem(); //주문한 물건들
 
 */
