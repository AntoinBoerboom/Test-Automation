package androidTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.android.AndroidBindingInstructionPage;
import pages.android.InstructionAfterRegistrationPage;
import pages.android.LoginPage;
import utility.TestBase;
import utility.testlisteners.ScreenshotHelper;

/**
 * Created by marc
 * Date: 07/04/2017
 * Time: 17:09
 */

@Listeners({ScreenshotHelper.class})
public class Android_AuthenticationTest extends TestBase {
    private static final Logger LOG = Logger.getLogger(Android_AuthenticationTest.class.getName());

    @AfterClass
    public void classTearDown() {
        LOG.info("All tests in class finished, starting class tear down ... ");
        driver.quit();
    }

    @Test(groups = {"development", "android"})
    public void shouldLoginSuccessful() throws Exception {
        AndroidBindingInstructionPage bindHearingAidPage = androidLogin(userConfig.getUserName(),
                userConfig.getPassword());

        assertThat(bindHearingAidPage)
                .returns(true, from(AndroidBindingInstructionPage::isLoaded));
    }

    @Test(groups = {"development", "android"})
    public void shouldFailLoginForInvalidUser() throws Exception {
        assertThat(performBadLoginAndGetErrorMessage("invalid@invalid.com", userConfig.getPassword()))
                .startsWith("Unable")
                .contains("Invalid credentials");
    }

    @Test(groups = {"development", "android"})
    public void shouldFailLoginForBadPassword() throws Exception {
        assertThat(performBadLoginAndGetErrorMessage(userConfig.getUserName(), "!@#$%^&*"))
                .startsWith("Unable")
                .contains("Invalid credentials");
    }

    @Test(groups = {"development", "android"})
    public void shouldRegisterNewUser() throws Exception {
        InstructionAfterRegistrationPage instructionPage = startAndroidApp()
                .newUserClick()
                .register("new.user@new-email.com", "P@ssw0rd",
                        "P@ssw0rd",
                        InstructionAfterRegistrationPage.class);
        assertThat(instructionPage)
                .returns(true, from(InstructionAfterRegistrationPage::isLoaded));
    }


    private String performBadLoginAndGetErrorMessage(String userName, String password) {
        return startAndroidApp()
                .existingUserLoginClick()
                .login(userName, password, LoginPage.class)
                .getErrorMessage();
    }
}
