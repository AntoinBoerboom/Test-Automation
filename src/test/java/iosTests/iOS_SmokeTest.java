package iosTests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.iOS.iOSBindingInstructionPage;
import pages.iOS.IosLifeEarStartPage;
import utility.TestBase;
import utility.testlisteners.ScreenshotHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;


/**
 * Created by tuba on 03/05/2017.
 */
@Listeners({ScreenshotHelper.class})
public class iOS_SmokeTest extends TestBase {
    private static final Logger LOG = Logger.getLogger(iOS_SmokeTest.class);

    @AfterClass
    public void classTearDown() {
        LOG.info("All tests in class finished, starting class tear down ... ");
        driver.quit();
    }

    @Test(groups = {"smoke", "ios"})
    public void earlShouldStart() throws Exception {

        TestBase.deviceConfig.getFileNameAndroid();
        IosLifeEarStartPage startPage = startIosApp();

        assertThat(startPage)
                .returns(true,
                        from(IosLifeEarStartPage::isLoaded));
    }

    @Test(groups = {"smoke", "ios"})
    public void shouldStartLoginAndLoadBindingSetup() throws Exception {
        iOSBindingInstructionPage bindingInstructionPage = iosLogin(userConfig.getUserName(),
                userConfig.getPassword());

        assertThat(bindingInstructionPage)
                .returns(true, iOSBindingInstructionPage::isLoaded);
    }
}
