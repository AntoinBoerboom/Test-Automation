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
public class BindingRightHearingAidFoundPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingRightHearingAidFoundPage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_search");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_restart")
    private WebElement restartSetupBtn;

    @FindBy(id ="Ok")
    private WebElement okRestartSetupBtn;

    @FindBy(id = "button_try_again")
    private WebElement tryAgainBtn;

    private By alertTitle = By.id("Are you sure?");

    private By rightHearingAidFound = By.id("Right aid found.");

        /**
     * Constructor for the class.
     */
    public BindingRightHearingAidFoundPage() {
        super(BindingRightHearingAidFoundPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public BindingRightBluetoothPairMessagePage next() {
        fluentWaitForLocator(rightHearingAidFound);
        return clickButton(nextBtn, BindingRightBluetoothPairMessagePage.class);
    }

    public BindingRightHearingAidFoundPage trySearchAgain(){
        fluentWaitForElement(tryAgainBtn);
        return clickButton(tryAgainBtn,BindingRightHearingAidFoundPage.class);
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
