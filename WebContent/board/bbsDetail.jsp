<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
ul {
		width:500px;
		height:50px;
		margin-left:-45px;
	 			
}

li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
}

.btn {
		background-color : white;
		margin-left : 0%;
		border : none;
		width : 70px;
		height : 50px;	
		opacity: 0.6;
  		transition: 0.3s;
}
.btn:hover {
		opacity: 1
}
</style>
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
			<td><a href="board-bbslist.do" class="btn">목록</a></td>
			<c:if test="${sessionScope.user ne null && sessionScope.user.u_idx eq bbs.bbsuser}">
				<td><a href="board-bbsedit.do?bbsid=${bbs.bbsID}" class="btn">수정</a></td>
				<td><a href="board-bbsdelete-process.do?bbsid=${bbs.bbsID}" class="btn">삭제</a></td>
				<td><a href="board-bbscontents.do?group=${bbs.bbsgroup}&order=${bbs.bbsorder}&depth=${bbs.bbsdepth}" class="btn">답글쓰기</a></td>
			</c:if>
		</tr>
	</table>
</div>
<div>
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
							<c:if test="${sessionScope.user ne null && sessionScope.user.u_idx eq bbs.bbsuser}">
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
						<button type="button" class="btnCancel">취소</button>
					</td>
				</tr>
			</c:forEach>
	</table>
	<div>
	<ul>
		<c:choose>
			<c:when test="${pagination.prevPage ge 1}">
				<li>
					<a href="board-bbsdetail.do?bbsid=${bbs.bbsID}&page=${pagination.prevPage}">◀</a>
				</li>
			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
					<c:when test="${pagination.page eq i}">		
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${pagination.page ne i}">
						<li>
							<a href="board-bbsdetail.do?bbsid=${bbs.bbsID}&page=${i}">${i}</a>						
						</li>
					</c:when>
				</c:choose>
		</c:forEach>
		<c:choose>
				<c:when test="${pagination.nextPage lt pagination.lastPage}">
					<li>
						<a href="board-bbsdetail.do?bbsid=${bbs.bbsID}&page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
		</c:choose> 
	</ul>
</div>
<form action="comments-comm-process.do" method="post">
<input type="hidden" name="b_id" value="${bbs.bbsID}">
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

$(document).on('click', '.btnCancel', function () {
	$(this).parent().parent().css('display', 'none');
});

$(document).on('click', '.btnUpdate', function () {
	let c_id = $(this).attr('c_id');
	let c_userid = $(this).attr('c_userid');
	let c_comments = $(this).parent().prev().find('textarea').val(); 
	let b_id = ${bbs.bbsID};
	
	$.ajax({
	  	method: "POST",
	  	url: "./aj-comment-update.do",
		data: { cid : c_id, c_uid : c_userid, com : c_comments, bbsid : b_id }
	})
  	.done(function( html ) {
  		$('#tblComments').html(html);
  	});
});

$(document).on('click', '.btnDelete', function () {
	let c_id = $(this).attr('c_id');
	let b_id = ${bbs.bbsID};
	
	$.ajax({
		method : "POST",
		url : "./aj-comment-delete.do",
		data : {cid : c_id, bbsid : b_id}
	})
	.done(function( html ) {
		$('#tblComments').html(html);
	});
});

$(document).on('click', '.btnReplyForm', function () {
	$(this).parent().parent().parent().next().next().css('display', '');
});

$(document).on('click', '.btnReply', function () {
	let cComments = $(this).parent().prev().find('textarea').val();
	let cGroup = $(this).attr('c_group');
	let cOrder = $(this).attr('c_order');
	let cDepth = $(this).attr('c_depth');
	let bid = ${bbs.bbsID};
	$.ajax({
		method : "POST",
		url : "./aj-comment-reply.do",
		data : {b_id : bid, c_comments : cComments, c_group : cGroup, c_order : cOrder, c_depth : cDepth}
	})
	.done(function(html) {
		$('#tblComments').html(html);
	});
});

</script>

</body>
</html>