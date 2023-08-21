<%-- 
    Document   : login
    Created on : Oct 17, 2021, 10:46:39 PM
    Author     : TRONG TUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login.css">
        <title>Login</title>
    </head>
    <body>
        <% 
            String username = (String)request.getAttribute("username");
            String err = (String) request.getAttribute("err");
            String act = (String) request.getAttribute("act");
        %>
        <form action="Login" method="POST">
            <div class="container">
                <div class="res"><h3>LOGIN</h3></div>
                <b>Username</b>
                <input type="hidden" name="act" value="<%=act %>" />
                <input type="text" placeholder="Enter Username" name="username" value="<%=(username == null ? "": username) %>" required <%=(username == null ? "autofocus": "") %>>
                </br>
                <b>Password</b>
                <input type="password" placeholder="Enter Password" name="password" required <%=(err == null ? "": "autofocus") %>>
                </br>
                <button type="submit" name="loginCus">Login</button>
                <div class="res"><a href="Login?action=register">Create an account</a>
                </div>
            </div>
        </form>
        </br>
        <div class="res"><h6><%=(err == null ? "" : err)%></h6></div>
    </body>
</html>
