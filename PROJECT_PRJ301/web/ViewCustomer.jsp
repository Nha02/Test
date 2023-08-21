<%-- 
    Document   : ViewCustomer
    Created on : Oct 8, 2021, 1:29:12 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("kqCus");
    String title = (String) request.getAttribute("titleCus");
%>
<div style="width: 100%">
    <div style="width: 7%; height:20px; margin-right: 3%; background:orange; text-align: center; border-radius:5px; float: right; border: #343a40 solid thin">
        <a style="color: black; font-size: 80%; font-weight: bold" href="ControllerCustomer?action=sortOrder">SORT</a>
    </div>
    </br>
</div>
<table border="1" style="width: 95%; margin-left: auto; margin-right: auto; text-align: center">
    <thead>
        <tr><th colspan="9" ><%=title%></th></tr>
        <tr>
            <th>CID</th>
            <th>Customer Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Username</th>
            <th>Total Spending</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <%while (rs.next()) {%>
        <tr>
            <td style="text-align: center"><%=rs.getInt(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getString(5)%></td>
            <td style="font-weight: bold"><a href="ControllerCustomer?action=listBill&cid=<%=rs.getInt(1)%>"><%=rs.getDouble(9)%> $</a></td>
            <td><%=(rs.getInt(7) == 1 ? "Active" : "Deactive")%></td>
            <td><a style="font-weight: bold" href="ControllerCustomer?action=update&cid=<%=rs.getInt(1)%>">[update]</a></td>
            <td><a style="font-weight: bold" href="ControllerCustomer?action=delete&cid=<%=rs.getInt(1)%>">[delete]</a></td>
        </tr>
        <%}%>
    </tbody>
</table>



