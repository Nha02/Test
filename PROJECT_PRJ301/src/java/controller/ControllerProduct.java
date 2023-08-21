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
import model.DAOCategory;
import model.DAOProduct;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/ControllerProduct"})
public class ControllerProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOProduct dao = new DAOProduct();
            DAOCategory daoC = new DAOCategory();
            String action = request.getParameter("action");
            if (action == null) {
                action = "listAll";
            }
            if (action.equals("listAll")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rs = dao.getData("select * from Product");
                    String title = "Product List";
                    request.setAttribute("kq", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("option", "viewPro");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);
                } else {
                    String cate[] = request.getParameterValues("category");
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String fromQua = request.getParameter("fromQua");
                    String toQua = request.getParameter("toQua");
                    String fromPri = request.getParameter("fromPri");
                    String toPri = request.getParameter("toPri");
                    String status = request.getParameter("status");
                    String cateS = "", idS = "", nameS = "", fromQuaS = "", toQuaS = "", fromPriS = "", toPriS = "", statusS = "";
                    try {
                        if (cate.length == 0) {
                            cateS = " ";
                        } else {
                            cateS = " and cateID in (";
                            for (int i = 0; i < cate.length; i++) {
                                cateS += cate[i];
                                if (i == cate.length - 1) {
                                    cateS += ") ";
                                } else {
                                    cateS += ",";
                                }
                            }
                        }
                    } catch (NullPointerException e) {
                    }

                    if (id == null || id.equals("")) {
                        idS = " ";
                    } else {
                        idS = "and pid like '%" + id + "%' ";
                    }
                    if (name == null || name.equals("")) {
                        nameS = "";
                    } else {
                        nameS = "and pname like '%" + name + "%' ";
                    }
                    if (fromQua == null || fromQua.equals("")) {
                        fromQuaS = " ";
                    } else {
                        fromQuaS = "and quantity >=" + fromQua + " ";
                    }
                    if (toQua == null || toQua.equals("")) {
                        toQuaS = " ";
                    } else {
                        toQuaS = "and quantity <=" + toQua + " ";
                    }
                    if (fromPri == null || fromPri.equals("")) {
                        fromPriS = " ";
                    } else {
                        fromPriS = "and price >=" + fromPri + " ";
                    }
                    if (toPri == null || toPri.equals("")) {
                        toPriS = " ";
                    } else {
                        toPriS = "and price <=" + toPri + " ";
                    }
                    if (!status.equals("2")) {
                        statusS = "and status = " + status;
                    } else {
                        statusS = " ";
                    }

                    String sql = "select * from Product where price >= 0 "
                            + cateS + idS + nameS + fromQuaS + toQuaS
                            + fromPriS + toPriS + statusS;
                    ResultSet rs = dao.getData(sql);
                    String title = "Product List";
                    request.setAttribute("kq", rs);
                    request.setAttribute("title", title);
                    request.setAttribute("cateValue", cate);
                    request.setAttribute("idValue", id);
                    request.setAttribute("nameValue", name);
                    request.setAttribute("fromQuaValue", fromQua);
                    request.setAttribute("toQuaValue", toQua);
                    request.setAttribute("fromPriValue", fromPri);
                    request.setAttribute("toPriValue", toPri);
                    request.setAttribute("statusValue", status);
                    request.setAttribute("option", "viewPro");
                    // select view
                    RequestDispatcher disp
                            = request.getRequestDispatcher("/adminIndex.jsp");
                    // run
                    disp.forward(request, response);

                }
            }

            if (action.equals("search2")) {
                //String cateID = request.getParameter("cateIDS");
                String ram[] = request.getParameterValues("ram");
                String rom[] = request.getParameterValues("rom");
                String fromPri = request.getParameter("fromPri");
                String toPri = request.getParameter("toPri");
                String ramS = "", romS = "", fromPriS = "", toPriS = "";
//                try {
//                    if (cateID == null) {
//                        cateS = " ";
//                    } else {
//                        cID = cateID;
//                        cateS = " and cateID = " + cateID + " "; 
//                    }
//                } catch (NullPointerException e) {    
//                }
                try {
                    if (ram.length == 0) {
                        ramS = "";
                    } else {
                        ramS = " and (";
                        for (int i = 0; i < ram.length; i++) {
                            ramS = ramS + "description like '%Ram: " + ram[i] + "%' ";
                            if (i == ram.length - 1) {
                                ramS += ") ";
                            } else {
                                ramS += " or ";
                            }
                        }
                    }
                } catch (NullPointerException e) {
                }
                try {
                    if (rom.length == 0) {
                        romS = "";
                    } else {
                        romS = " and (";
                        for (int j = 0; j < rom.length; j++) {
                            romS = romS + "description like '%Rom: " + rom[j] + "%' ";
                            if (j == rom.length - 1) {
                                romS += ") ";
                            } else {
                                romS += " or ";
                            }
                        }
                    }
                } catch (NullPointerException e) {
                }
                if (fromPri == null || fromPri.equals("")) {
                    fromPriS = " ";
                } else {
                    fromPriS = "and price >=" + fromPri + " ";
                }
                if (toPri == null || toPri.equals("")) {
                    toPriS = " ";
                } else {
                    toPriS = "and price <=" + toPri + " ";
                }
                String sql = "select * from Product where  pname like '%%' "
                        + ramS + romS + fromPriS + toPriS;
//               request.setAttribute("cateValue", cID);
                request.setAttribute("ramValue", ram);
                request.setAttribute("romValue", rom);
                request.setAttribute("fromPriValue", fromPri);
                request.setAttribute("toPriValue", toPri);
                ArrayList<Product> list = dao.search2(sql);
                request.setAttribute("listP", list);
                ArrayList<Category> listC = daoC.getCategory();
                request.setAttribute("listC", listC);
                request.setAttribute("action", "index");
                // select view
                RequestDispatcher disp
                        = request.getRequestDispatcher("/index.jsp");
                // run
                disp.forward(request, response);
            }

            if (action.equals("searchU")) {
                request.setCharacterEncoding("UTF-8");
                String search = request.getParameter("search");
                ArrayList<Product> list = dao.searchByName(search);
                request.setAttribute("listP", list);
                request.setAttribute("valueSearch", search);
                request.setAttribute("action", "index");
                ArrayList<Category> listC = daoC.getCategory();
                request.setAttribute("listC", listC);
                dispath(request, response, "index.jsp");
            }

            if (action.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// pre-update
                    String id = request.getParameter("id");
                    ResultSet rs = dao.getData("select * from Product where pid='" + id + "'");
                    request.setAttribute("rs", rs);
                    ResultSet rsCate = dao.getData("select * from Category");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("option", "updatePro");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    //code update here
                    String pid = request.getParameter("id");
                    String pname = request.getParameter("pname");
                    String quantity = request.getParameter("quantity");
                    String price = request.getParameter("price");
                    String image = request.getParameter("image");
                    String description = request.getParameter("des");
                    String status = request.getParameter("status");
                    String cateID = request.getParameter("cate");
                    //check data
                    //covert
                    int quan = Integer.parseInt(quantity);
                    double pri = Double.parseDouble(price);
                    int sta = Integer.parseInt(status);
                    int cID = Integer.parseInt(cateID);
                    //entity
                    Product pro = new Product(pid, pname, quan, pri, image, description, sta, cID);
                    dao.updateProduct(pro);
                    response.sendRedirect("ControllerProduct");
                    //dispath(request, response, "/ControllerProduct");
                }
            }
            if (action.equals("delete")) {
                String pid = request.getParameter("id");
                dao.removeProduct(pid);
                response.sendRedirect("ControllerProduct");
            }
            if (action.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsCate = dao.getData("select * from Category");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("option", "insertPro");
                    dispath(request, response, "/adminIndex.jsp");
                } else {
                    //code update here
                    String pid = request.getParameter("id");
                    String pname = request.getParameter("pname");
                    String quantity = request.getParameter("quantity");
                    String price = request.getParameter("price");
                    String image = request.getParameter("image");
                    String description = request.getParameter("des");
                    String status = request.getParameter("status");
                    String cateID = request.getParameter("cate");
                    //check data
                    //covert
                    int quan = Integer.parseInt(quantity);
                    double pri = Double.parseDouble(price);
                    int sta = Integer.parseInt(status);
                    int cID = Integer.parseInt(cateID);
                    if (dao.checkPID(pid) == true) {
                        //entity
                        Product pro = new Product(pid, pname, quan, pri, image, description, sta, cID);
                        dao.insertProduct(pro);
                        response.sendRedirect("ControllerProduct");
                    } else {
                        ResultSet rsCate = dao.getData("select * from Category");
                        request.setAttribute("rsCate", rsCate);
                        request.setAttribute("option", "insertPro");
                        request.setAttribute("messErr", "Duplicate Product ID!");
                        request.setAttribute("pname", pname);
                        request.setAttribute("quan", quantity);
                        request.setAttribute("price", price);
                        request.setAttribute("image", image);
                        request.setAttribute("desc", description);
                        request.setAttribute("cate", cateID);
                        dispath(request, response, "/adminIndex.jsp");
                    }
                    //dispath(request, response, "/ControllerProduct");
                }
            }
        } catch(NullPointerException ex){
            
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
