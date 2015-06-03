<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.8.20.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.10.3.custom.min.css"/>">
    <title>代理商管理</title>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>货品查询
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/order/proSearch" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="1" name="pageIndex" id="pageIndex"/>
                                    货品编号：<input style="width: 200px;" name="snCode" id="snCode" value="${snCode}" placeholder="货品编号" class="hasDatepicker">
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 100px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/order/proSearch"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
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
                            <th align="center" rowspan="1" colspan="1">订单号</th>
                            <th align="center" rowspan="1" colspan="1">货品编号</th>
                            <th align="center" rowspan="1" colspan="1">商品名</th>
                            <th align="center" rowspan="1" colspan="1">收件人姓名</th>
                            <th align="center" rowspan="1" colspan="1">收件人手机</th>
                            <th align="center" rowspan="1" colspan="1">发货人</th>
                            <th align="center" rowspan="1" colspan="1">订单时间</th>
                            <th align="center" rowspan="1" colspan="1">订单状态</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="orderItemBean">
                            <tr height="28px" class="odd">
                                <td align="center">${orderItemBean.ordersBean.orderId}</td>
                                <td align="center">${orderItemBean.proCode}</td>
                                <td align="center">${orderItemBean.ordersBean.good.goodName}</td>
                                <td align="center">${orderItemBean.ordersBean.shipName}</td>
                                <td align="center">${orderItemBean.ordersBean.shipMobile}</td>
                                <td align="center">${orderItemBean.ordersBean.realShipAgent==null?"商家发货":orderItemBean.ordersBean.realShipAgent.agentAccount}</td>
                                <td align="center"><fmt:formatDate value="${orderItemBean.ordersBean.addTime}" type="both"/></td>
                                <td align="center">${orderItemBean.ordersBean.orderStatus==0?"未发货":"已发货"}</td>
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
