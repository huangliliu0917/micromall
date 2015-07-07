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
    <script type="text/javascript" src="<c:url value="/resources/scripts/kindeditor-4.1.10/kindeditor-all.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/scripts/kindeditor-4.1.10/lang/zh_CN.js" />"></script>
    <title>编辑商品</title>
    <script type="text/javascript">
        var customerId = ${customerId};
        var ajaxUrl = "<c:url value="/weaponApi/saveWeapon" />";
        var uploadUrl = "<c:url value="/upload" />";
        var weaponId = ${weaponId};

        function fileChange() {
            $.jBox.tip("正在上传...", "loading");
            fileUpload(null, uploadUrl, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("上传成功", "success");
                    $("#weaponImg").val(json.file);
                    $("#previewImg").attr("src", json.fileUri);
                } else {
                    $.jBox.tip("上传失败", "error");
                }
            });
        }

        function checkImgCount() {
            $("#btnFile").click();
        }

        $(function () {
            <c:if test="${weaponId>0}">
            $("#previewImg").attr("src", "${uploadResourceServer.resourceUri(weaponBean.weaponImgs)}");
            </c:if>
            var editor;
            KindEditor.ready(function (K) {
                editor = K.create("#weaponContent", {
                    uploadJson: "<c:url value="/resources/scripts/kindeditor-4.1.10/jsp/upload_json.jsp?customerId=${customerId}" />"
                });
                if (weaponId > 0) {
                    editor.html($("#hdContent").html());
                }
            });


            $("#saveSubmit").click(function () {
                var weaponTitle = $("#weaponTitle").val();
                var weaponContent = editor.html();
                var weaponImgs = $("#weaponImg").val();
                if (weaponTitle.length == 0) {
                    $.jBox.tip("请输入标题");
                    return;
                }
                if (weaponContent.length == 0) {
                    $.jBox.tip("请输入软文内容");
                    return;
                }
                if (weaponImgs.length == 0) {
                    $.jBox.tip("请上传缩略图");
                    return;
                }
                $.jBox.tip("正在保存", "loading");
                var requestData = {
                    weaponTitle: weaponTitle,
                    weaponContent: weaponContent,
                    weaponImgs: weaponImgs,
                    weaponType: 1,
                    weaponId:${weaponId}
                }

                J.GetJsonRespons(ajaxUrl, requestData, function (json) {
                    if (json.result == 1) {
                        $.jBox.tip("保存成功", "success");
                        window.location.href = "<c:url value="/weapon/weaponList?weaponType=1" />"
                    } else {
                        $.jBox.tip("保存失败", "error");
                    }
                }, function () {
                }, J.PostMethod);
            });


        });
    </script>
    <style type="text/css">
        .waps-step-web li {
            margin: 5px 0;
        }

        #imgUl li {
            width: 100px;
            height: 100px;
            /*border: 1px solid #ccc;*/
            margin-left: 5px;
            float: left
        }

        .previewImg {
            width: 100px;
            height: 100px;
        }

        .delImg {
            width: 25px;
            height: 25px;
            margin-left: 75px;
            margin-top: -100px;
        }

        .emotion {
            width: 42px;
            height: 20px;
            background: url(<c:url value="/resources/images/face.gif" />) no-repeat 2px 2px;
            padding-left: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body style="background-color:#e4e7ea">
<div class="contentpanel">
    <div class="block">
        <div class="h">
            <p style="line-height:35px; padding-left:10px;"><i
                    class="fa  fa-file-o"></i>添加微武器（${weaponType==0?"图文":"软文"}）</p>

        </div>
        <div class="cnt-wp">
            <form>
                <div class="cnt form">
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <p class="title"><i class="red">*</i>标题：
                                    </p>

                                    <p style="height: 10px;"></p>
                                    <input id="weaponTitle" value="${weaponBean.weaponTitle}" name="weaponTitle"/>
                                </li>

                                <li>
                                    <p id="hdContent" style="display: none;">
                                        ${weaponBean.weaponContent}
                                    </p>

                                    <p class="title"><i class="red">*</i>内容：
                                    </p>

                                    <p style="height: 10px;"></p>

                                    <textarea id="weaponContent" name="content" style="width:700px;height:300px;"></textarea>
                                </li>
                                <li style="width: 500px;">
                                    <span class="title"><i class="red">*</i>图片（用于分享的缩略图）：</span>
                                    <input type="file" id="btnFile" name="btnFile" onchange="fileChange()"
                                           hidden="hidden"/>

                                    <p style="height: 10px;"></p>
                                    <ul style="width: 330px;" id="imgUl">
                                        <li>
                                            <img id="previewImg" src="" class="previewImg"/>
                                            <%--<img onclick='removeImg(${index.index})' class='delImg' src='<c:url value="/resources/images/delImg.png" />'/>--%>
                                            <input id="weaponImg" class='weaponImg' type='hidden'
                                                   value='${weaponBean.weaponImgs}'/>
                                        </li>
                                        <li style="width:100px;height: 100px;margin-left: 5px; float: left">
                                            <div class="fg-button clearfix" style="float:right;">
                                                <a href="javascript:checkImgCount()">上传图片</a>
                                            </div>
                                        </li>
                                    </ul>


                                </li>
                            </ul>
                        </div>
                        <p style="clear: both"></p>

                        <div style="padding:20px 5% 20px 5%">
                            <input type="button" id="saveSubmit" value="保存" class=" copybtn6">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
