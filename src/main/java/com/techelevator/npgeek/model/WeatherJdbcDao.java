package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Weather Jdbc Dao Interface
 * <p>
 * <p>
 * Implements WeatherDao and implements one method from WeatherDao
 * <p>
 * <p>
 * public List<Weather> getFiveDayForecast(String parkcode)
 * <p>
 * <p>
 * returns a list of 5 Weather Objects that are used to generate the 5 day forecast given the park code
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with WeatherDao as well as the NPGeekController
 * <p>
 */
@Component
public class WeatherJdbcDao implements WeatherDao{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public WeatherJdbcDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * <p>
	 * public List<Weather> getFiveDayForecast(String parkcode)
	 * <p>
	 * <p>
	 * returns a list of 5 Weather Objects that are used to generate the 5 day forecast given the park code
	 * <p>
	 */
	@Override
	public List<Weather> getFiveDayForecast(String parkcode) {
		//Create List and add Weather object to List for each day (1 thru 5)
		List<Weather> fiveDayForecast = new ArrayList<Weather>();
		fiveDayForecast.add(getWeatherForecastForOneDay(parkcode, 1));
		fiveDayForecast.add(getWeatherForecastForOneDay(parkcode, 2));
		fiveDayForecast.add(getWeatherForecastForOneDay(parkcode, 3));
		fiveDayForecast.add(getWeatherForecastForOneDay(parkcode, 4));
		fiveDayForecast.add(getWeatherForecastForOneDay(parkcode, 5));
		return fiveDayForecast;
	}
	
	/**
	 * <p>
	 * private Weather getWeatherForecastForOneDay(String parkcode, int day)
	 * <p>
	 * <p>
	 * returns a Weather Objects given the park code and the day number
	 * <p>
	 */
	private Weather getWeatherForecastForOneDay(String parkcode, int day) {
		Weather oneDayForecast = new Weather();
		String selectWeatherForOneDay = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectWeatherForOneDay, parkcode, day);
		oneDayForecast.setParkCode(parkcode);
		oneDayForecast.setForecastDay(day);
		while(results.next()) {
			oneDayForecast.setLowTempF(results.getInt("low"));
			oneDayForecast.setHighTempF(results.getInt("high"));
			oneDayForecast.setLowTempC(oneDayForecast.getLowTempF());
			oneDayForecast.setHighTempC(oneDayForecast.getHighTempF());
			oneDayForecast.setForecast(getCamelCaseForecast(results.getString("forecast").split(" ")));
			oneDayForecast.setDay();
		}
		return oneDayForecast;
	}
	
	/**
	 * <p>
	 * private String getCamelCaseForecast(String[] splitForecast)
	 * <p>
	 * <p>
	 * returns a String given String[] of words to be comined into one String followingCamelCasingConventions
	 * <p>
	 */
	private String getCamelCaseForecast(String[] splitForecast) {
		String camelCased = "";
		camelCased += splitForecast[0];
		for (int i = 1; i < splitForecast.length; i++) {
			if (splitForecast[i] != null && !splitForecast[i].isEmpty()) {
				camelCased += splitForecast[i].substring(0, 1).toUpperCase();
				camelCased += splitForecast[i].substring(1).toLowerCase();
			}
		}
		
		return camelCased;
	}

}
