/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.bestaveiro.mrducky.core.Birthday;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.error.Errors;
import pt.bestaveiro.mrducky.mail.MailGenerator;
import pt.bestaveiro.mrducky.parsers.SpreadsheetParser;

/**
 *
 * @author jeronimo
 */
public class Tasks {
    
    public static void sendAnniversaries() {
                
        // Get configuration
        Configuration conf = Configuration.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM"); // Date formatter
        
        // Read birthdays from spreadsheet
        List<Birthday> birthdaysList = readBirthdays(conf.getBirthdaySpreadsheetId());
        
        // Get today date
        LocalDate todayDate = LocalDate.now();
        
        // Send mail about the person that have birthdays today
        for (Birthday birthday : birthdaysList) {                            
            if (formatter.format(todayDate).equals(formatter.format(birthday.getDate()))) {
                // Send e-mail
                sendAnniversaryEmail(birthday, conf);
            }                
        }
    }
    
 
    private static List<Birthday> readBirthdays(String spreadsheet) {
                
        Set<Birthday> birthdays = new HashSet();
        
        // Read spreadsheet
        JSONObject json = SpreadsheetParser.parse(spreadsheet);
                
        // Read rows
        JSONArray rows = json.getJSONArray("rows");
        
        // Read birthdays
        for (int i = 0 ; i < rows.length() ; i++) {
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Date formatter
            
            // Get birthday data
            JSONObject birtdayData = rows.getJSONObject(i);
            
            // Create birthday
            String name = null;
            LocalDate date = null;
            Map<String, String> tags = new HashMap();
            
            for (String key : birtdayData.keySet()) {
                
                if (key.equalsIgnoreCase("name")) { // Name
                    try {
                        name = birtdayData.getString("name");
                        tags.put("name", name);
                    } catch (Exception ex) {
                        Errors.sendError("Can't get attribute name for an instance", ex);
                        name = null;
                    }
                } else if (key.equalsIgnoreCase("birthdaydate")) {
                    try {
                        String dateStr = birtdayData.getString("birthdaydate");                    
                        date = LocalDate.parse(dateStr, formatter);
                        tags.put("birthdaydate", dateStr);
                    } catch (Exception ex) {
                        Errors.sendError("Can't get attribute birthday date for an instance", ex);
                        date = null;
                    }
                } else {
                    try {
                        tags.put(key, birtdayData.getString(key));
                    } catch (Exception ex) {}
                }
            }
                        
            if (name != null && date != null) {
                Birthday bithday = new Birthday(name, date, tags);
                birthdays.add(bithday);
            }
        }
        
        return new ArrayList(birthdays);
    }
    
    private static void sendAnniversaryEmail(Birthday birthday, Configuration conf) {
            
        // Instantate a mail generator
        MailGenerator mailGen = new MailGenerator();

        // Replace tags
        String subject = replaceTags(conf.getMailSubject(), birthday.getParameters());
        String content = replaceTags(conf.getMailContent(), birthday.getParameters());

        // Send mail
        mailGen.sendEmail(conf.getSenderEmail(), conf.getSenderPassword(), 
                conf.getRecipientsEmails(), subject, content);

        System.out.println("Sent anniversary mail to " + birthday.getName());       
    }
    
    private static String replaceTags(String text, Map<String, String> tasks) {
                                
        for (Entry<String, String> tag : tasks.entrySet()) {            
            text = text.replaceAll("\\[" + tag.getKey() + "\\]", tag.getValue());
        }
        
        return text;
    }
}
