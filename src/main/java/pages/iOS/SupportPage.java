package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by marc
 * Date: 17/06/2017
 * Time: 21:01
 */


public class SupportPage extends UserArea {
    private static final Logger LOG = Logger.getLogger(SupportPage.class);

    private static final By PAGE_LOADED = By.id("support");

    @FindBy(id = "button_faq")
    private WebElement faqBtn;

    /**
     * Constructor for the class.
     */
    public SupportPage() {
        super(SupportPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }


}
