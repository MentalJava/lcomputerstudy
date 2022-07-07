<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<h1>게시글 수정</h1>
<form action="board-bbsedit-process.do" method="post">
	<input type="hidden" name="bbsID" value="${bbs.bbsID}">
	<div>작성자 : </div>
	<div><input name="userid" value="${bbs.bbsUserID}"></div>
	<div>제목 :</div>
	<div><input name="title" value="${bbs.bbsTitle}"></div>
	<div>내용 :</div>
	<div><input name="contents" value="${bbs.bbsContents}"></div>
	
	<div>
		<input type="submit" class="btn btn-outline-info" value="완료">
		<input type="reset" class="btn btn-outline-info" value="리셋">
		<button type="button" onclick="location.href='board-bbslist.do'">목록</button>
	</div>
</form>
</body>
</html>