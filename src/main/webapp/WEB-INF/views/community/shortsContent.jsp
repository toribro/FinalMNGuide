<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<%@ include file="../common/common-file.jsp" %>
	<link rel="stylesheet" href="resources/css/community/shortsContent.css"/>
    <script src="resources/js/community/shorts/shortsContent.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

    <div class="main">
        <div class="shorts-wrapper">
            <div class="shorts-content shorts-box-style">
                <video controls autoplay loop muted>
                    <source src="resources/video/test.mp4" type="video/mp4">
                </video>
                <div class="shorts-info">
                    <!-- 유저, 쇼츠 설명 -->
                    <div class="shorts-info-writer">
                        <div class="shorts-info-user">
                            <div><img src="resources/img/main/dog2.jpg" alt=""></div>
                            <div class="shorts-info-profile">
                                <div class="shorts-info-nickname">쿠키언니</div>
                                <div class="shorts-info-date">2024.05.13</div>
                            </div>
                        </div>
                        <div class="shorts-info-text">
                            쿠키 데리고 캠프 파이어 불멍했다 너무 귀여워 쿠키 최고
                        </div>
                    </div>

                    <!-- 찜, 댓글수 -->
                    <div class="shorts-info-count">
                        <!-- 찜 -->
                        <div class="shorts-like-count">
                            <div>♥</div>
                            <span>23</span>
                        </div>
                        <!-- 댓글 -->
                        <div class="shorts-reply-count" onclick="showReplyBox(this)" data-check="N">
                            <img src="resources/img/community/reply.png" alt="">
                            <span>45</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="shorts-reply-box shorts-box-style">
                <div class="reply-title-box">
                    <span class="reply-title-text">댓글</span>
                    <span class="reply-title-count font-color-gray">34</span>
                </div>

                <div class="reply-box">

                    <div class="reply-content-box" style="border: none">
                        <img src="resources/img/main/dog2.jpg" class="user-profile" alt="">
                        <div class="reply-content-wrap">
                            <div class="reply-content-info">
                                <div>
                                    <span class="reply-user">토리형</span>
                                    <span class="reply-date font-color-gray">2024.05.13</span>
                                </div>
                                <!-- 본인이 쓴 댓글일 경우 삭제하기로 변경 -->
                                <span class="reply-regist-re font-color-gray">답글 달기</span>
                            </div>
                            <div class="reply-content-text">
                                너무 귀여워ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
                            </div>
                        </div>
                    </div>

                    <div class="reply-content-box">
                        <img src="resources/img/main/dog2.jpg" class="user-profile" alt="">
                        <div class="reply-content-wrap">
                            <div class="reply-content-info">
                                <div>
                                    <span class="reply-user">토리형</span>
                                    <span class="reply-date font-color-gray">2024.05.13</span>
                                </div>
                                <span class="reply-regist-re font-color-gray">답글 달기</span>
                            </div>
                            <div class="reply-content-text">
                                캠핑 가고 싶다
                            </div>
                        </div>
                    </div>

                    <div class="reply-content-box re-reply">
                        <img src="resources/img/main/dog2.jpg" class="user-profile" alt="">
                        <div class="reply-content-wrap">
                            <div class="reply-content-info">
                                <div>
                                    <span class="reply-user">토리형</span>
                                    <span class="reply-date font-color-gray">2024.05.13</span>
                                </div>
                                
                            </div>
                            <div class="reply-content-text">
                                저도요
                            </div>
                        </div>
                    </div>
                </div>

                <div class="reply-regist-wrap">
                    <div class="reply-regist-box">
                        <img src="resources/img/main/dog2.jpg" class="user-profile" alt="">
                        <textarea name="" id=""></textarea>
                        <div><img src="resources/img/community/upload.png" alt=""></div>
                    </div>
                </div>

            </div>
        </div>

        <div class="shorts-content">
            
        </div>
    </div>
	
    <%@ include file="../common/footer.jsp"%>
</body>
</html>