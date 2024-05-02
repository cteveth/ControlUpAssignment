import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import weather.WeatherManager;
import weather.api.entities.CurrentWeather;

public class WeatherTest extends CommonTest {

    private static WeatherManager weatherManager;

    private Double UI_temperature;
    private Double API_temperature;
    private final static Logger logger = LogManager.getLogger(WeatherTest.class);

    @BeforeTest
    public void beforeTest() {
        weatherManager = new WeatherManager(getWebDriver());
        weatherManager.weatherHome.getElementActionsManager().setHandlePopups(false);
    }

    @Test
    public void getCurrentTemperature_Selenium() {
        String URL = "http://www.weather.com";
        getWebDriverInit().navigateTo(URL);
        weatherManager.weatherHome.enterZipCode("20852");
        weatherManager.weatherHome.chooseRockville();
        weatherManager.rockvilleWeather.changeToCelsiusDegrees();
        UI_temperature = Double.parseDouble(weatherManager.rockvilleWeather.getCurrentTemperature());
    }

    @Test
    public void getCurrentTemperature_API() {
        CurrentWeather currentTemperature = weatherManager.weatherApi.getCurrentWeather("20852");
        API_temperature = currentTemperature.getCurrent().getTemp_c();
        logger.info("Current weather via API: " + API_temperature);
    }

    @Test
    public void checkTemperatureGap() {
        double highTemperature = API_temperature > UI_temperature ? API_temperature : UI_temperature;
        double lowTemperature = API_temperature < UI_temperature ? API_temperature : UI_temperature;
        double diff = highTemperature - lowTemperature;
        double avg = (highTemperature + lowTemperature) / 2;
        double percentage = (diff / avg) * 100;
        if (percentage <= 10)
            logger.info("The gap between the 2 results is in the range of 10%. Gap= " + percentage);
        else
            logger.info("The gap between the 2 results is not in the range of 10%. Gap= " + percentage);
    }
}