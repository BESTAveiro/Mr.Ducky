/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import pt.bestaveiro.mrducky.core.Birthday;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.mail.MailGenerator;

/**
 *
 * @author jeronimo
 */
public class Main {
    
    public static void main(String[] args) {
               
        while (true) {
            try {
                TimeUnit.HOURS.sleep(24);
            } catch (InterruptedException e) {}

            System.out.println("Running ...");

            // Read configuration
            Configuration configuration = Tasks.readConfiguration();

            // Read birthdays
            List<Birthday> birthdays = Tasks.readBirthdays(configuration.getBirthdaySpreadsheet());

            // Sent initial mail
            MailGenerator jen = new MailGenerator();
            jen.sendEmail(configuration.getEmail(), configuration.getPassword(),
                    Arrays.asList(Constants.ADMIN.split(";")),
                    "[Mr.Ducky] Quack Quack I am on", "Hey dude I am online!");

            // Schedule tasks
            Tasks.scheduleDailyTask();
        }
    }
}
