import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public interface DateTimeComparable
{
    public boolean newerThan (GregorianCalendar inDateTimeUTC);
    public boolean olderThan (GregorianCalendar inDateTimeUTC);
    public boolean sameAs (GregorianCalendar inDateTimeUTC);
    
    public boolean newThan (ZonedDateTime inDateTimeUTC);
    public boolean olderThan(ZonedDateTime inDateTimeUTC);
    public boolean sameAs (ZonedDateTime inDateTimeUTC);

}
