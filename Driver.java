import java.io.IOException;

/**
 * @author Yangzom Dolma
 * 
 *         :Read in the data file, parse the data, create appropriate object
 *         from the data and summarize the data file, parse the data, create
 *         appropriate objects from this data, and summarize using maximum,
 *         minimum, and average mathematical functions.
 */
public class Driver
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {

        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;

        final String directory = "data";

        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

        mapData.parseFile();
        System.out.println(mapData);
    }
}