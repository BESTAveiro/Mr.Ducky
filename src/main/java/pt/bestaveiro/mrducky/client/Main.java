/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jeronimo
 */
public class Main {
    
    public static void main(String[] args) {               
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
        
        // Run anniversary process
        Tasks.sendAnniversaries();
    }
}
