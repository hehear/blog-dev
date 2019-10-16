<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="/images/favicon.png" type="image/png">

  <title>Bracket Responsive Bootstrap3 Admin</title>

  <link href="/css/style.default.css" rel="stylesheet">

</head>

<body class="signin">

<section>
  
    <div class="signinpanel">
        
        <div class="row" id="login">
            
            <div class="col-md-7">
                
                <div class="signin-info">
                    <div class="logopanel">
                        <h1><span>[</span> Management <span>]</span></h1>
                    </div><!-- logopanel -->
                
                    <div class="mb20"></div>
                
                    <h5><strong>Welcome to Bracket Bootstrap 3 Template!</strong></h5>
                    <ul>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> Fully Responsive Layout</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> HTML5/CSS3 Valid</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> Retina Ready</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> WYSIWYG CKEditor</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> and much more...</li>
                    </ul>
                    <div class="mb20"></div>
                    <strong>Not a member? <a href="signup.html">Sign Up</a></strong>
                </div><!-- signin0-info -->
            
            </div><!-- col-sm-7 -->
            
            <div class="col-md-5">
                
                <div >
                    <h4 class="nomargin">Sign In</h4>
                    <p class="mt5 mb20">Login to access your account.</p>
                
                    <input type="text" class="form-control uname" v-model="loginData.username" placeholder="Username" />
                    <input type="password" class="form-control pword" v-model="loginData.password" placeholder="Password" />
                    <a href=""><small>Forgot Your Password?</small></a>
                    <button class="btn btn-success btn-block" v-on:click="login(loginData)">Sign In</button>
                    
                </div>
            </div><!-- col-sm-5 -->
            
        </div><!-- row -->
        
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2018. All Rights Reserved. 
            </div>
            <div class="pull-right">
                Created By: <a href="http://www.runningcoder.top/" target="_blank">runningcoder</a>
            </div>
        </div>
        
    </div><!-- signin -->
  
</section>


<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/modernizr.min.js"></script>
<script src="js/jquery.sparkline.min.js"></script>
<script src="/js/jquery.cookies.js"></script>

<script src="/js/toggles.min.js"></script>
<script src="/js/retina.min.js"></script>

<script src="/js/custom.js"></script>
<script src="/js/vue.min.js"></script>
<script>
    jQuery(document).ready(function(){
        
        // Please do not use the code below
        // This is for demo purposes only
        var c = jQuery.cookie('change-skin');
        if (c && c == 'greyjoy') {
            jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
        } else if(c && c == 'dodgerblue') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        } else if (c && c == 'katniss') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        }
    });
</script>

<script type="text/javascript">


var vm = new Vue({
    el: '#login', //必须写在body内的标签才起效！！
    data: { 
    	 //登录信息
    	 loginData:{
    		 	username:'',
	        	password:''
	        }
        
    },
    methods: {
    	//登录
        login: function(loginData) {
        	
        	$.ajax({
				url : "/user/loginIn",
				type : "post",
				data : {"username":vm.loginData.username,"password":vm.loginData.password},
				success : function(result) { 
					
					if(result.code=="200"  && result.message == "success"){
						window.location="/";  //登录成功之后重定向到首页
					}else{
						alert("账号或密码不正确，登陆失败！");
					}
    			}
			});
        }
    },
    filters: {
    	//emptyTxt处理
        e:function (str) { return str || '暂无'; }
    }
});

</script>

</body>
</html>
