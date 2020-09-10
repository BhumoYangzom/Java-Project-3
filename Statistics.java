import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author yangzomdolma
 * @version 2018-10-23
 */

public class Statistics extends Observation implements DateTimeComparable {
	/**
	 * The format of the date: yyyy-MM-dd'T'HH:mm:ss z
	 */
	protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";

	protected DateTimeFormatter format;
	/**
	 * Gregorian Calendar
	 */
	private GregorianCalendar utcDateTime;
	/**
	 * Zoned date time
	 */
	private ZonedDateTime zdtDateTime;
	/**
	 * The number of reporting stations.
	 */
	private int numberOfReportingStations;

	private StatsType statType;

	/**
	 * 
	 * @param value
	 * @param stid
	 * @param dateTime
	 * @param numberOfValidStations
	 * @param inStatType
	 */
	public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
			StatsType inStatType) {
		super(value, stid);
		this.utcDateTime = dateTime;
		this.numberOfReportingStations = numberOfValidStations;
		this.statType = inStatType;

	}

	/**
	 * 
	 * @param value
	 * @param stid
	 * @param dateTime
	 * @param numberOfValidStations
	 * @param inStatType
	 */

	public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
			StatsType inStatType) {
		super(value, stid);
		this.zdtDateTime = dateTime;
		this.numberOfReportingStations = numberOfValidStations;
		this.statType = inStatType;

	}

	/**
	 * 
	 * @param dateTimeStr
	 * @return
	 * @throws ParseException
	 */

	public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		format.parse(dateTimeStr);

		return (GregorianCalendar) format.getCalendar();

	}

	/**
	 * 
	 * @param dateTimeStr
	 * @return
	 */

	public ZonedDateTime createZDateFromString(String dateTimeStr) {

		return ZonedDateTime.parse(dateTimeStr,format );

	}

	/**
	 * 
	 * @param calendar
	 * @return
	 */

	public String createStringFromDate(GregorianCalendar calendar) {

		return utcDateTime.toString();

	}

	public String createStringFromDate(ZonedDateTime calendar) {

		return zdtDateTime.toString();

	}

	/**
	 * 
	 * @return The number of Reporting Stations
	 */

	public int getNumberOfReportingStations() {
		return numberOfReportingStations;

	}

	/**
	 * 
	 * @return A string
	 */
	public String getUTCDateTimeString() {
		return utcDateTime.toString();
	}

	/**
	 * Returns true if the date if before the said date
	 */
	public boolean newerThan(GregorianCalendar inDateTime) {
		return utcDateTime.before(inDateTime);
	}

	/**
	 * returns true if the date is after the present date.
	 */

	public boolean olderThan(GregorianCalendar inDateTime) {
		return utcDateTime.after(inDateTime);
	}

	/**
	 * Returns true if the date is same as present date.
	 */
	public boolean sameAs(GregorianCalendar inDateTime) {
		return utcDateTime.equals(inDateTime);
	}

	/**
	 * Returns true if the date if before the said date
	 */

	public boolean newerThan(ZonedDateTime inDateTime) {
		return zdtDateTime.isBefore(inDateTime);
	}

	/**
	 * returns true if the date is after the present date.
	 */

	public boolean olderThan(ZonedDateTime inDateTime) {
		return zdtDateTime.isAfter(inDateTime);
	}

	/**
	 * Returns true if the date is same as present date.
	 */
	public boolean sameAs(ZonedDateTime inDateTime) {
		return zdtDateTime.equals(inDateTime);
	}

	/**
	 * returns nothing.
	 */

	public String toString() {
		return null;
	}

}
