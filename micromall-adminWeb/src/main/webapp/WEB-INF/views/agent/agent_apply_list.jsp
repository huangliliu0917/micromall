<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>代理商申请列表</title>
    <script type="text/javascript">
        $(function () {
            $("#applyStatus").val(${applyStatus});
        })
    </script>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>代理商申请列表
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/agent/applyAgentList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    搜索关键字：<input style="width: 200px;" name="searchKey" id="searchKey" placeholder="手机号/姓名" value="${searchKey}" class="hasDatepicker">
                                    审核状态：
                                    <select name="applyStatus" id="applyStatus">
                                        <option value="-1">全部</option>
                                        <option value="0">待审核</option>
                                        <option value="1">审核通过</option>
                                        <option value="2">审核失败</option>
                                    </select>
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 20px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/agent/applyAgentList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
                                </td>

                                <td align="right">

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
                            <th align="center" rowspan="1" colspan="1">姓名</th>
                            <th align="center" rowspan="1" colspan="1">手机号</th>
                            <th align="center" rowspan="1" colspan="1">推荐人</th>
                            <th align="center" rowspan="1" colspan="1">从事微商时间</th>
                            <th align="center" rowspan="1" colspan="1">月销售额</th>
                            <th align="center" rowspan="1" colspan="1">经营品牌类目</th>
                            <th align="center" rowspan="1" colspan="1">审核状态</th>
                            <th align="center" rowspan="1" colspan="1">申请时间</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="applyBean">
                            <tr height="28px" class="odd">
                                <td align="center">${applyBean.name}</td>
                                <td align="center">${applyBean.mobile}</td>
                                <td align="center">${applyBean.referrer}</td>
                                <td align="center">${applyBean.workOnTime}</td>
                                <td align="center">${applyBean.saleAmount}</td>
                                <td align="center">${applyBean.workOnType}</td>
                                <td align="center">${applyBean.applyStatus==0?"待审核":applyBean.applyStatus==1?"审核通过":"审核失败"}</td>
                                <td align="center"><fmt:formatDate value="${applyBean.applyTime}" type="both"/></td>
                                <td align="center">
                                    <a href="<c:url value="/agent/applyDetail?applyId=${applyBean.applyId}" />">查看详情</a>
                                </td>
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