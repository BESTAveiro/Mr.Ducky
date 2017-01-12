/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.core;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author jeronimo
 */
public class Anniversary {
    
    private String name;
    private Date date;
    private Map<String, String> tags;

    public Anniversary(String name, Date date, Map<String, String> tags) {
        this.name = name;
        this.date = date;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    
}
