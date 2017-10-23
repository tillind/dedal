package fr.miage.dedal.launcher;

import fr.miage.dedal.controller.ConfigurerController;
import fr.miage.dedal.controller.GameController;
import fr.miage.dedal.controller.MenuController;
import fr.miage.dedal.controller.RuleController;
import fr.miage.dedal.core.parameter.EMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private Stage stage;
    
    @Override
    public void start(Stage s) throws Exception {
        this.stage = s;
        switchScene(EMenu.MENU);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     public void switchScene(EMenu value){
        FXMLLoader loader;
        Parent root=null;
        try 
        {     
            switch(value){
                case GAME:
                    loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
                    root = (Parent)loader.load();
                    GameController controlGame = (GameController)loader.getController();
                    controlGame.setLogic(this); 
                    break;
                case MENU:
                    loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
                    root = (Parent)loader.load();
                    MenuController controlMenu = (MenuController)loader.getController();
                    controlMenu.setLogic(this);
                    break;
                case RULE:
                    loader = new FXMLLoader(getClass().getResource("/fxml/rule.fxml"));
                    root = (Parent)loader.load();
                    RuleController controlRule = (RuleController)loader.getController();
                    controlRule.setLogic(this);
                    break;
                case OPTION:
                    loader = new FXMLLoader(getClass().getResource("/fxml/configurer.fxml"));
                    root = (Parent)loader.load();
                    ConfigurerController controlOpt = (ConfigurerController)loader.getController();
                    controlOpt.setLogic(this);
                    break;
                default: Platform.exit();break;
            }   

            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (IOException e)
        {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
