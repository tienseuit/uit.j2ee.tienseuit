var APIDevice = {};
APIDevice.BUSClassName = "DeviceBUS";
APIDevice.DeviceEntity = function () {
    var t = this;
    t.id;
    t.name;
    t.mac;
    t.address;
    t.shopId;

};

APIDevice.validateDevice = function (data, edit) {
    if (edit) {
        data.name = data.name && data.name.trim();
        data.address = data.address && data.address.trim();
    }

    var re = "";
    re += Util.ckStrNullMax("Tên POS", data.name, 200); 
    
    if(data.shopId == null)
        re+="<br>Vui lòng chọn một cửa hàng";
    
    return re.length == 0 ? re : re.replace("<br>", "");
}

APIDevice.GetDeviceList = function (keyword, index, count, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIDevice.BUSClassName,
            "GetDeviceList",
            ["keyword", "index", "count"],
            [keyword, index, count],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}

APIDevice.InsertDevice = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIDevice.BUSClassName,
            "InsertDevice",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIDevice.UpdateDevice = function (data, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIDevice.BUSClassName,
            "UpdateDevice",
            ["jsonData"],
            [JSON.stringify(data)],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIDevice.DeleteDevice = function (ID, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIDevice.BUSClassName,
            "DeleteDevice",
            ["ID"],
            [ID],
            Ajax.callback.object,
            onSuccess,
            onError,
            skipIfEror
            );
}
APIDevice.UploadImage = function (file, onSuccess, onError, skipIfEror) {


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
APIDevice.cutShortDescription = function(data) {
    var temp = data.shortDescription;
    if(temp == null)
        return "";
   return temp.length < APIDevice.ShortDescription ? temp : temp.substr(0, APIDevice.ShortDescription - 3) + "...";
}


 