<div class="sidebar animated fadeInDown">
    <div class="logo-title">
        <div class="title">
            <img src="${options.blog_logo?default("/anatole/source/images/logo@2x.png")}" style="width:127px;<#if options.anatole_style_avatar_circle?default('false')=='true'>border-radius:50%</#if>" />
            <h3 title="">
                <a href="/">${options.blog_title?default("ANATOLE")}</a>
            </h3>
            <div class="description">
                <#if options.anatole_style_hitokoto?default("false")=="true">
                    <p id="yiyan">获取中...</p>
                <#else >
                    <p>${user.userDesc?default("六月的雨露")}</p>
                </#if>
            </div>
        </div>
    </div>
    <#include "social-list.ftl">
    <div class="footer">
        <a target="_blank" href="#">
            <span>Designed by </span>
            <a href="http://www.tracenight.com">TraceNight</a>
            <div class="by_halo">
                <a href="https://github.com/hay313955795" target="_blank">Proudly published with 夜幕下的尖椒&#65281;</a>
            </div>
            <#--<div class="footer_text">
                ${options.blog_footer_info?if_exists}
            </div>-->
        </a>
    </div>
</div>