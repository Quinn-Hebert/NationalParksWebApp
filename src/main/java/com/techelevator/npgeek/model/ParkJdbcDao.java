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
 * Park Jdbc Dao Class
 * <p>
 * <p>
 * Implements ParkDao and implements two methods from ParkDao
 * <p>
 * <p>
 * public List<Park> getAllParks()
 * <p>
 * <p>
 * returns a list of all the Park Objects stored in the park tabe in the database
 * <p>
 * <p>
 * public Park getInfoForPark(String parkcode)
 * <p>
 * <p>
 * returns the Park Object for the park with the given park code  from the park table in the database
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with ParkDao as well as the NPGeekController
 * <p>
 */
@Component
public class ParkJdbcDao implements ParkDao{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ParkJdbcDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * <p>
	 * public Park Jdbc getAllParks()
	 * <p>
	 * <p>
	 * returns a list of all the Park Objects stored in the park tabe in the database
	 * <p>
	 */
	@Override
	public List<Park> getAllParks() {
		//Creates list of park codes
		String selectParkCodes = "SELECT parkcode FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectParkCodes);
		List<String> parkCodesList = new ArrayList<String>();
		while(results.next()) {
			parkCodesList.add(results.getString("parkcode"));
		}
		//Uses list of park codes to create list of Park objects to return
		List<Park> parksList = new ArrayList<Park>();
		for (String parkcode: parkCodesList) {
			parksList.add(createPark(parkcode));
		}
		return parksList;
	}

	/**
	 * <p>
	 * public Park Jdbc getInfoForPark(String parkcode)
	 * <p>
	 * <p>
	 * returns the Park Object for the park with the given park code from the park table in the database
	 * <p>
	 */
	@Override
	public Park getInfoForPark(String parkcode) {
		return createPark(parkcode);
	}
	
	/**
	 * <p>
	 * private Park createPark(String parkcode)
	 * <p>
	 * <p>
	 * returns a Park Object for the park with the given park code from the park table in the database
	 * <p>
	 */
	private Park createPark(String parkcode) {
		//Gets info from park table in database
		String selectPark = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectPark, parkcode);
		//Creates new Park and sets the values in the new Park Oject
		Park park = new Park();
		park.setParkCode(parkcode);
		if (results.next()) {
			park.setParkName(results.getString("parkname"));
			park.setState(results.getString("state"));
			park.setAcreage(results.getInt("acreage"));
			park.setElevationInFeet(results.getInt("elevationinfeet"));
			park.setMilesOfTrail(results.getDouble("milesoftrail"));
			park.setCampsites(results.getInt("numberofcampsites"));
			park.setClimate(results.getString("climate"));
			park.setYearFounded(results.getInt("yearfounded"));
			park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
			park.setInspirationalQuote(results.getString("inspirationalquote"));
			park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
			park.setDescription(results.getString("parkdescription"));
			park.setEntryFee(results.getInt("entryfee"));
			park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		}
		return park;
	}

}
