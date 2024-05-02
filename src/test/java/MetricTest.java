import metric_conversions.MetricConversionsManager;
import metric_conversions.conversion_pages.LengthConversionPage;
import metric_conversions.conversion_pages.TemperatureConversionPage;
import metric_conversions.conversion_pages.WeightConversionPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class MetricTest extends CommonTest {
    private static MetricConversionsManager metricConversionsManager;
    private final static Logger logger = LogManager.getLogger(MetricTest.class);

    @BeforeTest
    public void beforeTest() {
        metricConversionsManager = new MetricConversionsManager(getWebDriver());
        metricConversionsManager.temperatureConversion.getElementActionsManager().setHandlePopups(true);
    }

    @BeforeMethod
    public void beforeMethod() {
        String metricURL = "https://www.metric-conversions.org/";
        getWebDriverInit().navigateTo(metricURL);
        metricConversionsManager.temperatureConversion.getElementActionsManager().handlePopups();
    }

    @Test
    public void convertCelsiusToFahrenheit() {
        metricConversionsManager.metricConversion.clickTemperatureButton();
        metricConversionsManager.temperatureConversion.chooseFromCelsiusOption();
        metricConversionsManager.temperatureConversion.chooseToFahrenheitOption();
        metricConversionsManager.temperatureConversion.enterTemperature(25);
        validateResult("25°C = 77.000°F");
    }

    @Test
    public void convertMetersToFeet() {
        metricConversionsManager.metricConversion.clickLengthButton();
        metricConversionsManager.lengthConversion.chooseFromMetersOption();
        metricConversionsManager.lengthConversion.chooseToFeetOption();
        metricConversionsManager.lengthConversion.enterLength(25);
        validateResult("25m = 82.021ft");
    }

    @Test
    public void convertOuncesToGrams() {
        metricConversionsManager.metricConversion.clickWeightButton();
        metricConversionsManager.weightConversion.chooseFromOuncesOption();
        metricConversionsManager.weightConversion.chooseToGramsOption();
        metricConversionsManager.weightConversion.enterWeight(25);
        validateResult("25oz = 708.74g");
    }

    private void validateResult(String expected_result) {
        String result = metricConversionsManager.temperatureConversion.getConversionResult();
        if (!result.equalsIgnoreCase(expected_result))
            logger.error("Conversion was failed.\n" +
                    "Expected: " + expected_result + ", Actual: " + result);
        logger.info("Conversion result: " + result);
    }
}