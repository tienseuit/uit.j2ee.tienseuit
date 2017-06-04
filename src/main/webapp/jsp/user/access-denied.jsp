<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>

<head>
    <jsp:include page="../component/include.jsp"></jsp:include>
    <title></title>
</head>

<body style="background: url(/image/access-denied.jpg) no-repeat; background-size: 100% 100%;"> 
    
<jsp:include page="../component/page-head.jsp"></jsp:include> 
<!--    <div style="height: 100%; text-align: center; position: absolute; top: 0; left: 0; right: 0; bottom: 0; background: rgba(255, 255, 255, 0.6);">
        <b style=" top: 40%; position: absolute; transform: translate(-50%, -40%);">
            <h1>Chức năng yêu cầu đăng nhập</h1>
            <h2><a>Đăng nhập với tài khoản của tôi</a><a>Tạo tài khoản mới</a></h2>
        </b>
    </div>-->
<style>
    .panel-body{
        text-align: center;
    } 
    .panel-body a:hover{
        text-decoration: none;
        cursor: pointer;
    }
    
    .popup-base .popup-base-button{
        width: 131px;
    }
    
</style>
    <script>
        var p = Popup.error(
                "<h1>Chức năng yêu cầu đăng nhập</h1>",
                function(re){
                    if(re == PopupResult.OK)
                        location.href = "/user?action=login";
                    else
                        location.href = "/user?action=register";
                },
                PopupButtons.OKCancel);
        p.setTitle("Truy cập bị từ chối");
        p.setButtonText(PopupButton.OK, "Đăng nhập");
        p.setButtonText(PopupButton.Cancel, "Đăng ký");
    </script>
</body> 
