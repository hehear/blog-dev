<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>RunningCoder</title>
<style>
  [v-cloak] {
	        display: none;
	    }
	pre {
		background-color:#f9f2f4 !important;
	}
	pre code{
		color:#660 !important;
	}
	div,header,a {
	    opacity: 1 !important; 
	}
</style>
	<link href="${pageContext.request.contextPath}/images/logo.ico" rel="icon" type="image/x-ico">
</head>
<body>
	<div  class="container sidebar-position-left  page-home" >
		<div class="headband"></div>

	    <!--header部分 Strt-->
 		<c:import url="../common/header.jsp" /> 
 		<!--header部分 end-->

		<!-- 主体部分 strt -->
		<main id="main" class="main">
		<div class="main-inner" >
			<div class="content-wrap">
				<div id="title" hidden="hidden">
					<nav class="pagination" style="opacity: 1; display: block;">
				    </nav>
				</div>
				<div id="content" class="content">
					<div id="list" v-cloak class="posts-expand">
						<section id="posts"  class="posts-expand">
							<article v-for='(info,i) in list' class="post post-type-normal">
								<div class="post-block show">
									<header class="post-header">
										<h1 class="post-title" >
											<a href=""class="post-title-link"> {{info.articleName}}</a>
										</h1>
										<div class="post-meta">
											<span class="post-time"> 
												<span class="post-meta-item-icon"> 
													<i class="fa fa-calendar-o"></i>
												</span> 
												<span class="post-meta-item-text">发表于</span> 
												<time>{{info.articleUpdtdt | timeformatter}}
												</time>
											</span> 
											<span class="post-category"> 
												<span class="post-meta-divider">|</span> 
												<span class="post-meta-item-icon"> <i class="fa fa-folder-o"></i> </span> 
												<span class="post-meta-item-text">分类于</span> 
												<span><a href="javascript:void(0)" ><span>{{info.articleTpNm}}</span></a></span>
											</span> 
											<span class="post-comments-count"> <span class="post-meta-divider">|</span> 
												<span class="post-meta-item-icon"> <i class="fa fa-comment-o"></i></span> 
												<a href="javascript:void(0)"> 
													<span class="post-meta-item-text">阅读数：</span> 
													<span class="post-comments-count valine-comment-count">{{info.articleClick||0}}</span>
												</a>
											</span>
										</div>
									</header>
									<div class="post-body" >
										<p>
											{{info.articleShort}}
										</p>
										<!--noindex-->
										<div class="post-button text-center">
											<a class="btn" v-on:click="getArticleDetail(info)" rel="contents"> 阅读全文 &raquo; </a>
										</div>
										<!--/noindex-->
									</div>
									<footer class="post-footer">
											<div class="post-eof"></div>
									</footer>
								</div>
							</article>
											
							<div class="sidebar-toggle">
								<div class="sidebar-toggle-line-wrap">
									<span class="sidebar-toggle-line sidebar-toggle-line-first"></span>
									<span class="sidebar-toggle-line sidebar-toggle-line-middle"></span>
									<span class="sidebar-toggle-line sidebar-toggle-line-last"></span>
								</div>
							</div>
						</section>
	
						<!-- 页码 -->
						<div class="pagination pagination-rounded page">
							<div class="article_page" id="page_container"></div>
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

 <!-- 文章内容页面 -->
 <c:import url="articleContent.jsp" /> 
 <script src="${pageContext.request.contextPath}/js/bootstrap.js" type='text/javascript'></script>	
</body>
</html>
<script>

var vm_list = new Vue({

	el : '#list',
	data : {		
		article:{},
		 
		list:[]
	},
	methods :{
		
		getArticle:function(){
			var art='${article}';
			vm_list.article=art;
		},

		getArticleList : function(pageIndex){
			
			$.ajax({
				url:"/article/getByPage",
				type:"get",
				data:{pageIndex:pageIndex||1},
				success:function(rs){
					
	             	if(rs.code=="200" && rs.message == "success"){
                		
	                    console.log("执行");
	                    
	                    if(rs.page.pageNum!="0"){
	                    	
	                    	vm_list.list=rs.page.itemsData;
		                    //生成分页
		            	   
		                    $('#page_container').pagination({
		                    	
		                    	totalData:rs.page.pageTotal, 
		                    	showData:rs.page.pageSize, 
		                    	current:pageIndex,
		                    	coping:true,
		                    	callback:vm_list.getArticleList
		                    	})
		                    
		                    
	                    } else {
	                    	$.info("没有相关数据");
	                    }
                	}
	             	
					
				},
				error:function(rs){
					alert("出错了，大兄弟！");
				}
			})
		},
		getArticleDetail : function(info){
			
			$.ajax({
				url:"/article/content",
				type:"post",
				data:{article:info},
				success:function(rs){
					
					$("#list").empty();
					$("#list").html(rs);
					$("#detail").show();
					vm_detail.article=info;
					vm_detail.add_article_click();
				},
				error:function(rs){
					alert("出错了，大兄弟！");
				}
			})
		},
       padDate:function(va){
    	     va=va<10?'0'+va:va;
    	     return va
    	}

	},
	filters:{
		
	     //时间戳转时间
	      timeformatter: function(time)
	       {
		    	var value=new Date(time);
	            var year=value.getFullYear();
	            var month=(value.getMonth()+1)<10?'0'+(value.getMonth()+1):(value.getMonth()+1);
	            var day=(value.getDate())<10?'0'+(value.getDate()):(value.getDate());
	            /* var hour= (value.getHours())<10?'0'+(value.getHours()):(value.getHours());
	            var minutes=(value.getMinutes())<10?'0'+(value.getMinutes()):(value.getMinutes());
	            var seconds=(value.getSeconds())<10?'0'+(value.getSeconds()):(value.getSeconds()); 
	            return year+'-'+month+'-'+day+' '+hour+':'+minutes+':'+seconds;*/
	            return year+'-'+month+'-'+day;

	       }
	}
});

vm_list.getArticleList(1);



</script>
