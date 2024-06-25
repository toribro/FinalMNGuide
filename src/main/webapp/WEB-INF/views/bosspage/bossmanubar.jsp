<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>
    
    
    <head>
        <meta charset="UTF-8">
        <title>사장님메뉴</title>
        
        <%@ include file="../common/common-file.jsp"%>
        <link rel="stylesheet" href="resources/css/bosspage/bosspage.css" />
        <script src="resources\js\bosspage\bosspage.js"></script>

    <body>
        <div id="bossmanubar">
            <div style="border-top-left-radius: 15px; border-top-right-radius: 15px; border-bottom: none;">
                <a href="<%=request.getContextPath()%>/bossMainPage.bm">사업자 / 개인 정보</a>
            </div>
            <div style="border-bottom: none;">
                <a href="<%=request.getContextPath()%>/bossLocation.bl">장소 정보</a>
            </div>
            <div style="border-bottom: none;"><p style="margin-left: 10px;">쿠폰 / 리뷰관리</p></div>
            <div style="border-bottom: none;">
                <a href="<%=request.getContextPath()%>/bossCouponPage.bc">쿠폰 관리</a>
            </div>
            <div style="border-bottom-left-radius:15px; border-bottom-right-radius: 15px;">
                <a href="<%=request.getContextPath()%>/bossPageReviews.bp">리뷰 관리</a>
            </div>
            <div class="chatlist">
                <a href="<%=request.getContextPath()%>/chatPage.cp">채팅 관리</a>
            </div>
        </div>
        

    </body>


    </html>