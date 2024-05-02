package metric_conversions;

import actions.ElementActionsManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MetricConversionPage {

    private final By temperatureButton = By.xpath("//*[@id=\"typeMenu\"]/a[1]");
    private final By lengthButton = By.xpath("//*[@id=\"typeMenu\"]/a[3]");
    private final By weightButton = By.xpath("//*[@id=\"typeMenu\"]/a[2]");
    private final By queryButton = By.id("queryFrom");

    private final ElementActionsManager elementActionsManager;
    private final static Logger logger = LogManager.getLogger(MetricConversionPage.class);

    public MetricConversionPage(RemoteWebDriver driver) {
        elementActionsManager = new ElementActionsManager(driver);
    }

    /**
     * This method clicks on temperature button.
     *
     * @see #clickButton(By, String)
     */
    public void clickTemperatureButton() {
        clickButton(temperatureButton, "temperature");
    }

    /**
     * This method clicks on length button.
     *
     * @see #clickButton(By, String)
     */
    public void clickLengthButton() {
        clickButton(lengthButton, "length");
    }

    /**
     * This method clicks on weight button.
     *
     * @see #clickButton(By, String)
     */
    public void clickWeightButton() {
        clickButton(weightButton, "weight");
    }

    /**
     * This method get an element locator {@link By} and name, and clicking on the button.
     *
     * @see ElementActionsManager#scrollToElement(By)
     * @see ElementActionsManager#click(By)
     */
    private void clickButton(By by, String button_name) {
        elementActionsManager.scrollToElement(queryButton);
        elementActionsManager.click(by);
        logger.info("Clicked on " + button_name + " button");
    }
}