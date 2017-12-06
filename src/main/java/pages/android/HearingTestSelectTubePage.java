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


public class HearingTestSelectTubePage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("activity_select_tube");

    @FindBy(id = "button_skip")
    private WebElement defaultTubeBtn;

    @FindBy(id = "next")
    private WebElement changedTubeBtn;

    @FindBy(id = "previous")
    private WebElement previousBtn;

    /**
     * Constructor for the class.
     */
    public HearingTestSelectTubePage() {
        super(HearingTestSelectTubePage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public HearingTestInternetConfirmationPage nextWithDefaultTubes() {
        return clickButton(defaultTubeBtn, HearingTestInternetConfirmationPage.class);
        //ToDo finish hearing test flow
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
