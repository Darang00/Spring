﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<jsp:include page="/WEB-INF/views/inc/visual.jsp" />
	
		<div id="main">
			<div class="top-wrapper clear">
				<div id="content">
					<h2>게시글</h2>
					<h3 class="hidden">방문페이지 로그</h3>
					<ul id="breadscrumb" class="block_hlist clear">
						<li>HOME</li>
						<li>
							고객센터
						</li>
						<li>
							게시글 목록
						</li>
					</ul>
					<h3 class="hidden">게시글 목록</h3>
					<form id="content-searchform" class="article-search-form" action="boardList.jsp" method="get">
						<fieldset>
							<legend class="hidden">
								목록 검색 폼
							</legend>
							<input type="hidden" name="pg" value="" />
							<label for="f"
							class="hidden">검색필드</label>
							<select name="f">
								<option value="TITLE">제목</option>
								<option value="CONTENT">내용</option>
							</select>
							<label class="hidden" for="q">검색어</label>
							<input type="text"
							name="q" value="" />
							<input type="submit" value="검색" />
						</fieldset>
					</form>
					<table class="article-list margin-small">
						<caption class="hidden">
							공지사항
						</caption>
						<thead>
							<tr>
								<th class="seq">번호</th>
								<th class="title">제목</th>
								<th class="writer">작성자</th>
								<th class="writedate">작성일</th>
								<th class="hit">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="board">
							<tr>
								<td class="seq">${board.seq}</td>
								<td class="title"><a href="boardDetail.htm?seq=${board.seq}">${board.title}</a></td>
								<td class="writer">${board.writer}</td>
								<td class="regdate">${board.writedate}</td>
								<td class="hit">${board.hit}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- Security 권한 설정 -->
					<p class="article-comment margin-small">
						<%-- <se:authorize access="hasRole('ROLE_USER') AND hasRole('ROLE_ADMIN')"> --%>
						<se:authorize access="hasRole('ROLE_USER')">
							<a class="btn-write button" href="boardWrite.htm">글쓰기</a>
						</se:authorize>	
					</p>
					
					<p id="cur-page" class="margin-small">
						<span class="strong">1</span> /
						10	page
					</p>
					<div id="pager-wrapper" class="margin-small">
						<div class="pager clear">
							<p id="btnPrev">
								<a class="button btn-prev"
								href="boardList.jsp">이전</a>
							</p>
							<ul>
								<li>
									<a class="strong" href="">1</a>
								</li>
								<li>
									<a href="">2</a>
								</li>
								<li>
									<a href="">3</a>
								</li>
								<li>
									<a href="">4</a>
								</li>
								<li>
									<a href="">5</a>
								</li>
							</ul>
							<p id="btnNext">
								<span class="button btn-next">다음</span>
							</p>
						</div>
					</div>
				</div>
			<jsp:include page="../inc/aside.jsp" />
			</div>
		</div>
		
		<jsp:include page="../inc/footer.jsp" />
	</body>
</html>
