var APIShop = {};
APIShop.BUSClassName = "ShopBUS";
APIShop.ShopEntity = function () {
    var t = this;
    t.id;
    t.name;
    t.address;
};

APIShop.validateShop = function(data, edit){
    if (edit) {
        data.name = data.name && data.name.trim();
    }

    return false == (
            data.name == false
            );
}

APIShop.GetShopList = function (keyword, index, count, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIShop.BUSClassName,
            "GetShopList",
            ["keyword", "index", "count"],
            [keyword, index, count],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}
    
APIShop.InsertShop = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIShop.BUSClassName,
            "InsertShop",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIShop.UpdateShop = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIShop.BUSClassName,
            "UpdateShop",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIShop.DeleteShop = function (ID, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIShop.BUSClassName,
            "DeleteShop",
            ["ID"],
            [ID],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}


 