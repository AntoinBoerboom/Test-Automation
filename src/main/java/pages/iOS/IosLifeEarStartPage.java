package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 07/04/2017
 * Time: 23:39
 */


public class IosLifeEarStartPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(IosLifeEarStartPage.class);

    private static final By PAGE_LOADED = By.id("account_start");

    @FindBy(id = "button_register")
    private WebElement registerBtn;

    @FindBy(id = "button_login")
    private WebElement loginBtn;

    @FindBy(id = "Log in with Facebook")
    private WebElement facebookBtn;

    private By imageViewLogo = By.id("Logo");

    /**
     * Constructor for the class.
     * This is the iOS start page
     *
     * @param driver the web driver instance to use
     */
    public IosLifeEarStartPage(WebDriver driver) {
        super(driver, IosLifeEarStartPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on the "already have an account button"
     *
     * @return: login page
     */
    public LoginPage existingUserLoginClick() {
        return clickButton(loginBtn, LoginPage.class);
    }

    public UserRegistrationPage newUserClick() {
        return clickButton(registerBtn, UserRegistrationPage.class);
    }

    /**
     * Verify the Earl app page has loaded
     **/
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
