var APIStatistic = {};
APIStatistic.BUSClassName = "StatisticBUS";

APIStatistic.GetProductStatisticByShop = function (shopID, startTime, endTime, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIStatistic.BUSClassName,
            "GetProductStatisticByShop",
            ['shopID', "startTime", "endTime"],
            [shopID, JSON.stringify(startTime), JSON.stringify(endTime)],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}

APIStatistic.GetPOSStatisticByShop = function (shopID, startTime, endTime, onSuccess, onError, skipIfEror) {
    Ajax.J2EE(
            APIStatistic.BUSClassName,
            "GetPOSStatisticByShop",
            ['shopID', "startTime", "endTime"],
            [shopID, JSON.stringify(startTime), JSON.stringify(endTime)],
            Ajax.callback.array,
            onSuccess,
            onError,
            skipIfEror
            );
}

 