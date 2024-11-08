/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.File;
import javax.swing.JOptionPane;
import pk_dababase.InventoryUtils;

/**
 *
 * @author G.A
 */
public class OpenPdf {
    
    public static void openById(String id){
        try{
            if(new File(InventoryUtils.billPath + id +".pdf").exists()){
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + InventoryUtils.billPath+"" + id +".pdf");
            }else{
                JOptionPane.showMessageDialog(null, "File is not Exists.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
                    
        }
    }
    
}
