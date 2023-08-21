<%-- 
    Document   : formSearchProduct
    Created on : Oct 27, 2021, 3:18:30 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.DAOCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String[] cateValue = (String[]) request.getAttribute("cateValue");
            String idValue = (String) request.getAttribute("idValue");
            String nameValue = (String) request.getAttribute("nameValue");
            String fromQuaValue = (String) request.getAttribute("fromQuaValue");
            String toQuaValue = (String) request.getAttribute("toQuaValue");
            String fromPriValue = (String) request.getAttribute("fromPriValue");
            String toPriValue = (String) request.getAttribute("toPriValue");
            String status = (String) request.getAttribute("statusValue");

        %>
        <form action="ControllerProduct?action=listAll" method="POST" style="width: 257px">
            <table border="0" style="text-align: left">
                <%                    int count = 0;
                    DAOCategory daoC = new DAOCategory();
                    ResultSet rs1 = daoC.getData("select * from Category");
                    while (rs1.next()) {
                        count++;
                %>
                <tr style="margin-left: 5px">
                    <th><%=(count > 1 ? "" : "Category: ")%></th>
                    <td style="text-align: left" ><input type="checkbox" value="<%=rs1.getInt(1)%>" name="category" <%=(daoC.checkCatecheck(rs1.getInt(1), cateValue) == 1 ? "checked" : "")%> /><%=rs1.getString(2)%></td>
                        <% }%>
                <tr>
                    <th>Product ID: </th>
                    <td><input type="text" name="id" value="<%=(idValue == null ? "" : idValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Product Name: </th>
                    <td><input type="text" name="name" value="<%=(nameValue == null ? "" : nameValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Quantity: </th>
                    <td><input type="text" name="fromQua" value="<%=(fromQuaValue == null ? "" : fromQuaValue)%>" placeholder="From..." style="width: 45px; text-align: center"/>
                        - <input type="text" name="toQua" value="<%=(toQuaValue == null ? "" : toQuaValue)%>" placeholder="To..." style="width: 45px; text-align: center"/> pcs</td>
                </tr>
                <tr>
                    <th>Price: </th>
                    <td><input pattern="[0-9.]{0,1000}" title="Enter number" type="text" value="<%=(fromPriValue == null ? "" : fromPriValue)%>" name="fromPri" placeholder="From..." style="width: 45px; text-align: center"/>
                        - <input pattern="[0-9.]{0,1000}" title="Enter number" type="text" value="<%=(toPriValue == null ? "" : toPriValue)%>" name="toPri" placeholder="To..." style="width: 45px; text-align: center"/> $</td>
                </tr>
                <tr>
                    <th>Status: </th>    
                    <td><select name="status" size="1">
                            <%
                                try {
                                    int sta = Integer.parseInt(status);
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
