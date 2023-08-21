<%-- 
    Document   : searchInClient
    Created on : Nov 2, 2021, 6:55:18 PM
    Author     : TRONG TUAN
--%>

<%@page import="model.DAOProduct"%>
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
        <%  //String cateValue = (String) request.getAttribute("cateValue");
            String[] ramValue = (String[]) request.getAttribute("ramValue");
            String[] romValue = (String[]) request.getAttribute("romValue");
            String fromPriValue = (String) request.getAttribute("fromPriValue");
            String toPriValue = (String) request.getAttribute("toPriValue");
            DAOProduct dao = new DAOProduct();
        %>
        <form action="ControllerProduct?action=search2" method="POST">
            <table border="0" style="text-align: left">
                <tr style="margin-left: 5px">
                    <th>RAM:   </th>
                    <td style="text-align: left" >
                        <input type="checkbox" value="4GB" name="ram" <%=(dao.checkbox("4GB", ramValue) == 1 ? "checked" : "")%> />4GB
                        <input type="checkbox" value="6GB" name="ram" <%=(dao.checkbox("6GB", ramValue) == 1 ? "checked" : "")%> />6GB
                        <input type="checkbox" value="8GB" name="ram" <%=(dao.checkbox("8GB", ramValue) == 1 ? "checked" : "")%> />8GB
                        <input type="checkbox" value="12GB" name="ram" <%=(dao.checkbox("12GB", ramValue) == 1 ? "checked" : "")%> />12GB
                    </td>
                </tr>

                <tr style="margin-left: 5px">
                    <th>ROM:   </th>
                    <td style="text-align: left" >
                        <input type="checkbox" value="64GB" name="rom" <%=(dao.checkbox("64GB", romValue) == 1 ? "checked" : "")%> />64GB
                        <input type="checkbox" value="128GB" name="rom" <%=(dao.checkbox("128GB", romValue) == 1 ? "checked" : "")%> />128GB
                        <input type="checkbox" value="256GB" name="rom" <%=(dao.checkbox("256GB", romValue) == 1 ? "checked" : "")%> />256GB
                        <input type="checkbox" value="512GB" name="rom" <%=(dao.checkbox("512GB", romValue) == 1 ? "checked" : "")%> />512GB
                    </td>
                </tr>
                <tr>
                    <th>Price: </th>
                    <td><input pattern="[0-9.]{0,1000}" title="Enter number" type="text" value="<%=(fromPriValue == null ? "" : fromPriValue)%>" name="fromPri" placeholder="From..." style="width: 80px; text-align: center"/>
                        - <input pattern="[0-9.]{0,1000}" title="Enter number" type="text" value="<%=(toPriValue == null ? "" : toPriValue)%>" name="toPri" placeholder="To..." style="width: 80px; text-align: center"/> $</td>
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
