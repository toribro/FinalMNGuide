<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>

        <%@ include file="../common/common-file.jsp" %>
        <link rel="stylesheet" href="resources/css/bosspage/bosspage.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <link rel="stylesheet" href="resources/css/common/minibox.css" />
        <script src="resources/js/bosspage/bosspage.js"></script>
        <script src="resources/js/bosspage/bossMainPage.js"></script>
        <script src="resources/js/bosspage/ajax/bossMainPageAjax.js"></script>
    </head>

    <body>
        <%@ include file="../common/header.jsp" %>

            <div id="bossmainpage-wrap" class="wrapper">
                <div class="bossmainpage">
                    <div>
                        <%@ include file="../bosspage/bossmanubar.jsp" %>
                    </div>
                    <div class="privacy-page">
                        <div class="privacy-top">사업자 / 개인 정보</div>
                        <div id="information">
                            <div class="privacy">
                                <p style="font-weight: 700;">사업자 정보</p>
                                <div>
                                    <p class="privacy-title">이름(대표자명)</p>
                                    <div>${loginUser.userName}</div>
                                </div>
                                <div>
                                    <p class="privacy-title">생년월일</p>
                                    <div>${loginUser.userBirthday}</div>
                                </div>
                                <div>
                                    <p class="privacy-title">성별</p>

                                    <div>
                                        <c:choose>
                                            <c:when test="${loginUser.userGender eq 'M'}">
                                                남자
                                            </c:when>
                                            <c:when test="${loginUser.userGender eq 'F'}">
                                                여자
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                                <div>
                                    <p class="privacy-title">휴대폰번호</p>
                                    <div id="phone-display">${loginUser.userPhone}</div>
                                    <button class="change-phone-button" onclick="showPhoneChange()">수정</button>
                                </div>
                                <div id="change-phone-container" style="display: inline; display: none;">
                                    <div>
                                        <button class="change-phone" onclick="updatePhoneNumber('${userNo}', '<%=contextPath%>')">변경</button>
                                    </div>
                                    <div style="margin-bottom: 20px;">
                                        <input type="text" id="boss-phone-input"
                                            style="width: 100%; border-radius: 15px; border: 1px solid #BABABA; padding: 20px;"
                                            placeholder="'-' 을 포함한 휴대폰 번호를 입력해 주세요.">
                                    </div>
                                </div>
                                
	                            <c:if test="${not empty location}">
                                    <div>
                                        <p class="privacy-title">사업자번호</p>
                                        <div>${location.businessNo}</div>
                                    </div>
                                    <div>
                                        <p class="privacy-title">상호명</p>
                                        <div>${location.locationName}</div>
                                    </div>
                                    <div>
                                        <p class="privacy-title">소재지</p>
                                        <div>${location.address}</div>
                                    </div>
                                </c:if>
                                <div>
                                    <p class="privacy-title">이메일</p>
                                    <div id="email-display">${loginUser.userEmail}</div>
                                    <button class="change-email-button" onclick="showEmailChange()">수정</button>
                                </div>
                                <div id="change-personal" style="display: none;">
                                    <div style="margin-top: 10px; display: block;">
                                        <button class="change-email" onclick="updateEmail('${userNo}','<%=contextPath%>')">변경</button>
                                        <div style="gap: 10px;">
                                            <input type="text" id="email-local-part" class="boss-email">
                                            <span>@</span>
                                            <input type="email" id="boss-email" class="boss-domain" placeholder="">
                                            <select class="boss-email" onchange="updateEmailDomain('${userNo}','<%=contextPath%>')">
                                                <option value="선택해주세요">선택해주세요</option>
                                                <option value="직접입력">직접입력</option>
                                                <option value="naver.com">naver.com</option>
                                                <option value="daum.net">daum.net</option>
                                                <option value="gmail.com">gmail.com</option>
                                                <option value="hanmail.net">hanmail.net</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="border-top: 3px solid #BABABA; margin-top: 70px; margin-bottom: 30px;"></div>
                            <div id="edit-option">
                                <button style="margin-right: auto;" onclick="showPasswordModal()">비밀번호 변경</button>
                                <button onclick="showRemoveModal()">가게 삭제 및 탈퇴</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 가게 삭제 및 탈퇴 모달창 -->
                <div class="modal-overlay" id="modal-overlay"></div>
                <div id="boss-remove-modal">
                    <div>
                        <p style="font-weight: 700; font-size: 30px;">가게 삭제 및 탈퇴</p>
                    </div>
                    <div>
                        탈퇴하신 후에는 해당 회원정보로<br>
                        활동 하실수 없습니다.<br>
                        작성하신 게시물에 대해서는<br>
                        일괄 삭제됨을 알려드리며<br>
                        정보 복구가 불가능한점<br>
                        양해 부탁드립니다.<br><br>
                        다시 사이트의 이용을위해선<br>
                        귀하의 사업자 번호와 실명인증을<br>
                        통해 다시 가입을 해주셔야<br>
                        정상적인 이용이 가능합니다.<br><br>
                    </div>
                    <div class="remove-checkbox">
                        <p>정말로 탈퇴를 하시겠습니까?</p>
                        <div>
                            네<input type="radio" name="confirm" value="yes" onclick="toggleRemoveButton()">
                        </div>
                        <div>
                            아니요<input type="radio" name="confirm" value="no" onclick="toggleRemoveButton()">
                        </div>
                    </div>
                    <div class="boss-pwd">
                        <p style="margin-left: 10%;">비밀번호</p>
                        <p class="pwd-checkMessage">비밀번호가 일치하지 않습니다.</p>
                    </div>
                    <div class="boss-pwd-input">
                        <input type="password" id="password-input" placeholder="비밀번호를 입력하세요." oninput="toggleRemoveButton()">
                    </div>
                    <div class="boss-remove-button">
                        <button class="boss-remove" disabled onclick="checkPassword('<%=request.getContextPath()%>', '${userNo}')">탈퇴</button>
                        <button onclick="hideRemoveModal()">취소</button>
                    </div>
                </div>
                
                <!--비밀변호 재설정 -->
                <div id="boss-pws-div"  class="wrapper">
                    <div class="minibox-wrap" >
                        <div class="minibox-title">비밀번호 재설정</div>
                        <div class="minibox-text">
                            ${loginUser.userId} 님의 <br>
                            사용하실 비밀번호를 재설정 해주세요.
                        </div>
                
                        <form action="" id="reset-form">
                            <div class="minibox-mini-title">
                                <span class="error-message" id="mismatch-error">비밀번호가 일치하지 않습니다.</span>
                            </div>
                            
                            <input type="password" id="password" class="minibox-input" placeholder="비밀번호 입력">
                            <input type="password" id="confirm-password" class="minibox-input" placeholder="비밀번호 확인">
                            
                            <button type="submit" class="common-button pink-button minibox-full-button" onclick="updatePassword(event, '${userNo}', '<%=request.getContextPath()%>')">확인</button>
                            <button class="close-button" onclick="pwdModalClose()">취소</button>
                        </form>
                    </div>
                </div>

                <%@ include file="../common/footer.jsp" %>





    </body>

    </html>