/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.core;

import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import pt.bestaveiro.mrducky.parsers.SpreadsheetParser;

/**
 *
 * @author jeronimo
 */
public class Configuration {
    
    private String senderEmail;
    private String senderPassword;
    private String senderName;
    
    private List<String> recipientsEmails;
    
    private String birthdaySpreadsheetId;
    
    private String mailSubject;
    private String mailContent;
    
    private List<String> adminsEmails;
    
    private static Configuration instance;

    /**
     * Constructor.
     */
    private Configuration() {
        
        // Initialize
        initialize();
    }
    
    /**
     * Configuration initializer.
     */
    private void initialize() {
        
        // Read configuration from online spreadsheet
        readConfigurationFromSpreadsheet(Constants.MAIN_CONFIGURATION_SPREADSHEET_ID);
    }
    
    private void readConfigurationFromSpreadsheet(String spreadsheetId) {
        
        // Read spreadsheet
        JSONObject json = SpreadsheetParser.parse(spreadsheetId);
        
        // Read spreadsheet row
        JSONObject data = json.getJSONArray("rows").getJSONObject(0);
        
        // Set configuration parameters
        this.senderEmail = data.getString("senderemail");
        this.senderPassword = data.getString("senderpassword");
        this.senderName = data.getString("sendername");
        
        this.recipientsEmails = Arrays.asList(data.getString("recipientsemails").split(";"));
        
        this.birthdaySpreadsheetId = data.getString("birthdayspreadsheetid");
        
        this.mailSubject = data.getString("mailsubject");
        this.mailContent = data.getString("mailcontent");
        
        this.adminsEmails = Arrays.asList(data.getString("adminsemails").split(";"));
    }
    
    /**
     * Get instance.
     * @return the unique configuration instance.
     */
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }        
        return instance;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }
    
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getRecipientsEmails() {
        return recipientsEmails;
    }

    public void setRecipientsEmails(List<String> recipientsEmails) {
        this.recipientsEmails = recipientsEmails;
    }

    public String getBirthdaySpreadsheetId() {
        return birthdaySpreadsheetId;
    }

    public void setBirthdaySpreadsheetId(String birthdaySpreadsheetId) {
        this.birthdaySpreadsheetId = birthdaySpreadsheetId;
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

    public List<String> getAdminsEmails() {
        return adminsEmails;
    }

    public void setAdminsEmails(List<String> adminsEmails) {
        this.adminsEmails = adminsEmails;
    }
}
