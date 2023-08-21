/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRONG TUAN
 */
public class DAOProduct extends DBConnect {
//    DBConnect dbConn;
//    Connection connect;
//
//    public DAOProduct(DBConnect dbconn) {
//        this.dbConn=dbconn;
//        connect=dbconn.conn;
//    }
//    DAO: insert, update,delete,search(select)

    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID) "
                + "values('" + pro.getPid() + "','" + pro.getPname() + "'," + pro.getQuantity()
                + "," + pro.getPrice() + ",'" + pro.getImage() + "','" + pro.getDescription() + "'," + pro.getStatus() + ","
                + pro.getCateID() + ")";
        // System.out.println(sql);
        try {
            //Statemetn
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID) "
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            set parameter ?
//            pre.setDataType(index,value);
//            DataType is datatype of field (attribute of table)
//            index of position of ? start 1
//            value is value parameter (pro)
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            //exec
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Product getProduct(String pid) {
        String sql = "Select * from Product where pid='" + pid + "'";
        ResultSet rs = getData(sql);
        Product pro = new Product();
        try {
            while (rs.next()) {
                pro.setPid(rs.getString(1));
                pro.setPname(rs.getString(2));
                pro.setQuantity(rs.getInt(3));
                pro.setPrice(rs.getDouble(4));
                pro.setImage(rs.getString(5));
                pro.setDescription(rs.getString(6));
                pro.setStatus(rs.getInt(7));
                pro.setCateID(rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pro;
    }

    public void updateProduct(Product pro) {
        int n = 0;
        String sql = "update Product set pname=?,quantity=?,price=?,image=?,description=?,"
                + "status=?,cateID=? where pid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(8, pro.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public void displayAll() {
        String sql = "Select * from Product";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid");// String pid=rs.getString(1);
                String pname = rs.getString(2);//String pname=rs.getString("pname")
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString("image");
                String des = rs.getString("description");
                int status = rs.getInt("status");
                int cateid = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, des, status, cateid);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "Select * from Product";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getInt("status") == 1 && rs.getInt("quantity") > 0) {
                    String pid = rs.getString("pid");// String pid=rs.getString(1);
                    String pname = rs.getString(2);//String pname=rs.getString("pname")
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    String image = rs.getString("image");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt(8);
                    Product pro = new Product(pid, pname, quantity, price, image, des, status, cateid);
                    arr.add(pro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Product> search2(String sql) {
        ArrayList<Product> arr = new ArrayList<Product>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getInt("status") == 1) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString(2);
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    String image = rs.getString("image");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt(8);
                    Product pro = new Product(pid, pname, quantity, price, image, des, status, cateid);
                    arr.add(pro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Product> getProductByCate(int cateID) {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product where cateID=" + cateID;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getInt("status") == 1 && rs.getInt("quantity") > 0) {
                    String pid = rs.getString("pid");// String pid=rs.getString(1);
                    String pname = rs.getString(2);//String pname=rs.getString("pname")
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    String image = rs.getString("image");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    Product pro = new Product(pid, pname, quantity, price, image, des, status, cateID);
                    arr.add(pro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Product> searchByName(String txt) {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product where pname like '%" + txt + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getInt("status") == 1) {
                    String pid = rs.getString("pid");// String pid=rs.getString(1);
                    String pname = rs.getString(2);//String pname=rs.getString("pname")
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    String image = rs.getString("image");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateID = rs.getInt("cateID");
                    Product pro = new Product(pid, pname, quantity, price, image, des, status, cateID);
                    arr.add(pro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException t) {
        }
        return arr;
    }

    public ResultSet Price(double from, double to) {
        String sql = "Select * from Product where price between " + from + " and " + to;
        return getData(sql);
    }

    public ResultSet Name(String name) {
        String sql = "Select * from Product where pname like '%" + name + "%'";
        return getData(sql);
    }

    public ArrayList<Product> getPrice(double from, double to) {
        ArrayList<Product> arr = new ArrayList<Product>();
        ResultSet rs = Price(from, to);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid");// String pid=rs.getString(1);
                String pname = rs.getString(2);//String pname=rs.getString("pname")
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString("image");
                String des = rs.getString("description");
                int status = rs.getInt("status");
                int cateid = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, des, status, cateid);
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public boolean checkPID(String pid) {
        String sql = "select * from Product where pid='" + pid + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<Product> getName(String name) {
        ArrayList<Product> arr = new ArrayList<Product>();
        ResultSet rs = Name(name);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid");// String pid=rs.getString(1);
                String pname = rs.getString(2);//String pname=rs.getString("pname")
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString("image");
                String des = rs.getString("description");
                int status = rs.getInt("status");
                int cateid = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, des, status, cateid);
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public void updateQuantity(String id, int quan) {
        String sql = "update Product set quantity = quantity + ? where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quan);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeStatus(String id, int status) {
        String sql = "update Product set status = ? where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeProduct(String id) {
        //check bien n co foreign key
        String sqlCheck = "select * from BillDetail where pid='" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                changeStatus(id, 0);
            } else {
                String sql = "delete from Product where pid='" + id + "'";
                Statement state = conn.createStatement();
                state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkQuan(String id, int quan) {
        int n = 0;
        String sqlCheck = "select * from Product where pid='" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                if (quan > rs1.getInt(3)) {
                    n = 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getQuan(String id) {
        int n = 0;
        String sqlCheck = "select quantity from Product where pid='" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                n = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String getPName(String id) {
        String n = "";
        String sqlCheck = "select pname from Product where pid='" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                n = rs1.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void setNewQuan(String id, int quan, String dau) {
        String sql = "update Product set quantity = ? where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            if (dau.equals("+")) {
                pre.setInt(1, getQuan(id) + quan);
            } else {
                pre.setInt(1, getQuan(id) - quan);
            }
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelBill(String oID){
        String sqlCheck = "select pid, quantity from BillDetail where oID='" + oID + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            while(rs1.next()) {
                setNewQuan(rs1.getString(1), rs1.getInt(2), "+");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkbox(String s, String[] str) {
        int a = 0;
        try {
            for (String i : str) {
                if (i.equals(s)) {
                    a = 1;
                }
            }
        } catch (NullPointerException e) {
        }
        return a;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        int n=dao.insertProduct(new Product("P08","HP G6",2, 500,"no image","second hand",1,1));
//        if(n>0)
//            System.out.println("inserted");
//        int n = dao.addProduct(new Product("P07", "HP G7", 3, 600, "no image", "second hand", 1, 1));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
//
////        int t = dao.updateProduct(new Product("P03", "HP G7", 3, 700, "no image", "second hand", 1, 1));
////        if (t > 0) {
////            System.out.println("updated");
////        }
//        int t = dao.removeProduct("P07");
//        if (t > 0) {
//            System.out.println("Remove Product");
//        }
//        dao.displayAll();
//        ArrayList<Product> arr = dao.getProduct();
//        for(int i = 0; i<arr.size(); i++){
//            System.out.println(arr.get(i));
//        }
    }
}
