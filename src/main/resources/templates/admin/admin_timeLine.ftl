<#include "module/_macro.ftl">
<@head title="${options.blog_title} | 后台管理"></@head>
<div class="wrapper">
    <!-- 顶部栏模块 -->
    <#include "module/_header.ftl">
    <!-- 菜单栏模块 -->
    <#include "module/_sidebar.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1 style="display: inline-block;">时间轴</h1>
            <ol class="breadcrumb">
                <li>
                    <a data-pjax="true" href="/admin">
                        <i class="fa fa-dashboard"></i> 首页</a>
                </li>
                <li><a data-pjax="true" href="#">文章</a></li>
                <li class="active">时间轴</li>
            </ol>
        </section>
        <style>
            #content {
                resize: none;
            }
        </style>
        <style>
            #chatbox{
                margin: 200px auto 0px;
                width: 50%;
            }
            #emoji,#image{
                width: 50px;
                font-size: 1em;
                cursor: pointer;
                float: left;
            }
            #send{
                width: 50px;
                height: 40px;
                line-height:40px;
                text-align: center;
                background-color: #66f3f1;
                color:#fff;
                float: right;
                cursor: pointer;
                padding: 0px;
                margin-right: -6px;
            }
            #result{
                clear: both;
                min-height: 50px;
                padding-top: 30px;
            }
            /*下面是表情的一些样式，可自行修改*/
            .emoji-box{
                overflow: hidden;
                width: 45%;
                position: absolute;
                border: 1px solid #ccc;
                z-index: 9998;
                background: #fff;
                border-radius: 5px;
            }
            em.tip{
                content:'';
                width:0px;
                height:0px;
                border-width:10px;
                border-style:solid;
                border-color:transparent  transparent  #ccc transparent ;
                position: absolute;
                z-index: 99999;
            }
            em.tip2{
                content:'';
                width:0px;
                height:0px;
                border-width:10px;
                border-style:solid;
                border-color:transparent  transparent  #fff transparent ;
                position: absolute;
                z-index: 99999;
            }
            .emoji-box>.emoji-btn-box{
                border-bottom: 1px solid #ccc;
            }
            .emoji-box>.emoji-btn-box>span{
                min-width: 50px;
                text-align:center;
                height: 30px;
                line-height: 30px;
                display: inline-block;
                padding:2px 10px;
                cursor: pointer;
                font-size: 1em;
                font-weight: bold;
                color: #ccc;
            }
            .emoji-box>ul.emoji-ul{
                padding: 0px;
                margin: 0px;
                width: 100%;
            }
            .emoji-box>ul.emoji-ul>li.emoji-li{
                border: 1px solid #fff;
                list-style: none;
                float: left;
            }
            .emoji-box>ul.emoji-ul>li.emoji-li:HOVER {
                border: 1px solid blue;
            }
        </style>
        <section class="content container-fluid">
            <script src="/static/js/imgUp/imgUp.js"></script>
            <link rel="stylesheet" href="/static/plugins/fileinput/fileinput.min.css">

            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body pad">
                            <div>
                                <textarea id="content" class="form-control" rows="3" style="overflow-x:visible;overflow-y:visible;" ></textarea>
                                <input id="imagelist" type="hidden"/>
                            </div>
                            <div id="toolBar" style="margin-top: 20px;margin-bottom: 10px;">
                                <a id="emoji" href="javascript:void(0);" >
                                    表情
                                </a>
                                <a id="showForm"  href="javascript:void(0);">
                                    上传
                                </a>
                                <button class="btn btn-primary btn-xs " style="position: absolute;right: 10px" id="submit">提交</button>
                            </div>
                            <div class="row" id="uploadForm" style="display: none;">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <div class="file-loading">
                                            <input id="uploadImg" class="file-loading" type="file" multiple name="file">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="result" ></div>
                   <#-- <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
-->
                    <script type="text/javascript">
                        var imageList = [];
                        $(function(){
                            $("#emoji").emoji({
                                content_el:"#content",
                                list:[
                                    {
                                        name:"QQ表情",
                                        code:"qq_",
                                        path:"/static/images/face/emoji1/",
                                        suffix:".gif",
                                        max_number:25
                                    },
                                    {
                                        name:"emoji",
                                        code:"em_",
                                        path:"/static/images/face/emoji2/",
                                        suffix:".png",
                                        max_number:22
                                    }
                                ]
                            });

                            $('#content').bind('input propertychange', function() {
                                var content = $("#content").val();
                                content = replace_em(content);
                                $("#result").html(content);
                            });

                        });

                        //表情格式替换
                        function replace_em(str){
                            str = str.replace(/\</g,'&lt;');
                            str = str.replace(/\>/g,'&gt;');
                            str = str.replace(/\n/g,'<br/>');
                            str = str.replace(/\[qq_([0-9]*)\]/g,"<img src='/static/images/face/emoji1/$1.gif' />");
                            str = str.replace(/\[em_([0-9]*)\]/g,"<img src='/static/images/face/emoji2/$1.png'  />");
                            str = str.replace(/\[other_([0-9]*)\]/g,"<img src='/static/images/face/emoji3/$1.png' />");
                            return str;
                        }



                        function loadFileInput() {
                            $.getScript("/static/plugins/fileinput/fileinput.min.js",function () {
                                $.getScript("/static/plugins/fileinput/zh.min.js",function () {
                                    $('#uploadImg').fileinput({
                                        language: 'zh',
                                        uploadUrl: '/admin/attachments/upload/images',
                                        uploadAsync: true,
                                        showUpload: false,
                                        allowedFileExtensions: ['jpg','gif','png','jpeg','svg','psd'],
                                        maxFileCount: 100,
                                        maxFilesNum: 9,
                                        dropZoneEnabled:false,
                                        enctype : 'multipart/form-data',
                                        showClose: false,
                                        layoutTemplates:{
                                            actionUpload:'',//去除上传预览缩略图中的上传图片；
                                            actionZoom:''
                                        }
                                    }).on("filebatchuploadsuccess",function (event,data,previewId,index) {
                                        var data = data.jqXHR.responseJSON;
                                        if(data.success=="1") {
                                            $("#uploadForm").hide(400);
                                            imageList.push(data.attachmentId);
                                            $("#imagelist").val($("#imagelist").val()+','+data.attachmentId)
                                            console.log(data.attachmentId);
                                        }
                                    });
                                });
                            });
                        }

                        $(document).ready(function () {
                            loadFileInput();
                        });
                        $("#showForm").click(function(){
                            $("#uploadForm").slideToggle(400);
                        });

                        $('#submit').click(function(){
                            var imglength = ($('#uploadForm img').length)/2;
                            console.log(imglength);
                            for(var index = 0;index<imglength;index++){
                                var imgsrc = $('#uploadForm img').eq(index*2).attr('src');
                                imageList.push(imgsrc)
                                //console.log(imageList[index]);
                            }
                            console.log(111)
                            load();
                        });
                        function load(){
                            console.log(123);
                            $.ajax({
                                type: 'post',
                                url: '/admin/timeline/new/push',
                                data: {
                                    "timeLineContent":replace_em($('#content').val()),
                                    "attachmentList":JSON.stringify(imageList)
                                },
                                dataType : 'json',
                                Async: false,
                                success:function(data){
                                    //刷新页面
                                    location.reload();
                                    console.log(111);
                                    console.log(data);
                                },
                                error:function(error){
                                    console.log('error')
                                    console.log(error)
                                }
                            })
                        }
                    </script>
                </div>
                <div class="col-md-7">
                    <div class="layui-container">
                        <link rel="stylesheet" href="/static/layui/css/layui.css">
                        <ul class="layui-timeline" id="showList">

                            <#if timeLines.content?size gt 0>

                                <#list timeLines.content as timeLine>

                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">${timeLine.pushDate}</h3>

                                            <div>
                                                <#if timeLine.timeLineContent?length gt 0>
                                                    <p>${timeLine.timeLineContent}</p>
                                                </#if>
                                                <#if timeLine.images?size gt 0>
                                                    <#list timeLine.images as img>
                                                            <img src="${img.attachSmallPath?if_exists}" style="width: 100px;height: 100px">
                                                    </#list>
                                                </#if>
                                            </div>
                                        </div>
                                    </li>
                                </#list>
                            </#if>
                            <#function add num1 num2>
                                <#return num1+num2>
                            </#function>
                            <li class="layui-timeline-item" id="loadMore">
                                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                <div class="layui-timeline-content layui-text">
                                    <div class="layui-timeline-title" id="load">更多</div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <#include "module/_footer.ftl">
</div>
<@footer></@footer>