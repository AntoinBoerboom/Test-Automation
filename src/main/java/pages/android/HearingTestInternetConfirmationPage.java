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
 * Time: 12:57
 */


public class HearingTestInternetConfirmationPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("activity_check_internet_connection");

    @FindBy(id = "confirm_internet_connection")
    private WebElement confirmInternetConnectionBtn;

    @FindBy(id = "button_back_to_intro")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public HearingTestInternetConfirmationPage() {
        super(HearingTestInternetConfirmationPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public BindingPairedConfirmationRightPage next() {
        return clickButton(confirmInternetConnectionBtn, BindingPairedConfirmationRightPage.class);
        //ToDo finish hearing test flow
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
