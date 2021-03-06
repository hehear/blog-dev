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
        <li class="active"><a href="index.html"><i class="fa fa-home"></i> <span>首页</span></a></li>
        <li class="nav-parent"><a href=""><i class="fa fa-edit"></i> <span>博客管理</span></a>
          <ul class="children">
            <li><a v-on:click="tomenu('article/list')" ><i class="fa fa-caret-right"></i> 博客列表</a></li>
            <li><a v-on:click="tomenu('article/articleAdd')"><i class="fa fa-caret-right"></i> 博客新增</a></li>
<!--        <li><a href="form-validation.html"><i class="fa fa-caret-right"></i> Form Validation</a></li>
            <li><a href="form-wizards.html"><i class="fa fa-caret-right"></i> Form Wizards</a></li>
            <li><a href="wysiwyg.html"><i class="fa fa-caret-right"></i> Text Editor</a></li>
            <li><a href="code-editor.html"><i class="fa fa-caret-right"></i> Code Editor</a></li>
            <li><a href="x-editable.html"><i class="fa fa-caret-right"></i> X-Editable</a></li> -->
          </ul>
        </li>
        <li><a href="email.html"><span class="pull-right badge badge-success">2</span><i class="fa fa-envelope-o"></i> <span>Email</span></a></li>
        <li class="nav-parent"><a href=""><i class="fa fa-suitcase"></i> <span>UI Elements</span></a>
          <ul class="children">
            <li><a href="buttons.html"><i class="fa fa-caret-right"></i> Buttons</a></li>
            <li><a href="icons.html"><span class="pull-right badge badge-danger">updated</span><i class="fa fa-caret-right"></i> Icons</a></li>
            <li><a href="typography.html"><i class="fa fa-caret-right"></i> Typography</a></li>
            <li><a href="alerts.html"><i class="fa fa-caret-right"></i> Alerts &amp; Notifications</a></li>
            <li><a href="modals.html"><i class="fa fa-caret-right"></i> Modals</a></li>
            <li><a href="tabs-accordions.html"><i class="fa fa-caret-right"></i> Tabs &amp; Accordions</a></li>
            <li><a href="sliders.html"><i class="fa fa-caret-right"></i> Sliders</a></li>
            <li><a href="graphs.html"><i class="fa fa-caret-right"></i> Graphs &amp; Charts</a></li>
            <li><a href="widgets.html"><i class="fa fa-caret-right"></i> Panels &amp; Widgets</a></li>
            <li><a href="extras.html"><i class="fa fa-caret-right"></i> Extras</a></li>
          </ul>
        </li>
        <li><a href="tables.html"><i class="fa fa-th-list"></i> <span>Tables</span></a></li>
        <li class="nav-parent"><a href=""><i class="fa fa-bug"></i> <span>Bug Tracker</span></a>
            <ul class="children">
                <li><a href="bug-tracker.html"><i class="fa fa-caret-right"></i> Summary</a></li>
                <li><a href="bug-issues.html"><i class="fa fa-caret-right"></i> Issues</a></li>
                <li><a href="view-issue.html"><i class="fa fa-caret-right"></i> View Issue</a></li>
            </ul>
        </li>
        <li><a href="maps.html"><i class="fa fa-map-marker"></i> <span>Maps</span></a></li>
        <li class="nav-parent"><a href=""><i class="fa fa-file-text"></i> <span>Pages</span></a>
          <ul class="children">
            <li><a href="calendar.html"><i class="fa fa-caret-right"></i> Calendar</a></li>
            <li><a href="media-manager.html"><i class="fa fa-caret-right"></i> Media Manager</a></li>
            <li><a href="timeline.html"><i class="fa fa-caret-right"></i> Timeline</a></li>
            <li><a href="blog-list.html"><i class="fa fa-caret-right"></i> Blog List</a></li>
            <li><a href="blog-single.html"><i class="fa fa-caret-right"></i> Blog Single</a></li>
            <li><a href="people-directory.html"><i class="fa fa-caret-right"></i> People Directory</a></li>
            <li><a href="profile.html"><i class="fa fa-caret-right"></i> Profile</a></li>
            <li><a href="invoice.html"><i class="fa fa-caret-right"></i> Invoice</a></li>
            <li><a href="search-results.html"><i class="fa fa-caret-right"></i> Search Results</a></li>
            <li><a href="blank.html"><i class="fa fa-caret-right"></i> Blank Page</a></li>
            <li><a href="notfound.html"><i class="fa fa-caret-right"></i> 404 Page</a></li>
            <li><a href="locked.html"><i class="fa fa-caret-right"></i> Locked Screen</a></li>
            <li><a href="signin.html"><i class="fa fa-caret-right"></i> Sign In</a></li>
            <li><a href="signup.html"><i class="fa fa-caret-right"></i> Sign Up</a></li>
          </ul>
        </li>
        <li class="nav-parent"><a href="layouts.html"><i class="fa fa-laptop"></i> <span>Skins &amp; Layouts</span></a>
            <ul class="children">
                <li><a href="layouts.html"><i class="fa fa-caret-right"></i> General Layouts</a></li>
                <li><a href="horizontal-menu.html"><i class="fa fa-caret-right"></i> Top Menu</a></li>
                <li><a href="horizontal-menu2.html"><i class="fa fa-caret-right"></i> Top Menu w/ Sidebar</a></li>
                <li><a href="fixed-width.html"><i class="fa fa-caret-right"></i> Fixed Width Page</a></li>
                <li><a href="fixed-width2.html"><i class="fa fa-caret-right"></i> Fixed Width w/ Menu</a></li>
            </ul>
        </li>
      </ul>
      <div class="infosummary">
        <h5 class="sidebartitle">Information Summary</h5>
        <ul>
            <li>
                <div class="datainfo">
                    <span class="text-muted">Daily Traffic</span>
                    <h4>630, 201</h4>
                </div>
                <div id="sidebar-chart" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">Average Users</span>
                    <h4>1, 332, 801</h4>
                </div>
                <div id="sidebar-chart2" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">Disk Usage</span>
                    <h4>82.2%</h4>
                </div>
                <div id="sidebar-chart3" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">CPU Usage</span>
                    <h4>140.05 - 32</h4>
                </div>
                <div id="sidebar-chart4" class="chart"></div>
            </li>
            <li>
                <div class="datainfo">
                    <span class="text-muted">Memory Usage</span>
                    <h4>32.2%</h4>
                </div>
                <div id="sidebar-chart5" class="chart"></div>
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

	