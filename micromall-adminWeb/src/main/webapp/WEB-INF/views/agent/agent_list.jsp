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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.8.20.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.10.3.custom.min.css"/>">
    <title>代理商管理</title>

    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/agentApi/" />";
        var superAgentId = ${superAgentId};
        $(function () {
            agentHandler.init(ajaxUrl);
            $("#agentLevel").val(${searchParams.agentLevel});
            $("#agentStatus").val(${searchParams.agentStatus});
            if (superAgentId > 0) {
                $("#backBtn").show();
            }
        })
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/agent/admin.agent.js" />"></script>
</head>
<body style="background-color:#e4e7ea">
<div id="setstatus_dialog" style="padding: 20px;display: none;padding-left: 100px;padding-right: 100px;">
    审核：<select id="setAgentStatus">
    <option value="-1">请选择</option>
    <option value="1">通过</option>
    <option value="2">未通过</option>
</select>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>代理商管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/agent/agentList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    <input type="hidden" value="${superAgentId}" name="superAgentId"/>
                                    登录名：<input size="10" name="agentAccount" id="agentAccount" value="${searchParams.agentAccount}" class="hasDatepicker">
                                    姓名：<input size="10" name="agentName" id="agentName" value="${searchParams.agentName}" class="hasDatepicker">
                                    等级：<select id="agentLevel" name="agentLevel">
                                    <option value="0">请选择</option>
                                    <c:forEach items="${levelList}" var="levelBean" varStatus="index">
                                        <option value="${levelBean.levelId}">${levelBean.levelName}
                                        </option>
                                    </c:forEach>
                                </select>
                                    审核状态：<select id="agentStatus" id="agentStatus">
                                    <option value="-1">请选择</option>
                                    <option value="0">待审核</option>
                                    <option value="1">审核通过</option>
                                    <option value="2">审核失败</option>
                                </select>
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 100px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/agent/agentList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right;display: none;" id="backBtn">
                                        <a href="javascript:window.location.href=document.referrer">返回</a>
                                    </div>
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="<c:url value="/agent/agentEdit" />">添加代理商</a>
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
                            <th align="center" rowspan="1" colspan="1">手机</th>
                            <th align="center" rowspan="1" colspan="1">证书</th>
                            <th align="center" rowspan="1" colspan="1">姓名</th>
                            <th align="center" rowspan="1" colspan="1">代理级别</th>
                            <th align="center" rowspan="1" colspan="1">审核</th>
                            <th align="center" rowspan="1" colspan="1">微信号</th>
                            <th align="center" rowspan="1" colspan="1">状态</th>
                            <th align="center" rowspan="1" colspan="1">创建日期</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->


                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="agentBean">
                            <tr height="28px" class="odd">
                                <td align="center">${agentBean.agentAccount}
                                </td>
                                <td align="center"><img src="${agentBean.agentCertificate}"/></td>
                                <td align="center">${agentBean.name}</td>
                                <td align="center">${agentBean.agentLevel.levelName}</td>
                                <td align="center">${agentBean.agentStatus==0?"待审核":agentBean.agentStatus==1?"审核通过":"审核失败"}</td>
                                <td align="center">${agentBean.agentWeixin}</td>
                                <td align="center">${agentBean.isDelete==0?"<span style='color:green'>活动</span>":"<span style='color:red'>冻结</span>"}</td>
                                <td align="center">${agentBean.addTime}</td>
                                <td align="center">
                                    <a href="<c:url value="/agent/agentList?superAgentId=${agentBean.agentId}" />">查看下级代理</a> |
                                    <a href="<c:url value="/agent/agentEdit?agentId=${agentBean.agentId}" />">编辑</a> |
                                    <a href="javascript:agentHandler.setAgentStatus(${agentBean.agentId},${agentBean.agentStatus})">审核</a> |
                                    <a href="javascript:agentHandler.setDelete(${agentBean.agentId},${agentBean.isDelete});">${agentBean.isDelete==0?"冻结":"恢复"}</a>
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