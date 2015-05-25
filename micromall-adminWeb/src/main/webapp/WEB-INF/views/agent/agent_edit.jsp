<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 16:36
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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jQuery.md5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <title>编辑商品</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var agentId = ${agentId};
        var ajaxUrl = "<c:url value="/agentApi/" />";
        var returnUrl = "<c:url value="/agent/agentList" />"
        var superAgentId = "${agentBean.superAgentId}";

    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/agent/admin.agent.js" />"></script>
    <script type="text/javascript">
        $(function () {
            agentHandler.init(ajaxUrl);
            <c:if test="${agentId>0}">
            $("#agentMobile").attr("disabled", "disabled");
            agentHandler.setSuperAgent(${agentBean.agentLevel.levelId}, superAgentId);
            </c:if>
        });
    </script>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;"><i class="fa  fa-file-o"></i>代理商编辑 </p>

        </div>
        <div class="cnt-wp">
            <div class="fg-button clearfix" style="float:right;display: block;" id="backBtn">
                <a href="javascript:window.location.href=returnUrl">返回代理商列表</a>
            </div>
            <div class="cnt form">
                <!---->
                <form>
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <span class="title"><i class="red">*</i>代理人手机号：</span>
                                    <input type="text" class="text" value="${agentBean.agentAccount}" onkeydown="J.CertainNumber(event)" id="agentMobile"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>代理人姓名：</span>
                                    <input type="text" class="text" id="agentName" value="${agentBean.name}"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>登录密码：</span>
                                    <input type="password" class="text" id="agentPassword"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>确认登录密码：</span>
                                    <input type="password" class="text" id="confirmPass">
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>代理级别：</span>
                                    <select id="agentLevel">
                                        <option value="0">请选择</option>
                                        <c:forEach items="${levelList}" var="levelBean" varStatus="index">
                                            <option value="${levelBean.levelId}" ${agentBean.agentLevel.levelId==levelBean.levelId?"selected='selected'":""}>${levelBean.levelName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>上级代理：</span>
                                    <select id="superAgent">
                                        <option value="0">请选择</option>
                                    </select>
                                </li>
                                <li>
                                    <span class="title">覆盖地区：</span>
                                    <input type="text" class="text" id="agentArea" value="${agentBean.agentArea}">
                                </li>
                                <li>
                                    <span class="title">销售渠道：</span>
                                    <input type="text" class="text" id="agentChannel" value="${agentBean.agentChannel}">
                                </li>
                                <li>
                                    <span class="title">身份证：</span>
                                    <input type="text" onkeydown="J.CertainNumber(event)" class="text" id="agentCardId" value="${agentBean.agentCardId}">
                                </li>
                                <li>
                                    <span class="title">qq号码：</span>
                                    <input type="text" onkeydown="J.CertainNumber(event)" class="text" id="agentQQ" value="${agentBean.agentQQ}">
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>微信号：</span>
                                    <input type="text" class="text" id="agentWeixin" value="${agentBean.agentWeixin}">
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>收货地址：</span>
                                    <input type="text" class="text" id="agentAddr" value="${agentBean.agentAddr}">
                                </li>
                            </ul>
                        </div>
                        <div style="padding:20px 5% 20px 5%">

                            <input type="button" value="保存" onclick="javascript:agentHandler.saveAgent()" class=" copybtn6">
                            <input type="reset" value="重置" class="copybtn7">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

