<!DOCTYPE html>
  <!-- 主页导航头部strt -->
  <div class="pageheader">
    <h2><i class="fa fa-file-text"></i> 博客修改 <span>To write a new blog..</span></h2>
    <div class="breadcrumb-wrapper">
      <span class="label">You are here:</span>
      <ol class="breadcrumb">
        <li><a href="index.html">Bracket</a></li>
        <li><a href="index.html">Pages</a></li>
        <li class="active">Blog Update</li>
      </ol>
    </div>
  </div>
	<!-- 主页导航头部end -->
	  <!-- 页面主体 strt -->
  <div class="contentpanel">
   <div id="update" class="row">
   
       <!-- <div class="col-md-6"> -->
          <div class="panel panel-default">

            <div class="panel-body">
                <div class="form-group">
                  <label class="col-sm-1 control-label">标题  <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <input type="text" v-model="article.articleName" class="form-control"  placeholder="输入博客标题..." required />
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label">分类 <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <select v-model="article.articleTpId"  style="width:100%;height:40px;" placeholder="选择分类">
					    <option :value="articleTp.articleTpId" :label="articleTp.articleTpNm"  v-for="articleTp in articleTpList" ></option>                                    
                    </select>
                    <label class="error" for="fruits"></label>
                  </div>
                </div><!-- form-group -->

                <div class="form-group">
                  <label class="col-sm-1 control-label">标签 <span class="asterisk">*</span></label>
                  <div class="col-sm-9" >

                    <div v-for='(info,i) in tagList' style="display: inline">
                      <input type="checkbox" :value="info.tagId" v-model="article.articleTags">

                      <label >{{info.tagNm}}  </label>

                    </div>

                  </div>
                </div><!-- form-group -->

                <div class="form-group">
                  <label class="col-sm-1 control-label">简介 <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <textarea rows="5" v-model="article.articleShort" class="form-control" placeholder="输入简介..." required></textarea>
                  </div>
                </div>

              <div class="form-group">
                <label class="col-sm-1 control-label">关键字 <span class="asterisk">*</span></label>
                <div class="col-sm-9">
                  <input type="text" v-model="article.articleKeywords"  class="form-control"  />
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-1 control-label">HTML内容 <span class="asterisk">*</span></label>
                <div class="col-sm-9">
                  <textarea rows="5" v-model="article.articleContent" class="form-control" placeholder="输入HTML内容..." required></textarea>
                </div>
              </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label">内容 <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <iframe :src="editormd_html" ref="iframe" style="width: 100%; height: 640px; margin-bottom: 15px; position: relative;"></iframe>
                  </div>
                </div>
                
                
              </div><!-- panel-body -->
              <div class="panel-footer">
                <div class="row">
                  <div class="col-sm-9 col-sm-offset-1">
                    <button class="btn btn-primary" v-on:click="sendMessage('sendUpdateMessage')" >Submit</button>
                    <button type="reset" class="btn btn-default">Reset</button>
                  </div>
                </div>
              </div>
            
          </div><!-- panel -->
          
          
        <!-- </div>col-md-6 -->
   
      </div><!-- row -->
  </div>
  <!-- 页面主体 end -->	
	
<script src="/editormd/js/editormd.js" type="text/javascript" ></script> 
<script src="/editormd/lib/marked.min.js" type="text/javascript" ></script>
<script src="/editormd/lib/prettify.min.js" type="text/javascript" ></script> 	
<script src='/js/vue.min.js' type='text/javascript'></script>	
<script>

var vm_update = new Vue({

	el : '#update',
	data : {		
		 
		updtEditor:{},
		article:{id:'${id?js_string}',articleName:'${articleName?js_string}',articleShort:'${articleShort?js_string}',articleContent:'${articleContent?js_string}'
			,articleContentMarkdown:'${articleContentMarkdown?js_string}',articleTpId:'${articleTpId?js_string}',articleTpNm:'${articleTpNm?js_string}',
			articleClick:'${articleClick?js_string}',articleHtmlName:'${articleHtmlName?js_string}',articleComments:'${articleComments?js_string}',
            articleKeywords:'${articleKeywords}',articleTags:[],
         },
		articleTpList:[],
		editormd_html:"/editormd/html/editormd.html",
		isLoaded:false,
		iframeWin: {},
        tagList:[]
	},
	mounted:function() {
		//window.removeEventListener('message', this.handleMessage);
		// 在外部vue的window上添加postMessage的监听，并且绑定处理函数handleMessage
	    window.addEventListener('message', this.handleMessage);
	    this.iframeWin = this.$refs.iframe.contentWindow;
	    
	},


	methods :{
		
		getArticleCategoriesList : function(){
			
			$.ajax({
				url:"/articleType/getArticleCategoriesList",
				type:"get",
				data:{},
				success:function(rs){
					
	             	if(rs.code=="200" && rs.message == "success"){
	             		vm_update.articleTpList=rs.list;
                	}
					
				},
				error:function(rs){
					alert("出错了，大兄弟！");
				}
			})
		},
        getArticleTagList : function(){

          $.ajax({
            url:"/tag/getArticleTagList",
            type:"get",
            data:{},
            success:function(rs){

              if(rs.code=="200" && rs.message == "success"){
                vm_update.tagList=rs.list;
              }

            },
            error:function(rs){
              alert("出错了，大兄弟！");
            }
          })
        },
       updateData:function(){

    	   vm_update.article.articleUpdtdt=new Date();
    	   
    	   $.ajax({
               url: '/article/update',
               type: 'post',
               //data: vm_update.article,
               data: JSON.stringify(vm_update.article),
               contentType: "application/json;charset=UTF-8",
               success:function(rs){
    				
               	if(rs.code=="200" && rs.message == "success"){
               		
               		//关闭模特框
           		 	$('#articleUpdateModal').modal("hide");
           		 	  alert("修改成功");
           		 	  location.reload(); 
               	}
                   
               },error:function(){
                   
            	   alert("修改失败");
	            }
          });
    	   
       },
       //外部vue向iframe内部传数据
       sendMessage :function(message) {
    	   //alert(message);
    	   console.log(vm_update.article.articleContentMarkdown);
    	   
   	       this.iframeWin.postMessage({
   	         cmd: message,
   	         params: {
   	        	 markdown:vm_update.article.articleContentMarkdown,
   	         }
   	        }, '*')
    	},
       //处理iframe返回的数据
       handleMessage:function async(event){
			
    	  if(vm_update.isLoaded){
    		  return;
    	  } 
    	   
		  const data = event.data
    	  
    	  if (data.params.success) {
              
    	  	   // 根据上面制定的结构来解析iframe内部发回来的数据
			   if("returnUpdateMessage"==data.cmd){
				   // 业务逻辑
			       //alert(data.params.html);
			       console.log(data.params.markdown);
			       vm_update.article.articleContentMarkdown=data.params.markdown;

			       if(vm_update.article.articleContent==null||vm_update.article.articleContent==''){
                      vm_update.article.articleContent=data.params.html;
                    }
		    	   //修改入库
		    	   vm_update.updateData();
			   }
			   vm_update.isLoaded = true;
            }
			   
		} 

	}
});
vm_update.getArticleCategoriesList();
vm_update.getArticleTagList();
setTimeout(function(){ vm_update.sendMessage("showUpdateMessage"); }, 300);
</script>
