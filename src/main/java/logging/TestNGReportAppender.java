package logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

/**
 * Created by MarthijsBerfelo on 9/21/15.
 * <p>
 * Custom log4j appender to add logging to the TestNG report
 */
class TestNGReportAppender extends AppenderSkeleton {
    @Override
    protected void append(final LoggingEvent loggingEvent) {
        Reporter.log(eventToString(loggingEvent));
    }

    private String eventToString(final LoggingEvent event) {
        final StringBuilder result = new StringBuilder(layout.format(event));

        if (layout.ignoresThrowable()) {
            final String[] s = event.getThrowableStrRep();
            if (s != null) {
                for (final String value : s) {
                    result.append(value).append(Layout.LINE_SEP);
                }
            }
        }
        return result.toString();
    }

    /**
     * Empty implementation of the close()
     */
    @Override
    public void close() {
        //Method stub
    }

    /**
     * (Override) implementation of requiresLayout()
     *
     * @return always TRUE
     */
    @Override
    public boolean requiresLayout() {
        return true;
    }
}
