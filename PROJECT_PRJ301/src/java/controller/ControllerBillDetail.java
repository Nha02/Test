/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBill;
import model.DAOBillDetail;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "ControllerBillDetail", urlPatterns = {"/ControllerBillDetail"})
public class ControllerBillDetail extends HttpServlet {

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
            DAOBill daoB = new DAOBill();
            DAOBillDetail dao = new DAOBillDetail();
            String action = request.getParameter("action");
            if (action == null) {
                action = "detail";
            }
            if (action.equals("detail")) {
                // model
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String oid = request.getParameter("oID");
                    ResultSet rs = dao.getData("select * from BillDetail where oID='" + oid + "'");
                    String title = "Bill Detail";
                    request.setAttribute("oid", oid);
                    request.setAttribute("rs", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("mess", "");
                    request.setAttribute("option", "detailBill");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String status = request.getParameter("status");
                    String oid = request.getParameter("oID");
                    int sta = Integer.parseInt(status);
                    daoB.changeStatus(oid, sta);
                    ResultSet rs = dao.getData("select * from BillDetail where oID='" + oid + "'");
                    String title = "Bill Detail";
                    request.setAttribute("oid", oid);
                    request.setAttribute("rs", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("mess", "Status update successfully");
                    request.setAttribute("option", "detailBill");
                    dispath(request, response, "adminIndex.jsp");
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
