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


public class BindingLeftBluetoothPairMessagePage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingLeftBluetoothPairMessagePage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_bond");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_back")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public BindingLeftBluetoothPairMessagePage() {
        super(BindingLeftBluetoothPairMessagePage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }


    public HearingTestInstructionPage next() {
        return clickButton(nextBtn, HearingTestInstructionPage.class);
    }

    public BindingLeftHearingAidFoundPage previousBindingPage() {

        return clickButton(previousBtn, BindingLeftHearingAidFoundPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
