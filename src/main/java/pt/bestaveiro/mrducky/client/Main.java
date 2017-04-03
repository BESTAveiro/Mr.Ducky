/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.util.concurrent.TimeUnit;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.mail.MailGenerator;

/**
 *
 * @author jeronimo
 */
public class Main {
    
    public static void main(String[] args) {               
                
        // Sent initial mail        
        MailGenerator jen = new MailGenerator();
        Configuration conf = Configuration.getInstance();
        jen.sendEmail(conf.getSenderEmail(), conf.getSenderPassword(), conf.getAdminsEmails(),
"[Mr.Ducky] Quack Quack I am on", "Hey dude I am online!");
        
        // Run anniversary process
        Tasks.sendAnniversaries();
    }
}
