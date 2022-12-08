<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderCommitted</title>
</head>
<body>
	<h3>상품목록(EL & JSTL 사용 출력)</h3>
	${orderCommand} 안에 orderItem(ArrayList) 필요해요
	<hr>
	<ul>
		<c:forEach items="${orderCommand.orderItem }" var="orderItem">
		<!-- items에는 com.model의 orderCommand고, OrderCommand 객체(list)의 주소를 가지는 변수 orderItem 이랑 이름 똑같아야 한다  -->
			<li>
				${orderItem.itemid }-${orderItem.number}-${orderItem.remark}
				<!-- <li> 안에 있는 orderItem은 var에서 지정해준 이름이랑 맞춰야 한다  -->
			</li>
		</c:forEach>
	</ul>
</body>
</html>