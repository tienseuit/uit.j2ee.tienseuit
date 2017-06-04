/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.app;
import uit.j2ee.app.BUSResponeCode;
import uit.j2ee.app.BUSStatus;

/**
 *
 * @author LAP10599-local
 */
public class BUSRespone<T> {

    public int statusCode;
    public T data;
    public String message;
    public Exception exception;

    public BUSRespone(T data) {
        this(data, null, null, -1);
    }

    public BUSRespone(T data, BUSStatus status) {
        this(data, status, null, -1);
    }

    public BUSRespone(T data, BUSStatus status, Exception exception) {
        this(data, status, exception, -1);
    }

    public BUSRespone(T data, BUSStatus status, Exception exception, int dontLog) {
        if (status != null) {
            setErrorEX(
                    status.code,
                    status.message,
                    exception,
                    dontLog,
                    2
            );
        } else {
            this.statusCode = BUSResponeCode.ok;
            this.data = data;

//                if(dontLog == false || dontLog !== -1)
//                    logAM("BUSRespone log\n\t\tdata: " . am_json_encode(data));
        }
    }
  
    public <TC> BUSRespone<TC> convert(){ 
        return new BUSRespone<>(null, new BUSStatus(statusCode, message), exception, -1);
    }
    
    public BUSRespone setErrorEX(int statusCode, String message, Exception exception, int dontLog, int backCount) {
        this.statusCode = statusCode;
        this.message = message;
        this.exception = exception;

//            if(dontLog == 0)
//                logAM(
//                    "BUSRespone log: " + message
//                        + "\n\t\tdata: ". is_object(this.data) ? am_json_encode(this.data) : this.data
//                        + "\n\t\tstatusCode: ". statusCode
//                        + "\n\t\tmessage: ". message
//                        + "\n\t\texception: ". exception
//                        + "\n\t\t----",
//                    backCount
//                 );
        return this;
    }

    public BUSRespone setError(BUSStatus status, Exception exception, int dontLog) {
        return setErrorEX(
                status.code,
                status.message,
                exception,
                dontLog,
                2
        );
    }
}
