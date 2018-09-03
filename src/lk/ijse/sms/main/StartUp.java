/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StartUp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/sms/view/Login_Form.fxml"));
            
            Scene mainScene = new Scene(root);
            
            primaryStage.setTitle("Student Manage Panel");
            primaryStage.setScene(mainScene);
        
            primaryStage.setResizable(false);
            
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
