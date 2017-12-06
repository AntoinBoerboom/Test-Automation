package pages.android;

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


public class BindingPairedConfirmationRightPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingPairedConfirmationRightPage.class);

    private static final By PAGE_LOADED = By.id("activity_add_hea_right");

    private By doneBtn = By.id("next");

    //Fixme: updaten id Ok button
    @FindBy(id = "button_restart")
    private WebElement restartBindingBtn;

    /**
     * Constructor for the class.
     */
    public BindingPairedConfirmationRightPage() {
        super(BindingPairedConfirmationRightPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public HearingTestInstructionPage nextSingleBindingFlow() {
        fluentWaitForLocator(doneBtn);
        return clickButton(getElementLocated(doneBtn), HearingTestInstructionPage.class);
    }

    public BindingLeftHearingAidPage nextBothBindingFlow(){
        return clickButton(getElementLocated(doneBtn),BindingLeftHearingAidPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
