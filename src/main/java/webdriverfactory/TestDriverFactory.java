package webdriverfactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utility.TestBase;
import webdriverfactory.configurations.LocalMobileDriverConfiguration;
import webdriverfactory.configurations.RemoteDriverConfiguration;

/**
 * Created by marthijs on 9/19/15.
 * Factory for the web driver to be used for the tests
 */
public class TestDriverFactory {
    private static final Logger LOG = Logger.getLogger(TestDriverFactory.class);

    /**
     * create web driver instance based on CLI input and chosen CONFIG xml file
     */
    public void createDriver() {
        WebDriver driver;

        if (TestBase.wdConfig.isRemote()) {

            LOG.info("Creating remote web driver instance");
            driver = new WebDriverFactory().create(
                    new RemoteDriverConfiguration(
                            TestBase.deviceConfig.getPlatform(),
                            TestBase.wdConfig.getSelHubUrl(),
                            TestBase.wdConfig.getSelHubPort()));
            try {
                TestBase.setFinalStatic(TestBase.class.getField("driver"), driver);
            } catch (Exception e) {
                LOG.error("ERROR setting [final static remote WebDriver driver] in TestBase.class..", e);
            }

        } else {

            LOG.info("Creating mobile driver instance");
            driver = new WebDriverFactory().create(
                    new LocalMobileDriverConfiguration(
                            TestBase.deviceConfig.getPlatform(),
                            TestBase.wdConfig.getSelHubUrl(),
                            TestBase.wdConfig.getSelHubPort()));
            try {
                TestBase.setFinalStatic(TestBase.class.getField("driver"), driver);
            } catch (Exception e) {
                LOG.error("ERROR setting [final static driver] in TestBase.class..", e);
            }
        }
    }
}
