<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>	
<form action="board-upload-test.do" method="post" enctype="multipart/form-data">
	<input type="text" name="teststr">
	<input type="file" name="file1">
	<input type="submit" value="저장">
</form>
</body>
</html>