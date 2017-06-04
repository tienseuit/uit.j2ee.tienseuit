<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>

<head>
    <jsp:include page="../component/include.jsp"></jsp:include>
    <ct:inclue src="css/login-register.css" />
    <title></title>
</head>
<body> 
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-login">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <a data-bind=" click:showLogin" id="btn-show-login">Đăng nhập</a>
                            </div>
                            <div class="col-xs-6">
                                <a data-bind="click:showRegister" id="btn-show-register">Đăng ký</a>
                            </div>
                        </div>
                        <hr>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form id="login-form" method="post" role="form" style="display: none;">
                                    <div class="form-group">
                                        <input placeholder="Nhập email của bạn" data-bind="value: user().email, event: { click: onInfoChange, keydown: onInfoChange}" class="form-control" tabindex="1" type="email">
                                    </div>
                                    <div class="form-group">
                                        <input placeholder="Nhập mật khẩu của bạn" data-bind="value: user().password, event: { click: onInfoChange, keydown: onInfoChange}" tabindex="2" class="form-control" type="password">
                                    </div>
                                    <div  data-bind="if: loginError()" class="form-group">
                                        <div class="alert alert-danger">
                                            <strong>Thất bại!</strong> <span data-bind="text: loginError()"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input  data-bind="click: login" value="Đăng nhập" type="button" tabindex="4" class="form-control btn btn-login">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="text-center">
                                                    <a tabindex="5" class="forgot-password">Bạn quên mật khẩu?</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form id="register-form" method="post" role="form" style="display: none;">
                                    
                                    <div class="form-group">
                                        <input placeholder="Tên" data-bind="value: user().firstName, event: { click: onInfoChange, keydown: onInfoChange}" class="form-control" tabindex="1" type="email">
                                    </div>
                                    <div class="form-group">
                                        <input placeholder="Họ và tên đệm" data-bind="value: user().lastName, event: { click: onInfoChange, keydown: onInfoChange}" class="form-control" tabindex="1" type="email">
                                    </div>
                                    <div class="form-group">
                                        <input placeholder="Email" data-bind="value: user().email, event: { click: onInfoChange, keydown: onInfoChange}" class="form-control" tabindex="1" type="email">
                                    </div>
                                    <div class="form-group">
                                        <input placeholder="Mật khẩu" data-bind="value: user().password, event: { click: onInfoChange, keydown: onInfoChange}" tabindex="2" class="form-control" type="password">
                                    </div>
                                    <div class="form-group">
                                        <input placeholder="Nhập lại mật khẩu" data-bind="value: user().confirmPassword, event: { click: onInfoChange, keydown: onInfoChange}" tabindex="2" class="form-control" type="password">
                                    </div>
                                    <div  data-bind="if: loginError()" class="form-group">
                                        <div class="alert alert-danger">
                                            <strong>Thất bại!</strong> <span data-bind="text: loginError()"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                 <input  data-bind="click: register" value="Đăng ký" type="button" tabindex="4" class="form-control btn btn-login">
                                               </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    </div>

    <ct:inclue src="js/lib/md5.js" />
    <ct:inclue src="js/api/APIUser.js" />
    <ct:inclue src="js/model/login-register.js" />
</body> 
