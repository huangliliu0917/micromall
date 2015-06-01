<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>编辑商品</title>
    <script type="text/javascript">
        var returnUrl = "<c:url value="/order/orderList" />"
    </script>
    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/orderApi/" />";
        var orderId = "${orderBean.orderId}";

        $(function () {
            $("#confirmBtn").click(function () {
                J.ShowDialog("confirmShip_dialog", "确认发货", function () {
                    var self = this;
                    confirmShip();
                }, function () {
                    $(this).dialog("close");
                });
            });
            //setInterval("loadData()", 2000);
            loadData();
        });

        function addPro() {
            var proHtml = $("#pro_template").html();
            var length = $(".proCode").length;
            proHtml = proHtml.replace(/{id}/g, length + 1);
            $("#proCodePanel").append(proHtml);
        }

        function removePro(id) {
            $("#code" + id).remove();
        }

        function confirmShip() {
            var index = true;
            var proCodes = "";
            var $proCodes = $(".proCodes");
            $proCodes.each(function (o, item) {
                var code = $.trim($(item).val());
                if (code.length == 0) {
                    index = false;
                    $.jBox.tip("还有货号没有填写");
                    return false;
                }
                if (o == $proCodes.length - 1) {
                    proCodes += $(item).val();
                } else {
                    proCodes += $(item).val() + ",";
                }
            });
            if (!index) {
                return;
            }
            var shipInfo = $.trim($("#shipInfo").val());
            if (shipInfo.length == 0) {
                $.jBox.tip("请输入物流信息");
                return;
            }
            $.jBox.tip("正在提交...", "loading");
            var requestData = {
                orderId: orderId,
                proCodes: proCodes,
                shipInfo: shipInfo
            }

            J.GetJsonRespons(ajaxUrl + "confirmShip", requestData, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("确认发货成功", "success");
                    window.location.reload();
                } else {
                    $.jBox.tip("确认发货失败", "error");
                }
            }, function () {
            }, J.PostMethod);
        }

        function loadData() {
            J.GetJsonRespons(ajaxUrl + "getShipProList", null, function (json) {
                $("#snProList").hide();
                $.each(json.snList, function (o, item) {
                    $("#snProList").append('<li>${item.sn}</li>');
                })
            }, function () {
            }, J.PostMethod);
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="confirmShip_dialog" style="padding: 20px;display: none;">
    <div class="cnt form">
        <!---->
        <form>
            <div class="waps-cpanel-content">
                <div class="waps-step-web plb5" style="width: 622px;">
                    <div style="float: left;">
                        待选货品
                        <ul id="snProList">
                        </ul>
                    </div>
                    <ul style="float:right;">
                        <li>
                            <span class="title">
                                请输入货号：
                                <a href="javascript:addPro()" id="addProCode" style="float: right"><span><img style="margin-top: -5px;float:right" src="<c:url value="/resources/images/jia.png"/>" width="27px"></span></a>
                            </span>
                            <br>

                            <div id="proCodePanel">
                                <div>
                                    <input type="text" class="text proCodes" value="" style="margin-top: 20px;width: 303px;"/>
                                </div>
                            </div>
                        </li>
                        <li>
                            <span class="title">请输入物流信息：</span><br>
                            <textarea id="shipInfo" style="width: 303px;height: 117px;margin-top: 20px;padding: 10px;" placeholder="物流名称：xxx；单号：xxxxxxxxx"></textarea>
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
            <p style="line-height:35px; padding-left:10px;"><i class="fa  fa-file-o"></i>订单详情</p>

        </div>
        <div class="cnt-wp">
            <div class="fg-button clearfix" style="float:right;display: block;" id="backBtn">
                <a href="javascript:window.location.href=returnUrl">返回订单列表</a>
            </div>
            <div class="cnt form">
                <!---->
                <form>
                    <div class="waps-cpanel-content">
                        <div class="waps-step-web plb5">
                            <ul>
                                <li>
                                    <span class="title">订单号：</span>
                                    <label>${orderBean.orderId}</label>
                                </li>
                                <li>
                                    <span class="title">名称：</span>
                                    <label>${orderBean.orderName}</label>
                                </li>
                                <li>
                                    <span class="title">收件人姓名：</span>
                                    <label>${orderBean.shipName}</label>
                                </li>
                                <li>
                                    <span class="title">收件人手机：</span>
                                    <label>${orderBean.shipMobile}</label>
                                </li>
                                <li>
                                    <span class="title">收货地址：</span>
                                    <label>${orderBean.shipAddr}</label>
                                </li>
                                <li>
                                    <span class="title">订单金额：</span>
                                    <lable>￥${orderBean.totalPrice}</lable>
                                </li>
                                <li>
                                    <span class="title">货品数量：</span>
                                    <lable>${orderBean.proNum}</lable>
                                </li>
                                <li>
                                    <span class="title">订单状态：</span>
                                    <label>${orderBean.orderStatus==0?"未发货":"已发货"}</label>
                                </li>
                                <c:if test="${orderBean.orderStatus==1}">
                                    <li>
                                        <span class="title">货号列表：</span>
                                        <ul>
                                            <c:forEach items="${orderBean.orderItems}" var="orderItem">
                                                <li>${orderItem.proCode}</li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                    <li>
                                        <span class="title">物流信息：</span>
                                        <lable>${orderBean.shipInfo}</lable>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <c:if test="${orderBean.orderStatus==0&&orderBean.realShipId==0}">
                            <div style="padding:20px 5% 20px 5%">
                                <input type="button" value="发货" id="confirmBtn" class=" copybtn6">
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/html" id="pro_template">
    <div id="code{id}">
        <input type="text" class="text proCodes" value="" style="margin-top: 20px;width: 303px;"/>
        <a href="#" onclick="removePro({id})" style="float: right"><span><img style="margin-top: 23px;float:right" src="<c:url value="/resources/images/jian.png"/>" width="27px"></span></a>
    </div>
</script>
