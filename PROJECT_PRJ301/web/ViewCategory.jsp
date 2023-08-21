<%-- 
    Document   : ViewCategory
    Created on : Oct 8, 2021, 12:51:13 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("kqCate");
    String title = (String) request.getAttribute("title");
%>
<table border="1" style="width: 95%; margin-left: auto; margin-right: auto">
    <thead style="text-align: center">
        <tr><th colspan="10" ><h5><%=title%></h5></th></tr>
        <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <%while (rs.next()) {%>
        <tr style="text-align: center">
            <td><%=rs.getInt(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=(rs.getInt(3) == 1 ? "Active" : "Deactive")%></td>
            <td><a style="font-weight: bold" a href="ControllerCategory?action=update&cateID=<%=rs.getInt(1)%>">[update]</a></td>
            <td><a style="font-weight: bold" href="ControllerCategory?action=delete&cateID=<%=rs.getInt(1)%>">[delete]</a></td>
        </tr>
        <%}%>
    </tbody>
<!--    <tfoot>        
        <tr>
            <th colspan="10" style="text-align: center"><h6><a href="ControllerCategory?action=insert">Insert Category</a></h6></th>
        </tr>
    </tfoot>-->
</table>
