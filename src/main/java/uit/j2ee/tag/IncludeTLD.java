package uit.j2ee.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import uit.j2ee.core.TLDBaseSimple;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IncludeTLD extends SimpleTagSupport implements DynamicAttributes  {

    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            String contextPath = request.getContextPath();
            JspWriter wt = pageContext.getOut();

            String type = (String) _Attributes.get("type");
            String src = (String) _Attributes.get("src");

            if (type == null) {
                if (src.startsWith("js/")) {
                    src = contextPath + "/" + src;
                    type = "js";
                } else if (src.startsWith("css/")) {
                    src = contextPath + "/" + src;
                    type = "css";
                }
            }
            
            if("js".equals(type)) {

                wt.print("<script src=\"");
                wt.print(src);
                wt.print("\"></script>");
            } else if ("css".equals(type)) {

                wt.print("<link href=\"");
                wt.print(src);
                wt.print("\" rel=\"stylesheet\"></script>");
            } else {
                wt.print("<script>console.warn(\"Failed to parse include tag\")</script>");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
