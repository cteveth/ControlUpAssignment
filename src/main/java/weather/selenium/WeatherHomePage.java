package weather.selenium;

import actions.ElementActionsManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WeatherHomePage {

    private final By searchBar = By.id("LocationSearch_input");
    private final By rockvilleOption = By.xpath("//*[text()='Rockville, MD']");

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

    public ElementActionsManager getElementActionsManager() {
        return elementActionsManager;
    }
}