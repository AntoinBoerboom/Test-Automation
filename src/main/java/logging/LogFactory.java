package logging;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import utility.DateHelper;
import utility.FileHelper;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;


/**
 * Created by marthijs on 9/21/15.
 * <p>
 * Factory to manage logging appenders and settings
 */
public class LogFactory {

    private static final String PATTERN = "%d{dd/MM/yyyy-HH:mm:ss,SSS} [%t] %p %C:%L - %m %n";
    private static final PatternLayout LAYOUT = new PatternLayout(PATTERN);
    private static final String TIMESTAMP = DateHelper.toTimeStampString(new Date());


    /**
     * Initializes logging
     * <p>
     * Adds appenders for logging to:
     * 1. Console
     * 2. File
     * 3. TestNG Report
     */
    public static void initLogging() {
        clearAppenders();
        addConsoleAppender();
        addFileAppender();
        addReportAppender();
    }

    /**
     * Adds and configures Console appender for logging
     */
    private static void addConsoleAppender() {
        final ConsoleAppender console = new ConsoleAppender();
        if (!Logger.getRootLogger().isAttached(console)) {
            console.setName("console");
            console.setLayout(LAYOUT);
            console.setThreshold(Level.INFO);
            console.activateOptions();
            clearAppenders();
            Logger.getRootLogger().addAppender(console);
        }
    }

    /**
     * Adds and configures File appender for logging
     */
    private static void addFileAppender() {
        final FileAppender file = new FileAppender();

        if (!Logger.getRootLogger().isAttached(file)) {
            file.setName("file");
            file.setLayout(LAYOUT);
            file.setThreshold(Level.DEBUG);

            final Path logDir = FileHelper.createRelativePath("target/log");
            final File logFile = new File(String.valueOf(logDir.toAbsolutePath()), TIMESTAMP + ".log");

            file.setFile(String.valueOf(logFile));
            file.setAppend(true);
            file.activateOptions();

            Logger.getRootLogger().addAppender(file);

            Logger.getRootLogger()
                    .info("File appender initialized, log file can be found <a href=../log/\""
                            + TIMESTAMP
                            + ".log\">here</a>");

        }
    }

    /**
     * Adds and configures Report appender for logging
     */
    private static void addReportAppender() {
        final TestNGReportAppender report = new TestNGReportAppender();

        if (!Logger.getRootLogger().isAttached(report)) {
            report.setName("report");
            report.setLayout(LAYOUT);
            report.setThreshold(Level.INFO);

            report.activateOptions();

            Logger.getRootLogger().addAppender(report);
        }
    }

    private static void clearAppenders() {
        if (Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
            Logger.getRootLogger().removeAllAppenders();
        }
    }
}
