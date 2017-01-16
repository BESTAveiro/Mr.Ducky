/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.util.List;
import org.json.JSONObject;
import static pt.bestaveiro.mrducky.client.Tasks.readBirthdays;
import pt.bestaveiro.mrducky.core.Birthday;
import pt.bestaveiro.mrducky.core.Configuration;
import pt.bestaveiro.mrducky.parsers.SpreadsheetParser;

/**
 *
 * @author jeronimo
 */
public class Main {
    
    public static void main(String[] args) {
               
        System.out.println("Running ...");
        
        // Read configuration
        Configuration configuration = Tasks.readConfiguration();
                        
        // Read birthdays
        List<Birthday> birthdays = Tasks.readBirthdays(configuration.getBirthdaySpreadsheet());
        
        // Schedule tasks
        Tasks.scheduleDailyTask();
    }    
}
