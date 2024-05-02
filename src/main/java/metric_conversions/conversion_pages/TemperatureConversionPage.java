package metric_conversions.conversion_pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class TemperatureConversionPage extends CommonPages {

    public TemperatureConversionPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * This method chooses Celsius as from option
     *
     * @see #chooseFromOption(String)
     */
    public void chooseFromCelsiusOption() {
        chooseFromOption("Celsius");
    }

    /**
     * This method chooses Fahrenheit as to option
     *
     * @see #chooseToOption(String)
     */
    public void chooseToFahrenheitOption() {
        chooseToOption("Fahrenheit");
    }

    /**
     * This method enters a temperature to the field
     *
     * @param temperature temperature to send to the field
     * @see #enterArg(int)
     */
    public void enterTemperature(int temperature) {
        enterArg(temperature);
    }
}
