<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
<div class="row" style="margin-bottom:20px; margin-left:1px;">
<div class="col-lg-12">
<h1 class="page-header">상세 페이지</h1>
</div>
</div>

<div class="panel" style="margin-left:1px;">
<div id="contAreaBox">
<div class="panel">
<div class="panel-body">
<div class="table-responsive" style="text-align:center;">
<table id="datatable-scroller"
	class="table table-bordered tbl_Form">
	<caption></caption>
	<colgroup>
		<col width="250px" />
		<col />
	</colgroup>
	<tbody>
		<tr>
			<th class="active" >작성자</th>
			<td>
			
			</td>
		</tr>
		<tr>
			<th class="active">제목</th>
			<td>
			
			</td>
		</tr>
		<tr>
			<th class="active" >내용</th>
			<td>
			
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="margin-left:1px;">
<a href="board-bbslist.do" class="btn btn-primary">목록</a>
<a href="board-bbsedit.do" class="btn btn-primary">수정</a>
<a href="board-bbsdelete.do" class="btn btn-primary">삭제</a>
</div>
</div>
</div>
</div>
</div>

</body>
</html>