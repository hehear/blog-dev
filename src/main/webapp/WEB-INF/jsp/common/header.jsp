<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="theme-next gemini use-motion" lang="zh-Hans">
<head>
    <style> /*配合v-cloak，解决闪屏问题*/
	    [v-cloak] {
	        display: none;
	    }
		.article_page li {             /*定义Li的类名为nav,可以取合法的任意名字*/
		 display: inline;     /*定义显示类型为一行显示,这里也可以写float:left */
		 padding: 10px; margin: 0;
		 }
		 
    </style>
	<!-- css -->
	<c:import url="../common/commoncss.jsp" /> 
</head>
<body  lang="zh-Hans">
	<div id ="vm_header" v-cloak>
		<header id="header" class="header">
			<div class="header-inner">
				<div class="site-brand-wrapper">
					<div class="site-meta ">
						<div class="custom-logo-site-title">
							<a href="index.htm" class="brand" rel="start"> <span
								class="logo-line-before"><i></i></span> <span class="site-title">RunningCoder</span>
								<span class="logo-line-after"><i></i></span>
							</a>
						</div>
						<p class="site-subtitle">学的不仅是技术，更是梦想！</p>
					</div>
					<div class="site-nav-toggle">
						<button aria-label="切换导航栏">
							<span class="btn-bar"></span> <span class="btn-bar"></span> <span
								class="btn-bar"></span>
						</button>
					</div>
				</div>
				<nav class="site-nav">
					<ul id="menu" class="menu">
						<li v-for="menu in menus" v-bind:class="menu.class">
							<a  v-on:click="tomenu(menu)" rel="section">
							<i v-bind:class="menu.icon"></i> <br>{{menu.menuNm}}</a>
						</li>
					</ul>
					<div class="site-search">
						<div class="popup search-popup local-search-popup">
							<div class="local-search-header clearfix">
								<span class="search-icon"> <i class="fa fa-search"></i>
								</span> <span class="popup-btn-close"> <i
									class="fa fa-times-circle"></i>
								</span>
								<div class="local-search-input-wrapper">
									<input autocomplete="off" placeholder="搜索..." spellcheck="false"
										type="text" id="local-search-input">
								</div>
							</div>
							<div id="local-search-result"></div>
						</div>
					</div>
				</nav>
			</div>
		</header>
		<div class="main-inner" id="scroll_header" >
			<div class="sidebar-toggle">
				<div class="sidebar-toggle-line-wrap">
					<span class="sidebar-toggle-line sidebar-toggle-line-first"></span>
					<span class="sidebar-toggle-line sidebar-toggle-line-middle"></span>
					<span class="sidebar-toggle-line sidebar-toggle-line-last"></span>
				</div>
			</div>
			<aside id="sidebar" class="sidebar">
				<div class="sidebar-inner">
					<section
						class="site-overview-wrap sidebar-panel sidebar-panel-active">
						<div class="site-overview">
							<div class="site-author motion-element" >
								<img class="site-author-image" src="${pageContext.request.contextPath}/images/160.png">
								<p class="site-author-name" >Running Coder</p>
								<p class="site-description motion-element">一个不会敲代码的程序猿</p>
							</div>
							<nav class="site-state motion-element">
								<div class="site-state-item site-state-posts">
									<a v-on:click="getArticleRecords()" >
										<span class="site-state-item-count">{{list.length}}</span> <span
										class="site-state-item-name">日志</span>
									</a>
								</div>
								<div class="site-state-item site-state-categories">
									<a v-on:click="getArticleCategories()" > <span
										class="site-state-item-count">{{typeList.length}}</span> <span
										class="site-state-item-name">分类</span>
									</a>
								</div>
								<div class="site-state-item site-state-tags">
									<a href="/"
										> <span
										class="site-state-item-count">32</span> <span
										class="site-state-item-name">标签</span>
									</a>
								</div>
							</nav>
							<div class="feed-link motion-element">
								<a href="atom.xml" 
									rel="alternate"> <i class="fa fa-rss"></i> RSS
								</a>
							</div>
							<div class="links-of-author motion-element">
								<span class="links-of-author-item"> <a
									
									title="Weibo &rarr; https://weibo.com/52cik" rel="noopener"
									target="_blank"><i class="fa fa-fw fa-weibo"></i></a>
								</span> <span class="links-of-author-item"> <a
									
									title="GitHub &rarr; https://github.com/52cik" rel="noopener"
									target="_blank"><i class="fa fa-fw fa-github"></i></a>
								</span> <span class="links-of-author-item"> <a
									href="mailto:cik520@qq.com"
									title="E-Mail &rarr; mailto:cik520@qq.com" rel="noopener"
									target="_blank"><i class="fa fa-fw fa-envelope"></i></a>
								</span>
							</div>
							<div class="cc-license motion-element" itemprop="license">
								<a
									
									class="cc-opacity" rel="noopener" target="_blank"><img src="${pageContext.request.contextPath}/images/cc-by-nc-nd.svg"
									alt="Creative Commons"></a>
							</div>
						</div>
					</section>
					<div class="back-to-top">
						<i class="fa fa-arrow-up"></i> <span id="scrollpercent"><span>0</span>%</span>
					</div>
				</div>
			</aside>
		</div>
	</div>
</body>
<!-- js -->
<c:import url="../common/commonjs.jsp" /> 
<script>
	var vm_header = new Vue({
		el : '#vm_header', //必须写在body内的标签才起效！！
		data : {
			menus : [{menuNm:"首页",icon:"menu-item-icon fa fa-fw fa-home",class:"menu-item menu-item-home menu-item-active",menuLnk:"/"},
			         {menuNm:"文章",icon:"menu-item-icon fa fa-fw fa-list",class:"menu-item menu-item-archives",menuLnk:"/article/list"},
/* 			         {menuNm:"分类",icon:"menu-item-icon fa fa-fw fa-book",class:"menu-item menu-item-archives",menuLnk:"/"}, */	
		             {menuNm:"归档",icon:"menu-item-icon fa fa-fw fa-archive",class:"menu-item menu-item-archives",menuLnk:"/article/records"},
			         {menuNm:"搜索",icon:"menu-item-icon fa fa-search fa-fw",class:"menu-item menu-item-archives",menuLnk:"/"},
			         {menuNm:"关于",icon:"menu-item-icon fa fa-fw fa-info",class:"menu-item menu-item-about",menuLnk:"/"}],
			list : [] ,
			typeList : [] ,
			page:{pageNum:'',pageTotal:''},
			addrs : [ "首页"]
		},
		mounted:function(){
		    console.log("页面加载完成");
		},
		methods : {
			
			getList : function(){
				
				$.ajax({
					url:"/article/getList",
					type:"post",
					data:{},
					success:function(rs){
						vm_header.list=rs.list;
					}
				})
			},
			getArticleType : function(){
				
				$.ajax({
					url:"/articleType/getArticleCategoriesList",
					type:"get",
					data:{},
					success:function(rs){
						
		             	if(rs.code=="200" && rs.message == "success"){
		             		vm_header.typeList=rs.list;
	                	}
						
					},
					error:function(rs){
						alert("出错了，大兄弟！");
					}
				})
			},
			
			getArticleRecords:function(){
				
				window.location.href="/article/records";
			},
			getArticleCategories:function(){
				
				window.location.href="/article/categories";
			},
			
			tomenu : function(menu) {

				window.location.href=menu.menuLnk;
/* 				$.ajax({
					url : menu.menuLnk,
					type : "post",
					data : {},
					success : function(result) { 
						$("#content").empty();
						$("#content").html(result);
					},
					error:function(result){
						if(result.status == "403"){
							$.error("无权限！");
						}else if(result.status == "404"){
							$.error("无此页面！");
						}else if(result.status == "500"){
							$.error("请求错误！");
						}else{
							$.error("出错啦，请联系管理员");
						}
						
					}

				}); */

			}
		}
	});
    window.onscroll=function(){
       //滚动的距离,距离顶部的距离
       var topScroll =document.body.scrollTop;
       //获取到导航栏id
       var bignav  = document.getElementById("scroll_header");
        if(topScroll > 325){  
        	//当滚动距离大于250px时执行下面的东西
            bignav.style.position = 'fixed';
            bignav.style.width = '20%';
            bignav.style.top = '-325';
            bignav.style.zIndex = '999';
        }else{
        	//当滚动距离小于250的时候执行下面的内容，也就是让导航栏恢复原状
        	bignav.style.width = '100%';
           bignav.style.position = 'static';
       }
    }
    vm_header.getList();
    vm_header.getArticleType();
</script>
</html>

	