package config;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.log4j.Logger;

import java.net.URI;

/**
 * Created by MarthijsBerfelo on 25-2-2016.
 * WebDriver properties object.
 * Properties are read from xml file.
 */
public class WebDriverProperties extends PropertyConfiguration {
    private static final Logger LOG = Logger.getLogger(WebDriverProperties.class);

    private static final String CONFIG_REALM = "webdriver";
    public static final XMLConfiguration CONFIG = createConfig(CONFIG_REALM);
    private static final ConfigurationNode ROOT_NODE = CONFIG.getRootNode();

    private static final Long INTERVAL = Long.parseLong(getPropertyValue("interval", ROOT_NODE));
    private static final Integer TIME_OUT = Integer.parseInt(getPropertyValue("timeOut", ROOT_NODE));
    private static final Boolean REMOTE = Boolean.parseBoolean(getPropertyValue("Remote", ROOT_NODE));
    private static final URI SELENIUM_HUB_URL = setUrl(getPropertyValue("SeleniumHubUrl", ROOT_NODE));
    private static final Integer SELENIUM_HUB_PORT = Integer.parseInt(getPropertyValue("SeleniumHubPort", ROOT_NODE));

    public long getInterval() {
        if (INTERVAL == null) {
            return 500;
        }
        return INTERVAL;
    }

    public long getTimeOut() {
        if (TIME_OUT == null) {
            return 5;
        }
        return TIME_OUT;
    }

    public boolean isRemote() {
        return REMOTE;
    }

    public URI getSelHubUrl() {
        return SELENIUM_HUB_URL;
    }

    public int getSelHubPort() {
        if (SELENIUM_HUB_PORT == null) {
            return 4444;
        }
        return SELENIUM_HUB_PORT;
    }

}
