<%-- 
    Document   : adminIndex
    Created on : Oct 22, 2021, 9:13:28 AM
    Author     : TRONG TUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
            <div class="container" style="padding-top: 170px">
                <div class="row">
                    <div class="col-sm-3" >
                        <!--                        <div class="card bg-light font-weight-bold" style="position: fixed; width: 260px">
                                                    <a class="card-header text-dark text-uppercase" href="ControllerCustomer">Customer Manager</a>
                                                    <a class="card-header text-dark text-uppercase" href="ControllerProduct">Product Manager</a>
                                                    <a class="card-header text-dark text-uppercase" href="ControllerBill">Bill Manager</a>
                                                </div>-->
                    <%try {
                            String option = request.getAttribute("option").toString();
                    %>
                    <div class="card bg-light mb-3 font-weight-bold" style="position: fixed">
                        <%if (option.equals("viewPro") || option.equals("insertPro") || option.equals("updatePro")) { %>
                        <p class="card-header text-dark text-uppercase bg-warning">Search Product</p>
                        <jsp:include page="formSearchProduct.jsp"></jsp:include>
                            <a class="card-header text-dark text-uppercase bg-warning" href="ControllerProduct?action=insert">Insert Product</a>
                            <a class="card-header text-dark text-uppercase bg-warning" href="ControllerCategory">Category Manager</a>
                        <%}%>
                        <%if (option.equals("viewCate") || option.equals("insertCate") || option.equals("updateCate")) { %>
                        <p class="card-header text-dark text-uppercase bg-warning">Search Category</p>
                        <jsp:include page="formSearchCategory.jsp"></jsp:include>
                            <a class="card-header text-dark text-uppercase bg-warning" href="ControllerCategory?action=insert">Insert Category</a>
                        <%}%>
                        <%if (option.equals("viewCus") || option.equals("updateCus")) { %>
                        <p class="card-header text-dark text-uppercase bg-warning">Search Customer</p>
                        <jsp:include page="formSearchCustomer.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewBill") || option.equals("updateBill") || option.equals("detailBill") || option.equals("viewTopOrders") || option.equals("billDate")) { %>
                        <p class="card-header text-dark text-uppercase bg-warning">Search Bill</p>
                        <jsp:include page="formSearchBill.jsp"></jsp:include>
                        <p class="card-header text-dark text-uppercase bg-warning">TOP ORDERS</p>
                        <jsp:include page="formTopOrder.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewAd") || option.equals("updateAd") || option.equals("insertAd")) { %>
                        <a class="card-header text-dark text-uppercase bg-warning" href="ControllerAdmin?action=listAll">Admin List</a>
                        <a class="card-header text-dark text-uppercase bg-warning" href="ControllerAdmin?action=insert">Insert Admin</a>
                        <%}%>
                    </div>
                </div>
                <div class="col-sm-9" >
                    <div class="row">
                        <%if (option.equals("viewCus")) { %>
                        <jsp:include page="ViewCustomer.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("updateCus")) { %>
                        <jsp:include page="UpdateCustomer.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewPro")) { %>
                        <jsp:include page="ViewProduct.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("updatePro")) { %>
                        <jsp:include page="updateProduct.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("insertPro")) { %>
                        <jsp:include page="insertProduct.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewCate")) { %>
                        <jsp:include page="ViewCategory.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("updateCate")) { %>
                        <jsp:include page="UpdateCategory.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("insertCate")) { %>
                        <jsp:include page="InsertCategory.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewBill")) { %>
                        <jsp:include page="ViewBill.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("updateBill")) { %>
                        <jsp:include page="UpdateBill.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("detailBill")) { %>
                        <jsp:include page="ViewDetail.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewAd")) { %>
                        <jsp:include page="ViewAdmin.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("updateAd")) { %>
                        <jsp:include page="UpdateAdmin.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("insertAd")) { %>
                        <jsp:include page="InsertAdmin.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("viewTopOrders")) { %>
                        <jsp:include page="TopOrder.jsp"></jsp:include>
                        <%}%>
                        <%if (option.equals("billDate")) { %>
                        <jsp:include page="ViewBill.jsp"></jsp:include>
                        <%}%>

                        <%} catch (NullPointerException e) {
                            }%>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
