/**
 * Created by Administrator on 2015/5/22.
 */
var agentHandler = {
    ajaxUrl: "",
    init: function (ajaxUrl) {
        this.ajaxUrl = ajaxUrl;
    },
    saveAgent: function () {
        var requestData = checkForm();
        if (requestData != null) {
            loading.show("正在保存...");
            J.GetJsonRespons(agentHandler.ajaxUrl + "saveAgent", requestData, function (json) {
                loading.close();
                if (json.result == 1) {
                    //SimplePrompt.showPrompt("保存成功");
                    SimplePrompt.showPromptWithFunc("保存成功", function () {
                        SimplePrompt.hide();
                        window.location.href = returnUrl;
                    })
                } else if (json.result == 2) {
                    SimplePrompt.showPrompt("该手机号码已注册");
                } else {
                    SimplePrompt.showPrompt("保存失败");
                }
            }, function () {
            }, J.PostMethod);
        }
    }
}

function checkForm() {
    var agentAccount = $.trim($("#agentAccount").val());
    var name = $.trim($("#name").val());
    var agentPassword = $.trim($("#agentPassword").val());
    var confirmPass = $.trim($("#confirmPass").val());
    var agentLevel = $("#agentLevel").val();
    var agentArea = $.trim($("#agentArea").val());
    var agentChannel = $.trim($("#agentChannel").val());
    var agentCardId = $.trim($("#agentCardId").val());
    var agentQQ = $.trim($("#agentQQ").val());
    var agentWeixin = $.trim($("#agentWeixin").val());
    var agentAddr = $.trim($("#agentAddr").val());

    if (agentAccount.length == 0) {
        SimplePrompt.showPrompt("请输入代理人手机号");
        return null;
    }
    if (!J.IsMobile(agentAccount)) {
        SimplePrompt.showPrompt("手机号码格式不正确");
        return null;
    }
    if (name.length == 0) {
        SimplePrompt.showPrompt("请输入代理商姓名");
        return null
    }
    if (editAgentId == 0 || agentPassword.length > 0) {
        if (agentPassword.length == 0) {
            SimplePrompt.showPrompt("请输入登录密码");
            return null;
        }
        if (agentPassword != confirmPass) {
            SimplePrompt.showPrompt("两次密码输入不相同");
            return null;
        }
    }
    if (editAgentId == 0) {
        if (agentLevel == 0) {
            SimplePrompt.showPrompt("请选择代理商等级");
            return null;
        }
    }
    if (agentWeixin.length == 0) {
        SimplePrompt.showPrompt("请输入代理商微信号");
        return null;
    }
    if (agentAddr.length == 0) {
        SimplePrompt.showPrompt("请输入代理商收货地址");
        return null;
    }

    var requestData = {
        agentId: editAgentId,
        agentAccount: agentAccount,
        name: name,
        agentPassword: agentPassword.length == 0 ? "" : $.md5(agentPassword),
        agentArea: agentArea,
        agentChannel: agentChannel,
        agentCardId: agentCardId,
        agentQQ: agentQQ,
        agentWeixin: agentWeixin,
        agentAddr: agentAddr,
        agentCertificate: "",
        levelId: agentLevel,
        customerId: customerId
    }

    return requestData;
}