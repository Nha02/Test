/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOAdmin;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "ControllerAdmin", urlPatterns = {"/ControllerAdmin"})
public class ControllerAdmin extends HttpServlet {

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
            DAOAdmin dao = new DAOAdmin();
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            if (action == null) {
                action = "adminLogin";
            }
            if (action.equals("logout")) {
                session.invalidate();
                response.sendRedirect("ControllerAdmin");
            }
            if (action.equals("adminLogin")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/adminLogin.jsp");
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    request.setAttribute("ad", username);
                    if (dao.checkLogin(username, password) == 1) {
                        session.setAttribute("accAd", username);
                        dispath(request, response, "/adminIndex.jsp");
                    } else {
                        String err = "Username or password incorrect!";
                        request.setAttribute("err", err);
                        dispath(request, response, "/adminLogin.jsp");
                    }
                }
            }

            if (action.equals("listAll")) {
                ResultSet rs = dao.getData("select * from admin");
                String title = "Admin List";
                request.setAttribute("kqAd", rs);
                request.setAttribute("titleAd", title);
                request.setAttribute("option", "viewAd");
                RequestDispatcher disp
                        = request.getRequestDispatcher("/adminIndex.jsp");
                disp.forward(request, response);
            }
            if (action.equals("changePass")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String adminID = request.getParameter("adminID");
                    request.setAttribute("adID", adminID);
                    String mess = "";
                    request.setAttribute("mess", mess);
                    request.setAttribute("option", "updateAd");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    String adminID = request.getParameter("adminID");
                    int id = Integer.parseInt(adminID);
                    ResultSet rs = dao.getData("select * from admin where adminID =" + id);
                    String oldpassword = request.getParameter("oldpassword");
                    String newpassword = request.getParameter("newpassword");
                    String renewpassword = request.getParameter("renewpassword");
                    String mess = "";
                    String oldP = "";
                    if (rs.next()) {
                        oldP = rs.getString(3);
                    }
                    if (oldP.compareTo(oldpassword) != 0) {
                        mess = "Old Password incorrect!";
                    } else {
                        if (newpassword.compareTo(renewpassword) != 0) {
                            mess = "New Password Does Not Match!";
                        } else {
                            //int id = Integer.parseInt(adminID);
                            dao.changePassword(id, newpassword);
                            mess = "Change password successfully!";
                        }
                    }
                    request.setAttribute("option", "updateAd");
                    request.setAttribute("adID", adminID);
                    request.setAttribute("mess", mess);
                    dispath(request, response, "/adminIndex.jsp");
                    //response.sendRedirect("ControllerAdmin&adminID="+id);
                }
            }

            if (action.equals("delete")) {
                String adminID = request.getParameter("adminID");
                dao.removeAdmin(adminID);
                response.sendRedirect("ControllerAdmin?action=listAll");
            }
            if (action.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.setAttribute("option", "insertAd");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if (dao.checkUser(username) == true) {
                        admin ad = new admin(username, password);
                        dao.addAdmin(ad);
                        response.sendRedirect("ControllerAdmin?action=listAll");
                    } else {
                        request.setAttribute("messAd", "Duplicate Username!");
                        request.setAttribute("option", "insertAd");
                        dispath(request, response, "/adminIndex.jsp");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
