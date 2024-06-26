<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/common/minibox.css"/>
<link rel="stylesheet" href="resources/css/member/memberLogin.css"/>
<script src="resources/js/member/memberEnroll.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	
    <div class="wrapper">
		<div class="minibox-wrap">
            <div class="minibox-title">비밀번호 재설정</div>
            <div class="minibox-text">
                가입된 아이디 : ${signUpId} <br>
                사용하실 비밀번호를 재설정 해주세요.
            </div>

            <form action="updatePwd.me" id="login-form" method="POST">
                <input type="text" name="signUpId" value="${signUpId}" style="display: none;">
                <div class="minibox-mini-title">
                    <span id="pwd-check-message" class="error-message-nomargin"></span>
                </div>
                
                <input type="password" name="userPwd" class="minibox-input" placeholder="비밀번호 입력">
                <input type="password" id="pwd-check" class="minibox-input" onkeyup="checkPwdCorrect()" placeholder="비밀번호 확인">

                <div class="minibox-mini-title">
                    <!-- <span class="error-message-nomargin">비밀번호 변경에 실패하였습니다. 다시 입력해주세요.</span> -->
                </div>
                
                <button type="submit" class="common-button pink-button minibox-full-button">확인</button>
            </form>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>