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


public class BindingRightBatteryPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingRightBatteryPage.class);

    private static final By PAGE_LOADED = By.id("setup_aid_turn_on");

    @FindBy(id = "button_next")
    private WebElement nextBtn;

    @FindBy(id = "button_back")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public BindingRightBatteryPage() {
        super(BindingRightBatteryPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }



    public BindingRightHearingAidFoundPage next() {
        return clickButton(nextBtn, BindingRightHearingAidFoundPage.class);
    }

    public iOSBindingInstructionPage previousBindingPage() {

        return clickButton(previousBtn, iOSBindingInstructionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
