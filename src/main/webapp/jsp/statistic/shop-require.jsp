<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="/WEB-INF/custom-tag.tld"%>
<html>
    <head>
        <jsp:include page="../component/include.jsp"/>
    </head>
    <body>
        <jsp:include page="../component/page-head.jsp"/>
        <jsp:include page="../component/nav.jsp"/>
        <div class="container" id="ko-binding">
         
            <br>
            <br>
            <h1 style="text-align: center; cursor: pointer">
                <a>${msg}</a>
            </h1>
            <!-- /ko --> 
        </div>
 
    </body>
</html>
