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
    <title>货品批次</title>
    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/barCodeApi/" />";
        var barCodeHandler = {
            createBarCode: function () {
                J.ShowDialog("create_dialog", "货品编码生成", function () {
                    var mainCodeNum = $.trim($("#mainCodeNum").val());
                    var subCodeNum = $.trim($("#subCodeNum").val());
                    if (mainCodeNum.length == 0) {
                        $.jBox.tip("请输入主码数量");
                        return;
                    }
                    if (subCodeNum.length == 0) {
                        $.jBox.tip("请输入副码数量");
                        return;
                    }
                    $.jBox.tip("正在生成货品编号，这可能需要一点时间...", "loading");
                    var requestData = {
                        mainCodeNum: mainCodeNum,
                        subCodeNum: subCodeNum,
                        goodId: ${goodId}
                    }
                    J.GetJsonRespons(ajaxUrl + "createBarCode", requestData, function (json) {
                        if (json.result > 0) {
                            $.jBox.tip("生成成功", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("生成失败，请重试", "error");
                        }
                    }, function () {
                        $.jBox.tip("生成失败，请重试", "error");
                    }, J.PostMethod);
                }, function () {
                    $(this).dialog("close");
                })
            },
            printCode: function (batchId) {

                if (0 ==${printed}) {
                    J.ShowDialog("message_dialog", "提示", function () {
                        $.jBox.tip("正在设置...", "loading");
                        J.GetJsonRespons(ajaxUrl + "updatePrinted", {batchCodeId: batchId}, function (json) {
                            if (json.result == 1) {
                                $.jBox.tip("设置成功", "success");
                                window.location.reload();
                            } else {
                                $.jBox.tip("设置失败", "error");
                            }
                        }, function () {
                        }, J.PostMethod);
                    }, function () {
                        $(this).dialog("close");
                    });
                }
                window.open("<c:url value="/delivery/printBarCode?batchCodeId=" />" + batchId);
            },
            deleteBatchCode: function (batchId) {
                jBox.confirm('确定要删除该批次，该批次下的所有记录都将被删除？', '提示', function (v, h, f) {
                    if (v == "ok") {
                        $.jBox.tip("正在删除...", "loading");
                        J.GetJsonRespons(ajaxUrl + "deleteBarCode", {batchCodeId: batchId}, function (json) {
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
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="create_dialog" style="padding: 20px;display: none;">
    <ul>
        <li style="height: 60px;">
            主码数量：<input type="text" style="width: 100px;" onkeydown="J.CertainNumber(event)" id="mainCodeNum"/>
        </li>
        <li style="height: 60px;">
            每个主码的副码数量：<input type="text" style="width: 100px;" onkeydown="J.CertainNumber(event)" id="subCodeNum"/>
        </li>
    </ul>
</div>
<div id="message_dialog" style="padding: 20px;display: none;">
    是否打印成功并将该批次设为已打印？
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>货品批次（${goodName}）
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
                                    <div class="fg-button clearfix" style="float:left">
                                        <a class="${printed==0?"on":""}" href="javascript:window.location.href='<c:url value="/delivery/batchCodeList?goodId=${goodId}&goodName=${goodName}&printed=0" />'">未打印</a>
                                    </div>
                                    <div class="fg-button clearfix" style="float:left">
                                        <a class="${printed==1?"on":""}" href="javascript:window.location.href='<c:url value="/delivery/batchCodeList?goodId=${goodId}&goodName=${goodName}&printed=1" />'">已打印</a>
                                    </div>
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="<c:url value="/good/goodList"/>">返回商品列表</a>
                                    </div>
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="javascript:barCodeHandler.createBarCode()">生成条码</a>
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
                            <th align="center" rowspan="1" colspan="1">批次</th>
                            <th align="center" rowspan="1" colspan="1">主码数量</th>
                            <th align="center" rowspan="1" colspan="1">副码数量</th>
                            <th align="center" rowspan="1" colspan="1">状态</th>
                            <th align="center" rowspan="1" colspan="1">生成时间</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="batchCodeBean">
                            <tr height="28px" class="odd">
                                <td align="center">${batchCodeBean.batchCode}</td>
                                <td align="center">${batchCodeBean.mainCodeNum}</td>
                                <td align="center">${batchCodeBean.subCodeNum}</td>
                                <td align="center">${batchCodeBean.printed==0?"未打印":"已打印"}</td>
                                <td align="center"><fmt:formatDate value="${batchCodeBean.addTime}" type="both"/></td>
                                <td align="center">
                                    <a href="<c:url value="/delivery/barCodeList?batchCodeId=${batchCodeBean.id}&goodName=${goodName}&goodId=${goodId}" />">查看</a>|
                                    <a href="javascript:barCodeHandler.printCode(${batchCodeBean.id})">打印</a> |
                                    <a href="javascript:barCodeHandler.deleteBatchCode(${batchCodeBean.id})">删除</a>
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
        window.location.href = "<c:url value="/delivery/batchCodeList?goodId=${goodId}&printed=${printed}&goodName=${goodName}&pageIndex=" />" + pageIndex;
    }
</script>