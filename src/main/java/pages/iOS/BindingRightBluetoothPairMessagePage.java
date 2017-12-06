package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 17/06/2017
 * Time: 12:20
 */


public class BindingRightBluetoothPairMessagePage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingRightBluetoothPairMessagePage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_bond");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_back")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public BindingRightBluetoothPairMessagePage() {
        super(BindingRightBluetoothPairMessagePage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }


    public HearingTestInstructionPage nextSingleBindingFlow() {
        fluentWaitForElement(nextBtn);
        return clickButton(nextBtn, HearingTestInstructionPage.class);
    }

    public BindingLeftHearingAidPage nextBothBindingFlow(){
        fluentWaitForElement(nextBtn);
        return clickButton(nextBtn,BindingLeftHearingAidPage.class);
    }

    public BindingRightHearingAidFoundPage previousBindingPage() {

        return clickButton(previousBtn, BindingRightHearingAidFoundPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}



