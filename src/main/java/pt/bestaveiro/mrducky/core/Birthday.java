/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.bestaveiro.mrducky.core;

import java.time.LocalDate;
import java.util.Map;

/**
 * Class that represents a Birthday.
 *
 * @author André Jerónimo (<a href="mailto:andre.jeronimo.santos@best.eu.org">andre.jeronimo.santos@best.eu.org</a>)
 * @author Miguel Oliveira (<a href="mailto:miguel.oliveira@best.eu.org">miguel.oliveira@best.eu.org</a>))
 */
public class Birthday {
    
    /**
     * Person name.
     */
    private String name;
    
    /**
     * Birthday date
     */
    private LocalDate date;
    
    /**
     * Map with other parameters
     */
    private Map<String, String> parameters;

    /**
     * Constructor.
     * @param name person name
     * @param date birthday date
     * @param parameters other parameters
     */
    public Birthday(String name, LocalDate date, Map<String, String> parameters) {
        this.name = name;
        this.date = date;
        this.parameters = parameters;
    }

    /**
     * Get name.
     * @return person name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     * @param name person name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get birthday date.
     * @return birthday date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set birthday date.
     * @param date birthday date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get other parameters.
     * @return other parameters
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * Set other parameters.
     * @param parameters other parameters
     */
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        // Verify if object to compare is null
        if (obj != null) {
            return false;
        }
        
        // Convert object to Birthday istance
        Birthday other = (Birthday) obj;
        
        // Two Birthday instances are equal if the name and birtday date are equal.
        if (name.equals(other.getName()) && date.equals(other.getDate())) {
            return true;
        }
        
        return false;
    }
}
