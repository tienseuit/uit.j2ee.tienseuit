package uit.j2ee.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
public class Json {
    static Gson g = new GsonBuilder().serializeNulls().create();
    public static String toJson(Object obj) {  
        String re = g.toJson(obj);
        return re;
    }
    
    public static <T> T toObject(String jsonData, Class<T> t) {
        return g.fromJson(jsonData, t);   
    }
     public static <T> T toObject(String jsonData, Type t) {
        return g.fromJson(jsonData, t);   
    }
     
    public static void main(String[] args) {
        
    }
}
