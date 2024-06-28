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
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	
    <div class="wrapper">
		<div class="minibox-wrap">
            <div class="minibox-title">로그인</div>
            <div class="minibox-text">
                로그인을 통해서 <br>
                반려동물과 행복한 동행을 시작하세요
            </div>

            <form action="<%=contextPath%>/login.me" method="POST" id="login-form">
                <c:choose>
                    <c:when test="${not empty cookie.saveId}">
                        <input type="text" name="userId" value="${cookie.saveId.value}" class="minibox-input" placeholder="아이디를 입력하세요">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="userId" class="minibox-input" placeholder="아이디를 입력하세요">
                    </c:otherwise>
                </c:choose>

                <input type="password" name="userPwd" class="minibox-input" placeholder="비밀번호를 입력하세요">

                <div>
                    <div>
                        <c:choose>
                            <c:when test="${not empty cookie.saveId}">
                                <input type="checkbox" name="saveId" id="save-id" class="checkbox-color-pink" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="saveId" id="save-id" class="checkbox-color-pink">
                            </c:otherwise>
                        </c:choose>

                        <label for="save-id">아이디 저장</label>
                    </div>
                    <div class="error-message-nomargin">${errorMsg}</div>
                </div>

                <button type="submit" class="common-button pink-button minibox-full-button">로그인</button>
            </form>

            <hr>

            <div id="search-id-password">
                <div onclick="location.href='<%=contextPath%>/searchMemberForm.mee?type=1'">아이디 찾기</div>
                <div onclick="location.href='<%=contextPath%>/searchMemberForm.mee?type=2'">비밀번호 찾기</div>
            </div>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>