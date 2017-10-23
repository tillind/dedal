/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.controller;

import fr.miage.dedal.core.parameter.EMenu;
import fr.miage.dedal.launcher.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private MainApp myApps;
    public void setLogic(Application apps){
        this.myApps = (MainApp) apps;
    }
    
    @FXML
    private void goToGameHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.GAME);
    }
    
    @FXML
    private void goToRuleHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.RULE);
    }
    
    @FXML
    private void goToOptionsHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.OPTION);
    }
    
    @FXML
    private void exitApplicationHandle(ActionEvent actionEvent){
        Platform.exit();
    }
}
