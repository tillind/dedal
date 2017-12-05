/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core.parameter;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public class Parameter implements Serializable{
    private String name;
    private EPersistance p;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the p
     */
    public EPersistance getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(EPersistance p) {
        this.p = p;
    }
   
    
    @Override
    public String toString() {
        return "Parameter [nom=" + this.name + ", p=" + this.p + "]";
    }
    
}
