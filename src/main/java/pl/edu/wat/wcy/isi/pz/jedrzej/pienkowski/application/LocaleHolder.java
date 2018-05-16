package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jedrzej Pienkowski on 25.02.2018.
 */
public class LocaleHolder {

    private static ResourceBundle defaultLocale;
    private LocaleHolder(){}

    public static ResourceBundle getDefaultInstance(){
        if(defaultLocale == null)
            defaultLocale = ResourceBundle.getBundle("words");
        return defaultLocale;
    }

    public static ResourceBundle changeDeafultInstance(Locale locale){
        Main.logger.info("Language in application was changed");
        return defaultLocale = ResourceBundle.getBundle("words", locale);
    }
    public static String readMessage(String key){
        return getDefaultInstance().getString(key);
    }
}
