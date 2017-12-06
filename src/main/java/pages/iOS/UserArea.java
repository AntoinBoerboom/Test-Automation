package pages.iOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import pages.PageObject;

/**
 * Created by marc
 * Date: 20/06/2017
 * Time: 22:03
 */
public abstract class UserArea extends PageObject {

    private static final Logger LOG = Logger.getLogger(UserArea.class);

    //Fixme: updaten id page_loaded
    private static final By BOTTOM_BAR = By.id("TabBar");

    private MenuBar menuBar;

    protected <T> UserArea(Class<T> page, By pageLoaded, Logger log) {
        super(page, pageLoaded, log);
        initMenuBar(page);
    }

    /**
     * Initialize a page with a menuBar,
     * wait for the menu bar locator to exist on the page
     *
     * @param page    the class for which to initialize the menu bar.
     * @param <T>     the page type.
     */
    private <T> void initMenuBar(final Class<T> page) {
        LOG.info(String.format("Initializing menuBar: [%s] ....", page.getName()));
        if (elementExists(BOTTOM_BAR)) {
            menuBar = new MenuBar(BOTTOM_BAR);
        }
    }

    public DashboardPage openDashboardPage() {
        return menuBar.gotoDashboardPage();
    }

    public HearingTestInstructionPage openHearingTestPage() {
        return menuBar.gotoHearingTestPage();
    }

    public SettingPage openSettingPage() {
        return menuBar.gotoSettingsPage();
    }

    public SupportPage openSupportPage() {
        return menuBar.gotoSupportPage();
    }

}
