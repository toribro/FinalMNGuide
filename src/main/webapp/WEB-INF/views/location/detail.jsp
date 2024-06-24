<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
			<%@ include file="../common/common-file.jsp" %>


				<link rel="stylesheet" href="resources/css/location/detail.css" />
				<link rel="stylesheet" href="resources/css/location/review.css" />
				<link rel="stylesheet" href="resources/css/location/room.css" />
				<link rel="stylesheet" href="resources/css/location/review_star.css" />
				<link rel="stylesheet" href="resources/css/location/reply.css" />
				
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f595fad336a38c5fdd5a3f12c81c8cdb&libraries=services,clusterer,drawing"></script>
				<script src='resources/js/location/detailMainImg.js'></script>
				<script src='resources/js/location/ajax/init.js'></script>
				<script src='resources/js/location/ajax/pickAjax.js'></script>
				<script src='resources/js/location/ajax/reviewAjax.js'></script>
				<script src='resources/js/location/ajax/replyAjax.js'></script>
				<script src='resources/js/location/pick/pick.js'></script>
				<script src='resources/js/location/map/map.js'></script>
				<script src='resources/js/location/map/hostpital-map.js'></script>
				<script src='resources/js/location/review/review.js'></script>
				<script src='resources/js/location/review/reply.js'></script>
			

		</head>
		<%@ include file="../common/header.jsp" %>

			<body onload="init('${contextPath}','${userNo}','${checkedMaster}')">
			

				<div class="wrapper detail-wrapper">
					<!--헤더 이미지-->
				
				
                 
					<div id="headerImg" class="header-img">


					
							<div class="slide">
								<c:forEach var="h" begin="1" end="${l.mainAttachMent.size()}">
									<img src="${l.mainAttachMent.get(h-1).filePath}${l.mainAttachMent.get(h-1).changeName}">
								</c:forEach>
							 </div>
					
						
					     <div class="overlay">
							<h1 class="gugi-regular">${l.locationName}</h1>
						</div>
						
					</div>
					


					<!--메인영역-->
					<div class="main detail-main">
						<div class="space-detail">
							<div class="title">${l.locationName}</div>
							<div class="good">
								<div><a id="heart" class="pick-heart">♥</a></div>&nbsp;
								<div id="count">46</div>

							</div>
						</div>

						<div class="section">
							<div class="title">소개</div>
							<div class="content"> ${l.explanation}</div>
						</div>

						<div class="detail-div">
							<div class="section detail-area">
								<div class="title">상세정보 </div>
								<div class="detail-information detail-content">
									<div class="detail-area">
										<div><h5>${l.categoryName}</h5></div>
										<div class="title"><h6>☎${l.locationPhone}</h6></div><br>
										<!-- 장소별로 식별-->
									
										<div><h5>체크인 체크아웃</h5></div>
										<div class="location-check-in-out detail-area">
											<c:forEach var="o" items="${l.operationTime}">
												<span>${o.day}:</span>
												<c:if test="${o.restStatus eq false}">
													<span>▼${o.startTime}~${o.endTime}▲ </;span>
												</c:if>
												<c:if test="${o.restStatus eq true}">
													<span>※휴무※</span>
												</c:if>
												
											</c:forEach>
										  </div>
									  
									</div>

									<div>

										<div class="title">반려동물 출입등급</div>
										<div class="detail-area">
											<c:forEach var="k" items="${l.petKindGrade}">
												&nbsp; <span class="content">${k.petSizeName}</span>&nbsp;&nbsp;
											</c:forEach>
										</div><br>
										<div class="content">${l.address}</div>
										<div class="content">${l.reservationLink}</div>
									</div>
								</div>
							</div>

							<div class="section more-area">
								<span class="title">더 알아보기</span><br>
								<div><button class="common-button white-button" style="width:100%;"
										onclick="location.href='https://map.kakao.com/link/search/${l.address}'">지도보기</button>
								</div>
								<div> </div>
								<div><button class="common-button pink-button" style="width:100%;"
										onclick="location.href='chatPage.cp?locationNo=${l.locationNo}'">채팅문의</button></div>
							</div>
						</div>


						<div class="section">
							<div class="title">시설 상세</div>
							<div class="img-space">
								<c:forEach var="i" items="${l.detailAttachMent}">
									<div class="img-div"><img src="${i.filePath}${i.changeName}" alt="Profile Image"></div>
								</c:forEach>
							</div>
						</div>

						<!-- 장소 종류에따라 처리-->

						<c:if test="${l.locationCategoryNo != 4}">
						<!--상품 정보-->
						<div class="section">
							<div class="title">상품 정보</div>
							<c:forEach var="g" items="${l.locationOption}">
								<div class="content sales">
									<div>${g.goods}</div>
									<div style="width:80%">
										<hr>
									</div>
									<div>${g.goodPrice}</div>
								</div>
							</c:forEach>
						</div>
						</c:if>

					<c:if test="${l.locationCategoryNo == 4}">

						<!--객실 정보-->
						<c:forEach var="r" items="${l.locationOption}">
							<div class="section room">
								<div class="title">객실 정보</div>
								<div class="room-section room-information">
									<div class="img-space">
										<c:forEach var="i" items="${l.detailAttachMent}">
											<div class="img-div "><img src="${i.filePath}${i.changeName}" alt="Profile Image"></div>
										</c:forEach>
									</div>
									<div class="room-info">
										<div class="title" style="color:var(--main-color);">${r.goods}</div>
										<div class="title price">${r.goodPrice}</div>
										<div style="width:100%">
											<hr>
										</div>

										<c:forEach var="t" items="${l.operationTime}">
											<div class="check-in-out">
												${t.day}</span>
											</div>
										</c:forEach>
										<div class="capacity">
											객실기준<span>${r.capacity}</span>명/최대<span>${r.capacity}</span>명
										</div>
										<div class="detail-show">
											<button type="button" class="common-button white-button btn btn-primary"
												data-toggle="modal"
												data-target="#myModal${r.locationOptionNo}">상세보기</button>
										</div>
									</div>
								</div>
							</div>
						  
							<!-- The Modal  ${r.locationOptionNo}-->

							<div class="modal fade wrapper" id="myModal${r.locationOptionNo}">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">


										<!-- Modal body -->
										<div class="modal-body room-modal">
											<div class="title">객실 상세</div>
											<div class="img-modal-div">
												<c:forEach var="m" items="${l.detailAttachMent}">
													<div class="img-modal"><img src="${m.filePath}${m.changeName}"
													 alt="Profile Image"></div>
												</c:forEach>
											</div>
											<table>
												<tr>
													<th>객실명</th>
													<td>${r.goods}</td>
												</tr>

												<tr>
													<th>가격</th>
													<td>${r.goodPrice}</td>
												</tr>


												<c:forEach var="o" items="${l.operationTime}">
													<tr>
														<th>체크인:</th>
														<td>${o.startTime}</td>
													</tr>
													<tr>
														<th>체크아웃:</th>
														<td>${o.endTime}</td>
													</tr>

													<tr>
														<th>체크인/체크아웃:</th>
														<td>${o.day}</td>
													</tr>
												</c:forEach>

												<tr>
													<th>수용인원</th>
													<td>최대${r.capacity}명</td>
												</tr>
												<tr>
													<th>상세정보</th>
													<td>${r.roomInfo}</td>
												</tr>
											</table>

										</div>
										<!-- Modal footer -->
										<div class="modal-footer" style="align-items: center; width:100%;">
											<button type="button" class="common-button pink-button" onclick="location.href='${l.reservationLink}'">예약하기</button>
											<button type="button" class="common-button white-button"
												data-dismiss="modal">닫기</button>
										</div>

									</div>
								</div>
							</div>
						</c:forEach>

					 </c:if>



						<!--위치-->
						<div class="location-div">
							<div class="section location-area">
								<div class="title">위치정보 </div>
								<input id="address" type="text" value="서울특별시 강남구 테헤란로 14길 6" hidden>
								<input id="locationName" type="text" value="${l.locationName}" hidden>
								<div class="content">
									<div id="location-map" style="width:100%; height:500px;"></div>
								</div>
							</div>

							<div class="section hospital-area">
								<div class="title">동물병원</div>
								<div class="content">
									<div id="hostpial_map" style="width:300px;height:500px;"></div>
								</div>
							</div>
						</div>


						<div class="section review">
							<div class="title">리뷰</div>
							<div class="review-detail">
								<ul class="detail-ul">
									<li class="title score">${l.locationStar}</li>
								    <li class="avg-star" style="color:#FE8B94;">
										<c:forEach var="p" begin="1" end="${l.locationStar+((l.locationStar%1>0.5)?(1-l.locationStar%1)%1:-(l.locationStar%1))}">
										               ★
									    </c:forEach>
									</li>
									<li class="count" style="color:#c2bcbc;">${reviewCount}건의 리뷰</li>
								</ul>
								<ul class="category-ul">
									<li><a  onclick="reviewPaging(1,'o')" style="cursor: pointer;">최신순</a></li>
									<li><a  onclick="reviewPaging(1,'h')" style="cursor: pointer;">높은 평점순</a></li>
									<li><a  onclick="reviewPaging(1,'l')" style="cursor: pointer;">낮은 평점순</a></li>
								</ul>
							</div>

							<!--내용-->
							<div id="review-content-box">
								<c:forEach var="r" items="${review}">

									<div class="review-section" >
										<div class="profile-star">
											<div class="profile">
												<div class="img-div"><img src="${r.userProfile.filePath}${r.userProfile.changeName}"
														alt="Profile Image"></div>
												<div><span class="title">${r.userNickName}</span><br>
													<span>${r.enrollDate}</span>
												</div>
											</div>

											<div>
												<span style="color:#FE8B94;">
													<c:forEach begin="1" end="${r.reviewStar}">★</c:forEach>
												</span>
												<span>
												   <c:if test="${r.userNo == userNo}">
													<a href="myPageMain.mp">수정</a>
													<a onclick="reviewDelete('${r.reviewNo}','${r.userNo}')">삭제</a>
												   </c:if>
													
												</span>
											</div>
										</div>

										<div class="img-content">
											<c:forEach var="a" items="${r.attachment}">
												<div class="img-div"><img src="${a.filePath}${a.changeName}"
														alt="Profile Image"></div>
											</c:forEach>
										</div>
										<div class="content">${r.reviewContent}</div>

										<!--사장님 답글 영역-->
										<div>
										
											<c:if test="${checkedMaster.equals('YYYY')}">
												<input class="master-check" type="text" value="YYYYY" hidden >
												<a id="reply-button${r.reviewNo}" class="reply-button" onclick="onReplyOnClick('${r.reviewNo}')">답글작성</a>&nbsp;
											</c:if>
											 <c:if test="${!checkedMaster.equals('YYYY')}">
												<input class="master-check" type="text" value="NNNNN" hidden  >
											 </c:if>
											 <a id="reply-button-show${r.reviewNo}" class="reply-button" onclick="onReplyShow('${r.reviewNo}')">답글</a></div>
										<div id="master-reply-input-div${r.reviewNo}" class="master-reply-input show-reply">
											<textarea id="reply-content${r.reviewNo}" class="master-reply-content"></textarea>
											<button class="master-reply-button" onclick="insertReplyAjax('${r.reviewNo}')">작성하기</button>
											
										</div>
									</div>

									 
									<!-- 답글이 있을때만 처리-->
									<c:if test="${r.masterReply != null}">
										<div id="master-reply-content${r.reviewNo}" class="master-reply-input"style="align-items: right;">
											<div class="review-section reply">

												<div class="reply-master">
													<div class="title master">사장님</div>
													<div>${r.masterReply.enrollDate}</div>
												</div>
												<div class="content master-reply">${r.masterReply.ownerReplyContent}</div>
											</div>
										</div>
									</c:if>

								</c:forEach>
							</div>

							<!--test리뷰 내용영역-->
							<!--<c:forEach begin="1" end="2">
				
				<div class="review-section">
					<div class="profile-star">
						<div class="profile">
							<div class="img-div"><img src="resources/img/tori.jpg" alt="Profile Image"></div>
							<div><span class="title">토리형</span><br>
								<span>2024.05.18</span>
							</div>
						</div>

						<div>
							<span style="color:#FE8B94;">★★★★★</span>
							<span><a href="#">수정</a>|<a href="#">삭제</a></span>
						</div>
				    </div>

					<div class="img-content">
						<c:forEach begin="1" end="3">
							<div class="img-div"><img src="resources/img/tori.jpg" alt="Profile Image"></div>
						</c:forEach>
					</div>

					<div class="content">
					  <c:forEach begin="1" end="50">
						  내용내용내용
					   </c:forEach>
					</div>

	         	</div>

				 답글 영역
				 답글이 있을때만 처리 

				 <div style="align-items: right;">
						<div class="review-section reply">
						
							<div class="reply-master">
								<div class="title master">사장님</div>
								<div>2024-05-17</div>
							</div>

							<div class="content master-reply">
									<%for(int k=0 ; k<30; k++){%>
										답글내용내용내용
									<%}%>
							</div>
						</div>
			       </div>
			</c:forEach>
	      	-->
							<!--리뷰 작성 영역-->
							<div class="review-section">
								<div class="enroll-div">
									<div class="enroll-profile">
										<c:if test="${userProfile!=null}">
											<div class="img-div">
												<img src="${userProfile.filePath}${userProfile.changeName}" alt="Profile Image">
											</div>
									    </c:if>

										<div class="star-rating">
											<input type="radio" class="star" name="rating" value="1">
											<input type="radio" class="star" name="rating" value="2">
											<input type="radio" class="star" name="rating" value="3">
											<input type="radio" class="star" name="rating" value="4">
											<input type="radio" class="star" name="rating" value="5">
										</div>
									</div>

									<div class="review-enroll">
										<button id="review-submit" class="common-button pink-button" type="button">리뷰
											등록</button>
									</div>
								</div>

								<div class="enroll-area">
									<div class="review-section input-review">
										<textarea id="review-content" class="input-area"
											placeholder="내용을 입력하세요"></textarea>
									</div>
									<div class="review-section input-img">
										<label id="img-background" for="fileInput" class="upload-label"
											style="background: url(resources/img/myPage/+.png)  no-repeat center center/cover;">
											<div id="img-count"
												style="float:right; margin-top:170px; font-weight: bold; font-size:18px; color:green">
												0</div>
										</label>
										<input id="fileInput" name="file" type="file" style="display: none;"
											onchange="fileUpload(this)">
									</div>
								</div>
								<div id="file-list">첨부파일</div>
							</div>

							<!--페이지 처리 영역-->
							<div id="page-div" class="page-div">
								<c:choose>
									<c:when test="${reviewPi.currentPage eq 1}">
										<div id="previous-button" class="prv-button">
											<li class="page-disabled"><a class="page-button">◀</a></li>
										</div>
									</c:when>

									<c:otherwise>
										<div id="previous-button" class="prv-button">
											<li><a class="page-button"
													onclick="reviewPaging('${reviewPi.currentPage-1}')">◀</a></li>
										</div>

									</c:otherwise>
								</c:choose>

								<c:forEach var="p" begin="${reviewPi.startPage }" end="${reviewPi.endPage }">
									<li class="page-item"><a class="page-link" onclick="reviewPaging('${p}')">${p}</a>
									</li>
								</c:forEach>

								<c:choose>
									<c:when test="${reviewPi.currentPage eq reviewPi.maxPage}">
										<div id="next-button" class="next-button">
											<li class="page-disabled"><a class="page-button">▶</a></li>
										</div>
									</c:when>

									<c:otherwise>
										<div id="next-button" class="next-button">
											<li><a class="page-button"
													onclick="reviewPaging('${reviewPi.currentPage+1}')">▶</a></li>
										</div>

									</c:otherwise>
								</c:choose>
							</div>

							<!--  	<div class="page-div">
						<div id="previous-button" class="prv-button">
							<li class="disabled"><a href="#" class="page-button">◀</a></li>
						</div>
						
						
						 <for문
						
							<div><li class="disabled"><a href="#" class="number">i</a></li></div>
						
						<div id="previous-button" class="next-button">
							<li class="disabled"><a href="#" class="page-button">▶</a></li>
						</div>
					</div>-->
						</div>

					</div>


				</div>

				<%@ include file="../common/footer.jsp" %>

			</body>

		</html>