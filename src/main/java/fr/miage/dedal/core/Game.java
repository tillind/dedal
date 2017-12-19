/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import fr.miage.dedal.controller.ConfigurerController;
import fr.miage.dedal.controller.GameController;
import fr.miage.dedal.core.parameter.Parameter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private GameController gc ;
    private Party p;
    
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
        this.setSerializeParam();
        this.facade = new FacadePersistence(param.getP()); 
        this.setPlayer(param.getName());
        p= facade.getHandle();

        
        
    }
    private void initLog(){
        ArrayList<Handle> tmp = p.getHandles();
        for (Handle h : tmp) {
            gc.addLog(this.player.getName(), h.getParty(), h.getHighScore());
        }
    }
    public void setPlayer(String name){
        this.player = new Player(name);
       
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    
    public void Launch(){
        this.player.Play();
        if(handle.getNumberHandle()==10){
            Logger.getLogger(Game.class.getName()).log(Level.INFO, "Sauvegarde de la manche","Sauvegarde de la manche");
            try {
                p.addHandle(handle.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            facade.saveHandle(p);
            gc.addLog(this.player.getName(), handle.getParty(), handle.getHighScore());
            handle.resetHandle();
            gc.updateParty(String.valueOf(handle.getParty()));
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
    
    public void setGc(GameController gc){
        this.gc = gc;
        initLog();
        ArrayList<Handle> tmp = p.getHandles();
        handle = new Handle();
        if(!tmp.isEmpty()){
            Handle h = tmp.get(tmp.size()-1);
            handle.setParty(h.getParty()+1);
        }
        player.addObserver(handle);
        gc.updateParty(String.valueOf(handle.getParty()));
    }
}
