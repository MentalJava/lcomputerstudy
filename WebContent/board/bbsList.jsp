<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
<div class="boardcss_list_table">
    <table class="list_table">
        <caption>게시글 목록</caption>
        <colgroup>
            <col />
            <col />
            <col />
            <col />
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
            		<td><a href="board-bbsdetail.do?bbsID=${item.bbsID}">${item.rownum}</a></td>
            		<td>${item.bbsTitle}</td>
            		<td>${item.bbsUserID}</td>
            		<td>${item.bbsDate}</td>
            		<td>${item.bbsViews}</td>
            	</tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div>
	<button type="button" onclick="location.href='board-bbscontents.do'">글등록</button>
</div>
</body>
</html>