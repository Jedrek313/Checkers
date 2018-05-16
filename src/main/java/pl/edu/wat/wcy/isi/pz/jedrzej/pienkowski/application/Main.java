package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.io.IOException;

/**
 * Created by Jedrzej Pienkowski on 23.01.2018.
 */
public class Main extends Application {

    public static Stage stage;
    final public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]){
        launch(args);
    }

    public void start(Stage primaryStage){

        try {
            Main.logger.error("Start of application");


            VBox root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"), LocaleHolder.getDefaultInstance());
            Scene scene = new Scene(root, 500,500);
            scene.getStylesheets().add("theme3.css");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle(LocaleHolder.getDefaultInstance().getString("LoginPage.title"));
            primaryStage.show();
            stage = primaryStage;
        } catch (IOException e) {
            Main.logger.error("Error.");
            e.printStackTrace();
        }
    }
}
