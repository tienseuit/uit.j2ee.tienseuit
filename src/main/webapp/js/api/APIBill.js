var APIBill = {};
APIBill.BUSClassName = "BillBUS";
APIBill.BillEntity = function () {
    var t = this;
    t.id;
    t.staffId;
    t.customerId;
    t.time;
    t.subtotal;
    t.shopId;
    t.deviceId;
};

APIBill.validateBill = function (data) {
    var re = "";
    re += Util.ckNumNull("Thông tin nhân viên", data.staffId);
    re += Util.ckNumNull("Thông tin cửa hàng", data.shopId);
    re += Util.ckNumNull("Thông tin thiết bị", data.deviceId);

    return re.length == 0 ? re : re.replace("<br>", "");
} 

APIBill.InsertBill = function (data, detailData, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIBill.BUSClassName,
            "InsertBill",
            ["jsonData", "jsonDetailData"],
            [JSON.stringify(data), JSON.stringify(detailData)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
 