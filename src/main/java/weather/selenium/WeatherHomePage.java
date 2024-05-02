package weather.selenium;

import actions.ElementActionsManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WeatherHomePage {

    private final By searchBar = By.id("LocationSearch_input");
    private final By unitDisplay = By.xpath("//*[@data-testid='ctaButton']");
    private final By celsiusDegrees = By.xpath("//*[@data-testid='degreesCbutton']");
    private final By rockvilleOption = By.xpath("//*[text()='Rockville, MD']");
    private final By temperatureValue = By.xpath("//*[@data-testid='TemperatureValue']");

    private final ElementActionsManager elementActionsManager;
    private final static Logger logger = LogManager.getLogger(WeatherHomePage.class);

    public WeatherHomePage(RemoteWebDriver driver) {
        elementActionsManager = new ElementActionsManager(driver);
    }

    /**
     * This method gets a Zip Code and sending it to the search bar field
     *
     * @param zip_code zip code to be sent
     * @see ElementActionsManager#sendKeys(By, String)
     */
    public void enterZipCode(String zip_code) {
        elementActionsManager.click(searchBar);
        elementActionsManager.sendKeys(searchBar, zip_code);
        logger.info("Zip code [" + zip_code + "] was sent to search bar");
    }

    /**
     * This method chooses the 'Rockville, MD' option
     *
     * @see ElementActionsManager#click(By)
     */
    public void chooseRockville() {
        elementActionsManager.click(rockvilleOption);
        logger.info("'Rockville, MD' option was chosen");
    }

    /**
     * This method gets the current temperature
     *
     * @return current temperature as {@link String}
     * @see ElementActionsManager#getTextFromField(By)
     */
    public String getCurrentTemperature() {
        String temperature = elementActionsManager.getTextFromField(temperatureValue);
        logger.info("Current weather via Selenium: " + temperature);
        return temperature.substring(0, temperature.length() - 1);
    }

    /**
     * This method changes the degrees to Celsius
     *
     * @see ElementActionsManager#click(By)
     */
    public void changeToCelsiusDegrees() {
        try {
            Thread.sleep(1000);
            elementActionsManager.click(unitDisplay);
            Thread.sleep(500);
            elementActionsManager.click(celsiusDegrees);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to change degrees to Celsius, due to: " + e);
        }
    }

    public ElementActionsManager getElementActionsManager() {
        return elementActionsManager;
    }
}