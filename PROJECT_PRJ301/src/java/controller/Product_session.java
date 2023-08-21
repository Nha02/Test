/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Category;
import entity.Customer;
import entity.Product;
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
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCategory;
import model.DAOCustomer;
import model.DAOProduct;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "Product_session", urlPatterns = {"/Product_session"})
public class Product_session extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            DAOCategory daoC = new DAOCategory();
            DAOBill daoBill = new DAOBill();
            DAOBillDetail daoDetail = new DAOBillDetail();
            DAOCustomer daoCus = new DAOCustomer();
            String action = request.getParameter("action");
            if (action == null) {
                action = "listAll";
            }
            if (action.equals("listAll")) {
                // model
                ResultSet rs = dao.getData("select * from Product");
                ResultSet rsCate = dao.getData("select * from Category");
                // some information
                String title = "Product List";
                // data view
                request.setAttribute("rsCate", rsCate);
                request.setAttribute("kq", rs);
                request.setAttribute("title", title);
                // select view
                RequestDispatcher disp
                        = request.getRequestDispatcher("/productList.jsp");
                // run
                disp.forward(request, response);
            }
            if (action.equals("addToCart")) {
                String id = request.getParameter("pid");
                if (id != null) {
                    Product pro = (Product) session.getAttribute(id);
                    if (pro == null) {
                        Product pro1 = dao.getProduct(id);
                        pro1.setQuantity(1);
                        session.setAttribute(id, pro1);
                    } else {
                        pro.setQuantity(pro.getQuantity() + 1);
                    }
                }
//                RequestDispatcher disp
//                        = request.getRequestDispatcher("/showCart.jsp");
//
//                disp.forward(request, response)
                response.sendRedirect("HomeController");
            }

            if (action.equals("showCart")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    DAOProduct daoP = new DAOProduct();
                    ArrayList<Product> listP = daoP.getProduct();
                    request.setAttribute("listP", listP);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("action", "showCart");
                    dispath(request, response, "index.jsp");
                } else {
                    java.util.Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String id = em.nextElement().toString();
                        if (!id.equals("user") && !id.equals("accAd")) {
                            Product pro = (Product) session.getAttribute(id);
                            String quantity = request.getParameter(id);
                            int q = Integer.parseInt(quantity);
                            if (dao.checkQuan(id, q) == 0) {
                                pro.setQuantity(q);
                            } else {
                                String m = "mess" + id;
                                request.setAttribute(m, "Maximum quantity of " + dao.getPName(id) + " is " + dao.getQuan(id) + " pcs");
                            }
                        }
                    }
                    DAOProduct daoP = new DAOProduct();
                    ArrayList<Product> listP = daoP.getProduct();
                    request.setAttribute("listP", listP);
                    ArrayList<Category> listC = daoC.getCategory();
                    request.setAttribute("listC", listC);
                    request.setAttribute("action", "showCart");
                    dispath(request, response, "index.jsp");
                }
            }

            if (action.equals("remove")) {
                String pid = request.getParameter("pid");
                session.removeAttribute(pid);
                //dispath(request, response, "/showCart.jsp");
                response.sendRedirect("Product_session?action=showCart");
            }
            if (action.equals("removeAll")) {
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (!id.equals("user") && !id.equals("accAd")) {
                        session.removeAttribute(id);
                    }
                }
                //dispath(request, response, "/showCart.jsp");
                response.sendRedirect("Product_session?action=showCart");
            }

            if (action.equals("checkout")) {
                Customer cus = (Customer) session.getAttribute("user");
                int count = 0;
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (!id.equals("user") && !id.equals("admin")) {
                        count = 1;
                    }
                }
                if (cus == null || count == 0) {
                    if (cus == null) {
                        request.setAttribute("act", "checkout");
                        dispath(request, response, "/login.jsp");
                    } else {
                        response.sendRedirect("Product_session?action=showCart");
                    }
                } else {
                    double totalAll = 0;
                    int cusid = cus.getCid();
                    String oid = cusid + daoBill.createOID();
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        DAOProduct daoP = new DAOProduct();
                        ArrayList<Product> listP = daoP.getProduct();
                        request.setAttribute("listP", listP);
                        ArrayList<Category> listC = daoC.getCategory();
                        request.setAttribute("listC", listC);
                        request.setAttribute("action", "checkout");
                        dispath(request, response, "/index.jsp");
                    } else {
                        //create bill
                        String cname = request.getParameter("name");
                        String cphone = request.getParameter("phone");
                        String cAddress = request.getParameter("address");
                        int status = 1;
                        Bill bi = new Bill(oid, cname, cphone, cAddress, totalAll, status, cusid);
                        daoBill.addBill(bi);
                        //add to bill detail
                        java.util.Enumeration t = session.getAttributeNames();
                        while (t.hasMoreElements()) {
                            String id = t.nextElement().toString();
                            if (!id.equals("user") && !id.equals("accAd")) {
                                Product pro = (Product) session.getAttribute(id);
                                int quan = pro.getQuantity();
                                double price = pro.getPrice();
                                double total = quan * price;
                                totalAll = total + totalAll;
                                BillDetail de = new BillDetail(id, oid, quan, price, total);
                                daoDetail.addBillDetail(de);
                                //setNewQuantity
                                dao.setNewQuan(id, quan, "-");
                                //delete product from cart
                                session.removeAttribute(id);
                            }
                        }
                        daoBill.updateTotal(oid, totalAll);
                        daoCus.setTotalAll(cusid, totalAll, "+");
                        daoCus.setOrders(cusid, daoCus.getOrders(cusid));
                        response.sendRedirect("Product_session?action=showBill&oID=" + oid);
                    }
                }
            }
            if (action.equals("showBill")) {
                String oid = request.getParameter("oID");
                ResultSet rs = dao.getData("select * from BillDetail where oID='" + oid + "'");
                String title = "Bill Detail";
                request.setAttribute("oid", oid);
                request.setAttribute("rs", rs);
                request.setAttribute("title", title);
                ArrayList<Category> listC = daoC.getCategory();
                request.setAttribute("listC", listC);
                request.setAttribute("action", "viewOrder");
                dispath(request, response, "/index.jsp");
            }
        } catch (NumberFormatException e) {
        } catch (NullPointerException ex) {
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
