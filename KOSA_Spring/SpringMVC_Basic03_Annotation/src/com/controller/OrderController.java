package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.OrderCommand;

/*
 하나의 요청 주소로 전송 방식에 대해서 2개의 업무 처리가 가능하다.
 /order/order.do
 
   GET 방식으로 들어오면 > 화면 주세요
   POST 방식으로 들어오면 > 처리해주세요
  
 */
@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	@GetMapping
	public String form() {
		return "order/OrderForm";
		// WEB-INF/views/ + ... + .jsp
	}
	
	@PostMapping
	public String submit(OrderCommand ordercommand) {
		/*
		 자동화된 코드를 보여드릴게요
		 1. OrderCommand ordercommand = new OrderCommand()
		 1.1 자동 매핑 >> member field >> private List<OrderItem> orderItem 자동 주입
		 
		 2. List<OrderItem> item = new ArrayList<>();
		 >>>> orderItem[0].itemid > 1
		 >>>> orderItem[0].number > 10
		 >>>> orderItem[0].remark
		 item.add(new OrderItem(1, 10, "주의하세요");
		 
		 ...
		 item.add(new OrderItem(2, 10, "주의하세요")
		  ...
		 item.add(new OrderItem(3, 10, "주의하세요")
		 
		 ordercommand.setOrderItem(item);
		 
		 원리: key 값(view) >> OrderCommand >> orderCommand
		 OrderCommand 이게 modelAttribute 안하면
		 자동으로 
		 OrderCommand의 첫 글자 소문자로 바꿈 orderCommand로
		 mv.addObject("orderCommand", ordercommand);
		 mv.setViewname("order/OrderCommitted");
		 
		 */
		return "order/OrderCommitted";
	}
}
