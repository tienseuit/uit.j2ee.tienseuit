<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body>
        <jsp:include page="../component/page-head.jsp"/>
        <jsp:include page="../component/nav.jsp"/>
        <input id="ipFile" type="file" accept="image/*" style="position: fixed; left: -1000%">

        <div class="container" id="ko-binding">

            <!--header-->
            <!--ko if: list().length > 0-->
            <div class="row lm-head" >
                <div class="col-sm-12" style="padding: 0 5%;">
                    <h2 data-bind="text: 'Danh sách mặt hàng của ' + APP.currentShopName"> </h2>
                    <button class="btn btn-info btn-md" data-bind="click:function(){showEdit()}">Thêm mặt hàng</button>
                </div>
            </div>
            <!--/ko-->
        </div> 

        <div class="container" id="ko-binding">
            <!--ko if: list().length > 0-->
            <div class="row" data-bind="foreach : list">
                <div class="product">
                    <div class="p-mainInfo">
                        <div class="image">
                            <img data-bind="attr:{src: image}">
                        </div>
                        <div class="p-info">
                            <a class="p-name" data-bind="text: name"></a>
                            <a class="p-price" data-bind="text: price + ' đ'"></a>
                        </div>
                    </div> 
                    <div class="p-des"><samp data-bind="text: APIProduct.cutShortDescription($data)"></samp></div>
                    <div class="p-admin"> 
                        <ul>
                            <li>
                                <a class="btn btn-primary" data-bind="click: $root.showEdit" title="sửa thông tin">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a> 
                            </li>
                            <li>
                                <a class="btn btn-danger" data-bind="click: $root.remove" title="xóa mặt hàng">
                                    <span class="glyphicon glyphicon-remove" ></span>
                                </a> 
                            </li>
                        </ul>
                        <div class="p-info">
                            <!--ko if: isPublic-->
                            <strong>Công khai</strong> 
                            <!--/ko-->
                            <!--ko if: isPublic != true-->
                            <strong>Không công khai</strong> 
                            <!--/ko-->
                        </div>
                    </div>

                </div>
            </div>

            <!--/ko-->

            <!--ko if: list().length == 0-->
            <br>
            <br>
            <h3 style="text-align: center; cursor: pointer">
                <a data-bind="click: function(){showEdit()}">Cửa hàng này chưa có mặt hàng nào<br> tôi muốn thêm mặt hàng</a>
            </h3>
            <!-- /ko --> 
            <!-- ko if: editing()-->
            <div class="popup-wrap">
                <div class="popup-box popup-box-md panel panel-default">
                    <div class="panel-heading">
                        <!--ko if:editing().id == null-->
                        <strong class="panel-title" data-bind="text: 'Thêm mặt hàng cho cửa hàng ' + APP.currentShopName"></strong>
                        <!--/ko-->
                        <!--ko if:editing().id != null-->
                        <strong class="panel-title" data-bind="text: 'Sửa thông tin mặt hàng ' +  editing().name"></strong>
                        <!--/ko-->
                        <button data-bind="click: closeEdit" title="hoàn tác" class="btn btn-primary btn-md">
                            <span class="glyphicon glyphicon-arrow-left"/>
                        </button>
                    </div>
                    <div class="panel-body row">
                        <div class="col-sm-4" style="text-align: center; padding-bottom: 50px">
                            <img data-bind="click: choseImage, attr: {src: editing().image || '/image/img-place.jpg'}" title="Chọn hình minh họa" style="min-height: 200px; width: 223px; cursor: pointer"> 
                        </div>
                        <div class="col-sm-8" style="margin-top: 34px;">
                            <table class="table table-form">
                                <tr> 
                                    <td class="col-xs-2"><strong>Tên: </strong></td>
                                    <td colspan="2">
                                        <input class="form-control" data-bind="value: editing().name" placeholder="Tên của mặt hàng" maxlength="200">
                                    </td>
                                </tr>
                                <tr> 
                                    <td><strong>Giá: </strong></td>
                                    <td>
                                        <input class="form-control" data-bind="value: editing().price" placeholder="Đơn giá" maxlength="20">
                                    </td>
                                    <td class="col-xs-5">
                                        <input class="form-control" data-bind="value: 1, checked: editing().isPublic" type="checkbox" value="" style="float: right; width: 29px; margin-left: 10px">
                                        <strong style="float:right; padding-top: 8px;">Công khai</strong> 
                                    </td>
                                </tr>
                                <tr> 
                                    <td><strong>Mô tả: </strong></td>
                                    <td  colspan="2"><textarea class="form-control" data-bind="value: editing().shortDescription" placeholder="Mô tả ngắn về mặt hàng" maxlength="200"></textarea></td>
                                </tr>
                            </table> 
                        </div>
                        <div class="table-form-action"> 
                            <button  data-bind="click: saveEdit" class="btn btn-primary btn-lg glyphicon glyphicon-floppy-disk">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ko-->
        </div>

        <ct:inclue src="js/api/APIProduct.js"/>
        <ct:inclue src="js/model/product-manager.js"/>
    </body>
</html>
