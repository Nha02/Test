/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
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
import model.DAOCustomer;
import model.DAOProduct;

/**
 *
 * @author TRONG TUAN
 */
@WebServlet(name = "ControllerBill", urlPatterns = {"/ControllerBill"})
public class ControllerBill extends HttpServlet {

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
            DAOBill dao = new DAOBill();
            DAOBillDetail daobd = new DAOBillDetail();
            DAOCustomer daoC = new DAOCustomer();
            DAOProduct daoP = new DAOProduct();
            String action = request.getParameter("action");
            if (action == null) {
                action = "listAll";
            }
            if (action.equals("listAll")) {
                String reset = request.getParameter("reset");
                String submit = request.getParameter("submit");
                if (reset == null) {
                } else {
                    submit = null;
                }
                if (submit == null) {
                    ResultSet rs = dao.getData("select * from Bill");
                    String title = "Bill List";
                    request.setAttribute("kqBill", rs);
                    request.setAttribute("titleBill", title);
                    request.setAttribute("option", "viewBill");
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    disp.forward(request, response);
                } else {
                    String id = request.getParameter("idBill");
                    String name = request.getParameter("nameBill");
                    String datef = request.getParameter("dateFrom");
                    String datet = request.getParameter("dateTo");
                    String totalf = request.getParameter("totalFrom");
                    String totalt = request.getParameter("totalTo");
                    String status = request.getParameter("statusBill");
                    String idS = "", nameS = "", datefS = "", datetS = "", totalfS = "", totaltS = "", statusS = "";

                    if (id == null || id.equals("")) {
                        idS = " ";
                    } else {
                        idS = "and oID like '%" + id + "%' ";
                    }
                    if (name == null || name.equals("")) {
                        nameS = "";
                    } else {
                        nameS = "and cname like '%" + name + "%' ";
                    }
                    if (datef == null || datef.equals("")) {
                        datefS = " ";
                    } else {
                        datefS = "and dateCreate >= '" + datef + "' ";
                    }
                    if (datet == null || datet.equals("")) {
                        datetS = " ";
                    } else {
                        datetS = "and dateCreate <= '" + datet + "' ";
                    }
                    if (totalf == null || totalf.equals("")) {
                        totalfS = " ";
                    } else {
                        totalfS = "and total >=" + totalf + " ";
                    }
                    if (totalt == null || totalt.equals("")) {
                        totaltS = " ";
                    } else {
                        totaltS = "and total <=" + totalt + " ";
                    }
                    if (!status.equals("0")) {
                        statusS = "and status = " + status;
                    } else {
                        statusS = " ";
                    }
                    String sql = "select * from Bill where oID like '%%' "
                            + idS + nameS + datefS + datetS
                            + totalfS + totaltS + statusS;
                    ResultSet rs = dao.getData(sql);
                    String title = "Bill List";
                    request.setAttribute("kqBill", rs);
                    request.setAttribute("titleBill", title);
                    request.setAttribute("idBillValue", id);
                    request.setAttribute("nameBillValue", name);
                    request.setAttribute("dateFromValue", datef);
                    request.setAttribute("dateToValue", datet);
                    request.setAttribute("totalFromValue", totalf);
                    request.setAttribute("totalToValue", totalt);
                    request.setAttribute("statusBillValue", status);
                    request.setAttribute("option", "viewBill");
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    disp.forward(request, response);
                }
            }

            if (action.equals("update")) {
                String submit = request.getParameter("submit");
                String id = request.getParameter("oID");
                ResultSet rs = dao.getData("select * from Bill where oID='" + id + "'");
                request.setAttribute("rs", rs);
                if (submit == null) {// pre-update

                    request.setAttribute("option", "updateBill");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    //code update here
                    String oID = request.getParameter("oID");
                    String name = request.getParameter("cname");
                    String phone = request.getParameter("cphone");
                    String address = request.getParameter("cAddress");
                    String status = request.getParameter("status");
                    //check data                    //covert
                    int sta = Integer.parseInt(status);
                    //entity
                    Bill bill = new Bill(oID, name, phone, address, sta);
                    dao.updateBill(bill);
                    response.sendRedirect("ControllerBill");
                    //dispath(request, response, "/ControllerProduct");
                }
            }
            if (action.equals("delete")) {
                String oID = request.getParameter("oID");
                int id = dao.getCusId(oID);
                double total = dao.getTotal(oID);
                daoP.cancelBill(oID);
                daobd.removeBillDetail(oID);
                dao.removeBill(oID);
                daoC.setTotalAll(id, total, "-");
                daoC.setOrdersCancel(id, daoC.getOrders(id));
                response.sendRedirect("ControllerCustomer?action=orderCus&cid=" + id);
            }

            if (action.equals("topOrders")) {
                String datef = request.getParameter("dateFrom");
                String datet = request.getParameter("dateTo");
                String datefS = "", datetS = "";

                if (datef == null || datef.equals("")) {
                    datefS = " ";
                } else {
                    datefS = "and dateCreate >= '" + datef + "' ";
                }
                if (datet == null || datet.equals("")) {
                    datetS = " ";
                } else {
                    datetS = "and dateCreate <= '" + datet + "' ";
                }

                String sql = "select Customer.cid, Customer.cname, Customer.cphone, Customer.cAddress, Customer.username, NumberOfOrders = count(Customer.cid), TotalAll = sum(total)\n"
                        + "from Customer inner join Bill on Customer.cid = Bill.cid\n"
                        + "where Customer.cid like '%%' "
                        + datefS + datetS
                        + "group by Customer.cid, Customer.cname, Customer.cphone, Customer.cAddress, Customer.username\n"
                        + "order by TotalAll desc";
                ResultSet rs = dao.getData(sql);
                String title = "TOP ORDERS";
                request.setAttribute("kqTop", rs);
                request.setAttribute("titleTop", title);
                request.setAttribute("dateFromValue", datef);
                request.setAttribute("dateToValue", datet);
                request.setAttribute("option", "viewTopOrders");
                RequestDispatcher disp
                        = request.getRequestDispatcher("/adminIndex.jsp");
                disp.forward(request, response);

            }

            if (action.equals("billDate")) {
                try {
                    String id = request.getParameter("cid");
                    String dateFrom = request.getParameter("datef");
                    String dateTo = request.getParameter("datet");
                    String datefS = "", datetS = "";

                    if (dateFrom == null || dateFrom.equals("")) {
                        datefS = " ";
                    } else {
                        datefS = "and dateCreate >= '" + dateFrom + "' ";
                    }
                    if (dateTo == null || dateTo.equals("")) {
                        datetS = " ";
                    } else {
                        datetS = "and dateCreate <= '" + dateTo + "' ";
                    }
                    ResultSet rs = dao.getData("select * from Bill where cid =" + id + " " + datefS + datetS);
                    String title = "Bill List - Customer ID: " + id;
                    request.setAttribute("cid", id);
                    request.setAttribute("kqBill", rs);
                    request.setAttribute("titleBill", title);
                    request.setAttribute("dateFromValue", dateFrom);
                    request.setAttribute("dateToValue", dateTo);
                    request.setAttribute("option", "billDate");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                } catch (NullPointerException ex) {

                }
            }
            
            if (action.equals("done_Bill")) {
                String sql = "select * from Bill where status = 3 ";
                ResultSet rs = dao.getData(sql);
                String title = "Bill List";
                request.setAttribute("kqBill", rs);
                request.setAttribute("titleBill", title);
                String status = "3";
                request.setAttribute("statusBillValue", status);
                request.setAttribute("option", "viewBill");
                RequestDispatcher disp
                        = request.getRequestDispatcher("/adminIndex.jsp");
                disp.forward(request, response);
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
