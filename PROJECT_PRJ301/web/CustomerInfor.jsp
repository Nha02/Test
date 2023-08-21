<%-- 
    Document   : CustomerInfor
    Created on : Oct 31, 2021, 6:46:20 PM
    Author     : TRONG TUAN
--%>

<%@page import="model.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.annotation.Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="margin-left: 15%">
            <div>
                <%  
                    DBConnect dao = new DBConnect();
                    String titleC = (String) request.getAttribute("titleCusInfo");
                    String cusid = (String) request.getAttribute("cid");
                    String mess = (String) request.getAttribute("mess");
                    String messP = (String) request.getAttribute("messP");
                    String show = (String) request.getAttribute("showChangePass");
                    ResultSet rsCus = dao.getData("select * from Customer where cid = '" + cusid + "'");
                %>
                <!--        <p><a href="ControllerAdmin">Back</a></p>-->
                <form action="ControllerCustomer?action=infoCus&cid=<%=cusid%>" method="Post" >
                    <table border="0"> 
                        <tr><h4 colspan="2" style="text-align: center"><%=titleC%></h4></tr>
                        <tr hidden>
                            <th>ID</th>
                            <td><input type="text" name="cusid" value="<%=cusid%>" /></td>
                        </tr>
                        <% while (rsCus.next()) {%>
                        <tr>
                            <th>Username: </th>
                            <th style="text-align: center"><%=rsCus.getString(5)%></th>
                        </tr>
                        <tr>
                            <th>Full name: </th>
                            <td><input pattern="[A-Za-z\s]{1,50}" title="Tên không được chứa chữ số và ký tự đặc biệt" type="text" name="cusname" value="<%=rsCus.getString(2)%>" required/></td>
                        </tr>
                        <tr>
                            <th>Phone: </th>
                            <td><input pattern="[0-9]{10}" title="Số điện thoại có 10 chữ số" type="text" name="cusphone" value="<%=rsCus.getString(3)%>" required/></td>
                        </tr>
                        <tr>
                            <th>Address: </th>
                            <td><input type="text" name="cusaddress" value="<%=rsCus.getString(4)%>" required/></td>
                        </tr>
                        <% }%>
                        <tr>
                            <th></th>
                            <td>
                                <input type="submit" value="Save" name="save"/>
                                <input type="reset" value="Reset" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <th style="color: red"><%=mess%></th>
                        </tr>
                    </table>
                </form>

                <%  try {
                        if (show.equals("open")) {
                %>
                <form action="ControllerCustomer?action=changePass&cid=<%=cusid%>" method="Post" style="margin-top: 5%">
                    <table border="0">
                        <tr>
                        <h6 colspan="2" style="text-align: center">---------------------</h6>
                        </tr>
                        <tr>
                        <h5 colspan="2" style="text-align: center">Change Password</h5>
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
                                <input type="submit" value="Change" name="change"/>
                                <input type="reset" value="Reset" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <th style="color: red"><%=messP%></th>
                        </tr>
                        </tr>
                    </table>
                </form>
                <% }
                    } catch (NullPointerException e) {
                    }%>
            </div>
            </br>
            <div style="text-align: center">
                <div style="width: 25%; height:28px;background:orange;border-radius:5px; float: left; border: #343a40 solid thin">
                    <a style="color: black; font-weight: bold" href="HomeController">< Home</a>
                </div>
                <%
                    try {
                        if (!show.equals("open")) {
                %>
                <div style="width: 65%; height:28px;background:orange;border-radius:5px; float: right; border: #343a40 solid thin">
                    <a style="color: black; font-weight: bold" href="ControllerCustomer?action=changePass&cid=<%=cusid%>">Change Password</a>
                </div>
                <% }
                } catch (NullPointerException e) {%>
                <div style="width: 65%; height:28px;background:orange;border-radius:5px; float: right; border: #343a40 solid thin">
                    <a style="color: black; font-weight: bold" href="ControllerCustomer?action=changePass&cid=<%=cusid%>">Change Password</a>
                </div>
                <%}%>
            </div> 
        </div>
    </body>
</html>
