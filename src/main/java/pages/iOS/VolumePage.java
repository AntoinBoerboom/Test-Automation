package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 08/04/2017
 * Time: 23:57
 */


public class VolumePage extends PageObject {
    private static final Logger LOG = Logger.getLogger(VolumePage.class);

    private static final By PAGE_LOADED = By.id("volume");

    @FindBy(id = "slider_left")
    private WebElement sliderLeftUpBtn;

    @FindBy(id = "slider_right")
    private WebElement sliderRighttUpBtn;

    @FindBy(id = "button_volume_up")
    private WebElement buttonVolumeUpBtn;

    @FindBy(id = "button_volume_down")
    private WebElement buttonVolumeDownBtn;

    @FindBy(id = "button_close")
    private WebElement closeBtn;

    /**
     * Constructor for the class.
     */
    public VolumePage() {
        super(VolumePage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public DashboardPage closeVolumePage() {
        return clickButton(closeBtn, DashboardPage.class);
    }

    public void setVolumeUpBtn(){
        clickButton(buttonVolumeUpBtn, VolumePage.class);
    }

    public void setVolumeDownBtn(){
        clickButton(buttonVolumeDownBtn, VolumePage.class);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }

}
