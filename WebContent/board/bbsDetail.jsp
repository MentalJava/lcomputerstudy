<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
	<table>
		<c:if test="${list != null}">
			<c:forEach var="comments" items="${list}">
    			 <tr>
					<td width="150">
						<div>${comments.c_userid}<br>
						<font size="2" color="lightgray">${comments.c_date}</font>
						</div>
					</td>
					<td width="550">
						<div>${comments.c_comments}</div>
					</td>
					<td width="80">
						<div id="btn" style="text-align:center;">
							<a href="#">답변</a>
							<a href="#" class="btnUpdateForm">수정</a>
							<a href="#">삭제</a>
						</div>
					</td>
				</tr>
				<tr style="display: none;">
					<td>
						<textarea rows="1" cols="80">${comments.c_comments}</textarea>
					</td>
					<td>
						<button type="button" class="btnUpdate">수정</button>
						<button type="button">취소</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
<form action="comments-comm-process.do" method="post">
<input type="hidden" name="b_id" value="${bbs.bbsID}">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="userid" size="10"></td>
		</tr>
	</table>
	<table>
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

<script>
$(document).on('click', '.btnUpdateForm', function () {
	$(this).parent().parent().parent().next().css('display', '');
});
</script>

</body>
</html>