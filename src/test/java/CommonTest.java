import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import webDriver.WebDriverInit;

class CommonTest {

    private static RemoteWebDriver webDriver;
    private static WebDriverInit webDriverInit;

    @BeforeSuite
    public void beforeSuite() {
        webDriverInit = new WebDriverInit();
        webDriver = webDriverInit.initWebDriver();
    }

    public static RemoteWebDriver getWebDriver() {
        return webDriver;
    }

    public static WebDriverInit getWebDriverInit() {
        return webDriverInit;
    }

    @AfterSuite
    public void afterClass() {
        webDriver.quit();
    }
}
