<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>Insert title here</title>

	<c:if test="${sessionScope.loginBean.memberId == 'admin'}">
		<script>location.href = "/web/admin/adminIndex";</script>
	</c:if>

<script type="text/javascript">
		var pagingBean;
		
		//리스트 출력
		function showList(pageNo) {
			
			var url = "/web/movie/selectMovieList";
			var params = {};
			
			params.pageNo = pageNo;
			if(pageNo == null || pageNo == '') {
				params.pageNo = 1;
			}
			
			$.ajax({      
		        type: "POST",  
		        url: url,      
		        data: params,
		        success: function(data){   
		           console.log(data);
		           		           
		           if(data.result == 'ok') {
					
		        	   $("#movieInfoList").empty();
		        	   $("#pageList").empty();
		        	   
		        	   pagingBean = data.pagingBean;
		        	   
		        	   for(var i=0; i<data.movieList.length; i++) {
		        		   
		        		   var bean = data.movieList[i];
		        		   
		        		   var str = 
		        			   '<tr>' 
					   			+ '<td align="center" style="cursor:pointer" onclick="selectMovie(\'' + bean.code + '\')">' + '<img src=' + bean.path + ' width=200px height=300px>' + '</td>'
					   			+ '<td align="center" style="cursor:pointer" onclick="selectMovie(\'' + bean.code + '\')">' + bean.titleKor + '</td>'
					   			+ '<td align="center" style="cursor:pointer" onclick="selectMovie(\'' + bean.code + '\')">' + bean.hall + '</td>'
					   			+ '<td align="center" style="cursor:pointer" onclick="selectMovie(\'' + bean.code + '\')">' + (bean.totSeat-bean.revSeat) + '</td>'
					   			+ '<td align="center"style="cursor:pointer" onclick="selectMovie(\'' + bean.code + '\')">' 
				   					+ bean.opTimeYear + '년 ' + bean.opTimeMonth + '월 ' + bean.opTimeDay + '일 ' 
				   					+ bean.opTimeHh + '시 ' + bean.opTimeMi + '분 ' + '</td>'
			   				+ '</tr>';
			   				
			   				$("#movieInfoList").append( str );
			   				
		        	   }//end for
		        	   
		        	   
		        	   //페이징 출력
		        	   var pBean = data.pagingBean;
		        	   var str = "";
		        	   
		        	   //그룹번호 달아주기
	        		   if(pBean.groupNo > 1 ) {
	        			   str += " [<span onclick='showList(" 
	        				   + (pBean.pageStartNo-1) + ")' style='cursor:pointer'>이전</span>] ";   
	        		   }
		        	   
		        	   for(var i=pBean.pageStartNo; i<=pBean.pageEndNo; i++) {
		        		   if(i == pBean.pageNo) {
		        			   str += "[<span>" + i + "</span>] &nbsp;";   
		        		   } else {
		        			   str += '[<span onclick="showList(' 
		        				   	+ i + ')" style="cursor:pointer; color:blue;">' + i + '</span>] &nbsp;';   
		        		   }
		        	   }
		        	   
		        	   //그룹번호 달아주기
	        		   if(pBean.groupNo < pBean.totalGroupCount ) {
	        			   str += " [<span onclick='showList(" 
	        				   + (pBean.pageEndNo+1) + ")' style='cursor:pointer'>다음</span>] ";   
	        		   }
	        		   
		        	   
		        	   $("#pageList").append(str);

		           }//end if
		        
		        },   
		        error:function(e){  
		        	console.log(e);  
		        }  
		   	});
			
		}

		$(function() {
			window.onbeforeunload = function(){

	      		alert("111");

	    	};
			
			showList('${param.pageNo}');
		});
		
		// 페이지 이동 
		function moveTo(i){
			// admin 아이디로 회원가입시 전체목록 출력	
			if(i == 'selectMemberList'){
				location.replace("/web/member/selectMemberList");
			}
			// 본인정보만 출력
			else if(i == 'selectMember'){
				location.replace("/web/member/selectMember");
			}
			else if(i == 'join'){
				location.replace("/web/member/joinMember");
			}
			// 로그아웃
			else if(i == 'logout'){
				location.replace("/web/member/logout");
			}
			else if(i == 'adminIndex'){
				location.replace("/web/admin/adminIndex");
			}
		}
		
		function selectMovie(code){
			location.replace("/web/movie/selectMovie?code=" + code);
		}
		
	</script>

</head>
<body>
	<div align='center'><h2>「현재 상영작」<br></h2>
	<h4 style="color:gray">지금 제일 잘 나가는 영화들을 모아봤습니다</h4></div><br><br>

				<table border="1" align="center" cellspacing="0" cellpadding="0" width="800px">
					<tr>
						<th>이미지</th>
						<th>제목</th>
						<th>상영관</th>
						<th>잔여좌석</th>
						<th>상영시간</th>
					</tr>
						<tbody id="movieInfoList"></tbody>
				</table>


	</body>
</html>
