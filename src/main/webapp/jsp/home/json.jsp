<%@page import="uit.j2ee.util.Json"%>
<% 
    Object data = request.getAttribute("data"); 
     if(data != null)
        response.getWriter().write(Json.toJson(data));
%>