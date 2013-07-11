package it.sasabz.sasabus.logic;

import it.sasabz.sasabus.data.models.Area;
import it.sasabz.sasabus.data.models.BusLine;
import it.sasabz.sasabus.data.models.BusStop;
import it.sasabz.sasabus.data.models.Itinerary;

import java.util.ArrayList;
import java.util.List;

public class BusSchedulesDatabase {

	
	/**
	 * 
	 * @return all {@link Area}s available in the database
	 */
	public static List<Area> getAllAreas() {
		
		//TODO fetch data from local database about all areas available
		//for Spinner in bus schedules tab
				
		List<Area> fakeList = new ArrayList<Area>();
			fakeList.add(new Area(0, "Bolzano", "Bozen"));;
			fakeList.add(new Area(0, "Merano", "Meran"));
			fakeList.add(new Area(0, "Extraurbano", "‹berland"));
		
		return fakeList;
		
	}
	
	/**
	 * Fetch all bus lines available in the database
	 * for a specific area
	 * @param area where to search for the various bus lines
	 * @return {@link List} of bus lines
	 */
	public static List<BusLine> getAllBusLinesForArea(Area area) {
		
		//TODO fetch data from local database about all bus stops available
		//for Spinner in bus schedules tab
		
		List<BusLine> fakeList = new ArrayList<BusLine>();
			fakeList.add(new BusLine("Linie 1", null, null, null, 0));
			fakeList.add(new BusLine("Linie 2", null, null, null, 0));
			fakeList.add(new BusLine("Linie 3", null, null, null, 0));
			fakeList.add(new BusLine("Linie 4", null, null, null, 0));
			fakeList.add(new BusLine("Linie 5", null, null, null, 0));
			
		return fakeList;
	}
	

	/**
	 * Fetch all bus stops available in the database for a specific busline
	 * @param busline is the line of the bus
	 * @return {@link List} of the busstops
	 */
	public static List<BusStop> getAllBusStopsForBusLine(BusLine busline) {
		
		//TODO fetch data from local database about all bus stops available
		//for spinner 
		
		List<BusStop> fakeList = new ArrayList<BusStop>();
			fakeList.add(new BusStop(0, "Bolzano - Via Merano", "Bozen - Meranerstr."));
			fakeList.add(new BusStop(0, "Bolzano - Via Druso 11", "Bozen - Drusus Str. 11"));
			fakeList.add(new BusStop(0, "Bolzano - Via G. di Vittorio Str.", "Bozen - G. di Vittorio Str."));
			fakeList.add(new BusStop(0, "Bolzano - Via Braille", "Bozen - Braille Str./Bz S"));
			fakeList.add(new BusStop(0, "Bolzano - Via Lancia 2", "Bozen - Lanciastr. 2"));
			
		return fakeList;
	}
	
	/**
	 * Fetch the starting and the ending bus stop of a specific busline
	 * @param area where to search for the busline
	 * @param busline the busline to search for
	 * @return a List of directions
	 */
	public static List<String> getStartAndEndBusStopForBusline(Area area, BusLine busline) {
		
		//TODO fetch data from local database about the first about 
		//the starting and ending bus stop of a specific line
		
		List<String> fakeList = new ArrayList<String>();
			fakeList.add("Fagenstraﬂe - Kardaun");
			fakeList.add("Kardaun - Fagenstraﬂe");
		
		return fakeList;
	}
	
	public static List<Itinerary> getItinerary(Area area, BusLine busLine, String direction) {
		
		
		List<Itinerary> fakeList = new ArrayList<Itinerary>();
			fakeList.add(new Itinerary(0, 1234567891, 0, 0, "09:10"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "09:20"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "09:30"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "09:40"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "09:50"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "10:00"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "10:10"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "10:20"));
			fakeList.add(new Itinerary(0, 1234, 0, 0, "10:30"));
				
		return fakeList;
	}
	
}
