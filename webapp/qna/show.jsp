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
    <div class="col-md-12 col-sm-12 col-lg-12">
        <div class="panel panel-default">
          <header class="qna-header">
              <h2 class="qna-title">${question.title}</h2>
          </header>
          <div class="content-main">
              <article class="article">
                  <div class="article-header">
                      <div class="article-header-thumb">
                          <img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
                      </div>
                      <div class="article-header-text">
                          <a class="article-author-name">${question.writer}</a>
                          <a class="article-header-time">${question.createdDate}</a>
                      </div>
                  </div>
                  <div class="article-doc">
                      <p>${question.contents}</p>
                  </div>
                  <div class="article-util">
                      <ul class="article-util-list">
                          <li>
                             <a class="link-modify-article" href="/question/form/${question.questionId}">수정</a>
                          </li>
                          <li>
                          	<a class="link-modify-article" href="/question/delete/${question.questionId}">삭제</a>
                          </li>
                          <li>
                             <a class="link-modify-article" href="/">목록</a>
                          </li>
                      </ul>
                  </div>
              </article>

              <div class="qna-comment">
                  <div class="qna-comment-slipp">
                      <p class="qna-comment-count"><strong>2</strong>개의 의견</p>
                      <div id="div_comment_list" class="qna-comment-slipp-articles">
						<c:forEach items="${answers}" var="answer">
		                    <article class="article" id="answer-1405">
		                              <div class="article-header">
		                              	<input type=hidden value="${answer.answerId}">
		                                  <div class="article-header-thumb">
		                                      <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
		                                  </div>
		                                  <div class="article-header-text">
		                                      <a class="article-author-name">${answer.writer}</a>
		                                      <a class="article-header-time">${answer.createdDate}</a>
		                                  </div>
		                              </div>
		                              <div class="article-doc comment-doc">
		                                  <p>${answer.contents}</p>
		                              </div>
		                              <div class="article-util">
		                                  <ul class="article-util-list">
		                                      <li>
		                                          <a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>
		                                      </li>
		                                      <li>
		                                          <form class="form-delete" action="/questions/413/answers/1405" method="POST">
		                                              <input type="hidden" name="_method" value="DELETE">
		                                              <button type="submit" class="link-delete-article">삭제</button>
		                                          </form>
		                                      </li>
		                                  </ul>
		                              </div>
		                          </article>
		                </c:forEach>
                      </div>
                      <form name="answer" class="submit-write">
                          	<input type=hidden name="questionId" value="${question.questionId}">
                          	<div class="form-group col-lg-4" style="padding-top:10px;">
                          	<input type="text" id="writer" name="writer" 
                          		value="<c:if test="${not empty sessionScope.user}">${sessionScope.user.userId}</c:if>">
                         	</div>
                              <div class="form-group col-lg-12" style="padding:14px;">
                                  <textarea id="contents" name="contents" class="form-control" placeholder="Update your status"></textarea>
                              </div>
                              <button id="btn_submit_answer" class="btn btn-success pull-right" type="button">댓글남기기</button>
                              <div class="clearfix"></div>
                          </form>
                  </div>
              </div>
          </div>
        </div>
    </div>
</div>

<%@ include file="/include/footer.jspf" %>
<script id="answer" type="text/x-jquery-tmpl"> 
<article class="article" id="answer-1405">
<div class="article-header">
	<input type=hidden value="{{= answerId}}">
    <div class="article-header-thumb">
        <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
    </div>
    <div class="article-header-text">
        <a class="article-author-name">{{= writer}}</a>
        <a class="article-header-time">{{= createdDate}}</a>
    </div>
</div>
<div class="article-doc comment-doc">
    <p>{{= contents}}</p>
</div>
<div class="article-util">
    <ul class="article-util-list">
        <li>
            <a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>
        </li>
        <li>
            <form class="form-delete" action="/questions/413/answers/1405" method="POST">
                <input type="hidden" name="_method" value="DELETE">
                <button type="submit" class="link-delete-article">삭제</button>
            </form>
        </li>
    </ul>
</div>
</article>
</script>
<script>
$( "#btn_submit_answer" ).on( "click", function(e) {
	e.preventDefault();
	var qeuryString = $("form[name=answer]").serialize();
	
	console.log(qeuryString);
	
	$.ajax({
		type: 'post',
		url: '/answer/create',
		data: qeuryString,
		dataType:  'json',
		success: function(answer) {
			console.log(answer);
			
			$("#answer").tmpl([ {
				answerId : answer.answerId,
				writer : answer.writer,
				createdDate : answer.createdDate,
				contents : answer.contents
			} ]).appendTo("#div_comment_list");
		},
		error : function(e) {
			alert("error");
		}
	});
});
</script>
</body>
</html>