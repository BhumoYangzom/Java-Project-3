import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * MapData class reads data from the file in the directory "Data" and computes
 * the maximum, minimum and average for the variables srad (Solar radiation),
 * tair (air temperature), ta9m (air temperature at 9 meters). It produces a
 * catalog with time.
 * 
 * @author yangzomdolma
 * @version 2018 - 10- 14
 */

public class MapData {
	/**
	 * A HashMap that stores a list of data in an arraylist called dataCatalog with
	 * a String as a key.
	 */
	private HashMap<String, ArrayList<Observation>> dataCatalog;
	private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;
	private TreeMap<String, Integer> paramPositions;
	private int NUMBER_OF_MISSING_OBSERVATIONS = 10;
	private Integer numberOfStations = null;
	private String TA9M = "TA9M";
	private String TAIR = "TAIR";
	private String SRAD = "SRAD";
	private String STID = "STID";
	private final String MESONET = "Mesonet";
	private String fileName;
	private GregorianCalendar utcDateTime;

	public MapData(int year, int month, int day, int hour, int minute, String directory) {
		this.utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
		directory = "data";
	}

	public String createFileName(int year, int month, int day, int hour, int minute, String directory) {
		String Year = Integer.toString(year);
		String Month = String.format("0%d", month);
		String Day = Integer.toString(day);
		String Hr = Integer.toString(hour);
		String Mint = Integer.toString(minute);
		String FileName = String.format("%s%s%s%s%s.mdf", Year, Month, Day, Hr, Mint);
		return FileName;
	}

	String[] headerArray;

	private void parseParamHeader(String inParamStr) {
		headerArray = inParamStr.trim().split("\\s+");
		for (int i = 0; i < headerArray.length; ++i) {
			paramPositions.put(headerArray[i], i);

		}
	}

	public Integer getIndexOf(String inParamStr) {
		return this.paramPositions.get(inParamStr);
	}

	ArrayList<Observation> dataFromFile = new ArrayList<Observation>();

	public void parseFile() throws IOException {
		String fileName = createFileName(2018, 8, 30, 17, 45, "data");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		br.readLine();
		String headers = br.readLine();
		String[] headerArray = headers.trim().split("\\s+");
		parseParamHeader(headers);
		prepareDataCatalog();
		
//Some from Maria
		
		String line = br.readLine();
			while (line != null) {
				String[] lineArrays = line.trim().split("\\s+");
				
				for (int i = 1; i < lineArrays.length; ++i) {

					dataCatalog.get(headerArray[i]).add(new Observation (Double.parseDouble(lineArrays[i]), lineArrays[getIndexOf(STID)]));
					
				}
				line = br.readLine();
				//increase the number of stations. 
			}

			br.close();
		}

	

	private void calculateAllStatistics() {
		Statistics Minimum;
		Statistics Maximum;
		Statistics Average;
		Statistics Total;
		
		String stid = null;
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double total = 0.0;
		numberOfStations= 0;
		this.statistics = new EnumMap <StatsType, TreeMap<String, Statistics>>(statistics);
		TreeMap<String, Statistics> treemap = new TreeMap();
		Set<String> parameterIds = dataCatalog.keySet();
		for (String paramId : parameterIds) {
			ArrayList<Observation> data = dataCatalog.get(paramId);
			for (Observation obs : data) {
				double value = obs.getValue();
				total += value;
				if (min > value) {
					min = value;
					stid = obs.getStid();
				}
				if (max < value) {
					max = value;
					stid = obs.getStid();
				}
				numberOfStations ++;
			}
			double average = total / numberOfStations;
			
			average = total/numberOfStations;
			Minimum = new Statistics (min, stid, utcDateTime, numberOfStations, StatsType.MINIMUM);
			Maximum = new Statistics (max, stid, utcDateTime, numberOfStations, StatsType.MAXIMUM);
			Average = new Statistics (average, stid, utcDateTime, numberOfStations, StatsType.AVERAGE);
			Total = new Statistics (total, stid, utcDateTime, numberOfStations, StatsType.TOTAL);
			
			treemap.put(paramId, Minimum);
			statistics.put(StatsType.MINIMUM, treemap);
			treemap.replace(paramId, Maximum);
			statistics.put(StatsType.MAXIMUM, treemap);
			treemap.replace(paramId, Average);
			statistics.put(StatsType.AVERAGE, treemap);
			treemap.replace(paramId, Total);
			statistics.put(StatsType.TOTAL, treemap);
		

			 /* String ta9m = "";
            if (paramId.equals(TA9M))
            {
                ta9m = String.format(
                        "========================================================\n"
                                + "Maximum Air Temperature[9.0m] = %.1f C at %s\n"
                                + "Minimum Air Temperature[9.0m] = %.1f C at %s\n"
                                + "Average Air Temperature[9.0m] = %.1f C at %s\n"
                                + "========================================================\n",

                        getStatistics(StatsType.MAXIMUM, TA9M).getValue(),
                        getStatistics(StatsType.MAXIMUM, TA9M).getStid(),

                        getStatistics(StatsType.MINIMUM, TA9M).getValue(),
                        getStatistics(StatsType.MINIMUM, TA9M).getStid(),

                        getStatistics(StatsType.AVERAGE, TA9M).getValue(), MESONET);
                System.out.print(ta9m);
            }
            String tair = "";
            if (paramId.equalsIgnoreCase(TAIR))
            {
                tair = String.format(
                        "========================================================\n"
                                + "Maximum Air Temperature[1.5m] = %.1f C at %s\n"
                                + "Minimum Air Temperature[1.5m] = %.1f C at %s\n"
                                + "Average Air Temperature[1.5m] = %.1f C at %s\n"
                                + "========================================================\n",

                        getStatistics(StatsType.MAXIMUM, TAIR).getValue(),
                        getStatistics(StatsType.MAXIMUM, TAIR).getStid(),

                        getStatistics(StatsType.MINIMUM, TAIR).getValue(),
                        getStatistics(StatsType.MINIMUM, TAIR).getStid(),

                        getStatistics(StatsType.AVERAGE, TAIR).getValue(), MESONET);
                System.out.print(tair);

            }
            String srad = "";
            if (paramId.equals(SRAD))
            {
                srad = String.format(
                        "========================================================\n"
                                + "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                                + "Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                                + "Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                                + "========================================================\n",
                        getStatistics(StatsType.MAXIMUM, SRAD).getValue(),
                        getStatistics(StatsType.MAXIMUM, SRAD).getStid(),

                        getStatistics(StatsType.MINIMUM, SRAD).getValue(),
                        getStatistics(StatsType.MINIMUM, SRAD).getStid(),

                        getStatistics(StatsType.AVERAGE, SRAD).getValue(), MESONET);
                System.out.print(srad);

            }*/
		}

	}

	private void prepareDataCatalog() {
		//From Maria
		for (Map.Entry<String, Integer> entry: paramPositions.entrySet()) {
			String key = entry.getKey();
			ArrayList <Observation> newArray = new ArrayList (key, )
		} 

		/*
		 * for (int i = 0; i < headerArray.length; ++i) {
			dataCatalog.put(headerArray[i], dataFromFile);
		}
		 */
	}

	private void calculateStatistics() {
		calculateAllStatistics();

	}

	public Statistics getStatistics(StatsType type, String paramId) {

		
	}

	public String toString() {

	}

}
