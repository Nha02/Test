<%-- 
    Document   : formSearchCustomer
    Created on : Oct 29, 2021, 1:12:48 PM
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
            String idCusValue = (String) request.getAttribute("idCusValue");
            String nameCusValue = (String) request.getAttribute("nameCusValue");
            String phoneCusValue = (String) request.getAttribute("phoneCusValue");
            String addressCusValue = (String) request.getAttribute("addressCusValue");
            String userCusValue = (String) request.getAttribute("userCusValue");
            String statusCusValue = (String) request.getAttribute("statusCusValue");
        %>
        <form action="ControllerCustomer?action=listAll" method="POST" style="width: 257px">
            <table border="0" style="text-align: left">
                <tr>
                    <th>Customer ID: </th>
                    <td><input pattern="[0-9]{0,1000}" title="Enter number" type="text" name="idCus" value="<%=(idCusValue == null ? "" : idCusValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Customer Name: </th>
                    <td><input type="text" name="nameCus" value="<%=(nameCusValue == null ? "" : nameCusValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Phone: </th>
                    <td><input pattern="[0-9]{0,10}" title="Enter phone number" type="text" name="phoneCus" value="<%=(phoneCusValue == null ? "" : phoneCusValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Address: </th>
                    <td><input type="text" name="addressCus" value="<%=(addressCusValue == null ? "" : addressCusValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Username: </th>
                    <td><input type="text" name="userCus" value="<%=(userCusValue == null ? "" : userCusValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Status: </th>    
                    <td><select name="statusCus" size="1">
                            <%
                                try {
                                    int sta = Integer.parseInt(statusCusValue);
                            %>
                            <option value="2" <%=(sta == 2 ? "selected" : "")%> >-</option>
                            <option value="1" <%=(sta == 1 ? "selected" : "")%>  >Enable</option>
                            <option value="0" <%=(sta == 0 ? "selected" : "")%> >Disable</option>
                            <%
                            } catch (NumberFormatException e) { %>
                            <option value="2" selected >-</option>
                            <option value="1" >Enable</option>
                            <option value="0" >Disable</option>
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
                        <input type="reset" name="reset" value="Reset" style="width: 60px; text-align: center"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
