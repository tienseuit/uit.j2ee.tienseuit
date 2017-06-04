function MODEL_SHOP() {
    var t = this;
    var selecting = null;
    t.list = ko.observableArray([]);
    t.editing = ko.observable();

    t.showEdit = function (data) {
        if (data == null)
            data = new APIShop.ShopEntity();

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
        if (APIShop.validateShop(data) == false) {
            Popup.error("Vui lòng điền tên cửa hàng");
            return;
        }

        var isInsert = data.id == null;

        function onSaveDone(res) {
            if (res.statusCode == BUSResponeCode.ok) {
                Object.assign(selecting, res.data);

                if (isInsert){ // insert model{ 
                    t.list.push(selecting);
                    var a = $('<a/>')
                    a.attr("id", "shop-item-" + selecting.id);
                    a.attr("href", "?shopID=" + selecting.id);
                    a.text(selecting.name);
                    var li = $("<li/>");
                    li.append(a);
                    $("#nav-shop-list").append(li);
                }
                else {    // update model
                    t.list.update(selecting);
                    $("#shop-item-" + selecting.id).text(selecting.name)
                }
                t.editing(null);
                Popup.success();
            } else {
                console.error(res);
                Popup.failed();
            }
        }
        if (isInsert) {
            APIShop.InsertShop(data, onSaveDone);
        } else {
            APIShop.UpdateShop(data, onSaveDone);
        }
    };

    t.remove = function (data) {
        if (data == null || data.id == null)
            return;

        Popup.warning(
                "Bạn có chắc chắn muốn xóa cửa hàng <b>" + data.name + "</b>",
                function (re) {
                    if (re == PopupButton.OK) {

                        APIShop.DeleteShop(data.id, function (res) {
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

    APIShop.GetShopList('', '', '', function (res) {
        if (res.statusCode == BUSResponeCode.ok)
            t.list(res.data);
    }, null, 1);
}

var MODEL = new MODEL_SHOP();
ko.applyBindings(MODEL, $("ko-binding")[0])