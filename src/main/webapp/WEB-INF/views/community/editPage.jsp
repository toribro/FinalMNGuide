<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<%@ include file="../common/common-file.jsp" %>
	<link rel="stylesheet" href="resources/css/community/community.css"/>
	<script src="resources/js/community/board/editPageInit.js"></script>
	<script src="resources/js/community/board/editPage.js"></script>
</head>
<body onload="init('${contextPath}')">
	<%@ include file="../common/header.jsp"%>
	<input type="hidden" id="contextPath" value="${contextPath}" />
    <input type="hidden" id="userNo" value="${empty loginUser.userNo ? 0 : loginUser.userNo}" />
	<div class="wrapper">
		<div class="main">
			<div class="community-area">
				<form action="update.bo" method="POST" enctype="multipart/form-data">
					
					<input name="boardNo" value="${updateBoard.boardNo}"  hidden>
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
							<input type="text" name="boardTitle" placeholder="제목을 입력하세요" id="titleInput" value="${updateBoard.boardTitle}">
						</div>
						
					</div>

					<hr>
					
					<textarea id="summernote" name="boardContent">${updateBoard.boardContent}</textarea>

					<hr>

					<!--"${( not empty updateBoard.attachment)?updateBoard.attachment.get(0):''}"-->
					<input type="file" name="upfile" style="display: none;"  onchange="thumbnailImg(this)">

					<c:if test = "${!updateBoard.attachment.isEmpty()}">
					   <input  id="tempExistedFile" type="text"   value="${updateBoard.attachment.get(0).changeName}" hidden >
                       
					   <div id="existingFile">현재 업로드된 파일 : 
                      	 <a  href="${updateBoard.attachment.get(0).changeName}" download="${updateBoard.attachment.get(0).originName}">${updateBoard.attachment.get(0).originName}</a>
						&nbsp;<a id="existedRemove" style="cursor: pointer;" onclick="removeExistedFile()">삭제</a>
					   </div>

					   <input id="existedFileChange" type="text" name="existedFileChangeName"  value="none" hidden >
                    </c:if>

					<div class="flex-box" style="height: 100px; justify-content: space-between;">
						<div id="img-box">
							<img src="resources/img/default/defaultImg.png" alt="이미지등록" id="img-icon" style="cursor: pointer;" width="50" height="50" onclick="insertImg()">
							<span></span>
						</div>
						<button type="submit" class="common-button pink-button">게시글 수정</button>
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