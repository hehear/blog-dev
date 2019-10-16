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
		<main  class="main">
		<div class="main-inner" >
			<div class="content-wrap">
				
				<div id="categoryContent" class="content">
					<div id="category" v-cloak class="posts-expand">
						  <div class="post-block page">
							  <header class="post-header">
								<h1 class="post-title" itemprop="name headline">All categories</h1>
								  <div class="post-meta">
								</div>
							  </header>
							  <div class="post-body">
								<div class="category-all-page">
									 <div class="category-all-title">
									                                      目前共计 {{list.length}} 个分类
									  </div>
									  <div class="category-all">
									        <ul class="category-list" v-for="(info,i) in list">
									        	<li class="category-list-item">
									        	<a v-on:click="getArticleCategoriesDetail(info)" class="category-list-link" >{{info.articleTpNm}}</a>
									        	<span class="category-list-count">{{info.articleTpNo}}</span></li>
									        </ul>
									   </div>
									</div>
							   </div>
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
<c:import url="../article/articleContent.jsp" />
 <script src="${pageContext.request.contextPath}/js/bootstrap.js" type='text/javascript'></script>	
</body>
</html>
<script>

var vm_category = new Vue({

	el : '#category',
	data : {		
		article:{},
		 
		list:[]
	},
	methods :{
		
		getArticleCategoriesList : function(){
			
			$.ajax({
				url:"/articleType/getArticleCategoriesList",
				type:"get",
				data:{},
				success:function(rs){
					
	             	if(rs.code=="200" && rs.message == "success"){
	             		vm_category.list=rs.list;
                	}
					
				},
				error:function(rs){
					alert("出错了，大兄弟！");
				}
			})
		},
		getArticleCategoriesDetail : function(info){
			
			$.ajax({
				url:"/article/articleCategoriesList",
				type:"post",
				data:{},
				success:function(rs){
					
					$("#categoryContent").empty();
					$("#categoryContent").html(rs);
					$("#categoryDetail").show();
					vm_category_detail.articleType=info;
					vm_category_detail.list=info.articleList;
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

vm_category.getArticleCategoriesList();

</script>
