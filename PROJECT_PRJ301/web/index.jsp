<%-- 
    Document   : index
    Created on : Oct 20, 2021, 2:18:14 PM
    Author     : TRONG TUAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
            <nav>
                <div class="main">
                    <div class="row">
                        <div class="col-sm-3">
                        <jsp:include page="Menu.jsp"></jsp:include></div>
                        <div class="col-sm-9" style="padding-top: 160px" >
                            <div class="row">
                            <%try {
                                    String action = request.getAttribute("action").toString();
                            %>
                            <%if (action.equals("index")) { %>
                            <jsp:include page="Content.jsp"></jsp:include>
                            <%}%>
                            <%if (action.equals("showCart")) { %>
                            <jsp:include page="showCart.jsp"></jsp:include>
                            <%}%>
                            <%if (action.equals("checkout")) { %>
                            <jsp:include page="checkout.jsp"></jsp:include>
                            <%}%>
                            <%if (action.equals("viewOrder")) { %>
                            <jsp:include page="ViewOrder.jsp"></jsp:include>
                            <%}%>
                            <%if (action.equals("viewAllOrder")) { %>
                            <jsp:include page="ViewAllOrderCus.jsp"></jsp:include>
                            <%}%>
                            <%if (action.equals("cusInfo")) { %>
                            <jsp:include page="CustomerInfor.jsp"></jsp:include>
                            <%}%>
                            <%} catch (NullPointerException e) {
                            }%>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>
