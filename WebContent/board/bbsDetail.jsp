<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
<div>
<div>

	<table
		style="text-align:center; border:1px solid #dddddd">
		
		<thead>
			<tr>
				<th colspan="3"
					style="background-color: #eeeeee; text-align:center;">상세보기</th>
					
			</tr>
		</thead>
		<tbody>
		<tr>
			<th class="active">작성자</th>
			<td>
				${bbs.bbsUserID}
			</td>
		</tr>
		<tr>
			<th class="active">제목</th>
			<td>
				${bbs.bbsTitle}
			</td>
		</tr>
		<tr>
			<th class="active" >내용</th>
			<td>
				${bbs.bbsContents}
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="margin-left:1px;">
	<table>
		<tr>
			<td><a href="board-bbslist.do" class="btn btn-primary">목록</a></td>
			<td><a href="board-bbsedit.do?bbsid=${bbs.bbsID}" class="btn btn-primary">수정</a></td>
			<td><a href="board-bbsdelete-process.do?bbsid=${bbs.bbsID}" class="btn btn-primary">삭제</a></td>
			<td><a href="board-bbscontents.do?group=${bbs.bbsgroup}&order=${bbs.bbsorder}&depth=${bbs.bbsdepth}" class="btn btn-primary">답글쓰기</a>
		</tr>
	</table>
</div>
<div>
<form action="comments-comm-process.do" method="post">
<input type="hidden" name="b_id" value="${bbs.bbsID}">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="userid" size="50"></td>
		</tr>
		<tr>
			<td>댓글</td>
		</tr>
		<tr>
			<td><textarea name="comments"></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" value="저장"></td>
		</tr>
	</table>
</form>
</div>
<div>

</div>
</div>

</body>
</html>