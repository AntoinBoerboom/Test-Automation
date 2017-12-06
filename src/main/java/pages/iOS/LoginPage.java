package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 07/04/2017
 * Time: 23:33
 */
public class LoginPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("login");

    @FindBy(id = "textfield_email")
    private WebElement emailInput;

    @FindBy(id = "textfield_password")
    private WebElement passwordInput;

    @FindBy(id = "button_login")
    private WebElement submitBtn;

    @FindBy(id = "button_forgot_password")
    private WebElement forgotPasswordBtn;

    //Fixme: add correct id of the error message
    private By errorMessage = By.id("error_dialog");

    /**
     * Constructor for the class.
     */
    public LoginPage() {
        super(LoginPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public String getErrorMessage() {
        WebElement errorMessageField = getElementLocated(errorMessage);
        return errorMessageField.getText();
    }

    /**
     * Login functions
     **/
    //Todo fillInputField doesn't recognize the passwordInput field => feedback needed from Marthijs
    public <T> T login(String userName, String passWord, Class<T> expectedPage) {
        LOG.info("email field found: " + emailInput);
        fillInputField(emailInput, userName);
        LOG.info("passWord field found: " + passwordInput);
        fillInputField(passwordInput, passWord);
        return clickButton(submitBtn, expectedPage);
    }

    // Verify the Login page has loaded
    public boolean getLoginPageLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
