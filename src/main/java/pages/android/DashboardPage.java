package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by marc
 * Date: 08/04/2017
 * Time: 23:23
 */


public class DashboardPage extends UserArea {
    private static final Logger LOG = Logger.getLogger(DashboardPage.class);

    private static final By PAGE_LOADED = By.id("activity_dashboard");

    @FindBy(id = "button_program_button")
    private WebElement programSwitchBtn;

    @FindBy(id = "buttons_volume")
    private WebElement volumeSwitchBtn;

    /**
     * Constructor for the class.
     */
    public DashboardPage() {
        super(DashboardPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public ProgramsPage gotoProgramSwitchPage() {
        return clickButton(programSwitchBtn, ProgramsPage.class);
    }

    public VolumePage gotoVolumeSwitchPage() {
        return clickButton(volumeSwitchBtn, VolumePage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
