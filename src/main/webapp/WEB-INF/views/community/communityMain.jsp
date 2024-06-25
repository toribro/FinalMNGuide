<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
            <%@ include file="../common/common-file.jsp" %>
                <link rel="stylesheet" href="resources/css/community/community.css"/>
                <script src='resources/js/community/ajax/init.js'></script>
                <script src='resources/js/community/shorts/shorts.js'></script>
                <script src='resources/js/community/ajax/shortAjax.js'></script>
                <script src='resources/js/community/board/board.js'></script>
                <script src='resources/js/community/ajax/boardAjax.js'></script>
              

        </head>
         <%@ include file="../common/header.jsp"%>

        <body onload="init('${contextPath}')">
                <div class="wrapper community-wrap">
                    <div class="main community-main">

                        <div class="community-short-area">

                            <div class="title-box flex-box short-title-height">
                                <div class="minibox-title">쇼츠</div>
                                <div>
                                    <a href="${contextPath}/enrollshort.view"><button
                                            class="common-button pink-button">쇼츠 등록</button></a>
                                </div>
                            </div>


                            <div class="short-content">
                                <!--그리드-->
                                <div id="short-content" class="grid-box short-area">
                                    <c:forEach var="s" items="${shorts}">
                                         <div class="short-img-div" onclick="location.href='${contextPath}/shortsView.bo?shortNo=${s.shortsNo}'">
                                                <div class="short-img">
                                                    <img src="${s.attachment.filePath}${s.attachment.changeName}">
                                                    <div class="img-detail">
                                                        <div style="font-weight: bold; font-size:20px;">${s.userNo}
                                                        </div><br>
                                                        <div>${s.enrollDate}</div>
                                                        <div>${s.shortsContent}</div>
                                                    </div>
                                                </div>
                                            </div>
                                    </c:forEach>
                                </div>

                                <!--페이지 처리 영역-->
                                <div id="page-shorts-div" class="page-div">
                                    <c:choose>
                                        <c:when test="${shortsPi.currentPage eq 1}">
                                            <div id="previous-button" class="prv-button">
                                                <li class="page-disabled"><a class="page-button">◀</a></li>
                                            </div>
                                        </c:when>

                                        <c:otherwise>
                                            <div id="previous-button" class="prv-button">
                                                <li><a class="page-button"
                                                        onclick="pagIngShorts('${shortsPi.currentPage-1}')">◀</a>
                                                </li>
                                            </div>
                                        </c:otherwise>

                                    </c:choose>
                                    <!--페이징 처리(페이징 객체 받아올것)-->
                                    <c:forEach var="p" begin="${shortsPi.startPage }" end="${shortsPi.endPage }">
                                        <li class="page-item"><a class="page-link" onclick="pagIngShorts('${p}')">${p}</a></li>
                                    </c:forEach>

                                    <c:choose>
                                        <c:when test="${shortsPi.currentPage eq shortsPi.maxPage}">
                                            <div id="next-button" class="next-button"><li><a class="page-button">▶</a></li></div>
                                        </c:when>


                                        <c:otherwise>
                                            <div id="next-button" class="next-button">
                                                <li><a class="page-button"
                                                        onclick="pagIngShorts('${shortsPi.currentPage+1}')">▶</a>
                                                </li>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>


                            </div>
                        </div>

                        <div class="community-board-area">
                            <div class="title-box board-title-height">
                                <div class="flex-box">
                                    <div class="minibox-title">게시판</div>
                                    <div>
                                        <a href="${contextPath}/enrollBoard.bo"><button class="common-button pink-button">게시글 등록</button></a>
                                    </div>
                                </div>
                                <div id="category-box" class="flex-category-box">
                                    <div><button class="common-button white-button" data-categoryno='0'>전체 조회</button></div>
                                    <c:forEach var="bc" items="${boardCategory}">
                                        <div><button class="common-button white-button" data-categoryno='${bc.categoryNo}'>${bc.categoryName}</button></div>
                                    </c:forEach>
                                </div>
                            </div>
                            <!--그리드-->
                            <div id="board-content" class="board-content">
                                <!--게시판 영역-->
                                <c:forEach var="b" items="${boards}">
                                    <div class="grid-box board-area" onclick="location.href='${contextPath}/detailView.bo?bno=${b.boardNo}'">
                                        <div class="board-flex-div">
                                            <div class="content-box">
                                                <div class="profile">
                                                    <div class="img-div">
                                                        <img src="${b.userProfile.filePath}${b.userProfile.changeName}">
                                                    </div>
                                                    <div>${b.userNickName}</div>
                                                    <div>${b.createDate}</div>
                                                </div>
                                                <div class="content-title">
                                                    <span class="category" style="font-size:18px;
                                    font-weight:bold;">${b.categoryName}</span>&nbsp;&nbsp;
                                                    <span class="">${b.boardTitle}</span>
                                                </div>
                                                <div class="content">
                                                    ${b.boardContent}

                                                </div>
                                                <div class="reply-count">
                                                    <span><a href="#">조회수${b.count}</a></span>&nbsp;&nbsp;
                                                    <span><a href="#">댓글${b.replyCount}</a></span>
                                                </div>
                                            </div>
                                            <div class="img-box">
                                                <img src="${b.attachment.get(0).filePath}${b.attachment.get(0).changeName}">
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                            <!--페이지 처리 영역-->
                            <div id="page-board-div" class="page-div">
                                <c:choose>
                                    <c:when test="${boardPi.currentPage eq 1}">
                                        <div id="previous-board-button" class="prv-button">
                                            <li class="page-disabled"><a class="page-button">◀</a></li>
                                        </div>
                                    </c:when>

                                    <c:otherwise>
                                        <div id="previous-board-button" class="prv-button">
                                            <li><a class="page-button"
                                                    onclick="pagIngBoard('${boardPi.currentPage-1}')">◀</a>
                                            </li>
                                        </div>
                                    </c:otherwise>

                                </c:choose>
                                <!--페이징 처리(페이징 객체 받아올것)-->
                                <c:forEach var="p" begin="${boardPi.startPage}" end="${boardPi.endPage}">
                                    <li class="page-item"><a class="page-link" onclick="pagIngBoard('${p}')">${p}</a></li>
                                </c:forEach>

                                <c:choose>
                                    <c:when test="${boardPi.currentPage eq boardPi.maxPage}">
                                        <div id="next-board-button" class="page-disabled" class="next-button"><li><a class="page-button">▶</a></li></div>
                                    </c:when>


                                    <c:otherwise>
                                        <div id="next-board-button" class="next-button">
                                            <li><a class="page-button"
                                                    onclick="pagIngBoard('${boardPi.currentPage+1}')">▶</a>
                                            </li>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                             </div>
                         

                                <div class="board-search">
                                    <div class="category-div">
                                        <select class=" select-box" name="kind" id="kind" class="category-kind">
                                            <option  disabled>제목+본문</option>
                                            <c:forEach var="bc" items="${boardCategory}">
                                                <option value="${bc.categoryNo}" >${bc.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="search-div">
                                        <input id="board-input-content" class=" input-box" type="text">
                                        <button id="search-button" class="search-button" type="button">🔍</button>
                                    </div>
                                </div>

                    </div>
                </div>

                <%@ include file="../common/footer.jsp" %>

        </body>

        </html>