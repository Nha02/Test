<%-- 
    Document   : InsertCustomer
    Created on : Oct 8, 2021, 1:29:46 PM
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
        <form action="ControllerCustomer?action=insert" method="Post">
            <table border="0"> 
                <tr>
                    <td>Customer Name</td>
                    <td><input type="text" name="cname" value="" /></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="cphone" value="" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="cAddress" value="" /></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="" /></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="radio" name="status" value="1" checked />Enable
                        <input type="radio" name="status" value="0" />Disable
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Insert" name="submit"/>
                        <input type="reset" value="reset" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
