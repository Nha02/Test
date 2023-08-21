/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import entity.Customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRONG TUAN
 */
public class DAOBill extends DBConnect {

    public int addBill(Bill bill) {
        int n = 0;
        String sql = "insert into Bill(oID, cname, cphone, cAddress, total, status, cid)"
                + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getoID());
            pre.setString(2, bill.getCname());
            pre.setString(3, bill.getCphone());
            pre.setString(4, bill.getcAddress());
            pre.setDouble(5, bill.getTotal());
            pre.setInt(6, bill.getStatus());
            pre.setInt(7, bill.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Bill> getBill() {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Bill> getName(String name) {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill where cname like '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public String createOID() {
        java.util.Date date=new java.util.Date();  
        Format formatter = new SimpleDateFormat("yyMMddHHmmss");
        String oID = formatter.format(date);
        return oID;
    }

    public int doneBill(){
        int n = 0;
        String sql = "select COUNT(oID) from Bill where status = 3";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Bill getBill(String oid) {
        Bill bill = new Bill();
        String sql = "Select * from Bill where oID='" + oid + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill;
    }

    public String getDate(String oid) {
        String dateCreate = null;
        String sql = "Select dateCreate from Bill where oID ='" + oid + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                dateCreate = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateCreate;
    }
    
    public int getCusId (String oid) {
        int id = 0;
        String sql1 = "select cid from Bill where oID = '" + oid + "'";
        ResultSet rs1 = getData(sql1);
        try {
            if (rs1.next()) {
                id = rs1.getInt("cid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public double getTotal (String oid) {
        double t = 0;
        String sql1 = "select total from Bill where oID = '" + oid + "'";
        ResultSet rs1 = getData(sql1);
        try {
            if (rs1.next()) {
                t = rs1.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public ArrayList<Bill> getPhone(String phone) {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill where cphone like '%" + phone + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Bill> getDate(String from, String to) {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill where dateCreate between '" + from + "' and '" + to + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Bill> getNameInDate(String name, String from, String to) {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill where dateCreate between '" + from + "' and '" + to + "'"
                + "and cname like '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Bill> getTotal(double from, double to) {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "Select * from Bill where total between '" + from + "' and '" + to + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public void updateBill(Bill bill) {
        //int n = 0;
        String sql = "update Bill set cname=?, cphone=?, cAddress=?, status=? where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getCname());
            pre.setString(2, bill.getCphone());
            pre.setString(3, bill.getcAddress());
            pre.setInt(4, bill.getStatus());
            pre.setString(5, bill.getoID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public void updateTotal(String oID, double total) {
        //int n = 0;
        String sql = "update Bill set total=? where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, total);
            pre.setString(2, oID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public void changeStatus(String id, int status) {
        String sql = "update Bill set status = ? where oID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeBill(String id) {
        int n = 0;
        String sqlCheck = "select * from BillDetail where oID = '" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                changeStatus(id, 0);
                n = -1;
            } else {
                String sql = "delete from Bill where oID = '" + id + "'";
                Statement state = conn.createStatement();
                state.executeUpdate(sql);
                n = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
        System.out.println(dao.getCusId("2024211031163300"));
        System.out.println(dao.doneBill());
    }
}
