<%-- 
    Document   : ViewAllOrderCus
    Created on : Oct 31, 2021, 5:27:01 PM
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
        <%
            ResultSet rs = (ResultSet) request.getAttribute("kqBill");
            String title = (String) request.getAttribute("titleBill");
            int count = 0;
            double totalAll = 0;
        %>
        <table border="1" style="width: 95%; margin-left: auto; margin-right: auto">
            <thead style="text-align: center">
                <tr><th colspan="10" ><h5><%=title%></h5></th></tr>
                <tr>
                    <th>No.</th>
                    <th>Date</th>
                    <th>Recipient's name</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>Detail</th>
                    <th>Cancel Order</th>

                </tr>
            </thead>
            <tbody>
                <%
                    while (rs.next()) {
                        count++;
                %>
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
                    <td><%=count%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getDouble(6)%></td>
                    <% totalAll += rs.getDouble(6);%>
                    <td><%=status%></td>
                    <td><a style="font-weight: bold" href="Product_session?action=showBill&oID=<%=rs.getString(1)%>">[detail]</a></td>
                    <% if (rs.getInt(7) == 1) {%>
                    <td><a style="font-weight: bold" href="ControllerBill?action=delete&oID=<%=rs.getString(1)%>">[cancel]</a></td>
                    <% } %>
                </tr>
                <%}%>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="3" style="text-align: center">Total</th>
                    <th style="text-align: center"><%=Math.round(totalAll * 100.0) / (100.0)%></th>
                </tr>
            </tfoot>
        </table>
        
        <div style="margin-top: 2%; margin-left: 2.5%; text-align: center; width: 8%; height:28px; background:orange;border-radius:5px; float: left; border: #343a40 solid thin">
            <a style="color: black; font-weight: bold" href="HomeController">< Home</a>
        </div>
    </body>
</html>
