<!DOCTYPE html>
<!-- 主页导航头部strt -->
<div class="pageheader">
    <h2><i class="fa fa-file-text"></i> 博客类型列表 <span>Read our latest news...</span></h2>
    <div class="breadcrumb-wrapper">
        <span class="label">You are here:</span>
        <ol class="breadcrumb">
            <li><a href="index.html">Bracket</a></li>
            <li><a href="index.html">Pages</a></li>
            <li class="active">Article Type List</li>
        </ol>
    </div>
</div>
<!-- 主页导航头部end -->

<!-- 页面主体 strt -->
<div class="contentpanel">

    <div id="app">
        <ul class="filemanager-options mt10">
            <li>
                <a class="itemopt " href="javascript: void(0)" v-on:click="toUpd()"><i class="glyphicon glyphicon-plus-sign"></i>新增</a>
            </li>
            <li><a href="#" class="itemopt disabled"><i class="glyphicon glyphicon-download-alt"></i>Download</a></li>
            <li><a href="javascript:void(0)" class="itemopt "><i class="glyphicon glyphicon-refresh"></i>刷新</a></li>
            <li class="filter-type">
                Show:<a href="all.html" class="active">All</a> <a href="document.html">document</a> <a
                        href="audio.html">Audio</a> <a href="image.html">Images</a> <a href="video.html">Videos</a></li>
        </ul>
        <div class="filemanager-options mt10">
            <form onsubmit="return false;" class="form-inline form-search">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group"><label>类型ID：</label>
                            <input type="text" v-model="filter.articleTpId" placeholder="类型ID" class="form-control"></div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group"><label>类型名称：</label>
                            <input type="text" v-model="filter.articleTpNm" placeholder="类型名称_模糊查询" class="form-control"></div>
                    </div>
                </div>
                <div class="row mt10">
                    <div class="col-md-12 m" >
                        <button type="button" class="btn btn-primary btn-xs" v-on:click="loadList()" >查询</button>
                        <button type="button" class="btn btn-default btn-xs"  v-on:click="reset()">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row mt10">
            <div class="col-md-12">
                <div class="table-responsive">
                    <table class="table table-dark  table-bordered table-hover table-striped mb0">
                        <thead>
                        <tr>
                            <th style="width:50px;">序号</th>
                            <th>类型ID</th>
                            <th>类型名称</th>
                            <th>操作时间</th>

                            <th class="m">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(item,index) in articleTpList">
                            <td class="m">{{index+1}}</td>
                            <td>{{item.articleTpId}}</td>
                            <td>{{item.articleTpNm}}</td>
                            <td>{{item.updtTm | timeformatter}}</td>
                            <td class="table-action">
                                <a href="javascript:void(0)" v-on:click="toUpd(item)" title="编辑">
                                    <i class="glyphicon glyphicon-edit"></i></a>&nbsp;
                                <a href="javascript:void(0)" v-on:click="toDlt(item)" title="删除">
                                    <i class="glyphicon glyphicon-trash"></i></a>&nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
<#--            <div class="col-md-12">-->
<#--                <div class="page-footer">-->
<#--                    <div class="dataTables_info "><span class="label label-primary ">显示<span class="num">1</span> 至<span-->
<#--                                    class="num">1</span>条 共 <span class="num">10</span> 条</span></div>-->
<#--                    <div id="list_pager" class="dataTables_paginate paging_simple_numbers "></div>-->
<#--                </div>-->
<#--            </div>-->
            <!---->
        </div>

        <!--  新增修改模态框 strt-->
        <div id ="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">

            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header" >
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h5 class="modal-title" >类型维护-{{title}}</h5>
                    </div>
                    <div class="modal-body">
                        <form>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label  class="control-label">类型ID</label>
                                        <input type="text" class="form-control"  v-model="curr.articleTpId">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label  class="control-label">类型名称</label>
                                        <input type="text" class="form-control"  v-model="curr.articleTpNm">
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-sm" v-on:click="save" >保存</button>
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <!--  新增修改模态框 end-->
    </div>

</div>
<!-- 页面主体 end -->
<!-- js -->
<script src="/js/masonry.pkgd.min.js"></script>

<script>
    var vm = new Vue({
        el: '#app',

        data: {

            //当前编辑信息
            curr: {},
            //查询条件信息
            filter: {
            },
            //文章类型列表
            articleTpList:[],
            //修改界面标题
            title: '新增',
            //查询结果文本提示信息
            rsMes: '正在努力加载...',
            rsMesObj: {
                search: '正在努力加载...',
                none: '无相关数据信息'
            },
            editorType: 'ck'

        },
        methods: {
            reset: function () {
                vm.filter = {
                    articleTpId: '',
                    articleTpNm: ''
                };
            },
            loadList: function () {

                $.ajax({
                    url: '/articleType/list',
                    type: 'post',
                    data: vm.filter,
                    success: function (rs) {

                        vm.articleTpList=rs.list;

                    }
                });
            },
            //新增或修改
            toUpd: function (item) {
                vm.title = item ? '修改' : '新增';
                vm.curr = item ?item :{
                    articleTpId: '',
                    articleTpNm: ''
                };
                $('#myModal').modal('show');
            },
            //删除
            toDlt:function(item){

                $.ajax({
                    url:'/articleType/delete',
                    type:'post',
                    data:{id:item.id},
                    success:function(){
                        alert("删除成功！");
                        vm.loadList();
                    },
                    error:function(){
                        alert("删除失败！")
                    }
                })

            },
            save: function () {
                vm.curr.updtTm=new Date();
                $.ajax({
                    url: '/articleType/save',
                    type: 'post',
                    data: vm.curr,
                    success: function (rs) {
                        $('#myModal').modal('hide');
                        vm.loadList();
                    }
                });

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
                var hour= (value.getHours())<10?'0'+(value.getHours()):(value.getHours());
                var minutes=(value.getMinutes())<10?'0'+(value.getMinutes()):(value.getMinutes());
                var seconds=(value.getSeconds())<10?'0'+(value.getSeconds()):(value.getSeconds());
                return year+'-'+month+'-'+day+' '+hour+':'+minutes+':'+seconds;
                //return year+'-'+month+'-'+day;

            }
        }
    });

    vm.loadList();
</script>