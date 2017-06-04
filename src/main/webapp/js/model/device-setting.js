function MODEL_DEVICE_SETTING() {
    var t = this; 
    t.editing = ko.observable();
    t.listShop = ko.observableArray([]);
    var currentUserID;
    t.saveEdit = function () {
        var data = t.editing();
        var msg = APIDevice.validateDevice(data)
        if (msg.length) {
            Popup.error(msg);
            return;
        }

        var isInsert = data.id == null;

        function onSaveDone(res) {
            if (res.statusCode == BUSResponeCode.ok) {
                var d = res.data;
                d.staffId = currentUserID;
                t.editing(d);
                localStorage.info = JSON.stringify(d);
                console.log(res.message);
                Popup.success();
            } else {
                console.error(res);
                Popup.failed();
            }
        }
        if (isInsert) {
            APIDevice.InsertDevice(data, onSaveDone);
        } else {
            APIDevice.UpdateDevice(data, onSaveDone);
        }
    };

    t.rollBack = function () {
        location.href="/business/pos";
    }

    APIShop.GetShopList(null, null, null, function (res){
        if(res.statusCode == BUSResponeCode.ok){
            if(res.data.length > 0){
                t.listShop(res.data);
            }else{
                Popup.error("Bạn cần có ít nhất một shop mới có thể sử dụng chức năng này");
            }
        }
    });
    APIUser.GetCurrentUserID(function(res){
        if(res.statusCode == BUSResponeCode.ok){
            currentUserID = res.data; 
        } else {
            Popup.error("Đã xảy ra lỗi trong lúc tải dữ liệu");
        }
    });
    try {
        var json = localStorage.info;
        var data = JSON.parse(json);
        t.editing(data);
    } catch (ex) {
        var data = new APIDevice.DeviceEntity();
        data.mac="updating";
        data.staffId = APP.currentShopID
        t.editing(data);
    }
     
}

var MODEL = new MODEL_DEVICE_SETTING();
ko.applyBindings(MODEL, $("ko-binding")[0])