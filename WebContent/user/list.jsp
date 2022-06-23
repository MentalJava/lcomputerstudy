<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록2</title>
<style>
	ul {
		width:400px;
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
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
</head>
<body>
<h1>회원 목록</h1>
	<table>
		<tr>
			<td colspan="3">전체 회원수 : ${usercount}</td>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
		</tr>
		<c:forEach items= "${list}" var="item">
			<tr>
				<td><a href="user-detail.do?u_idx=${item.u_idx}">${item.u_idx}</a></td>
				<td>${item.u_id}</td>
				<td>${item.u_name}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<ul>
			<li><a href="user-list.do?page=1">1</a></li>
			<li><a href="user-list.do?page=2">2</a></li>
			<li><a href="user-list.do?page=3">3</a></li>
			<li><a href="user-list.do?page=4">4</a></li>
			<li><a href="user-list.do?page=5">5</a></li>
		</ul>
	</div>
</body>
</html>