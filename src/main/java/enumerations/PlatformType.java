package enumerations;

/**
 * Created by MarthijsBerfelo on 9/18/15.
 * Enumeration for the type of platform (Operating System)
 */
public enum PlatformType {
    /**
     * Enum value for IOS native platform
     */
    IOS {
        public String toString() {
            return "iOS";
        }
    },
    /**
     * Enum value for ANDROID native platform
     */
    ANDROID {
        public String toString() {
            return "android";
        }
    }

}