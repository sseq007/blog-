<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form action="/blog/user?cmd=join" method="post" onsubmit="return vaild()">
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="usernameCheck()">중복체크</button>
		</div>

		<div class="form-group">
			<input type="text" name="username"  id="username" class="form-control" placeholder="Enter Username" required />
		</div>

		<div class="form-group">
			<input type="password" name="password" class="form-control" placeholder="Enter password" required />
		</div>

		<div class="form-group">
			<input type="email" name="email" class="form-control" placeholder="Enter Email" required />
		</div>
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="goPopup(); ">주소검색</button>
		</div>
		<input type="text" name="address" id="address" class="form-control" placeholder="Enter Address" required readonly /> <br />
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>

<script>

var isChecking = false;

function vaild(){
	if(isChecking = false){
		alert("아이디 중복체크를 확인하시오")
	}
	
	return isChekcing;
}

function usernameCheck(){
	
	var username = $("#username").val();
	console.log(username);
	
	$.ajax({
		type:"POST",
		url: "/blog/user?cmd=usernameCheck",
		data: username,
		contentType: "text/plain; charset=utf-8",
		dataType: "text"
	}).done(function(data){
		if(data === 'ok'){
			isChecking = false;
			alert("유저네임이 중복되었습니다")
		}else{
			isChecking=true;
			alert("유저네임을 사용할 수 있습니다")
		}
	});
}



function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/blog/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
		var addressEI=document.querySelector("#address");
		addressEI.value = roadFullAddr;
}

</script>
</body>
</html>