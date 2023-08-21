/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
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
public class DAOCustomer extends DBConnect {

    public int addCustomer(Customer cus) {
        int n = 0;
        String sql = "insert into Customer(cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll)"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, cus.getNumberOfOrders());
            pre.setDouble(8, cus.getTotalAll());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "update Customer set cname=?,cphone=?,cAddress=?,username=?, password=?, status=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, cus.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateInfo(String name, String phone, String add, String cid) {
        int n = 0;
        String sql = "update Customer set cname=?,cphone=?,cAddress=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, phone);
            pre.setString(3, add);
            pre.setString(4, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Customer> getCustomer() {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "Select * from Customer";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                int NumberOfOrders = rs.getInt(8);
                double totalAll = rs.getDouble(9);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll);
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Customer> getName(String name) {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "Select * from Customer where cname like '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                int NumberOfOrders = rs.getInt(8);
                double totalAll = rs.getDouble(9);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll);
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public boolean checkUser(String user) {
        String sql = "Select * from Customer where username='" + user + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<Customer> getUsername(String user) {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "Select * from Customer where username like '%" + user + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                int NumberOfOrders = rs.getInt(8);
                double totalAll = rs.getDouble(9);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll);

                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public Customer getCus(String user) {
        Customer cus = new Customer();
        String sql = "Select * from Customer where username = '" + user + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                int NumberOfOrders = rs.getInt(8);
                double totalAll = rs.getDouble(9);
                cus = new Customer(cid, cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public Customer getCusByID(String id) {
        Customer cus = new Customer();
        String sql = "Select * from Customer where cid = '" + id + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                int NumberOfOrders = rs.getInt(8);
                double totalAll = rs.getDouble(9);
                cus = new Customer(cid, cname, cphone, cAddress, username, password, status, NumberOfOrders, totalAll);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public void changePassword(int id, String password) {
        //int n = 0;
        String sql = "update Customer set password=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return n;
    }

    public void changeStatus(int id, int status) {
        String sql = "update Customer set status = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeCustomer(int id) {
        int n = 0;
        String sqlCheck = "select * from Bill where cid = '" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                changeStatus(id, 0);
                n = -1;
            } else {
                String sql = "delete from Customer where cid = '" + id + "'";
                Statement state = conn.createStatement();
                state.executeUpdate(sql);
                n = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int checkLogin(String user, String pass) {
        int n = 0;
        String sqlCheck = "select password from Customer where username = '" + user + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                if (pass.equals(rs1.getString(1))) {
                    n = 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getID(String user) {
        int n = 0;
        String sqlCheck = "select cid from Customer where username = '" + user + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                n = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    

    public int getOrders(int id) {
        int n = 0;
        String sqlCheck = "select NumberOfOrders from Customer where cid = '" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                n = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void setOrders(int id, int num) {
        String sql = "update Customer set NumberOfOrders = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            num += 1;
            pre.setInt(1, num);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getTotalAll(int id) {
        double n = 0;
        String sqlCheck = "select totalAll from Customer where cid = '" + id + "'";
        ResultSet rs1 = getData(sqlCheck);
        try {
            if (rs1.next()) {
                n = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void setTotalAll(int id, double num, String dau) {
        String sql = "update Customer set totalAll = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            if (dau.equals("+")) {
                pre.setDouble(1, getTotalAll(id) + num);
            } else {
                pre.setDouble(1, getTotalAll(id) - num);
            }
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrdersCancel(int id, int num) {
        String sql = "update Customer set NumberOfOrders = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            num -= 1;
            pre.setInt(1, num);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();

    }
}
