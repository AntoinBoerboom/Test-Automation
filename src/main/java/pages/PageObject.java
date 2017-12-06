package pages;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.TestBase;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Page Object super class
 */
public abstract class PageObject implements AppView {


    private static final Logger LOG = Logger.getLogger(PageObject.class);

    protected WebDriver driver;

    String pageTitle;

    /**
     * Constructor for the class.
     *
     * @param driver     the web driver instance to use
     * @param page       the class of the page where this constructor is called.
     * @param pageLoaded the By locator that indicates the page is loaded.
     * @param log        the logger instance from the page where this constructor is called.
     */
    protected <T> PageObject(final WebDriver driver, Class<T> page, By pageLoaded, Logger log) {
        this.driver = driver;
        initPage(page, pageLoaded, log);
    }

    /**
     * Overload constructor for the class
     *
     * @param page       the class of the page where this constructor is called.
     * @param pageLoaded the By locator that indicates the page is loaded.
     * @param log        the logger instance from the page where this constructor is called.
     */
    protected <T> PageObject(Class<T> page, By pageLoaded, Logger log) {
        driver = TestBase.driver;
        initPage(page, pageLoaded, log);
    }

    /**
     * fluently wait for a page to have a title that contains
     *
     * @param title the expected title part
     */
    private void fluentWaitForTitleContains(final String title) {
        final ExpectedCondition<Boolean> condition = ExpectedConditions.titleContains(title);

        fluentWaitFor(condition, null);
        LOG.info(String.format("Page title contains: %s", title));
    }

    /**
     * fluently wait for a keyword in the page source
     *
     * @param keyword the expected word
     */
    private void fluentWaitForKeyWordOnPage(final String keyword) {
        final ExpectedCondition<Boolean> condition = driver -> driver.getPageSource().contains(keyword);

        fluentWaitFor(condition, null);
        LOG.info(String.format("Keyword [%s] was found on current page", keyword));
    }

    /**
     * get the calling method from stacktrace
     *
     * @param depth index of the stacktrace element of the calling method
     * @return the stack trace element
     */
    private StackTraceElement getCaller(final int depth) {
        final StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        return stacktrace[depth];
    }

    private Object execute(String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    /**
     * fluently wait for an expected condition
     *
     * @param condition the expected condition (predicate)
     * @param exception the exception to ignore
     * @param <K>       the type of the expected condition
     */
    private <K> void fluentWaitFor(final ExpectedCondition<K> condition,
                                   final Class<? extends Throwable> exception) {
        final long interval = TestBase.wdConfig.getInterval();
        final long timeOut = TestBase.wdConfig.getTimeOut();
        final WebDriverWait wait = new WebDriverWait(driver, timeOut, interval);

        if (exception != null) {
            wait.ignoring(exception);
        }

        wait.until(condition);
    }

    /**
     * Initialize a page,
     * wait for a locator to exist on the page
     *
     * @param page    the page class to initialize
     * @param locator the expected locator
     * @param <T>     the page type
     */
    private <T> void initPage(final Class<T> page, final By locator, final Logger log) {
        LOG.info(String.format("Initializing page: [%s] ....", page.getName()));
        fluentWaitForLocator(locator);
        log.info(format("[%s] loaded", page.getSimpleName()));
    }

    /**
     * instantiate and return new web page object
     *
     * @param expectedPage the page to create
     * @param <T>          generic class type to return
     * @return new web page
     */
    protected <T> T returnNewPage(final Class<T> expectedPage) {

        try {
            return expectedPage.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("ERROR while creating new page: " + expectedPage.getClass().getName(), e);
            return null;
        }
    }

    /**
     * collect all elements that are found as children
     *
     * @param element the parent element
     * @param locator the locator the child elements are identified by
     * @return a list of child web elements
     */
    protected List<WebElement> collectChildElements(final WebElement element, final By locator) {
        final StackTraceElement caller = getCaller(3);
        fluentWaitForElement(element);
        LOG.info(String.format("[%s.%s] - collecting children with BY [%s] from PARENT: %s",
                caller.getClassName(), caller.getMethodName(), locator.toString(), element.toString()));
        return element.findElements(locator);
    }

    /**
     * enter text in a text field
     *
     * @param textField the web element to enter the text into
     * @param text      the text to enter
     */
    protected void fillInputField(final WebElement textField, final String text) {
        final StackTraceElement caller = getCaller(3);
        fluentWaitForElement(textField);
        textField.click();
        textField.clear();
        textField.sendKeys(text);
    }

    /**
     * select an option from a dropdown box
     *
     * @param dropDown the dropdown web element
     * @param option   the option to select
     */
    protected void selectDropDownOption(final WebElement dropDown, final String option) {
        final StackTraceElement caller = getCaller(3);
        fluentWaitForElement(dropDown);

        final Select select = new Select(dropDown);
        final List<WebElement> options = select.getOptions();

        final WebElement optionElement
                = options
                .stream()
                .filter(o -> o.getText().matches(option))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(option));

        optionElement.click();
        LOG.info(String.format("[%s.%s] - Clicked option from dropdown: %s / %s",
                caller.getClassName(), caller.getMethodName(), dropDown, option));
    }

    /**
     * click a button and continue to another page
     *
     * @param <T>          the type of page to return
     * @param button       the button web element to click
     * @param expectedPage the expected page class
     * @return the page to continue to in the page flow
     */
    protected <T> T clickButton(final WebElement button, final Class<T> expectedPage) {
        final StackTraceElement caller = getCaller(3);
        fluentWaitForElement(button);

        button.click();
        LOG.info(String.format("[%s.%s] - Clicked button: %s",
                caller.getClassName(), caller.getMethodName(), button.toString()));
        return returnNewPage(expectedPage);
    }

    /**
     * fluently wait for an element to become visible
     *
     * @param element the web element
     */
    protected void fluentWaitForElement(final WebElement element) {
        final StackTraceElement caller = getCaller(3);
        final ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(element);

        fluentWaitFor(condition, NoSuchElementException.class);
        LOG.info(String.format("[%s.%s] - Element was found on current page: [%s]",
                caller.getClassName(), caller.getMethodName(), element.toString()));
    }

    /**
     * fluently wait for a locator to be present
     *
     * @param locator the By locator
     */
    protected void fluentWaitForLocator(final By locator) {
        final StackTraceElement caller = getCaller(3);
        final ExpectedCondition<WebElement> condition = ExpectedConditions.presenceOfElementLocated(locator);

        fluentWaitFor(condition, NoSuchElementException.class);
        LOG.info(String.format("[%s.%s] - Locator was found on current page: %s",
                caller.getClassName(), caller.getMethodName(), locator));
    }

    protected void fluentWaitForDisappearance(final By locator) {
        final StackTraceElement caller = getCaller(3);
        final ExpectedCondition<WebElement> condition = ExpectedConditions.presenceOfElementLocated(locator);
        final ExpectedCondition<Boolean> notCondition = ExpectedConditions.not(condition);

        fluentWaitFor(notCondition, NoSuchElementException.class);
        LOG.info(String.format("[%s.%s] - Locator was found on current page: %s",
                caller.getClassName(), caller.getMethodName(), locator));
    }

    protected WebElement getElementLocated(By locator) {
        fluentWaitForLocator(locator);
        return driver.findElement(locator);
    }


    protected boolean elementExists(By locator) {
        return ofNullable(getElementLocated(locator)).isPresent();
    }

    /**
     * get the current WebDriver for state of the page
     *
     * @return WebDriver instance
     */
    public WebDriver getWebDriver() {
        return driver;
    }

    public String getPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        By pageTitleLocator = By.id(pageTitle);
        fluentWaitForLocator(pageTitleLocator);
        return driver.getTitle();
    }

    /**
     * Set implicit wait in seconds *
     */
    public void setWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Press the back button *
     */
    public void back() {
        driver.navigate().back();
    }

    /**
     * Return a static text locator by xpath index *
     */
    public By for_text(int xpathIndex) {
        return By.xpath("//android.widget.TextView[" + xpathIndex + "]");
    }

}
