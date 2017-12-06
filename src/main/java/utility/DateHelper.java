package utility;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marthijs on 9/29/15.
 * Helper class for date related actions
 */
public class DateHelper {
    private static final Logger LOG = Logger.getLogger(DateHelper.class);

    /**
     * convert string to date
     *
     * @param date       the date time string
     * @param dateFormat the format string (e.g. 'yyyy-MM-dd HH:mm:ss')
     * @return converted date
     */
    private static Date toDate(final String date, final String dateFormat) {
        final DateFormat format = new SimpleDateFormat(dateFormat);
        Date dateObject = null;
        try {
            dateObject = format.parse(date);
        } catch (ParseException e) {
            LOG.error(String
                    .format("Error setting Date from string [%s] using format [%s]! StackTrace: "
                            , date, dateFormat), e);
        }
        return dateObject;
    }

    /**
     * format date as a timestamp with mask 'yyyyMMdd-HHmmssSSS'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toTimeStampString(final Date date) {
        return new SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(date);
    }

    /**
     * format date as a date time with mask 'yyyy-MM-dd HH:mm:ss'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toDateTimeString(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * convert string to date (string must have format 'yyyy-MM-dd HH:mm:ss'.
     *
     * @param dateTime the date time string
     * @return converted date
     */
    public static Date toDateFromDateTime(final String dateTime) {
        return toDate(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * convert string to date (string must have iso date format 'yyyy-MM-dd'T'HH:mm:sssZ'
     *
     * @param isoDate the iso date string
     * @return converted date
     */
    public static Date toDateFromIsDate(final String isoDate) {
        return toDate(isoDate, "yyyy-MM-dd'T'HH:mm:sssZ");
    }

    /**
     * format a date as date time with timezone with mask: 'yyyy-MM-dd'T'HH:mm:ssZ'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toDateTimeTimezoneString(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
    }

    /**
     * format a date as date time with timezone with mask: 'yyyy-MM-dd'T'HH:mm:sssZ'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toIsoDateString(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssZ").format(date);
    }

    /**
     * format date as a date time with mask 'yyyy_MM_dd'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toDateString(final Date date) {
        return new SimpleDateFormat("yyyy_MM_dd").format(date);
    }

    /**
     * format date as a date time with mask 'yyyy_MM'.
     *
     * @param date the date object to format
     * @return formatted string
     */
    public static String toYearMonthString(final Date date) {
        return new SimpleDateFormat("yyyy_MM").format(date);
    }

    /**
     * get the date of yesterday.
     *
     * @return Date object of Now() -1 day
     */
    public static Date getYesterdaysDate() {
        return getDateOfDaysAgo(1);
    }

    /**
     * get the date of x days before today.
     *
     * @param days the number of days ago
     * @return Date object of Now() -x days
     */
    public static Date getDateOfDaysAgo(final int days) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }

    /**
     * get the date of x hours before now.
     *
     * @param seconds the number of hours ago
     * @return Date object of Now() -x days
     */
    public static Date getDateOfSecondsAgo(final int seconds) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, -seconds);

        return cal.getTime();
    }
}
