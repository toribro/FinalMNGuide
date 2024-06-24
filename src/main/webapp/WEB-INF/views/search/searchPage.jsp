<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/common/minibox.css"/>
<link rel="stylesheet" href="resources/css/search/searchPage.css"/>
<script src="resources/js/search/searchInit.js"></script>
<script src="resources/js/search/search.js"></script>
<script src="resources/js/search/searchAjax.js"></script>
</head>
<body onload="init('<%=request.getContextPath()%>', '${loginUser.userNo}')">
	<%@ include file="../common/header.jsp"%>
    <div class="wrapper">
		<div class="main">
            <!-- 검색 필터링 -->
            <div id="category-box">
                <!-- 동물 필터링 -->
                <div class="category-box gray-round-box">
                    <!-- category-box-content div 클릭 시 박스 체크 되도록 js 만들어야 함 -->
                    <div class="category-box-content category-big">
                        <label for="filter-dog">강아지</label>
                    </div>
                    <div class="category-box-content">
                        <label for="filter-dog-small">소형견</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="1" id="filter-dog-small" class="filter-pet checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-dog-middle">중형견</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="2" id="filter-dog-middle" class="filter-pet checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-dog-big">대형견</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="3" id="filter-dog-big" class="filter-pet checkbox-color-pink">
                    </div>
                    <div id="category-cat" class="category-box-content category-border-none">
                        <label for="filter-cat">고양이</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="4" id="filter-cat" class="filter-pet checkbox-color-pink category-border-none">
                    </div>
                </div>

                <!-- 장소 필터링 -->
                <div class="category-box gray-round-box">
                    <!-- category-box-content div 클릭 시 박스 체크 되도록 js 만들어야 함 -->
                    <div class="category-box-content category-big">
                        <span for="filter-place" style="margin: auto 0px;">장소 카테고리</span>
                    </div>
                    <div class="category-box-content">
                        <label for="filter-restaurant">식당</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="2" id="filter-restaurant" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-cafe">카페</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="3" id="filter-cafe" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-hotel">숙소</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="4" id="filter-hotel" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-event">행사</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="5" id="filter-event" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-park">테마파크</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="6" id="filter-park" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content">
                        <label for="filter-hospital">병원</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="7" id="filter-hospital" class="filter-location checkbox-color-pink">
                    </div>
                    <div class="category-box-content category-border-none">
                        <label for="filter-etc">기타</label>
                        <input type="checkbox" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" value="1" id="filter-etc" class="filter-location checkbox-color-pink category-border-none">
                    </div>
                </div>
            </div>

            <!-- 검색 결과 컨텐츠 -->
            <div id="search-result-box" class="gray-round-box">
                <!-- 검색 결과 타이틀 / 정렬기준 -->
                <div id="search-title-box">
                    <div id="search-title-text">"${keyword}" 검색 결과</div>
                    <div id="search-order-by">
                        <div id="order-by-title" onclick="showOrderBox()">
                            <span>정렬기준</span>
                            <img src="resources/img/searchpage/open-icon.png" alt="">
                        </div>
                        <input type="radio" name="order" id="order-by-time" value="1" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" style="display: none;">
                        <input type="radio" name="order" id="order-by-star" value="2" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" checked="true" style="display: none;">
                        <input type="radio" name="order" id="order-by-pick" value="3" onchange="searchFilter('${keyword}', '${loginUser.userNo}')" style="display: none;">
                        <div id="order-by-box">
                            <label class="order-by-list" for="order-by-time">최신순</label>
                            <label class="order-by-list" for="order-by-star" style="color: var(--main-color);">별점순</label>
                            <label id="order-by-last" class="order-by-list" for="order-by-pick">찜개수순</label>
                        </div>
                        <!-- 이걸 onclick으로 하면 지금 현재 어떤 값이 클릭되어 있는지를 확인하지 못함, select box나 radio로 해야 함 -->
                        
                    </div>
                </div>
                <c:choose>
                    <c:when test="${empty locationList}">
                        <div class="gray-round-box none-search">
                            <div class="none-text">검색 결과가 없습니다.</div>
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <c:forEach var="loc" items="${locationList}">
                            <div class="search-content-box gray-round-box" onclick="handelPick(event, '${loginUser.userNo}', '${loc.locationNo}')">
                                <c:choose>
                                    <c:when test="${not empty loc.attachment}">
                                        <img src="${loc.attachment.filePath}${loc.attachment.changeName}" alt="">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="resources/img/default/default_location_img.png" alt="">
                                    </c:otherwise>
                                </c:choose>
                                
                                <div class="search-content">
                                    <div class="content-title">
                                        <span>${loc.locationName}</span>
                                        <div class="pick-box" data-locno='${loc.locationNo}'>
                                            <c:choose>
                                                <c:when test="${loc.userPick eq 0}">
                                                    <img src="resources/img/searchpage/like-pre.png" style="cursor: pointer;" alt="">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="resources/img/searchpage/like-after.png" style="cursor: pointer;" alt="">
                                                </c:otherwise>
                                            </c:choose>
                                            <span>${loc.pickCount}</span>
                                        </div>
                                    </div>
                                    <div class="content-upper-box">
                                        <div>
                                            <span class="font-bold">분류</span>
                                            <span>${loc.locationCategoryNo}</span>
                                        </div>
                                        <div>
                                            <span class="font-bold">평점</span>
                                            <span>${loc.locationStar}</span>
                                            <div>
                                                <c:forEach begin="1" end="${loc.locationStarCount}">
                                                    <img src="resources/img/searchpage/rating-star.png" alt="">
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div>
                                            <span class="font-bold">종류</span>
                                            <span>
                                                <c:forEach var="size" items="${loc.enterList}">
                                                    ${size.petSizeNo} 
                                                </c:forEach>
                                            </span>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="content-lower-box">
                                        <div>
                                            <img src="resources/img/searchpage/location.png" alt="">
                                            <span>${loc.address}</span>
                                        </div>
                                        <div>
                                            <img src="resources/img/searchpage/phone.png" alt="">
                                            <span>${loc.locationPhone}</span>
                                        </div>
                                        <div>
                                            <img src="resources/img/searchpage/time.png" alt="">
                                            <!-- <c:choose>
                                                <c:when test="${loc.locationCategoryNo eq '숙소'}">
                                                    <span class="operation-time">
                                                </c:when>
                                                <c:otherwise>
                                                    
                                                </c:otherwise>
                                            </c:choose> -->
                                            <span class="operation-time" data-category="${loc.locationCategoryNo}" data-start="${loc.opTime.startTime}" data-end="${loc.opTime.endTime}" data-status="${loc.opTime.restStatus}"></span>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                
                        <!-- 페이징 바 -->
                        <div id="paging-bar">
                            <c:if test="${pi.currentPage ne pi.startPage}">
                                <button onclick="searchFilterPage('${keyword}', '${loginUser.userNo}', '${pi.currentPage - 1}')">&lt;</button>
                            </c:if>
                            <c:forEach var="i" begin="${pi.startPage }" end="${pi.endPage }">
                                <button onclick="searchFilterPage('${keyword}', '${loginUser.userNo}', '${i}')">${i}</button>
                            </c:forEach>
                            <c:if test="${pi.currentPage ne pi.maxPage}">
                                <button onclick="searchFilterPage('${keyword}', '${loginUser.userNo}', '${pi.currentPage + 1}')">&gt;</button>
                            </c:if>
                            
                        </div>

                    </c:otherwise>
                </c:choose>
                
            </div>
        </div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
	
</body>
</html>