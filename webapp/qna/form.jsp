<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="kr">
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<%@ include file="/include/navigation.jspf"%>
	<c:set var ="writer"/>
	<c:choose>
       <c:when test="${not empty question.questionId}">
           <c:set var ="writer" value="${question.writer}"/>
       </c:when>
       <c:otherwise>
          <c:set var ="writer" value="${sessionScope.user.userId}"/>
       </c:otherwise>
   </c:choose>
						   
	<div class="container" id="main">
		<div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
			<div class="panel panel-default content-main">
				<form name="question" method="post" action="/question/create">
				
					<input id="question-id" type=hidden value="${question.questionId}">
					<div class="form-group">
						<label for="writer">작성자</label> <input class="form-control"
							id="writer" name="writer" placeholder="작성자"
							value="${writer}" />
					</div>
					<div class="form-group">
						<label for="title">제목</label> <input type="text"
							class="form-control" id="title" name="title" placeholder="제목"
							value="${question.title}" />
					</div>
					<div class="form-group">
						<label for="contents">내용</label>
						<textarea name="contents" id="contents" rows="5"
							class="form-control">${question.contents}</textarea>
					</div>
					<button type="submit" id="btn_submit_question"
						class="btn btn-success clearfix pull-right">등록</button>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/include/footer.jspf"%>
</body>
</html>