<%@page import="uit.j2ee.model.NavInfo"%>
<%@page import="uit.j2ee.app.BUSResponeCode"%>
<%@page import="uit.j2ee.app.BUSRespone"%>
<%@page import="java.util.List"%>
<%@page import="uit.j2ee.been.Shop"%> 
<%@page import="uit.j2ee.util.BUSReflection"%>
<%@page import="uit.j2ee.model.ShopBUS"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 

<%
    NavInfo navInfo = new NavInfo();
    if (navInfo.build(request, response).statusCode != BUSResponeCode.ok) {
        return;
    }

%> 
<div class="row path"> 
    <script>
        function onChangeShop(shopID) {
            Util.setURLVales("shopID", shopID);
            location.reload();
        }
        APP.currentShopID = <%=navInfo.selectedShopStringID%>;
        APP.currentShopName = "<%=navInfo.selectedNavShop.getName()%>";
    </script>
    <div class="col-sm-1"></div>
    <ul class="col-sm-10">
        <li class="dropdown">
            <a a id="shop-item-<%=navInfo.selectedNavShop.getId()%>" href="/business/product?shopID=<%=navInfo.selectedNavShop.getId()%>">
                <strong>Cửa hàng: </strong><%=navInfo.selectedNavShop.getName()%>
            </a>
            <ul class="dropdown-menu" id="nav-shop-list">
                <c:forEach var="item" items="<%=navInfo.listNavShop%>">
                    <li><a id="shop-item-${item.getId()}" href="${item.getLink()}">${item.getName()}</a></li>
                    </c:forEach>
            </ul>
        <li class="dropdown">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <a><%=navInfo.selectedNavFeature.getName()%></a>
            <ul class="dropdown-menu">
                <c:forEach var="item" items="<%=navInfo.listNavFeature%>">
                    <li><a href="${item.getLink()}">${item.getName()}</a></li>
                    </c:forEach> 
            </ul>
        </li>
    </ul> 
</div>
