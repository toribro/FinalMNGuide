<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/chat/chat.css"/>
<script src='resources/js/chat/chat.js'></script>
</head>
	<%@ include file="../common/header.jsp"%>
<body onload="init('${contextPath}','${master}')">
   
   	
	<div class="wrapper chat-wrapper">
		<div class="main chat-main">
			
			<div class="chat-box">

               <!--유저가 사장님이면-->
			 <c:if test="${!master.equals('NNNNN')}">
				<input id="master-No" type="text" value="${loginUser.userNo}" hidden>
				<div class="chat-list">
					<div class="title t">채팅문의</div>
					<div class="over-flow">

                        <c:forEach var="userList" items="${chatUserList}">
                            
							<div  id="chatRoom${userList.userNo}" class="chat-grid chat-list-area" onclick="chooseChatRoom(1,'${userList.userNo}')">
								<div class="profile-area">
									<div class="img-div">
										<img src="resources/img/default/default_profile.jpg">
									</div>
									<div class="profile-list">
										<input id="userId${userList.userNo}" type="text"  value="${userList.userId}" hidden>
										<input id="userNo${userList.userNo}" type="text"  value="${userList.userNo}" hidden>
										<div id="userNickName${userList.userNo}" class="title">${userList.userNickName}</div>
										<div id="content${userList.userNo}">${userList.lastestMessage.message}</div>
									</div>
								</div>
								
								<!--실시간 처리-->
								<div id="notice${userList.userNo}" class="notice">
									<div id=date${userList.userNo}  class="date">${userList.lastestMessage.enroll_time}</div>
									<c:if test="${userList.messageCount != 0}">
										<div id="notifyCount${userList.userNo}" class="notify">${userList.messageCount}</div>
									</c:if>
								</div>
							</div>

						</c:forEach>
					
					</div>
				</div>
			  </c:if>

			  <c:if test="${master.equals('NNNNN')}">
			  	  <input id="masterId" type="text" value="${masterInfo.masterId}" hidden>
				  <input id="masterNo" type="text" value="${masterInfo.masterNo}" hidden>
				  <input id="roomNo" type="text" value="2" hidden>
			  </c:if>

			  



				<!--채팅영역-->
				<div class="chat-content">
					<!--사장일때-->

					<c:if test="${!master.equals('NNNNN')}">
						<input  id="userKey" type="text" hidden>
				   </c:if>
					<!--유저일때-->
					<c:if test="${master.equals('NNNNN')}">
						<input  id="userKey" type="text" value="${masterInfo.masterNo}" hidden>
				   </c:if>

					<div class="profile-box">
						<div class="img-div">
							<img src="resources/img/default/default_profile.jpg">
						</div>
						<div id="chattingUser" class="title"></div>
						<c:if test="${master.equals('NNNNN')}">
							<div style="width:350px ;">&nbsp;&nbsp;</div>
							<div style="float:right">
								<a onclick="location.href='${contextPath}/userchat.view'" style="cursor: pointer;">채팅목록으로</a>&nbsp;&nbsp;
								<a onclick="moveToLocation()" style="cursor: pointer;">장소로이동</a>
							</div>
						</c:if>
					</div>

					<!--#########-->
					<!--채팅내용 (비동기처리)-->
					<div  id="chatMsg" class="chat-chatting">
						
						<c:forEach var="chat" items="${chats}">

						<c:choose>
							<c:when test="${chat.userNo eq masterInfo.masterNo}">
								<!--사장님-->
								<div class="send-master">
									<div class="master-profile">
										<div class="img-div">
											<img src="resources/img/default/default_profile.jpg">
										</div>
										<div class="master-name title">사장님</div>
									</div>
									<div  class="content master-content master-color">
										<div>${chat.message}</div>
										<div class="time">${chat.enrollTime}</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>

								<!--유저-->
								<div class="send-user">
									<div class="content  user-content user-color">
										<div>${chat.message}</div>
									<div class="time">${chat.enrollTime}</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						  
					</c:forEach>
					</div>



					<!--#########-->



					<!--채팅 입력창-->
					<div class="input-chatting">
						<div class="img">
							<label for="file" class="img-button">
								<img src="resources/img/img-icon.png">
							</label>
							<input id="file" type="file" style="display:none;">
						</div>
						<div class="input">
							<input  name="msg" type="text">
						</div>
						<div class="submit">
							<button id="send-button" type="button">
								<img src="resources/img/send-img.png">
							</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
   	 
	<%@ include file="../common/footer.jsp"%>
  
</body>

</html>