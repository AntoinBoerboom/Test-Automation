package pages.android;

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
public class BindingLeftHearingAidPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingLeftHearingAidPage.class);

    private static final By PAGE_LOADED = By.id("activity_add_hea_left");

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "button_restart_onboarding")
    private WebElement restartSetupBtn;

    private By alertTitle = By.id("alertTitle");

    /**
     * Constructor for the class.
     */
    public BindingLeftHearingAidPage() {
        super(BindingLeftHearingAidPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public BindingBatteryLeftPage next() {
        return clickButton(nextBtn, BindingBatteryLeftPage.class);
    }

    public AndroidBindingInstructionPage restartSetup() {
        restartSetupBtn.click();
        fluentWaitForLocator(alertTitle);
        return clickButton(restartSetupBtn, AndroidBindingInstructionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
