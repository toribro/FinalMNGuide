<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@ include file="../common/common-file.jsp" %>
    
    <link rel="stylesheet" href="resources/css/community/community.css"/>
    <script src="resources/js/shortsShow/shortsShow.js"></script>
    <script src="resources/js/shortsShow/ajax/shortsShowAjax.js"></script>
</head>


<body>
    <div class="headerbar">
        <%@ include file="../common/header.jsp"%>
    </div>
    <input type="hidden" id="contextPath" value="${contextPath}" />
    <input type="hidden" id="userNo" value="${empty loginUser.userNo ? 0 : loginUser.userNo}" />

    <div class="wrapper">
        <div class="main">
            <div style="height: 100px;"></div>
            <div id="container"></div>
            <div id="loader">Loading...</div>
        </div>
        <img src="" id="like-btn">
    </div>
</body>
</html>
