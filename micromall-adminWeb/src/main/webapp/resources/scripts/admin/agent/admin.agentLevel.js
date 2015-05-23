/**
 * Created by Administrator on 2015/5/18.
 */

function editLevel(levelId, levelName) {
    if (levelId > 0) {
        $("#levelName").val(levelName);
    }
    J.ShowDialog("edit_dialog", "编辑等级", function () {
        var self = this;
        var levelName = $.trim($("#levelName").val());
        if (levelName.length == 0) {
            $.jBox.tip("请输入等级名称");
            return;
        }
        J.GetJsonRespons(ajaxUrl + "saveLevel", {
            customerId: customerId,
            levelName: levelName,
            levelId: levelId
        }, function (json) {
            if (json.result == 1) {
                $.jBox.tip("保存成功", "success");
                $(self).dialog("close");
                window.location.reload();
            } else {
                $.jBox.tip("保存失败--" + json.msg);
            }
        }, function () {
        }, "post");

    }, function () {
        $(this).dialog("close");
    })
}