<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 15:19
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

    <title>代理商等级管理</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/agentApi/" />";
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/agent/admin.agentLevel.js?23234" />"></script>
</head>
<body style="background-color:#e4e7ea">
<div id="edit_dialog" style="padding:20px;display: none;">
    <span>等级名称：</span>
    <input type="text" id="levelName" style="width: 200px;"/>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>代理商等级管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <form>
                    <!--提示--->
                    <p style="clear:both"></p>

                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                </td>

                                <td align="right">


                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="javascript:editLevel(0,null)">添加</a>
                                    </div>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <!---表格--->
                    <div class="dataTables_wrapper">
                        <table width="100%" class="table_appss tablept5" id="table_apps">
                            <thead>
                            <tr class="sdkbar" style="font-weight:bold;">
                                <th align="center" rowspan="1" colspan="1"></th>
                                <th align="center" rowspan="1" colspan="1">等级名称</th>
                                <th align="center" rowspan="1" colspan="1">操作</th>
                            </tr>
                            </thead>
                            <!---表头---->
                            <tbody>
                            <c:forEach items="${levelList}" var="levelBean" varStatus="index">
                                <tr height="28px" class="odd">
                                    <td align="center">等级${index.count}
                                    </td>
                                    <td align="center">${levelBean.levelName}
                                    </td>
                                    <td align="center">
                                        <a href="javascript:editLevel(${levelBean.levelId},'${levelBean.levelName}')">编辑</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <!---内容---->
                        </table>
                        <p style=" clear:both"></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
