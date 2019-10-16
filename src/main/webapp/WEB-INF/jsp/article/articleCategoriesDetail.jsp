<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div id="categoryDetail" v-cloak class="post-block archive" hidden="hidden">
	     <!-- <div class="post-block category"> -->
   		    <div id="posts" class="posts-collapse">
		       <div class="collection-title">
		        	<h1>{{articleType.articleTpNm}}<small>分类</small></h1>
		      	</div>
				<section  v-for='(info,i) in list'  >
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
			<!-- </div> -->
		</div>
	</div>

 <!-- 文章内容页面 -->
 <c:import url="../article/articleContent.jsp" />
 <script src="${pageContext.request.contextPath}/js/bootstrap.js" type='text/javascript'></script>	
<script>

var vm_category_detail = new Vue({
	el : '#categoryDetail',
	data : {		
		article:{},
		articleType:{},
		 
		list:[]
	},
	methods :{

		getArticleDetail : function(info){
			
			$.ajax({
				url:"/article/content",
				type:"post",
				data:{article:info},
				success:function(rs){
					
					$("#categoryContent").empty();
					$("#categoryContent").html(rs);
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

</script>
