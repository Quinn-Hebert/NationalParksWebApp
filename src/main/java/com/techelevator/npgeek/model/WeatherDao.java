package com.techelevator.npgeek.model;

import java.util.List;

/**
 * <p>
 * Weather Dao Interface
 * <p>
 * <p>
 * Any class that implements this interface requires the implementation of one method
 * <p>
 * <p>
 * List<Weather> getFiveDayForecast(String parkcode);
 * <p>
 * <p>
 * returns a list of 5 Weather Objects that are used to generate the 5 day forecast given the park code
 * <p>
 * <p>
 * This interface was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with WeatherJdbcDao as well as the NPGeekController
 * <p>
 */
public interface WeatherDao {
	
	/**
	 * List<Weather> getFiveDayForecast(String parkcode);
	 * <p>
	 <p>
	 * returns a list of 5 Weather Objects that are used to generate the 5 day forecast
	 * <p>
	 */
	List<Weather> getFiveDayForecast(String parkcode);
	
}
