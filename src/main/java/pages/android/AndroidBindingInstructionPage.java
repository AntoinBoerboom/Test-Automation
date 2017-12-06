package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 10/05/2017
 * Time: 18:04
 */
public class AndroidBindingInstructionPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private static final By PAGE_LOADED = By.id("activity_instruction");

    @FindBy(id = "orientationBoth")
    private WebElement bothEarsBtn;

    @FindBy(id = "orientationLeftEar")
    private WebElement leftEarBtn;

    @FindBy(id = "orientationRightEar")
    private WebElement rightEarBtn;

    /**
     * Constructor for the class.
     */
    public AndroidBindingInstructionPage() {
        super(AndroidBindingInstructionPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }


    public BindingRightHearingAidPage bothEarsBinding() {
        return clickButton(bothEarsBtn, BindingRightHearingAidPage.class);
    }

    public BindingLeftHearingAidPage leftEarBinding() {
        return clickButton(leftEarBtn, BindingLeftHearingAidPage.class);
    }

    public BindingRightHearingAidPage rightEarBinding() {
        return clickButton(rightEarBtn, BindingRightHearingAidPage.class);
    }

    // bluetooth permission page after selecting binding booth pages
    public LocationPermissionPage bindBothEarsWithBluetoothPermissionPage(){
        return clickButton(bothEarsBtn,LocationPermissionPage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
