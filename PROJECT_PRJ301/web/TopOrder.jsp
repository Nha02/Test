<%-- 
    Document   : TopOrder
    Created on : Nov 4, 2021, 4:21:28 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String dateFromValue = (String) request.getAttribute("dateFromValue");
    String dateToValue = (String) request.getAttribute("dateToValue");
    ResultSet rs = (ResultSet) request.getAttribute("kqTop");
    String title = (String) request.getAttribute("titleTop");
    int count = 1;
%>
<table border="1" style="width: 95%; margin-left: auto; margin-right: auto">
    <thead style="text-align: center">
        <tr><th colspan="10" style="background-color: #E8C900"><h5><%=title%></h5></th></tr>
        <tr>
            <th>No.</th>
            <th>ID</th>
            <th>Username</th>
            <th>Customer Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Number Of Orders</th>
            <th>Total</th>
            <th>Detail</th>
        </tr>
    </thead>
    <tbody>
        <% try {
                while (rs.next()) {%>
        <tr style="text-align: center">
            <td style="font-weight: bold"><%=count++%></td>
            <td><%=rs.getInt(1)%></td>
            <td><%=rs.getString(5)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getInt(6)%></td>
            <td style="font-weight: bold"><%=rs.getDouble(7)%> $</td>
            <td><a href="ControllerBill?action=billDate&cid=<%=rs.getInt(1)%>&datef=<%=dateFromValue%>&datet=<%=dateToValue%>" style="font-<% %>weight: bold">[detail]</a></td>
        </tr>
        <%}
            } catch (SQLException e) {
            }
        %>
    </tbody>
</table>
