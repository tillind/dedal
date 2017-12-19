/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Party {
    private ArrayList<Handle> handles;
            
    public Party(){
       handles = new ArrayList<>();
    }
    
    public void addHandle(Handle h){
        this.handles.add(h);
    }

    /**
     * @return the handles
     */
    public ArrayList<Handle> getHandles() {
        return handles;
    }

    /**
     * @param handles the handles to set
     */
    public void setHandles(ArrayList<Handle> handles) {
        this.handles = handles;
    }
    
}
