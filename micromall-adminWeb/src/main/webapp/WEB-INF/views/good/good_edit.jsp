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
    <script type="text/javascript" src="<c:url value="/resources/scripts/jBox/jquery.jBox-2.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/scripts/jBox/Skins/Green/jbox.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/scripts/ajaxfileupload.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/admin.upload.js" />"></script>
    <title>编辑商品</title>
    <script type="text/javascript">
        var goodId = ${goodId};
        var ajaxUrl = "<c:url value="/goodApi/" />";
        var returnUrl = "<c:url value="/good/goodList" />";
        var uploadUrl = "<c:url value="/upload" />"
        $(function () {
            if (goodId > 0) {
                $("#previewImg").show();
                //设置价格
                <c:forEach items="${goodBean.priceViewModelList}" var="viewModel">
                $("#level${viewModel.levelId}_price").val(${viewModel.price});
                </c:forEach>

                var groups = "${goodBean.groups}";
                if (groups == "all") {
                    $("#checkAll").attr("checked", "checked");
                    $("input[name='chkGroup']").attr("disabled", "disabled");
                } else {
                    $("#checkAll").removeAttr("checked");
                    var groupArray = groups.substring(1, groups.length - 1).split("|");
                    $.each(groupArray, function (o, item) {
                        $("input[name='chkGroup'][value='" + item + "']").attr("checked", "checked");
                    })
                }
            }
            $("#checkAll").change(function () {
                if ($(this).attr("checked")) {
                    $("input[name='chkGroup']").attr("disabled", "disabled");
                } else {
                    $("input[name='chkGroup']").removeAttr("disabled");
                }
            })
        })
    </script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/admin/good/admin.good.js" />"></script>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;"><i class="fa  fa-file-o"></i>商品编辑 </p>

        </div>
        <div class="cnt-wp">
            <div class="fg-button clearfix" style="float:right;display: block;" id="backBtn">
                <a href="javascript:window.location.href=returnUrl">返回商品列表</a>
            </div>
            <form>
                <div class="cnt form">
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <span class="title"><i class="red">*</i>商品名称：</span>
                                    <input type="text" class="text" value="${goodBean.goodName}" id="goodName"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>商品编号：</span>
                                    <input type="text" readonly="readonly" class="text" id="goodCode" value="${goodBean==null?createCode:goodBean.goodCode}"/>
                                </li>
                                <li style="width: 500px;">
                                    <span class="title"><i class="red">*</i>商品图片：</span>
                                    <input type="file" id="btnFile" name="btnFile" hidden="hidden" onchange="goodHandler.uploadImg()"/>
                                    <input type="hidden" id="goodImg" readonly="readonly" style="width: 300px" value="${goodBean.goodImg}"/>
                                    <img id="previewImg" style="height: 130px;width: 130px;display: none;" src="${uploadResourceServer.resourceUri(goodBean.goodImg)}"/>

                                    <div class="fg-button clearfix" style="float:right;">
                                        <a href="javascript:$('#btnFile').click();">上传图片</a>
                                    </div>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>销售价：</span>
                                    <input type="text" class="text" id="price" value="${goodBean.price}"/>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>代理商进价：</span>
                                    <ul style="margin-left: 20px;">
                                        <c:forEach items="${levelList}" var="levelBean" varStatus="index">
                                            <li>${levelBean.levelName}：
                                                <input type="text" id="level${levelBean.levelId}_price" class="text priceClass" value=""/>
                                                <input type="hidden" value="${levelBean.levelId}" class="levelClass"/>
                                                <input type="hidden" value="${levelBean.levelName}" class="levelNameClass"/></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <li>
                                    <span class="title"><i class="red"></i>可查看分组：</span>
                                    <input value="0" id="checkAll" checked="checked" type="checkbox"/>全部
                                    <c:forEach items="${groupList}" var="group">
                                        &nbsp; &nbsp; &nbsp;<input name="chkGroup" type="checkbox" value="${group.groupId}"/>${group.groupName}
                                    </c:forEach>
                                    <br>
                                    <span style="color:red;">tip:指定分组的代理商才可以查看</span>
                                </li>
                                <li>
                                    <span class="title"><i class="red">*</i>描述：</span>
                                    <br><br>
                                    <textarea style="width: 401px;height: 136px;" id="goodDesc">${goodBean.goodDesc}</textarea>
                                </li>
                            </ul>
                        </div>
                        <div style="padding:20px 5% 20px 5%">

                            <input type="button" value="保存" onclick="javascript:goodHandler.saveGood()" class=" copybtn6">
                            <input type="reset" value="重置" class="copybtn7">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

