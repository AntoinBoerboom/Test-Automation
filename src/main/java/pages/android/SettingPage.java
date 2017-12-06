package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by marc
 * Date: 17/06/2017
 * Time: 21:00
 */


public class SettingPage extends UserArea {
    private static final Logger LOG = Logger.getLogger(SettingPage.class);

    private static final By PAGE_LOADED = By.id("activity_settings");


    @FindBy(id = "button_details_user")
    private WebElement userBtn;

    /**
     * Constructor for the class.
     */

    public SettingPage() {
        super(SettingPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public SettingsUserPage userClick() {
        return clickButton(userBtn, SettingsUserPage.class);
    }

    /**
     * Verify the Settings page has loaded
     **/
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
