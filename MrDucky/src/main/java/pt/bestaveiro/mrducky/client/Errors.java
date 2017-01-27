/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.util.Arrays;
import java.util.List;
import pt.bestaveiro.mrducky.mail.MailGenerator;

/**
 *
 * @author jeronimo
 */
public class Errors {
    
    public static void sendError(String sender, String password, String description, Exception ex) {
        
        // Send e-mail with error to admin
        MailGenerator mailGen = new MailGenerator();
        List<String> list = Arrays.asList(Constants.ADMIN.split(";"));
        mailGen.sendEmail(sender, password, list, "[Mr. Ducky][Error] Quack Quack Error :(", 
                description + "<br/>" + ex.getMessage());

        // Print stack trace
        ex.printStackTrace();        
    }
    
}
