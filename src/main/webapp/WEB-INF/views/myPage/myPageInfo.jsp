<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <%@ include file="../common/common-file.jsp" %>

        <link rel="stylesheet" href="resources/css/myPage/myPageInfo.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


        <script src="resources/js/myPage/myPageInfo.js"></script>
    </head>

    <body>

        <%@ include file="../common/header.jsp" %>

            <div class="wrapper main_wrapper">
                <div class="main_main" id="mainWrapper">
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
                                    onclick="location.href='<%=request.getContextPath()%>/myPageMain.mp'">작성한 리뷰</div>
                                <div id="wishList"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageWish.mp'">공감 목록</div>
                                <div id="coupon"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageCoupon.mp'">쿠폰 목록</div>
                                <div id="boardList"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageBoard.mp'">게시글 / 쇼츠 목록
                                </div>
                                <div id="info" style="color: #FE8B94;"
                                    onclick="location.href='<%=request.getContextPath()%>/myPageInfo.mp'">개인정보</div>
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
                        <form id="main_main_right" action="update.mp">
                            <h1 style="margin-top: 50px; color: #FE8B94; text-align: center;">개인정보 수정</h1>
                            <!-- <div id="id-input">
                                <span style="text-align: left; margin-bottom: 0px; font-size: 19px;">아이디</span>
                                <span id="hiddenId" style="color: red; font-size: 15px;"><b>경고!</b> 사용할 수 없는 아이디
                                    입니다.</span><br>
                                <input type="text" style="border-radius: 15px; border: 1px solid #bababa; margin-top: 0px; width: 80%; float: left;
                                  height: 50px; text-align: left; padding-left: 20px;" value="${loginUser.userId}"
                                    id="userId" name="userId">
                            </div> -->
                            <div id="name-input">
                                <p style="text-align: left; margin-bottom: 0px; font-size: 19px;">이름</p>
                                <input type="text" style="border-radius: 15px; border: 1px solid #bababa; margin-top: 0px; width: 80%; float: left;
                                  height: 50px; text-align: left; padding-left: 20px;" value="${loginUser.userName}"
                                    id="userName" name="userName">
                            </div>
                            <div id="nickName-input">
                                <p style="text-align: left; margin-bottom: 0px; font-size: 19px;">닉네임</p>
                                <input type="text" style="border-radius: 15px; border: 1px solid #bababa; margin-top: 0px; width: 80%; float: left;
                                  height: 50px; text-align: left; padding-left: 20px;"
                                    value="${loginUser.userNickname}" id="userNickname" name="userNickname">
                            </div>
                            <div id="gender-input">
                                <input type="radio" name="userGender" id="men" value="M" <% if
                                    ("M".equals(loginUser.getUserGender())) { %> checked <% } %>>
                                    <label for="men">남자</label>
                                    <input type="radio" name="userGender" id="women" value="F" <% if
                                        ("F".equals(loginUser.getUserGender())) { %> checked <% } %>>
                                        <label for="women">여자</label>
                            </div>
                            <div id="birth-input">
                                <p style="text-align: left; margin-bottom: 0px; font-size: 19px;">생년월일</p>
                                <input type="date" style="border-radius: 15px; border: 1px solid #bababa; margin-top: 0px; width: 80%; float: left;
                                  height: 50px; text-align: left; padding-left: 20px;"
                                    value="${loginUser.userBirthday}" id="userBirthday" name="userBirthday">
                            </div>
                            <div id="email-input"
                                style="display: flex; margin-left: 70px; margin-top: 50px; justify-content: space-between;">
                                <input type="text" style="width: 300px; height: 50px; border-radius: 15px;
                                 border: 1px solid #bababa; padding-left: 20px;" value="${loginUser.userEmail}"
                                    id="userEmail" name="userEmail">
                                <p style="font-size: 22px; line-height: 50px;">@</p>
                                <select id="website"
                                    style="border-radius: 15px; width: 300px; height: 50px; border: 1px solid #bababa; margin-right: 165px; padding-left: 20px;">
                                    <option value="naver.com">naver.com</option>
                                    <option value="daum.net">daum.net</option>
                                    <option value="gmail.com">gmail.com</option>
                                    <option value="gmail.com">nate.com</option>
                                </select>
                            </div>
                            <button type="submit" style="width: 80%; align-items: center; margin-top: 50px;
                             margin-left: 70px; border-style: none; color: white; background-color: #FE8B94;
                             border-radius: 15px; height: 50px; font-size: 22px; font-weight: bold;">수정</button>
                            <div
                                style="width: 80%; margin-top: 50px; margin-left: 70px; font-size: 19px; margin-bottom: 30px;">
                                <span style="float: left; text-align: left; cursor: pointer;" onclick="">비밀번호 변경</span>
                                <span style="float: right; text-align: right; cursor: pointer;"
                                    onclick="showConfirmation()">회원탈퇴</span>
                            </div>
                        </form>
                    </div>
                </div>
                <div id="confirmationModal" class="modal"
                    style="width: 400px; height: 400px; border: 1px solid #bababa; border-radius: 15px; position: absolute; z-index: 1000;"
                    hidden>
                    <div class="modal-content" style="text-align: center; font-size: 23px;">
                        <h1 style="color: red; margin-top: 40px;">알림</h1>
                        <p style="margin-top: 70px;">정말 "탈퇴" 하겠습니까?</p>
                        <button onclick="deleteMember('${loginUser.userNo}', '<%=request.getContextPath()%>')" style="margin-top: 80px; width: 130px; height: 50px; margin-right: 10px; font-size: 20px;
                                border-style: none; border-radius: 15px; background-color: #FE8B94; color: white; font-weight: bold;
                                cursor: pointer;">확인</button>
                        <button onclick="hideConfirmation()" style="margin-top: 80px; width: 130px; height: 50px; margin-left: 10px; font-size: 20px;
                                border-style: none; border-radius: 15px; background-color: #FE8B94; color: white; font-weight: bold;
                                cursor: pointer;">취소</button>
                    </div>
                </div>
            </div>

            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>