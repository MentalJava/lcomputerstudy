<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 본문</title>
</head>
<body>

<form action="board-bbscontents-process.do" method="post">
		<table style="width:550px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="userid" size="50"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="contents"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="저장">&nbsp;&nbsp;<button type="button" onclick="location.href='board-bbslist.do'">목록</button></td>
			</tr>
		</table>
</form>				
</body>
</html>