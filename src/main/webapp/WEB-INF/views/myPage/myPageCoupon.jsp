<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <%@ include file="../common/common-file.jsp" %>

        <link rel="stylesheet" href="resources/css/myPage/myPageCoupon.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="resources/js/myPage/myPageCoupon.js"></script>
    </head>

    <body>

        <%@ include file="../common/header.jsp" %>

            <div class="wrapper main_wrapper">
                <div class="main_main">
                    <div id="left">
                        <div id="main_main_left">
                            <div id="main_main_left1">
                                <img src="resources/img/myPage/camera.png"
                                    onclick="document.getElementById('fileInput').click()" id="camera">
                                <div id="profile" style="background-image: url(${loginUser.userProfile.filePath}${loginUser.userProfile.changeName}); background-position: center; background-repeat: no-repeat; background-size: cover;">
                                    <input type="file" id="fileInput" style="display: none;"
                                        onchange="uploadProfileImage('<%=request.getContextPath()%>')">
                                </div>
                                <input type="file" id="fileInput" style="display: none;">
                                <p id="nickName" style="margin-bottom: 10px;">${loginUser.userNickname}</p>
                                <div id="solid"></div>
                                <p id="pets">반려동물</p>
                                <c:forEach var="pet" items="${petList}" varStatus="loop">
                                    <span id="petProfileName"
                                        style="text-align: center; overflow: initial; white-space: initial;">
                                        ${pet.petName}<c:if test="${not loop.last}">&nbsp;&</c:if>
                                    </span>
                                </c:forEach>
                            </div>
                            <div id="main_main_left2">
                                <div id="mainList"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageMain.mp'">작성한 리뷰
                                </div>
                                <div id="wishList"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageWish.mp'">공감 목록
                                </div>
                                <div id="coupon"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageCoupon.mp'">쿠폰 목록
                                </div>
                                <div id="boardList"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageBoard.mp'">게시글 / 쇼츠 목록
                                </div>
                                <div id="info" onclick="location.href='<%=request.getContextPath()%>/myPageInfo.mp'">
                                    개인정보
                                </div>
                                <div style="border-style: none;" id="petInfo"
                                    onclick="location.href='<%=request.getContextPath()%>/myPagePetInfo.mp'">반려동물 정보
                                </div>
                            </div>
                            <div id="main_main_left3">
                                <div id="chatting" onclick="location.href='userchat.view'" style="cursor: pointer;">채팅 목록</div>
                            </div>
                        </div>
                    </div>
                    <div id="right">
                        <div id="main_main_right">
                            <div id="right1">쿠폰 목록</div>
                            <div id="right2">
                                <div id="right2-left">
                                    <div id="top">AAA카페 송파점</div>
                                    <div id="mid">10000원 할인</div>
                                    <div id="bottom">2024.05.10 ~ 2024.11.02</div>
                                </div>
                                <div id="right2-right">
                                    <button id="use">사용하기</button>
                                </div>
                            </div>
                            <div id="right3" style="margin-bottom: 30px;">
                                <div id="right3-left">
                                    <div id="top">AAA카페 송파점</div>
                                    <div id="mid">10000원 할인</div>
                                    <div id="bottom">2024.05.10 ~ 2024.11.02</div>
                                </div>
                                <div id="right3-right">
                                    <button id="none">사용하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>