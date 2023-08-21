<%-- 
    Document   : add2Cart
    Created on : Oct 11, 2021, 2:18:16 PM
    Author     : TRONG TUAN
--%>

<%@page import="model.DAOProduct"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
//            String id = (String)request.getAttribute("id");
//            ResultSet rs = (ResultSet)request.getAttribute("rs");
            
        %>
        <h1>Item with pid=<%=id%> was added to the Shopping Cart</h1>
    </body>
</html>
