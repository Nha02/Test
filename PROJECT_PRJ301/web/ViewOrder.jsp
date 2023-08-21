<%-- 
    Document   : ViewOrder
    Created on : Oct 25, 2021, 10:58:26 AM
    Author     : TRONG TUAN
--%>

<%@page import="model.DAOBill"%>
<%@page import="model.DAOCustomer"%>
<%@page import="model.DAOProduct"%>
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
            String oID = request.getAttribute("oid").toString();
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            String title = request.getAttribute("title").toString();
            DAOProduct dao = new DAOProduct();
            DAOBill daoB = new DAOBill();
            DAOCustomer daoCus = new DAOCustomer();
            int cid = daoB.getBill(oID).getCid();
            int status = daoB.getBill(oID).getStatus();
            String c = "" + cid;
        %>
        <form class="col-sm-12" action="Product_session?action=showBill&oID=<%=oID%>" >
            <table class="col-sm-10" border="0" style="width: 95%; margin-left: auto; margin-right: auto">
                <thead>
                    <tr><th colspan="10" style="text-align: center"><h4><%=title%></h4></th></tr>
                </thead>
                <tbody>
                    <tr>
                        <th colspan="4">Bill ID: <%=oID%></th>
                        <th colspan="2">Date: <%=daoB.getDate(oID).substring(0, 10)%></th>
                        <th colspan="3">Time: <%=daoB.getDate(oID).substring(11, 19)%></th>
                    </tr>
                    <tr>
                        <th colspan="4">Orderer ID: <%=cid%></th>
                        <th colspan="5">Receiver: <%=daoB.getBill(oID).getCname()%></th>
                    </tr>
                    <tr>
                        <th colspan="4">Orderer: <%=daoCus.getCusByID(c).getCname()%></th>
                        <th colspan="5">Phone:  <%=daoB.getBill(oID).getCphone()%></th>
                    </tr>
                    <tr>
                        <%
                            String s = null;
                            if (status == 1) {
                                s = "WAIT";
                            }
                            if (status == 2) {
                                s = "PROCESS";
                            }
                            if (status == 3) {
                                s = "DONE";
                            }
                        %>
                        <th>Status: </th>
                        <th style="color: red"><%=s%></th>
                        <th colspan="5">Address: <%=daoB.getBill(oID).getcAddress()%></th>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="10" style="text-align: center">-------------------------</th>
                    </tr>
                </tfoot>
            </table>
            <table class="col-sm-10" border="1" style="width: 95%; margin-left: auto; margin-right: auto">
                <thead style="text-align: center">
                    <tr><th colspan="7" ><h5>Product List</h5></th></tr>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%while (rs.next()) {%>
                    <tr style="text-align: center; padding-left: 10px; padding-right: 10px">
                        <td><%=rs.getString(1)%></td>
                        <td><%=dao.getProduct(rs.getString(1)).getPname()%></td>
                        <td><%=rs.getInt(3)%></td>
                        <td><%=rs.getDouble(4)%></td>
                        <td><%=rs.getDouble(5)%></td>
                    </tr>
                    <%}%>
                </tbody>
                <tfoot>        
                    <tr>
                        <th colspan="4" style="text-align: center"><h5>Total</h5></th>
                        <th style="text-align: center"><h5><%=(daoB.getBill(oID).getTotal())%></h5></th>
                    </tr>
                </tfoot>
            </table>
            </br>
            <div style="text-align: center; margin-left: 9%; width: 15%; height:28px; background:orange;border-radius:5px; float: left; border: #343a40 solid thin">
                <a style="color: black; font-weight: bold" href="ControllerCustomer?action=orderCus&cid=<%=cid%>">< Order History</a>
            </div>
        </form>
    </body>
</html>
