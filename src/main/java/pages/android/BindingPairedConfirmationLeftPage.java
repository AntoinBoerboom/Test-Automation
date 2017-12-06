package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 17/06/2017
 * Time: 12:29
 */


public class BindingPairedConfirmationLeftPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingPairedConfirmationLeftPage.class);

    private static final By PAGE_LOADED = By.id("activity_register");

    private By doneBtn = By.id("next");

    /**
     * Constructor for the class.
     */
    public BindingPairedConfirmationLeftPage() {
        super(BindingPairedConfirmationLeftPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public HearingTestInstructionPage next() {
        fluentWaitForLocator(doneBtn);
        return clickButton(getElementLocated(doneBtn), HearingTestInstructionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
