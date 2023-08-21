/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOAdmin;
import model.DAOCustomer;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            HttpSession session = request.getSession();
            DAOCustomer daoCus = new DAOCustomer();
            DAOAdmin daoAd = new DAOAdmin();
            String action = request.getParameter("action");
            if (action == null) {
                action = "login";
            }

            if (action.equals("logout")) {
                //session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("HomeController");

            }

            if (action.equals("login")) {
                session.removeAttribute("user");
                String submit = request.getParameter("loginCus");
                if (submit == null) {
                    dispath(request, response, "/login.jsp");
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String act = request.getParameter("act");
                    if (daoCus.checkLogin(username, password) == 1) {
                        Customer cus = daoCus.getCus(username);
                        session.setAttribute("user", cus);
                        if (act.equals("checkout")) {
                            response.sendRedirect("Product_session?action=checkout");
                        } else {
                            response.sendRedirect("HomeController");
                        }
                    } else {
                        request.setAttribute("username", username);
                        String err = "Username or password incorrect!";
                        request.setAttribute("err", err);
                        dispath(request, response, "/login.jsp");
                    }
                }
            }

            if (action.equals("register")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/Register.jsp");
                } else {
                    String cname = request.getParameter("cname");
                    String cphone = request.getParameter("cphone");
                    String cAddress = request.getParameter("cAddress");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String repassword = request.getParameter("repassword");
                    int status = 1;
                    if (password.equals(repassword)) {
                        if (daoCus.checkUser(username) == true) {
                            Customer cus = new Customer(cname, cphone, cAddress, username, password, status, 0, 0);
                            daoCus.addCustomer(cus);
                            int cid = daoCus.getID(username);
                            Customer cus1 = daoCus.getCus(username);
                            session.setAttribute("user", cus1);
                            response.sendRedirect("HomeController");
                        } else {
                            request.setAttribute("cname", cname);
                            request.setAttribute("cphone", cphone);
                            request.setAttribute("cAddress", cAddress);
                            request.setAttribute("username", username);
                            String err = "Username already exists!";
                            request.setAttribute("err", err);
                            dispath(request, response, "/Register.jsp");
                        }
                    } else {
                        request.setAttribute("cname", cname);
                        request.setAttribute("cphone", cphone);
                        request.setAttribute("cAddress", cAddress);
                        request.setAttribute("username", username);
                        String err = "Passwords are not the same!";
                        request.setAttribute("err1", err);
                        dispath(request, response, "/Register.jsp");
                    }
                }
            }
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
