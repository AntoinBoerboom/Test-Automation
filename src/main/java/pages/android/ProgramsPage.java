package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 09/04/2017
 * Time: 00:08
 */


public class ProgramsPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(ProgramsPage.class);

    private static final By PAGE_LOADED = By.id("activity_program");

    @FindBy(id = "button_close")
    private WebElement closeBtn;

    @FindBy(id ="button_program_0")
    private WebElement automaticProgramBtn;

    @FindBy(id ="button_program_1")
    private WebElement quietProgramBtn;

    @FindBy(id ="button_program_2")
    private WebElement conversationProgramBtn;

    @FindBy(id ="button_program_3")
    private WebElement restaurantProgramBtn;

    /**
     * Constructor for the class.
     */
    public ProgramsPage() {
        super(ProgramsPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    // close program switch page

    public DashboardPage closeProgramSwitchPage() {
        return clickButton(closeBtn, DashboardPage.class);
    }

    // Verify the Login page has loaded
    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
