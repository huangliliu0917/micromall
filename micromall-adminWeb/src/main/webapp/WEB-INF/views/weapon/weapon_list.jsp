<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">

    <link href="<c:url value="/resources/css/houtaikk.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery.utils.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.8.20.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.10.3.custom.min.css"/>">
    <title>代理商管理</title>

    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/weaponApi/" />";

        function delWeapon(weaponId) {
            jBox.confirm('确定要删除该微武器？', '提示', function (v, h, f) {
                if (v == "ok") {
                    $.jBox.tip("正在删除，请稍后...", 'loading');
                    J.GetJsonRespons(ajaxUrl + "deleteWeapon", {
                        weaponId: weaponId
                    }, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("删除成功", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("删除失败", "error");
                        }
                    }, function () {

                    }, J.PostMethod);
                }
                return true;
            });
        }
    </script>
    <style>
        .boxx {
            min-height: 179px;
            border: 1px solid #E9E9E9;
            background-color: #fff;
            width: 98%;
            margin: 0 auto;
        }

        .pngii {
            float: left;
            width: 25%;
            margin: 6px 13px;
        }
    </style>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>微武器管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <div class="day">
                    <!--时间-->
                    <table width="100%">
                        <tbody>
                        <tr>
                            <td width="55%">
                                <div class="fg-button clearfix" style="float:right">
                                    <a class="${weaponType==1?"on":""}" href="javascript:window.location.href='<c:url value="/weapon/weaponList?weaponType=1" />'">软文</a>
                                </div>
                                <div class="fg-button clearfix" style="float:right">
                                    <a class="${weaponType==0?"on":""}" href="javascript:window.location.href='<c:url value="/weapon/weaponList" />'">图文</a>
                                </div>

                            </td>

                            <td align="right">
                                <div class="fg-button clearfix" style="float:right">
                                    <a href="javascript:window.location.href='<c:url value="/weapon/weaponEdit?weaponType=${weaponType}" />'">添加微武器</a>
                                </div>

                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!---表格--->
                <div class="dataTables_wrapper">
                    <c:choose>
                        <c:when test="${weaponType==0}">
                            <ul>
                                <c:forEach items="${pageInfo.getContent()}" var="weaponBean">
                                    <li style="float: left; width: 50%; margin-bottom: 30px; ">
                                        <div class="boxx">
                                            <p style="width: 90%;margin: 0 auto;padding: 10px;text-overflow:ellipsis;text-align:center;white-space:nowrap;overflow:hidden;display:inline-block;">
                                                    ${weaponBean.weaponContent}
                                            </p>

                                            <p style="text-align:center">
                                                <c:if test="${weaponBean.weaponImgs!=''}">
                                                    <c:forEach items="${weaponBean.imgList}" var="img">
                                                        <span class="pngii"><img src="${uploadResourceServer.resourceUri(img)}" width="80px" height="80px"></span>
                                                    </c:forEach>
                                                </c:if>

                                            </p>

                                            <p style="clear: both"></p>

                                            <p style="float: right; margin-right: 10px;">
                                                <a href="<c:url value="/weapon/weaponEdit?weaponId=${weaponBean.weaponId}&weaponType=0"/>"> <span>编辑</span></a> |
                                                <a href="javascript:delWeapon(${weaponBean.weaponId})"><span>删除</span></a>
                                            </p>
                                        </div>

                                    </li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <table width="100%" class="table_appss tablept5" id="table_apps">
                                <thead>
                                <tr class="sdkbar" style="font-weight:bold;">
                                    <th align="center" rowspan="1" colspan="1">缩略图</th>
                                    <th align="center" rowspan="1" colspan="1">软文标题</th>
                                    <th align="center" rowspan="1" colspan="1">操作</th>
                                </tr>
                                </thead>
                                <!---表头---->


                                <tbody>
                                <c:forEach items="${pageInfo.getContent()}" var="weaponBean">
                                    <tr height="28px" class="odd">
                                        <td align="center">
                                            <c:if test="${weaponBean.weaponImgs!=''}">
                                                <img style="width: 80px;height: 80px;" src="${uploadResourceServer.resourceUri(weaponBean.weaponImgs)}"/>
                                            </c:if>
                                        </td>
                                        <td>
                                            <p style="width: 90%;margin: 0 auto;padding: 10px;text-overflow:ellipsis;text-align:center;white-space:nowrap;overflow:hidden;display:inline-block;">
                                                    ${weaponBean.weaponTitle}
                                            </p>
                                        </td>
                                        <td align="center">
                                            <a href="<c:url value="/weapon/weaponEdit?weaponId=${weaponBean.weaponId}&weaponType=1"/>">编辑</a> |
                                            <a href="javascript:delWeapon(${weaponBean.weaponId})">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <!---内容---->
                            </table>
                        </c:otherwise>
                    </c:choose>
                    <!--翻页--->
                    <p style="clear:both;"></p>

                    <div class="dataTables_paginate paging_full_numbers" style="float: right; padding: 8px"></div>
                    <p style=" clear:both"></p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    $(function () {
        Index.LoadPaging($(".paging_full_numbers"), ${pageIndex}, ${pageSize}, ${pageInfo.getTotalElements()}, function (o, p, s, t, callback) {
            paginate(p);
        });
    });

    function paginate(pageIndex) {
        window.location.href = "<c:url value="/weapon/weaponList?weaponType=${weaponType}&pageIndex=" />" + pageIndex;
    }
</script>