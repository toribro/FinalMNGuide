<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>안녕하세요</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            background: #333;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 600px;
            padding: 2em;
            background: rgba(0, 0, 0, 0.8);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            position: relative;
            overflow: hidden;
        }
        h1 {
            font-size: 3em;
            margin-bottom: 0.5em;
            color: #ff4747;
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
            animation: explode 1s infinite alternate;
        }
        @keyframes explode {
            0% { transform: scale(1); }
            100% { transform: scale(1.1); }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>오류</h1>
        <p>다시 시도해 주세요</p>
        <div class="btn-group">
            <a href="<%=request.getContextPath()%>">홈으로 돌아가기</a>
            <a href="javascript:location.reload()">새로고침</a>
        </div>
        <div class="img-container">
            <img src="resources/img/error/bomb.png" alt="Exploding bomb animation">
        </div>
    </div>
</body>
</html>
