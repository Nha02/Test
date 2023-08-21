<%-- 
    Document   : UpdateBill
    Created on : Oct 8, 2021, 1:57:04 PM
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
        <% ResultSet rs = (ResultSet) request.getAttribute("rs");
        %>
        <form action="ControllerBill?action=update" method="Post" style="margin-left: auto; margin-right: auto">
            <table border="0"> 
                <tr>
                    <td colspan="2"><h4 style="text-align: center">Update Bill Information</h4></td>
                </tr>
                <% if (rs.next()) {%>
                <tr>
                    <td>Bill ID</td>
                    <td><input type="text" name="oID" value="<%=rs.getString(1)%>" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td><input type="text" name="dateCreate" value="<%=rs.getString(2)%>" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>Customer ID</td>
                    <td><input type="text" name="cid" value="<%=rs.getInt(8)%>" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>Customer Name</td>
                    <td><input type="text" name="cname" value="<%=rs.getString(3)%>" /></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="cphone" value="<%=rs.getString(4)%>" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="cAddress" value="<%=rs.getString(5)%>" /></td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td><input type="text" name="total" value="<%=rs.getDouble(6)%>" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td hidden="hidden"><input type="radio" name="status" value="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%> />Enable
                        <input type="radio" name="status" value="0" <%=(rs.getInt(7) == 0 ? "checked" : "")%>/>Disable
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Update" name="submit"/>
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
