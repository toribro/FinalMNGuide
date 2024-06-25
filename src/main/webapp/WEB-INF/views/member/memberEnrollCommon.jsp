<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/common/minibox.css"/>
<link rel="stylesheet" href="resources/css/member/memberEnroll.css"/>
<link rel="stylesheet" href="resources/css/member/memberEnrollCommon.css"/>
<script src="resources/js/member/memberInit.js"></script>
<script src="resources/js/member/memberEnroll.js"></script>
<script src="resources/js/member/memberAjax.js"></script>
</head>
<body onload="init('<%=request.getContextPath()%>', 'N')">
	<%@ include file="../common/header.jsp"%>
	
    <div class="wrapper">
		<div class="minibox-wrap">
            <!-- if문으로 아이디 일 때, 비밀번호 일 때 구분해서 타이틀 넣을 것 -->
            <div class="minibox-title">회원가입</div>
            <div class="minibox-text">
                회원가입을 통해서 <br>
                반려동물과 행복한 동행을 시작하세요.
            </div>

            <form action="<%=contextPath%>/memberEnrollCommon.me" method="POST">
                <div class="minibox-mini-title">
                    <span>휴대폰번호 인증</span>
                    <span class="required-color">*</span>
                    <span id="phone-message"></span>
                </div>
                <!-- 휴대폰번호 API 인증 필요 -->
                <!-- <form action=""> -->
                    <!-- <select name="" id="" class="minibox-input">
                        <option value="SKT" class="minibox-input">SKT</option>
                        <option value="KT" class="minibox-input">KT</option>
                        <option value="LGU+" class="minibox-input">LGU+</option>
                        <option value="알뜰폰" class="minibox-input">알뜰폰</option>
                    </select> -->
                <div class="double-check-box">
                    <input type="text" name="userPhone" class="minibox-input" data-check='false' onkeyup="checkDoublePhone()" placeholder="- 없이 입력하세요. (ex) 01012345678)">
                    <button type="button" id="phone-check" class="common-button white-button before-check-button" data-check='false' onclick="checkPhoneNumber()" disabled>중복확인</button>
                </div>

                    <!-- 
                        1. userPhone onkeyup 해서 형식에 맞는 번호면 중복 체크 활성화
                        2. 중복 체크 확인 되면 회원가입 버튼 활성화 할 데이터로 바꾸기
                        3. 중복 체크 확인 되고 약관 동의 누르면 인증 요청 버튼 활성화
                        5. 인증 되면 display 확인된 형식으로 바꾸기
                        6. 인증 되면 회원가입 가능한 데이터로 바꾸기
                        
                        4. 인증 요청하면 시간 체크해서 3분 지나면 데이터 삭제 ajax 실행하기
                    -->
                    
                    
                <div id="after-certify-box">
                    <div id="check-agree-title" class="minibox-mini-title">
                        <span>본인 인증 약관 동의</span>
                        <span class="required-color">*</span>
                        <!-- <span class="error-message-margin">체크되지 않은 필수 항목이 있습니다.</span> -->
                    </div>
                    <div class="check-agree-box">
                        <input type="checkbox" id="check-agree-all" class="checkbox-color-pink check-agree" onchange="clickAllAgree(), activeCommonEnroll(), activeCertifyPhone()">
                        <label for="check-agree-all">전체 동의합니다.</label>
                    </div>
                    <hr>
                    <div class="check-agree-box check-box">
                        <input type="checkbox" id="check-agree-first" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeCommonEnroll(), activeCertifyPhone()">
                        <label for="check-agree-first">[필수] 본인 확인 서비스 이용 약관 동의</label>
                    </div>
                    <div class="check-agree-box check-box">
                        <input type="checkbox" id="check-agree-second" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeCommonEnroll(), activeCertifyPhone()">
                        <label for="check-agree-second">[필수] 통신사 이용 약관 동의</label>
                    </div>
                    <div class="check-agree-box check-box">
                        <input type="checkbox" id="check-agree-third" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeCommonEnroll(), activeCertifyPhone()">
                        <label for="check-agree-third">[필수] 통신사/인증사의 개인정보 이용, 제공 동의</label>
                    </div>
                    <div class="check-agree-box check-box">
                        <input type="checkbox" id="check-agree-fourth" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeCommonEnroll(), activeCertifyPhone()">
                        <label for="check-agree-fourth">[필수] 고유식별 정보 처리 동의</label>
                    </div>

                    <br>
                    
                    <div class="minibox-mini-title">
                        <span id="certify-message" class="error-message-nomargin"></span>
                    </div>

                    <div id="check-number">
                        <input type="text" id="certify-code" class="minibox-input" placeholder="인증번호 6자리 숫자">
                        <button type="button" id="certify-phone-button" class="common-button white-button" onclick="getCertifyCode()" disabled>인증번호 요청</button>
                        <!-- <div>2:00</div> -->
                    </div>
                    
                    <!-- <div id="plus-time">시간 연장</div> -->
                    <button type="button" id="check-phone-button" class="common-button pink-button minibox-full-button" onclick="checkCertifyCode()">확인</button>
                <!-- </form> -->
                </div>

                <div id="checked-phone-text" class="common-button" style="display: none;">휴대폰번호 인증이 완료되었습니다.</div>

                <div class="minibox-mini-title">
                    <span>아이디</span>
                    <span class="required-color">*</span>
                    <span id="id-message" class="error-message-margin"></span>
                </div>
                <div id="check-user-info">
                    <input id="user-id" type="text" name="userId" class="minibox-input" data-check='false' onkeyup="checkId()" minlength="8" maxlength="12" placeholder="8~12자리의 영문, 숫자로 입력하세요.">
                    <button type="button" id="id-check" class="common-button white-button before-check-button" onclick="checkDoubleId()" disabled>중복확인</button>
                </div>
                
                <div class="minibox-mini-title">
                    <span>비밀번호</span>
                    <span class="required-color">*</span>
                    <span id="pwd-message" class="error-message-margin"></span>
                </div>
                <input type="password" name="userPwd" class="minibox-input" onkeyup="checkPwd()" minlength="8" maxlength="14" placeholder="8~14자리의 영문, 숫자로 입력하세요.">

                <div class="minibox-mini-title">
                    <span>비밀번호 확인</span>
                    <span class="required-color">*</span>
                    <span id="pwd-check-message" class="error-message-margin"></span>
                </div>
                <input type="password" id="pwd-check" class="minibox-input" onkeyup="checkPwdCorrect()" placeholder="8~14자리의 영문, 숫자로 입력하세요.">

                <div class="minibox-mini-title">
                    <span>이름</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <input type="text" name="userName" class="minibox-input" onkeyup="activeCommonEnroll()" placeholder="이름을 입력하세요.">

                <div class="minibox-mini-title">
                    <span>닉네임</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <input type="text" name="userNickname" class="minibox-input" onkeyup="activeCommonEnroll()" placeholder="사용할 닉네임을 입력하세요.">

                <div class="select-button">
                    <div id="select-f" class="flex-box" onclick="selectGender('F'), activeCommonEnroll()">여자</div>
                    <div id="select-m" class="flex-box" onclick="selectGender('M'), activeCommonEnroll()">남자</div>
                </div>

                <input type="radio" name="userGender" id="radio-f" value="F" >
				<input type="radio" name="userGender" id="radio-m" value="M">

                <input type="text" name="userKind" id="radio-kind" value="N">

                <div class="minibox-mini-title">
                    <span>생년월일</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <input type="date" name="userBirthday" class="minibox-input" onkeyup="activeCommonEnroll()" placeholder="0000-00-00">
                
                <div class="minibox-mini-title">
                    <span>이메일</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <div id="email-enroll">
                    <input type="text" id="email-pre" class="minibox-input" onkeyup="changeEmail(), activeCommonEnroll()" placeholder="이메일 입력">
                    <span>@</span>
                    <input type="text" id="email-post" class="minibox-input" onkeyup="changeEmail(), activeCommonEnroll()" placeholder="직접입력">
                    <select id="email-select-box" class="minibox-input" onchange="selectPostEmail(), activeCommonEnroll()">
                        <!-- selected 하면 기본값으로 지정 -->
                        <option value="직접입력" selected>직접입력</option>
                        <option value="naver.com">naver.com</option>
                        <option value="google.com">google.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="daum.com">daum.com</option>
                    </select>
                </div>

                <input type="text" id="input-email" name="userEmail">
                <button type="submit" id="enroll-button" class="common-button minibox-full-button" disabled>회원가입</button>
            </form>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>