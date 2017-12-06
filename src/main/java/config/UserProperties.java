package config;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

/**
 * Created by marc
 * Date: 20/04/2017
 * Time: 16:10
 */


public class UserProperties extends PropertyConfiguration {

    private static final String CONFIG_REALM = "user";
    public static final XMLConfiguration CONFIG = createConfig(CONFIG_REALM);
    private static final ConfigurationNode ROOT_NODE = CONFIG.getRootNode();

    private static final String USER_NAME = getPropertyValue("username", ROOT_NODE);
    private static final String PASSWORD = getPropertyValue("password", ROOT_NODE);


    public UserProperties() {
        logConfiguration(ROOT_NODE, CONFIG_REALM);
    }

    public String getUserName() {
        return USER_NAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

}
