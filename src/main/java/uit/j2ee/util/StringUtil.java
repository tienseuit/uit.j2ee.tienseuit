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
public class StringUtil { 
    public static boolean isNullOrSpaces(String str) {
        if( str == null)
            return true;
        
        int n = str.length();
        for(int i = 0; i < n; i++){
            if(str.charAt(i) == ' ') {
            } else {
                return false;
            }
        }
        
        return true;
    }
}
