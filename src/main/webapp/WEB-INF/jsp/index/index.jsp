<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="theme-next gemini use-motion" lang="zh-Hans">
<head>
	<title>RunningCoder</title>
	<style> 
/* 	    .editormd-preview-close-btn {
	    display:none !important} */
	    
		.article_page li {             /*定义Li的类名为nav,可以取合法的任意名字*/
		 display: inline;     /*定义显示类型为一行显示,这里也可以写float:left */
		 padding: 10px; margin: 0;
		 }
    </style>
	<link href="images/logo.ico" rel="icon" type="image/x-ico">
</head>
<body  lang="zh-Hans" id="blogBody">
	<div id="index" >
	<div class="container sidebar-position-left  page-home" >
		<div class="headband"></div>

	    <!--header部分 Strt-->
 		<c:import url="../common/header.jsp" /> 
 		<!--header部分 end-->

		<!-- 主体部分 strt -->
		<main id="main" class="main">
		<div class="main-inner">
			<div class="content-wrap">
				<div id="title" hidden="hidden">
					<nav class="pagination" style="opacity: 1; display: block;">
						
				    </nav>
				</div>
				<div id="content" class="content">
					<div class="post-block page" style="opacity: 1; display: block;">
			      		<header class="post-header" style="opacity: 1; display: block; transform: translateY(0px);">
							<h1 class="post-title">首页</h1>
							<div class="post-meta">
							</div>
						</header>
					      <div class="post-body" style="opacity: 1; display: block; transform: translateY(0px); text-align:left;">
					      		<p><br/></p>
								<p><br/></p>
								<img src="${pageContext.request.contextPath}/images/160.png">
								<p><br/></p>
								<p><br/></p>
								<h2 id="版权声明"><a href="#版权声明" class="headerlink" title="版权声明"></a>版权声明</h2><p><a href="https://creativecommons.org/licenses/by-nc-nd/4.0/" target="_blank" rel="noopener">署名-非商业性使用-相同方式共享 4.0 国际 (CC BY-NC-SA 4.0)</a></p>
								<p><br/></p>
								<p><br/></p>

					      </div>
			    	</div>
				</div>
			</div>
		</div>
		</main>
		<!-- 主体部分 end -->

		<!-- footer部分 strt -->
		<c:import url="../common/footer.jsp" /> 
		<!-- footer部分 end -->

	</div>
   </div>
</body>

</html>
