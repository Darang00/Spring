<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		이름:<input type="text" name="name"><br>
		나이:<input type="text" name="age"><br>
		사진:<input type="file" name="file"><br>
		<!-- name="file"이 dto에서 설정한 CommonMultipartFile 객체의 변수 이름 "file"과 같아야 mapping 된다 -->
		<input type="submit" value="파일업로드">
	</form>
</body>
</html>