package pages.android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.PageObject;

/**
 * Created by marc
 * Date: 10/06/2017
 * Time: 17:54
 */


public class EmailConfirmationPage extends PageObject {
    private static final Logger LOG = Logger.getLogger(EmailConfirmationPage.class);

    public EmailConfirmationPage() {
        super(EmailConfirmationPage.class, By.id("todo"), LOG);
        PageFactory.initElements(driver, this);
    }

}
