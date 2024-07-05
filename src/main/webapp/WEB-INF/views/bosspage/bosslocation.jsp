<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="../common/common-file.jsp" %>
    <link rel="stylesheet" href="resources/css/bosspage/bosspage.css" />
    <link rel="stylesheet" href="resources/css/bosspage/locationinfo.css" />
    <link rel="stylesheet" href="resources/css/common/common.css" />
    <script src="resources/js/bosspage/bosslocation.js"></script>
    <script>
        var contextPath = "<%= request.getContextPath() %>";
    </script>
</head>
<body onload="initializePage()">
    <%@ include file="../common/header.jsp" %>
    <div id="bossmainpage-wrap" class="wrapper">
        <div class="bossmainpage">
            <div>
                <%@ include file="../bosspage/bossmanubar.jsp" %>
            </div>
            <div class="privacy-page">
                <div class="privacy-top">${loginUser.userName} 대표님의 장소정보</div>
                <div id="location-details">
                    <p>업종</p>
                    <p class="location-category" id="location-category">${location.locationCategoryNo}</p>
                    <div>
                        <p>상호명</p>
                        <p class="locationName">${location.locationName}</p>
                    </div>
                    <div>
                        <p>가게 전화번호(*필수*)</p>
                        <input type="tel" name="가게전화번호" id="store-phone" style="background: white;" placeholder="(ex.지역번호)-0000-0000">
                    </div>
                    <div id="operating">
                        <p>운영시간(*필수*)</p>
                        <div id="operating-hours-container"></div>
                    </div>
                    <div id="storeinfo">
                        <p>상세 설명(필수*)</p>
                        <textarea id="store-description" style="border-radius: 15px;" placeholder="*장소에 대한 설명과 반려동물 풀입 시 도움이 될 만한 정보를 적어주세요. (ex)테이블 밀집도, 케이지 필요 여부 등."></textarea>
                    </div>
                    <div>
                        <p>반려동물 종류 및 크기(*필수*)</p>
                        <div id="animal-check">
                            <p>소형견</p>
                            <input type="checkbox" name="animal-type" value="소형견">
                            <p>중형견</p>
                            <input type="checkbox" name="animal-type" value="중형견">
                            <p>대형견</p>
                            <input type="checkbox" name="animal-type" value="대형견">
                            <p>고양이</p>
                            <input type="checkbox" name="animal-type" value="고양이">
                        </div>
                    </div>
                    <div id="reservation-link">
                        <p>외부 예약링크(선택)</p>
                        <input type="text" id="reservation-link-input" placeholder="ex)www.mnguide.com">
                    </div>
                    <div>
                        <p>업체 사진등록(*필수*)</p>
                        <div id="photo-registration">
                            <label for="file7" class="registration-upload" data-target="file7">
                                <img src="resources/img/myPage/+.png">
                            </label>
                            <input type="file" accept="image/*" name="file" id="file7" class="company-file" style="display: none;">
                            <label for="file8" class="registration-upload" data-target="file8">
                                <img src="resources/img/myPage/+.png">
                            </label>
                            <input type="file" accept="image/*" name="file" id="file8" class="company-file" style="display: none;">
                            <label for="file9" class="registration-upload" data-target="file9">
                                <img src="resources/img/myPage/+.png">
                            </label>
                            <input type="file" accept="image/*" name="file" id="file9" class="company-file" style="display: none;">
                        </div>
                    </div>
                    <div id="product-registration-section">
                        <p>상품등록</p>
                        <div id="product-registration">
                            <button id="add-product">
                                <img class="add-productimg" src="resources/img/myPage/+.png">
                            </button>
                        </div>
                    </div>
                    <div id="upload-location">
                        <button class="upload-bt" onclick="saveLocationInfo()">장소정보 등록 & 업데이트</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>
    
</body>
</html>