<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
table {
	margin-left:auto;
	margin-right:auto;
}

table td, th {
	border-collapse : collapse;
	border : 1px solid teal;
	text-align : center;
}

.button {
	background-color : white;
	margin-left : 60%;
	border : none;
	width : 70px;
	height : 50px;	
	opacity: 0.6;
  transition: 0.3s;
}

.button:hover {
	opacity : 1
}

ul {
		width:500px;
		height:50px;
		margin:10px auto;
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
</style>
</head>
<body>
<div>
    <table>
        <caption style="font-size : 35px">게시글 목록</caption>
        <colgroup>
            <col width="50px"/>
            <col width="200px"/>
            <col width="70px"/>
            <col width="100px"/>
            <col width="60px"/>
        </colgroup>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일자</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
        	 <c:forEach items= "${list1}" var="item" varStatus="status">
            	<tr>
            		<td>${item.rownum}</td>
            		<td><a href="board-bbsdetail.do?bbsid=${item.bbsID}">${item.bbsTitle}</a></td>
            		<td>${item.bbsUserID}</td>
            		<td>${item.bbsDate}</td>
            		<td>${item.bbsViews}</td>
            	</tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div>
	<ul>
		<c:choose>
			<c:when test="${pagination1.prevPage ge 1}">
				<li>
					<a href="board-bbslist.do?page=${pagination1.prevPage}">◀</a>
				</li>
			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${pagination1.startPage}" end="${pagination1.endPage}" step="1">
				<c:choose>
					<c:when test="${pagination1.page eq i}">		
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${pagination1.page ne i}">
						<li>
							<a href="board-bbslist.do?page=${i}">${i}</a>						
						</li>
					</c:when>
				</c:choose>
		</c:forEach>
		<c:choose>
				<c:when test="${ pagination1.nextPage lt pagination1.lastPage }">
					<li>
						<a href="board-bbslist.do?page=${pagination1.nextPage}">▶</a>
					</li>
				</c:when>
		</c:choose> 
	</ul>
</div>
<div>
		<button class="button" onclick="location.href='board-bbscontents.do'">글등록</button>
</div>
</body>
</html>