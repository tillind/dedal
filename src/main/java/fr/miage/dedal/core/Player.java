/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Player extends Observable implements Observer{
    private static  Logger logger = Logger.getLogger(Player.class.getName());
    
    private String name;
    
    private Dice dice1;
    private Dice dice2;
    
    private int  score;
    
    
    public Player(String name){
        this.name = name;
        this.score = 0;
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.dice1.addObserver(this);
        this.dice2.addObserver(this);
    }
    
    public String getName(){
        return this.name;
    }
    
    public void Play(){
        this.score = 0;
        this.dice1.roll();
        this.dice2.roll();
        this.setChanged();
        this.notifyObservers(this);
    }
    
    public int valueDice2(){
        return dice2.getValue();
    }
    public int valueDice1(){
        return dice1.getValue();
    }
    public int score(){
        return score;
    }
    

    @Override
    public void update(Observable o, Object arg) {
        logger.log(Level.INFO, "Le player a recu les valeurs du dé");
        int value = (int) arg;
        this.score += value;
       logger.log(Level.INFO, "Valeur de manche " + this.score);
    }
}
