package pages.android;

import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by marc
 * Date: 18/06/2017
 * Time: 23:22
 */


public class SettingsUserPage extends UserArea {
    private static final Logger LOG = Logger.getLogger(SettingPage.class);

    private static final By PAGE_LOADED = By.id("activity_settings_user");

    @FindBy(id = "button_logout")
    private WebElement logoutBtn;

    @FindBy(id = "button1")
    private WebElement confirmLogoutUserBtn;

    private By alertTitle = By.id("alertTitle");

    private TouchAction moveTo;

    /**
     * Constructor for the class.
     */
    public SettingsUserPage() {
        super(SettingsUserPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public AndroidLifeEarStartPage logoutUser() {
        moveTo.moveTo(logoutBtn);
        //moveTo.moveTo(0,-10);
        logoutBtn.click();
        return clickButton(confirmLogoutUserBtn, AndroidLifeEarStartPage.class);
    }

    /**
     * Verify the User settings page has loaded
     **/
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }


}


