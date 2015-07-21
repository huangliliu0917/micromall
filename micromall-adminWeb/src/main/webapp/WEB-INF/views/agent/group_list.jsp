<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/18
  Time: 15:19
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

    <title>代理商等级管理</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/groupApi/" />";

        var groupHandler = {
            saveGroup: function (groupId, groupName, groupDesc) {
                $("#groupName").val(groupName);
                $("#groupDesc").val(groupDesc);
                J.ShowDialog("edit_dialog", groupId > 0 ? "编辑分组" : "新增分组", function () {
                    groupHandler.submitForm(groupId);
                }, function () {
                    $(this).dialog("close");
                })
            },
            deleteGroup: function (groupId) {
                jBox.confirm('确定要删除该分组？', '提示', function (v, h, f) {
                    if (v == "ok") {

                    }
                    return true;
                });
            },
            submitForm: function (groupId) {
                var groupName = $.trim($("#groupName").val());
                var groupDesc = $.trim($("#groupDesc").val()).replace(/\r/g, "").replace(/\n/g, "");
                if (groupName.length == 0) {
                    $.jBox.tip("请输入分组名称");
                    return;
                }
                if (groupDesc.length == 0) {
                    $.jBox.tip("请输入分组描述");
                    return;
                }
                $.jBox.tip("正在保存...", "loading");
                var requestData = {
                    groupId: groupId,
                    groupName: groupName,
                    groupDesc: groupDesc
                }
                J.GetJsonRespons(ajaxUrl + "save", requestData, function (json) {
                    if (json == 1) {
                        $.jBox.tip("保存成功", "success");
                        window.location.reload();
                    } else {
                        $.jBox.tip("保存失败", "error");
                    }
                }, function () {
                }, J.PostMethod);
            }
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="edit_dialog" style="padding:20px;display: none;">
    <span>分组名称：</span>
    <input type="text" id="groupName" style="width: 200px;"/>
    <br/><br/>
    <span>分组描述</span>
    <textarea id="groupDesc"></textarea>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>代理商分组管理
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <form>
                    <!--提示--->
                    <p style="clear:both"></p>

                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                </td>

                                <td align="right">


                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="javascript:groupHandler.saveGroup(0,'','')">添加</a>
                                    </div>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <!---表格--->
                    <div class="dataTables_wrapper">
                        <table width="100%" class="table_appss tablept5" id="table_apps">
                            <thead>
                            <tr class="sdkbar" style="font-weight:bold;">
                                <th align="center" rowspan="1" colspan="1"></th>
                                <th align="center" rowspan="1" colspan="1">分组名称</th>
                                <th align="center" rowspan="1" colspan="1">分组描述</th>
                                <th align="center" rowspan="1" colspan="1">操作</th>
                            </tr>
                            </thead>
                            <!---表头---->
                            <tbody>
                            <c:forEach items="${groupList}" var="group" varStatus="index">
                                <tr height="28px" class="odd">
                                    <td align="center">${index.count}
                                    </td>
                                    <td align="center">${group.groupName}
                                    </td>
                                    <td align="center">${group.groupDesc}
                                    </td>
                                    <td align="center">
                                        <a href="javascript:groupHandler.saveGroup(${group.groupId},'${group.groupName}','${group.groupDesc}')">编辑</a> |
                                        <a href="javascript:groupHandler.deleteGroup(${group.groupId})">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <!---内容---->
                        </table>
                        <p style=" clear:both"></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
