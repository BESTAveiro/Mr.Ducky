/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.core;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author jeronimo
 */
public class Birthday {
    
    private String name;
    private LocalDate date;
    private String photo;
    private Map<String, String> tags;

    public Birthday(String name, LocalDate date, String photo, Map<String, String> tags) {
        this.name = name;
        this.date = date;
        this.photo = photo;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj != null) {
            return false;
        }
        
        Birthday other = (Birthday) obj;
        
        if (name.equals(other.getName()) && 
                date.equals(other.getDate())) {
            return true;
        }
        
        return false;
    }
}
