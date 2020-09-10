import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class StatisticsTest {
	// ZoneId zone = null;
	protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
	GregorianCalendar utcDateTime = new GregorianCalendar(2018, 8, 30, 17, 45, 30);
	// ZonedDateTime zdtDateTime = new ZonedDateTime(2018, 8, 30, 17, 45, 30, 00,
	// zone);
	ZonedDateTime zdtDateTime;
	String DateTimeStr = "2018-08-30 17:45";
	StatsType statstype;
	Statistics s = new Statistics(22.5, "JAYX", zdtDateTime, 10, statstype);

	Statistics stat = new Statistics(22.5, "JAYX", utcDateTime, 10, statstype);

	/**
	 * Test for toString method
	 */
	@Test
	public void testToString() {
		String expected = null;
		String actual = stat.toString();
		assertEquals(expected, actual);
	}

	/**
	 * test for CreateDateFromString
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testCreateDateFromString() throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat(s.DATE_TIME_FORMAT);
		Date d = f.parse("2018-08-30T17:45:30 UTC");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		GregorianCalendar expected = s.createDateFromString("2018-08-30T17:45:30 UTC");
		assertEquals(expected, (GregorianCalendar) f.getCalendar());

	}

	@Test
	public void testCreateZDateFromString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateStringFromDateGregorianCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateStringFromDateZonedDateTime() {
		fail("Not yet implemented");
	}

	/**
	 * Test for getNumberOfReportingStations
	 */

	@Test
	public void testGetNumberOfReportingStations() {
		int expected = 10;
		int actual = s.getNumberOfReportingStations();
		assertEquals("Error", expected, actual);

	}

	/**
	 * Test for getUTCDateTimeString
	 */
	@Test
	public void testGetUTCDateTimeString() {
		String expected = "gregory";

		assertEquals("Faiure", expected, stat.getUTCDateTimeString());

	}

	/**
	 * Test for newerThanGregorianCalendar()
	 */
	@Test
	public void testNewerThanGregorianCalendar() {
		GregorianCalendar newer = new GregorianCalendar(2017, 8, 30, 17, 45, 30);

		boolean expected = true;
		boolean actual = newer.before(utcDateTime);
		Assert.assertEquals(expected, actual);

	}

	/**
	 * Test for olderThanGregorianCalendar()
	 */

	@Test
	public void testOlderThanGregorianCalendar() {
		GregorianCalendar newer = new GregorianCalendar(2017, 8, 30, 17, 45, 30);

		boolean expected = false;
		boolean actual = newer.after(utcDateTime);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test for sameAsGregorianCalendar()
	 */

	@Test
	public void testSameAsGregorianCalendar() {
		GregorianCalendar sameDate = new GregorianCalendar(2018, 8, 30, 17, 45, 30);

		boolean expected = true;
		boolean actual = sameDate.equals(utcDateTime);
		Assert.assertEquals(expected, actual);

	}
	/**
	 * Test for newerThanZonedDateTime()
	 */

	@Test
	public void testNewerThanZonedDateTime() {
		ZonedDateTime newer = new ZonedDateTime(2017, 8, 30, 17, 45, 30);

		boolean expected = false;
		boolean actual = newer.after(zdtDateTime);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testOlderThanZonedDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSameAsZonedDateTime() {
		fail("Not yet implemented");
	}

}
