<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../common/common-file.jsp" %>
	<link rel="stylesheet" href="resources/css/community/community.css"/>
	<link rel="stylesheet" href="resources/css/community/boardContent.css"/>
    <script src='resources/js/boardShow/init.js'></script>
    <script src='resources/js/boardShow/reply.js'></script>
    <script src='resources/js/boardShow/board.js'></script>
    <script src='resources/js/boardShow/ajax/replyAjax.js'></script>
    <script src='resources/js/boardShow/ajax/boardAjax.js'></script>
</head>
    <%@ include file="../common/header.jsp"%>

<body onload="init('${contextPath}','${loginUser.userNickname}','${loginUser.userNo}','${userProfile.filePath}','${userProfile.changeName}')">
	
	<div class="wrapper">
        <div class="main">
            <div class="gray-round-box">
                <!-- 타이틀 있는 헤더 -->
                <div>
                    <div id="board-header-title">
                        <div>
                            <span id="board-title-text">${board.boardTitle}</span>
                            <span id="board-category-text">${board.categoryName}</span>
                        </div>
                        <div class="change-button-box">
                            <img src="resources/community/threeCircle.png" alt="">
                            <c:if test="${loginUser!=null && (loginUser.userNo eq board.userNo )}">
                                <div class="change-box">
                                    <div class="change-box-list" onclick="update()" style="cursor: pointer;">수정하기</div>
                                    <div class="change-box-list change-box-last"  onclick="deleteBoard()" style="cursor: pointer;">삭제하기</div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div id="board-header-info">
                        <div>
                            <img src="${board.userProfile.filePath}${board.userProfile.changeName}" alt="">
                            <span>${board.userNickName}</span>
                        </div>
                        <span>조회수 ${board.count}</span>
                        <span>등록일 ${board.createDate}</span>
                    </div>
                </div> 

                <!-- 글 내용 및 사진 영역 -->
                <div id="board-content-box">
                    <div id="board-content-text">
                        <c:forEach var="i" items="${board.attachment}">
                            <img src="${i.filePath}${i.changeName}" alt="">
                        </c:forEach>

                        <img src="resources/community/userProfile.jpg" alt="">
                        <div>${board.boardContent}</div>
                    </div>
                    <div id="board-communication-box">
                        <div class="board-communication-info">
                            <!-- 좋아요 여부에 따라 처리 필요 -->
                            <img src="resources/community/like-after.png" alt="">
                            <span id="good" style="cursor: pointer;">좋아요 </span><span id="goodCount">${board.goodCount}</span>
                        </div>
                        <div class="board-communication-info">
                            <img src="resources/community/reply.png" alt="">
                            <span>댓글 ${board.replyCount}</span>
                        </div>
                    </div>
                </div>

                <!-- 댓글 리스트 -->
                <div id="board-reply-title">댓글</div>
                 

                        <div id="board-reply-list">
                            <c:forEach   var="r"  items="${board.replys}">

                            <!-- 로그인한 유저가 작성한 본인 댓글 -->
                                    <div class="board-reply-box">
                                        <div class="board-reply-profile">
                                            <img src="${r.replyUserProfile.filePath}${r.replyUserProfile.changeName}"alt="">
                                        </div>
                                        
                                        <div class="board-reply-content">
                                            <div class="reply-title-box">
                                                <div>
                                                    <span class="reply-user">${r.userNickName}</span>
                                                    <span class="reply-date">${r.createDate}</span>
                                                    <button  type="button" onclick="replyShowInsert('${r.replyNo}')">답글 달기</button>
                                                </div>
                                                
                                                <div class="change-button-box">
                                                    <img src="resources/community/threeCircle.png" alt="">
                                                    <c:if test="${loginUser!=null && (loginUser.userNo eq r.userNo )}">
                                                        <div class="change-box">
    
                                                            <div id="deleteReply${r.replyNo}" onclick="replyDelete('${r.replyNo}')"><a style="cursor: pointer;">삭제하기</a></div>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <div class="reply-content">${r.content}</div>
                                        </div>

                                    </div>


                                <!-- 대댓글 형식 -->
                                    <c:forEach   var="rr"  items="${r.replyReply}">
                                        <div class="board-reply-box re-reply-width">
                                                <div class="board-reply-profile"><img src="${rr.replyUserProfile.filePath}${rr.replyUserProfile.changeName}" alt=""></div>
                                                <div class="board-reply-content">
                                                    <div class="reply-title-box">
                                                        <div>
                                                            <span class="reply-user">${rr.userNickName}</span>
                                                            <span class="reply-date">${rr.createDate}</span>
                                                         
                                                        </div>
                                                        <c:if test="${loginUser!=null && (loginUser.userNo eq rr.userNo )}">
                                                            <div class="change-box">
                                                                <div id="deleteReply${rr.replyNo}" onclick="replyDelete('${rr.replyNo}')"><a style="cursor: pointer;">삭제하기</a></div>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="reply-content">${rr.content}</div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                   


                                <!-- 답글 달기 클릭 시 나오는 input 창 -->
                                    <div id="reply-content${r.replyNo}" style="display:none;" class="re-reply-width board-reply-box">
                                       
                                            <div class="reply-regist-info">
                                                <div>
                                                    <img src="${userProfile.filePath}${userProfile.changeName}" alt="">
                                                    <span>${(loginUser!=null)?loginUser.userNickname:'로그인하세요'}</span>
                                                </div>
                                            </div>
                                            <div class="input-reply-div">
                                                <div class="replytext-div"><textarea id="replyReplyText${r.replyNo}" class="reply-textarea gray-round-box" placeholder="댓글을 입력하세요."></textarea></div>
                                            </div>
                                            <div  class="replybutton-div"><button  type="button"id="replyReplyButton" class="common-button pink-button" onclick="replyReplyInsert('${r.replyNo}')">답글 등록</button></div>
                                     
                                    </div>
                            </c:forEach>


                        </div>

                        <!--페이지 처리 영역-->
							<div id="page-div" class="page-div">
								<c:choose>
									<c:when test="${replyPi.currentPage eq 1}">
										<div id="previous-button" class="prv-button">
											<li class="page-disabled"><a class="page-button">◀</a></li>
										</div>
									</c:when>

									<c:otherwise>
										<div id="previous-button" class="prv-button">
											<li><a class="page-button"
													onclick="replyPaging('${replyPi.currentPage-1}')">◀</a></li>
										</div>

									</c:otherwise>
								</c:choose>

								<c:forEach var="p" begin="${replyPi.startPage }" end="${replyPi.endPage }">
									<li class="page-item"><a class="page-link" onclick="replyPaging('${p}')">${p}</a>
									</li>
								</c:forEach>

								<c:choose>
									<c:when test="${replyPi.currentPage eq replyPi.maxPage}">
										<div id="next-button" class="next-button">
											<li class="page-disabled"><a class="page-button">▶</a></li>
										</div>
									</c:when>

									<c:otherwise>
										<div id="next-button" class="next-button">
											<li><a class="page-button"
													onclick="replyPaging('${replyPi.currentPage+1}')">▶</a></li>
										</div>

									</c:otherwise>
								</c:choose>
							</div>
               

                <!-- 댓글 등록란 -->
                <div id="board-reply-regist">
                   
                        <div class="reply-regist-info">
                            <div>
                                <img src="${userProfile.filePath}${userProfile.changeName}" alt="">
                                <span>${(loginUser!=null)?loginUser.userNickname:'로그인하세요'}</span>
                            </div>
                            <button  type="button" id="replyButton" class="common-button pink-button">댓글 등록</button>
                        </div>
                        <textarea name="replyContent" id="replyText" class="reply-textarea gray-round-box" placeholder="댓글을 입력하세요."></textarea>
                    
                </div>
            </div>

                

            </div>

            <!-- The Modal -->
            <div class="modal" id="delete-content">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!-- Modal body -->
                        <div class="modal-body" id="delete-text">
                            <div class="minibox-title">
                                해당 글을 삭제하시겠습니까?
                            </div>
                            
                            <button class="common-button pink-button">삭제</button>
                            <button class="common-button white-button" data-dismiss="modal">취소</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- <div class="tmp-box">
                <div class="row-box">
                    <div class="tmp-box"><h1>제목</h1></div>
                    <div class="tmp-box">말머리</div>
                    <div class="tmp-box">⋮</div>
                </div>
                <div class="row-box">
                    <div class="tmp-box">프사</div>
                    <div class="tmp-box">조회수</div>
                    <div class="tmp-box">등록일</div>
                </div>
            </div>

            <div class="tmp-box">
                <div class="tmp-box">사진</div>
                <div>글 내용</div>
                <div class="row-box">
                    <div class="tmp-box">좋아요 아이콘</div>
                    <div class="tmp-box">좋아요 n</div>
                    <div class="tmp-box">댓글 아이콘</div>
                    <div class="tmp-box">댓글 n</div>
                </div>
            </div>

            <div class="tmp-box">
                <div class="row-box">
                    <div class="tmp-box">프사</div>
                    <div class="tmp-box">닉네임</div>
                    <div class="tmp-box">시간</div>
                    <div class="tmp-box">답글달기</div>
                    <div class="tmp-box">⋮</div>
                </div>
                <div>댓글 내용</div>
            </div>
            <hr>
            <div class="tmp-box">
                <div class="row-box">
                    <div class="tmp-box">프사</div>
                    <div class="tmp-box">닉네임</div>
                    <div class="tmp-box">시간</div>
                    <div class="tmp-box">답글달기</div>
                    <div class="tmp-box">⋮</div>
                </div>
                <div>댓글 내용</div>
            </div>
            <hr>
            <div class="tmp-box">
                <div class="row-box">
                    <div class="tmp-box">프사</div>
                    <div class="tmp-box">닉네임</div>
                    <div class="tmp-box">시간</div>
                    <div class="tmp-box">답글달기</div>
                    <div class="tmp-box">⋮</div>
                </div>
                <div>댓글 내용</div>
            </div>
            <hr>
            <div>
                <div class="row-box">
                    <div class="tmp-box">아이콘</div>
                    <div class="tmp-box">닉네임</div>
                    <div class="tmp-box"><button>댓글 등록</button></div>
                </div>
                <input type="text" placeholder="댓글을 작성하세요.">
            </div> -->
        </div>
    </div>
	
    <%@ include file="../common/footer.jsp"%>
</body>
</html>