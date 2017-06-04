/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.app;

/**
 *
 * @author LAP10599-local
 */
public class BUSStatus {

    public int code;
    public String message;

    public static BUSStatus paramsInvalid() {
        return new BUSStatus(BUSResponeCode.paramsInvalid, "Missing request data");
    }

    public static BUSStatus jsonFailedd() {
        return new BUSStatus(BUSResponeCode.jsonFailed, "Invalid request data");
    }

    public static BUSStatus dbFailedd() {
        return new BUSStatus(BUSResponeCode.dbFailed, "Database action failed");
    }

    public static BUSStatus exceptiond() {
        return new BUSStatus(BUSResponeCode.exception, "Exception");
    }
    
    public static BUSStatus denied() {
        return new BUSStatus(BUSResponeCode.denied, "Access is denied");
    }
    
    public static BUSStatus busFailed() {
        return new BUSStatus(BUSResponeCode.busFailed, "Besiness failed");
    }
    public BUSStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
