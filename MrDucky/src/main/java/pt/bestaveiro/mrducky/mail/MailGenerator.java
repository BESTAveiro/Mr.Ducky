/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jeronimo
 */
public class MailGenerator {
    
    private Properties mailServerProperties;

    public MailGenerator() {
        initialize();
    }
    
    private void initialize() {
        
        // Set mail server properties
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
    }
    
    public boolean sendEmail(String recipient, String subject, String content) {
        
        // Create email session
        Session mailSession = Session.getDefaultInstance(mailServerProperties, null);
        
        try {
            // Create email
            MimeMessage mail = new MimeMessage(mailSession);
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            mail.setSubject(subject);
            mail.setContent(content, "text/html");
            
            // Send email
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "mr.ducky.aveiro@gmail.com", 
                    "valentinaaveiro");
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
                
        } catch(MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    
}
