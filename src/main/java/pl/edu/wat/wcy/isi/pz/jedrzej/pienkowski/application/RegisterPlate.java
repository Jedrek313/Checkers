package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.DuplicateException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.RegistrationException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.UsernameException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model.DataBaseCommunication;

import java.util.ResourceBundle;

public class RegisterPlate {

    private static GridPane gridPane;
    private static Stage stage;

    public static void showRegisterPlate(){
        if(gridPane==null){
            initializeGridPane();
        }
        show();
    }

    private RegisterPlate() {
    }

    private static void initializeGridPane(){
        ResourceBundle rb = LocaleHolder.getDefaultInstance();

        Label loginLabel = new Label(rb.getString("LoginPage.login"));
        TextField login = new TextField();
        Label registerLabel = new Label(rb.getString("LoginPage.password"));
        PasswordField password = new PasswordField();
        Label communicate = new Label("");
        Button exitButton = new Button(rb.getString("Exit"));

        exitButton.setOnMouseClicked(event -> {
            close();
        });

        Button register = new Button(LocaleHolder.getDefaultInstance().getString("LoginPage.registerButton"));
        register.setOnMouseClicked(event -> {
            String l = login.getText();
            String p = password.getText();
            try {
                DataBaseCommunication.getInstance().register(l,p);
                communicate.setText(rb.getString("RegisterPlate.registerCompleted"));
            } catch (DuplicateException e) {
                Main.logger.error("Registration failed causing by duplicate");
                communicate.setText(rb.getString("RegisterPlate.duplicate"));
            } catch (RegistrationException e) {
                Main.logger.error("Registration failed");
                communicate.setText(rb.getString("RegisterPlate.warning"));
            } catch (UsernameException e) {
                Main.logger.error("Logging in uncompleted");
                communicate.setText(rb.getString("RegisterPlate.warning"));
            }
        });

        gridPane = new GridPane();
        gridPane.getRowConstraints().add(new RowConstraints());
        gridPane.getColumnConstraints().add(new ColumnConstraints());
        gridPane.addColumn(0, loginLabel, login, registerLabel, password,register , communicate, exitButton);
        makeStage();
    }

    private static void makeStage(){
        Scene scene = new Scene(gridPane, 200, 200);
        stage = new Stage();
        stage.setScene(scene);
    }

    private static void show(){
        stage.show();
    }

    private static void close(){
        stage.close();
    }
}
