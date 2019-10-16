<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<div id="update">
<div class="modal fade" id="articleUpdateModal" tabindex="-1" area="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content" style="width: 180%;position:absolute;left:-30%">
            <div class="modal-header " style="text-align: left">
                <h4 class="modal-title"  >修改</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                   
				  <div id="layout">
						<div style="width: 95%; margin-bottom: 15px;">
							<label>标题</label> 
							<input type="text" v-model="article.articleName" style="width: 95%; height: 35px;position:relative;left:15px">
						</div>
						<div style="width: 95%; margin-bottom: 15px;">
							<label>分类</label> 
							<select  v-model="article.articleTpId"  style="width: 95%; height: 35px;position:relative;left:15px">                                        
							    <option :value="articleTp.id"  v-for="articleTp in articleTpList" >{{articleTp.name}}</option>                                    
							</select>
						</div>
						<div style="width: 95%; margin-bottom: 15px;">
							<label>简介</label> 
							<div style="display:inline-block;position:relative;left:15px;top:-15px">
								<textarea v-model="article.articleShort" rows="3" cols="115"></textarea>
							</div>
						</div>
						<div style="position: relative; top: 10px;left:30px">
							<div style="display:inline;position:relative;top:-640px;left:-32px">
								<label>内容</label>
							</div>
				            <iframe :src="editormd_html" ref="iframe" style="width: 90%;height:640px; margin-bottom: 15px;position: relative;left:-15px;"></iframe>
				        </div>   
			        </div>	
			        
            	</div>
            <div class="modal-footer">
                <button type="button" v-on:click="sendMessage('sendUpdateMessage')" class="btn btn-primary" >确定</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</div>

<script>

var vm_update = new Vue({

	el : '#update',
	data : {		
		 
		updtEditor:{},
		articleTpList:[{id:'1',name:'技术'},{id:"2",name:'杂文'}],
		article:{},
		editormd_html:"/editormd/html/editormd.html",
		isLoaded:false,
		iframeWin: {}
	},
	mounted:function() {
		//window.removeEventListener('message', this.handleMessage);
		// 在外部vue的window上添加postMessage的监听，并且绑定处理函数handleMessage
	    window.addEventListener('message', this.handleMessage);
	    this.iframeWin = this.$refs.iframe.contentWindow;
	},
	methods :{
			  
       updateData:function(){

    	   vm_update.article.articleUpdtdt=new Date();
    	   
    	   $.ajax({
               url: '${pageContext.request.contextPath}/article/update',
               type: 'post',
               data: vm_update.article,
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
			       //console.log(data.params.markdown);
			       vm_update.article.articleContentMarkdown=data.params.markdown;
			       vm_update.article.articleContent=data.params.html;
		    	   //修改入库
		    	   vm_update.updateData();
			   }
			   vm_update.isLoaded = true;
            }
			   
		} 

	}
});

</script>
