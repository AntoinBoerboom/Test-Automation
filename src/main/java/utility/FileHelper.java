package utility;

import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mberfelo on 4/27/16.
 * Helper class for file related actions
 */
public class FileHelper {
    private static final Logger LOG = Logger.getLogger(FileHelper.class);

    private static final Path RESOURCE_PATH = Paths.get("target", "classes");
    private static final Path TEMP_DIR = setTempDir();
    private static final String DIR_SEP = File.separator;

    private static Path setTempDir() {
        String tempDir;
        Path tmp;
        tempDir = "/tmp";

        tmp = Paths.get(tempDir);
        ensureExistence(tmp);

        return tmp;
    }

    private static void ensureExistence(Path path) {
        boolean pathExists;
        pathExists = doesFileExist(path);
        if (!pathExists) {
            pathExists = path.toFile().mkdirs();
        }
    }


    public static boolean doesFileExist(final Path file) {
        File f = file.toFile();
        return f.exists();
    }

    public static Path getResourcePath() {
        LOG.info("Resource path is: " + RESOURCE_PATH);
        return RESOURCE_PATH;
    }

    public static String getDirSep() {
        return DIR_SEP;
    }

    /**
     * construct a relative directory path from an array of strings.
     *
     * @param dirs the array of strings
     * @return Path object for the relative directory path
     */
    private static Path createRelativePath(final String[] dirs) {
        Path current = Paths.get("");
        for (final String dir : dirs) {
            current = current.resolve(dir);
        }
        return current;
    }

    /**
     * construct a relative directory path from a string.
     *
     * @param dir the string to convert
     * @return Path object for the relative directory path
     */
    public static Path createRelativePath(final String dir) {
        final String[] dirs = {dir};

        return createRelativePath(dirs);
    }

    /**
     * create directory for the path if the dir does not exist.
     *
     * @param relativePath the path for which the dir must be created
     * @return the absolute path of the created (or existing) directory
     */
    public static String createDirectory(final Path relativePath) {

        final String dir = String.valueOf(relativePath.toAbsolutePath());
        final File path = new File(dir);
        boolean dirAvailable = path.exists();

        if (dirAvailable) {
            return dir;
        } else {
            dirAvailable = path.mkdirs();
            assert dirAvailable;
            return dir;
        }
    }

}
