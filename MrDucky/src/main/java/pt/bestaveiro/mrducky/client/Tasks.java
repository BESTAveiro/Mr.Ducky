/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.stringtemplate.v4.ST;
import pt.bestaveiro.mrducky.core.Birthday;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.mail.MailGenerator;
import pt.bestaveiro.mrducky.parsers.SpreadsheetParser;

/**
 *
 * @author jeronimo
 */
public class Tasks {
    
    public static void scheduleDailyTask() {
        
        // This code will run every day at 2 am
        Date date2am = new Date();
        date2am.setHours(1);
        date2am.setMinutes(31);        
        int period = 1000*60*60*24;
        
        // Schedule task
        DailyTask task = new DailyTask();        
        Timer timer = new Timer();
        timer.schedule(task, date2am, period);        
    }
    
    private static class DailyTask extends TimerTask {
        
        @Override
        public void run() {
            
            // Read configuration
            Configuration configuration = readConfiguration();
            
            // Get all birthdays
            List<Birthday> birthdays = readBirthdays(configuration.getBirthdaySpreadsheet());
            
            // Schedule emails for birthdays that will occur today
            LocalDate today = LocalDate.now();
            
            System.out.println(today.getDayOfMonth() + " " + today.getMonth());
            
            for (Birthday birthday : birthdays) {                
                if (birthday.getDate().getDayOfMonth() == today.getDayOfMonth() &&
                        birthday.getDate().getMonth().equals(today.getMonth())) {                    
                    System.out.println(birthday.getName() + " has his/her birthday today :)");
                    
                    // Schedule e-mail
                    scheduleEmailTask(birthday, configuration);
                }                
            }
        }       
        
    }
    
    public static Configuration readConfiguration() {
                
        // Read spreadsheet
        JSONObject json = SpreadsheetParser.parse(Constants.MAIN_CONFIGURATION_SPREADSHEET);
        
        // Read configuration
        JSONObject data = json.getJSONArray("rows").getJSONObject(0);
        
        String email = data.getString("email");
        String password = data.getString("password");
        String recipient = data.getString("destinatario");
        String birtdaySS = data.getString("spreadsheetdeaniversarios");
        String mailSuject = data.getString("assunto");
        String mailContent = data.getString("mailmodelo");
        
        // Create configuration object
        Configuration configuration = new Configuration(email, password, 
                recipient, birtdaySS, mailSuject, mailContent);
        
        return configuration;
    }        
    
    public static List<Birthday> readBirthdays(String spreadsheet) {
                
        Set<Birthday> birthdays = new HashSet();
        
        // Read spreadsheet
        JSONObject json = SpreadsheetParser.parse(spreadsheet);
                
        // Read rows
        JSONArray rows = json.getJSONArray("rows");
        
        // Read birthdays
        for (int i = 0 ; i < rows.length() ; i++) {
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            
            // Get birthday data
            JSONObject birtdayData = rows.getJSONObject(i);
            
            // Create birthday
            String name = "";
            LocalDate date = null;
            String photo = "";
            Map<String, String> tags = new HashMap();
            
            for (String key : birtdayData.keySet()) {
                if (key.equalsIgnoreCase("nome")) {
                    name = birtdayData.getString("nome");
                    tags.put("nome", name);
                } else if (key.equalsIgnoreCase("datadeaniversario")) {
                    String dateStr = birtdayData.getString("datadeaniversario");                    
                    date = LocalDate.parse(dateStr, formatter);
                    tags.put("datadeaniversario", dateStr);
                } else if (key.equalsIgnoreCase("fotografia")) {
                    try {                    
                        photo = birtdayData.getString("fotografia");
                    } catch (JSONException ex) {
                        photo = "";
                    }
                    tags.put("fotografia", photo);
                } else {
                    tags.put(key, birtdayData.getString(key));
                }
            }
            
            Birthday bithday = new Birthday(name, date, photo, tags);
            birthdays.add(bithday);
        }
        
        return new ArrayList(birthdays);
    }
    
    public static void scheduleEmailTask(Birthday birthday, 
            Configuration configuration) {
        
        // This code will run at 9 am of today
        Date date9am = new Date();
        date9am.setHours(1);
        date9am.setMinutes(50);        
        
        // Schedule task
        EmailTask task = new EmailTask(birthday, configuration);        
        Timer timer = new Timer();
        timer.schedule(task, date9am);        
    }
    
    private static class EmailTask extends TimerTask {
        
        private Birthday birthday;
        private Configuration configuration;
        
        public EmailTask(Birthday birthday, Configuration configuration) {            
            this.birthday = birthday;
            this.configuration = configuration;
        }
        
        @Override
        public void run() {
            
            // Send e-mail
            MailGenerator mailGen = new MailGenerator();
            
            List<String> recipients = Arrays.asList(configuration.getRecipient().split(";"));
            
            // Replace tags
            String subject = replaceTags(configuration.getMailSubject(), birthday.getTags());
            String content = replaceTags(configuration.getMailContent(), birthday.getTags());
            
            mailGen.sendEmail(recipients, subject, content);
            
            System.out.println("Sent e-mail -> " + birthday.getName());
        }       
        
    }
    
    public static String replaceTags(String text, Map<String, String> tasks) {
        
        ST st = new ST(text);
        
        for (String tag : tasks.keySet()) {
            st.add(tag, tasks.get(tag));
        }
        
        return st.render();
    }
}
