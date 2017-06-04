<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body> 
        <div class="container" data-bind="if: listShop().length > 0">
            <div class="popup-wrap">
                <div class="popup-box popup-box-md panel panel-default">
                    <div class="panel-heading">
                        <strong class="panel-title">Cài đặt điểm bán hàng</strong>
                        <button data-bind="click: rollBack" class="btn btn-primary btn-md">
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
        </div>

        <ct:inclue src="js/api/APIUser.js"/>
        <ct:inclue src="js/api/APIDevice.js"/>
        <ct:inclue src="js/api/APIShop.js"/>
        <ct:inclue src="js/model/device-setting.js"/>
    </body>
</html>
