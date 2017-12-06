package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 11/06/2017
 * Time: 08:43
 */


public class InstructionAfterRegistrationPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(InstructionAfterRegistrationPage.class);

    //Fixme: add implementation
    private static final By PAGE_LOADED = By.id("todo");

    public InstructionAfterRegistrationPage() {
        super(InstructionAfterRegistrationPage.class, PAGE_LOADED, LOG);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return elementExists(PAGE_LOADED);
    }
}
