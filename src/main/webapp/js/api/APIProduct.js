var APIProduct = {};
APIProduct.BUSClassName = "ProductBUS";
APIProduct.ShortDescription = ((window.innerWidth - 110) / 10 ) | 0;
APIProduct.ProductEntity = function () {
    var t = this;
    t.id;
    t.name;
    t.price;
    t.quantity;
    t.checkCount;
    t.shortDescription;
    t.shopId;
    t.isPublic;
};

APIProduct.validateProduct = function (data, edit) {
    if (edit) {
        data.name = data.name && data.name.trim();
        data.shortDescription = data.shortDescription && data.shortDescription.trim();
    }

    var re = "";
    re += Util.ckStrNullMax("Tên mặt hàng", data.name, 200);
    re += Util.ckStrNullMax("Giá mặt hàng", data.price ? data.price.toString() : "", 200);
    re += Util.ckStrMax("Mô tả ngắn", data.shortDescription, 200);
    re += Util.ckStrNullMax("Hình minh họa", data.image, 200);

    if(data.shopId == null)
        re+="<br>Chức năng đang được bảo trì";
    
    return re.length == 0 ? re : re.replace("<br>", "");
}

APIProduct.GetProductList = function (keyword, index, count, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIProduct.BUSClassName,
            "GetProductList",
            ["keyword", "index", "count"],
            [keyword, index, count],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}

APIProduct.GetProductListOfShop = function (shopID, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIProduct.BUSClassName,
            "GetProductListOfShop",
            ['shopID'],
            [shopID],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}

APIProduct.InsertProduct = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIProduct.BUSClassName,
            "InsertProduct",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIProduct.UpdateProduct = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIProduct.BUSClassName,
            "UpdateProduct",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIProduct.DeleteProduct = function (ID, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIProduct.BUSClassName,
            "DeleteProduct",
            ["ID"],
            [ID],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIProduct.UploadImage = function (file, onSuccess, onError, skipIfEror) {


    var data = new FormData();
    data.append("file", file);
    data.append("folder", "image/product");

    var callBack = Ajax.callback.object(onSuccess, "UploadImage", skipIfEror);

    $.ajax({
        type: 'POST',
        url: APP.contextPath + "upload",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: callBack,
        error: function (re) {
            onError && onError(re);
            console.warn({data: oMyForm, re: re});
            Popup.error(re.responseText).setTitle('Lỗi máy chủ');
        }
    });
}
APIProduct.cutShortDescription = function(data) {
    var temp = data.shortDescription;
    if(temp == null)
        return "";
   return temp.length < APIProduct.ShortDescription ? temp : temp.substr(0, APIProduct.ShortDescription - 3) + "...";
}


 