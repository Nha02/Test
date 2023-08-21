<%-- 
    Document   : ViewBill
    Created on : Oct 8, 2021, 1:56:46 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  try {
        ResultSet rs = (ResultSet) request.getAttribute("kqBill");
        String title = (String) request.getAttribute("titleBill");
        double totalAll = 0;
%>
<!--        <p><a href="ControllerBill?action=insert">Insert Bill</a></p>-->
<table border="1" style="width: 95%; margin-left: auto; margin-right: auto">
    <thead style="text-align: center">
        <tr><th colspan="10" style="background-color: #E8C900"><h5><%=title%></h5></th></tr>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Customer Name</th>
            <th>Total</th>
            <th>Status</th>
            <th>Detail</th>
            <th>Update Infor</th>
            <!--                    <th>Delete</th>-->
        </tr>
    </thead>
    <tbody>
        <%while (rs.next()) {%>
        <tr style="text-align: center">
            <%  String status = null;
                if (rs.getInt(7) == 1) {
                    status = "wait";
                }
                if (rs.getInt(7) == 2) {
                    status = "process ";
                }
                if (rs.getInt(7) == 3) {
                    status = "done";
                }
            %>
            <td><%=rs.getString(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getDouble(6)%> $</td>
            <% totalAll += rs.getDouble(6);%>
            <td><%=status%></td>
            <td><a style="font-weight: bold" href="ControllerBillDetail?action=detail&oID=<%=rs.getString(1)%>">[detail]</a></td>
            <td><a style="font-weight: bold" href="ControllerBill?action=update&oID=<%=rs.getString(1)%>">[update]</a></td>
<!--                    <td><a href="ControllerBill?action=delete&oID=<%=rs.getString(1)%>">delete</a></td>-->
        </tr>
        <%}
        %>
    </tbody>
    <tfoot>
        <tr>
            <th colspan="3" style="text-align: center">Total</th>
            <th style="text-align: center"><%=Math.round(totalAll * 100.0) / (100.0)%> $</th>
                <%} catch (NullPointerException ex) {

                }%>
        </tr>
    </tfoot>
</table>
