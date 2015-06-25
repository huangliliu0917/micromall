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
    <script type="text/javascript" src="<c:url value="/resources/scripts/ajaxfileupload.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/admin.upload.js" />"></script>
    <title>编辑商品</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var agentId = ${agentId};
        var ajaxUrl = "<c:url value="/agentApi/" />";
        var returnUrl = "<c:url value="/agent/agentList" />"
        var superAgentId = "${agentBean.superAgentId}";
        var uploadUrl = "<c:url value="/upload" />";

    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/agent/admin.agent.js" />"></script>
    <script type="text/javascript">
        $(function () {
            agentHandler.init(ajaxUrl);
            <c:if test="${agentId>0}">
            $("#agentMobile").attr("disabled", "disabled");
            agentHandler.setSuperAgent(${agentBean.agentLevel.levelId}, superAgentId);

            $("#previewCardImg").show();
            $("#agentChannel").val("${agentBean.agentChannel}");
            </c:if>
        });

        function uploadImg() {
            $("#agentCardImg").val("");
            $.jBox.tip("正在上传...", "loading");
            fileUpload(null, uploadUrl, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("上传成功", "success");
                    $("#agentCardImg").val(json.file);
                    $("#previewCardImg").show();
                    $("#previewCardImg").attr("src", json.fileUri);
                } else {
                    $.jBox.tip("上传失败", "error");
                }
            });
        }
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
                                    <select ${agentId>0?"disabled='disabled'":""} id="agentLevel">
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
                                    <span class="title"><i class="red">*</i>身份证：</span>
                                    <input type="text" onkeydown="J.CertainNumber(event)" class="text" id="agentCardId" value="${agentBean.agentCardId}">
                                </li>
                                <li style="width: 500px;">
                                    <span class="title"><i class="red">*</i>手持身份证照片：</span>
                                    <input type="file" id="btnFile" name="btnFile" hidden="hidden" onchange="uploadImg()"/>
                                    <input type="hidden" id="agentCardImg" readonly="readonly" style="width: 300px" value="${agentBean.agentCardImg}"/>
                                    <img id="previewCardImg" style="display: none;width: 200px;height: 130px;" src="${uploadResourceServer.resourceUri(agentBean.agentCardImg)}"/>

                                    <div class="fg-button clearfix" style="float:right;">
                                        <a href="javascript:$('#btnFile').click();">上传图片</a>
                                    </div>
                                </li>
                                <li>
                                    <span class="title">覆盖地区：</span>
                                    <input type="text" class="text" id="agentArea" value="${agentBean.agentArea}">
                                </li>
                                <li>
                                    <span class="title">销售渠道：</span>
                                    <select id="agentChannel">
                                        <option value="未选择">请选择</option>
                                        <option value="淘宝">淘宝</option>
                                        <option value="微信">微信</option>
                                    </select>
                                </li>
                                <li>
                                    <span class="title">淘宝Id：</span>
                                    <input type="text" class="text" id="agentTaobaoId" value="${agentBean.agentTaobaoId}">
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

