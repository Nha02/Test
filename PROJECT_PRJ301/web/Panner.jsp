<%-- 
    Document   : Content
    Created on : Oct 20, 2021, 3:02:15 PM
    Author     : TRONG TUAN
--%>

<%@page import="model.DAOBill"%>
<%@page import="entity.Product"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<link href="css/style.css" rel="stylesheet" type="text/css"/>


<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand text-dark text-center font-weight-bold" href="HomeController">Nguyễn Trọng Tuấn</br>HE151272</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mt-3 mb-3 ml-auto">
                <c:if test="${sessionScope.accAd == null}">
                    <li class="nav-item">
                        <form action="ControllerProduct?action=searchU" method="POST" class="form-inline mr-5 ml-3 mb-0">
                            <input class="form-control mr-1 p-2" style="width: 280px" type="search" name="search" value="${valueSearch}" placeholder="Search product . . ." aria-label="Search">
                            <button class="btn btn-warning my-3 my-sm-0 px-2 p-2" style="font-weight: bold" type="submit">Search</button>
                        </form>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null && sessionScope.accAd != null}">
                    <%  DAOBill daoB = new DAOBill();
                        int n = daoB.doneBill();%>
                    <li class="nav-item">
                        <a class="btn btn-warning ml-3 p-2 font-weight-bold" href="ControllerBill?action=done_Bill">Bill Done: <%=n %></a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-warning ml-3 p-2 font-weight-bold">Welcome Admin: ${sessionScope.accAd}</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-warning ml-3 p-2 font-weight-bold" href="ControllerAdmin?action=logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null && sessionScope.accAd == null}">
                    <li class="nav-item dropdown">
                        <a class="dropdown-toggle btn btn-warning ml-3 p-2 font-weight-bold" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Welcome ${sessionScope.user.cname}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" >
                            <a class="dropdown-item" href="ControllerCustomer?action=infoCus&cid=${sessionScope.user.cid}">Information</a>
                            <a class="dropdown-item" href="ControllerCustomer?action=orderCus&cid=${sessionScope.user.cid}">Order History</a>
                            <a class="dropdown-item" href="Login?action=logout">Logout</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null && sessionScope.accAd == null}">
                    <li class="nav-item">
                        <a class="btn btn-warning ml-3 p-2 font-weight-bold" href="login.jsp">Login / Register</a>
                    </li>
                </c:if>
            </ul>
            <c:if test="${sessionScope.accAd == null}">
                <a class="btn btn-warning ml-3 p-2 font-weight-bold" href="Product_session?action=showCart">Show Cart
                    <%
                        int count = 0;
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String id = em.nextElement().toString();
                            if (!id.equals("user") && !id.equals("accAd")) {
                                Product pro = (Product) session.getAttribute(id);
                                int quan = pro.getQuantity();
                                count += quan;
                            }
                        }
                    %>
                    <span>[<%=count%>]</span>
                </a>
            </c:if>
        </div>
    </div>
</nav>
<div class="space">
    <c:if test="${sessionScope.user == null && sessionScope.accAd != null}">
        <%if (session.getAttribute("accAd").equals("general_admin")) { %>
        <div class="row " style="margin-left: 3%; margin-top: 27px">
            <div class="col-sm-3" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerCustomer">Customer Manager</a></div>
            <div class="col-sm-3" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerProduct">Product Manager</a></div>
            <div class="col-sm-3" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerBill">Bill Manager</a>
            </div>
            <div class="col-sm-3" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerAdmin?action=listAll">Admin Manager</a></div>
        </div>
        <%} else {%>
        <div class="row " style="margin-left: 10%; margin-top: 27px">
            <div class="col-sm-4" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerCustomer">Customer Manager</a></div>
            <div class="col-sm-4" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerProduct">Product Manager</a></div>
            <div class="col-sm-4" style="width: 25%">
                <a class="card-header text-dark text-uppercase bg-warning font-weight-bold" style="text-align: center" href="ControllerBill">Bill Manager</a>
            </div>
        </div>
        <%}%>
    </c:if>
</div>
<div class="spacewhite"></div>

<!--<section class="jumbotron text-center">
</section>-->

