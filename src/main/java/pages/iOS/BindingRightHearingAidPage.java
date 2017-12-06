package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 10/05/2017
 * Time: 18:04
 */
public class BindingRightHearingAidPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(iOSBindingInstructionPage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_unbox");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_restart")
    private WebElement restartSetupBtn;

    @FindBy(id ="Ok")
    private WebElement okRestartSetupBtn;

    private By alertTitle = By.id("Are you sure?");

    /**
     * Constructor for the class.
     */
    public BindingRightHearingAidPage() {
        super(BindingRightHearingAidPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }


    public BindingRightBatteryPage next() {
        return clickButton(nextBtn, BindingRightBatteryPage.class);
    }

    public iOSBindingInstructionPage restartSetup() {
        restartSetupBtn.click();
        fluentWaitForLocator(alertTitle);
        okRestartSetupBtn.click();
        return clickButton(restartSetupBtn, iOSBindingInstructionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
