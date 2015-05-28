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
    <title>代理商管理</title>

</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>通讯录
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/user/userList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    <input type="hidden" value="${agentId}" name="agentId"/>
                                    搜索关键字：<input style="width: 200px;" name="searchKey" id="searchKey" placeholder="手机号/姓名" value="${searchKey}" class="hasDatepicker">
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 20px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/user/userList?agentId=${agentId}"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right;" id="backBtn">
                                        <a href="javascript:window.location.href='<c:url value="/agent/agentList" />'">返回</a>
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
                            <th align="center" rowspan="1" colspan="1">手机号</th>
                            <th align="center" rowspan="1" colspan="1">姓名</th>
                            <th align="center" rowspan="1" colspan="1">QQ</th>
                            <th align="center" rowspan="1" colspan="1">微信号</th>
                            <th align="center" rowspan="1" colspan="1">收货地址</th>
                        </tr>
                        </thead>
                        <!---表头---->


                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="userBean">
                            <tr height="28px" class="odd">
                                <td align="center">${userBean.userMobile}</td>
                                <td align="center">${userBean.userName}</td>
                                <td align="center">${userBean.userQQ}</td>
                                <td align="center">${userBean.userWeixin}</td>
                                <td align="center">${userBean.userAddr}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <!---内容---->
                    </table>
                    <!--翻页--->
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
        Index.LoadPaging($(".paging_full_numbers"), ${pageIndex}, J.PageSize, ${pageInfo.getTotalElements()}, function (o, p, s, t, callback) {
            paginate(p);
        });
    });

    function paginate(pageIndex) {
        $("#pageIndex").val(pageIndex);
        $("#searchForm").submit();
    }
</script>