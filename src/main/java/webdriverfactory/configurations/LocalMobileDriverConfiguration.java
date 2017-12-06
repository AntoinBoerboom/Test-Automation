package webdriverfactory.configurations;

import enumerations.PlatformType;

import java.net.URI;

/**
 * Created by marthijs on 9/18/15.
 * PropertyConfiguration class for web driver
 * configuration to run against
 * remote browser servers like:
 * - Selenium Grid,
 * - Sauce labs (additional settings required),
 * - BrowserStack (additional settings required)
 */
public class LocalMobileDriverConfiguration {

    public final transient PlatformType platform;
    public final transient URI seleniumHubUrl;
    public final transient int seleniumHubPort;

    /**
     * Constructor for the remote web driver config
     *
     * @param platform        the platform to use
     * @param seleniumHubUrl  the selenium hub uri
     * @param seleniumHubPort the selenium hup port
     */
    public LocalMobileDriverConfiguration(final PlatformType platform,
                                          final URI seleniumHubUrl, final int seleniumHubPort) {

        this.platform = platform;
        this.seleniumHubUrl = seleniumHubUrl;
        this.seleniumHubPort = seleniumHubPort;
    }
}
