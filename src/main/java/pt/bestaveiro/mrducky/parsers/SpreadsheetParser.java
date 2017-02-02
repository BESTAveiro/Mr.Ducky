/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.parsers;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import pt.bestaveiro.mrducky.error.Errors;

/**
 *
 * @author jeronimo
 */
public class SpreadsheetParser {
    
    public static JSONObject parse(String spreadsheetId) {
        
        // Build spreadsheet url
        String url = "http://gsx2json.com/api?id=" + spreadsheetId;
        
        
        // Retrieve data from the spreadsheet
        try {
            JSONObject json = new JSONObject(
                    IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
            
            return json;
            
        } catch (IOException ex) {
            
            Errors.sendError("", ex);            
            return null;
        }        
    }
    
    public static void main(String[] args){        
        
        List<String> cols = new ArrayList<>();
        
        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL("http://gsx2json.com/api?id=1SmD6cL4sdGhKSMBeFhn-VolvptKVy96kVRtwMun4uss"), Charset.forName("UTF-8")));
            
            JSONArray jsonarr = json.getJSONArray("rows");
            
            for(int i=0; i<jsonarr.length(); i++){
                JSONObject obj = jsonarr.getJSONObject(i);
                for(String param : obj.keySet()){
                    System.out.println(param + obj.getString(param));
                }
            }
            //System.out.println(json.toString());
        } catch (IOException ex) {
            Logger.getLogger(SpreadsheetParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
