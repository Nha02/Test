<%-- 
    Document   : Content
    Created on : Oct 20, 2021, 3:02:15 PM
    Author     : TRONG TUAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach items="${listP}" var="i">
    <div class="col-12 col-md-6 col-lg-3" style="margin-bottom: 20px">
        <div class="card">
            <img class="card-img-top" style="max-width: 200px" src="${i.image}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${i.pname}</h5>
                <p class="card-text show_txt">${i.description}</p>
                <div class="row">
                    <div class="col-md-7">
                        <p class="btn btn-outline-dark font-weight-bold" >${i.price} $</p>
                    </div>
                    <div class="col-md-5">
                        <a href="Product_session?action=addToCart&pid=${i.pid}" class="btn btn-warning"><i class="fa fa-shopping-cart"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
