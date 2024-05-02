package metric_conversions.conversion_pages;

import actions.ElementActionsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

class CommonPages {
    private final By selectUnitFrom = By.id("unitFrom");
    private final By selectUnitTo = By.id("unitTo");
    private final By argField = By.id("arg");
    private final By swapUnitAnswer = By.id("answerDisplay");

    private final ElementActionsManager elementActionsManager;

    protected CommonPages(RemoteWebDriver driver) {
        elementActionsManager = new ElementActionsManager(driver);
    }

    /**
     * This method gets the requested 'from' option text and choosing it.
     *
     * @see ElementActionsManager#chooseOption(By, String)
     */
    protected void chooseFromOption(String option_text) {
        elementActionsManager.chooseOption(selectUnitFrom, option_text);
    }

    /**
     * This method gets the requested 'to' option text and choosing it.
     *
     * @see ElementActionsManager#chooseOption(By, String)
     */
    protected void chooseToOption(String option_text) {
        elementActionsManager.chooseOption(selectUnitTo, option_text);
    }

    /**
     * This method gets a number and sending it to the 'arg' field
     *
     * @param arg number to be sent
     * @see ElementActionsManager#sendKeys(By, String)
     */
    protected void enterArg(int arg) {
        elementActionsManager.sendKeys(argField, String.valueOf(arg));
    }

    /**
     * This method extracts the text of the conversion result
     *
     * @return conversion result
     * @see ElementActionsManager#getTextFromField(By)
     */
    public String getConversionResult() {
        return getElementActionsManager().getTextFromField(swapUnitAnswer);
    }

    public ElementActionsManager getElementActionsManager() {
        return elementActionsManager;
    }
}
