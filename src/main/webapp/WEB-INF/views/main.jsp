<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="./common/common-file.jsp"%>
<link rel="stylesheet" href="resources/css/common/minibox.css"/>
<link rel="stylesheet" href="resources/css/main/main.css"/>
<script type="text/javascript"
src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f595fad336a38c5fdd5a3f12c81c8cdb&libraries=services,clusterer,drawing"></script>
<script src="resources/js/main/mainInit.js"></script>
<script src="resources/js/location/map/main-map.js"></script>
<script src="resources/js/main/main.js"></script>
<script src="resources/js/main/mainAjax.js"></script>
</head>
<body onload="init('<%=request.getContextPath()%>')">
	
	<%@ include file="./common/header.jsp"%>
	<!-- if문 등록해야 함 -->
	
	<!-- <div id="basic-info" onload="init('<%=request.getContextPath()%>', '<%=loginUser%>')"></div> -->
	<!-- 여기서 객체 자체를 넘기는 순간 String으로 다 넘어가므로 html 단에서 애초에 이 데이터만 넘겨야 함 -->
	<button type="button" id="onload-button" onclick="compareRegistPetTime('${loginUser.userNo}', '${loginUser.userId}')" style="display: none"></button>

	<c:if test="${not empty loginUser}">
		<div class="modal size" id="regist-pet-alarm">
			<div class="modal-dialog size">
				<div class="modal-content size">
					<!-- Modal body -->
					<div class="modal-body">
						<div class="minibox-title" id="regist-question-title">
							아직 반려동물 등록이 안 되어 있으시네요!
						</div>
						<div class="minibox-text" id="regist-question-content">
							동행하고 있는 반려동물을 등록하면 <br>
							좀 더 편리한 서비스 사용이 가능합니다.
						</div>
						<div id="regist-question-text">
							반려동물을 등록하시겠습니까?
						</div>

						<button class="common-button white-button" onclick="hideRegistPetModal(), checkRegistPetTime('${loginUser.userNo}', '${loginUser.userId}')" data-dismiss="modal">나중에 하기</button>
						<button class="common-button pink-button" onclick="gotoRegistPet()">지금 등록하기</button>

						<div id="regist-question-footer">
							<div>
								<input type="checkbox" id="check-alarm" class="checkbox-color-pink">
								<label for="check-alarm">3일 동안 보지 않기</label>
							</div>
							<!-- <button class="close" data-dismiss="modal" onclick="hideRegistPetModal()">닫기</button> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

    <div id="mainpage-wrap" class="wrapper">
		<!-- 상단 이미지 -->
		<div class="mainpage-full-img">
			<img src="resources/img/main/arjan-stalpers-8-sgismcDAQ-unsplash.jpg" alt="">
		</div>

		<div class="mainpage-wrap">

			<!-- 아이콘(검색, 커뮤니티, 쇼츠, 지도) -->
			<div id="mainpage-search-icon" class="gray-round-box flex-box">
				<div onclick="location.href='<%=contextPath%>/searchKeyword.pl'">
					<img src="resources/img/main/dog1.jpg" alt="">
					<div class="gugi-icon-title">검색</div>
				</div>
				<div onclick="location.href='<%=contextPath%>/community'">
					<img src="resources/img/main/cat1.jpg" alt="">
					<div class="gugi-icon-title">커뮤니티</div>
				</div>
				<div onclick="location.href='<%=contextPath%>/shortsContent.sh'">
					<img src="resources/img/main/dog2.jpg" alt="">
					<div class="gugi-icon-title">쇼츠</div>
				</div>
				<div onclick="location.href='<%=contextPath%>/'">
					<img src="resources/img/main/cat2.jpg" alt="">
					<div class="gugi-icon-title">지도</div>
				</div>
			</div>

			<!-- 추천 컨텐츠 -->
			<div id="search-ranking" class="gray-round-box">
				<div>반려동물과 좋은 추억을 남길 장소를 추천해드려요</div>
				<div style="overflow:hidden">
					<img id="place-img" src="" alt="">
					
					<!-- 추천 컨텐츠 세 개를 전부 감싸는 박스 -->
					<div id="search-contents-wrap">

					</div>
				</div>
			</div>
		</div>

		<!-- 커뮤니티 인기글 -->
		<div id="community-ranking" class="mainpage-full-img">
			<span>커뮤니티 인기글을 통해 우리 집 반려동물에 맞는 정보를 찾아보세요</span>
			
			<div class="flex-box">
				<!-- <div class="community-ranking-box">
					<table id="table-count">
						<tr><th colspan="2">인기 조회글</th></tr>

					</table>
				</div>
				<div class="community-ranking-box">
					<table id="table-good">
						<tr><th colspan="2">인기 추천글</th></tr>
						
					</table>
				</div>
				<div class="community-ranking-box">
					<table id="table-reply">
						<tr><th colspan="2">댓글 최다순</th></tr>
						
					</table>
				</div> -->
			</div>
		</div>

		<div class="mainpage-wrap">
			<div id="shorts-ranking" class="gray-round-box">
				<span>인기 멍냥이 쇼츠</span>
				<div>

				</div>
			</div>
		</div>

		<div id="mainpage-map" class="mainpage-full-img">
			<div class="mainpage-wrap">
				<div id="mainpage-map-title">대동반려지도</div>
				<div id="mainpage-map-text">지도를 중심으로 반려동물과 동행 가능한 장소를 찾아보세요</div>
				
				<div id="mainpage-map-content">
					<div id="mainpage-map-inbox" class="gray-round-box">
						<div class="mainpage-map-inbox-row">
							<div class="mainpage-map-category"  data-categoryName='식당/카페'>
								<div><img src="resources/img/main/food.png" alt=""></div>
								<span>식당/카페</span>
							</div>
							<div class="mainpage-map-category" data-categoryName='숙소'>
								<div class="padding-category"><img src="resources/img/main/hotel.png" alt=""></div>
								<span>숙소</span>
							</div>
						</div>
						<div class="mainpage-map-inbox-row">
							<div class="mainpage-map-category" data-categoryName='행사/테마파크'>
								<div class="padding-category"><img src="resources/img/main/themepark.png" alt=""></div>
								<span>행사/테마파크</span>
							</div>
							<div class="mainpage-map-category" data-categoryName='병원'>
								<div class="padding-category"><img src="resources/img/main/hospital.png" alt=""></div>
								<span>병원</span>
							</div>
						</div>
						<div class="mainpage-map-inbox-row">
							<div class="mainpage-map-category" data-categoryName='기타'>
								<div class="padding-category"><img src="resources/img/main/etc.png" alt=""></div>
								<span>기타</span>
							</div>
						</div>
					</div>
					<div id="mainpage-map-map" class="gray-round-box">
						<!-- <img src="resources/img/main/KakaoTalk_20240517_154417343.png" alt=""> -->
					</div>
				</div>
			</div>
		</div>
		
	</div>

	
    <%@ include file="./common/footer.jsp"%>
	
</body>
</html>