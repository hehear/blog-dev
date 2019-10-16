<!DOCTYPE html>
<!-- 主页导航头部strt -->
<div class="pageheader">
    <h2><i class="fa fa-file-text"></i> 博客列表 <span>Read our latest news...</span></h2>
    <div class="breadcrumb-wrapper">
        <span class="label">You are here:</span>
        <ol class="breadcrumb">
            <li><a href="index.html">Bracket</a></li>
            <li><a href="index.html">Pages</a></li>
            <li class="active">Blog List</li>
        </ol>
    </div>
</div>
<!-- 主页导航头部end -->

<!-- 页面主体 strt -->
<div class="contentpanel">
    <div id="bloglist" class="row">
        <div class="col-xs-6 col-sm-4 col-md-3" v-for='(info,i) in list'>
            <div class="blog-item" style="width:288px;height:490px;">
                <a href="blog-single.html" class="blog-img"><img src="/images/photos/blog1.jpg" class="img-responsive"
                                                                 alt=""/></a>
                <div class="blog-details">
                    <h4 class="blog-title"><a href="">{{info.articleName.length>20?info.articleName.substr(0,20):info.articleName}}</a>
                    </h4>
                    <ul class="blog-meta">
                        <li>{{info.articleUpdtdt | timeformatter}}</li>
                        <li>分类：<a href="">{{info.articleTpNm}}</a></li>
                        <li>点击数：<a href="">{{info.articleClick||0}}</a></li>
                    </ul>
                    <div class="blog-summary">
                        <p>{{info.articleShort | substrArticleShort}}</p>
                        <button class="btn btn-sm btn-white" v-on:click="update(info)">Edit This</button>
                        <button class="btn btn-sm btn-white" v-on:click="delete_article(info)">Delete</button>
                    </div>
                </div>
            </div><!-- blog-item -->

        </div><!-- col-xs-6 -->
        <!-- 页码 -->
        <div class="pagination pagination-rounded page">
            <div class="article_page" id="page_container"></div>
        </div>
    </div><!-- row -->
</div>
<!-- 页面主体 end -->
<!-- js -->
<script src="/js/masonry.pkgd.min.js"></script>
<!-- <script>
  jQuery(window).load(function(){
    "use strict";
    var container = document.querySelector('#bloglist');
    var msnry = new Masonry( container, {
      // options
      columnWidth: '.col-xs-6',
      itemSelector: '.col-xs-6'
    });
    // check on load
    if(jQuery(window).width() <= 480 )
        msnry.destroy();
    // check on resize
    jQuery(window).resize(function(){
        if(jQuery(this).width() <= 480 )
            msnry.destroy();
    });
    // relayout items when clicking chat icon
    jQuery('#chatview, .menutoggle').click(function(){
       msnry.layout();
    });
  });
</script> -->
<script type="text/javascript">

    var vm_list = new Vue({

        el: '#bloglist',
        data: {
            article: {},

            list: []
        },
        methods: {


            getArticleList: function (pageIndex) {

                $.ajax({
                    url: "/article/getList",
                    type: "get",
                    data: {},
                    success: function (rs) {

                        if (rs.code == "200" && rs.message == "success") {

                            console.log("执行");

                            vm_list.list = rs.list;

                        }

                    },
                    error: function (rs) {
                        alert("出错了，大兄弟！");
                    }
                })
            },
            getArticleDetail: function (info) {

                $.ajax({
                    url: "/article/content",
                    type: "post",
                    data: {article: info},
                    success: function (rs) {

                        $("#list").empty();
                        $("#list").html(rs);
                        $("#detail").show();
                        vm_detail.article = info;
                        vm_detail.add_article_click();
                    },
                    error: function (rs) {
                        alert("出错了，大兄弟！");
                    }
                })
            },
            update: function (article) {

                article.articleUpdtdt = new Date();


                $.ajax({
                    url: "/article/articleUpdate",
                    type: "post",
                    data: article,
                    success: function (rs) {

                        $("#blog_content").empty();
                        $("#blog_content").html(rs);
                    },
                    error: function (rs) {
                        alert("出错了，大兄弟！");
                    }
                })
            },
            //删除
            delete_article: function (article) {

                $.ajax({
                    url: '/article/delete',
                    type: 'post',
                    data: {id: article.id},
                    success: function () {
                        alert("删除成功！");
                        vm_list.getArticleList(1);
                        //location.reload();
                    },
                    error: function () {
                        alert("删除失败！")
                    }
                })

            },
            padDate: function (va) {
                va = va < 10 ? '0' + va : va;
                return va
            }

        },
        filters: {

            //时间戳转时间
            timeformatter: function (time) {
                var value = new Date(time);
                var year = value.getFullYear();
                var month = (value.getMonth() + 1) < 10 ? '0' + (value.getMonth() + 1) : (value.getMonth() + 1);
                var day = (value.getDate()) < 10 ? '0' + (value.getDate()) : (value.getDate());
                /* var hour= (value.getHours())<10?'0'+(value.getHours()):(value.getHours());
                var minutes=(value.getMinutes())<10?'0'+(value.getMinutes()):(value.getMinutes());
                var seconds=(value.getSeconds())<10?'0'+(value.getSeconds()):(value.getSeconds());
                return year+'-'+month+'-'+day+' '+hour+':'+minutes+':'+seconds;*/
                return year + '-' + month + '-' + day;

            },
            substrArticleShort: function (articleShort) {

                if (articleShort.length > 30) {

                    return articleShort.substr(0, 30) + '...';
                } else {
                    return articleShort;
                }
            }
        }
    });

    vm_list.getArticleList(1);

</script>

