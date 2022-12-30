<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<div id="header">
			<div class="top-wrapper">
				<h1 id="logo"><a href="/"><img src="" alt="로고" /></a></h1>
				<h2 class="hidden">메인메뉴</h2>
				<ul id="mainmenu" class="block_hlist">
					<li>
						<a href="">About us</a>
					</li>
					<li>
						<a href="" >Product</a>
					</li>
					<li>
						<a href="" >Product</a>
					</li>
				</ul>
				<form id="searchform" action="" method="get">
					<fieldset>
						<legend class="hidden">
							과정검색폼
						</legend>
						<label for="query">과정검색</label>
						<input type="text" name="query" />
						<input type="submit" class="button" value="검색" />
					</fieldset>
				</form>
				<h3 class="hidden">로그인메뉴</h3>
				<ul id="loginmenu" class="block_hlist">
					<li>
						<a href="${pageContext.request.contextPath}/index.htm">HOME</a>
					</li>
					<!-- Spring security tags lib 사용 -->
					<%-- <se:authorize access="!hasRole('ROLE_USER')">
						 <li><a href="${pageContext.request.contextPath}/member/login.htm">로그인</a></li>
					</se:authorize> --%>
					
					
					<!-- 인증이 성공하면 Spring  내부적으로 객체 생성 : userPrincipal 객체가 가지는 속성 값 : name -->
					<se:authentication property="name" var="loginuser" />
					<!-- Spring security tags lib 사용 -->
					<se:authorize access="!hasRole('ROLE_USER')">
						 <li><a href="${pageContext.request.contextPath}/member/login.htm">로그인</a></li>
						 <li><a href="${pageContext.request.contextPath}/member/join.htm">회원가입</a></li>
					</se:authorize>
					<se:authorize access="hasAnyRole('ROLE_USER')">	<!-- if문 처럼 사용 -->
						<li><a href="${pageContext.request.contextPath}/logout">(${loginuser })로그아웃</a></li>
					</se:authorize>
					
					 <li><a href="${pageContext.request.contextPath}/member/login.htm">로그인</a></li>
				</ul>
				<h3 class="hidden">회원메뉴</h3>
				<ul id="membermenu" class="clear">
					<li>
						<div class="Button">
							<a href="${pageContext.request.contextPath}/member/mypage.htm">
							<input class="w-btn w-btn-gra1" type="button" value="My Lovely Page" id="ajaxBtn">
							</a>
						</div>
					</li>
					<li>
						<div class="Button">
							<a href="${pageContext.request.contextPath}/board/boardList.htm">
							<input class="w-btn w-btn-gra1" type="button" value="Board List" id="ajaxBtn">
							</a>
						</div>
					</li>
				</ul>
			</div>
</div>