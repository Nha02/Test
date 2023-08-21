<%-- 
    Document   : formSearchCategory
    Created on : Oct 29, 2021, 11:07:46 AM
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
            String idValue = (String) request.getAttribute("idCateValue");
            String nameValue = (String) request.getAttribute("nameCateValue");
            String status = (String) request.getAttribute("statusCateValue");
        %>
        <form action="ControllerCategory?action=listAll" method="POST" style="width: 257px">
            <table border="0" style="text-align: left">
                <tr>
                    <th>Category ID: </th>
                    <td><input type="text" name="idCate" value="<%=(idValue == null ? "" : idValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Category Name: </th>
                    <td><input type="text" name="nameCate" value="<%=(nameValue == null ? "" : nameValue)%>" style="width: 135px"/> </td>
                </tr>
                <tr>
                    <th>Status: </th>    
                    <td><select name="statusCate" size="1">
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
