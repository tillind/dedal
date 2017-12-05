/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Dice extends Observable{
      private static  Logger logger = Logger.getLogger(Dice.class.getName());
   private int value ;
   
   public Dice(){
       this.value = 0;
   }
   public int getValue(){
       return value;
   }
   
   public void roll(){
       logger.log(Level.INFO," le dé roule");
       Random r = new Random();
        int n = 6 - 1 + 1;
        int i = r.nextInt() % n;
        value =  1 + i;
       this.setChanged();
       this.notifyObservers(this.value);
   }
    
}
