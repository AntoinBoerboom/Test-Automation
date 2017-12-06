package androidTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.android.AndroidLifeEarStartPage;
import pages.android.AndroidBindingInstructionPage;
import utility.TestBase;
import utility.testlisteners.ScreenshotHelper;


/**
 * Created by tuba on 03/05/2017.
 */
@Listeners({ScreenshotHelper.class})
public class Android_SmokeTest extends TestBase {
    private static final Logger LOG = Logger.getLogger(Android_SmokeTest.class);

    @AfterClass
    public void classTearDown() {
        LOG.info("All tests in class finished, starting class tear down ... ");
        driver.quit();
    }

    @Test(groups = {"smoke", "android"})
    public void earlShouldStart() throws Exception {

        AndroidLifeEarStartPage startPage = startAndroidApp();

        assertThat(startPage)
                .returns(true,
                        from(AndroidLifeEarStartPage::isLoaded));
    }

    @Test(groups = {"smoke", "android"})
    public void shouldStartLoginAndLoadBindingSetup() throws Exception {
        AndroidBindingInstructionPage bindingInstructionPage = androidLogin(userConfig.getUserName(),
                userConfig.getPassword());

        assertThat(bindingInstructionPage)
                .returns(true, AndroidBindingInstructionPage::isLoaded);
    }
}
