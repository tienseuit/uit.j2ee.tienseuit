<%@page import="java.io.PrintWriter"%>
<%@page import="uit.j2ee.been.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}">JPayment</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav"> 
                <li><a href="#">Giới thiệu</a></li>
                <li><a href="#">Trợ giúp</a></li>
<!--                <li  class="dropdown">
                    <a href="#">Dev login</a>
                    
                     <ul class="dropdown-menu">
                        <li><a onclick="DevLogin(1);">1</a></li>
                        <li><a onclick="DevLogin(2);">2</a></li>
                        <li><a onclick="DevLogin(3);">3</a></li>
                        <li><a onclick="DevLogin(4);">4</a></li>
                        <li><a onclick="DevLogin(5);">5</a></li>
                        <script> 
                            function DevLogin(userID) {
                                Ajax.J2EE(
                                       "UserBUS",
                                        "DevLogin",
                                        ["userID"],
                                        [userID],
                                        Ajax.callback.object,
                                        function(){location.reload(true);},
                                        function(re){console.log(re)}
                                        );
                            }
                        </script>
                    </ul>
                </li>-->
               
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    User user = null;
                    Object temp = request.getSession().getAttribute("user");
                    if (temp == null) {
                %>
                <li><a href="${pageContext.request.contextPath}/user?action=register"><span class="glyphicon glyphicon-user"></span>Đăng ký</a></li>
                <li><a href="${pageContext.request.contextPath}/user?action=login"><span class="glyphicon glyphicon-log-in"></span>Đăng nhập</a></li>

                <%
                } else {
                    user = (User) temp;
                %>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span>${user.getFirstName()}</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}personal">Tài khoản của tôi</a></li>
                        <li><a href="${contextPath}business">Cửa hàng của tôi</a></li>
                        <li><a href="${contextPath}business/pos">Bán hàng</a></li>
                        <li><a href="${contextPath}business/pos-setting">Cài đặt bán hàng</a></li>
                        <li><a href="${contextPath}logout">Đăng xuất</a></li>
                    </ul>
                </li>
                <%
                    }
                %> 
            </ul>
        </div><!--/.nav-collapse -->
    </div><!--/.container-fluid -->
</nav>

<style>
    .path{
        margin-top: 16px;
    }
    .path a{
        cursor: pointer;
        color: black;
    }
    .path>ul{
        list-style-type: none;
        padding-left: 50px;
        background: linear-gradient(to right ,white, #f8f8f8 5%, #f8f8f8 95%,white);
    }
    .path>ul>li{
        display: inline-block; 
        padding: 14px 3px;
    }

    .path .dropdown > .dropdown-menu { 
        margin-top: 0;
        border-top-left-radius: 0;
        border-top-right-radius: 0; 
    }
    .path .dropdown:hover > .dropdown-menu { 
        display: block;
    } 
</style>

