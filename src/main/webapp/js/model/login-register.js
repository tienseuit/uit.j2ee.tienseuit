
function MODEL_LOGIN() {
    var t = this;
    var action = Util.getURLValue("action");

    t.user = ko.observable(new APIUser.UserEntity());
    t.loginError = ko.observable();
    t.registerError = ko.observable();

    t.user().confirmPassword = null;
    
    
//    t.user().email = 'tienseuit@gmail.com'; // dev_test
//    t.user().password = '123123';// dev_test




    // click to show login form
    t.showLogin = function () {
        action = "login";
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#btn-show-register').removeClass('active');
        $("#btn-show-login").addClass('active');

        Util.setURLVales("action", "login");
    }

    // click to show register form
    t.showRegister = function () {
        action = "register";
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#btn-show-login').removeClass('active');
        $("#btn-show-register").addClass('active');

        Util.setURLVales("action", "register");
    }

    // input change to remove error message
    t.onInfoChange = function (e) {
        t.loginError(null);
        t.registerError(null);
        return true;
    }

    // do login
    t.login = function () {
        var user = t.user();
        if (user.email == false || user.password == false) {
            t.loginError("Bạn vui lòng điền email và password");
            return;
        }

        APIUser.Login(user.email, hex_md5(user.password), function (data) {
            if (data.statusCode == BUSResponeCode.ok) {
                window.location.href = APP.contextPath;
            } else if (data.statusCode == BUSResponeCode.busFailed) {
                t.loginError("Sai email hoặc password");
            } else {
                console.warn(data);
                t.loginError("Đăng nhập chưa thành công");
                Popup.failed();
            }
        });
    }

    // do register
    t.register = function () {

        var user = t.user();
        var validate = APIUser.validateUser(user, 1);
        
        if(user.password != user.confirmPassword){
            validate = validate.length > 0 ?
                validate + "<br>Nhập lại mật khẩu không đúng"
                : validate + "Nhập lại mật khẩu không đúng";
        }
        
        if (validate.length > 0) {
            Popup.error(validate);
            return;
        }

        var pass = user.password;
        user.password = hex_md5(pass);
        APIUser.InsertUser(JSON.stringify(user), function (data) {
            if (data.statusCode == BUSResponeCode.ok) {
                
                var personalLink = APP.contextPath + "my-account";
                var mes = "Đăng ký tài khoản thành công <br>Bạn vui lòng cập nhật đầy đủ thông tin cho hồ sơ"
                        + "<br><br><a href='" + personalLink + "'>Hồ sơ của tôi</a> ";
                var count = 15;
                var p = Popup.info(mes);
                p.setTitle("Chúc mừng");
                var handle = setInterval(function () {
                    if ((count--) < 1) {
                        location.href = personalLink;
                        clearInterval(handle);
                        return;
                    }

                    p.setContent(mes + "(" + count + ")");
                }, 1000);
                location.href = APP.contextPath;
            } else {
                console.warn(data);
                Popup.failed();
            }
        });
        user.password = pass;
    }


    // check action
    if (action == "register")
        t.showRegister();
    else
        t.showLogin();

    // hot key
    $(window).keydown(function (e) {

        // press enter to login or register
        if (e.keyCode == 13) {
            e.target.blur();
            if (action == "register")
                t.register();
            else
                t.login();
        }
    });
}

var MODEL = new MODEL_LOGIN();
ko.applyBindings(MODEL);





