package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.*;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.checker.*;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model.FrozenState;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model.State;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jedrzej Pienkowski on 20.03.2018.
 */
public class GameController implements Initializable{

    private State state;
    CheckerColor turnColor;
    Checker movingChecker;

    @FXML
    private Board board;

    @FXML
    private MenuItem exit, theme1, theme2, theme3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.logger.info("GameController start initializing");
        initializeMenu();
        state = new FrozenState(board);
        for(Node n :board.getChildren()){
            if(n.getClass()==Checker.class){
                n.setOnMouseClicked(event -> {
                    state = state.clickChecker((Checker)n);
                });
            }else if(n.getClass()==BoardField.class){
                n.setOnMouseClicked(event -> {
                    state.clickBoardField((BoardField)n);
                });
            }
        }
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
