package pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.model;

import org.json.JSONObject;
import org.json.JSONTokener;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.application.Main;
import pl.edu.wat.wcy.isi.pz.jedrzej.pienkowski.exceptions.WeatherException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast {


    public String city = "Warsaw";
    public String requestURL = "http://api.openweathermap.org/data/2.5/weather?q="+ city + ",pl&APPID=4878fd9db754512ef66c8a6fcd34cb58";

    public double getCurrentWeatherCelcius() throws WeatherException {
        return getCurrentWeatherKelven() - 273.15;
    }

    public double getCurrentWeatherKelven() throws WeatherException {
        double result = -1;
        try {
            URL wikiRequest = new URL(requestURL);
            JSONTokener tokener = null;
            tokener = new JSONTokener(wikiRequest.openStream());
            JSONObject root = new JSONObject(tokener);
            JSONObject observation = root.getJSONObject("main");
            result = (double)observation.get("temp");
            Main.logger.info("Weather forecast download correct");
        } catch (IOException e) {
            Main.logger.error("Error during download weather forecast");
            throw new WeatherException();
        }
        return result;
    }
}
