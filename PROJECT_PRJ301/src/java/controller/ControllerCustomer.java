/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCategory;
import model.DAOCustomer;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/ControllerCustomer"})
public class ControllerCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOCustomer dao = new DAOCustomer();
            DAOCategory daoC = new DAOCategory();
            String action = request.getParameter("action");
            if (action == null) {
                action = "listAll";
            }
            if (action.equals("listAll")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    // model
                    ResultSet rs = dao.getData("select * from Customer");
                    // some information
                    String title = "Customer List";
                    request.setAttribute("kqCus", rs);
                    request.setAttribute("titleCus", title);
                    request.setAttribute("option", "viewCus");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String id = request.getParameter("idCus");
                    String name = request.getParameter("nameCus");
                    String phone = request.getParameter("phoneCus");
                    String address = request.getParameter("addressCus");
                    String username = request.getParameter("userCus");
                    String status = request.getParameter("statusCus");
                    String idS = "", nameS = "", phoneS = "", addressS = "", usernameS = "", statusS = "";

                    if (id == null || id.equals("")) {
                        idS = " ";
                    } else {
                        idS = "and cid like '%" + id + "%' ";
                    }
                    if (name == null || name.equals("")) {
                        nameS = "";
                    } else {
                        nameS = "and cname like '%" + name + "%' ";
                    }
                    if (phone == null || phone.equals("")) {
                        phoneS = " ";
                    } else {
                        phoneS = "and cphone like '%" + phone + "%' ";
                    }
                    if (address == null || address.equals("")) {
                        addressS = " ";
                    } else {
                        addressS = "and cAddress like '%" + address + "%' ";
                    }
                    if (username == null || username.equals("")) {
                        usernameS = " ";
                    } else {
                        usernameS = "and username like '%" + username + "%' ";
                    }
                    if (!status.equals("2")) {
                        statusS = "and status = " + status;
                    } else {
                        statusS = " ";
                    }

                    String sql = "select * from Customer where username like '%%' "
                            + idS + nameS + phoneS + addressS
                            + usernameS + statusS;
                    ResultSet rs = dao.getData(sql);
                    String title = "Customer List";
                    request.setAttribute("kqCus", rs);
                    request.setAttribute("titleCus", title);
                    request.setAttribute("idCusValue", id);
                    request.setAttribute("nameCusValue", name);
                    request.setAttribute("phoneCusValue", phone);
                    request.setAttribute("addressCusValue", address);
                    request.setAttribute("userCusValue", username);
                    request.setAttribute("statusCusValue", status);
                    request.setAttribute("option", "viewCus");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                }
            }
            if (action.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// pre-update
                    String id = request.getParameter("cid");
                    ResultSet rs = dao.getData("select * from Customer where cid='" + id + "'");
                    request.setAttribute("rs", rs);
                    request.setAttribute("option", "updateCus");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    String cid = request.getParameter("cid");
                    String cname = request.getParameter("cname");
                    String cphone = request.getParameter("cphone");
                    String cAddress = request.getParameter("cAddress");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String status = request.getParameter("status");
                    //convert
                    int sta = Integer.parseInt(status);
                    int id = Integer.parseInt(cid);
                    Customer cus = new Customer(id, cname, cphone, cAddress, username, password, sta);
                    dao.updateCustomer(cus);
                    response.sendRedirect("ControllerCustomer");
                }
            }

            if (action.equals("delete")) {
                String cid = request.getParameter("cid");
                int id = Integer.parseInt(cid);
                dao.removeCustomer(id);
                response.sendRedirect("ControllerCustomer");
            }
            if (action.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/InsertCustomer.jsp");
                } else {
                    String cname = request.getParameter("cname");
                    String cphone = request.getParameter("cphone");
                    String cAddress = request.getParameter("cAddress");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String status = request.getParameter("status");
                    //covert
                    int sta = Integer.parseInt(status);
                    //entity
                    Customer cus = new Customer(cname, cphone, cAddress, username, password, sta);
                    dao.addCustomer(cus);
                    response.sendRedirect("ControllerCustomer");
                }
            }

            if (action.equals("listBill")) {
                String cid = request.getParameter("cid");
                int id = Integer.parseInt(cid);
                ResultSet rs = dao.getData("select * from Bill where cid = '" + cid + "'");
                // some information
                String title = "Bill List - ID: " + cid + " - Number Of Orders: " + dao.getOrders(id);
                request.setAttribute("kqBill", rs);
                request.setAttribute("titleBill", title);
                request.setAttribute("option", "viewBill");
                // select view
                RequestDispatcher disp
                        = request.getRequestDispatcher("/adminIndex.jsp");
                // run
                disp.forward(request, response);
            }

            if (action.equals("orderCus")) {
                String cid = request.getParameter("cid");
                int id = Integer.parseInt(cid);
                ResultSet rs = dao.getData("select * from Bill where cid = '" + cid + "'");
                // some information
                String title = "Bill List - Number Of Orders: " + dao.getOrders(id);
                request.setAttribute("kqBill", rs);
                request.setAttribute("titleBill", title);
                ArrayList<Category> listC = daoC.getCategory();
                request.setAttribute("listC", listC);
                request.setAttribute("action", "viewAllOrder");
                // select view
                RequestDispatcher disp
                        = request.getRequestDispatcher("/index.jsp");
                // run
                disp.forward(request, response);
            }
            if (action.equals("infoCus")) {
                String save = request.getParameter("save");
                if (save == null) {
                    String cid = request.getParameter("cid");
                    ResultSet rs = dao.getData("select * from Customer where cid = '" + cid + "'");
                    String title = "Customer Information";
                    request.setAttribute("rsCusInfo", rs);
                    request.setAttribute("titleCusInfo", title);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("mess", "");
                    request.setAttribute("cid", cid);
                    request.setAttribute("action", "cusInfo");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/index.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String id = request.getParameter("cusid");
                    String cname = request.getParameter("cusname");
                    String cphone = request.getParameter("cusphone");
                    String cadd = request.getParameter("cusaddress");
                    dao.updateInfo(cname, cphone, cadd, id);
                    ResultSet rs = dao.getData("select * from Customer where cid = '" + id + "'");
                    String title = "Customer Information";
                    request.setAttribute("rsCusInfo", rs);
                    request.setAttribute("titleCusInfo", title);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("action", "cusInfo");
                    request.setAttribute("cid", id);
                    request.setAttribute("mess", "Update successful!");
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/index.jsp");
                    // run
                    disp.forward(request, response);
                }
            }
            
            if (action.equals("changePass")) {
                String change = request.getParameter("change");
                if (change == null) {
                    String cid = request.getParameter("cid");
                    ResultSet rs = dao.getData("select * from Customer where cid = '" + cid + "'");
                    String title = "Customer Information";
                    request.setAttribute("rsCusInfo", rs);
                    request.setAttribute("titleCusInfo", title);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("mess", "");
                    request.setAttribute("messP", "");
                    request.setAttribute("showChangePass", "open");
                    request.setAttribute("cid", cid);
                    request.setAttribute("action", "cusInfo");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/index.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String cid = request.getParameter("cid");
                    int cusid = Integer.parseInt(cid);
                    ResultSet rs1 = dao.getData("select * from Customer where cid = '" + cid + "'");
                    String oldpassword = request.getParameter("oldpassword");
                    String newpassword = request.getParameter("newpassword");
                    String renewpassword = request.getParameter("renewpassword");
                    String messP = "";
                    String oldP = "";
                    if (rs1.next()) {
                        oldP = rs1.getString(6);
                    }
                    if (oldP.compareTo(oldpassword) != 0) {
                        messP = "Old Password incorrect!";
                    } else {
                        if (newpassword.compareTo(renewpassword) != 0) {
                            messP = "New Password Does Not Match!";
                        } else {
                            //int id = Integer.parseInt(adminID);
                            dao.changePassword(cusid, newpassword);
                            messP = "Change password successfully!";
                        }
                    }
                    String title = "Customer Information";
                    request.setAttribute("rsCusInfo", rs1);
                    request.setAttribute("titleCusInfo", title);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("action", "cusInfo");
                    request.setAttribute("cid", cid);
                    request.setAttribute("showChangePass", "open");
                    request.setAttribute("messP", messP);
                    request.setAttribute("mess", "");
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/index.jsp");
                    // run
                    disp.forward(request, response);
                }
            }
            if (action.equals("sortOrder")) {
                ResultSet rs = dao.getData("select * from Customer order by totalAll desc");
                    // some information
                    String title = "Customer List";
                    request.setAttribute("kqCus", rs);
                    request.setAttribute("titleCus", title);
                    request.setAttribute("option", "viewCus");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e){
            
        }
    }

    public void dispath(HttpServletRequest request,
            HttpServletResponse response, String url) throws ServletException,
            IOException {
        RequestDispatcher disp
                = request.getRequestDispatcher(url);
        // run
        disp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
