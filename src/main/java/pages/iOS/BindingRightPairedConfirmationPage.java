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
 * Time: 12:29
 */


public class BindingRightPairedConfirmationPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingRightPairedConfirmationPage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_bond");

    private By doneBtn = By.id("button_next");


    /**
     * Constructor for the class.
     */
    public BindingRightPairedConfirmationPage() {
        super(BindingRightPairedConfirmationPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public HearingTestInstructionPage nextSingleBindingFlow() {
        fluentWaitForLocator(doneBtn);
        return clickButton(getElementLocated(doneBtn), HearingTestInstructionPage.class);
    }

    public BindingLeftHearingAidPage nextBothBindingFlow(){
        fluentWaitForLocator(doneBtn);
        return clickButton(getElementLocated(doneBtn),BindingLeftHearingAidPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
