package metric_conversions.conversion_pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WeightConversionPage extends CommonPages {

    public WeightConversionPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * This method chooses Ounces as from option
     *
     * @see #chooseFromOption(String)
     */
    public void chooseFromOuncesOption() {
        chooseFromOption("Ounces");
    }

    /**
     * This method chooses Grams as to option
     *
     * @see #chooseToOption(String)
     */
    public void chooseToGramsOption() {
        chooseToOption("Grams");
    }

    /**
     * This method enters a weight to the field
     *
     * @param weight weight to send to the field
     * @see #enterArg(int)
     */
    public void enterWeight(int weight) {
        enterArg(weight);
    }
}