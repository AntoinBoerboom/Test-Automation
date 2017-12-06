package utility.testlisteners;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import utility.DateHelper;
import utility.FileHelper;
import utility.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by marthijs on 9/29/15.
 * Helper class for web driver to take screenshot on failure
 */
public class ScreenshotHelper extends TestListenerAdapter {

    private static final Logger LOG = Logger.getLogger(ScreenshotHelper.class);

    /**
     * Reporter to take screenshot on test failure (web driver)
     *
     * @param tr test result
     */
    @Override
    public void onTestFailure(final ITestResult tr) {

        Reporter.setCurrentTestResult(tr);

        WebDriver driver = TestBase.driver;
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String destDir = FileHelper.createDirectory(FileHelper.createRelativePath("target/screenshots"));

        String testName = tr.getTestClass().getRealClass().getSimpleName().trim();
        String methodName = tr.getMethod().getMethodName().trim();

        String destFile = String.format("%s_%s_%s.png",
                DateHelper.toTimeStampString(new Date()), testName, methodName);

        try {
            FileUtils.copyFile(srcFile, new File(destDir, destFile));
        } catch (IOException e) {
            LOG.error(String.format("Error writing screenshot to dir [%s] with filename %s : %s",
                    destDir, destFile, e.getMessage()), e);
        }

        LOG.info("Test failure detected! Screenshot taken and saved, click <a href=../../target/screenshots/" + destFile + ">here</a> to open.");

    }
}
