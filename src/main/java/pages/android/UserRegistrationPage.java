package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by marc
 * Date: 07/04/2017
 * Time: 23:16
 */


public class UserRegistrationPage extends UserArea {
    private static final Logger LOG = Logger.getLogger(LoginPage.class.getName());

    private static final By PAGE_LOADED = By.id("activity_register");

    @FindBy(id = "field_register_email")
    private WebElement registerEmailInput;

    @FindBy(id = "field_register_password")
    private WebElement registerPasswordInput;

    @FindBy(id = "field_register_password_repeat")
    private WebElement registerPasswordRepeatInput;

    @FindBy(id = "button_register_submit")
    private WebElement submitRegisterBtn;

    private By errorMessage = By.id("snackbar_text");

    /**
     * Constructor for the class.
     */
    public UserRegistrationPage() {
        super(UserRegistrationPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    // validate if an error message is shown, not which error text is shown.
    public boolean hasErrorMessage() {
        return elementExists(errorMessage);
    }


    public String getErrorMessage() {
        WebElement errorMessageField = getElementLocated(errorMessage);
        return errorMessageField.getText();
    }

    public void FillInTextField(WebElement element, String text) {
        element.sendKeys(text);
    }


    public <T> T register(String userName, String passWord, String confirmationPassWord, Class<T> expectedPage) {
        LOG.info("email field found: " + registerEmailInput);
        fillInputField(registerEmailInput, userName);
        LOG.info("password field found: " + registerPasswordInput);
        fillInputField(registerPasswordInput, passWord);
        LOG.info("password confirmation field found:" + registerPasswordRepeatInput);
        fillInputField(registerPasswordRepeatInput, confirmationPassWord);
        LOG.info("submit registration button found:" + submitRegisterBtn);
        return clickButton(submitRegisterBtn, expectedPage);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
