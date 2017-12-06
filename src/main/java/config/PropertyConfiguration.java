package config;

import com.typesafe.config.ConfigException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.log4j.Logger;
import utility.FileHelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by MarthijsBerfelo on 25-2-2016.
 * PropertyConfiguration super object.
 * System properties determine the property details.
 */
public class PropertyConfiguration {
    private static final Logger LOG = Logger.getLogger(PropertyConfiguration.class);

    private static final String DIR_SEP = FileHelper.getDirSep();
    private static final String CONFIG_ROOT_DIR
            = String.format("%2$s %1$s config %1$s", DIR_SEP, FileHelper.getResourcePath()).replace(" ", "");

    /**
     * Get the full configuration file name
     *
     * @param configRealm the realm of the configuration
     * @return file name with relative path and extension (.xml)
     */
    private static String getFullCfgFileName(final String configRealm) {
        String sysPropName = configRealm;
        String configVariant;
        if ("webdriver".equalsIgnoreCase(configRealm)) {
            sysPropName = "webDriverConfigType";
        }
        if ("user".equalsIgnoreCase(configRealm)) {
            sysPropName = "userSettingsFile";
        }
        if ("devices".equalsIgnoreCase(configRealm)) {
            sysPropName = "deviceConfigFile";
        }

        if (System.getProperty(sysPropName) == null) {
            throw new IllegalArgumentException("Unable to read system property: " + configRealm);

        } else {
            configVariant = System.getProperty(sysPropName);

            return String.format("%s%s%s%s.xml", CONFIG_ROOT_DIR, configRealm, DIR_SEP, configVariant);
        }
    }

    private static void logPropertyKeyValuePair(final String key, final ConfigurationNode cfg) {
        LOG.info(String.format("\t Key: %s  --->  Value: %s", key, getPropertyValue(key, cfg)));
    }

    /**
     * Create configuration from properties xml file
     *
     * @param configRealm the configuration realm (env, webDriver)
     * @return Configuration object
     */
    static XMLConfiguration createConfig(final String configRealm) {
        final String cfgFileName = getFullCfgFileName(configRealm);
        XMLConfiguration cfg;

        try {
            cfg = new XMLConfiguration(cfgFileName);
        } catch (ConfigurationException cfgE) {
            LOG.error("ERROR creating PropertyConfiguration using file: [" + getFullCfgFileName(configRealm) + "] stackTrace: ", cfgE);
            cfg = null;
        }
        return cfg;
    }

    /**
     * write the properties read from xml to LOG framework
     *
     * @param cfg   the config to log
     * @param realm the realm for which to log
     */
    @SuppressWarnings("unchecked")
    static void logConfiguration(final ConfigurationNode cfg, final String realm) {
        final List<ConfigurationNode> cfgKeys = cfg.getChildren("entry");

        LOG.info("\t ------------------------------------");
        if ("config/webdriver".equalsIgnoreCase(realm)) {
            LOG.info("\t ------- webdriver properties -------");
        }
        for (final ConfigurationNode node : cfgKeys)
            logPropertyKeyValuePair((String) node.getAttribute(0).getValue(), cfg);
        LOG.info("\t ------------------------------------");
    }

    /**
     * create URI from string
     *
     * @param url the string to convert
     * @return the URI after converting string
     */
    static URI setUrl(final String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException uriE) {
            LOG.error("ERROR converting URI from String: " + url, uriE);
            IllegalArgumentException exception = new IllegalArgumentException("Unable to convert URI from String " + url);
            exception.addSuppressed(uriE);
            throw exception;
        }
    }

    /**
     * Get the property value from xml config
     *
     * @param propName the name of the property
     * @param cfg      the xml configuration
     * @return String value
     */
    @SuppressWarnings("unchecked")
    static String getPropertyValue(final String propName, final ConfigurationNode cfg) {
        final boolean propExists = cfg.getChildren("entry")
                .stream()
                .anyMatch(n -> n.getAttribute(0).getValue().equals(propName));

        if (propExists) {
            return (String) cfg.getChildren("entry")
                    .stream()
                    .filter(n -> n.getAttribute(0).getValue().equals(propName))
                    .findFirst()
                    .orElseThrow(() -> new ConfigException.Missing(propName))
                    .getValue();
        } else {
            LOG.warn(String.format("Property for setting [%s] not found, returning empty String! ", propName));
            return "";
        }
    }

}
