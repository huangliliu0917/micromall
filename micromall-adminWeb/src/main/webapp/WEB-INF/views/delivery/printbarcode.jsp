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
    <title>打印编码</title>
    <script type="text/javascript">
        function printCode() {
            document.body.innerHTML = $("#printPanel").html();
            window.print();
        }
    </script>
</head>
<body style="background-color:#fff">
<div class="contentpanel">
    <div class="fg-button clearfix" style="float:right;margin-left: 20px;">
        <a href="javascript:printCode();">打印</a>
    </div>
    <div id="printPanel">
        <c:forEach items="${list}" var="mainBarCode">
            <div style="text-align: center;width: 200px; margin: 10px 0px">
                <p>${mainBarCode.goodBean.goodName}（主码）</p>

                <p><img src="${uploadResourceServer.resourceUri(mainBarCode.mainCodeImg)}" style="width: 200px;height: 50px"></p>

                <p>${mainBarCode.mainCode}</p>
            </div>

            <div>
                <c:forEach items="${mainBarCode.subBarCodeBeans}" var="subBarCode">
                    <div style="text-align: center;width: 200px; float: left; margin: 10px 0px">
                        <p>${mainBarCode.goodBean.goodName}（副码）</p>

                        <p><img src="${uploadResourceServer.resourceUri(subBarCode.subCodeImg)}" style="width: 200px;height: 50px"></p>

                        <p>${subBarCode.subCode}</p>
                    </div>
                </c:forEach>
            </div>
            <p style="border-bottom: 1px solid #ddd;clear: both"></p>
        </c:forEach>
    </div>
</div>

</body>
</html>