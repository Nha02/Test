<%-- 
    Document   : productList
    Created on : Oct 11, 2021, 2:02:43 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("kq");
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            String title = (String) request.getAttribute("title");
        %>
       
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Category</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>               
                    <th>Buy</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getInt(8)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><img src="<%=rs.getString(5)%>" width="100px" height="150px"></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Active" : "Deactive")%></td>
                    <td><a href="Product_session?action=addToCart&pid=<%=rs.getString(1)%>">Add to cart</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
