<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>${name}</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?29799af71dfd42c15a405221cf2261b8";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>

<body>
<!-- style="visibility:hidden"  -->
<div class="titleBox" >
    <a id="title" href="${redirectUrl}">
        <p>${name}</p>
    <span class="ui-icon-return">
        <span></span>
    </span>
    </a>
</div>
<div id="leamain">
   <#if iframe1??> <iframe id="iframe1" src="${iframe1}" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" name=""></iframe>
   </#if>
   <#if iframe2??> <iframe id="iframe2" src="${iframe2}" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" name=""></iframe>
   </#if>
   <#if iframe3??> <iframe id="iframe3" src="${iframe3}" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" name=""></iframe>
   </#if>
   <#if iframe4??> <iframe id="iframe4" src="${iframe4}" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" name=""></iframe>
   </#if>
   <#if iframe5??> <iframe id="iframe5" src="${iframe5}" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" name=""></iframe>
   </#if>


</div>
</body>
<script>
    var d = document.getElementById("title");
    var iframe1 = document.getElementById("iframe1");
    var iframe2 = document.getElementById("iframe2");
    var iframe3 = document.getElementById("iframe3");
    var iframe4 = document.getElementById("iframe4");
    var iframe5 = document.getElementById("iframe5");
    var e = window.location.href.split("?");
    var s = e[1].split("&");
    a = s[0].split("=")
    d.href = d.href.replace("{tid}",a[1])
    if(iframe1.src.length>0){
        iframe1.src = iframe1.src.replace("{tid}",a[1]+1)
    }
    if(iframe2.src.length>0){
        iframe2.src = iframe2.src.replace("{tid}",a[1]+2)
    }
    if(iframe3.src.length>0){
        iframe3.src = iframe3.src.replace("{tid}",a[1]+3)
    }
    if(iframe4.src.length>0){
        iframe4.src = iframe4.src.replace("{tid}",a[1]+4)
    }
    if(iframe5.src.length>0){
        iframe5.src = iframe5.src.replace("{tid}",a[1]+5)
    }
</script>
</html>