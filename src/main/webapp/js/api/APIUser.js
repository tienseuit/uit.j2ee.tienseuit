var APIUser = {};
APIUser.BUSClassName = "UserBUS";
APIUser.UserEntity = function () {
    var t = this;

    t.id;
    t.firstName;
    t.lastName;
    t.dateOfBirth;
    t.email;
    t.phone;
    t.address;
    t.password;
};
APIUser.validateUser = function (data, edit) {
    if (edit) {
        data.firstName = data.firstName && data.firstName.trim();
        data.lastName = data.lastName && data.lastName.trim();
        data.email = data.email && data.email.toLowerCase().trim();
        data.phone = data.phone && data.phone.trim();
        data.address = data.address && data.address.trim();
    }

    var re = "";

    re += Util.ckStrNullMax("Tên", data.firstName);
    re += Util.ckStrMax("Họ và tên đệm", data.lastName, 100);
    re += Util.ckStrNullMax("Email", data.email, 100);
    re += Util.ckStrMax("Số điện thoại", data.phone);
    re += Util.ckStrMax("Địa chỉ", data.address, 500);
    re += Util.ckStrNullMax("Mật khẩu", data.password, 500);

    if (data.password && data.password.length < 6)
        re += "<br>Mật khẩu phải dài từ 6 ký tự trở lên";

    return re.length == 0 ? re : re.replace("<br>", "");
}

APIUser.Login = function (email, password, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIUser.BUSClassName,
            "Login",
            ["email", "password"],
            [email, password],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}

APIUser.InsertUser = function (jsonData, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIUser.BUSClassName,
            "InsertUser",
            ["jsonData"],
            [jsonData],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIUser.UpdateUser = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIUser.BUSClassName,
            "UpdateUser",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIUser.GetCurrentUserID = function (onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIUser.BUSClassName,
            "GetCurrentUserID",
            [],
            [],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}



 