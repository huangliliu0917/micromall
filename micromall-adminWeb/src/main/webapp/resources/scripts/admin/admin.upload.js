/**
 * Created by Administrator on 2015/5/19.
 */

var fileUpload = function (data, url, callBack) {
    $.ajaxFileUpload({
        url: url,
        secureuri: false,//安全协议
        fileElementId: 'btnFile',//id
        dataType: 'json',
        type: "post",
        data: data,
        error: function (data, status, e) {

        },
        success: function (json) {
            callBack(json);
        }
    });
}