<%-- 
    Document   : ViewProduct
    Created on : Oct 5, 2021, 3:05:21 PM
    Author     : DELL
--%>

<%@page import="model.DAOProduct"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("kq");
    String title = (String) request.getAttribute("title");
%>

<table border="1" style="width: 95%; margin-left: auto; margin-right: auto; text-align: center">
    <thead style="text-align: center">
        <tr><th colspan="10" ><h5><%=title%></h5></th></tr>
        <tr>
            <th>PID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Image</th>
            <th>Description</th>
            <th>Status</th>
            <th>CateID</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <%while (rs.next()) {%>
        <tr style="text-align: center">
            <td><%=rs.getString(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><img src="<%=rs.getString(5)%>" width="100px" height="150px"></td>
            <td><%=rs.getString(6)%></td>
            <td><%=(rs.getInt(7) == 1 ? "Active" : "Deactive")%></td>
            <td><%=rs.getString(8)%></td>
            <td><a style="font-weight: bold" href="ControllerProduct?action=update&id=<%=rs.getString(1)%>">[update]</a></td>
            <td><a style="font-weight: bold" href="ControllerProduct?action=delete&id=<%=rs.getString(1)%>">[delete]</a></td>
        </tr>
        <%}%>
    </tbody>
    <!--    <tfoot>        
            <tr>
                <th colspan="5" style="text-align: center"><h5><a href="ControllerProduct?action=insert">Insert Product</a></h5></th>
                <th colspan="5" style="text-align: center"><h5><a href="ControllerCategory">Category Manager</a></h5></th>
            </tr>
        </tfoot>-->
</table>