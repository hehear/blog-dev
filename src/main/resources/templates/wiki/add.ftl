<!DOCTYPE html>
  <!-- 主页导航头部strt -->
  <div class="pageheader">
    <h2><i class="fa fa-file-text"></i> WIKI新增 <span>To write a new wiki..</span></h2>
    <div class="breadcrumb-wrapper">
      <span class="label">You are here:</span>
      <ol class="breadcrumb">
        <li><a href="index.html">Bracket</a></li>
        <li><a href="index.html">Pages</a></li>
        <li class="active">Wiki Add</li>
      </ol>
    </div>
  </div>
	<!-- 主页导航头部end -->
	  <!-- 页面主体 strt -->
  <div class="contentpanel">
   <div id="add" class="row">
   
          <div class="panel panel-default">
             
              <div class="panel-body">

                <div class="form-group">
                  <label class="col-sm-1 control-label">ID <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <input type="text" v-model="wiki.wikiId"  class="form-control" placeholder="输入ID..." required />
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">NAME <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <input type="text" v-model="wiki.wikiName"  class="form-control" placeholder="输入NAME..." required />
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">PID <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <input type="text" v-model="wiki.wikiPid"  class="form-control" placeholder="输入PID..." required />
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-1 control-label">HTML内容 <span class="asterisk">*</span></label>
                  <div class="col-sm-9">
                    <textarea rows="5" v-model="wiki.wikiContent" class="form-control" placeholder="输入HTML内容..." required></textarea>
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
                    <button class="btn btn-primary" v-on:click="sendMessage()" >Submit</button>
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
var isLoaded=false;
var vm_add = new Vue({

	el : '#add',
	data : {		
		wiki:{wikiId:'',wikiName:'',wikiPid:'',wikiContentMd:'',wikiContent:''},
		editormd_html:"/editormd/html/editormd.html",
		addEditor:{},
		isLoaded:false,
		iframeWin: {},
        tagList:[]
	},
	// 在外部vue的window上添加postMessage的监听，并且绑定处理函数handleMessage
	mounted:function() {

		//window.removeEventListener('message', this.handleMessage);
	    window.addEventListener('message', this.handleMessage);
	    this.iframeWin = this.$refs.iframe.contentWindow;
	},
	
	methods:{


       addData:function(){
    	   
    	   $.ajax({
    		   
    		   url:'/wiki/wikiAdd',
    		   type:'post',
               data: JSON.stringify(vm_add.wiki),//将对象序列化成JSON字符串
               contentType: "application/json;charset=UTF-8",
    		   success:function(rs){
    			   
    			   if(rs.code=="200" && rs.message == "success"){
               		
    				alert("保存成功!!!!");  
               		//关闭模特框
	            	$('#articleAddModal').modal("hide");
               		
               		//console.log("successs");
	                    
                 	//$.success("保存成功");
               		location.reload();

               	}
    		   },
    		   error:function(){}
    		   
    	   })
       },
      getWikiId:function(){

        $.ajax({
          url:'/wiki/getWikiId',
          type:'get',
          data: {},
          success:function(rs){

            if(rs.code=="200" && rs.message == "success"){

              vm_add.wiki.wikiId=rs.record;

            }
          },
          error:function(){}

        })
      },
       //外部vue向iframe内部传数据
       sendMessage :function() {
    	      this.iframeWin.postMessage({
    	        cmd: 'sendAddMessage',
    	        params: {}
    	      }, '*')
    	    },
       //处理iframe返回的数据
       handleMessage:function async(event){
			
    	  if(vm_add.isLoaded){
    		  return;
    	  } 
    	   
		  var data = event.data
    	  
    	  if (data.params.success) {
              
    	  	   // 根据上面制定的结构来解析iframe内部发回来的数据
			   if("returnAddMessage"==data.cmd){
				   // 业务逻辑
			       //alert(data.params.html);
			       //console.log(data.params.markDown);
			       vm_add.wiki.wikiContentMd=data.params.markdown;
			       if(vm_add.wiki.wikiContent==null||vm_add.wiki.wikiContent==''){
                     vm_add.wiki.wikiContent=data.params.html;
                   }
		    	   //新增入库
		    	   vm_add.addData();
			   }
			  vm_add.isLoaded = true;
            }
			   
		}  
	}
});

vm_add.getWikiId();

</script>
