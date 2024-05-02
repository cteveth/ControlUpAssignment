package weather;

import org.openqa.selenium.remote.RemoteWebDriver;
import weather.api.WeatherApiUtil;
import weather.selenium.WeatherHomePage;

public class WeatherManager {

    public final WeatherHomePage weatherHome;
    public final WeatherApiUtil weatherApi;

    public WeatherManager(RemoteWebDriver driver) {
        weatherHome = new WeatherHomePage(driver);
        weatherApi = new WeatherApiUtil();
    }
}