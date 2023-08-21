<%-- 
    Document   : formSearchBill
    Created on : Oct 29, 2021, 2:16:20 PM
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
            String idBillValue = (String) request.getAttribute("idBillValue");
            String dateFromValue = (String) request.getAttribute("dateFromValue");
            String nameBillValue = (String) request.getAttribute("nameBillValue");
            String dateToValue = (String) request.getAttribute("dateToValue");
            String totalFromValue = (String) request.getAttribute("totalFromValue");
            String totalToValue = (String) request.getAttribute("totalToValue");
            String statusBillValue = (String) request.getAttribute("statusBillValue");

        %>
        <form action="ControllerBill?action=listAll" method="POST" style="width: 257px">
            <table border="0" style="text-align: left">
                <tr>
                    <th>Bill ID: </th>
                    <td><input type="text" name="idBill" value="<%=(idBillValue == null ? "" : idBillValue)%>" style="width: 85%"/> </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td><input type="date" name="dateFrom" value="<%=(dateFromValue == null ? "" : dateFromValue)%>" placeholder="From..." style="width: 85%; text-align: center"/> - </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input type="date" name="dateTo" value="<%=(dateToValue == null ? "" : dateToValue)%>" placeholder="To..." style="width: 85%; text-align: center"/></td>
                </tr>
                <tr>
                    <th>Name: </th>
                    <td><input type="text" name="nameBill" value="<%=(nameBillValue == null ? "" : nameBillValue)%>" style="width: 85%"/> </td>
                </tr>
                <tr>
                    <th>Total: </th>
                    <td><input type="text" pattern="[0-9.]{0,1000}" title="Enter number" name="totalFrom" value="<%=(totalFromValue == null ? "" : totalFromValue)%>" placeholder="From..." style="width: 39%; text-align: center"/>
                        - <input type="text" pattern="[0-9.]{0,1000}" title="Enter number" name="totalTo" value="<%=(totalToValue == null ? "" : totalToValue)%>" placeholder="To..." style="width: 38%; text-align: center"/> $</td>
                </tr>
                <tr>
                    <th>Status: </th>    
                    <td><select name="statusBill" size="1" style="width: 85%">
                            <%
                                try {
                                    int sta = Integer.parseInt(statusBillValue);
                            %>
                            <option value="0" <%=(sta == 0 ? "selected" : "")%> >-</option>
                            <option value="1" <%=(sta == 1 ? "selected" : "")%>  >wait</option>
                            <option value="2" <%=(sta == 2 ? "selected" : "")%> >process</option>
                            <option value="3" <%=(sta == 3 ? "selected" : "")%> >done</option>
                            <%
                            } catch (NumberFormatException e) { %>
                            <option value="0" selected >-</option>
                            <option value="1" >wait</option>
                            <option value="2" >process</option>
                            <option value="3" >done</option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" name="submit" value="Search" style="width: 70px; text-align: center"/>
                        <input type="submit" name="reset" value="Reset" style="width: 60px; text-align: center"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
