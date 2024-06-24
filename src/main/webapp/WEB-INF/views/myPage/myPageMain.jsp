<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
            <%@ include file="../common/common-file.jsp" %>

                <link rel="stylesheet" href="resources/css/myPage/myPageMain.css" />
                <link rel="stylesheet" href="resources/css/common/common.css" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="resources/js/myPage/myPageMain.js"></script>
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
                                    <div id="profile"
                                        style="background-image: url(${loginUser.userProfile.filePath}${loginUser.userProfile.changeName}); 
                                            background-position: center; background-repeat: no-repeat; background-size: cover; object-fit: cover;">
                                        <input type="file" id="fileInput" style="display: none;"
                                            onchange="uploadProfileImage('<%=request.getContextPath()%>')">
                                    </div>
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
                                        onclick="location.href='<%=request.getContextPath()%>/myPageBoard.mp'">게시글 / 쇼츠
                                        목록
                                    </div>
                                    <div id="info"
                                        onclick="location.href='<%=request.getContextPath()%>/myPageInfo.mp'">
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
                                <div id="right1">작성한 리뷰</div>
                                <c:forEach var="review" items="${reviews}" varStatus="loop">
                                    <div id="right2">
                                        <div id="right2-top">
                                            <div id="right2-top1">
                                                <div><img src="${loginUser.userProfile.filePath}${loginUser.userProfile.changeName}" id="reviewProfile">
                                                </div>
                                                <div id="top1Text">
                                                    <p
                                                        style="margin-top: 20px; margin-bottom: 0px; font-size: 18px; font-weight: bold; text-align: left;">
                                                        ${review.locationName}</p>
                                                    <p style="margin-top: 0px; font-size: 13px; color: #bababa;">
                                                        ${review.modifyDate}</p>
                                                </div>
                                            </div>
                                            <div id="right2-top2">
                                                <span style="margin-right: 5px; color: #FE8B94; cursor:default;">
                                                    <script>
                                                        // reviewStar 변수에 따라서 1부터 5까지의 ★로 채워진 별을 생성
                                                        var reviewStarCount = ${ review.reviewStar }; // reviewStar 변수의 값

                                                        // 최대 5개까지 ★로 채워진 별 생성
                                                        if (reviewStarCount >= 1) {
                                                            document.write('★');
                                                        }
                                                        if (reviewStarCount >= 2) {
                                                            document.write('★');
                                                        }
                                                        if (reviewStarCount >= 3) {
                                                            document.write('★');
                                                        }
                                                        if (reviewStarCount >= 4) {
                                                            document.write('★');
                                                        }
                                                        if (reviewStarCount >= 5) {
                                                            document.write('★');
                                                        }
                                                    </script>
                                                </span>
                                                <span style="color: #bababa;" onclick="ShowUpdateForm(this)">수정</span>
                                                <span style="color: #bababa; cursor:default;">｜</span>
                                                <span style="color: #bababa;"
                                                    onclick="deleteReview('${review.reviewNo}', '<%=request.getContextPath()%>')">삭제</span>
                                            </div>
                                        </div>
                                        <div id="right2-mid">
                                            <c:choose>
                                                <c:when test="${empty review.reviewImg}">
                                                    <img src="resources/img/myPage/logo.PNG" style="object-fit: contain;">
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="img" items="${review.reviewImg}">
                                                        <img src="${img.filePath}${img.changeName}" style="object-fit: cover;">
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div id="right2-bottom">
                                            <p
                                                style="text-align: left; overflow: initial; white-space: initial; margin-bottom: 0px;">
                                                ${review.reviewContent}
                                            </p>
                                            <div id="updateForm" style="margin-top: 0px;" hidden>
                                                <input type="text"
                                                    style="width: 500px; height: 40px; border: 1px solid #bababa;
                                                    border-top-left-radius: 15px; border-bottom-left-radius: 15px; float: left; padding-left: 10px;"
                                                    value="${review.reviewContent}" id="updateContent-${review.reviewNo}">
                                                <button
                                                    style="width: 40px; height: 40px; border: 1px solid #bababa; float: left;"
                                                    onclick="updateReview('${review.reviewNo}', '<%=request.getContextPath()%>', 'updateContent-${review.reviewNo}')">확인</button>
                                                <button style="width: 40px; height: 40px; border: 1px solid #bababa; float: left;
                                                    border-top-right-radius: 15px; border-bottom-right-radius: 15px;"
                                                    onclick="HideUpdateForm(this)">취소</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div id="right3">
                                    <span>1 2 3 4 5 6 7 8 9 10</span>
                                    <span>▶</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%@ include file="../common/footer.jsp" %>

        </body>

        </html>