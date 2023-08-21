<%-- 
    Document   : ViewAdmin
    Created on : Oct 8, 2021, 11:30:30 AM
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
            ResultSet rs = (ResultSet) request.getAttribute("kqAd");
            String title = (String) request.getAttribute("titleAd");
        %>
        <table border="1" style="width: 95%; margin-left: auto; margin-right: auto">  
            <thead style="text-align: center">
                <tr><th colspan="5" style="background-color: #E8C900"><%=title%></th></tr>
                <tr st>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Change Password</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody style="text-align: center">
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><a style="font-weight: bold" href="ControllerAdmin?action=changePass&adminID=<%=rs.getInt(1)%>">[change]</a></td>
                    <%if (!rs.getString(2).equals("general_admin")) {%>
                    <td><a style="font-weight: bold" href="ControllerAdmin?action=delete&adminID=<%=rs.getInt(1)%>">[delete]</a></td>
                    <%} else {%>
                    <td style="font-weight: bold; color: red">HOST</td>
                    <%} %>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
