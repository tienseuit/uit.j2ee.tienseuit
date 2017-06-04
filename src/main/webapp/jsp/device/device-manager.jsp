<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body>
        <jsp:include page="../component/page-head.jsp"/>
        <jsp:include page="../component/nav.jsp"/>
        <div class="container" id="ko-binding">
            <!--header-->
            <!--ko if: list().length > 0-->
            <div class="row" >
                <div class="col-sm-7">
                    <h1>Quản lý POS</h1>
                </div>
            </div>
            <div class="row">
                <table class="table" >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên</th>
                            <!--<th>MAC</th>-->
                            <th>Địa chỉ</th>
                            <th>Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody data-bind="foreach: list">
                        <tr>
                            <td data-bind="text: $index() + 1"></td>
                            <td data-bind="text: name"></td>
                            <!--<td data-bind="text: mac"></td>-->
                            <td data-bind="text: $data.address || ''"></td>
                            <td>

                                <a class="btn btn-primary" data-bind="click: $root.showEdit" title="sửa thông tin">
                                    <span class="glyphicon glyphicon-pencil">

                                    </span></a> 
                                <a class="btn btn-danger" data-bind="click: $root.remove" title="xóa thiết bị">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>


            </div>
            <!--/ko-->

            <!--ko if: list().length == 0-->
            <br>
            <br>
            <h3 style="text-align: center;">
                <a>Chưa có POS nào</a> 
            </h3>
            <!-- /ko -->

            <!-- ko if: editing()-->
            <div class="popup-wrap">
                <div class="popup-box popup-box-md panel panel-default">
                    <div class="panel-heading">
                        <strong class="panel-title" data-bind="text: 'Sửa thông tin POS ' +  editing().name"></strong>
                        <button data-bind="click: closeEdit" title="hoàn tác" class="btn btn-primary btn-md">
                            <span class="glyphicon glyphicon-arrow-left"/>
                        </button>
                    </div>
                    <div class="panel-body">
                          <table class="table table-form">
                            <tr> 
                                <td class="col-md-3"><strong>Tên: </strong></td>
                                <td class="col-md-8"><input class="form-control" data-bind="value: editing().name" placeholder="Tên của máy POS"></td>
                            </tr>
                            <tr> 
                                <td><strong>Cửa hàng: </strong></td>
                                <td>  
                                    <select class="form-control" data-bind="options: listShop,
                                        optionsValue: 'id',
                                        optionsText: 'name',
                                        value: editing().shopId,
                                        optionsCaption: 'Chọn cửa hàng cho máy POS',
                                        "></select>
                                </td>
                            </tr>
                            <tr> 
                                <td><strong>Địa chỉ: </strong></td>
                                <td><textarea class="form-control" data-bind="value: editing().address" placeholder="Địa chỉ đặt máy POS"></textarea></td>
                            </tr>
                        </table> 
                        <div class="table-form-action"> 
                            <button  data-bind="click: saveEdit" class="btn btn-primary btn-lg glyphicon glyphicon-floppy-disk">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ko-->
        </div>

        <ct:inclue src="js/api/APIShop.js"/>
        <ct:inclue src="js/api/APIDevice.js"/>
        <ct:inclue src="js/model/device-manager.js"/>
    </body>
</html>
