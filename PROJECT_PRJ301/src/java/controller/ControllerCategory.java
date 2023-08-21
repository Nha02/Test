/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOAdmin;
import model.DAOCategory;
import model.DAOProduct;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "ControllerCategory", urlPatterns = {"/ControllerCategory"})
public class ControllerCategory extends HttpServlet {

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
            DAOCategory dao = new DAOCategory();
            DAOProduct daoP = new DAOProduct();
            String action = request.getParameter("action");
            if (action == null) {
                action = "listAll";
            }
            if (action.equals("listAll")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    // model
                    ResultSet rs = dao.getData("select * from Category");
                    String title = "Category List";
                    request.setAttribute("kqCate", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("option", "viewCate");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String id = request.getParameter("idCate");
                    String name = request.getParameter("nameCate");
                    String status = request.getParameter("statusCate");
                    String idS = "", nameS = "", statusS = "";
                    if (id == null || id.equals("")) {
                        idS = " ";
                    } else {
                        idS = "and cateID like '%" + id + "%' ";
                    }
                    if (name == null || name.equals("")) {
                        nameS = " ";
                    } else {
                        nameS = "and cateName like '%" + name + "%' ";
                    }
                    if (!status.equals("2")) {
                        statusS = "and status = " + status;
                    } else {
                        statusS = " ";
                    }
                    String sql = "select * from Category where cateName like '%%' "
                            + idS + nameS + statusS;
                    ResultSet rs = dao.getData(sql);
                    String title = "Category List";
                    request.setAttribute("kqCate", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("idCateValue", id);
                    request.setAttribute("nameCateValue", name);
                    request.setAttribute("statusCateValue", status);
                    request.setAttribute("option", "viewCate");
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
                    String cateID = request.getParameter("cateID");
                    int id = Integer.parseInt(cateID);
                    ResultSet rs = dao.getData("select * from Category where cateID=" + id);
                    request.setAttribute("rs", rs);
                    request.setAttribute("option", "updateCate");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    String cateName = request.getParameter("cateName");
                    String status = request.getParameter("status");
                    String cateID = request.getParameter("cateID");
                    //check data
                    //covert
                    int id = Integer.parseInt(cateID);
                    int sta = Integer.parseInt(status);
                    //entity
                    if (cateName.equals("") || cateName == null) {
                        out.println("<h5>Cate Name is not empty</h5>");
                    } else {
                        if (cateName.length() > 50) {
                            out.println("<h5>Cate Name can't longer than 50</h5>");
                        } else {
                            Category cate = new Category(id, cateName, sta);
                            dao.updateCategory(cate);
                            response.sendRedirect("ControllerCategory");
                        }
                    }
                }
            }

            if (action.equals("delete")) {
                String cateID = request.getParameter("cateID");
                int id = Integer.parseInt(cateID);
                dao.removeCategory(id);
                response.sendRedirect("ControllerCategory");
            }
            if (action.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.setAttribute("option", "insertCate");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    String cateN = request.getParameter("cateName");
                    String status = request.getParameter("status");

                    int sta = Integer.parseInt(status);
                    //entity
                    Category cate = new Category(cateN, sta);
                    dao.addCategory(cate);
                    response.sendRedirect("ControllerCategory");
                }
            }
            if (action.equals("getProduct")) {
                String cateID = request.getParameter("cateID");
                int id = Integer.parseInt(cateID);
                ArrayList<Category> listC = dao.getCategory();
                request.setAttribute("listC", listC);
                ArrayList<Product> list = daoP.getProductByCate(id);
                request.setAttribute("listP", list);
                request.setAttribute("flag", id);
                request.setAttribute("action", "index");
                dispath(request, response, "index.jsp");
            }

        } catch (NumberFormatException e) {
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
