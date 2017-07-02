<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
	<%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
	<div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
		<div class="panel panel-default qna-list">
			<ul class="list" id="ul_question_list">
				<!-- 질문 요약 리스트 --> 
			</ul>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6 text-center">
					<ul class="pagination center-block" style="display:inline-block;">
						<li><a href="#">«</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
					</ul>
				</div>
				<div class="col-md-3 qna-write">
					<a href="/question/form" class="btn btn-primary pull-right" role="button">질문하기</a>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/include/footer.jspf" %>

<script id="questionSummary" type="text/x-jquery-tmpl"> 
<li>
					<div class="wrap">
						<div class="main">
							<input type=hidden value="{{= questionId}}">
							<strong class="subject">
								<a href="/question/show/{{= questionId}}">{{= title}}</a>
							</strong>
							<div class="auth-info">
								<i class="icon-add-comment"></i>
								<span class="time">{{= createdDate}}</span>
								<a href="./user/profile.html" class="author">{{= writer}}</a>
							</div>
							<div class="reply" title="댓글">
								<i class="icon-reply"></i>
								<span class="point">8</span>
							</div>
						</div>
					</div>
				</li>
</script>

<script>
$(function() {
	$.ajax({
		url : '/question/summaries',
		type : 'GET',
		dataType : 'json',
		success : function(questionSummaries) {
			$.each(questionSummaries, function(index, questionSummary) {
				$("#questionSummary").tmpl([ {
					questionId : questionSummary.questionId,
					title : questionSummary.title,
					writer : questionSummary.writer,
					createdDate : questionSummary.createdDate
				} ]).appendTo("#ul_question_list");
			})
		},
		error : function(e) {
			alert("error");
		}
	});
});
</script>
</body>
</html>