<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<%@ include file="../common/common-file.jsp" %>
	<link rel="stylesheet" href="resources/css/community/community.css"/>
	<script src="resources/js/community/board/writingPageInit.js"></script>
	<script src="resources/js/community/board/writingPage.js"></script>
</head>
<body onload="init('${contextPath}')">
	<%@ include file="../common/header.jsp"%>
	<input type="hidden" id="contextPath" value="${contextPath}" />
    <input type="hidden" id="userNo" value="${empty loginUser.userNo ? 0 : loginUser.userNo}" />
	<div class="wrapper">
		<div class="main">
			<div class="community-area">
				<form action="enrollBoard.bo" method="POST" enctype="multipart/form-data">
					<div>
						<div>
							<select id="category" name="boardCategoryNo">
								<option value="" selected hidden>카테고리</option>
								<option value="1">건강/병원</option>
								<option value="2">식당/카페</option>
								<option value="3">여행/숙소</option>
								<option value="4">행사/테마파크</option>
								<option value="5">반려용품</option>
								<option value="6">잡담</option>
								<option value="7">Q&A</option>
							</select>
						</div>
						<div>
							<input type="text" name="boardTitle" placeholder="제목을 입력하세요" id="titleInput">
						</div>
						
					</div>

					<hr>
					
					<textarea id="summernote" name="boardContent"></textarea>

					<hr>

					<input type="file" name="upfile" style="display: none;" onchange="thumbnailImg(this)">
					<div class="flex-box" style="height: 100px; justify-content: space-between;">
						<div id="img-box">
							<img src="resources/img/default/defaultImg.png" alt="이미지등록" id="img-icon" style="cursor: pointer;" width="50" height="50" onclick="insertImg()">
							<span></span>
						</div>
						<button type="submit" class="common-button pink-button">게시글 등록</button>
					</div>
					
				</form>
			
				<script>
				//   $('#summernote').summernote({
				// 	placeholder: '내용을 입력하세요',
				// 	height: 500,
				// 	toolbar: [
				// 	['style', ['style']],
				// 	['font', ['bold', 'underline', 'clear']],
				// 	['color', ['color']],
				// 	['para', ['ul', 'ol', 'paragraph']],
				// 	['table', ['table']],
				// 	['insert', ['link', 'picture', 'video']],
				// 	['view', ['fullscreen', 'codeview', 'help']]
				// 	]
				//   });
				</script>
			</div>
		</div>
	</div>
	
    <%@ include file="../common/footer.jsp"%>
</body>
</html>