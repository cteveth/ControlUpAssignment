package webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverInit {

    private static RemoteWebDriver webDriver = null;
    private final static Logger logger = LogManager.getLogger(WebDriverInit.class);

    /**
     * This method initializes the web driver, in case was not initialized, and return the driver
     *
     * @return initialized driver as {@link RemoteWebDriver}
     * @see #init()
     */
    public RemoteWebDriver initWebDriver() {
        if (webDriver == null)
            return init();
        logger.info("WebDriver was already initialized");
        return webDriver;
    }

    /**
     * This method set up the web driver and initialize him.
     *
     * @return initialized driver as {@link RemoteWebDriver}, in case of failure {@link RuntimeException} will be thrown.
     */
    private RemoteWebDriver init() {
        try {
            logger.info("Initializing webDriver...");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            logger.info("WebDriver was initialized successfully");
            return webDriver;
        } catch (Exception e) {
            throw new RuntimeException("Fail to initialize Web Driver.\n Error: " + e);
        }
    }

    /**
     * This method gets an url and navigate to it on the web driver
     *
     * @param URL url to navigate to
     */
    public void navigateTo(String URL) {
        webDriver.navigate().to(URL);
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        logger.info("Navigated to: " + URL);
    }
}