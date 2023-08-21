<%-- 
    Document   : formTopOrder
    Created on : Nov 4, 2021, 4:21:04 PM
    Author     : TRONG TUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String dateFromValue = (String) request.getAttribute("dateFromValue");
            String dateToValue = (String) request.getAttribute("dateToValue");
        %>
        <form action="ControllerBill?action=topOrders" method="POST" style="width: 257px">
            <table border="0" style="text-align: left">
                <tr>
                    <th>Date:  </th>
                    <td><input type="date" name="dateFrom" value="<%=(dateFromValue == null ? "" : dateFromValue)%>" placeholder="From..." style="width: 90%; text-align: center"/> - </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input type="date" name="dateTo" value="<%=(dateToValue == null ? "" : dateToValue)%>" placeholder="To..." style="width: 90%; text-align: center"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" name="submit" value="Search Top Orders" style="width: 80%; text-align: center"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
