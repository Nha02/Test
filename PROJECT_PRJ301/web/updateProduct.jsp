<%-- 
    Document   : updteProduct
    Created on : Oct 6, 2021, 1:31:48 PM
    Author     : DELL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% ResultSet rs = (ResultSet) request.getAttribute("rs");
    ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
%>
<form action="ControllerProduct?action=update" method="Post" style="margin-left: auto; margin-right: auto">
    <table border="0"> 
        <tr>
            <td colspan="2"><h4 style="text-align: center">Update Product</h4></td>
        </tr>
        <% if (rs.next()) { %>
        <tr>
            <td>Category: </td>
            <td>
                <select name="cate" size="1">
                    <%while (rsCate.next()) {%>
                    <option value="<%=rsCate.getInt(1)%>" <%=(rsCate.getInt(1) == rs.getInt(8) ? "selected" : "")%> ><%=rsCate.getString(2)%></option>
                    <%}%>
                </select>
            </td>
        </tr>   
        <tr>
            <td>Product ID: </td>
            <td style="font-weight: bold; color: red"><%=rs.getString(1)%></td>
        </tr>
        <tr hidden="">
            <td>Product ID: </td>
            <td><input type="text" name="id" value="<%=rs.getString(1)%>"/></td>
        </tr>
        <tr>
            <td>Product Name: </td>
            <td><input type="text" name="pname" value="<%=rs.getString(2)%>" required /></td>
        </tr>
        <tr>
            <td>Quantity: </td>
            <td><input type="text" name="quantity" value="<%=rs.getInt(3)%>" required /></td>
        </tr>
        <tr>
            <td>Price: </td>
            <td><input type="text" name="price" value="<%=rs.getDouble(4)%>" required /></td>
        </tr>
        <tr>
            <td>Image Name: </td>
            <td><input type="text" name="image" value="<%=rs.getString(5)%>" required /></td>
        </tr>
        <tr>
            <td>Description: </td>
            <td><input type="text" name="des" value="<%=rs.getString(6)%>" required /></td>
        </tr>
        <tr>
            <td>Status: </td>
            <td><input type="radio" name="status" value="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%> />Enable
                <input type="radio" name="status" value="0" <%=(rs.getInt(7) == 0 ? "checked" : "")%>/>Disable
            </td>
        </tr>
        <%}%>

        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update" name="submit"/>
                <input type="reset" value="Reset" />
            </td>
        </tr>
    </table>
</form>
