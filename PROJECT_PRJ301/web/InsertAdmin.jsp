<%-- 
    Document   : InsertAdmin
    Created on : Oct 8, 2021, 11:31:30 AM
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
        <% String messAd = (String) request.getAttribute("messAd");%>
        <form action="ControllerAdmin?action=insert" method="Post" style="margin-left: 20%">
            <table border="0"> 
                <tr><h4 colspan="2" style="text-align: center">Insert Admin</h4></tr>
                <tr>
                    <th>Username </th>
                    <td><input pattern="[^\s]{1,50}" title="Username không được chứa dấu cách" type="text" name="username" value="" required /></td>
                </tr>
                <tr>
                    <th>Password </th>
                    <td><input pattern="[^\s]{8,100}" title="Mật khẩu có tối thiểu 8 ký tự và không được chứa dấu cách" type="password" name="password" value="" required /></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" value="Insert" name="submit"/>
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td style="font-weight: bold; color: red"><%=(messAd == null ? "" : messAd)%></td>
                </tr>
            </table>
        </form>
    </body>
</html>
