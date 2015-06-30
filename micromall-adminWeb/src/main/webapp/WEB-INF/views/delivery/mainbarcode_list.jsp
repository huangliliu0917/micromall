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
    <title>货品编码管理</title>
    <script type="text/javascript">
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="create_dialog" style="padding: 20px;display: none;">
    <ul>
        <li style="height: 60px;">
            主码数量：<input type="text" style="width: 100px;" onkeydown="J.CertainNumber(event)" id="mainCodeNum"/>
        </li>
        <li style="height: 60px;">
            副码数量：<input type="text" style="width: 100px;" onkeydown="J.CertainNumber(event)" id="subCodeNum"/>
        </li>
    </ul>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>货品主码列表（${goodName}）
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
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="<c:url value="/delivery/batchCodeList?goodId=${goodId}&goodName=${goodName}"/>">返回批次列表</a>
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
                            <th align="center" rowspan="1" colspan="1">主码</th>
                            <th align="center" rowspan="1" colspan="1">主码条码</th>
                            <th align="center" rowspan="1" colspan="1">副码数量</th>
                            <th align="center" rowspan="1" colspan="1">生成时间</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="barCodeBean">
                            <tr height="28px" class="odd">
                                <td align="center">${barCodeBean.mainCode}</td>
                                <td align="center"><img src="${uploadResourceServer.resourceUri(barCodeBean.mainCodeImg)}"/></td>
                                <td align="center">${barCodeBean.subCodeNum}</td>
                                <td align="center"><fmt:formatDate value="${barCodeBean.addTime}" type="both"/></td>
                                <td align="center">
                                    <a href="<c:url value="/delivery/subBarCodeList?mainCodeId=${barCodeBean.id}" />">查看副码</a>
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
        window.location.href = "<c:url value="/delivery/barCodeList?goodId=${goodId}&printed=${printed}&goodName=${goodName}&pageIndex=" />" + pageIndex;
    }
</script>