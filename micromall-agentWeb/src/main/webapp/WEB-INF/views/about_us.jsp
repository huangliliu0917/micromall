<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/21
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="">
    <link rel="stylesheet" href="css/common.css">
    <title>关于${configBean.title}</title>
    <style>
        #content img {
            width: 100%;
        }
    </style>
</head>
<body>
<p style="text-align: center;font-size: 20px;">关于${configBean.title}</p>

<div id="content">
    ${configBean.aboutUs}
</div>
</body>
</html>
