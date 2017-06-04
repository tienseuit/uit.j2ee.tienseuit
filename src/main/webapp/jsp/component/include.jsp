<%-- 
    Document   : newjsp
    Created on : May 19, 2017, 9:19:15 PM
    Author     : LAP10599-local
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<% String contextPath = request.getContextPath() + "/";
request.setAttribute("contextPath", contextPath);
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<ct:inclue src="css/lib/bootstrap.min.css"/>
<ct:inclue src="css/style.css"/>
<ct:inclue src="js/lib/jquery-3.1.1.js"/>
<ct:inclue src="js/lib/bootstrap.min.js"/>
<ct:inclue src="js/lib/knockout-3.4.1.js"/>
<ct:inclue src="js/i18n/vi.js"/>
<ct:inclue src="js/app/common.js"/> 
<ct:inclue src="js/app/app.js"/> 
<ct:inclue src="js/app/util.js"/> 
<script>
    var SYSTEM = window.SYSTEM || {};
    APP.contextPath = "${contextPath}";
    APP.currentShopID = null;
    APP.currentShopName = null;
</script>
<ct:inclue src="js/lib/ko-custom.js"/> 
<ct:inclue src="js/lib/base-popup.js"/>
<ct:inclue src="js/lib/bus-ajax.js"/>

