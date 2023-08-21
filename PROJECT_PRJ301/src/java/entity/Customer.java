/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author TRONG TUAN
 */
public class Customer {

    private int cid; //int primary key identity(1,1)
    private String cname, cphone, cAddress, username, password;
    private int status;
    private int NumberOfOrders;
    private double totalAll;

    public Customer() {
    }

    public Customer(int cid, String cname, String cphone, String cAddress, String username, String password, int status) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password, int status) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password, int status, int NumberOfOrders, double totalAll) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
        this.NumberOfOrders = NumberOfOrders;
        this.totalAll = totalAll;
    }

    public Customer(int cid, String cname, String cphone, String cAddress, String username, String password, int status, int NumberOfOrders, double totalAll) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
        this.NumberOfOrders = NumberOfOrders;
        this.totalAll = totalAll;
    }

    public int getNumberOfOrders() {
        return NumberOfOrders;
    }

    public void setNumberOfOrders(int NumberOfOrders) {
        this.NumberOfOrders = NumberOfOrders;
    }

    public double getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(double totalAll) {
        this.totalAll = totalAll;
    }

    

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", cphone=" + cphone + ", cAddress=" + cAddress + ", username=" + username + ", password=" + password + ", status=" + status + ", NumberOfOrders=" + NumberOfOrders + ", totalAll=" + totalAll + '}';
    }

}
