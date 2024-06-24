<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <%@ include file="../common/common-file.jsp" %>

        <link rel="stylesheet" href="resources/css/myPage/myPageWishList.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="resources/js/myPage/myPageWishList.js"></script>
    </head>

    <body>

        <%@ include file="../common/header.jsp" %>

            <div class="wrapper main_wrapper">
                <div class="main_main">
                    <div id="left">
                        <div id="main_main_left">
                            <div id="main_main_left1">
                                <img src="resources/img/myPage/camera.png"
                                    onclick="document.getElementById('fileInput').click()" id="camera" style="object-fit: cover;">
                                <div id="profile"
                                    style="background-image: url(${loginUser.userProfile.filePath}${loginUser.userProfile.changeName}); 
                                    background-position: center; background-repeat: no-repeat; background-size: cover; object-fit: cover;">
                                    <input type="file" id="fileInput" style="display: none;"
                                        onchange="uploadProfileImage('<%=request.getContextPath()%>')">
                                </div>
                                <p id="nickName" style="margin-bottom: 10px;">${loginUser.userNickname}</p>
                                <div id="solidMain"></div>
                                <p id="pets">반려동물</p>
                                <c:forEach var="pet" items="${petList}" varStatus="loop">
                                    <span id="petProfileName"
                                        style="text-align: center; overflow: initial; white-space: initial;">
                                        ${pet.petName}<c:if test="${not loop.last}">&nbsp;&</c:if>
                                    </span>
                                </c:forEach>
                            </div>
                            <div id="main_main_left2">
                                <div id="main" onclick="location.href='<%=request.getContextPath()%>/myPageMain.mp'">작성한
                                    리뷰
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
                            <div id="right1">공감 목록</div>
                            <c:forEach var="wish" items="${wishList}" varStatus="loop">
                                <div id="right2">
                                    <div id="right2-left" onclick="location.href='<%=request.getContextPath()%>/detail'"
                                        style="cursor: pointer;">
                                        <img src="${wish.locationImg.filePath}${wish.locationImg.changeName}" id="cafe" style="object-fit: cover;">
                                    </div>
                                    <div id="right2-right" style="height: 80%;">
                                        <div id="right2-right1">
                                            <div id="top" style="justify-content: space-between; margin-right: 30px;">
                                                <span id="title"
                                                    onclick="location.href='<%=request.getContextPath()%>/detail'"
                                                    style="cursor: pointer;">${wish.locationName}</span>
                                                <div>
                                                    <div id="wishIcon-${loop.index}" class="wishIcon"
                                                        onclick="wishDelete('${loginUser.userNo}', '${wish.locationNo}', '<%=request.getContextPath()%>')"
                                                        style="display: flex; color: #FE8B94;">♥</div>
                                                    <div id="count" style="display: flex;">${wish.pickCount}</div>
                                                </div>
                                            </div>
                                            <div id="category"
                                                onclick="location.href='<%=request.getContextPath()%>/detail'"
                                                style="cursor: pointer;">
                                                <span id="category1">분류</span>
                                                <span id="category2">${wish.locationCategoryNo}</span>
                                            </div>
                                            <div id="score"
                                                onclick="location.href='<%=request.getContextPath()%>/detail'"
                                                style="cursor: pointer;">
                                                <span id="score1">평점</span>
                                                <span id="score2">${wish.locationStar}</span>
                                                <span id="score3">
                                                    <c:forEach var="i" begin="1" end="${wish.locationStar}">
                                                        ★
                                                    </c:forEach>
                                            </div>
                                            <div id="type"
                                                onclick="location.href='<%=request.getContextPath()%>/detail'"
                                                style="cursor: pointer;">
                                                <span id="type1">종류</span>
                                                <span id="type2">
                                                    <c:forEach var="enter" items="${wish.getEnterList}">
                                                        ${enter.petSizeName}
                                                    </c:forEach>
                                                </span>
                                            </div>
                                            <div id="solid">
                                                <div id="solid2"></div>
                                            </div>
                                        </div>
                                        <div id="right2-right2"
                                            onclick="location.href='<%=request.getContextPath()%>/detail'"
                                            style="cursor: pointer;">
                                            <div id="address">
                                                <img src="resources/img/myPage/address.png" id="address1">
                                                <span id="address2">${wish.address}</span>
                                            </div>
                                            <div id="phone">
                                                <img src="resources/img/myPage/phone.png" id="phone1">
                                                <span id="phone2">${wish.locationPhone}</span>
                                            </div>
                                            <div id="time">
                                                <img src="resources/img/myPage/time.png" id="time1">
                                                <span id="time2">영업 중</span>
                                                <span id="time3">22:00</span>
                                                <span id="time4">종료</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div id="container"></div>
                            <div id="right7">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>