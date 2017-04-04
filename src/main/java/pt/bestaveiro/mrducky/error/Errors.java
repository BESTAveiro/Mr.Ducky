/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.mail.MailGenerator;

/**
 *
 * @author jeronimo
 */
public class Errors {
    
    public static void sendError(String message, Exception ex) {
        
        // Get configuration
        Configuration conf = Configuration.getInstance();
        
        // Instantiate a mail generator
        MailGenerator mailGenerator = new MailGenerator();
        
        // Subject and content
        String subject = "[Mr. Ducky][Anniversary][Error] " + message;
        String content = ex.getMessage() + "<br/><br/>" + ExceptionUtils.getStackTrace(ex);

        mailGenerator.sendEmail(conf.getSenderEmail(), conf.getSenderName(), conf.getSenderPassword(),
                conf.getAdminsEmails(), subject, content); 
        
        System.out.println(subject + "\n\n" + content);
    }    
}
