/**
 * Created by Administrator on 2015/5/23.
 */
var userHandler = {
    saveUser: function () {
        var requestData = checkForm();
        if (requestData != null) {
            loading.show("正在保存");
            J.GetJsonRespons(ajaxUrl + "saveUser", requestData, function (json) {
                loading.close();
                if (json.result == 1) {
                    SimplePrompt.showPromptWithFunc("保存成功", function () {
                        SimplePrompt.hide();
                        window.location.href = returnUrl;
                    });
                } else if (json.result == 2) {
                    SimplePrompt.showPrompt("该用户手机已存在在您的通讯录中，无需添加");
                } else {
                    SimplePrompt.showPrompt("保存失败");
                }
            }, function () {
            }, J.PostMethod);
        }
    },
    deleteUser: function (userId) {
        var prompt = new Prompt({
            promptId: "content1",
            buttons: {
                "确定": function () {
                    prompt.hidePrompt();
                    loading.show("正在删除");
                    J.GetJsonRespons(ajaxUrl + "deleteUser", {
                        userId: userId,
                        customerId: customerId
                    }, function (json) {
                        loading.close();
                        if (json.result == 1) {
                            SimplePrompt.showPromptWithFunc("删除成功", function () {
                                SimplePrompt.hide();
                                window.location.reload();
                            });
                        } else {
                            SimplePrompt.showPrompt("删除失败");
                        }
                    }, function () {

                    }, J.PostMethod);
                },
                "取消": function () {
                    prompt.hidePrompt();
                }
            }
        });
        prompt.showPrompt("确定要删除该代理商？");
    }
}

function checkForm() {
    var userMobile = $.trim($("#userMobile").val());
    var userName = $.trim($("#userName").val());
    var userQQ = $.trim($("#userQQ").val());
    var userWeixin = $.trim($("#userWeixin").val());
    var userAddr = $.trim($("#userAddr").val());

    if (userMobile.length == 0) {
        SimplePrompt.showPrompt("请输入手机号码");
        return null;
    }
    if (!J.IsMobile(userMobile)) {
        SimplePrompt.showPrompt("您输入的手机号码格式不正确");
        return null;
    }
    if (userName.length == 0) {
        SimplePrompt.showPrompt("请输入用户姓名");
        return null;
    }
    if (userWeixin.length == 0) {
        SimplePrompt.showPrompt("请输入用户微信号码");
        return null;
    }
    if (userAddr.length == 0) {
        SimplePrompt.showPrompt("请输入用户收获地址");
        return null;
    }

    var requestData = {
        userId: userId,
        customerId: customerId,
        userMobile: userMobile,
        userName: userName,
        userQQ: userQQ,
        userWeixin: userWeixin,
        userAddr: userAddr
    }

    return requestData;
}