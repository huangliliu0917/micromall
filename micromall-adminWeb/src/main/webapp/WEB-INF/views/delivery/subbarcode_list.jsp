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
    <title>货品编码管理</title>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>货品副码列表
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
                                        <a href="javascript:window.location.href=document.referrer;">返回</a>
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
                            <th align="center" rowspan="1" colspan="1">副码</th>
                            <th align="center" rowspan="1" colspan="1">副码条码</th>
                            <th align="center" rowspan="1" colspan="1">生成时间</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${subBarCodeBeans}" var="subBarCodeBean">
                            <tr height="28px" class="odd">
                                <td align="center">${subBarCodeBean.mainBar.mainCode}</td>
                                <td align="center">${subBarCodeBean.subCode}</td>
                                <td align="center"><img src="${uploadResourceServer.resourceUri(subBarCodeBean.subCodeImg)}"/></td>
                                <td align="center"><fmt:formatDate value="${subBarCodeBean.addTime}" type="both"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <!---内容---->
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>