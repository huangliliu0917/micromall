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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jQuery.md5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.8.20.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jqueryui/jquery-ui-1.10.3.custom.min.css"/>">
    <title>出库管理员列表</title>
    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/managerApi/" />";
        var managerHandler = {
            add: function () {
                $("#account").removeAttr("readonly");
                J.ShowDialog("edit_dialog", "新增管理员", function () {
                    var account = $.trim($("#account").val());
                    var name = $.trim($("#name").val());
                    if (account.length == 0) {
                        $.jBox.tip("请输入账户名");
                        return;
                    }
                    if (name == 0) {
                        $.jBox.tip("请输入姓名");
                        return;
                    }
                    $.jBox.tip("正在保存...", "loading");
                    var requestData = {
                        account: account,
                        name: name,
                        password: $.md5("123456")
                    }
                    J.GetJsonRespons(ajaxUrl + "addManager", requestData, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("保存成功", "success");
                            window.location.reload();
                        } else if (json.result == 2) {
                            $.jBox.tip("该账户已存在");
                        } else {
                            $.jBox.tip("保存失败", "error");
                        }
                    }, function () {
                    }, J.PostMethod)
                }, function () {
                    $(this).dialog("close");
                })
            },
            updateName: function (managerId, account) {
                $("#account").attr("readonly", "readonly");
                $("#account").val(account);
                J.ShowDialog("edit_dialog", "编辑", function () {
                    var name = $.trim($("#name").val());
                    if (name == 0) {
                        $.jBox.tip("请输入姓名");
                        return;
                    }
                    $.jBox.tip("正在保存...", "loading");
                    var requestData = {
                        newName: name,
                        managerId: managerId
                    }
                    J.GetJsonRespons(ajaxUrl + "updateName", requestData, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("保存成功", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("保存失败", "error");
                        }
                    }, function () {
                    }, J.PostMethod);
                }, function () {
                    $(this).dialog("close");
                })

            },
            resetPassword: function (managerId) {
                jBox.confirm('确定要重置该管理员的密码？密码将被重置为123456', '提示', function (v, h, f) {
                    if (v == "ok") {
                        $.jBox.tip("正在重置...", "loading");
                        J.GetJsonRespons(ajaxUrl + "resetPassword", {
                            managerId: managerId,
                            newPass: $.md5("123456")
                        }, function (json) {
                            if (json.result == 1) {
                                $.jBox.tip("密码已重置", "success");
                            } else {
                                $.jBox.tip("操作失败");
                            }
                        }, function () {
                        }, J.PostMethod)
                    }
                    return true;
                });
            },
            deleteManager: function (managerId) {
                jBox.confirm('确定要删除该管理员？', '提示', function (v, h, f) {
                    if (v == "ok") {
                        $.jBox.tip("正在删除...", "loading");
                        J.GetJsonRespons(ajaxUrl + "delete", {managerId: managerId}, function (json) {
                            if (json.result == 1) {
                                $.jBox.tip("删除成功", "success");
                                window.location.reload();
                            } else {
                                $.jBox.tip("删除失败");
                            }
                        }, function () {
                        }, J.PostMethod)
                    }
                    return true;
                });
            }
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div style="padding: 20px;display: none;" id="edit_dialog">
    <div class="cnt form">
        <!---->
        <form>
            <div class="waps-cpanel-content">
                <div class="waps-step-web plb5" style="width: 362px;">
                    <ul>
                        <li>
                            <span class="title">账户名：</span><br>
                            <input id="account" type="text"/> <span style="color: red;">（初始密码为123456）</span>
                        </li>
                        <li>
                            <span class="title">姓名：</span><br>
                            <input id="name" type="text"/>
                        </li>
                    </ul>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;">
                <i class="fa  fa-file-o"></i>出库管理员列表
            </p>
        </div>
        <div class="cnt-wp">
            <div class="cnt form">
                <p style="clear:both"></p>

                <form id="searchForm" method="get" action="<c:url value="/delivery/managerList" />">
                    <div class="day">
                        <!--时间-->
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="55%">
                                    <input type="hidden" value="${pageIndex}" name="pageIndex" id="pageIndex"/>
                                    搜索关键字：<input style="width: 200px;" name="searchKey" id="searchKey" placeholder="账户名/姓名" value="${searchKey}" class="hasDatepicker">
                                    <input type="submit" class="copybtn" value="查询" style="margin-left: 20px;">
                                    <input type="button" onclick="javascript:window.location.href='<c:url value="/delivery/managerList"/>'" class="copybtn" value="显示全部" style="margin-left: 10px;">
                                </td>

                                <td align="right">
                                    <div class="fg-button clearfix" style="float:right">
                                        <a href="javascript:managerHandler.add()">添加管理员</a>
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
                            <th align="center" rowspan="1" colspan="1">账号</th>
                            <th align="center" rowspan="1" colspan="1">姓名</th>
                            <th align="center" rowspan="1" colspan="1">添加时间</th>
                            <th align="center" rowspan="1" colspan="1">操作</th>
                        </tr>
                        </thead>
                        <!---表头---->
                        <tbody>
                        <c:forEach items="${pageInfo.getContent()}" var="managerBean">
                            <tr height="28px" class="odd">
                                <td align="center">${managerBean.account}</td>
                                <td align="center">${managerBean.name}</td>
                                <td align="center"><fmt:formatDate value="${managerBean.addTime}" type="both"/></td>
                                <td align="center">
                                    <a href="javascript:managerHandler.updateName(${managerBean.id},'${managerBean.account}')">修改</a> |
                                    <a href="javascript:managerHandler.resetPassword(${managerBean.id})">重置密码</a> |
                                    <a href="javascript:managerHandler.deleteManager(${managerBean.id})">删除</a>
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