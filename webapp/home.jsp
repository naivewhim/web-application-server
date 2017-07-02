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
			<ul class="list">
				 <c:forEach items="${questionSummaries}" var="questionSummary">
                   <li>
					<div class="wrap">
						<div class="main">
							<input type=hidden value="${questionSummary.questionId}">
							<strong class="subject">
								<a href="/qna/show/${questionSummary.questionId}">${questionSummary.title}</a>
							</strong>
							<div class="auth-info">
								<i class="icon-add-comment"></i>
								<span class="time">${questionSummary.createdDate}</span>
								<a href="./user/profile.html" class="author">${questionSummary.writer}</a>
							</div>
							<div class="reply" title="댓글">
								<i class="icon-reply"></i>
								<span class="point">8</span>
							</div>
						</div>
					</div>
				</li>

                </c:forEach>
			
				
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
					<a href="/qna/form" class="btn btn-primary pull-right" role="button">질문하기</a>
				</div>
			</div>
		</div>
	</div>
</div>

<script id="questionSummaries" type="text/x-jquery-tmpl"> 
    			
{{each resultList}}		
	
<li>
	<div class="wrap">
		<div class="main">
							<strong class="subject">
								<a href="./qna/show.html">${title}</a>
							</strong>
							<div class="auth-info">
								<i class="icon-add-comment"></i>
								<span class="time">2016-01-15 18:47</span>
								<a href="./user/profile.html" class="author">${writer}</a>
							</div>
							<div class="reply" title="댓글">
								<i class="icon-reply"></i>
								<span class="point">8</span>
							</div>
						</div>
					</div>
				</li>

		{{/each}}

</script>
<script src="/js/jquery/jquery.tmpl.min.js"></script>
<%@ include file="/include/footer.jspf" %>
<script>
$(function() {
	  
});
</script>
</body>
</html>