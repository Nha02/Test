<%-- 
    Document   : insertProduct
    Created on : Oct 8, 2021, 11:15:30 AM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
    String messErr = (String) request.getAttribute("messErr");
    String pname = (String) request.getAttribute("pname");
    String quan = (String) request.getAttribute("quan");
    String price = (String) request.getAttribute("price");
    String image = (String) request.getAttribute("image");
    String desc = (String) request.getAttribute("desc");
    String cate = (String) request.getAttribute("cate");
%>
<form action="ControllerProduct?action=insert" method="Post" style="margin-left: auto; margin-right: auto">
    <table border="0"> 
        <tr>
            <td colspan="2"><h4 style="text-align: center">Insert Product</h4></td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <%if (cate == null) {%>
                <select name="cate" size="1" >
                    <%while (rsCate.next()) {%>
                    <option value="<%=rsCate.getInt(1)%>"><%=rsCate.getString(2)%></option>
                    <%}%>
                </select>
                <%} else {
                    int c = Integer.parseInt(cate);
                %>
                <select name="cate" size="1" >
                    <%while (rsCate.next()) {%>
                    <option value="<%=rsCate.getInt(1)%>" <%=(c == rsCate.getInt(1) ? "selected" : "")%>><%=rsCate.getString(2)%></option>
                    <%}%>
                </select>
                <%} %>
            </td>
        </tr>   
        <tr>
            <td>Product ID</td>
            <td><input type="text" name="id" value="" required /></td>
        </tr>
        <tr>
            <td>Product Name  </td>
            <td><input type="text" name="pname" value="<%=(pname == null ? "" : pname)%>" required /></td>
        </tr>
        <tr>
            <td>Quantity</td>
            <td><input pattern="[0-9]{0,1000}" title="Enter number" type="text" name="quantity" value="<%=(quan == null ? "" : quan)%>" required /></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input pattern="[0-9.]{0,1000}" title="Enter number" type="text" name="price" value="<%=(price == null ? "" : price)%>" required /></td>
        </tr>
        <tr>
            <td>Image Name</td>
            <td><input type="text" name="image" value="<%=(image == null ? "img/" : image)%>" required /></td>
        </tr>
        <tr>
            <td>Description   </td>
            <td><input type="text" name="des" value="<%=(desc == null ? "RAM:  - ROM: " : desc)%>" required /></td>
        </tr>
        <tr>
            <td>Status</td>
            <td><input type="radio" name="status" value="1" checked />Enable
                <input type="radio" name="status" value="0" />Disable
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Insert" name="submit"/>
                <input type="reset" value="Reset" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="font-weight: bold; color: red"><%=(messErr == null ? "" : messErr)%></td>
        </tr>
    </table>
</form>
