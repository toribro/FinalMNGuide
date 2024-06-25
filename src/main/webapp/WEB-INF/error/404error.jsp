<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>잘못된 길</title>
    <link rel="stylesheet" href="404.css">
   <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            background: #f9f9f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 600px;
            padding: 2em;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        h1 {
            font-size: 3em;
            margin-bottom: 0.5em;
        }
        p {
            font-size: 1.2em;
            margin-bottom: 1em;
        }
        .btn-group a {
            display: inline-block;
            padding: 0.75em 1.5em;
            font-size: 1em;
            color: #fff;
            background-color: #007BFF;
            text-decoration: none;
            border-radius: 0.25em;
            transition: background-color 0.3s;
            margin: 0.5em;
        }
        .btn-group a:hover {
            background-color: #0056b3;
        }
        .img-container {
            margin-top: 2em;
        }
        .img-container img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>길을 잃으셨나요?</h1>
        <p>걱정 마세요! 새로운 모험을 떠날 시간입니다.</p>
        <div class="btn-group">
            <a href="<%=request.getContextPath()%>">홈으로 돌아가기</a>
            <a href="<%=request.getContextPath()%>/community">인기 페이지 보기</a>
        </div>
        <div class="img-container">
            <img src="resources/img/error/travelingDog.png" alt="Lost character in a maze">
        </div>
    </div>

</body>
</html>