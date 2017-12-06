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
 * Time: 12:20
 */


public class BindingBatteryLeftPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(BindingBatteryLeftPage.class);

    private static final By PAGE_LOADED = By.id("activity_add_hea_left");

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "previous")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public BindingBatteryLeftPage() {
        super(BindingBatteryLeftPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }


    public BindingPairedConfirmationLeftPage next() {
        return clickButton(nextBtn, BindingPairedConfirmationLeftPage.class);
    }

    public AndroidBindingInstructionPage previousBindingPage() {

        return clickButton(previousBtn, AndroidBindingInstructionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
