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
<link rel="stylesheet" href="resources/css/member/memberEnrollAdmin.css"/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/js/member/memberInit.js"></script>
<script src="resources/js/member/memberEnroll.js"></script>
<script src="resources/js/member/memberAjax.js"></script>

</head>
<body onload="init('<%=request.getContextPath()%>', 'Y')">
	<%@ include file="../common/header.jsp"%>
	
    <div class="wrapper">
		<div class="minibox-wrap">
            <!-- if문으로 아이디 일 때, 비밀번호 일 때 구분해서 타이틀 넣을 것 -->
            <div class="minibox-title">회원가입</div>
            <div class="minibox-text">
                환영합니다 사장님 <br>
                멍냥가이드와 함께 승승장구 하세요
            </div>

            <form action="<%=contextPath%>/memberEnrollBoss.me" method="POST">
                <div class="minibox-mini-title minibox-mini-title-bold">본인 인증</div>
                
                <div class="minibox-mini-title">
                    <span>이름(대표자명)</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <input type="text" name="userName" class="minibox-input" onkeyup="activeBossEnroll()" placeholder="이름을 입력하세요.">

                <div class="minibox-mini-title">
                    <span>생년월일</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <input type="date" name="userBirthday" class="minibox-input" onkeyup="activeBossEnroll()" placeholder="0000-00-00">
                
                <div class="select-button">
                    <div id="select-f" class="flex-box" onclick="selectGender('F'), activeBossEnroll()">여자</div>
                    <div id="select-m" class="flex-box" onclick="selectGender('M'), activeBossEnroll()">남자</div>
                </div>

                <input type="radio" name="userGender" id="radio-f" value="F" >
				<input type="radio" name="userGender" id="radio-m" value="M">

                <input type="text" name="userKind" id="radio-kind" value="Y">

                <div class="minibox-mini-title">
                    <span>휴대폰번호 인증</span>
                    <span class="required-color">*</span>
                    <span id="phone-message"></span>
                </div>
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

                    <div id="after-certify-box">
                        <div id="check-agree-title" class="minibox-mini-title">
                            <span>본인 인증 약관 동의</span>
                            <span class="required-color">*</span>
                            <!-- <span class="error-message-margin">체크되지 않은 필수 항목이 있습니다.</span> -->
                        </div>
                        <div class="check-agree-box">
                            <input type="checkbox" id="check-agree-all" class="checkbox-color-pink check-agree" onchange="clickAllAgree(), activeBossEnroll(), activeCertifyPhone()">
                            <label for="check-agree-all">전체 동의합니다.</label>
                        </div>
                        <hr>
                        <div class="check-agree-box check-box">
                            <input type="checkbox" id="check-agree-first" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeBossEnroll(), activeCertifyPhone()">
                            <label for="check-agree-first">[필수] 본인 확인 서비스 이용 약관 동의</label>
                        </div>
                        <div class="check-agree-box check-box">
                            <input type="checkbox" id="check-agree-second" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeBossEnroll(), activeCertifyPhone()">
                            <label for="check-agree-second">[필수] 통신사 이용 약관 동의</label>
                        </div>
                        <div class="check-agree-box check-box">
                            <input type="checkbox" id="check-agree-third" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeBossEnroll(), activeCertifyPhone()">
                            <label for="check-agree-third">[필수] 통신사/인증사의 개인정보 이용, 제공 동의</label>
                        </div>
                        <div class="check-agree-box check-box">
                            <input type="checkbox" id="check-agree-fourth" class="checkbox-color-pink check-agree" onchange="clickAgree(), activeBossEnroll(), activeCertifyPhone()">
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
                    </div>

                <div id="checked-phone-text" class="common-button" style="display: none;">휴대폰번호 인증이 완료되었습니다.</div>


                <div class="minibox-mini-title minibox-mini-title-bold">사업자 정보 <!--인증--></div>
                
                <!-- <form action=""> -->
                    <div class="minibox-mini-title">
                        <span>사업자등록번호</span>
                        <span class="required-color">*</span>
                        <span class="error-message-margin"></span>
                    </div>
                    <input type="text" name="businessNo" class="minibox-input" placeholder="000-00-00000">

                    <div class="minibox-mini-title">
                        <span>상호명</span>
                        <span class="required-color">*</span>
                        <span class="error-message-margin"></span>
                    </div>
                    <input type="text" name="locationName" class="minibox-input" placeholder="상호명을 입력하세요.">

                    <div class="minibox-mini-title">
                        <span>소재지</span>
                        <span class="required-color">*</span>
                        <span class="error-message-margin"></span>
                    </div>

                    <div id="check-boss-info">
                        <input type="text" id="address-zipCode" class="minibox-input" placeholder="우편번호 입력">
                        <button type="button" class="common-button white-button" onclick="showAddress()">우편번호 검색</button>
                    </div>
                    <input type="text" name="address" id="address-content" class="minibox-input" onkeyup="changeAddress()" placeholder="">
                    <!-- <input type="text" id="address-detail" class="minibox-input" onkeyup="changeAddress()" placeholder="상세 주소를 입력하세요."> -->

                    <!-- <input type="text" name="address" id="input-address"> -->

                    <!-- <button type="submit" id="check-admin-button" class="common-button pink-button minibox-full-button">사업자 정보 인증</button> -->
                <!-- </form> -->

                <!-- <div id="checked-boss-info" class="common-button">사업자 정보 인증이 완료되었습니다.</div> -->


                <div class="minibox-mini-title minibox-mini-title-bold">사용자 정보 입력</div>
                
                <div class="minibox-mini-title">
                    <span>아이디</span>
                    <span class="required-color">*</span>
                    <span id="id-message" class="error-message-margin"></span>
                </div>
                <div id="check-boss-info">
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
                    <span>이메일</span>
                    <span class="required-color">*</span>
                    <!-- <span class="error-message-margin">필수 항목입니다.</span> -->
                </div>
                <div id="email-enroll">
                    <input type="text" id="email-pre" class="minibox-input" onkeyup="changeEmail(), activeBossEnroll()" placeholder="이메일 입력">
                    <span>@</span>
                    <input type="text" id="email-post" class="minibox-input" onkeyup="changeEmail(), activeBossEnroll()" placeholder="직접입력">
                    <select id="email-select-box" class="minibox-input" onchange="selectPostEmail(), activeBossEnroll()">
                        <!-- selected 하면 기본값으로 지정 -->
                        <option value="직접입력" selected>직접입력</option>
                        <option value="naver.com">naver.com</option>
                        <option value="google.com">google.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="daum.com">daum.com</option>
                    </select>
                </div>

                <input type="text" id="input-email" name="userEmail">

                <div class="minibox-mini-title">
                    <span>운영업체 카테고리</span>
                    <span class="required-color">*</span>
                </div>
                <select name="locationCategoryNo" class="minibox-input">
                    <!-- 직접입력 선택 시 js로 input 창으로 바뀌도록 -->
                    <option value="2">식당</option>
                    <option value="3">카페</option>
                    <option value="4">숙소</option>
                    <option value="5">행사</option>
                    <option value="6">테마파크</option>
                    <option value="7">병원</option>
                    <option value="1">기타</option>
                </select>

                <button type="submit" id="enroll-button" class="common-button minibox-full-button" disabled>회원가입</button>
            </form>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>