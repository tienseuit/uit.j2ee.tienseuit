<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body> 
        <style>
            .product{
                margin: 10px 13px;
            } 

            .p-list > .product{
                width: 47%;
            } 
            input{
                text-align: right;
            }   /*            ///////////////////*/

            .product > .quantity{
                background: #fffdf4;
                border-radius: 50%;
                width: 70px;
                height: 70px;
                position: absolute;
                right: -12px;
                bottom: -12px;
                font-size: xx-large;
                text-align: center;
                padding-top: 11px;
                font-weight: bold;
            } 

            .p-list>.product > .quantity{
                display: none;
            }
            
            .p-list>.selected > .quantity{
                display: block;
            }
        </style>
        <div class="container-fluid"> 
            <!--ko if: list().length > 0-->
            <div class="col-xs-8 p-list" data-bind="foreach : list">
                <div class="product" data-bind="click: $root.select, css: {selected: quantity}">
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
                    <div class="quantity" data-bind="text: quantity"></div>
                </div>
            </div>
            <div class="bill col-xs-4">
                <div class="bill-detail" data-bind="foreach : billProduct">
                    <div class="product" data-bind="click: $root.unSelect">
                        <div class="p-mainInfo">
                            <div class="image">
                                <img data-bind="attr:{src: image}">
                            </div>
                            <div class="p-info">
                                <a class="p-name" data-bind="text: name"></a>
                                <a class="p-price" data-bind="text: price + ' đ'"></a>
                            </div>
                        </div> 
                        <div class="quantity" data-bind="text: quantity"></div>
                    </div>
                </div>
                <div class="bill-pay">
                    <div  class="subtotal">
                        <h2><a>Tổng cộng: </a><a style="float: right" data-bind="text: subtotal() + 'đ'"></a></h2>
                    </div>
                    <div class="pay-method">
                        <div class="page">
                            <table class="table">
                                <tr>
                                    <td><strong>Nhận: </strong></td>
                                    <td><input data-bind="event: {keyup: moneyInputKeyDown} ,value: moneyGet" type="number" class="form-control"></td>
                                    <td rowspan="2"><button data-bind="click: makeBill" class="btn btn-primary" style="height: 60px;">Thanh toán</button> </td>
                                </tr>
                                <tr>
                                    <td><strong>Dư: </strong></td>
                                    <td><input data-bind="value: moneyGet() - subtotal()" class="form-control" disabled style="background: white;"></td>
                                </tr>
                            </table>

                        </div>
                        <div class="page" style="display: none">
                            <button class="btn btn-primary btn-group-lg">Thanh toán</button>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button class="btn btn-primary">Thanh toán QR code</button>
                        <button class="btn btn-primary">Thanh toán tiền mặt</button> 
                    </div> 
                </div>
            </div>  

            <style>

            </style>

            <!--/ko-->

            <!--ko if: ready() && list().length == 0-->
            <br>
            <br>
            <h3 style="text-align: center; cursor: pointer">
                <a data-bind="click: function(){showEdit()}">Cửa hàng này chưa có mặt hàng nào<br> tôi muốn thêm mặt hàng</a>
            </h3>
            <!-- /ko -->
        </div>

        <ct:inclue src="js/api/APIProduct.js"/>
        <ct:inclue src="js/api/APIBill.js"/>
        <ct:inclue src="js/model/pos.js"/>
    </body>
</html>
