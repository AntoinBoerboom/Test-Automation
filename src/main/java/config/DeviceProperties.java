package config;

import enumerations.PlatformType;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import utility.FileHelper;

/**
 * Created by MarthijsBerfelo on 25-2-2016.
 * WebDriver properties object.
 * Properties are read from xml file.
 */
public class DeviceProperties extends PropertyConfiguration {

    // General device properties
    private static final String CONFIG_REALM = "devices";
    private static final XMLConfiguration CONFIG = createConfig(CONFIG_REALM);
    private static final ConfigurationNode ROOT_NODE = CONFIG.getRootNode();
    private static final String APPIUM_VERSION = getPropertyValue("appium-version", ROOT_NODE);
    private static final PlatformType PLATFORM_TYPE = setPlatformType(getPropertyValue("platformType", ROOT_NODE));
    private static final String PLATFORM_VERSION = getPropertyValue("platformVersion", ROOT_NODE);
    private static final String DEVICE_NAME = getPropertyValue("deviceName", ROOT_NODE);
    private static final String APP_PACKAGE = getPropertyValue("appPackage", ROOT_NODE);
    private static final boolean EMULATED = Boolean.parseBoolean(getPropertyValue("emulated", ROOT_NODE));

    //android device properties
    private static final String APP_ACTIVITY = getPropertyValue("appActivity", ROOT_NODE);
    private static final String APP_WAIT_ACTIVITY = getPropertyValue("appWaitActivity", ROOT_NODE);
    private static final String PERMISSIONS = System.getProperty("permissions");
    private static final String FILE_NAME_ANDROID = setFileName(System.getProperty("fileAndroid"));

    //iOS device properties
    private static final String BUNDLE_ID = getPropertyValue("bundleId",ROOT_NODE);
    private static final String UDID = getPropertyValue("UDID", ROOT_NODE);
    private static final String AUTOMATION_NAME = getPropertyValue("automationName", ROOT_NODE);
    private static final String FILE_NAME_IOS = setFileName(System.getProperty("fileIos"));

    //Todo: validate if permissions autoGrantPermissions also works for android 4,5,6,7?
    //https://discuss.appium.io/t/android-driver-will-not-accept-dismiss-android-device-alerts/14048/11


    //General device properties

    public DeviceProperties() {
        logConfiguration(ROOT_NODE, CONFIG_REALM);
    }

    public String getAppiumVersion() {
        return APPIUM_VERSION;
    }

    public String getPlatformVersion() {
        return PLATFORM_VERSION;
    }

    public String getDeviceName() {
        return DEVICE_NAME;
    }

    public String getFileNameAndroid(){return FILE_NAME_ANDROID;}

    public String getFileNameIos(){return FILE_NAME_IOS;}

    public boolean isEmulated() {
        return EMULATED;
    }

    //android device properties

    public String getAppPackage() {
        return APP_PACKAGE;
    }

    public String getAppActivity() {
        return APP_ACTIVITY;
    }

    public String getAppWaitActivity() {
        return APP_WAIT_ACTIVITY;
    }

    public String getPermissions(){ return PERMISSIONS; }


    //iOS device properties

    public String getBundleId(){return BUNDLE_ID;}

    public String getUdid(){return UDID;}

    public String getAutomationName(){return AUTOMATION_NAME;}

    /**
     * get platform type from the properties
     *
     * @return PlatformType enum
     */
    private static PlatformType setPlatformType(final String platform) {
        if ("".equalsIgnoreCase(platform)) {
            return PlatformType.IOS;
        }
        return PlatformType.valueOf(platform.toUpperCase());
    }

    private static String setFileName(final String appFilePath) {
        return FileHelper
                .getResourcePath()
                .resolve(appFilePath)
                .toAbsolutePath()
                .toString();
    }

    public PlatformType getPlatform() {
        if (PLATFORM_TYPE == null) {
            return PlatformType.IOS;
        }
        return PLATFORM_TYPE;
    }

}
