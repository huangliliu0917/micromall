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

    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/orderApi/" />";
        $(function () {
            $("#orderStatus").val(${searchViewModel.orderStatus});
        })
        function shipOrder(orderId) {
            J.ShowDialog("confirmShip_dialog", "确认发货", function () {
                confirmShip(orderId);
            }, function () {
                $(this).dialog("close");
            });
        }

        function addPro() {
            var proHtml = $("#pro_template").html();
            var length = $(".proCode").length;
            proHtml = proHtml.replace(/{id}/g, length + 1);
            $("#proCodePanel").append(proHtml);
        }

        function removePro(id) {
            $("#code" + id).remove();
        }

        function confirmShip(orderId) {
            var index = true;
            var proCodes = "";
            var $proCodes = $(".proCodes");
            $proCodes.each(function (o, item) {
                var code = $.trim($(item).val());
                if (code.length == 0) {
                    index = false;
                    $.jBox.tip("还有货号没有填写");
                    return false;
                }
                if (o == $proCodes.length - 1) {
                    proCodes += $(item).val();
                } else {
                    proCodes += $(item).val() + ",";
                }
            });
            if (!index) {
                return;
            }
            var shipInfo = $.trim($("#shipInfo").val());
            if (shipInfo.length == 0) {
                $.jBox.tip("请输入物流信息");
                return;
            }
            $.jBox.tip("正在提交...", "loading");
            var requestData = {
                orderId: orderId,
                proCodes: proCodes,
                shipInfo: shipInfo
            }

            J.GetJsonRespons(ajaxUrl + "confirmShip", requestData, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("确认发货成功", "success");
                    window.location.reload();
                } else {
                    $.jBox.tip("确认发货失败", "error");
                }
            }, function () {
            }, J.PostMethod);
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="confirmShip_dialog" style="padding: 20px;display: none;">
    <div class="cnt form">
        <!---->
        <form>
            <div class="waps-cpanel-content">
                <div class="waps-step-web plb5" style="width: 362px;">
                    <ul>
                        <li>
                            <span class="title">
                                请输入货号：
                                <a href="javascript:addPro()" id="addProCode" style="float: right"><span><img style="margin-top: -5px;float:right" src="<c:url value="/resources/images/jia.png"/>" width="27px"></span></a>
                            </span>
                            <br>

                            <div id="proCodePanel">
                                <div>
                                    <input type="text" class="text proCodes" value="" style="margin-top: 20px;width: 303px;"/>
                                </div>
                            </div>
                        </li>
                        <li>
                            <span class="title">请输入物流信息：</span><br>
                            <textarea id="shipInfo" style="width: 303px;height: 117px;margin-top: 20px;padding: 10px;" placeholder="物流名称：xxx；单号：xxxxxxxxx"></textarea>
                        </li>
                    </ul>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>订单列表
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/order/orderList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    订单号：<input size="10" name="orderId" id="orderId" value="${searchViewModel.orderId}" placeholder="订单号" class="hasDatepicker">
                                    收件人：<input size="10" name="shipName" id="shipName" value="${searchViewModel.shipName}" placeholder="收件人" class="hasDatepicker">
                                    收件人手机：<input size="10" name="shipMobile" id="shipMobile" value="${searchViewModel.shipMobile}" placeholder="收件人手机" class="hasDatepicker">
                                    订单状态：<select id="orderStatus" name="orderStatus">
                                    <option value="-1">全部</option>
                                    <option value="2">需要发货</option>
                                    <option value="0">未发货</option>
                                    <option value="1">已发货</option>
                                </select><br><br>
                                    创建时间：<input type="text" id="beginTime" name="beginTime" placeholder="起始时间" value="${searchViewModel.beginTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true})"/>~
                                    <input type="text" id="endTime" name="endTime" placeholder="结束时间" value="${searchViewModel.endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"/>
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 100px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/order/orderList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
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
                            <th align="center" rowspan="1" colspan="1">名称</th>
                            <th align="center" rowspan="1" colspan="1">收件人姓名</th>
                            <th align="center" rowspan="1" colspan="1">收件人手机</th>
                            <th align="center" rowspan="1" colspan="1">订单时间</th>
                            <th align="center" rowspan="1" colspan="1">订单状态</th>
                            <th align="center" rowspan="1" colspan="1">出库状态</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="orderBean">
                            <tr height="28px" class="odd">
                                <td align="center">${orderBean.orderId}</td>
                                <td align="center">${orderBean.orderName}</td>
                                <td align="center">${orderBean.shipName}</td>
                                <td align="center">${orderBean.shipMobile}</td>
                                <td align="center"><fmt:formatDate value="${orderBean.addTime}" type="both"/></td>
                                <td align="center">
                                        ${orderBean.orderStatus==1?"已发货":orderBean.realShipAgent==null?"<span style='color:red'>需要发货</span>":"未发货"}
                                </td>
                                <td align="center">
                                    <c:if test="${orderBean.realShipAgent==null}">
                                        ${orderBean.deliverStatus==0?"未出库":"已出库"}
                                    </c:if>
                                </td>
                                <td align="center">
                                        <%--<c:if test="${orderBean.realShipAgent==null&&orderBean.orderStatus==0}">--%>
                                        <%--<a href="javascript:shipOrder('${orderBean.orderId}')">发货</a> |--%>
                                        <%--</c:if>--%>
                                    <a href="<c:url value="/order/orderDetail?orderId=${orderBean.orderId}" />">订单详情</a>
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

<script type="text/html" id="pro_template">
    <div id="code{id}">
        <input type="text" class="text proCodes" value="" style="margin-top: 20px;width: 303px;"/>
        <a href="#" onclick="removePro({id})" style="float: right"><span><img style="margin-top: 23px;float:right" src="<c:url value="/resources/images/jian.png"/>" width="27px"></span></a>
    </div>
</script>
