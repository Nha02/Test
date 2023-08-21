<%-- 
    Document   : Menu
    Created on : Oct 20, 2021, 3:02:30 PM
    Author     : TRONG TUAN
--%>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3" style="position: fixed; padding-top: 160px; z-index: 10000">
    <div class="card bg-light mb-3">
        <a class="card-header bg-warning text-dark font-weight-bold" href="HomeController">ALL CATEGORIES</a>
        <ul class="list-group category_hover">
            <c:forEach items="${listC}" var="i">
                <li class="list-group-item ${flag == i.cateID ? "flag" : "" }"><a href="ControllerCategory?action=getProduct&cateID=${i.cateID}">${i.cateName}</a></li>
                </c:forEach>
        </ul>
    </div>
    <%try {
            String action = request.getAttribute("action").toString();
    %>
    <%
            if (action.equals("index")) { %>
    <div class="card bg-light " style="z-index: 1000; border: 1px solid gainsboro">
        <jsp:include page="searchInClient.jsp"></jsp:include>
        </div>
    <%}%>
    <%} catch (NullPointerException e) {
            }%>
</div>
