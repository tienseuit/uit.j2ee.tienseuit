package uit.j2ee.model;

import java.util.List;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.been.Device;
import uit.j2ee.been.Shop;
import uit.j2ee.core.Business;
import uit.j2ee.util.BUSReflection;
import uit.j2ee.util.Json;
import uit.j2ee.util.NumberUtil;
import uit.j2ee.util.StringUtil;

public class DeviceBUS extends Business<Device> {

    public DeviceBUS() {
        super(Device.class);
    }

    public BUSRespone GetDeviceList(String keyword, String index, String count) throws Exception {
        if(checkRoleOfCurrentMethod()== false)
            return new BUSRespone(null, BUSStatus.denied());
        
        return selectByShop();
    }

    public BUSRespone InsertDevice(String jsonData) throws Exception{
        if(checkRoleOfCurrentMethod()== false)
            return new BUSRespone(null, BUSStatus.denied());
       
        return insertWithJsonData(jsonData);
    }

    public BUSRespone GetDeviceById(String ID){
        Integer id;
        try {
            id = Integer.valueOf(ID);
        } catch (Exception e) {
            id = null;
        }
        
        try {
            Device re = selectByID(id);
            return new BUSRespone(re);
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.dbFailedd(), e);
        }
    }
    
    public BUSRespone UpdateDevice(String jsonData)  throws Exception{
        if(checkRoleOfCurrentMethod()== false)
            return new BUSRespone(null, BUSStatus.denied());
        
         
        Device data; 
        try{
            data = Json.toObject(jsonData, _BeenClass);
        }catch(Exception ex){
            return new BUSRespone(null, BUSStatus.paramsInvalid(), ex);
        }
        
        Integer ID = data.getId();
        String strID = ID == null ? null : ID.toString();
        BUSRespone res = GetDeviceById(strID);
        if(res.statusCode != BUSResponeCode.ok)
            return res;
         
       try {
           BUSRespone re =  new BUSRespone(data);
           if(res.data == null){
                insert(data);
                re.message = "insert";
           }else{ 
               data.setId(((Device)res.data).getId());
               update(data);
                re.message = "update";
           }           
            return re;
        } catch (Exception e) {
            return new BUSRespone(null, BUSStatus.exceptiond(), e);
        }
    }

    public BUSRespone DeleteDevice(String ID)  throws Exception{
        if(checkRoleOfCurrentMethod()== false)
            return new BUSRespone(null, BUSStatus.denied());
        
        return deleteWithJsonData(ID);
    }

}
