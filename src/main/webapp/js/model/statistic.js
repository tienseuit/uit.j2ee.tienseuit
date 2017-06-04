function MODEL_STATISTIC() {
    var t = this;
    var toDay = new Date();
    var lastMonth = new Date();
    lastMonth.setDate(toDay.getDate() - 30);

    t.listProduct = ko.observableArray([]);
    t.listPOS = ko.observableArray([]);
    t.startTime = ko.observable(lastMonth);
    t.endTime = ko.observable(toDay);
    t.sum = ko.observable(0);
    t.title = ko.observable();
    t.isProductMethod = ko.observable(1);

    var currentMethod;

    function bildListPercent(l, target) {
        var n = l.length;

        if (n == 0) {
            target([]);
            return;
        }

        var sumSubtotal = 0
        l.forEach(function (item) {
            sumSubtotal += item.subtotal;
        });

        var percentRemain = 100;
        for (var i = 1; i < n; i++) {
            var item = l[i];
            var p = item.subtotal / sumSubtotal;
            p = (Math.round(p * 10000) / 100);
            percentRemain -= p;
            item.percent = getPrecent(p);
        }
        l[0].percent = getPrecent(Math.round(percentRemain * 100) / 100);

        target(l);
        t.sum(sumSubtotal);
    }

    function getPrecent(p) {
        p = p < 10 ? '0' + p.toString() : p.toString();
        if (p.length < 3)
            p += ".00";
        else if (p.length < 5)
            p += "0";

        return p + "%";
    }

    t.getProductStatistic = function () {
        t.isProductMethod(1);
        t.title("mặt hàng");
        APIStatistic.GetProductStatisticByShop(APP.currentShopID, t.startTime(), t.endTime(), function (res) {
            if (res.statusCode == BUSResponeCode.ok) {
                bildListPercent(res.data, t.listProduct);
            }
        });
    }

    t.getPOSStatistic = function () {
        t.isProductMethod(0);
        t.title("POS");
        APIStatistic.GetPOSStatisticByShop(APP.currentShopID, t.startTime(), t.endTime(), function (res) {
            if (res.statusCode == BUSResponeCode.ok) {
                bildListPercent(res.data, t.listPOS);
            }
        });

    }

    currentMethod = t.getProductStatistic;
    currentMethod();
    
    
    function onTimeChange(){
        if(t.startTime() > t.endTime()){
            Popup.error("Thời gian bắt đầu không thế sớm hơn thời gian kết thúc");
        }else{
            currentMethod();
        }
    }
    
    t.startTime.subscribe(onTimeChange);
    t.endTime.subscribe(onTimeChange);
}

var MODEL = new MODEL_STATISTIC();


ko.bindingHandlers.datePicker = {
    init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
        // Register change callbacks to update the model
        // if the control changes.       
        ko.utils.registerEventHandler(element, "change", function () {
            var value = valueAccessor();
            value(new Date(element.value));
        });
    },
    // Update the control whenever the view model changes
    update: function (element, valueAccessor, allBindingsAccessor, viewModel) {
        var d = valueAccessor()();

        element.value =
                d.getFullYear()
                + "-" + (d.getMonth() + 1).toString().padStart(2, '0')
                + "-" + d.getDate().toString().padStart(2, '0');
    }
};
ko.applyBindings(MODEL)