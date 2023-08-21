/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
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
public class DAOCategory extends DBConnect { //Database Access Object
//    DBConnect dbconn;
//    Connection conn;
//    
//    public DAOCategory(DBConnect dbconn){
//        this.dbconn = dbconn;
//        conn = dbconn.conn;
//    }

//    public int addCategory(Category cate) {
//        int n = 0;
//        String sql = "insert into Category values('" + cate.getCateName() + "', " + cate.getStatus() + ")";
//        System.out.println(sql);
//
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }
    public int addCategory(Category cate) {
        int n = 0;
        String sql = "insert into Category(cateName, status)" + "values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "Select * from Category";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Category> getName(String name) {
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "Select * from Category where cateName like '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public void changeStatus(int id, int status) {
        String sql = "update Category set status = ? where cateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeCategory(int id) {
        int n = 0;
        String sqlCheck = "select * from Product where cateID = '" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                changeStatus(id, 0);
                n = -1;
            } else {
                String sql = "delete from Category where cateID = '" + id + "'";
                Statement state = conn.createStatement();
                state.executeUpdate(sql);
                n = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateCategory(Category cate) {
        //int n = 0;
        String sql = "update Category set cateName=?, status=? where cateID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            pre.setInt(3, cate.getCateID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public int checkCatecheck(int id, String[] cate) {
        int a = 0;
        String idS = "" + id;
        try {
            for (String cate1 : cate) {
                if (cate1.equals(idS)) {
                    a = 1;
                }
            } 
        } catch (NullPointerException e) {
        }
        return a;
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        int n = dao.addCategory(new Category(2, "Sony2", 1));
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}
