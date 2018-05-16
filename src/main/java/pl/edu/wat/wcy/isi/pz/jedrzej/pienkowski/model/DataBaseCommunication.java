package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model;

import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.ConnectionManager;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.Main;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.DuplicateException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.LoginException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.RegistrationException;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.UsernameException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCommunication {

    private static DataBaseCommunication dataBaseCommunication;

    private DataBaseCommunication(){

    }

    public static synchronized DataBaseCommunication getInstance(){
        if(dataBaseCommunication==null){
            dataBaseCommunication = new DataBaseCommunication();
        }
        return dataBaseCommunication;
    }

    public synchronized void tryLogIn(String login, String password) throws LoginException, UsernameException {

        Main.logger.info("Trying to log in");
        String sql;

        sql = "SELECT username, password FROM checkers.User where username ='" + login + "' AND password='" + password + "'";
        Connection conn = ConnectionManager.getConnection();
        Statement stmt = null;
        boolean result = false;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            result = rs.first();
            Main.logger.error("Logging in completed succesfully");
        } catch (SQLException e) {
            Main.logger.error("SQL exception, did not log in");
            throw new LoginException();
        }

        if(!result){
            throw new UsernameException();
        }
    }

    public synchronized void register(String login, String password) throws DuplicateException, RegistrationException, UsernameException {





        Main.logger.info("Trying to register");

        String sql;

        sql = "SELECT username FROM checkers.User where username ='" + login + "'";
        Connection conn = ConnectionManager.getConnection();
        boolean result = false;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            result = rs.first();
            if (result) {
                throw new DuplicateException();
            }
        } catch (SQLException e) {
            Main.logger.error("SQL exception");
        }

        sql = "INSERT INTO User VALUES ('" + login + "', '" + password + "')";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
            tryLogIn(login, password);
            Main.logger.error("Registration completed succesfully");
        } catch (SQLException e) {
            throw new RegistrationException();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
