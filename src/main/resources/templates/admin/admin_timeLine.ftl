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
        <section class="content container-fluid">
            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body pad">
                            <div>
                                <textarea id="content" class="form-control" rows="3" style="overflow-x:visible;overflow-y:visible;"></textarea>
                            </div>
                            <div id="toolBar">
                                <a class="S_txt1" href="javascript:void(0);" action-type="face" action-data="type=500&amp;action=1&amp;log=face&amp;cate=1" title="表情" node-type="smileyBtn" suda-uatrack="key=tblog_home_edit&amp;value=phiz_button">
                                    &#x1F600;
                                    表情
                                </a>
                            </div>
                        </div>
                    </div>

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