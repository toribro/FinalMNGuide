<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../common/common-file.jsp" %>
	<!-- <link rel="stylesheet" href="resources/css/community/community.css"/> -->
    <link rel="stylesheet" href="resources/css/community/shorts.css"/>
    <script src="resources/js/shortsEnroll/init.js"></script>
    <script src="resources/js/shortsEnroll/shortsEnroll.js"></script>
</head>

<%@ include file="../common/header.jsp"%>
<body onload="init('${loginUser.userNo}')" >
	<div class="wrapper">
        <div class="main shorts-main">
            <div class="community-area">
                <form action="${contextPath}/enroll.short" method="post" enctype="multipart/form-data">
                    <div class="row-box">
                        <div class="flex-box upload-container">
                            <div id="uploadVideo" class="community-area col-box upload-video">
                                <label for="videoInput" class="video-input">
                                    <h1>&#43; 동영상 업로드</h1>
                                    <video preload="metadata"  id="video"  class="video" src="resources/img/video.png" alt="동영상"></video>
                                    <div class="spacer"></div>
                                    <input id="videoInput" type="file" name="files" class="file-video" hidden>
                                </label>
                            </div>
                        </div>
                        <div class="thumbnail-container">
                            <div class="row-box header">
                                <h1>썸네일 선택</h1>
                            </div>
                            <div class="row-box thumbnail-box">
                                <div class="thumbnail-inner">
                                    <label id="Thumnail-background" for="Thunmailfile" class="community-area col-box thumbnail-label">
                                        <div class="thumbnail-input-container">
                                            <input id="Thunmailfile" type="file" name="files" class="file-thumnail" hidden>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <div class="row-box header">
                                <h1>세부내용 입력</h1>
                            </div>
                            <div class="textarea-container">
                                <textarea type="text" placeholder="내용을 입력하세요" name="shortsContent" class="textarea-input"></textarea>
                            </div>
                            <div class="right-box settings-container">
                                <div>
                                    <img src="resources/img/earth.png" alt="지구이미지" width="50px" height="50px">
                                </div>
                                <div class="spacer"></div>
                                <div>
                                    <select name="showRange" class="show-range">
                                        <option value="public" selected>공개</option>
                                        <option value="private">비공개</option>
                                    </select>
                                </div>
                                <div class="spacer"></div>
                                <div>
                                    <button class="common-button pink-button">쇼츠등록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
	
    <%@ include file="../common/footer.jsp"%>
</body>
</html>