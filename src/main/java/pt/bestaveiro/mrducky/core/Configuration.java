/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.core;

/**
 *
 * @author jeronimo
 */
public class Configuration {
    
    private String email;
    private String password;
    private String recipient;
    private String birthdaySpreadsheet;
    private String mailSubject;
    private String mailContent;

    public Configuration(String email, String password, String recipient, 
            String birthdaySpreadsheet, String mailSubject, String mailContent) {
        this.email = email;
        this.password = password;
        this.recipient = recipient;
        this.birthdaySpreadsheet = birthdaySpreadsheet;
        this.mailSubject = mailSubject;
        this.mailContent = mailContent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBirthdaySpreadsheet() {
        return birthdaySpreadsheet;
    }

    public void setBirthdaySpreadsheet(String birthdaySpreadsheet) {
        this.birthdaySpreadsheet = birthdaySpreadsheet;
    }
    
    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    } 

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }  
}
