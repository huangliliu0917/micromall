//相当于dialog
function Prompt(option) {
    this.options = option;

    this.init = function () {
        var index = 0;
        var content = "";

        content += "<div class='mod_dialog' style='display:none;' id='" + this.options.promptId + "_modDialog'>";
        content += "<div class='dialog_mask'></div>";
        content += "<div class='dialog_main qb_br qb_tac'>";
        content += "<div class='dialog_bd' id='" + this.options.promptId + "_content'></div>";

        var buttonHtml = "<div class='dialog_ft qb_flex'>";

        $.each(this.options.buttons, function (o, item) {
            var tempHtml = "<a class=flex_box id='button" + index + "'>" + o + "</a>";
            buttonHtml += tempHtml;

            index++;
        });

        buttonHtml += "</div>";

        content += buttonHtml;
        content += "</div></div></div>";

        $("body").append(content);

        this.bindClick();
    }

    this.showPrompt = function (content) {
        this.init();
        $("#" + this.options.promptId + "_content").html(content);
        $("#" + this.options.promptId + "_modDialog").show();
    }

    this.hidePrompt = function () {
        $("#" + this.options.promptId + "_modDialog").remove();
    }

    this.bindClick = function () {
        var index = 0;
        $.each(this.options.buttons, function (o, item) {
            $("#button" + index).bind("click", item);

            index++;
        });
    }
}

//相当于重写alert
var SimplePrompt = {
    showPrompt: function (content) {
        var tempHtml = "";

        tempHtml += "<div class='mod_dialog' id='simple_prompt'>";
        tempHtml += "<div class='dialog_mask'></div>";
        tempHtml += "<div class='dialog_main qb_br qb_tac'>";
        tempHtml += "<div class='dialog_bd' id='simple_prompt_content'>" + content + "</div>";
        tempHtml += "<div class='dialog_ft qb_flex'><a class=flex_box id='simple_button'>确定</a></div>";
        tempHtml += "</div></div></div>";

        $("body").append(tempHtml);

        $("#simple_button").bind("click", function () {
            $("#simple_prompt").remove();
        });
    },
    showPromptWithFunc: function (content, func) {
        var tempHtml = "";

        tempHtml += "<div class='mod_dialog' id='simple_prompt'>";
        tempHtml += "<div class='dialog_mask'></div>";
        tempHtml += "<div class='dialog_main qb_br qb_tac'>";
        tempHtml += "<div class='dialog_bd' id='simple_prompt_content'>" + content + "</div>";
        tempHtml += "<div class='dialog_ft qb_flex'><a class=flex_box id='simple_button'>确定</a></div>";
        tempHtml += "</div></div></div>";

        $("body").append(tempHtml);

        $("#simple_button").bind("click", func);
    },
    hide: function () {
        $("#simple_prompt").remove();
    }
}

///<summary>
///通知出现
///</summary>
///<param name="customerid" type="Number">通知内容</param>
///<param name="out_trade_no" type="String">类型：notification和loading</param>
function showNotificatioin(content, type) {
    $("#content").html(content);
    $("#content").fadeIn(100);

    if (type == "notification") {
        setTimeout(function () {
            $("#content").fadeOut(100);
        }, 2000);
    } else {
        $("#bg").show();
    }
}

function hideNotification(content) {
    $("#content").html(content);
    $("#bg").hide();
    setTimeout(function () {
        $("#content").fadeOut(100);
    }, 2000);
}

//新加代码
//function alert(msg) {
//    var prompt = new Prompt({
//        promptId: "content1",
//        buttons: {
//            "确定": function () {
//                prompt.hidePrompt();
//            }
//        }
//    });
//    prompt.showPrompt("<span style='line-height:24px;'>" + msg + "</span>");
//}