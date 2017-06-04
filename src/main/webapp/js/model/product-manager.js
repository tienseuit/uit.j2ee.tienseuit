function MODEL_PRODUCT() {
    var t = this;
    var selecting = null;
    t.list = ko.observableArray([]);
    t.editing = ko.observable();
    t.joFile = $("#ipFile");

    t.showEdit = function (data) {
        
        
        if (data == null){
            if(APP.currentShopID == null){
                Popup.error("Bạn đang xem mặt hàng của tất cả cửa hàng<br><br>Bạn cần chọn một cửa hàng cụ thể mới có thể thêm mặt hàng");
                return;
            }
            data = new APIProduct.ProductEntity();
            data.shopId = APP.currentShopID;
        }
         
        selecting = data;
        var temp = {};
        Object.assign(selecting, data);
        t.editing(selecting);
    };

    t.closeEdit = function () {
        selecting = null;
        t.editing(null);
    };

    t.saveEdit = function () {
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

    t.remove = function (data) {
        if (data == null || data.id == null)
            return;

        Popup.warning(
                "Bạn có chắc chắn muốn xóa cửa hàng <b>" + data.name + "</b>",
                function (re) {
                    if (re == PopupButton.OK) {

                        APIProduct.DeleteProduct(data.id, function (res) {
                            if (res.statusCode == BUSResponeCode.ok) {
                                t.list.remove(data);
                                Popup.success();
                            } else {
                                Popup.failed();
                            }
                        });
                    }
                },
                PopupButtons.OKCancel
                );
    };

    t.choseImage = function () {
        t.joFile.click();
    }

    APIProduct.GetProductList('', '', '', function (res) {
        if (res.statusCode == BUSResponeCode.ok)
            t.list(res.data);
    }, null, 1);

    t.joFile.change(function () {
        var files = t.joFile[0].files;
        if (files.length != 1)
            return;
        APIProduct.UploadImage(files[0], function (res) {
            if(res.statusCode == BUSResponeCode.ok){
                var ob = t.editing();
                ob.image = res.data;
                t.editing(ob);
            }                
            else{
                Popup.error("Upload hình không thành công");
            }
        });
    });
}

var MODEL = new MODEL_PRODUCT();
(function () {
    var m = MODEL;

    m.txtProducListOf = "Danh sách mặt hàng của cửa hàng";

})();

ko.applyBindings(MODEL, $("ko-binding")[0])