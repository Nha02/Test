<%-- 
    Document   : checkout
    Created on : Oct 11, 2021, 1:59:59 PM
    Author     : TRONG TUAN
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            Customer cus = (Customer) session.getAttribute("user");
        %>
        <div>
            <form style="margin-left: 120px" action="Product_session?action=checkout" method="Post">
                <h3>Check-out</h3>
                <table border="0"> 
                    <tr>
                        <td>Recipient's name</td>
                        <td><input type="text" name="name" value="<%=cus.getCname() %>" required="" /></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input pattern="[0-9]{10}" title="Số điện thoại có 10 chữ số" type="text" name="phone" value="<%=cus.getCphone() %>" required /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" value="<%=cus.getcAddress() %>" required /></td>
                    </tr>
                    <tr>
                        <td><input type="hidden"/></td>
                        <td>
                            <input type="submit" value="Check-out" name="submit"/>
                            <input type="reset" value="Reset" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
