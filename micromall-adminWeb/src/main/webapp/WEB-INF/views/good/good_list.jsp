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
        var ajaxUrl = "<c:url value="/goodApi/" />";
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/good/admin.good.js" />"></script>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>商品管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/good/goodList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    商品名称：<input style="width: 300px;" name="goodName" id="goodName" value="${goodName}" class="hasDatepicker">

                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 50px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/good/goodList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right;display: none;" id="backBtn">
                                        <a href="javascript:window.location.href=document.referrer">返回</a>
                                    </div>
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="<c:url value="/agent/agentEdit" />">添加商品</a>
                                    </div>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
                <!---表格--->
                <div class="dataTables_wrapper">
                    <table width="100%" class="table_appss tablept5" id="table_apps">
                        <thead>
                        <tr class="sdkbar" style="font-weight:bold;">
                            <th align="center" rowspan="1" colspan="1">商品编号</th>
                            <th align="center" rowspan="1" colspan="1">商品名称</th>
                            <th align="center" rowspan="1" colspan="1">销售价</th>
                            <th align="center" rowspan="1" colspan="1">创建日期</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->


                        <tbody>
                        <c:forEach items="${goodList}" var="goodBean">
                            <tr height="28px" class="odd">
                                <td align="center">${goodBean.goodCode}</td>
                                <td align="center">${goodBean.goodName}</td>
                                <td align="center">${goodBean.price}</td>
                                <td align="center">${goodBean.addTime}</td>
                                <td align="center">
                                    <a href="<c:url value="/good/goodEdit?goodId=${goodBean.goodId}" />">编辑</a> |
                                    <a href="javascript:goodHandler.setDelete(${goodBean.goodId})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <!---内容---->
                    </table>
                    <!--翻页--->
                    <p style=" clear:both"></p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>