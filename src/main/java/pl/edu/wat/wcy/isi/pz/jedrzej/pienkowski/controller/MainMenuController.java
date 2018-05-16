package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.LocaleHolder;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jedrzej Pienkowski on 26.02.2018.
 */
public class MainMenuController implements Initializable {

    @FXML
    Button start;

    @FXML
    private MenuItem exit, theme1, theme2, theme3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        start.setOnMouseClicked((MouseEvent event) ->{
            try {
                VBox root = FXMLLoader.load(getClass().getResource("/GamePage.fxml"), LocaleHolder.getDefaultInstance());
                Scene scene = Main.stage.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        initializeMenu();
    }



    private void initializeMenu(){

        theme1.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme2.css");
        });
        theme2.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme1.css");
        });
        theme3.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme3.css");
        });
        exit.setOnAction(event -> {
            System.exit(0);
        });
    }

}
