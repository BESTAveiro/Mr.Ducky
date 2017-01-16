/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.client;

import java.util.ArrayList;
import java.util.List;
import pt.bestaveiro.mrducky.mail.MailGenerator;

/**
 *
 * @author jeronimo
 */
public class Test {
    
    public static void main(String[] args) {
        
        MailGenerator jen = new MailGenerator();
        
        List<String> list = new ArrayList();
        list.add("migueljgoliveira@gmail.com");
        
        for (int i = 0 ; i < 20 ; i++) {
        
            jen.sendEmail(list, "Ai que lindo", "<h1>SQN</h1>");
            
        }
        
    }
    
}
