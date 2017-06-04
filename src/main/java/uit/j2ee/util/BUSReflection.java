/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.DefaultParameterNameDiscoverer; 

/**
 *
 * @author LAP10599-local
 */
public class BUSReflection {

    protected Class<?> _BUSClass;
    protected Method _Method;
    protected Field _RequestField;
    protected Field _ResponseField;
    protected String[] _ParamValues;
    protected String _BasePacket;
    protected Object _Instance;

    public BUSReflection() {
        this(null);
    }

    public BUSReflection(String basePacket) {
        _BasePacket = basePacket == null ? "" : basePacket + ".";
    }

    public boolean setDataFromRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String className = request.getParameter("c");
        String methodName = request.getParameter("m");

        className = getFullClassName(className); 
        if (null == (getClass(className)) // get class
                || null == (getMethod(methodName)) //get method
                || null == (getInvokeParam(request))) // get params value
        {
            return false;
        }

        _Instance = _BUSClass.newInstance();

        _RequestField = getField(_BUSClass, "_Request");
        if (_RequestField == null) {
            throw new Exception("Missing _Request field");
        }
        _ResponseField = getField(_BUSClass, "_Response");
        if (_ResponseField == null) {
            throw new Exception("Missing _Response field");
        }

        _RequestField.setAccessible(true);
        _RequestField.set(_Instance, request);

        _ResponseField.setAccessible(true);
        _ResponseField.set(_Instance, response);
        return true;
    }

     public boolean setDataFromRequest(HttpServletRequest request, HttpServletResponse response, Object instance) throws Exception {
         
         Class c = instance.getClass();
        _RequestField = getField(c, "_Request");
        if (_RequestField == null) {
            throw new Exception("Missing _Request field");
        }
        _ResponseField = getField(c, "_Response");
        if (_ResponseField == null) {
            throw new Exception("Missing _Response field");
        }

        _RequestField.setAccessible(true);
        _RequestField.set(instance, request);

        _ResponseField.setAccessible(true);
        _ResponseField.set(instance, response);
        return true;
    }

     public<T> T createBUS(HttpServletRequest request, HttpServletResponse response, Class<T> c)throws Exception {
         T re = c.newInstance();
         setDataFromRequest(request, response, re); 
         return re;
     }
     
    public Object involke() throws Exception {
        Object re = _Method.invoke(_Instance, _ParamValues);
        return re;
    }

    private Field getField(Class c, String fieldName) {
        try {
            return c.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = c.getSuperclass();
            if (superClass == null) {
                return null;
            } else {
                return getField(c.getSuperclass(), fieldName);
            }
        }
    }

    private String[] getInvokeParam(HttpServletRequest request) {
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(_Method);
        //Parameter[] params = method.getParameters();
        int n = params.length;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            String paramName = params[i];
            String parameterValue = request.getParameter(paramName);
            result[i] = parameterValue;
        }
        return _ParamValues = result;
    }

    public Method getMethod(String methodName) {
        if (_BUSClass == null) {
            return _Method = null;
        }

        Method[] allMethods = _BUSClass.getMethods();
        for (Method m : allMethods) {
            if (m.getName().equals(methodName)) {
                return _Method = m;
            }
        }
        return _Method = null;
    }

    public Class<?> getClass(String className) {
        try {
            return _BUSClass = Class.forName(className);
        } catch (Exception ex) {
            return _BUSClass = null;
        }
    }

    protected String getFullClassName(String className) {
        if (className.indexOf('.') == -1) {
            className = _BasePacket + className;
        }

        return className;
    }
}
