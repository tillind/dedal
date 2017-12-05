/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import fr.miage.dedal.controller.ConfigurerController;
import fr.miage.dedal.core.parameter.Parameter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Game {
    private static Game inst = null;
    
    private Date datePartie;
    private Player player;
    private FacadePersistence facade;
    private Handle handle;
    private Parameter param;
    
    public static Game getInstance(){
        if(inst == null){
            inst = new Game();
        }
        return inst;
    }
    
    private Game(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); 
        this.datePartie =today.getTime();
        this.handle = new Handle();
        this.setSerializeParam();
        this.facade = new FacadePersistence(param.getP()); 
        this.setPlayer(param.getName());
    }
    
    public void setPlayer(String name){
        this.player = new Player(name);
        player.addObserver(handle);
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    
    public void Launch(){
        this.player.Play();
        if(Handle.numberHandle==10){
            Logger.getLogger(Game.class.getName()).log(Level.INFO, "Sauvegarde de la manche","Sauvegarde de la manche");
            facade.saveHandle(handle);
        }
    }
    
    private void setSerializeParam(){
        File file =  new File("param.ser") ;

        ObjectInputStream ois = null ;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            param= (Parameter)ois.readObject() ;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
