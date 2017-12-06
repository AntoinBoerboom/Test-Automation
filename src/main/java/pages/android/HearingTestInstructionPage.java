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
 * Time: 09:50
 */


public class HearingTestInstructionPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("activity_measurement_intro");

    @FindBy(id = "button_create_hearing_profile")
    private WebElement createHearingProfileBtn;

    @FindBy(id = "button_about_hearing_profile")
    private WebElement aboutHearingProfileBtn;

    @FindBy(id = "button_preparation_do_this_later")
    private WebElement skipHearingTestBtn;

    @FindBy(id = "button1")
    private WebElement confirmSkipHearTestBtn;

    private By alertTitle = By.id("alertTitle");

    /**
     * Constructor for the class.
     */
    public HearingTestInstructionPage() {
        super(HearingTestInstructionPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public HearingTestSelectTubePage createHearingProfile(){
        return clickButton(createHearingProfileBtn,HearingTestSelectTubePage.class);
    }

    public DashboardPage skipHearingTest() {
        skipHearingTestBtn.click();
        return clickButton(confirmSkipHearTestBtn, DashboardPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
