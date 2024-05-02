package metric_conversions;

import metric_conversions.conversion_pages.LengthConversionPage;
import metric_conversions.conversion_pages.TemperatureConversionPage;
import metric_conversions.conversion_pages.WeightConversionPage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MetricConversionsManager {

    public final MetricConversionPage metricConversion;
    public final TemperatureConversionPage temperatureConversion;
    public final LengthConversionPage lengthConversion;
    public final WeightConversionPage weightConversion;

    public MetricConversionsManager(RemoteWebDriver driver) {
        metricConversion = new MetricConversionPage(driver);
        temperatureConversion = new TemperatureConversionPage(driver);
        lengthConversion = new LengthConversionPage(driver);
        weightConversion = new WeightConversionPage(driver);

    }
}