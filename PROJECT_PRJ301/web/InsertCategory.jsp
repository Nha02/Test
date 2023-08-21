<%-- 
    Document   : InsertCategory
    Created on : Oct 8, 2021, 12:50:11 PM
    Author     : TRONG TUAN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="ControllerCategory?action=insert" method="Post" style="margin-left: auto; margin-right: auto">
    <table border="0"> 
        <tr>
            <td colspan="2"><h4 style="text-align: center">Insert Category</h4></td>
        </tr>
        <tr>
            <td>Category Name  </td>
            <td><input type="text" name="cateName" value="" required /></td>
        </tr>
        <tr>
            <td>Status </td>
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
    </table>
</form>
