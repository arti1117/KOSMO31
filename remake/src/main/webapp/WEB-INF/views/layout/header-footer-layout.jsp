<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">

<title><decorator:title default="JAVA 일본 취업 3기" /></title>

<!-- 공용 CSS : 항상 insert 되는 애 -->
<link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="/resources/css/style(sub).css">
<link rel="stylesheet" type="text/css" href="/resources/css/reserveMvButton.css">

<!-- 공용 JS : 항상 insert 되는 애 -->
<script type="text/javascript" src="/resources/js/common/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/js/common/angular/angular.js"></script>
<script type="text/javascript" src="/resources/js/common/common.js"></script>

<decorator:head />

</head>
<body>

	<!--헤더 -->
	<header>
		<div class="head">
			<c:choose>
				<c:when test="${sessionScope.loginBean.memberId == 'admin'}">
					<h1 class="logo">
						<a href="/web/admin/adminIndex"><img src="images/logo.jpg"
							alt="로고" /></a>
					</h1>
				</c:when>
				<c:otherwise>
					<h1 class="logo">
						<a href="/web/"><img src="images/logo.jpg" alt="로고" /></a>
					</h1>
				</c:otherwise>
			</c:choose>

			<div class="menu_box">
				<ul class="menu">
					<li><a href="/web/">현재상영작</a>
				</ul>
			</div>

		</div>

	</header>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!--헤더끝 -->
		<div class="sub_menu">

			<c:choose>

					<c:when test="${sessionScope.loginBean.memberId != null}">
						
						<c:if test="${sessionScope.loginBean.memberId == 'admin'}">
						<ul>
						<li><h3>관리자 전용 페이지</h3></li>
						<li style="padding:5px"><input type=button value="추가" onclick="moveTo('add')">&nbsp;&nbsp;
						<input type=button value="수정" onclick="moveTo('upd')">&nbsp;&nbsp;
						<input type=button value="삭제" onclick="moveTo('del')"></li>
						<li style="padding:5px"><input type=button value="회원목록" onclick="moveTo('sel')">&nbsp;
						<input type=button value="로그아웃" onclick="moveTo('out')">
						</li>
						<li ><a href="/web" style="color:blue">첫 화면으로</a></li>		
						</ul>
						</c:if>
						<c:if test="${sessionScope.loginBean.memberId != 'admin'}">
						<ul>
						<li><h4>${sessionScope.loginBean.name}님 환영합니다!</h4></li>
						<li><input type="button" value="예약정보" style="cursor:pointer" onclick=location.replace("/member/selectMember.do")>&nbsp;
						<input type="button" value="로그아웃" style="cursor:pointer" onclick=location.replace("/member/logout.do")>
						</li>
						<li><a href="/web/" style="color:gray">첫 화면으로</a></li>			
						</ul>
						</c:if>
					</c:when>
					<c:otherwise>
							<ul>
							<li><h3>로그인</h3></li>
						<form action="/web/member/loginProc" method="POST">
							<li>ID : <input type="text" name="memberId"></li> 
							<li>PW : <input type="password" name="password"></li> 
							<li style='line-height:5'><input type="submit"value="로그인" style="cursor: pointer">
							<input type="button"value="회원가입" style="cursor: pointer" onclick="moveTo('join')"></li>
							
						</form>
				</ul>
		
					</c:otherwise>
				</c:choose>
			<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>	
		</div>


		

		<decorator:body />


		<hr />
		<div class="grid">
			<ul>
				<li>소재지 : 서울특별시 금천구 가산 디지털단지 어딘가 / 대표자 : 윤승렬 / 대표전화 : 0000-0000</li>
				<li>사업자등록번호 : 금천-가-000-0000 / 통신판매업신고 : 금천-허-00000 / 이메일 :
					abc@gmail.com</li>
				<li>Copyright 2017. All right reserved.</li>
			</ul>
		</div>
</body>
</html>