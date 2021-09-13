/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.mail;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
        mailServerProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mailServerProperties.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    }
    
    public boolean sendEmail(String senderEmail, String senderName, String senderPassword, 
            List<String> recipientsEmails, String mailSubject, 
            String mailContent) {
        
        // Create email session
        Session mailSession = Session.getDefaultInstance(mailServerProperties, null);
        
        try {
            // Create mail
            MimeMessage mail = new MimeMessage(mailSession);
            
            // Set from
            mail.setFrom(new InternetAddress(senderEmail, senderName));
            
            // Set recipients emails
            for (String recipient : recipientsEmails) {
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            
            // Set mail subject and content
            mail.setSubject(mailSubject);
            mail.setContent(mailContent, "text/html"); // Email content can be in HTML format
            
            // Send email
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", senderEmail, senderPassword);
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
                
        } catch(MessagingException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }   
}
