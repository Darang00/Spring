<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
	<link href="../css/customer.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<jsp:include page="../inc/visual.jsp" />
		<div id="main">
			<div class="top-wrapper clear">
				<div id="content">
					<h2>공지사항</h2>
					<h3 class="hidden">방문페이지위치</h3>
					<ul id="breadscrumb" class="block_hlist">
						<li id="home">
							<a href="">HOME</a>
						</li>
						<li>
							<a href="">고객센터</a>
						</li>
						<li>
							<a href="">공지사항</a>
						</li>
					</ul>
					<div id="notice-article-detail" class="article-detail margin-large" >						
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								제목
							</dt>
							<dd class="article-detail-data">
								${board.title}
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								작성일
							</dt>
							<dd class="article-detail-data">
								${board.writedate}
							</dd>
						</dl>
						<dl class="article-detail-row half-row">
							<dt class="article-detail-title">
								작성자
							</dt>
							<dd class="article-detail-data half-data" >
								${board.writer}
							</dd>
						</dl>
						<dl class="article-detail-row half-row">
							<dt class="article-detail-title">
								조회수
							</dt>
							<dd class="article-detail-data half-data">
								${board.hit}
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								첨부파일_1
							</dt>
							<dd class="article-detail-data">
								<a href="download.htm?p=upload&f=${board.filesrc}">${board.filesrc}</a>
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								첨부파일_2
							</dt>
							<dd class="article-detail-data">
								<a href="download.htm?p=upload&f=${board.filesrc2}">${board.filesrc2}</a>
							</dd>
						</dl>
						<div class="article-content" >
							${board.content}
						</div>
					</div>
					<p class="article-comment margin-small">
						<a class="btn-list button" href="boardList.htm">목록</a>	
						<se:authentication property="name" var="userid" />
						<se:authorize access="hasRole('ROLE_USER')">
						<c:if test= "${userid eq board.writer}" >
						<a class="btn-edit button" href="boardUpdate.htm?seq=${board.seq}">수정</a>
						<a class="btn-del button" href="boardDelete.htm?seq=${board.seq}">삭제</a>
						</c:if>
						</se:authorize>
					</p>
				
					<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								▲ 다음글
							</dt>
							<dd class="article-detail-data">
								다음 글이 없습니다.
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								▼ 이전글
							</dt>
							<dd class="article-detail-data">
								1회 경진대회
							</dd>
						</dl>
					</div>					
				</div>				
				<jsp:include page="../inc/aside.jsp" />
			</div>
		</div>
		<jsp:include page="../inc/footer.jsp" />
	</body>
</html>
