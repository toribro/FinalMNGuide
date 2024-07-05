<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <%@ include file="../common/common-file.jsp"%>
    <link rel="stylesheet" href="resources/css/common/common.css" />
    <link rel="stylesheet" href="resources/css/common/minibox.css"/>
    <link rel="stylesheet" href="resources/css/bosspage/bosspage.css"/>
    <link rel="stylesheet" href="resources/css/bosspage/bossCouponPage.css"/>
    <script src="resources/js/bosspage/bossCouponPageInit.js"></script>
    <script src="resources/js/bosspage/bossCouponPage.js"></script>
    <script src="resources/js/bosspage/ajax/bossCouponPageAjax.js"></script>
</head>

<body>
    <%@ include file="../common/header.jsp" %>

    <div id="bossmainpage-wrap" class="wrapper">
        <div class="bossmainpage">
            <div>
                <%@ include file="../bosspage/bossmanubar.jsp" %>
            </div>
    
            <!-- 등록된 쿠폰 있는 경우! -->
            <div class="privacy-page">
                <div class="privacy-top">쿠폰 관리</div>
            
                <!-- 테이블 항목명 -->
                <table>
                    <tr id="coupon-table-title">
                        <th class="coupon-title">쿠폰명</th>
                        <th class="coupon-expiration">유효기간(일)</th>
                        <th class="coupon-count">사용 / 발매</th>
                        <th class="coupon-admin">관리</th>
                    </tr>

                    <!-- 등록된 쿠폰 없을 시 -->
                    <tr id="coupon-none">
                        <td colspan="4">등록된 쿠폰이 없습니다.</td>
                    </tr>

                    <!-- 등록된 쿠폰이 있을 때 표시되는 쿠폰 항목 -->
                    <tr class="coupon-content">
                        <td class="coupon-title">멍냥가이드회원감사쿠폰이천원멍냥가이드회원감사쿠폰이천원멍냥</td>
                        <td class="coupon-expiration">14</td>
                        <td class="coupon-count">1 / 3</td>
                        <td class="coupon-admin">
                            <button class="common-button pink-button" onclick="editCouponForm(this)">수정</button>
                            <button class="common-button pink-button" data-toggle="modal" data-target="#delete-coupon">삭제</button>
                        </td>
                    </tr>
            
                    <!-- 쿠폰 추가 등록 (초기에는 숨겨짐) -->
                        <tr class="coupon-content new-coupon-form" style="display:none;">
                           <!-- 쿠폰명 30글자 제한 필요 -->
                            <td class="coupon-title">
                                <input type="text" class="minibox-input" placeholder="쿠폰명을 입력하세요">
                            </td>
                            <td class="coupon-expiration">
                                <input type="number" class="minibox-input" placeholder="ex)14">
                            </td>
                            <td class="coupon-count">0 / 0</td>
                            <td class="coupon-admin">
                                <button class="common-button pink-button">확인</button>
                                <button class="common-button white-button" onclick="removeCouponForm(this)">취소</button>
                            </td>
                        </tr>

                    <!-- 쿠폰 추가 버튼 -->
                    <tr id="coupon-regist">
                        <td colspan="4">
                            <button class="common-button" onclick="showNewCouponForm()">
                                <img src="resources/img/bosspage/plus.png" alt="">
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

        <!-- 쿠폰 삭제 클릭 시 나오는 모달 -->
        <div class="modal size" id="delete-coupon">
            <div class="modal-dialog size">
                <div class="modal-content size">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="minibox-title" id="delete-coupon-title">
                            쿠폰 삭제
                        </div>
                        <div id="delete-coupon-text">멍냥가이드감사쿠폰2000원</div>
                        <div class="minibox-text" id="delete-coupon-content">
                            삭제 시 해당 쿠폰의 수정과 <br>
                            사용/발매 내역 조회가 불가능합니다. <br><br>
                            해당 쿠폰을 삭제하시겠습니까?
                        </div>
                        <button class="common-button pink-button">삭제</button>
                        <button class="common-button white-button" data-dismiss="modal">취소</button>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../common/footer.jsp" %>

</body>

</html>
