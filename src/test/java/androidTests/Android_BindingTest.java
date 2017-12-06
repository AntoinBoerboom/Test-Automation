package androidTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.android.AndroidLifeEarStartPage;
import pages.android.DashboardPage;
import pages.android.MenuBar;
import utility.TestBase;
import utility.testlisteners.ScreenshotHelper;

/**
 * Created by marc
 * Date: 07/04/2017
 * Time: 17:09
 */

@Listeners({ScreenshotHelper.class})
public class Android_BindingTest extends TestBase {
    private static final Logger LOG = Logger.getLogger(Android_BindingTest.class.getName());

    @BeforeClass
    public void classSetup() throws InstantiationException {
        LOG.info("Starting class setup ... ");
    }

    @AfterClass
    public void classTearDown() {
        LOG.info("All tests in class finished, starting class tear down ... ");
        driver.quit();
    }

    MenuBar menuBar;

    @Test(groups = {"development", "android"})
    public void shouldBindBothEars() throws Exception {

        DashboardPage dashboardPage = androidLogin(userConfig.getUserName(), userConfig.getPassword())
                .bothEarsBinding()  //bind both ears
                .next() // take out right earing aid
                .next() // battery is in right earing aid
                .nextBothBindingFlow() // take out left hearing aid
                .next() // battery is in left earing aid
                .next() // confirm left hearing aid
                .next() // hearing test
                .skipHearingTest(); // dashboard page is going to be opend

        assertThat(dashboardPage)
                .returns(true, DashboardPage::isLoaded);

    }
    //Fixme: logout flow after binding
    // restart binding after user logout
    @Test(groups = {"development","android"})
    public void loginBindAndLogoutUser() throws Exception {

        AndroidLifeEarStartPage androidEarlStartPage = androidLogin(userConfig.getUserName(), userConfig.getPassword())
                .leftEarBinding()
                .next() // battery is in left earing aid
                .next() // confirm left hearing aid
                .next() // hearing test
                .skipHearingTest() // dashboard page is going to be opend
                .openSettingPage()
                .userClick()
                .logoutUser();

        assertThat(androidEarlStartPage)
                .returns(true, AndroidLifeEarStartPage::isLoaded);

        //Todo: add a soft assert, see https://rameshbaskar.wordpress.com/2013/09/11/soft-assertions-using-testng/
    }


    @Test(groups = {"development", "android"})
    public void shouldBindRightEars() throws Exception {

        DashboardPage dashboardPage = androidLogin(userConfig.getUserName(), userConfig.getPassword())
                .rightEarBinding() // bind only right hearing aid
                .next() // take out right earing aid
                .next() // battery is in right earing aid
                .nextSingleBindingFlow() // hearing test
                .skipHearingTest(); // dashboard page is going to be opened

        assertThat(dashboardPage)
                .returns(true, DashboardPage::isLoaded);

        menuBar.gotoSettingsPage()
                .userClick()
                .logoutUser();
    }

}
