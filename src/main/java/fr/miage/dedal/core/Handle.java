/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observer;


/**
 *
 * @author alex
 */
public class Handle implements Observer, Serializable,Cloneable {
    
    private int party ;
    private int numberHandle;
    
    private int highScore;
    transient HashMap<String,Entry> handles;
    
    
    public Handle(){
        party =1;
        numberHandle=0;
        this.handles = new HashMap<>();
        highScore = 0;
    }
    
    public void addScore(String name,int score){
        setHighScore(getHighScore() + score);
        this.handles.put(Integer.toString(getNumberHandle()), new Entry(name, score));
        setNumberHandle(getNumberHandle() + 1);
    }
    
    public void resetHandle(){
        setNumberHandle(0);
        this.handles = new HashMap<>();
        setHighScore(0);
        setParty(getParty() + 1);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Player tmp = (Player) o;
        this.addScore(tmp.getName(), tmp.score());
    }

    /**
     * @return the party
     */
    public int getParty() {
        return party;
    }

    /**
     * @param party the party to set
     */
    public void setParty(int party) {
        this.party = party;
    }

    /**
     * @return the numberHandle
     */
    public int getNumberHandle() {
        return numberHandle;
    }

    /**
     * @param numberHandle the numberHandle to set
     */
    public void setNumberHandle(int numberHandle) {
        this.numberHandle = numberHandle;
    }

    /**
     * @return the highScore
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * @param highScore the highScore to set
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public Handle clone() throws CloneNotSupportedException{
        return (Handle)super.clone();
    }
}
