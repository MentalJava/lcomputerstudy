<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table id="tblComments">
			<c:forEach var="comments" items="${list}">
    			 <tr>
					<td width="150">
						<div>	
							<c:if test="${comments.c_depth > 0}">
								<c:forEach begin="1" end="${comments.c_depth}">
									&nbsp;&nbsp;
								</c:forEach>
								┖
							</c:if>${comments.c_userid}<br>
						<font size="2" color="lightgray">${comments.c_date}</font>
						</div>
					</td>
					<td width="550">
						<div>${comments.c_comments}</div>
					</td>
					<td width="80">
						<div class="btn" style="text-align:center;">
							<c:if test="${sessionScope.user ne null}">
								<a href="#" class="btnReplyForm">답변</a>
								<a href="#" class="btnUpdateForm">수정</a>
								<a href="#" class="btnDelete" c_id="${comments.c_id}">삭제</a>
							</c:if>
						</div>
					</td>
				</tr>
				<tr style="display: none;">
					<td>
						<textarea rows="1" cols="80">${comments.c_comments}</textarea>
					</td>
					<td>
						<button type="button" class="btnUpdate" c_userid="${comments.c_userid}" c_id="${comments.c_id}">수정</button>
						<button type="button" class="btnCancel">취소</button>
					</td>
				</tr>
				<tr style="display:none;">
					<td>	
						<textarea rows="1" name="c_comments" cols="80"></textarea>
					</td>
					<td>
						<button type="button" class="btnReply" c_group="${comments.c_group}" c_order="${comments.c_order}" c_depth="${comments.c_depth}">저장</button>
						<button type="button" class="btncancle">취소</button>
					</td>
				</tr>
			</c:forEach>
	</table>