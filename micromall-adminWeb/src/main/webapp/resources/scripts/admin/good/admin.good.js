/**
 * Created by Administrator on 2015/5/19.
 */
var goodHandler = {
    saveGood: function () {
        var requestData = checkForm();
        if (requestData != null) {
            $.jBox.tip("正在保存...", "loading");
            J.GetJsonRespons(ajaxUrl + "saveGood", requestData, function (json) {
                if (json.result == 1) {
                    $.jBox.tip("保存成功", "loading");
                    window.location.href = returnUrl;
                } else if (json.result == 2) {
                    $.jBox.tip("该商品编号已存在");
                } else {
                    $.jBox.tip("保存失败", "error");
                }
            }, function () {
            }, J.PostMethod);
        }
    },
    uploadImg: function () {
        $("#goodImg").val("");
        $.jBox.tip("正在上传...", "loading");
        fileUpload(null, uploadUrl, function (json) {
            if (json.result == 1) {
                $.jBox.tip("上传成功", "success");
                $("#goodImg").val(json.file);
                $("#previewImg").attr("src", json.fileUri);
                $("#previewImg").show();
            } else {
                $.jBox.tip("上传失败", "error");
            }
        });
    },
    setDelete: function (goodId) {
        jBox.confirm('确定要删除该商品？', '提示', function (v, h, f) {
            if (v == "ok") {
                $.jBox.tip("正在删除，请稍后...", 'loading');
                setTimeout(function () {
                    J.GetJsonRespons(ajaxUrl + "setDelete", {
                        goodId: goodId
                    }, function (json) {
                        if (json.result == 1) {
                            $.jBox.tip("删除成功", "success");
                            window.location.reload();
                        } else {
                            $.jBox.tip("删除失败", "error");
                        }
                    }, function () {

                    }, J.PostMethod);
                }, 500);
            }
            return true;
        });
    }
};

function checkForm() {
    var goodName = $.trim($("#goodName").val());
    var goodCode = $.trim($("#goodCode").val());
    var goodImg = $.trim($("#goodImg").val());
    var price = $.trim($("#price").val());
    var levelPriceInfo = "";
    var groups = "";
    if ($("#checkAll").attr("checked")) {
        groups = "all";
    } else {
        var $chkDom = $("input[name='chkGroup']:checked");
        $chkDom.each(function () {
            groups += $(this).val() + "|";
        })
        groups = "|" + groups;
    }
    if (goodName.length == 0) {
        $.jBox.tip("请输入商品名称");
        return;
    }
    if (goodCode.length == 0) {
        $.jBox.tip("请输入商品编号");
        return;
    }
    if (goodImg.length == 0) {
        $.jBox.tip("请上传商品图片");
        return;
    }
    if (price.length == 0) {
        $.jBox.tip("请输入商品销售价");
        return;
    }
    levelPriceInfo = getLevelPriceInfo();
    if (levelPriceInfo.length == 0) {
        $.jBox.tip("还有价格未设置");
        return null;
    }
    if (groups.length == 0) {
        $.jBox.tip("请选择分组");
        return null;
    }
    var goodDesc = $.trim($("#goodDesc").val()).replace(/\r/g, "").replace(/\n/g, "");
    if (goodDesc.length == 0) {
        $.jBox.tip("请输入商品描述");
        return null;
    }

    var requestData = {
        goodId: goodId,
        goodName: goodName,
        goodCode: goodCode,
        priceInfo: levelPriceInfo,
        goodDesc: goodDesc,
        goodImg: goodImg,
        price: price,
        groups: groups
    }

    return requestData;
}

function getLevelPriceInfo() {
    var levelPriceInfo = "";
    var priceClass = $(".priceClass");
    var levelIdList = $(".levelClass");
    var levelNameList = $(".levelNameClass");

    priceClass.each(function (o, item) {
        var price = $(this).val();
        if (price.length == 0) {
            levelPriceInfo = "";
            return false;
        }
        var tempInfo = "";
        if (o == priceClass.length - 1) {
            tempInfo = levelIdList[o].value + ":" + levelNameList[o].value + ":" + price;
        } else {
            tempInfo = levelIdList[o].value + ":" + levelNameList[o].value + ":" + price + ",";
        }
        levelPriceInfo += tempInfo;
    });
    return levelPriceInfo;
}