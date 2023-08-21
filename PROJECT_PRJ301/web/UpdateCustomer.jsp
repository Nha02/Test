<%-- 
    Document   : UpdateCustomer
    Created on : Oct 8, 2021, 1:35:23 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ResultSet rs = (ResultSet) request.getAttribute("rs");
%>
<form action="ControllerCustomer?action=update" method="Post" style="margin-left: auto; margin-right: auto">
    <table border="0"> 
        <% if (rs.next()) {%>
        <tr>
            <td colspan="2"><h4 style="text-align: center">Update Customer</h4></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>CID</td>
            <td style="font-weight: bold"><%=rs.getInt(1)%></td>
        </tr>
        <tr hidden="">
            <td>CID</td>
            <td><input type="text" name="cid" value="<%=rs.getInt(1)%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>Customer Name</td>
            <td><input type="text" name="cname" value="<%=rs.getString(2)%>" required /></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="cphone" value="<%=rs.getString(3)%>" required /></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="cAddress" value="<%=rs.getString(4)%>" required /></td>
        </tr>
        <tr hidden>
            <td>Username</td>
            <td><input type="text" name="username" value="<%=rs.getString(5)%>" readonly="readonly" /></td>
        </tr>
        <tr hidden>
            <td>Password</td>
            <td><input type="password" name="password" value="<%=rs.getString(6)%>" readonly="readonly"/></td> 
        </tr>
        <tr>
            <td>Status</td>
            <td><input type="radio" name="status" value="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%> />Enable
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