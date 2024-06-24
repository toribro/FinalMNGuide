<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <%@ include file="../common/common-file.jsp" %>

        <link rel="stylesheet" href="resources/css/myPage/myPagePetSignUp.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="resources/js/myPage/myPagePetSignUp.js"></script>
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
                                <div style="border-style: none; color: #FE8B94;" id="petInfo"
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
                            <form action="insertPet.mp" id="petSignUp" enctype="multipart/form-data">
                                <h1 style="margin-top: 50px; text-align: center;">반려동물 등록</h1>
                                <p style="margin-top: 40px; font-size: 17px; text-align: center;">반려동물 정보를 입력해<br>행복한 멍냥
                                    가이드를 누려보세요</p>
                                <div
                                    style="margin-left: 100px; margin-top: 30px; font-size: 21px; display: flex; flex-direction: column;">
                                    <div style="margin-top: 40px;">
                                        <span style="float: left; margin-bottom: 0px;">이름</span>
                                        <span style="color: #FE8B94; float: left; margin-bottom: 0px;">*</span>
                                    </div>
                                    <div>
                                        <input type="text" placeholder="이름을 입력하세요" id="petName"
                                            style="font-size: 17px; padding-left: 30px;" name="petName">
                                    </div>
                                </div>
                                <div style="margin-left: 100px; margin-top: 40px; font-size: 21px;">
                                    <select name="petSizeNo" id="petSizeNo"
                                        style="font-size: 17px; padding-left: 30px;">
                                        <option value="1">소형견</option>
                                        <option value="2">중형견</option>
                                        <option value="3">대형견</option>
                                        <option value="4">고양이</option>
                                    </select>
                                </div>
                                <div style="margin-left: 100px; font-size: 21px; margin-top: 40px;">
                                    <span style="float: left;">생일</span><br>
                                    <input type="date" id="petBirthday"
                                        style="font-size: 17px; padding-left: 30px; padding-right: 30px;"
                                        name="petBirthday">
                                </div>
                                <div id="gender-input">
                                    <input type="radio" name="petGender" id="men" value="M">
                                    <label for="men">남아</label>
                                    <input type="radio" name="petGender" id="women" value="F">
                                    <label for="women">여아</label>
                                </div>
                                <!-- <div style="margin-left: 100px; font-size: 21px; margin-top: 40px;">
                                    <span style="float: left;">사진</span><br>
                                    <div id="imgInput" onclick=""
                                        style="background-image: url(resources/img/myPage/+.png);"></div>
                                    <input type="file" id="fileInput" style="display: none;" accept="image/*">
                                </div> -->
                                <!-- <div
                                    style="margin-left: 100px; font-size: 21px; margin-top: 40px; flex-direction: column;">
                                    <span
                                        style="float: left; text-align: left; margin-bottom: 0px !important;">사진</span><br>
                                    <label for="imgFileInput" id="imgInput"
                                        style="margin-top: 0px; top: 0px; background-image: url(resources/img/myPage/+.png); cursor: pointer; width: 150px; height: 150px; display: block; background-size: cover; background-position: center; border: 2px solid transparent;"></label>
                                    <input type="file" id="imgFileInput" style="display: none;" accept="image/*"
                                        onchange="displayImage(this)">
                                </div> -->
                                <button type="submit" id="submit">등록하기</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>