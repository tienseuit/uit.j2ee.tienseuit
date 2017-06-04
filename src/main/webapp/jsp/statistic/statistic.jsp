<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body>
        <jsp:include page="../component/page-head.jsp"/>
        <jsp:include page="../component/nav.jsp"/>
        <div class="container">

            <style>
                input{
                    min-width: 25px;
                }

                .t-right{
                    text-align: right;  
                }

                .time-lable{
                    min-width: 88px;
                }

                .static-option{
                    text-align: center;
                    font-size: large;
                }
                .summary > .summary-info{
                    display: block;
                    float: right;
                    width: 444px;
                    font-size: x-large;

                    color: #890000;
                    padding: 30px 30px 30px 77px;
                    margin-right: -30px;
                    background: linear-gradient(to right, white , #ecf3ff 92%, white);
                }

                .summary > .summary-info > .subtotal{
                    float: right;
                }
                .progress-bar{
                    color: black;
                }
            </style>

            <!--header-->
            <div class="row lm-head" >
                <h2 data-bind="text: 'Thống kê doanh thu các ' + title() + ' của ' + APP.currentShopName"> </h2> 
            </div> 
            <div class="row" >
                <table class="table" > 
                    <tr>
                        <th colspan="4" class="col-xs-6 static-option"><strong>Thời gian báo báo</strong></th>
                        <th class="col-xs-1"></th>
                        <th colspan="4" class="col-xs-5 static-option"><strong>Loại thống kê</strong></th>
                    </tr> 
                    <tr>
                        <th class="time-lable t-right"><i>Từ ngày</i></th>
                        <th><input class="form-control"type="date" data-bind="datePicker: startTime"></th>
                        <th class="time-lable t-right"><i>Đến ngày</i></th>
                        <th><input class="form-control" type="date" data-bind="datePicker: endTime"></th>
                        <th></th>
                        <th style="width: 15px"><input class="form-control" data-bind="event:{change: getProductStatistic}" checked value="1" type="radio" name="s-method"></th>
                        <th><i>Theo mặt hàng</i></th>
                        <th style="width: 15px"><input class="form-control" data-bind="event:{change: getPOSStatistic}" type="radio" name="s-method"  value="2" ></th>
                        <th><i>Theo POS</i></th>
                    </tr> 
                </table>
            </div> 

            <div data-bind="if: isProductMethod() == 1">
                <!--ko if: listProduct().length > 0-->
                <div class="row"> 
                    <table class="table" >
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên mặt hàng</th>
                                <th class="t-right">Số lượng bán</th>
                                <th class="t-right">Đơn giá</th>
                                <th class="t-right">Doanh thu</th>
                                <th class="t-right">Tỷ lệ</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: listProduct">
                            <tr>
                                <td data-bind="text: $index() + 1"></td>
                                <td data-bind="text: name"></td>
                                <td data-bind="text: quantity" class="t-right"></td>
                                <td data-bind="text: price" class="t-right"></td>
                                <td data-bind="text: subtotal" class="t-right"></td>
                                <!--<td data-bind="text: percent" class="t-right"></td>-->
                                <td>
                                    <div class="progress" style="margin: 0;">
                                        <div data-bind="text: percent, style:{width: percent}" class="progress-bar progress-bar-info progress-bar-striped" role="progressbar">
                                         </div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="row summary"> 
                    <div class="summary-info">
                        <strong>Tổng doanh thu: </strong>
                        <strong class="subtotal" data-bind="text: sum() + 'đ'"></strong>
                    </div>
                </div>   
                <!-- /ko --> 
                <!--ko if: listProduct().length == 0-->
                <br>
                <br>
                <h3 style="text-align: center; cursor: pointer">
                    <a>Chưa có dữ liệu</a>
                </h3>
                <!-- /ko --> 
            </div>  

            <div data-bind="if: isProductMethod() == 0">
                <!--ko if: listPOS().length > 0-->
                <div class="row"> 
                    <table class="table" >
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên POS</th>
                                <th>Địa chỉ</th>
                                <th class="t-right">Doanh thu</th>
                                <th class="t-right">Tỷ lệ</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: listPOS">
                            <tr>
                                <td data-bind="text: $index() + 1"></td>
                                <td data-bind="text: name"></td>
                                <td data-bind="text: address" class="t-right"></td>
                                <td data-bind="text: subtotal" class="t-right"></td>
                                <!--<td data-bind="text: percent" class="t-right"></td>-->
                                <td>
                                    <div class="progress" style="margin: 0;">
                                        <div data-bind="text: percent, style:{width: percent}" class="progress-bar progress-bar-info progress-bar-striped" role="progressbar">
                                         </div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div> 
                <div class="row summary"> 
                    <div class="summary-info">
                        <strong>Tổng doanh thu: </strong>
                        <strong class="subtotal" data-bind="text: sum() + 'đ'"></strong>
                    </div>
                </div>   
                <!-- /ko --> 
                <!--ko if: listPOS().length == 0-->
                <br>
                <br>
                <h3 style="text-align: center; cursor: pointer">
                    <a>Chưa có dữ liệu</a>
                </h3>
                <!-- /ko -->
            </div>  
        </div>

        <ct:inclue src="js/api/APIStatistic.js"/>
        <ct:inclue src="js/model/statistic.js"/>
    </body>
</html>
