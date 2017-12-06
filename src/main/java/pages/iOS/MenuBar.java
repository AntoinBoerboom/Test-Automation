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
 * Time: 20:34
 */


public class MenuBar extends PageObject {
    private static final Logger LOG = Logger.getLogger(MenuBar.class);

    //Fixme: updaten id page_loaded
    private static final By PAGE_LOADED = By.id("TabBar");

    @FindBy(id = "Dashboard")
    private WebElement dashboardSwitchBtn;

    @FindBy(id = "Hearing profile")
    private WebElement hearingProfileSwitchBtn;

    @FindBy(id = "Settings")
    private WebElement settingsSwitchBtn;

    @FindBy(id = "Support")
    private WebElement supportSwitchBtn;

    /**
     * Constructor for the class.
     */
    public MenuBar(By menuBarLocator) {
        super(MenuBar.class, menuBarLocator, LOG);
        PageFactory.initElements(driver, this);
    }

    public DashboardPage gotoDashboardPage() {
        return clickButton(settingsSwitchBtn, DashboardPage.class);
    }

    public HearingTestInstructionPage gotoHearingTestPage() {
        return clickButton(settingsSwitchBtn, HearingTestInstructionPage.class);
    }

    public SettingPage gotoSettingsPage() {
        return clickButton(settingsSwitchBtn, SettingPage.class);
    }

    public SupportPage gotoSupportPage() {
        return clickButton(supportSwitchBtn, SupportPage.class);
    }

    // Verify the Login page has loaded
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
