<!DOCTYPE html>
<html class="theme-next gemini use-motion" lang="zh-Hans">

<body  lang="zh-Hans">
  <div id ="vm_left" class="leftpanel">
    <div class="logopanel">
        <h1><span>[</span> Management <span>]</span></h1>
    </div><!-- logopanel -->
    <div class="leftpanelinner">
        <!-- 浏览器缩小窗口时显示 strt -->
        <div class="visible-xs hidden-sm hidden-md hidden-lg">
            <div class="media userlogged">
                <img alt="" src="/images/photos/loggeduser.png" class="media-object">
                <div class="media-body">
                    <h4>John Doe</h4>
                    <span>"Life is so..."</span>
                </div>
            </div>
            <h5 class="sidebartitle actitle">Account</h5>
            <ul class="nav nav-pills nav-stacked nav-bracket mb30">
              <li><a href="profile.html"><i class="fa fa-user"></i> <span>Profile</span></a></li>
              <li><a href=""><i class="fa fa-cog"></i> <span>Account Settings</span></a></li>
              <li><a href=""><i class="fa fa-question-circle"></i> <span>Help</span></a></li>
              <li><a href="signout.html"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
            </ul>
        </div>
        <!-- 浏览器缩小窗口时显示 end-->
      <h5 class="sidebartitle">导航</h5>
      <ul class="nav nav-pills nav-stacked nav-bracket">
        <li class="active"><a href="/"><i class="fa fa-home"></i> <span>首页</span></a></li>
        <li class="nav-parent"><a href=""><i class="fa fa-edit"></i> <span>博客管理</span></a>
          <ul class="children">
            <li><a v-on:click="tomenu('article/list')" ><i class="fa fa-caret-right"></i> 博客列表</a></li>
            <li><a v-on:click="tomenu('article/articleAdd')"><i class="fa fa-caret-right"></i> 博客新增</a></li>
          </ul>
        </li>
        <li class="nav-parent"><a href=""><i class="fa fa-sort"></i> <span>博客类型管理</span></a>
              <ul class="children">
                  <li><a v-on:click="tomenu('articleType/')" ><i class="fa fa-caret-right"></i> 博客类型列表</a></li>
              </ul>
        </li>
          <li class="nav-parent"><a href=""><i class="fa fa-tag"></i> <span>博客标签管理</span></a>
              <ul class="children">
                  <li><a v-on:click="tomenu('tag/')" ><i class="fa fa-caret-right"></i> 博客标签列表</a></li>
              </ul>
          </li>

          <li class="nav-parent"><a href=""><i class="fa fa-tag"></i> <span>博客wiki管理</span></a>
              <ul class="children">
                  <li><a v-on:click="tomenu('wiki/')" ><i class="fa fa-caret-right"></i> 博客wiki列表</a></li>
                  <li><a v-on:click="tomenu('wiki/add')"><i class="fa fa-caret-right"></i> 博客wiki新增</a></li>
              </ul>
          </li>
      </ul>
      <div class="infosummary">
        <h5 class="sidebartitle">信息摘要</h5>
        <ul>
            <li>
                <div class="datainfo">
                    <span class="text-muted">日访问量</span>
                    <h4>630, 201</h4>
                </div>
                <div id="sidebar-chart" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">日评论量</span>
                    <h4>1, 332, 801</h4>
                </div>
                <div id="sidebar-chart2" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">博客数量</span>
                    <h4>82.2%</h4>
                </div>
                <div id="sidebar-chart3" class="chart"></div>
            </li>
        </ul>
      </div><!-- infosummary -->
    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->
  
</body>
<script src="/js/vue.min.js"></script>
<script>
	var vm_left = new Vue({
		el : '#vm_left', //必须写在body内的标签才起效！！
		data : {
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
			
			tomenu : function(url) {

			   //window.location.href=url;
				
 			   $.ajax({
					url : url,
					type : "post",
					data : {},
					success : function(result) { 
						$("#blog_content").empty();
						$("#blog_content").html(result);
						
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

			}); 

			}
		}
	});
</script>
</html>

	