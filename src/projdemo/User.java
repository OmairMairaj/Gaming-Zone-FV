/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

/**
 *
 * @author Owner
 */
import java.util.*;

public class User {
    protected int userID;
    protected String username;
    private String name;
    private String password;
    //private Login credentials;
    private int contactNo;
    private String email;
    private String Reg_Date;
    private String Security_Question;
    private String Answer;

    public User(int userID, String username, String name, String password, int contactNo, String email, String Reg_Date, String Security_Question, String Answer) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.contactNo = contactNo;
        this.email = email;
        this.Reg_Date = Reg_Date;
        this.Security_Question = Security_Question;
        this.Answer = Answer;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getReg_Date() {
        return Reg_Date;
    }

    public String getSecurity_Question() {
        return Security_Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReg_Date(String Reg_Date) {
        this.Reg_Date = Reg_Date;
    }

    public void setSecurity_Question(String Security_Question) {
        this.Security_Question = Security_Question;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", username=" + username + ", name=" + name + ", password=" + password + ", contactNo=" + contactNo + ", email=" + email + ", Reg_Date=" + Reg_Date + ", Security_Question=" + Security_Question + ", Answer=" + Answer + '}';
    }
    
    
    
    public void changeContactNo(int b){
        contactNo=b;
    }
}
