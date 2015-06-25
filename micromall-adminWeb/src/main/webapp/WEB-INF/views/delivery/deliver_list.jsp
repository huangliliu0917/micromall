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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.8.20.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.10.3.custom.min.css"/>">
    <title>出货管理</title>
    <script type="text/javascript">
        function showPros(proCode) {
            var $proLi = $("#proLi");
            $proLi.html("<li>货品编号：</li>");
            var proCodeArray = proCode.split(",");
            $.each(proCodeArray, function (o, item) {

                $proLi.append("<li>" + item + "</li>");
            });
            J.ShowDialogButton("proList_dialog", "货品编号列表", {
                "确定": function () {
                    $(this).dialog("close");
                }
            });
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div style="padding: 20px;display: none;" id="proList_dialog">
    <ul id="proLi">
        <li></li>
    </ul>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>出货管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/delivery/deliverList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    搜索关键字：<input style="width: 150px;" name="managerKey" id="managerKey" placeholder="出库人账户/姓名" value="${managerKey}" class="hasDatepicker">
                                    <input style="width: 150px;" name="shipKey" id="shipKey" placeholder="收件人手机/姓名" value="${shipKey}" class="hasDatepicker"><br><br>
                                    创建时间：<input type="text" id="beginTime" name="beginTime" placeholder="起始时间" value="${beginTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true})"/>~
                                    <input type="text" id="endTime" name="endTime" placeholder="结束时间" value="${endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"/>
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 20px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/delivery/deliverList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
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
                            <th align="center" rowspan="1" colspan="1">订单号</th>
                            <th align="center" rowspan="1" colspan="1">出货人</th>
                            <th align="center" rowspan="1" colspan="1">收件人</th>
                            <th align="center" rowspan="1" colspan="1">收件人手机</th>
                            <th align="center" rowspan="1" colspan="1">状态</th>
                            <th align="center" rowspan="1" colspan="1">出货时间</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="deliverBean">
                            <tr height="28px" class="odd">
                                <td align="center">${deliverBean.orderBean.orderId}</td>
                                <td align="center">${deliverBean.managerBean.name}</td>
                                <td align="center">${deliverBean.orderBean.shipName}</td>
                                <td align="center">${deliverBean.orderBean.shipMobile}</td>
                                <td align="center">${deliverBean.deliverStatus==0?"待发货":deliverBean.deliverStatus==1?"已作废":"已发货"}</td>
                                <td align="center"><fmt:formatDate value="${deliverBean.addTime}" type="both"/></td>
                                <td align="center">
                                    <a href="javascript:showPros('${deliverBean.proCode}')">货品编号列表</a>
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
<script type="text/javascript" src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js" />"></script>