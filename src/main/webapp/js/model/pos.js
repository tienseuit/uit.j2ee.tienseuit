

function MODEL_POS() {
    var t = this;
    t.list = ko.observableArray([]);
    t.bill = ko.observable();
    t.billProduct = ko.observableArray([]);
    t.ready = ko.observable();
    t.subtotal = ko.observable(0);
    t.moneyGet = ko.observable(0);


    (function () { // load local data
        var posData;
        try {
            var info = localStorage.info;
            posData = JSON.parse(info);
        } catch (e) {
        }

        if (posData== null || posData.id == null || posData.shopId == null || posData.staffId == null) {
            var p = Popup.error(
                    "Bạn cần cài đặt POS trước khi sử dụng",
                    function () {
                        location.href = "/business/pos-setting";
                    },
                    PopupButtons.OK
                    );

            p.setButtonText(PopupButton.OK, "Cài đặt POS");
            $("body").addClass("load-failed");
        } else {
            var bill = new APIBill.BillEntity();
            bill.shopId = posData.shopId;
            bill.staffId = posData.staffId;
            bill.deviceId = posData.id;
            t.bill = ko.observable(bill);
        }
    })();
    if (t.bill() == null)
        return t;

    function clearBillProduct() {
        var lp = t.billProduct();
        var n = lp.length;
        for (var i = 0; i < n; i++) {
            var item = lp[0];
            item.quantity = 0;
            t.list.update(item);
            t.billProduct.remove(item);
        }
    }

    function getBillDetail() {
        var lp = t.billProduct();
        var n = lp.length;
        var re = [];
        for (var i = 0; i < n; i++) {
            re.push({
                productId: lp[i].id,
                quantity: lp[i].quantity,
                subtotal: lp[i].quantity * lp[i].price
            });
        }

        return re;
    }

    t.insertBill = function () {
        var data = t.editing();
        var validateMessage = APIProduct.validateProduct(data);
        if (validateMessage.length > 0) {
            Popup.error(validateMessage);
            return;
        }

        var isInsert = data.id == null;

        function onSaveDone(res) {
            if (res.statusCode == BUSResponeCode.ok) {
                Object.assign(selecting, res.data);

                if (isInsert) // insert model
                    t.list.push(selecting);
                else     // update model
                    t.list.update(selecting);
                t.editing(null);
                Popup.success();
            } else {
                console.error(res);
                Popup.failed();
            }
        }
        if (isInsert) {
            APIProduct.InsertProduct(data, onSaveDone);
        } else {
            APIProduct.UpdateProduct(data, onSaveDone);
        }
    };
    t.select = function (data) {
        data.quantity++;
        t.list.update(data);

        t.billProduct.remove(data);
        t.billProduct.unshift(data);
    }
    t.unSelect = function (data) {
        data.quantity--;
        t.list.update(data);

        if (data.quantity == 0)
            t.billProduct.remove(data);
        else
            t.billProduct.update(data);
    }
    t.moneyInputKeyDown = function (model, e) {
        if (e.key == 'e')
            return false;

        t.moneyGet(e.currentTarget.value);

        return true;
    }
    t.makeBill = function () {
        var data = t.bill();
        var billProduct = t.billProduct();

        if (billProduct.length == 0)
            return;

        data.time = new Date();
        var billDetail = getBillDetail();
         
        var validateMessage = APIBill.validateBill(data);
        if (validateMessage.length > 0) {
            Popup.error(validateMessage)
                    .setTitle("Không thể bán");
            return;
        }
        
        if(t.moneyGet() < data.subtotal){
            Popup.error("Khách hàng chưa trả đủ tiền")
                    .setTitle("Không thể bán");
            return;
        }
            

        APIBill.InsertBill(data, billDetail, function (res) {
            if (res.statusCode == BUSResponeCode.ok) {
                
                if(t.moneyGet() > data.subtotal)
                    Popup.info("Thanh toán thành công<br><b>Đừng quên trả lại tiền thừa</b>").setTitle("CHÚ Ý, CHÚ Ý");
                else
                    Popup.info("Thanh toán thành công");
                
                t.moneyGet(0);
                clearBillProduct();
            } else {
                console.error(res);
                Popup.error("Thanh toán chưa thành công");
            }
        });
    }

    t.subtotal.subscribe(function (val) {
        if (isNaN(val)) {
            t.subtotal(0);
            return;
        }

        t.bill().subtotal = val;
    });
    t.billProduct.subscribe(function (arr) {
        var n = arr.length;
        var subtotal = 0;
        for (var i = 0; i < n; i++) {
            subtotal += arr[i].price * arr[i].quantity;
        }
        t.subtotal(subtotal);
    });

    APIProduct.GetProductListOfShop(t.bill().shopId, function (res) {
        if (res.statusCode == BUSResponeCode.ok) {
            var l = res.data;
            var n = l.length;
            for (var i = 0; i < n; i ++)
                l[i].quantity = 0;
            t.list(l);
        }

    }, null, 1);
}


var MODEL = new MODEL_POS();
(function () {
    var m = MODEL;

})();

ko.applyBindings(MODEL);
 