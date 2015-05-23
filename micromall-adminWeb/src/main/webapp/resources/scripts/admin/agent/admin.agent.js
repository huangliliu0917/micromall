/**
 * Created by Administrator on 2015/5/18.
 */

var index = true;
var superAgentCount = 0;
var agentHandler = {
    ajaxUrl: "",
    init: function (ajaxUrl) {
        this.ajaxUrl = ajaxUrl;
    },
    saveAgent: function () {
        var requestData = checkForm();
        if (requestData != null && index) {
            $.jBox.tip("正在保存", "loading");
            J.GetJsonRespons(agentHandler.ajaxUrl + "saveAgent", requestData, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("保存成功", "success");
                    //if (agentId == 0) {
                    //    window.location.href = returnUrl;
                    //}
                } else if (json.result == 2) {
                    $.jBox.tip("该手机号码已注册");
                } else {
                    $.jBox.tip("保存失败");
                }
            }, function () {

            }, J.PostMethod);
        }
    },
    setDelete: function (agentId, currentStatus) {
        var isDelete = 0;
        var type = "恢复";
        if (currentStatus == 0) {
            isDelete = 1;
            type = "冻结"
        }
        jBox.confirm('确定要' + type + '该代理商？', '提示', function (v, h, f) {
            if (v == "ok") {
                $.jBox.tip("正在设置，请稍后...", 'loading');
                setTimeout(function () {
                    J.GetJsonRespons(agentHandler.ajaxUrl + "setDelete", {
                        agentId: agentId,
                        isDelete: currentStatus == 0 ? 1 : 0
                    }, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("操作成功", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("操作失败", "error");
                        }
                    }, function () {

                    }, J.PostMethod);
                }, 500);
            }
            return true;
        });
    },
    setAgentStatus: function (agentId, currentStatus) {
        $("#setAgentStatus").val(currentStatus);
        J.ShowDialog("setstatus_dialog", "审核", function () {
            var self = this;

            $.jBox.tip("正在提交...", "loading");
            J.GetJsonRespons(agentHandler.ajaxUrl + "setAgentStatus", {
                agentId: agentId,
                status: $("#setAgentStatus").val()
            }, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("操作成功", "success");
                    window.location.reload();
                } else {
                    $.jBox.tip("操作失败", "error");
                }
            }, function () {
            }, J.PostMethod);
        }, function () {
            $(this).dialog("close");
        });
    },
    setSuperAgent: function (agentLevel, superAgentId) {
        J.GetJsonRespons(agentHandler.ajaxUrl + "getSuperAgent", {
            levelId: agentLevel
        }, function (json) {
            var $dom = $("#superAgent");
            $dom.empty();
            $dom.append('<option value="0">请选择</option>');
            superAgentCount = json.list.length;
            $.each(json.list, function (o, item) {
                $dom.append('<option value="' + item.agentId + '">' + item.name + '</option>');
            })
            if (agentId > 0) {
                $("#superAgent").val(superAgentId);
            }
        }, function () {
        }, J.PostMethod);
    }
}

function checkForm() {
    var agentMobile = $.trim($("#agentMobile").val());
    var agentName = $.trim($("#agentName").val());
    var agentPassword = $.trim($("#agentPassword").val());
    var confirmPass = $.trim($("#confirmPass").val());
    var agentLevel = $("#agentLevel").val();
    var superAgent = $("#superAgent").val();
    var agentArea = $.trim($("#agentArea").val());
    var agentChannel = $.trim($("#agentChannel").val());
    var agentCardId = $.trim($("#agentCardId").val());
    var agentQQ = $.trim($("#agentQQ").val());
    var agentWeixin = $.trim($("#agentWeixin").val());
    var agentAddr = $.trim($("#agentAddr").val());

    if (agentMobile.length == 0) {
        $.jBox.tip("请输入代理人手机号");
        return null;
    }
    if (!J.IsMobile(agentMobile)) {
        $.jBox.tip("手机号码格式不正确");
        return null;
    }
    if (agentName.length == 0) {
        $.jBox.tip("请输入代理商姓名");
        return null
    }
    if (agentId == 0 || agentPassword.length > 0) {
        if (agentPassword.length == 0) {
            $.jBox.tip("请输入登录密码");
            return null;
        }
        if (agentPassword != confirmPass) {
            $.jBox.tip("两次密码输入不相同");
            return null;
        }
    }
    if (agentLevel == 0) {
        $.jBox.tip("请选择代理商等级");
        return null;
    }
    if (superAgentCount > 0 && superAgent == 0) {
        $.jBox.tip("请选择上级代理");
        return null;
    }
    if (agentWeixin.length == 0) {
        $.jBox.tip("请输入代理商微信号");
        return null;
    }

    var requestData = {
        agentId: agentId,
        agentAccount: agentMobile,
        name: agentName,
        agentPassword: agentPassword.length == 0 ? "" : $.md5(agentPassword),
        superAgentId: superAgent,
        agentArea: agentArea,
        agentChannel: agentChannel,
        agentCardId: agentCardId,
        agentQQ: agentQQ,
        agentWeixin: agentWeixin,
        agentAddr: agentAddr,
        agentCertificate: "",
        levelId: agentLevel
    }

    return requestData;
}

$(function () {
    $("#agentMobile").blur(function () {
        if (agentId == 0) {
            var account = $.trim($("#agentMobile").val());
            if (account.length > 0) {
                J.GetJsonRespons(agentHandler.ajaxUrl + "accountExists", {
                    account: account
                }, function (json) {
                    if (json == 1) {
                        index = false;
                        $.jBox.tip("该手机号码已注册");
                    } else {
                        index = true;
                    }
                }, function () {
                }, J.PostMethod);
            }
        }
    });

    $("#agentLevel").change(function () {
        agentHandler.setSuperAgent($(this).val(), 0);
    })
})