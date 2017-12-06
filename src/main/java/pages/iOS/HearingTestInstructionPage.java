package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.android.LoginPage;
import pages.PageObject;

/**
 * Created by marc
 * Date: 17/06/2017
 * Time: 09:50
 */


public class HearingTestInstructionPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("hearing_test_start");

    @FindBy(id = "button_next")
    private WebElement createHearingProfileBtn;

    @FindBy(id = "button_about")
    private WebElement aboutHearingProfileBtn;

    @FindBy(id = "button_skip")
    private WebElement skipHearingTestBtn;

    @FindBy(id = "Do later")
    private WebElement confirmSkipHearTestBtn;

    private By alertTitle = By.id("Are you sure?");

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
        fluentWaitForLocator(alertTitle);
        return clickButton(confirmSkipHearTestBtn, DashboardPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
