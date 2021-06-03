/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class reg_User extends User {
    
    private int userID;

    public reg_User(int userID, String username, String name, String password, int contactNo, String email, String Reg_Date, String Security_Question, String Answer) {
        super(userID, username, name, password, contactNo, email, Reg_Date, Security_Question, Answer);
    }    
    
    @Override
    public String toString() {
        return "reg_User{" + "userID=" + userID + ", username=" + username +  '}';
    }

    
    
    public void chooseGame(){
        
    }
    
    public void ChangeGame(){
    
    }
    
    public void setSession(){
    
    }
    
    public void pay(){
    
    }
    
    public void Book(){    
    }
    
    public void ChangeDetails(){
        
    }
}
