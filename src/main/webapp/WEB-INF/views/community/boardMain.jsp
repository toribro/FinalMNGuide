<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../common/common-file.jsp" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/community.css"/>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	
    <div class="community-wrapper">
        <div class="tmp-box contents-box">
            <div class="tmp-box row-box" style="background-color: var(--border-color)">
                <div class="tmp-box"><h1>쇼츠</h1></div>
                <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo"><button class="white-button">더보기</button></a></div>
                <div class="tmp-box"><a href="<%=request.getContextPath()%>/enrollShorts.bo"><button class="pink-button">쇼츠 등록</button></a></div>
            </div>
            <div class="flex-box">
                <div class="row-box">
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                </div>
                <div class="row-box">
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                    <div class="tmp-box"><a href="<%=request.getContextPath()%>/shortsView.bo">썸네일</a></div>
                </div>
            </div>
        </div>
        <div class="tmp-box contents-box">
            <div class="tmp-box row-box" style="background-color: var(--border-color)">
                <div class="tmp-box"><h1>게시판</h1></div>
                <div class="tmp-box"><a href="<%=request.getContextPath()%>/enrollBoard.bo"><button class="pink-button">게시글 등록</button></a></div>
            </div>
            <div class="tmp-box row-box">
                <div class="tmp-box"><button class="category-button">전체글</button></div>
                <div class="tmp-box"><button class="white-button">건강병원</button></div>
                <div class="tmp-box"><button class="white-button">식당카페</button></div>
                <div class="tmp-box"><button class="white-button">여행숙소</button></div>
                <div class="tmp-box"><button class="white-button">행사테마파크</button></div>
                <div class="tmp-box"><button class="white-button">반려용품</button></div>
                <div class="tmp-box"><button class="white-button">잡담</button></div>
                <div class="tmp-box"><button class="white-button">Q&A</button></div>
            </div>
            <div>
                <div>
                    <div class="row-box">
                        <div>
                            <div class="row-box">
                                <div class="tmp-box">프사</div>
                                <div class="tmp-box">닉네임</div>
                                <div class="tmp-box">날짜</div>
                            </div>
                            <div class="row-box">
                                <div contenteditable="tmp-box">말머리</div>
                                <div class="tmp-box" style="color: var(--main-color);"><a href="<%=request.getContextPath()%>/detailView.bo">제목</a></div>
                            </div>
                        </div>
                        <div class="tmp-box">사진</div>
                    </div>
                    <div class="flex-box tmp-box">내용 미리보기</div>
                    <div>조회수 / 댓글</div>
                    <div class="tmp-box flex-box">1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 다음</div>
                </div>
            </div>
        </div>
        <div class="tmp-box contents-box">
            <select name="showFilter" style="border-radius: 15px;">
                <option value="nameAndContent" selected>제목 + 본문</option>
                <option value="nickname">닉네임</option>
            </select>
            <input type="text" placeholder="게시글 혹은 쇼츠를 검색하세요" style="border-radius: 15px;">
            <button>검색</button>
        </div>
    </div>
	
    <%@ include file="../common/footer.jsp"%>
</body>
</html>

