<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<c:url value="/resources/css/houtaikk.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <title>微商管理后台</title>
</head>

<body class="horizontal-menu-sidebar">
<script type="text/javascript" language="javascript">
    function dyniframesize(down) {
        var pTar = null;
        if (document.getElementById) {
            pTar = document.getElementById(down);
        }
        else {
            eval('pTar = ' + down + ';');
        }
        if (pTar && !window.opera) {
            //begin resizing iframe
            pTar.style.display = "block"
            if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
                //ns6 syntax
                pTar.height = pTar.contentDocument.body.offsetHeight + 20;
                pTar.width = pTar.contentDocument.body.scrollWidth + 20;
            }
            else if (pTar.Document && pTar.Document.body.scrollHeight) {
                //ie5+ syntax
                pTar.height = pTar.Document.body.scrollHeight;
                pTar.width = pTar.Document.body.scrollWidth;
            }
        }
    }

    function goMenu(url, dom) {
        $("#contentFrame").attr("src", url);
        $(".children .active").removeClass("active");
        $(dom).addClass("active");
    }

    function reload() {
        window.location.reload();
    }

    $(function () {
        <c:if test="${configBean==null}">
        goMenu('<c:url value="/config/mallConfig" />');
        </c:if>
        $("#leftpanel").height($(window).height());
        resizeWindow();
    });

    window.onresize = function () {
        resizeWindow();
    }

    function resizeWindow() {
        $("#contentFrame").height($(window).height());

    }
</script>
<section>
    <div style="overflow: auto;" id="leftpanel" class="leftpanel">
        <div class="logopanel">
            <img src="${uploadResourceServer.resourceUri(configBean.logo)}" height="40px" style="margin-left:15px">
            <span style="width:160px; text-overflow:ellipsis;white-space:nowrap; overflow:hidden;display:inline-block;">
                                <a href="#" style="color:#8f939e">${configBean.title}</a>
                            </span>
        </div>
        <!-- logopanel -->

        <div class="leftpanelinner" style="border-bottom:1px dotted #444">
            <ul class="nav nav-pills nav-stacked nav-bracket" style="margin-bottom:0px">
                <li style="clear:both; height:10px"></li>
                <li>
                    <p style="margin-left:15px">
                            <span>
                                <a href="javascript:goMenu('<c:url value="/config/mallConfig" />')" style="color:#8f939e">商家设置</a>
                            </span>
                            <span style="float: right">
                                <a href="#" style="color:#8f939e">退出</a>
                            </span>
                    </p>
                </li>

            </ul>
        </div>


        <div style="height:100%;">
            <div class="leftpanelinner">


                <ul class="nav nav-pills nav-stacked nav-bracket">
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>代理商管理</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/agent/agentList"/>',this)" class="active"><a href="#"><i class="fa fa-caret-right"></i>代理商列表</a></li>
                            <li onclick="goMenu('<c:url value="/agent/agentEdit"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>添加代理商</a></li>
                            <li onclick="goMenu('<c:url value="/agent/levelList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>代理商等级管理</a></li>
                            <li onclick="goMenu('<c:url value="/agent/applyAgentList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>代理商申请管理</a></li>
                            <li onclick="goMenu('<c:url value="/group/groupList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>代理商分组管理</a></li>
                        </ul>
                    </li>
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>商品管理</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/good/goodList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>商品列表</a></li>
                            <li onclick="goMenu('<c:url value="/good/goodEdit"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>添加商品</a></li>

                        </ul>
                    </li>
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>货品订单</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/order/proSearch"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>货品查询</a></li>
                            <li onclick="goMenu('<c:url value="/order/orderList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>订单查询</a></li>
                        </ul>
                    </li>
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>出货管理</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/delivery/deliverList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>出货列表</a></li>
                            <li onclick="goMenu('<c:url value="/delivery/managerList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>出货管理员</a></li>
                        </ul>
                    </li>
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>微武器</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/weapon/weaponList"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>微武器管理</a></li>
                            <li onclick="goMenu('<c:url value="/weapon/weaponEdit"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>添加图文</a></li>
                            <li onclick="goMenu('<c:url value="/weapon/weaponEdit?weaponType=1"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>添加软文</a></li>
                        </ul>
                    </li>
                    <li class="nav-parent nav-active active" style="border-bottom: 1px dotted #444;"><a href=""><i class="fa fa-chevron-circle-right"></i> <span>统计报表</span></a>
                        <ul class="children" style="display: block">
                            <li onclick="goMenu('<c:url value="/statistics/agentShipments"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>代理商出货量统计</a></li>
                            <li onclick="goMenu('<c:url value="/statistics/goodShipments"/>',this)"><a href="#"><i class="fa fa-caret-right"></i>商品出货量统计</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- leftpanelinner -->
        </div>
    </div>
    <!-- leftpanel -->

    <div class="mainpanel">
        <!-- headerbar -->
        <div style="clear:both"></div>
        <iframe src="<c:url value="/agent/agentList"/>" scrolling="auto" frameborder="0" height="1000px" id="contentFrame" width="100%"></iframe>
    </div>
    <!-- mainpanel -->

</section>
</body>
</html>

