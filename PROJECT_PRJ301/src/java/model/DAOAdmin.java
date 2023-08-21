/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.admin;
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
public class DAOAdmin extends DBConnect {

    public boolean login(String username, String password) {
        String sql = "select * from admin where username=? and password=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<admin> getAdmin() {
        ArrayList<admin> arr = new ArrayList<admin>();
        String sql = "Select * from admin";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                admin ad = new admin(adminID, username, password);
                arr.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<admin> getAdmin(String user) {
        ArrayList<admin> arr = new ArrayList<admin>();
        String sql = "Select * from admin where username like '%" + user + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                admin ad = new admin(adminID, username, password);
                arr.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public admin getAd(String user){
        admin ad = new admin();
        String sql = "Select * from admin where username ='" + user + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                ad = new admin(adminID, username, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ad;
    }

    public int addAdmin(admin ad) {
        int n = 0;
        String sql = "insert into admin(username, password)" + "values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ad.getUsername());
            pre.setString(2, ad.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void changePassword(int adminID, String passwword) {
        //int n = 0;
        String sql = "update admin set password=? where adminID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, passwword);
            pre.setInt(2, adminID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public void removeAdmin(String adminID) {
        //int n = 0;
        String sql = "delete from admin where adminID = '" + adminID + "'";
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }
    
    public int checkLogin(String user, String pass) {
        int n = 0;
        String sqlCheck = "select password from admin where username = '" + user + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                if (pass.equals(rs1.getString(1))) {
                    n = 1;
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public boolean checkUser(String user) {
        String sql = "select * from admin where username='" + user + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
//        int n = dao.addAdmin(new admin("trongtuan11", "123456778"));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }

//        int n = dao.removeAdmin(2);
//        if (n > 0) {
//            System.out.println("Remove");
//        }
        ArrayList<admin> arr = dao.getAdmin();
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

    }
}
