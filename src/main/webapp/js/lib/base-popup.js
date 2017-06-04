PopupType = {
    info: "popup-base-type-info",
    warning: "popup-base-type-warning",
    error: "popup-base-type-error",
};
PopupResult = PopupButton = {
    OK: 2,
    Retry: 1,
    Cancel: 0,
};
PopupButtons = {
    OK: [0, 0, 1],
    OKCancel: [1, 0, 1],
    OKRetryCancel: [1, 1, 1],
}

PopupLoginType = {
    login: "login-form",
    register: "register-form",
}

function PopupInfo(msg, func, buttons, type) {
    var t = this;

    type = type || PopupType.info;
    var title = func ? _L.popup_title_validate : _L.popup_title_notify;
    var buttonText = [_L.popup_button_cancel, _L.popup_button_retry, _L.popup_button_ok];
    var joContainer;
    var joTitle;
    var joContent;
    var listJOButton;
    var buttonFocus = PopupButton.OK;

    function hotKey(e) {
        switch (e.keyCode) {
            case 27:
                t.remove();
                break; //  ESC
        }
    }

    t.remove = function () {
        $(document).unbind("keydown", hotKey);
        joContainer.remove();
    }

    t.show = function (joTemplate) {
        joContainer = joTemplate.clone();
        joTitle = joContainer.find(".popup-base-title");
        joContent = joContainer.find(".popup-base-content");
        listJOButton = [];

        { // preparse data
            var btns = joContainer.find(".popup-base-button");
            var n = btns.length;
            for (var i = 0; i < n; i++)
                //listJOButton[i] = $(btns[n - 1 - i]);
                listJOButton[i] = $(btns[i]);

            buttons = buttons ? buttons : (func ? PopupButtons.OKCancel : PopupButtons.OK);
            func = func || function () { };
        }

        function initButton(button) {
            t.setButtonText(button, buttonText[button]);
            var jo = listJOButton[button];
            jo.click(function () {
                t.remove();
                func(button);
            });
            jo.show();
        }
        ;
        for (var i = 0; i < buttons.length; i++) {
            if (buttons[i] == false)
                continue;

            initButton(i);
        }

        t.setTitle(title);
        t.setType(type);
        t.setContent(msg);
        t.setButtonFocus(buttonFocus);
        $('body').append(joContainer);
        listJOButton[buttons.length - 1].focus();
        $(window).bind("keydown", hotKey);
        joContainer.click(function (e) {
            if (e.target == joContainer[0])
                t.remove();

        });

        return t;
    };
    t.setTitle = function (val) {
        title = val;
        joTitle && joTitle.text(val);
        return t;
    };
    t.setType = function (val) {
        joContainer && type && joContainer.removeClass(type);
        type = val;
        joContainer && joContainer.addClass(type);
        return t;
    };
    t.setContent = function (val) {
        msg = val;
        joContent && joContent.html(msg);
        return t;
    };
    /*
     popupButton:
     example:
     popupButton = PopupButton.OK
     text = 'Yes'
     */
    t.setButtonText = function (popupButton, text) {
        buttonText[popupButton] = text;
        listJOButton && listJOButton[popupButton].text(text);
        return t;
    };
    t.setButtonFocus = function (popupButton) {
        buttonFocus = popupButton;
        listJOButton && listJOButton[popupButton].focus();
        return t;
    };
}
;

function PopupLogin() {
    var t = this;

    var joContainer;
    function hotKey(e) {
        switch (e.keyCode) {
            case 27:
                t.remove();
                break; //  ESC
        }
    }

    t.remove = function () {
        $(document).unbind("keydown", hotKey);
        joContainer.remove();
    }
    t.show = function (joTemplate, type) {
        joContainer = joTemplate;
        
        if (joContainer.parent().length == 0) {
            $('body').append(joContainer);
            $(window).bind("keydown", hotKey);
            joContainer.click(function (e) {
                if (e.target == joContainer[0])
                    t.remove();
            });
        }
        
        $("#" + type).show();
        if (type == PopupLoginType.login) {
            $("#" + PopupLoginType.register).hide();
        } else {
            $("#" + PopupLoginType.login).hide();
        }
 
        return t;
    };


}
;

function BasePopup() {
    var t = this;

    var joInput;
    var joInfo;
    var joLogin;
    function showGetInput(lable, value, onOk, onCancel, onValidate) {
        var jo = joInput.clone();
        var joIP = jo.find("input");

        function cancel(e) {
            onCancel && onCancel(joIP.val());
            jo.remove();
        }

        function ok() {
            var re = joIP.val();
            if (onValidate && onValidate(re) == false)
                return;

            onOk(re);
            jo.remove();
        }

        joIP.val(value);
        jo.find("strong").text(lable);
        jo.find("#popup-input-ok").click(ok);
        jo.find("#popup-input-cancel").click(cancel);
        jo.click(cancel);
        jo.children().click(function (e) {
            e.stopPropagation();
        })
        $("body").append(jo);
    }
    function showInfo(msg, func, buttons) {
        var re = new PopupInfo(msg, func, buttons);
        re = new PopupInfo(msg, func, buttons);
    }
    ;
    function loadAndShowPopupInfo(popup) {
        if (joInfo == null) {
            var baseUrl = APP.contextPath + "component?name=base-popup";
            var temp = $("<div>").load(baseUrl, function () {
                joInfo = temp.children();
                popup.show(joInfo);
            });
        } else {
            popup.show(joInfo);
        }
        return popup;
    }
    ;
    function loadAndShowLoginForm(loginPopup, type) {
        if (joLogin == null) {
            var baseUrl = APP.contextPath + "component?name=login-register";
            var temp = $("<div>").load(baseUrl, function () {
                joLogin = temp.children();
                joLogin.remove();
                loginPopup.show(joLogin, type);
            });
        } else {
            loginPopup.show(joLogin, type);
        }
        return loginPopup;
    }
    ;

    t.info = function (msg, func, buttons) {
        return loadAndShowPopupInfo(new PopupInfo(msg, func, buttons, PopupType.info));
    };
    t.warning = function (msg, func, buttons) {
        return loadAndShowPopupInfo(new PopupInfo(msg, func, buttons, PopupType.warning));
    };
    t.error = function (msg, func, buttons) {
        return loadAndShowPopupInfo(new PopupInfo(msg, func, buttons, PopupType.error));
    };

    t.success = function () {
        t.info(_L.popup_content_business_complete);
    };
    t.failed = function () {
        t.error(_L.popup_content_business_error);
    };
    t.missingInfo = function () {
        t.error("Vui lòng điền đầy đủ các trường có dấu sao (*)");
    }

    t.showLoginForm = function () {
        var re = new PopupLogin();
        loadAndShowLoginForm(re, PopupLoginType.login);
        return re;
    }
    t.showRegisterForm = function () {
        var re = new PopupLogin();
        loadAndShowLoginForm(re, PopupLoginType.register);
        return re;
    }






//    t.cleanPopupNode = function () {
//        ko.cleanNode($('#mainPopup')[0]);
//        ko.applyBindings(POPUP, $('#mainPopup')[0]);
//    };
//
//    t.viewConfirm = function (targetAction, content, isPrevented) {
//        PopupBusiness.targetAction = targetAction;
//
//        var mainPopup = $('#confirm-popup-container');
//        if (mainPopup.attr('id') === undefined) {
//            mainPopup = $('<div id="confirm-popup-container" class="modal fade in"></div>');
//            $('body').append('<div id="backdrop" class="modal-backdrop fade in"></div>');
//            $('body').append(mainPopup);
//
//            if (isPrevented) {
//                CommonModelFunctions.loadPage(mainPopup, 'components/confirm.html', loadImageCallack);
//            } else {
//                CommonModelFunctions.loadPage(mainPopup, 'components/confirm.html', loadImageCallack);
//            }
//        } else {
//
//            loadConfirmContent(targetAction);
//        }
//
//        function loadConfirmContent(targetAction) {
//            if (isPrevented) {
//                //$('#asset-prevented-sign-out').html();
//                $('#confirm-reject').hide();
//            } else {
//                $('#confirm-reject').show();
//            }
//            if (Common.isNull(content)) {
//                content = "Thao tác này có thể sẽ làm mất dữ liệu và không phục hồi được! <br><b>Bạn chắc chắn muốn thực hiện?</b></br>";
//            }
//            $('#confirmContent').html(content);
//            if (Common.isNull(targetAction)) {
//                $('#confirmTitle').html('Thông báo');
//            }
//            else {
//                $('#confirmTitle').html('Cảnh báo');
//            }
//
//            if (Common.isNull(targetAction)) {
//                $('#confirm-ok').hide();
//            } else {
//                $('#confirm-ok').show();
//            }
//            $('#confirm-popup-container').fadeIn('fast');
//            $('#confirm-popup-container').show();
//            $('#backdrop').fadeIn('fast');
//            $('#backdrop').show();
//
//
//            //debugger;
//            $('#confirm-ok')[0].onclick = function () {
//                CommonModelFunctions.callBack(PopupBusiness.targetAction);
//                CommonModelFunctions.closeConfirm();
//                $('#backdrop').hide();
//            };
//
//            // hot key
//            //$(document)[0].popupHotkey.on();
//        }
//
//        function loadImageCallack() {
//
//            // hotkey
//            var joDoc = $(document);
//
//            function PopupHotkey() {
//                me = this;
//                me.btnOK = $('#confirm-ok');
//                me.btnReject = $('#confirm-reject');
//
//                me.handler = function (e) {
//                    e.preventDefault();
//
//                    switch (e.which) {
//                        case 9: //tab
//                            if (me.btnOK.is(":focus"))
//                                me.btnReject.focus();
//                            else
//                                me.btnOK.focus();
//                            break;
//
//                        case 13: //enter
//                            if (me.btnOK.is(":focus"))
//                                me.btnOK.click();
//                            else
//                                me.btnOK.click();
//
//                        case 27: //esc
//                            me.btnReject.click();
//                            break;
//                    }
//                };
//
//                me.on = function () {
//                    me.btnOK.focus();
//                    joDoc.bind("keydown", me.handler);
//                };
//
//                me.off = function () {
//                    joDoc.unbind("keydown", me.handler);
//                };
//
//                me.btnOK.click(me.off);
//                me.btnReject.click(me.off);
//            };
//
//            joDoc[0].popupHotkey = new PopupHotkey();
//
//            loadConfirmContent(targetAction);
//
//        }
//    };
//
//    t.getInput = function (lable, value, onOk, onCancel, onValidate) {
//        if (joInput == null) {
//            joInput = $("<div>").load("components/input.html", function () {
//                joInput = $(joInput.find("div:first-child")[0]);
//                showGetInput(lable, value, onOk, onCancel, onValidate);
//            });
//        } else {
//            showGetInput(lable, value, onOk, onCancel, onValidate);
//        }
//    }
//
//    t.byRequired = function (targetAction) {
//        PopupBusiness.viewConfirm(targetAction, 'Bạn chưa điền đủ thông tin');
//    };
//
//    t.bySuccess = function (targetAction) {
//        PopupBusiness.viewConfirm(targetAction, 'Thành công');
//    };
//
//    t.byFailed = function (targetAction) {
//        PopupBusiness.viewConfirm(targetAction, 'THẤT BẠI\nXin vui lòng thử lại');
//    };

    /*
     input
     info
     confirm
     */
}

var Popup = new BasePopup();





