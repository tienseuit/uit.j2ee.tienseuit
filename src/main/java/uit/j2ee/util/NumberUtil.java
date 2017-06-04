/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.util;

/**
 *
 * @author LAP10599-local
 */
public class NumberUtil {
    
    
 public static Integer parseInt(String strValue, Integer defaultValue) {
        try {
            return Integer.valueOf(strValue);
        } catch (Exception ex) {
            return defaultValue;
        }
    }
 public static Integer parseInt(Object value, Integer defaultValue) {
        try { 
            if(value instanceof Integer)
                return value == null ? defaultValue : (Integer)value;
            else if(value instanceof String)
                return Integer.valueOf((String)value);
            else 
                return Integer.valueOf(value.toString());
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static Long parseLong(String strValue, Long defaultValue) {
        try {
            return Long.valueOf(strValue);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

}
