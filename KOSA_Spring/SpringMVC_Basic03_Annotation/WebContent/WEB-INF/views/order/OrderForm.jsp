<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문화면</title>
</head>
<body>
<!-- 
private List<OrderItem> orderItem
add()

 List<OrderItem> itemlist = new ArrayList<>();
 itemlist.add(new OrderItem(1, 10, "파손주의"))
 itemlist.add(new OrderItem(10, 1, "리모컨은 별도 구매")) 이걸 하는 거다
 -->
	<form method="POST">
	상품_1<br>
	상품ID: <input type="text" name="orderItem[0].itemid">
	상품 수량:<input type="text" name="orderItem[0].number">
	상품 주의사항:<input type="text" name="orderItem[0].remark">
	<br>
	상품_2<br>
	상품ID: <input type="text" name="orderItem[1].itemid">
	상품 수량:<input type="text" name="orderItem[1].number">
	상품 주의사항:<input type="text" name="orderItem[1].remark">
	<br>
	상품_3<br>
	상품ID: <input type="text" name="orderItem[2].itemid">
	상품 수량:<input type="text" name="orderItem[2].number">
	상품 주의사항:<input type="text" name="orderItem[2].remark">
	<br>
	<hr>
	<input type="submit" value="SEND!">
	</form>
</body>
</html>