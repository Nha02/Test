<%-- 
    Document   : showCart
    Created on : Oct 11, 2021, 1:58:57 PM
    Author     : TRONG TUAN
--%>

<%@page import="entity.Customer"%>
<%@page import="model.DAOCustomer"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form class="col-sm-12" action="Product_session?action=showCart" method="POST">
            <div class="col-sm-12" style="text-align: center">
                <h3 class="col-sm-12">Shopping Cart Details</h3>
                <table class="col-sm-12" width=80% border="1">
                    <thead>
                        <tr style="text-align: center">
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>               
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            //Customer cus = (Customer) session.getAttribute("user");
                            //String cname = cus.getCname();
                            
                        %>

                        <%
                            java.util.Enumeration em = session.getAttributeNames();
                            double totalAll = 0;
                            while (em.hasMoreElements()) {
                                String id = em.nextElement().toString();
                                if (!id.equals("user") && !id.equals("admin")) {
                                    String m = "mess" + id;
                                    String mess = (String) request.getAttribute(m);
                                    Product pro = (Product) session.getAttribute(id);
                                    String name = pro.getPname();
                                    int quan = pro.getQuantity();
                                    double price = pro.getPrice();
                                    double total = quan * price;
                                    totalAll = total + totalAll;
                        %>
                        <tr style="text-align: center">
                            <td><%=id%></td>
                            <td><%=name%></td>
                            <td style="padding-bottom: auto; padding-top: auto">
                                <input size="1px" style="text-align: center" type="text" required="" pattern="[0-9]{1,10}" title="Enter a number" name="<%=id%>" value="<%=quan%>" />
                                <% if(mess != null) { %>
                                <p style="color: red; font-size: 80%; font-weight: bold"><%=mess %></p>
                                <% } else {} %>
                            <td><%=price%></td>
                            <td><%=Math.round(total * 100.0) / (100.0)%></td>
                            <td><a href="Product_session?action=remove&pid=<%=id%>">Remove</a></td>
                        </tr>
                        <% }
                        }%>
                    </tbody>
                    <tfoot>
                        <tr style="text-align: center">
                            <td colspan="2">
                                <div style="width:150px; border-radius:5px; margin-left: 90px; margin-right: 0px">
                                    <input style="color: white; background: gray; font-size: 100%; font-weight: bold" 
                                           type="submit" value="Update Quantity" name="submit" />
                                </div>
                            </td>
                            <td colspan="2"><p style="font-size: 110%; font-weight: bold; margin-bottom: auto; margin-top: auto">Total</p></td>
                            <td><%=Math.round(totalAll * 100.0) / (100.0)%></td>
                            <td><a href="Product_session?action=removeAll">Remove All</a></td>
                        </tr>
                    </tfoot>
                </table>
                </br>
                <div style="width: 100%;">
                    <div style="width:110px;height:30px;background:orange;border-radius:5px; float: left; border: #343a40 solid thin">
                        <a style="color: black; font-size: 105%; font-weight: bold" href="HomeController"><- Back</a>
                    </div>
                    <div style="width:110px;height:30px;background:orange;border-radius:5px; float: right; border: #343a40 solid thin">
                        <a style="color: black; font-size: 105%; font-weight: bold" href="Product_session?action=checkout">Check-out</a>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
