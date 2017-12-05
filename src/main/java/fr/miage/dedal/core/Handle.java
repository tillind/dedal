/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import java.util.HashMap;
import java.util.Observer;


/**
 *
 * @author alex
 */
public class Handle implements Observer {
    
    public static int numberHandle=0;
    
    private HashMap<String,Entry> handles;
    
    
    public Handle(){
        this.handles = new HashMap<>();
    }
    
    public void addScore(String name,int score){
        this.handles.put(Integer.toString(numberHandle), new Entry(name, score));
        numberHandle++;
    }
    
    public void resetHandle(){
        numberHandle = 0;
        this.handles = new HashMap<>();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Player tmp = (Player) o;
        this.addScore(tmp.getName(), tmp.score());
    }


}
