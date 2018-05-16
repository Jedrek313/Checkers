package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.LocaleHolder;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.Main;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.RegisterPlate;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.LoginException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.UsernameException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.WeatherException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model.DataBaseCommunication;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model.WeatherForecast;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jedrzej Pienkowski on 25.02.2018.
 */
public class LoginController implements Initializable{
    static final ObservableList languageList = FXCollections.observableArrayList("Polski", "English");

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn, register;

    @FXML
    private MenuItem exit, theme1, theme2, theme3;

    @FXML
    private ComboBox<String> language;

    @FXML
    private Label weather, communicate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.logger.info("LoginController start initializing");
        try {
            WeatherForecast forecast = new WeatherForecast();
            String city = LocaleHolder.readMessage("City");
            weather.setText(city + ": " + forecast.getCurrentWeatherCelcius()+"C");
        } catch (WeatherException e) {
            communicate.setText(LocaleHolder.readMessage("LoginPage.weatherError"));
        }

        language.setItems(languageList);

        if(LocaleHolder.getDefaultInstance().getLocale().equals(Locale.US)){
            language.getSelectionModel().selectLast();
        }else{
            language.getSelectionModel().selectFirst();
        }


        language.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1 == languageList.get(0)){
                    LocaleHolder.changeDeafultInstance(Locale.getDefault());
                }else{
                    LocaleHolder.changeDeafultInstance(Locale.US);
                }
                new Main().start(Main.stage);
            }
        });

        signIn.setOnMouseClicked((MouseEvent event) ->{
            String l = login.getText();
            String p = password.getText();

            //
            try {
                DataBaseCommunication.getInstance().tryLogIn(l,p);
                VBox root = FXMLLoader.load(getClass().getResource("/GamePage.fxml"), LocaleHolder.getDefaultInstance());
                Scene scene = Main.stage.getScene();
                scene.setRoot(root);
            } catch (LoginException e) {
                communicate.setText(LocaleHolder.readMessage("LoginPage.loginException"));
            } catch (UsernameException e) {
                communicate.setText(LocaleHolder.readMessage("LoginPage.usernameException"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        register.setOnMouseClicked((MouseEvent event) ->{
            RegisterPlate.showRegisterPlate();
        });

        initializeMenu();

    }


    private void initializeMenu(){

        theme1.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme2.css");
            Main.logger.info("Theme change to theme2");
        });
        theme2.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme1.css");
            Main.logger.info("Theme change to theme1");
        });
        theme3.setOnAction(event -> {
            Scene scene = Main.stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("theme3.css");
            Main.logger.info("Theme change to theme3");
        });
        exit.setOnAction(event -> {
            System.exit(0);
        });
    }
}
