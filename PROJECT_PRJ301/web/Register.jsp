<%-- 
    Document   : Register
    Created on : Oct 17, 2021, 11:25:08 PM
    Author     : TRONG TUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login.css">
        <title>Register</title>
    </head>
    <body>
        <% 
            String name = (String)request.getAttribute("cname");
            String phone = (String)request.getAttribute("cphone");
            String address = (String)request.getAttribute("cAddress");
            String username = (String)request.getAttribute("username");
            String err = (String) request.getAttribute("err");
            String err1 = (String) request.getAttribute("err1");
        %>
        <form action="Login?action=register" method="Post">
            <div class="container">
                <div class="res"><h3>REGISTER</h3></div>
                <b>Customer Name</b>
                <input type="text" placeholder="Enter Your Name" name="cname" value="<%=(name == null ? "": name) %>" required <%=(name == null ? "autofocus": "") %>
                       pattern="[A-Za-z\s]{1,50}" title="Tên không được chứa chữ số và ký tự đặc biệt">
                </br>
                <b>Phone</b>
                <input type="text" placeholder="Enter Your Phone" name="cphone" value="<%=(phone == null ? "": phone) %>" required
                       pattern="[0-9]{10}" title="Số điện thoại có 10 chữ số">
                </br>
                <b>Address</b>
                <input type="text" placeholder="Enter Your Address" name="cAddress" value="<%=(address == null ? "": address) %>" required>
                </br>
                <b>Username</b>
                <input type="text" placeholder="Enter Username" name="username" value="<%=(username == null ? "": username) %>" required
                       pattern="[^\s]{1,50}" title="Username không được chứa dấu cách" <%=(err == null ? "": "autofocus") %>>
                <h6><%=(err == null ? "" : err)%></h6> 
                <b>Password</b>
                <input type="password" placeholder="Enter Password" name="password" value="" required
                       <%=(err1 == null ? "": "autofocus") %>
                       pattern="[^\s]{8,100}" title="Mật khẩu có tối thiểu 8 ký tự và không được chứa dấu cách">
                <h6><%=(err1 == null ? "" : err1)%></h6> 
                <b>Repeat Password</b>
                <input type="password" placeholder="Re-enter Password" name="repassword" value="" required >
                </br>
                <button type="submit" name="submit">Register</button>
                <div><a href="Login"><- Back</a></div>
            </div>
        </form>
    </body>
</html>
