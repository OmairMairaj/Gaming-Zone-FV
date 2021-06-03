/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

import projdemo.Pages.Login_Page;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Owner
 */
public class ProjDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try{
             UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        Login_Page lp = new Login_Page();
        lp.setVisible(true);
        
    }
    
}
