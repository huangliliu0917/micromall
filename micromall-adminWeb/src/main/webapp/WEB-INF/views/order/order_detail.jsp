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
    <style type="text/css">
        #snProList a {
            border: 1px solid #ccc;
            padding: 5px;
            margin-left: 10px;
            margin-top: 10px;
        }
    </style>
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
            <c:if test="${orderBean.orderStatus==0&&orderBean.realShipAgent==null}">
            setInterval("loadData()", 1000);
            </c:if>
            //loadData();
        });

        function addPro(id, snCode) {
            var proHtml = $("#pro_template").html();
            var length = $(".proCodes").length;
            proHtml = proHtml.replace(/{id}/g, id);
            proHtml = proHtml.replace("{snCode}", snCode);
            proHtml = proHtml.replace("{snBtn}", id);
            $("#proCodePanel").append(proHtml);
            $("#btn" + id).attr("disabled", "disabled");
            $("#btn" + id).val("已选择");
        }

        function removePro(id) {
            var snBtn = $("#snBtn" + id).val();
            $("#btn" + snBtn).removeAttr("disabled");
            $("#btn" + snBtn).val("选择");
            $("#code" + id).remove();
        }

        function confirmShip() {
            var index = true;
            var proCodes = "";
            var $proCodes = $(".proCodes");
            if ($proCodes.length == 0) {
                $.jBox.tip("请至少选择一个货品发货");
                return;
            }
            $proCodes.each(function (o, item) {
                var code = $.trim($(item).val());
                if (code.length == 0) {
                    index = false;
                    $.jBox.tip("请至少选择一个货品发货");
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
//            var shipInfo = $.trim($("#shipInfo").val());
//            if (shipInfo.length == 0) {
//                $.jBox.tip("请输入物流信息");
//                return;
//            }
            var logiName = $("#logiName").val();
            var logiNum = $.trim($("#logiNum").val());
            if (logiNum.length == 0) {
                $.jBox.tip("请输入物流单号");
                return;
            }
            $.jBox.tip("正在提交...", "loading");
            var requestData = {
                orderId: orderId,
                proCodes: proCodes,
                logiName: logiName,
                logiNum: logiNum
            };

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
            J.GetJsonRespons(ajaxUrl + "getShipProList", {
                agentMobile: "${orderBean.shipMobile}"
            }, function (json) {
                //$("#snProList").html("");
                if (json.snList.length > 0) {
                    $.each(json.snList, function (o, item) {
                        var $hdSnId = $(".hdSnId");
                        var index = true;
                        $hdSnId.each(function () {
                            if (item.id == $(this).val()) {
                                index = false;
                                return false;
                            }
                        });
                        if (index) {
                            $("#snProList").append("<li style='float:left;'>" + item.sn + "（" + (item.snType == 0 ? "主码" : "副码") + "）：" + item.goodBean.goodName +
                            "<input id='btn" + item.id + "' type='button'onclick='addPro(" + item.id + ",\"" + item.sn + "\")' value='选择' />" +
                            "<input class='hdSnId' type='hidden' value='" + item.id + "' /></li>");
                        }
                    });
                }
            }, function () {
            }, J.PostMethod);
        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="confirmShip_dialog" style="padding: 10px;display: none;">
    <div class="cnt form">
        <!---->
        <form>
            <div class="waps-cpanel-content">
                <div class="waps-step-web plb5" style="width: 782px;">
                    <div style="float: left;">
                        待选货品
                        <ul id="snProList" style="width: 364px;">
                        </ul>
                    </div>
                    <ul style="float:right;width: 343px;">
                        <li>
                            <span class="title">
                                请从左侧选择货品编码：
                                <%--<a href="javascript:addPro()" id="addProCode" style="float: right"><span>--%>
                                    <%--<img style="margin-top: -5px;float:right" src="<c:url value="/resources/images/jia.png"/>" width="27px"></span></a>--%>
                            </span>
                            <br>

                            <div id="proCodePanel">
                                <div>
                                    <%--<input type="text" class="text proCodes" value="" style="margin-top: 20px;width: 303px;"/>--%>
                                </div>
                            </div>
                        </li>
                        <li>
                            <span class="title">物流公司：</span><br>
                            <select style="margin-top: 10px;" id="logiName">
                                <option value="中国邮政">中国邮政</option>
                                <option value="申通快递">申通快递</option>
                                <option value="圆通速递">圆通速递</option>
                                <option value="顺丰速运">顺丰速运</option>
                                <option value="天天快递">天天快递</option>
                                <option value="韵达快递">韵达快递</option>
                                <option value="中通速递">中通速递</option>
                                <option value="龙邦物流">龙邦物流</option>
                                <option value="宅急送">宅急送</option>
                                <option value="全一快递">全一快递</option>
                                <option value="汇通速递">汇通速递</option>
                                <option value="民航快递">民航快递</option>
                                <option value="亚风速递">亚风速递</option>
                                <option value="快捷速递">快捷速递</option>
                                <option value="DDS快递">DDS快递</option>
                                <option value="华宇物流">华宇物流</option>
                                <option value="中铁快运">中铁快运</option>
                                <option value="FedEx">FedEx</option>
                                <option value="UPS">UPS</option>
                                <option value="DHL">DHL</option>
                            </select>
                        </li>
                        <li>
                            <span class="title">物流单号：</span><br>
                            <input type="text" id="logiNum"/>
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
                                        <span class="title">发货人：</span>
                                        <lable>${orderBean.realShipAgent==null?"商家发货":orderBean.realShipAgent.name}</lable>
                                    </li>
                                </c:if>
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
                                        <lable>物流公司：${orderBean.logiName}，物流单号：${orderBean.logiNum}</lable>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <c:if test="${orderBean.orderStatus==0&&orderBean.realShipAgent==null}">
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
        <input id="snBtn{id}" type="hidden" value="{snBtn}"/>
        <input type="text" readonly="readonly" class="text proCodes" value="{snCode}" style="margin-top: 20px;width: 303px;"/>
        <a href="#" onclick="removePro({id})" style="float: right"><span><img style="margin-top: 23px;float:right" src="<c:url value="/resources/images/jian.png"/>" width="27px"></span></a>
    </div>
</script>
