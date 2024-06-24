<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/common/minibox.css"/>
<link rel="stylesheet" href="resources/css/member/memberSearch.css"/>
<script src="resources/js/member/memberInit.js"></script>
<script src="resources/js/member/memberSearch.js"></script>
<script src="resources/js/member/memberAjax.js"></script>
</head>
<body onload="init('${contextPath}', '0')">
	<%@ include file="../common/header.jsp"%>
	
    <div class="wrapper">
		<div class="minibox-wrap">
            <!-- if문으로 아이디 일 때, 비밀번호 일 때 구분해서 타이틀 넣을 것 -->
            
            <c:choose>
                <c:when test="${type eq 1}">
                    <div class="minibox-title">아이디 찾기</div>
                </c:when>
                <c:otherwise>
                    <div class="minibox-title">비밀번호 찾기</div>
                </c:otherwise>
            </c:choose>

            <div class="minibox-title"></div>
            <div class="minibox-text">
                가입 시 사용한 전화번호를 입력하세요.
            </div>

            <c:choose>
                <c:when test="${type eq 1}">
                    <form action="">
                </c:when>
                <c:otherwise>
                    <form action="">
                </c:otherwise>
            </c:choose>
                <!-- <div class="minibox-mini-title">
                    <span>이름</span>
                    <span class="required-color">*</span>
                    <span class="error-message-margin">필수 항목입니다.</span>
                </div>
                <input type="text" class="minibox-input" placeholder="가입 시 사용한 이름을 입력하세요.">
                 -->
                    <div class="minibox-mini-title">
                        <span>휴대폰번호 인증</span>
                        <span class="required-color">*</span>
                        <span id="check-number-message" class="error-message-margin"></span>
                    </div>
                    <!-- <select name="" id="" class="minibox-input">
                        <option value="SKT" class="minibox-input">SKT</option>
                        <option value="KT" class="minibox-input">KT</option>
                        <option value="LGU+" class="minibox-input">LGU+</option>
                        <option value="알뜰폰" class="minibox-input">알뜰폰</option>
                    </select> -->
                    <input type="text" name="userPhone" class="minibox-input" placeholder="가입 시 사용한 전화번호를 입력하세요. (- 없이 입력)">

                    <div class="minibox-mini-title">
                        <span class="error-message-nomargin"></span>
                    </div>
                    <div id="check-number">
                        <input type="text" class="minibox-input" placeholder="인증번호 6자리 숫자">
                        <button type="button" class="common-button white-button" onclick="checkPhoneNumber()">인증번호 요청</button>
                        <!-- <div>2:00</div> -->
                    </div>
                    
                    <!-- <div id="plus-time">시간 연장</div> -->
                <button type="submit" id="enroll-button" class="common-button pink-button minibox-full-button" disabled>확인</button>
            </form>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>