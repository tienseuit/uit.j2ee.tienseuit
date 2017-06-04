

/*
 create search model

 _________________________________________________
 method
 setDataSource
 dataSource: list
 searchMember: list of object { Name, IsUnicode}
 -- ex:
 var x; // instance of SearchModel
 var dataSource = [{Value: "v", Display: "đa tềnh tự cổ năng di hận"}];
 var searchMember = [{Name: 'Value', IsUnicode: false}, {Name: 'Display', IsUnicode:true}];
 x.setDataSource(dataSource, searchMember);
 */
function SearchModel(dataSource, searchMember){

    var t = this;
    var source = [];
    var listSearchMethod = [];
    var _unicode = "àáâãèéêìíòóôõùúýăđĩũơưạảấầẩẫậắằẳẵặẹẻẽếềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ";
    var _ascii   = "aaaaeeeiioooouuyadiuouaaaaaaaaaaaaeeeeeeeeiioooooooooooouuuuuuuyyyy";
    function getUchar(inputC){
        var minIndex = 0;
        var maxIndex = _unicode.length - 1;
        var i;
        var c;
        while (minIndex <= maxIndex) {
            i = (minIndex + maxIndex) / 2 | 0;
            c = _unicode[i];

            if (c < inputC) {
                minIndex = i + 1;
            }
            else if (c > inputC) {
                maxIndex = i - 1;
            }
            else {
                return _ascii[i];
            }
        }

        return inputC;
    };

    function optimize1(str){
        try{
            return str.replace(/ +(?= )/g,'').trim().toLowerCase();
        }catch (ex){
            return "";
        }
    };

    function optimize2(strOpt1) {
        var chars = [];
        var n = strOpt1.length;
        for(var i = 0; i < n; i++)
            chars.push(getUchar(strOpt1[i]));

        return chars.join("");
    };

    t.setDataSource = function(dataSource, searchMember){
        source = dataSource;
        listSearchMethod = [];

        for(var index = 0 ; index  < searchMember.length; index++){
            var item = searchMember[index];
            var n = dataSource.length;
            var srcOpt1 = [];
            var searchMethod;
            var mName = item.Name;

            if(item.IsUnicode){
                var srcOpt2 = [];

                for(var i = 0 ; i  < n; i++){
                    var strOpt1 = optimize1(dataSource[i][mName]);
                    var strOpt2 = optimize2(strOpt1);
                    srcOpt1.push(strOpt1);
                    srcOpt2.push(strOpt2);
                }

                searchMethod = new SearchMethodUnicode(srcOpt1, srcOpt2);
            } else {
                for(var i = 0 ; i  < n; i++){
                    var strOpt1 = optimize1(dataSource[i][mName]);
                    srcOpt1.push(strOpt1);
                }

                searchMethod = new SearchMethodAscii(srcOpt1);
            }
            listSearchMethod.push(searchMethod);
        }
    };

    t.search = function(keyword){
        var strOpt1 = optimize1(keyword);
        var strOpt2 = optimize2(strOpt1);
        var n = source.length;
        var lSM = listSearchMethod;
        var nSM = lSM.length;
        var re = [];
        for(var i = 0; i < n; i++){
            for(var iSM = 0; iSM < nSM; iSM++){
                if(lSM[iSM].match(i, strOpt1, strOpt2)){
                    re.push(source[i]);
                    break;
                }
            }
        }
        return re;
    }

    dataSource && t.setDataSource(dataSource, searchMember);
}

function SearchMethodAscii(srcOpt1){
    var t = this;

    t.match = function(index, strOpt1, strOpt2){
        return srcOpt1[index].indexOf(strOpt1) == -1 ? 0 : 1;
    };
}

function SearchMethodUnicode(srcOpt1, srcOpt2){
    var t = this;

    t.match = function(index, strOpt1, strOpt2){
        if(srcOpt1[index].indexOf(strOpt1) > -1)
            return 1;

        return srcOpt2[index].indexOf(strOpt2) == -1 ? 0 : 1;
    };
}