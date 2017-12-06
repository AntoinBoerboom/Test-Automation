package utility;

import config.DeviceProperties;
import config.UserProperties;
import config.WebDriverProperties;
import logging.LogFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.AppView;
import pages.android.AndroidLifeEarStartPage;
import pages.android.DashboardPage;
import pages.iOS.IosLifeEarStartPage;
import pages.android.AndroidBindingInstructionPage;
import pages.iOS.iOSBindingInstructionPage;
import webdriverfactory.TestDriverFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Marc van 't Veer on april 2017.
 * Superclass for running tests
 */
public class TestBase {
    private static final Logger LOG = Logger.getLogger(TestBase.class);

    public static WebDriver driver = null;
    public static WebDriverProperties wdConfig;
    public static DeviceProperties deviceConfig;
    protected static UserProperties userConfig;

    public TestBase() {
        LogFactory.initLogging();
        initConfig();
    }

    /**
     * Wait for seconds...
     *
     * @param seconds the number of seconds to pause
     */
    protected static void waitForSeconds(final int seconds) {
        final int pause = seconds * 1000;
        try {
            Thread.sleep(pause);
            LOG.warn("HARD WAIT FOR " + seconds + " seconds ...");
        } catch (InterruptedException e) {
            LOG.error("Interrupted during pause ...", e);
        }
    }

    /**
     * Change value of a static field using reflection
     *
     * @param field    the field to change
     * @param newValue the new value
     * @throws Exception exception if field modification fails
     */
    public static void setFinalStatic(final Field field, final Object newValue) throws Exception {
        LOG.info(String.format("Modifying field [%s].[%s], setting value [%s]",
                field.getDeclaringClass().getName(), field.getName(), newValue.toString()));
        field.setAccessible(true);

        final Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    private void initConfig() {
        if (wdConfig == null) {
            wdConfig = new WebDriverProperties();
        }
        if (deviceConfig == null) {
            deviceConfig = new DeviceProperties();
        }
        if (userConfig == null) {
            userConfig = new UserProperties();
        }
    }

    /**
     * Starts the driver to connect with the device and opens the android app.
     *
     * @return the start page, after it is loaded.
     */
    protected AndroidLifeEarStartPage startAndroidApp() {
        new TestDriverFactory().createDriver();
        return new AndroidLifeEarStartPage(driver);
    }

    /**
     * Starts the driver to connect with the device and opens the iOS app.
     *
     * @return the start page, after it is loaded.
     */
    protected IosLifeEarStartPage startIosApp() {
        new TestDriverFactory().createDriver();
        return new IosLifeEarStartPage(driver);
    }

    /**
     * Starts the android app, navigates to Login page and expects successful androidLogin.
     *
     * @param userName a valid registered user.
     * @param password a valid password.
     * @return the bind hearing aid page, after it is loaded.
     */
    protected AndroidBindingInstructionPage androidLogin(String userName, String password) {
        return startAndroidApp()
                .existingUserLoginClick()
                .login(userName, password, AndroidBindingInstructionPage.class);
    }

    /**
     * Starts the iOS app, navigates to Login page and expects successful androidLogin.
     *
     * @param userName a valid registered user.
     * @param password a valid password.
     * @return the bind hearing aid page, after it is loaded.
     */
    protected iOSBindingInstructionPage iosLogin(String userName, String password) {
        return startIosApp()
                .existingUserLoginClick()
                .login(userName,password,iOSBindingInstructionPage.class);
    }

    /**
     * Starts the android app, expects successful androidLogin, and runs default setup.
     *
     * @param userName a valid registered user.
     * @param password a valid password.
     * @return the dashboard page, after it is loaded.
     */
    protected DashboardPage androidLoginAndProcessUntilDashboard(String userName, String password) {
        return androidLogin(userName, password)
                .bothEarsBinding()  //bind both ears
                .next() // take out right earing aid
                .next() // battery is in right earing aid
                .nextBothBindingFlow() // take out left hearing aid
                .next() // battery is in left earing aid
                .next() // confirm left hearing aid
                .next() // hearing test
                .skipHearingTest(); // dashboard page is going to be opend
    }

    /**
     * Starts the iOS app, expects successful androidLogin, and runs default setup.
     *
     * @param userName a valid registered user.
     * @param password a valid password.
     * @return the dashboard page, after it is loaded.
     */
    protected AppView iosLoginAndProcessUntilDashboard(String userName, String password) {
        return iosLogin(userName, password);
    }

    //todo: add app reset method if you need to restart the app

//    	androidCapabilities.setCapability("fullReset", true);
//            driver.resetApp()

}
