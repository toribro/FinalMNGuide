<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅목록</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/chat/chat.css"/>
</head>
 <%@ include file="../common/header.jsp"%>
<body>


            <div class="wrapper chat-wrapper">
                <div class="main chat-main">
                    <div class="chat-grid chat-list-area" onclick="location.href='http://localhost:3000?prompt=소심함'">반려견 행동성향에 관한 AI 챗봇상담(클릭)</div><br>

                    <div class="chat-list">
                        <div class="title t">채팅방목록</div>
                        <div class="over-flow">
            
                            <c:forEach var="chatList" items="${locationInfo}">
                                
                                <div  class="chat-grid chat-list-area" onclick="location.href='chatPage.cp?locationNo=${chatList.locationNo}'" style="cursor: pointer;">
                                    <div class="profile-area">
                                        <div class="img-div">
                                            <img src="resources/img/default/default_profile.jpg">
                                        </div>
                                        <div class="profile-list">
                                           <div class="title">${chatList.locationName} &nbsp; 사장님 </div>
                                        </div>
                                    </div>
                                    
                                    <div class="notice">
                                    
                                    </div>
                                </div>
            
                            </c:forEach>
                    </div>
                </div>

    </div>
      
       
</body>
</html>