<%-- 
    Document   : UpdateAdmin
    Created on : Oct 8, 2021, 11:31:45 AM
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
        <% String idAd = (String) request.getAttribute("adID");
           String m = (String) request.getAttribute("mess");
        %>
        <!--        <p><a href="ControllerAdmin">Back</a></p>-->
        <form action="ControllerAdmin?action=changePass&adminID=<%=idAd %>" method="Post" style="margin-left: 15%">
            <table border="0"> 
                <tr><h4 colspan="2" style="text-align: center">Change Password</h4></tr>
                <tr>
                    <th>ID</th>
                    <td><input type="text" name="adminID" value="<%=idAd %>" /></td>
                </tr>
                <tr>
                    <th>Old Password</th>
                    <td><input type="password" name="oldpassword" value="" required/></td>
                </tr>
                <tr>
                    <th>New Password</th>
                    <td><input pattern="[^\s]{8,100}" title="Mật khẩu có tối thiểu 8 ký tự và không được chứa dấu cách" type="password" name="newpassword" value="" required/></td>
                </tr>
                <tr>
                    <th>Retype New Password</th>
                    <td><input pattern="[^\s]{8,100}" title="Mật khẩu có tối thiểu 8 ký tự và không được chứa dấu cách" type="password" name="renewpassword" value="" required/></td>
                </tr>
                
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" value="Change Password" name="submit"/>
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <th style="color: red"><%=m %></th>
                </tr>
            </table>
        </form>
    </body>
</html>
