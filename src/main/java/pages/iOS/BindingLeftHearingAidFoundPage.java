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
public class BindingLeftHearingAidFoundPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingLeftHearingAidFoundPage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_search");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_restart")
    private WebElement restartSetupBtn;

    @FindBy(id ="Ok")
    private WebElement okRestartSetupBtn;

    @FindBy(id = "button_try_again")
    private WebElement tryAgainBtn;

    private By leftHearingAidFound = By.id("Left aid found.");

    private By alertTitle = By.id("Are you sure?");

    /**
     * Constructor for the class.
     */
    public BindingLeftHearingAidFoundPage() {
        super(BindingLeftHearingAidFoundPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public BindingLeftBluetoothPairMessagePage next() {
        fluentWaitForLocator(leftHearingAidFound);
        return clickButton(nextBtn, BindingLeftBluetoothPairMessagePage.class);
    }

    public BindingLeftHearingAidFoundPage trySearchAgain(){
        fluentWaitForElement(tryAgainBtn);
        return clickButton(tryAgainBtn,BindingLeftHearingAidFoundPage.class);
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
