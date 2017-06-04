package uit.j2ee.core;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class TLDBaseSimple extends SimpleTagSupport implements DynamicAttributes {
    
    protected HashMap<String, Object> _Attributes = new HashMap<String, Object>();

    @Override
    public void setDynamicAttribute(String arg0, String name, Object value) throws JspException {
        _Attributes.put(name, value);
    }

    public void doAttributes(JspWriter wt) throws IOException {
        for (Map.Entry<String, Object> item : _Attributes.entrySet()) {
            wt.print(" ");
            wt.print(item.getKey());
            wt.print("=\"");
            wt.print(item.getValue());
            wt.print("\"");
        }
    }
     
    
}
