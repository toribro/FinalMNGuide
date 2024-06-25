<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>잘못된 주소</title>
    <link rel="stylesheet" href="404.css">
    <style>
			body{
				font-family: 'Arial', sans-serif;
				background-color: #f4f4f4;
				color: #333;
				display: flex;
				justify-content: center;
				align-items: center;
				height: 100vh;
				margin: 0;
			}
			
			.container {
				text-align: center;
				padding: 2em;
				background: #fff;
				box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
				border-radius: 10px;
			}
			
			h1 {
				font-size: 6em;
				margin: 0;
				color: #ff6b6b;
			}
			
			p {
				font-size: 1.2em;
				margin: 20px 0;
			}
			
			a {
				text-decoration: none;
				color: #fff;
				background: #ff6b6b;
				padding: 10px 20px;
				border-radius: 5px;
				transition: background 0.3s;
			}
			
			a:hover {
				background: #ff4757;
			}
</style>
</head>
<body>
    <div class="container">
        <h1>잘못된 값</h1>
        <p>경로를 잘못 입력하셨습니다.</p>
        <a href="<%=request.getContextPath()%>">홈으로 돌아가기</a>
    </div>
</body>
</html>