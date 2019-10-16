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
				
					 <div id="records" v-cloak class="post-block archive">
 						<div id="posts" class="posts-collapse">
					        <span class="archive-move-on"></span>
					        <span class="archive-page-counter">
					        	不错! 目前共计 {{list.length}} 篇日志。 继续努力。
					        </span>
							<section  v-for='(info,i) in list'  >
							       
							      <div v-if="i==0" class="collection-title">
							          <h1 class="archive-year" id="archive-year-2018">{{info.articleUpdtdt | yearFormatter}}</h1>
							      </div>
							       <div v-if="yearFormatter(info.articleUpdtdt) != yearFormatter(list[i-1<0?0:i-1].articleUpdtdt)" class="collection-title">
							          <h1 class="archive-year" id="archive-year-2018">{{info.articleUpdtdt | yearFormatter}}</h1>
							      </div>
								  
								  <article class="post post-type-normal" >
								    <header class="post-header">
								      <h2 class="post-title">
								         <a class="post-title-link"  >
								            <span itemprop="name" v-on:click="getArticleDetail(info)" >{{info.articleName}}</span>
								          </a>
								      </h2>
								      <div class="post-meta">
								        <time>{{info.articleUpdtdt | dateformatter}}</time>
								      </div>
								    </header>
							 	  </article>
							</section>	  
					
					</div>
					
			      </div>

					<div class="sidebar-toggle">
						<div class="sidebar-toggle-line-wrap">
							<span class="sidebar-toggle-line sidebar-toggle-line-first"></span>
							<span class="sidebar-toggle-line sidebar-toggle-line-middle"></span>
							<span class="sidebar-toggle-line sidebar-toggle-line-last"></span>
						</div>
					</div>
				      
					<!-- 页码 -->
					<div class="pagination pagination-rounded page">
						<div class="article_page" id="page_container"></div>
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
 <c:import url="../article/articleContent.jsp" />
 <script src="${pageContext.request.contextPath}/js/bootstrap.js" type='text/javascript'></script>	
</body>
</html>
<script>

var vm_records = new Vue({

	el : '#records',
	data : {		
		article:{},
		 
		list:[]
	},
	methods :{
		

		getArticleList : function(pageIndex){
			
			$.ajax({
				url:"/article/getByPage",
				type:"get",
				data:{pageIndex:pageIndex||1},
				success:function(rs){
					
	             	if(rs.code=="200" && rs.message == "success"){
                		
	                    console.log("执行");
	                    
	                    if(rs.page.pageNum!="0"){
	                    	
	                    	vm_records.list=rs.page.itemsData;

	                    	//生成分页
		                    $('#page_container').pagination({
		                    	
		                    	totalData:rs.page.pageTotal, 
		                    	showData:rs.page.pageSize, 
		                    	current:pageIndex,
		                    	coping:true,
		                    	callback:vm_records.getArticleList
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
					
					$("#content").empty();
					$("#content").html(rs);
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
    	},
		yearFormatter: function(time)
	       {
	    	var value=new Date(time);
            var year=value.getFullYear();
            return year;

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

	       },
	       yearFormatter: function(time)
	       {
		    	var value=new Date(time);
	            var year=value.getFullYear();
	            return year;

	       },
	       dateformatter: function(time)
	       {
		    	var value=new Date(time);
	            var year=value.getFullYear();
	            var month=(value.getMonth()+1)<10?'0'+(value.getMonth()+1):(value.getMonth()+1);
	            var day=(value.getDate())<10?'0'+(value.getDate()):(value.getDate());
	            return month+'-'+day;

	       },
	       
	}
});

vm_records.getArticleList(1);



</script>
