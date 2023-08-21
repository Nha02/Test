<%-- 
    Document   : adminLogin
    Created on : Oct 22, 2021, 8:51:07 AM
    Author     : TRONG TUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login.css">
        <title>Login Admin</title>
    </head>
    <body>
        <% 
            String username = (String)request.getAttribute("ad");
            String err = (String) request.getAttribute("err");
        %>
        <form action="ControllerAdmin" method="POST">
            <div class="container">
                <div class="res"><h3>LOGIN ADMIN</h3></div>
                <b>Username</b>
                <input type="text" placeholder="Enter Username" name="username" value="<%=(username == null ? "": username) %>" required autofocus="autofocus">
                </br>
                <b>Password</b>
                <input type="password" placeholder="Enter Password" name="password" required>
                </br>
                <button type="submit" name="submit">Login</button>
                </div>
            </div>
        </form>
        </br>
        <div class="res"><h6><%=(err == null ? "" : err)%></h6></div>
    </body>
</html>
