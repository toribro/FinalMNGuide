<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>

        <%@ include file="../common/common-file.jsp" %>
            <link rel="stylesheet" href="resources/css/myPage/myPagePetInfo.css" />
            <link rel="stylesheet" href="resources/css/common/common.css" />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="resources/js/myPage/myPagePetInfo.js"></script>
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
                                    style="background-image: url(${loginUser.userProfile.filePath}${loginUser.userProfile.changeName}); background-position: center; background-repeat: no-repeat; background-size: cover;">
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
                            <c:if test="${empty petList}">
                                <div id="right1" style="display: none;">
                                    <p id="contentText">아직 등록하신 반려동물이 없으시네요!<br>지금 바로 반려동물을 등록하고 행복한 동행을 시작해보세요.</p>
                                    <button type="submit" id="signUp"
                                        onclick="location.href='<%=request.getContextPath()%>/myPagePetSignUp.mp'"
                                        style="cursor: pointer;">반려동물 등록하기</button>
                                </div>
                            </c:if>
                            <div id="right2">
                                <h1>반려동물 1</h1>
                                <c:forEach items="${petList}" var="pet" varStatus="loop">
                                    <form class="petForm${loop.index}" id="right2-container" method="post"
                                        action="updatePet.mp" data-index="${loop.index}">
                                        <div id="right2-container" class="right2-container-${loop.index}">
                                            <div id="right2-left" style="padding-left: 60px;">
                                                <c:choose>
                                                    <c:when test="${ empty pet.petImg}">
                                                        <div style="background-image: url(resources/img/myPage/logo.PNG); background-position: center;
                                                        background-repeat: no-repeat; width: 300px; height: 300px; cursor: pointer;
                                                        border-radius: 15px; object-fit: cover;" id="petProfile-${loop.index}"
                                                        onclick="document.getElementById('petImgInput-${loop.index}').click()">
                                                    </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div style="background-image: url(${pet.petImg.filePath}${pet.petImg.changeName}); background-position: center;
                                                            background-repeat: no-repeat; background-size: cover; width: 300px; height: 300px; cursor: pointer;
                                                            border-radius: 15px;" id="petProfile-${loop.index}"
                                                            onclick="document.getElementById('petImgInput-${loop.index}').click()">
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="file" id="petImgInput-${loop.index}" style="display: none;"
                                                    onchange="uploadPetImage('${pet.petNo}', '<%=request.getContextPath()%>', ${loop.index})">
                                                <p style="font-size: 18px; color: #FE8B94;margin-top: 10px;">사진 클릭시 사진
                                                    변경 가능</p>
                                                <button type="button" class="delete"
                                                    style="cursor: pointer; margin-top: 35px;"
                                                    onclick="deletePet('${pet.petNo}', '<%=request.getContextPath()%>')">삭제</button>
                                            </div>
                                            <div id="right2-right" style="display: flex; flex-direction: column;">
                                                <div>
                                                    <p style="font-size: 18px; margin-bottom: 0px; text-align: left;">이름
                                                    </p>
                                                    <input type="text" class="petName" name="petName"
                                                        value="${pet.petName}" style="padding-left: 10px;">
                                                </div>
                                                <div style="margin-top: 30px;">
                                                    <p style="font-size: 18px; margin-bottom: 0px; text-align: left;">생일
                                                    </p>
                                                    <input type="date" class="petBirthday" name="petBirthday"
                                                        value="${pet.petBirthday}" style="padding-left: 10px;">
                                                </div>
                                                <div style="margin-top: 30px;">
                                                    <p style="font-size: 18px; margin-bottom: 0px; text-align: left;">크기
                                                    </p>
                                                    <select name="petSizeNo" class="petSizeNo"
                                                        style="padding-left: 10px;">
                                                        <option value="1" ${pet.petSizeNo eq '1' ? 'selected' : '' }>소형견
                                                        </option>
                                                        <option value="2" ${pet.petSizeNo eq '2' ? 'selected' : '' }>중형견
                                                        </option>
                                                        <option value="3" ${pet.petSizeNo eq '3' ? 'selected' : '' }>대형견
                                                        </option>
                                                        <option value="4" ${pet.petSizeNo eq '4' ? 'selected' : '' }>고양이
                                                        </option>
                                                    </select>
                                                </div>
                                                <div class="gender-input" id="gender-input${loop.index}"
                                                    style="display: flex;">
                                                    <input type="radio" name="petGender" id="men${loop.index}" value="M"
                                                        ${pet.petGender eq 'M' ? 'checked' : '' } class="petGender">
                                                    <label for="men${loop.index}">남아</label>
                                                    <input type="radio" name="petGender" id="women${loop.index}"
                                                        value="F" ${pet.petGender eq 'F' ? 'checked' : '' }
                                                        class="petGender">
                                                    <label for="women${loop.index}">여아</label>
                                                </div>
                                                <button type="submit" class="update update-${loop.index}"
                                                    style="margin-top: 55px; cursor: pointer;"
                                                    onclick="updatePet(petNo)">수정</button>
                                            </div>
                                        </div>
                                        <input type="text" hidden name="petNo" value="${pet.petNo}">
                                    </form>
                                </c:forEach>

                            </div>
                            <div id="right3">
                                <button id="addSignUp"
                                    onclick="location.href='<%=request.getContextPath()%>/myPagePetSignUp.mp'"
                                    style="cursor: pointer;">반려동물 추가
                                    등록하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>