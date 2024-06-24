<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        
        <%@ include file="../common/common-file.jsp" %>
        <link rel="stylesheet" href="resources/css/bosspage/bossPageReviews.css" />
        <link rel="stylesheet" href="resources/css/common/common.css" />
        <script src="resources/js/bosspage/bossreviews.js"></script>
    </head>

    <body>
        <div id="modal-background"></div>
        <%@ include file="../common/header.jsp" %>
            <div id="bossmainpage-wrap" class="wrapper">
                <div class="bossmainpage">
                    <div>
                        <%@ include file="../bosspage/bossmanubar.jsp" %>
                    </div>
                    <div class="privacy-page">
                        <div class="title">리뷰 관리</div>
                        <div id="reviews">
                            <div id="top">
                                <div style="margin-top: 20px; margin-left: 20px; line-height: 35px; display: flex;" id="top1">
                                    <img src="resources/img/myPage/profile.png" id="reviewsProfile">
                                    <span style="float: left; margin-left: 20px; font-size: 17px; font-weight: bold;">닉네임</span>
                                    <span style="float: left; margin-left: 20px; color: #FE8B94;">★★★★★</span>
                                    <span style="float: left; margin-left: 20px; font-size: 15px; color: #bababa;">2024.05.10</span>
                                    <button id="showReplyButton" onclick="showReply()" style="margin-left: 60px;">
                                        답글 달기
                                    </button>
                                    <div class="complete-message" style="margin-left: 60px; display: none;">
                                        <p contenteditable="showReplyButton">답글 완료</p>
                                    </div>
                                    <button onclick="giveCoupon()">
                                        쿠폰 발급
                                    </button>
                                </div>
                            </div>
                            <div id="mid">
                                <img src="resources/img/myPage/cafe.png">
                                <img src="resources/img/myPage/cafe.png">
                                <img src="resources/img/myPage/cafe.png">
                            </div>
                            <div id="bottom">
                                <p style="text-align: left; overflow: initial; white-space: initial;">좋아요 좋아요 좋아요 좋아요
                                    좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋...</p>
                            </div>
                            <div id="reply" style="margin-top: 20px; margin-left: 20px;">
                                <textarea name="content" id="replyArea" placeholder="사장님의 답글을 입력해 주세요."></textarea>
                                <button id="replySubmit" onclick="closeReply()">등록</button>
                            </div>
                            <!-- 답글 내용 -->
                            <div id="rpple-complete" style="display: none;">
                                <div class="rpple-date" id="replyContent"></div>
                                <button onclick="onDeleteClick()" class="rpple-removal">삭제</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="modal" style="display: none;">
                <div id="modal-body">
                    <div id="modal-body-top">
                        쿠폰 발급
                    </div>
                    <table id="modal-body-mid">
                        <tr id="coupon-table-title">
                            <th id="coupon-title">쿠폰명</th>
                            <th id="coupon-expiration">유효기간(일)</th>
                            <th id="coupon-count">사용 / 발매</th>
                            <th id="coupon-admin">증정 개수</th>
                        </tr>
                        <tr id="coupon-table-content">
                            <th id="checkbox"><input type="checkbox" name="" id=""></th>
                            <th id="content-title">멍냥식당 고객감사 2000원 할인</th>
                            <th id="content-expiration">14일</th>
                            <th id="content-count" style="margin-right: 60px;">6 / 10</th>
                            <th id="-"><button style="background-size: 8px; background-image: url(resources/img/myPage/-.png);" onclick="decreaseCount();">
                            </button></th>
                            <th id="content-admin">1</th>
                            <th id="+"><button style="background-size: 20px; background-image: url(resources/img/myPage/+.png);" onclick="increaseCount();">
                            </button></th>
                        </tr>
                    </table>
                    <div id="modal-body-end">
                        <button style="background-color: #FE8B94; color: white; margin-left: 50px;" onclick="issueCoupon()">
                            발급
                        </button>
                        <button style="background-color: white; margin-right: 50px;" id="cancel" onclick="closeModal()">
                            취소
                        </button>
                    </div>
                </div>
            </div>
            <%@ include file="../common/footer.jsp" %>
        </div>

        



    </body>

    </html>