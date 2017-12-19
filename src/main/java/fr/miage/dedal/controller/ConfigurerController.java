/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.controller;

import fr.miage.dedal.core.parameter.EMenu;
import fr.miage.dedal.core.parameter.EPersistance;
import fr.miage.dedal.core.parameter.Parameter;
import fr.miage.dedal.launcher.MainApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class ConfigurerController implements Initializable {
    private Parameter param;
    
    @FXML
    public TextField pseudo;
    @FXML
    public RadioButton json,xml,redis;
    @FXML
    public ToggleGroup type;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        pseudo.setText(param.getName());
        
        if(param.getP().equals(EPersistance.XML)){
            this.xml.setSelected(true);
        }else if(param.getP().equals(EPersistance.JSON)){
            this.json.setSelected(true);
        }else{
            this.redis.setSelected(true);
        }
      

    }  
    
    private MainApp myApps;
    public void setLogic(Application apps){
        this.myApps = (MainApp) apps;
    }
    
    @FXML
    private void saveAndExitHandle(ActionEvent actionEvent){
        param.setName(pseudo.getText());
        File fichier =  new File("param.ser") ;
        ObjectOutputStream oos = null ;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            oos.writeObject(param) ;
        } catch (IOException ex) {
            Logger.getLogger(ConfigurerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         this.myApps.switchScene(EMenu.MENU);
    }
    
    @FXML
    private void exitToMenuHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.MENU);
    }
    
    @FXML
    private void selectXmlRadioHandle(ActionEvent actionEvent){
        param.setP(EPersistance.XML);
    }
    
    @FXML
    private void selectJsonRadioHandle(ActionEvent actionEvent){
        param.setP(EPersistance.JSON);
    }
    
    @FXML
    private void selectRedisRadioHandle(ActionEvent actionEvent){
        param.setP(EPersistance.REDIS);
    }
    
    

}
