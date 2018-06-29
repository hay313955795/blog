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
            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body pad">
                            <div>
                                <textarea id="content" class="form-control" rows="3" style="overflow-x:visible;overflow-y:visible;" ></textarea>
                            </div>
                            <div id="toolBar">
                                <a id="emoji" href="javascript:void(0);" >
                                    表情
                                </a>
                            </div>
                            <div class="img-box full">
                                <section class=" img-section">
                                    <p class="up-p">图片上传</p>
                                    <div class="z_photo upimg-div clear" >

                                        <section class="z_file fl">
                                            <img src="/static/images/imgUp/a11.png" class="add-img">
                                            <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
                                        </section>
                                    </div>
                                </section>
                            </div>
                            <aside class="mask works-mask">
                                <div class="mask-content">
                                    <p class="del-p">您确定要删除作品图片吗？</p>
                                    <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
                                </div>
                            </aside>
                        </div>
                    </div>
                    611
                    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
                    <script type="text/javascript">
                        $(function(){
                            $("#emoji").emoji({content_el:"#content",
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
                    </script>
                </div>
                <div class="col-md-7">
                    dd
                </div>
            </div>
        </section>
    </div>
    <#include "module/_footer.ftl">
</div>
<@footer></@footer>