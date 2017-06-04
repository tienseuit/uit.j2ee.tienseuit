window.Ajax = {};
BUSResponeCode = {

    // custom
    paramsInvalid: 601,
    jsonFailed: 611,
    dbFailed: 621,
    exception: 701,
    busFailed: 801,

    // html code standard
    ok: 200,
    denied: 403
}

Ajax.J2EE = function (className, methodName, pNames, pValues, callbackMethod, onSuccess, onError, skipIfEror) {
    var data = {
        c: className,
        m: methodName
    };
    
    if(APP.currentShopID!=null && isNaN(APP.currentShopID) == false)
        data.shopID = APP.currentShopID;
    
    var n = pNames.length;
    for (var i = 0; i < n; i++)
        data[pNames[i]] = pValues[i];

    $.ajax({
        type: 'POST',
        url: APP.contextPath + "bus",
        data: data,
        success: callbackMethod(onSuccess, methodName, skipIfEror),
        error: function (re) {
            onError && onError(re);
            console.warn({className: className, methodName: methodName, pNames: pNames, pValues: pValues, re: re});
            Popup.error(re.responseText).setTitle('Lỗi máy chủ');
        }
    });
};

Ajax.callback = new function () {
    var t = this;

    function log(name, jData, type, ex) {

        Popup.error(jData);
        console.warn("Data is not a \"" + type + "\" at: " + name);
        try {
            console.warn(JSON.parse(jData));
        } catch (ex) {
            console.warn(jData);
        }
        ex && console.error(ex);
    }
    ;
    t.string = function (callback, name, skipIfEror) {
        return callback;
    }
    t.object = function (callback, name, skipIfEror) {
        return function (jData) {
            var obj = null;
            try {
                obj = JSON.parse(jData);
            } catch (ex) {
                log(name, jData, "object", ex);
            }

            obj || log(name, jData, "object");

            if (obj || skipIfEror == flase)
                callback(obj, jData);
        };
    }
    t.array = function (callback, name, skipIfEror) {
        return function (jData) {
            var res = null;
            try {
                res = JSON.parse(jData);
            } catch (ex) {
                log(name, jData, "array", ex);
            }

            if (res && Array.isArray(res.data) && res.hasOwnProperty("statusCode")) {

                callback(res, jData);
            } else {

                log(name, jData, "array");
                if (skipIfEror == false)
                    callback(null, jData);
                return;
            }

        };
    }
    t.int = function (callback, name, skipIfEror) {
        return function (jData) {
            if (isNaN(jData)) {
                log(name, jData, "int");
                callback(null, jData);
                return;
            }

            var number = null;
            try {
                number = parseInt(jData);
            } catch (ex) {
                log(name, jData, "int", ex);
            }

            number || log(name, jData, "int");

            if (number || skipIfEror == false)
                callback(null, jData);

        };
    }
    t.float = function (callback, name, skipIfEror) {
        return function (jData) {
            if (isNaN(jData)) {
                log(name, jData, "float");
                callback(null, jData);
                return;
            }

            var number = null;
            try {
                number = parseFloat(jData);
            } catch (ex) {
                log(name, jData, "float", ex);
            }

            number || log(name, jData, "float");

            if (number || skipIfEror == false)
                callback(null, jData);
        };
    }
};
