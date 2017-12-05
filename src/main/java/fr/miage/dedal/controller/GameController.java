/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.controller;

import fr.miage.dedal.core.FacadePersistence;
import fr.miage.dedal.core.Game;
import fr.miage.dedal.core.Player;
import fr.miage.dedal.core.parameter.EMenu;
import fr.miage.dedal.core.parameter.Parameter;
import fr.miage.dedal.launcher.MainApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class GameController implements Initializable, Observer {
    private Game game;
    private MainApp myApps;
    private int numberHandle;
    ObservableList<String> items =FXCollections.observableArrayList ();

    private Parameter param;
    
    @FXML
    public Label valueDice1,valueDice2,valueTotal,handle;
    @FXML
    public ListView<String> logHandle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.game = Game.getInstance();
        this.game.getPlayer().addObserver(this);
        
        logHandle.setItems(items);
        numberHandle = 1;
        
        this.valueDice1.setText("0"); 
        this.valueDice2.setText("0"); 
        this.valueTotal.setText("0");
        this.handle.setText(Integer.toString(numberHandle));
    }
    

    public void setLogic(Application apps){
        this.myApps = (MainApp) apps;
    }
 
    @FXML
    private void playHandle(ActionEvent actionEvent){
        this.game.Launch();
    }
    
    
    @FXML
    private void exitToMenuHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.MENU);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.numberHandle++;
        Player tmp = (Player) arg;
        this.valueDice1.setText(Integer.toString(tmp.valueDice1())); 
        this.valueDice2.setText(Integer.toString(tmp.valueDice2())); 
        this.valueTotal.setText(Integer.toString(tmp.score()));
        this.handle.setText(Integer.toString(numberHandle));
        this.addLog(tmp.getName(), tmp.valueDice1(), tmp.valueDice2(), tmp.score());
        
    }
    
    private void addLog(String name,int value1,int value2, int score){
        this.items.add("["+name+"] : Dé 1: "+value1+" Dé 2: " +value2+" score :"+score);
    }
    
    
}
