<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<button type="button" class="btnUpdate" c_id="${comments.c_userid}">수정</button>
			<button type="button">취소</button>
		</td>
	</tr>
</c:forEach>