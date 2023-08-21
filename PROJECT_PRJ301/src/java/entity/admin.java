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
public class admin {
    private int adminID;
    private String username, password;

    public admin() {
    }

    public admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public admin(int adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    @Override
    public String toString() {
        return "admin{" + "adminID=" + adminID + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
