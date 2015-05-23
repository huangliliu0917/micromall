<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/22
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/weishang.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jquery.utils.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/prompt/jquery.prompt.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/scripts/prompt/prompt.css" />">
    <script type="text/javascript" src="<c:url value="/resources/scripts/loading/jquery.loading-0.1.js" />"></script>
    <title>我的代理商</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        $(function () {
            $("#searchBtn").click(function () {
                $("#searchForm").submit();
            })
        })

        function showMore(agentId) {
            $("#selAgentId").val(agentId);
            $("#more_dialog").show();
        }

        function closeMore() {
            $("#selAgentId").val("");
            $("#more_dialog").hide();
        }

        function goUri(url) {
            window.location.href = url + $("#selAgentId").val();
        }

        function setDelete() {
            var selAgentId = $("#selAgentId").val();
            var prompt = new Prompt({
                promptId: "content1",
                buttons: {
                    "确定": function () {
                        prompt.hidePrompt();
                        loading.show("正在操作");
                        J.GetJsonRespons("<c:url value="/agentApi/setDelete" />", {
                            agentId: selAgentId,
                            customerId: customerId
                        }, function (json) {
                            loading.close();
                            if (json.result == 1) {
                                SimplePrompt.showPromptWithFunc("操作成功", function () {
                                    SimplePrompt.hide();
                                    window.location.reload();
                                });
                            } else {
                                SimplePrompt.showPrompt("操作失败");
                            }
                        }, function () {

                        }, J.PostMethod);
                    },
                    "取消": function () {
                        prompt.hidePrompt();
                    }
                }
            });
            prompt.showPrompt("确定要删除该代理商？");
        }
    </script>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div id="content1"></div>
<!---------------------/////////////////------------------------------------>

<div style="background-color:#545454;height:44px;">
    <p onclick="javascript:window.location.href='<c:url value="/agentEdit?customerId=${customerId}"/>'" style="color:#fff; float:left;margin-top: 10px;margin-left: 10px;">新增代理</p>

    <form id="searchForm" action="<c:url value="/agentList" />">
        <p style=" float:right;margin-top:5px;margin-right: 10px;">
            <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
            <input type="hidden" value="${customerId}" name="customerId" id="customerId"/>
            <input type="hidden" value="${superAgentId}" name="superAgentId" id="superAgentId"/>

            <input style="height: 32px;background-color: #545454;border: solid 1px #7E7E7E;color: #fff;padding-left: 7px;width: 123px;" type="" id="searchKey" name="searchKey" value="${searchKey}" placeholder="登录名/姓名">
            <span id="searchBtn"><img class="imgg" src="<c:url value="/resources/images/untitled10.png" />" width="20px"></span>
        </p>
    </form>

</div>

<p style="clear:both;"></p>


<!---------------------/////////////////------------------------------------>

<div id="leftTabBoxs" class="tabBoxs">
    <div class="bdd">
        <ul>
            <c:forEach items="${pageInfo.getContent()}" var="agentBean">
                <li class="tttt">
                    <div class="conn">
                        <div class="pic">
                            <a href="javascript:showMore(${agentBean.agentId})"><img src="<c:url value="/resources/images/wyNewPic.png" />"/></a>
                        </div>
                        <p style="color:#000; margin-bottom:2px;line-height: 54px;font-size: 20px;">${agentBean.agentAccount}</p>

                        <p style="color:#000;line-height: 20px;">姓名：${agentBean.name}</p>

                        <p style="color:#000;line-height: 20px;">级别：${agentBean.agentLevel.levelName}</p>
                            <%--<p style="color:#000;">下线代理：4</p>--%>
                    </div>
                    <p style="height:20px"></p>

                    <p style="height:4px; border-top:1px dotted #ddd; clear:both"></p>

                    <div class="DDDH"><span style="float:left; color:#999">${agentBean.addTime}</span></div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


<!---------------------/////////弹窗////////------------------------------------>

<div id="more_dialog" style="background-color: #fff;position: fixed;bottom: 0;left: 0;right: 0;margin: auto;display: none;">
    <input type="hidden" id="selAgentId"/>

    <p onclick="javascript:closeMore()" style="color: #A7A7A7;font-size: 30px;float: right;margin-right: 10px;">×</p>

    <p style="clear:both"></p>

    <div class="ws_wrap">
        <div class="add_wei">
            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a href="javascript:goUri('<c:url value="/agentList?customerId=${customerId}&superAgentId=" />')" class="wsws_back button">查看下级代理</a>
            </p>

            <p style="height:20px"></p>

            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a href="javascript:goUri('<c:url value="/agentEdit?customerId=${customerId}&editAgentId=" />')" class="wsws_back button">修改信息</a>
            </p>

            <p style="height:20px"></p>

            <p class="command" style="background-color:transparent; padding:0px 20%;">
                <a href="javascript:setDelete()" class="wsws_back button" id="add">删除</a>
            </p>
        </div>
    </div>
</div>
<!---------------------/////////弹窗end////////------------------------------------>
</body>
</html>

