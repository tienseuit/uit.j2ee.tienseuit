Util = new function () {
    var t = this;

    var URLParams = {};

    t.getURLValue = function (name) {
        if (URLParams.hasOwnProperty(name))
            return URLParams[name];

        var url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);

        var re;
        if (!results)
            re = null;
        else if (!results[2])
            re = '';
        else
            re = decodeURIComponent(results[2].replace(/\+/g, " "));

        URLParams[name] = re;
        return re;
    }
    t.setURLVales = function (name, value) {
        var url = window.location.href;
        var hash = location.hash;
        url = url.replace(hash, '');
        if (url.indexOf(name + "=") >= 0)
        {
            var prefix = url.substring(0, url.indexOf(name));
            var suffix = url.substring(url.indexOf(name));
            suffix = suffix.substring(suffix.indexOf("=") + 1);
            suffix = (suffix.indexOf("&") >= 0) ? suffix.substring(suffix.indexOf("&")) : "";
            url = prefix + name + "=" + value + suffix;
        } else
        {
            if (url.indexOf("?") < 0)
                url += "?" + name + "=" + value;
            else
                url += "&" + name + "=" + value;
        }

        URLParams[name] = value;
        if (location.href.endsWith(url) == false)
            history.pushState(null, document.title, url);
    }



    // kiểm tra chuỗi, nếu null hoặc dài hơn max ký tự thì trả về thông báo, ngược lại trả về null
    t.ckStrNullMax = function (name, str, max = 50) {
        if (str == null || str == "")
            return "<br>" + name + " không thể bỏ trống";
        else
            return t.ckStrMax(name, str, max);
    }

    t.ckNumNull = function (name, num) {
        if (num == null)
            return "<br>" + name + " không thể bỏ trống";
        else if (isNaN(num))
            return "<br>" + name + " không hợp lệ";
        else
            return "";
    }

    t.ckNull = function (name, num) {
        if (num == null)
            return "<br>" + name + " không thể bỏ trống";
        else
            return "";
    }

    // kiểm tra chuỗi, nếu dài hơn max ký tự thì trả về thông báo, ngược lại trả về null
    t.ckStrMax = function (name, str, max = 50) {
        if (str != null && str.length > max)
            return "<br>" + name + " không thể dài hơn " + max + " ký tự";

        return "";
    }
//    t.setUtilClose = function (joContainer, onClose, byHotKey = 1, byBackground = 1, doRemove = 1) {
//        if(byHotKey){
//            
//        }
//        
//        
//        function doClose(e) {
//            if (e.target == joContainer[0]) {
//                if (doRemove)
//                    t.remove();
//                else
//                    t.hide();
//
//                onClose && onClose();
//            }
//        }
//
//        function hotKey(e) {
//            switch (e.keyCode) {
//                case 27:
//                    t.remove();
//                    break; //  ESC
//            }
//        }
//
//        $(window).bind("keydown", doClose);
//        joContainer.click(doClose);
//    }

}