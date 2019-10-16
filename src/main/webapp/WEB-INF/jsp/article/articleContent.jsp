<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="detail" class="posts-expand" hidden="hidden">
  <article class="post post-type-normal" >
  <div class="post-block" style="opacity: 1; display: block;">
      <header class="post-header" style="opacity: 1; display: block; transform: translateY(0px);">
          <h1 class="post-title" itemprop="name headline">{{article.articleName}}
          </h1>
        <div class="post-meta">
	        <span class="post-category" style="display:none">
				  <span class="post-meta-item-icon"><i class="fa fa-edit"></i></span> 
				  <a><span class="post-meta-item-text" v-on:click="update_article(article)">修改</span></a> 
				  <span class="post-meta-divider">|</span>
			</span>
			<span class="post-category" style="display:none"> 
				  <span class="post-meta-item-icon"><i class="fa fa-plus"></i></span> 
				  <a><span class="post-meta-item-text" v-on:click="add_article()">新增</span></a> 
				  <span class="post-meta-divider">|</span>
			</span>
			<span class="post-category" style="display:none"> 
				  <span class="post-meta-item-icon"><i class="fa fa-minus"></i></span> 
				  <a><span class="post-meta-item-text" v-on:click="delete_article(article)">删除</span></a> 
	        	<!-- <span class="post-meta-divider">|</span> -->
			</span>
	        <span class="post-time">
                <span class="post-meta-item-icon"><i class="fa fa-calendar-o"></i></span>
                <span class="post-meta-item-text">发表于</span>
                <time title="创建时间：2018-12-03 14:25:58" itemprop="dateCreated datePublished" datetime="2018-12-03T14:25:58+08:00">{{article.articleUpdtdt | timeformatter}}</time>
            </span>
            <span class="post-category">
               <span class="post-meta-divider">|</span>
                <span class="post-meta-item-icon"> <i class="fa fa-folder-o"></i></span>
                <span class="post-meta-item-text">分类于</span>
                <span ><a href="javascript:void(0)" ><span>{{article.articleTpNm}}</span></a></span>
            </span>
              <span class="post-comments-count">
                <span class="post-meta-divider">|</span>
                <span class="post-meta-item-icon"><i class="fa fa-comment-o"></i></span>
                <a>
                  <span class="post-meta-item-text">阅读数：</span> <span class="post-comments-count valine-comment-count" >{{article.articleClick||0}}</span>
                </a>
              </span>
        </div>
      </header>
    <div class="post-body" v-html="article.articleContent" style="opacity: 1; display: block; transform: translateY(0px);">

    </div>
      <div>
<ul class="post-copyright" style="text-align:left">
  <li class="post-copyright-author">
    <strong>本文作者： </strong>Running Coder</li>
  <li class="post-copyright-link">
    <strong>本文链接：</strong>
    <a href="javascript:void(0)" title="node http keep-alive demo">article.html</a>
  </li>
  <li class="post-copyright-license">
    <strong>版权声明： </strong>本博客所有文章除特别声明外，均采用 <a href="javascript:void(0)" rel="noopener" target="_blank"><i class="fa fa-fw fa-creative-commons"></i>BY-NC-ND</a> 许可协议。转载请注明出处！</li>
</ul>
      </div>
    <footer class="post-footer">
        <!-- <div class="post-tags">
            <a href="/tags/node/" rel="tag"># node</a>
            <a href="/tags/http/" rel="tag"># http</a>
        </div>  -->
        <div class="post-nav">
          <div class="post-nav-next post-nav-item">
              <a href="/" rel="next" >
                <i class="fa fa-chevron-left"></i> 
              </a>
          </div>
          <span class="post-nav-divider"></span>
          <div class="post-nav-prev post-nav-item">
          </div>
        </div>
    </footer>
  </div>
  </article>
  </div>
   <!-- 文章修改页面 -->
 <c:import url="articleUpdate.jsp" /> 
 <!-- 文章新增页面 -->
 <c:import url="articleAdd.jsp" /> 
<script>

var vm_detail = new Vue({

	el : '#detail',
	data : {		
		article:{},
		list:[],
		isLoaded:false
	},
	methods :{
	
		//阅读次数加一
		add_article_click:function(){
	    	   
		   vm_detail.article.articleUpdtdt=new Date();
	   	   $.ajax({
	   		   url:'/article/addArticleClick',
	   		   type:'post',
	   		   data:vm_detail.article,
	   		   success:function(rs){
		   			if(rs.code=="200" && rs.message == "success"){
		   				vm_detail.article=rs.record;
		   			}
	   		   },
	   		   error:function(){
	   			   alert("失败！")
	   		   }
	   	   })
	    },
		
	   //修改
       update_article:function(article){
    	   
    	   console.log('准备进入修改页面');
    	   
    	   vm_update.article=article;
    	   
    	   vm_update.sendMessage("showUpdateMessage");
           //弹出模态框
            $('#articleUpdateModal').modal({
 			   backdrop : 'static'
		    });
           
           
       },
       //新增
	   add_article:function(){
    	   
    	   console.log('准备进入新增页面');
    	   
    	  
           //弹出模态框
            $('#articleAddModal').modal({
 			   backdrop : 'static'
		    });
       },
       //删除
		delete_article:function(article){
			    	   
	   	   $.ajax({
	   		   url:'/article/delete',
	   		   type:'post',
	   		   data:{id:vm_detail.article.id},
	   		   success:function(){
	   			   alert("删除成功！");
	   			   location.reload(); 
	   		   },
	   		   error:function(){
	   			   alert("删除失败！")
	   		   }
	   	   })
	    	   
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

vm_add.isLoaded=false;


</script>
