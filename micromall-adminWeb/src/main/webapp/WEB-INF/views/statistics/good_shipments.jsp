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
    <title>商品出货量统计</title>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>商品出货量统计
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/statistics/goodShipments" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    时间：<input type="text" style="width: 200px;" id="beginTime" name="beginTime" placeholder="起始时间" value="${beginTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true})"/>~
                                    <input type="text" style="width: 200px;" id="endTime" name="endTime" placeholder="结束时间" value="${endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"/>

                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 20px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/statistics/goodShipments"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
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
                            <th align="center" rowspan="1" colspan="1">商品编号</th>
                            <th align="center" rowspan="1" colspan="1">商品名称</th>
                            <th align="center" rowspan="1" colspan="1">销售价</th>
                            <th align="center" rowspan="1" colspan="1">出货量</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${list}" var="viewModel">
                            <tr height="28px" class="odd">
                                <td align="center">${viewModel.goodCode}
                                </td>
                                <td align="center">${viewModel.goodName}</td>
                                <td align="center">${viewModel.price}</td>
                                <td align="center">${viewModel.num}</td>
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
<script type="text/javascript" src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js" />"></script>