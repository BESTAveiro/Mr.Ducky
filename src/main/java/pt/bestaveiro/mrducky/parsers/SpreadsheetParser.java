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
        int repeat = 0;
        boolean succeeded = false;
        while (!succeeded) {
            try {
                JSONObject json = new JSONObject(
                        IOUtils.toString(new URL(url), Charset.forName("UTF-8")));

                succeeded = true;
                return json;

            } catch (IOException ex) {
                repeat++;
                if (repeat == 10) {
                    Errors.sendError("", ex);
                    return null;
                }
            }
        }
        return null;
    }
    
}
