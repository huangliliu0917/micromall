<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>订单详情</title>
    <script type="text/javascript">
        var ajaxUrl = "<c:url value="/orderApi/" />";
        var orderId = "${orderBean.orderId}";

        $(function () {
            <c:if test="${itemBean!=null}">
            $("#logiName").val("${itemBean.logiName}");
            </c:if>
            $("#confirmBtn").click(function () {
                J.ShowDialogButton("confirmShip_dialog", "确认发货", {
                    "确认发货": function () {
                        confirmShip();
                    },
                    "重新出货": function () {
                        reDeliver();
                    },
                    "取消": function () {
                        $(this).dialog("close");
                    }
                });
            });
        });

        function confirmShip() {
            var logiName = $("#logiName").val();
            var logiNum = $.trim($("#logiNum").val());
            if (logiNum.length == 0) {
                $.jBox.tip("请输入物流单号");
                return;
            }
            $.jBox.tip("正在提交...", "loading");
            var requestData = {
                orderId: orderId,
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

        function reDeliver() {
            jBox.confirm('确定要设置重新出货？请联系出库管理员重新操作出货', '提示', function (v, h, f) {
                if (v == "ok") {
                    $.jBox.tip("正在设置重新出货...", "loading");
                    var requestData = {
                        orderId: orderId,
                        itemId:${itemBean.id}
                    }
                    J.GetJsonRespons(ajaxUrl + "reDeliver", requestData, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("重新出货成功，请联系相应出库管理员重新操作出货", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("操作失败", "error");
                        }
                    }, function () {
                    }, J.PostMethod);
                }
                return true;
            });

        }
    </script>
</head>
<body style="background-color:#e4e7ea">
<div id="confirmShip_dialog" style="padding: 10px;display: none;">
    <div class="cnt form">

        <form>
            <div class="waps-cpanel-content">
                <div class="waps-step-web plb5" style="width: 782px;">
                    <ul>
                        <li>
                            <span class="title">
                                货品编号列表：
                                <%--<a href="javascript:addPro()" id="addProCode" style="float: right"><span>--%>
                                    <%--<img style="margin-top: -5px;float:right" src="<c:url value="/resources/images/jia.png"/>" width="27px"></span></a>--%>
                            </span>
                            <br>

                            <div id="proCodePanel">
                                <c:if test="${itemBean!=null}">
                                    <c:forEach items="${itemBean.proList}" var="proCode">
                                        <div>
                                            <input readonly="readonly" type="text" class="text proCodes" value="${proCode}（${fn:length(proCode)==15?"主码":"副码"}）" style="margin-top: 20px;width: 303px;"/>
                                        </div>
                                    </c:forEach>
                                </c:if>

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
                            <input type="text" id="logiNum" value="${itemBean.logiNum}"/>
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
                                <c:if test="${orderBean.realShipAgent==null}">
                                    <li>
                                        <span class="title">出货状态：</span>
                                        <label>${orderBean.deliverStatus==0?"未出货":"已出货"}</label>
                                    </li>
                                </c:if>
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
                        <c:if test="${orderBean.orderStatus==0&&orderBean.realShipAgent==null&&orderBean.deliverStatus==1}">
                            <div style="padding:20px 5% 20px 5%">
                                <input type="button" value="确认发货" id="confirmBtn" class=" copybtn6">
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
