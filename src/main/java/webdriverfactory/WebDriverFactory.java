package webdriverfactory;


import config.DeviceProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import webdriverfactory.configurations.LocalMobileDriverConfiguration;
import webdriverfactory.configurations.RemoteDriverConfiguration;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by marthijs on 9/18/15.
 * Factory to create web driver instance
 */
public class WebDriverFactory {
    private static final Logger LOG = Logger.getLogger(WebDriverFactory.class);

    private static DesiredCapabilities capabilities;
    private DeviceProperties deviceProperties;

    /**
     * create remote web driver to a cloud farm
     *
     * @param configuration remote driver configuration
     * @return a web driver instance for remote execution of tests
     */
    WebDriver create(final RemoteDriverConfiguration configuration) {
        URL remoteServer = buildRemoteServer(configuration.seleniumHubUrl, configuration.seleniumHubPort);
        WebDriver driver;

        // TODO: fill capabilities
        driver = new RemoteWebDriver(remoteServer, capabilities);
        LOG.info("Successfully created new remote web driver");

        return driver;
    }

    AppiumDriver create(final LocalMobileDriverConfiguration configuration) {
        URL deviceServer = buildRemoteServer(configuration.seleniumHubUrl, configuration.seleniumHubPort);
        AppiumDriver driver;
        deviceProperties = new DeviceProperties();
        capabilities = new DesiredCapabilities();
        String appFileNameIos = deviceProperties.getFileNameIos();
        Boolean emulated = deviceProperties.isEmulated();

        switch (configuration.platform) {
            case IOS:
                capabilities.setCapability("appium-version", deviceProperties.getAppiumVersion());
                capabilities.setCapability("platformName", deviceProperties.getPlatform().toString());
                capabilities.setCapability("platformVersion", deviceProperties.getPlatformVersion());
                capabilities.setCapability("deviceName", deviceProperties.getDeviceName());
                capabilities.setCapability("bundleId", deviceProperties.getBundleId());
                capabilities.setCapability("UDID", deviceProperties.getUdid());

                // switch between .app file for emulator and .ipa for physical iPhone

                if(emulated){
                    LOG.info("appFileNameIos is: " + appFileNameIos.concat(".app"));
                    capabilities.setCapability("app", appFileNameIos.concat(".app"));
                }else capabilities.setCapability("app", appFileNameIos.concat(".ipa"));

                // after migration to XCUITest an extra capability should be set for only ios 9 platform
                if(deviceProperties.getPlatformVersion().equalsIgnoreCase("9.3")){
                    LOG.info("plaformVersion is: " + deviceProperties.getPlatformVersion());
                    capabilities.setCapability("automationName",deviceProperties.getAutomationName());
                }
                LOG.info("plaformVersion is: " + deviceProperties.getPlatformVersion());

                driver = new IOSDriver(deviceServer, capabilities);

                LOG.info("Successfully created new local ios app iosDriver");
                break;
            case ANDROID:
                LOG.info("Android is the default");
            default:
                capabilities.setCapability("appium-version", deviceProperties.getAppiumVersion());
                capabilities.setCapability("platformName", deviceProperties.getPlatform().toString());
                capabilities.setCapability("platformVersion", deviceProperties.getPlatformVersion());
                capabilities.setCapability("appPackage", deviceProperties.getAppPackage());
                capabilities.setCapability("deviceName", deviceProperties.getDeviceName());
                capabilities.setCapability("appActivity", deviceProperties.getAppActivity());
                capabilities.setCapability("appWaitActivity", deviceProperties.getAppWaitActivity());
                capabilities.setCapability("app", deviceProperties.getFileNameAndroid());
                capabilities.setCapability("autoGrantPermissions", deviceProperties.getPermissions());

                driver = new AndroidDriver(deviceServer, capabilities);

                LOG.info("Successfully created new local android app androidDriver");
        }

        return driver;
    }

    /**
     * set the url for the remote server (grid)
     *
     * @param remoteServer     the uri address of the remote server
     * @param remoteServerPort the port of the remote server
     * @return URL for the remote server
     */
    private URL buildRemoteServer(final URI remoteServer, final int remoteServerPort) {
        URI u = null;
        String s = String.format("%s:%d/wd/hub", remoteServer.toString(), remoteServerPort);

        // TODO: fill capabilities
        try {
            u = new URIBuilder(s).build();
            LOG.info("URI constructed for the remote server: " + u);
            return u.toURL();
        } catch (URISyntaxException uEx) {
            LOG.error(String.format("Error building uri with inputs: %s and %d ERROR message: %s",
                    remoteServer, remoteServerPort, uEx.getMessage()), uEx);
            return null;
        } catch (MalformedURLException e) {
            LOG.error(String.format("Error while converting to URL, using URI: %s ERROR message: %s ", u, e.getMessage()), e);
        }
        return null;
    }
}
