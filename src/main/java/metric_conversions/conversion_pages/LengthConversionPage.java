package metric_conversions.conversion_pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LengthConversionPage extends CommonPages {

    public LengthConversionPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * This method chooses Meters as from option
     *
     * @see #chooseFromOption(String)
     */
    public void chooseFromMetersOption() {
        chooseFromOption("Meters");
    }

    /**
     * This method chooses Feet as to option
     *
     * @see #chooseToOption(String)
     */
    public void chooseToFeetOption() {
        chooseToOption("Feet");
    }

    /**
     * This method enters a length to the field
     *
     * @param length length to send to the field
     * @see #enterArg(int)
     */
    public void enterLength(int length) {
        enterArg(length);
    }
}