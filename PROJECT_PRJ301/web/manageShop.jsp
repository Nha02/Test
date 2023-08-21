<%-- 
    Document   : managerShop
    Created on : Oct 18, 2021, 1:26:21 PM
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
        <h1>Hello Admin!</h1>
        <form action="ManagerShop?action=overview" method="Post">
            <table border="1"> 
                <tr>
                    <td><a href="ManagerShop?action=admin">Manage Admin</a></td>
                </tr>
                <tr>
                   <td><a href="ManagerShop?action=customer">Manage Customers</a></td> 
                </tr>
                <tr>
                    <td><a href="ManagerShop?action=category">Manage Category</a></td>
                </tr>
                <tr>
                  <td><a href="ManagerShop?action=product">Manage Products</a></td>
                </tr>
                <tr>
                    <td><a href="ManagerShop?action=bill">Manage Bill</a></td>
                </tr>    
            </table>
        </form>
    </body>
</html>
