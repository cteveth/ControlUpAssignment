package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActionsManager {

    private final By logo = By.id("logo");
    private final RemoteWebDriver webDriver;
    private final WebDriverWait wait;
    private boolean handlePopups;
    private final static Logger logger = LogManager.getLogger(ElementActionsManager.class);

    public ElementActionsManager(RemoteWebDriver driver) {
        if (driver == null)
            throw new RuntimeException("WebDriver cannot be null. exit..");
        webDriver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    /**
     * This method gets an element locator {@link By} and searching for the element.
     * Returns the element as {@link WebElement}
     *
     * @param by element locator to be searched
     * @return requested element as {@link WebElement}, in case of failure an exception
     * ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown
     */
    public WebElement findElement(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return webDriver.findElement(by);
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to find element by [" + by + "], Element not exist.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to find element by [" + by + "]\n" + tme);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find element by [" + by + "], due to:\n" + e);
        }
    }

    /**
     * This method gets an element locator {@link By} and clicking on the element.
     * in case of failure an exception ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown.
     *
     * @param by element locator to be clicked
     */
    public void click(By by) {
        try {
            handlePopups();
            wait.until(ExpectedConditions.elementToBeClickable(by));
            webDriver.findElement(by).click();
            logger.debug("Element [" + by + "] was clicked.");
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to find element by [" + by + "], Element not exist.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to find element by [" + by + "]\n" + tme);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find element by [" + by + "], due to:\n" + e);
        }
    }

    /**
     * This method gets an element locator {@link By} and text, and sending the text to the element.
     * In case of failure an exception ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown.
     *
     * @param by   element locator to send the input to
     * @param text text to sent to the element
     */
    public void sendKeys(By by, String text) {
        try {
            handlePopups();
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            webDriver.findElement(by).sendKeys(text);
            logger.debug("Text [" + text + "] was sent to element [" + by + "].");
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to send keys to element by [" + by + "], Element not exist.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to send keys to element by [" + by + "]\n" + tme);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element by [" + by + "], due to:\n" + e);
        }
    }

    /**
     * This method gets an element locator {@link By} and extract the text from the field.
     *
     * @param by element locator to get his text
     * @return element locator text as {@link String}, in case of failure an exception ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown.
     */
    public String getTextFromField(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            String text = findElement(by).getText();
            logger.debug("Element [" + by + "] text: " + text);
            return text;
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to get text from element [" + by + "], Element not found.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to get text from element [" + by + "]\n" + tme);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get text from element [" + by + "], due to:\n" + e);
        }
    }

    /**
     * This method get an element locator {@link By} and scrolling rhe page to the element.
     * In case of failure an exception ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown.
     *
     * @param by element locator to scroll down to
     */
    public void scrollToElement(By by) {
        try {
            handlePopups();
            WebElement webElement = findElement(by);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
            Thread.sleep(500);
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to scroll to element [" + by + "], Element not found.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to scroll to element [" + by + "]\n" + tme);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to scroll to element, due to:\n" + e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to element [" + by + "], due to:\n" + e);
        }
    }

    /**
     * This method gets a dropdown element locator {@link By} and option text and choose the requested option.
     * In case of failure an exception ({@link NoSuchElementException}|{@link TimeoutException}|{@link RuntimeException}) will be thrown.
     *
     * @param dropdown   dropdown element locator to update
     * @param optionText option's text to be chosen
     */
    public void chooseOption(By dropdown, String optionText) {
        try {
            handlePopups();
            Select selectUnitDropdown = new Select(findElement(dropdown));
            selectUnitDropdown.selectByVisibleText(optionText);
            logger.info(optionText + " option was chosen");
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Failed to choose option [" + optionText + "], Element not found.\n" + nse);
        } catch (TimeoutException tme) {
            throw new TimeoutException("TimeOut acceded, Failed to choose option [" + optionText + "]\n" + tme);
        } catch (Exception e) {
            throw new RuntimeException("Failed to choose option [" + optionText + "], due to:\n" + e);
        }
    }

    /**
     * This method handles  the website popups
     */
    public void handlePopups() {
        if (handlePopups) {
            try {
                Actions actions = new Actions(webDriver);
                WebElement btnElement = webDriver.findElement(logo);
                actions.doubleClick(btnElement).perform();
            } catch (Exception e) {
                throw new RuntimeException("Failed to handle popups, due to:\n" + e);
            }
        }
    }

    public void setHandlePopups(boolean _handlePopups) {
        handlePopups = _handlePopups;
    }
}