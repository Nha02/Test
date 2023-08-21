<%-- 
    Document   : UpdateCategory
    Created on : Oct 8, 2021, 12:51:33 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ResultSet rs = (ResultSet) request.getAttribute("rs");
%>
<form action="ControllerCategory?action=update" method="Post" style="margin-left: 20%">
    <table border="0"> 
        <tr>
            <td colspan="2"><h4 style="text-align: center">Update Category</h4></td>
        </tr>
        <% if (rs.next()) {%>
        <tr>
            <td>ID</td>
            <td><input type="text" name="cateID" value="<%=rs.getInt(1)%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>Category Name  </td>
            <td><input type="text" name="cateName" value="<%=rs.getString(2)%>" required /></td>
        </tr>
        <tr>
            <td>Status</td>
            <td><input type="radio" name="status" value="1" <%=(rs.getInt(3) == 1 ? "checked" : "")%> />Enable
                <input type="radio" name="status" value="0" <%=(rs.getInt(3) == 0 ? "checked" : "")%>/>Disable
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
