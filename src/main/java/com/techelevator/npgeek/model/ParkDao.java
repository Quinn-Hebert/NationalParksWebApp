package com.techelevator.npgeek.model;

import java.util.List;

/**
 * <p>
 * Park Dao Interface
 * <p>
 * <p>
 * Any class that implements this interface requires the implementation of two methods
 * <p>
 * <p>
 * List<Park> getAllParks();
 * <p>
 * <p>
 * returns a list of all the Park Objects stored in the park tabe in the database
 * <p>
 * <p>
 * Park getInfoForPark(String parkcode);
 * <p>
 * <p>
 * returns the Park Object for the park with the given park code  from the park table in the database
 * <p>
 * <p>
 * This interface was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with ParkJdbcDao as well as the NPGeekController
 * <p>
 */
public interface ParkDao {
	
	/**
	 * <p>
	 * Park Jdbc getAllParks();
	 * <p>
	 * <p>
	 * returns a list of all the Park Objects stored in the park tabe in the database
	 * <p>
	 */
	List<Park> getAllParks();
	/**
	 * <p>
	 * Park Jdbc getInfoForPark(String parkcode);
	 * <p>
	 * <p>
	 * returns the Park Object for the park with the given park code from the park table in the database
	 * <p>
	 */
	Park getInfoForPark(String parkcode);
	
}
