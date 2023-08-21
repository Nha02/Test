/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRONG TUAN
 */
public class DAOBillDetail extends DBConnect {

    public void addBillDetail(BillDetail bD) {
        //int n = 0;
        String sql = "insert into BillDetail(pid, oID, quantity, price, total)"
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bD.getPid());
            pre.setString(2, bD.getoID());
            pre.setInt(3, bD.getQuantity());
            pre.setDouble(4, bD.getPrice());
            pre.setDouble(5, bD.getTotal());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public ArrayList<BillDetail> getBillDetail() {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        String sql = "Select * from BillDetail";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                double total = rs.getDouble(5);
                BillDetail bD = new BillDetail(pid, oID, quantity, price, total);
                arr.add(bD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<BillDetail> getoID(String id) {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        String sql = "select * from BillDetail where oID = '" + id + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                double total = rs.getDouble(5);
                BillDetail bD = new BillDetail(pid, oID, quantity, price, total);
                arr.add(bD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<BillDetail> getoIDpid(String o, String p) {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        String sql = "select * from BillDetail where oID = '" + o + "'"
                + "and pid = '" + p + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                double total = rs.getDouble(5);
                BillDetail bD = new BillDetail(pid, oID, quantity, price, total);
                arr.add(bD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public int updateBillDetail(BillDetail bD) {
        int n = 0;
        String sql = "update BillDetail set quantity=?, price=?, total=? where pid=? and oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bD.getQuantity());
            pre.setDouble(2, bD.getPrice());
            pre.setDouble(3, bD.getTotal());
            pre.setString(4, bD.getPid());
            pre.setString(5, bD.getoID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateQuantity(String pid, String oid, int quan) {
        String sql = "update BillDetail set quantity = quantity + ? where pid = ? and oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quan);
            pre.setString(2, pid);
            pre.setString(3, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePrice(String pid, String oid, double price) {
        String sql = "update BillDetail set price = price + ? where pid = ? and oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, price);
            pre.setString(2, pid);
            pre.setString(3, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeBillDetail(String oid) {
        String sql = "delete from BillDetail where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        //int n = dao.addBillDetail(new BillDetail("P01", "1", 1, 30, 90));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }
    }
}
