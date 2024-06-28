<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>반려동물 AI 상담사</title>
    <%@ include file="../common/common-file.jsp"%>
    <link rel="stylesheet" href="resources/css/gpt/gpt.css">
    <script src="resources/js/gpt/gpt.js"></script>
</head>
<%@ include file="../common/header.jsp"%>
<body>
  
    <div class="wrapper user-chat-wrapper">
        <div class="main user-chat-main">

            <div id="app-container">
                <div id="title"></div>

                <div id="contents">
                    <div id="chat-display"></div>
                </div>

                <div id="footer">
                    <div id="search-bar-container">
                        <input type="text" id="search-input" placeholder="궁금한 것을 물어보세요.." />
                        <div id="upload-icon-box">
                            <span id="upload-icon">⬆️</span>
                        </div>
                    </div>
                    <p id="description-text">ChatGPT는 실수를 할 수 있습니다. 중요한 정보를 확인해 보세요.</p>
                </div>
            </div>
        </div>
     </div>

   
</body>
 <%@ include file="../common/footer.jsp"%>
</html>
