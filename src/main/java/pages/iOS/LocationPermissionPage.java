package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.android.LoginPage;
import pages.PageObject;

/**
 * Created by marc
 * Date: 12/06/2017
 * Time: 23:41
 */


public class LocationPermissionPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("activity_location_permission");

    private static final By APP_ALLOW_BTN = By.id("button_give_permission");

    /**
     * Constructor for the class.
     */
    public LocationPermissionPage() {
        super(LocationPermissionPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    // before binding permission for bluetooth is needed
    public iOSBindingInstructionPage allowPermissions() {
        return grantPermission()
                .confirmPermission();
    }

    // app Bluetooth permission button
    public LocationPermissionPage grantPermission() {
        return clickButton(getElementLocated(APP_ALLOW_BTN), this.getClass());
    }

    // android platfrom permission button
    public iOSBindingInstructionPage confirmPermission() {
        fluentWaitForLocator(APP_ALLOW_BTN);
        return clickButton(getElementLocated(APP_ALLOW_BTN), iOSBindingInstructionPage.class);
    }

    // Verify the Login page has loaded
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}