package com.techelevator.npgeek.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Survey Jdbc Dao Class
 * <p>
 * <p>
 * Implements SurveyDao and implements two methods from SurveyDao
 * <p>
 * <p>
 * public Map<Park,Integer> getVoteCount(String state, int activityNum)
 * <p>
 * <p>
 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
 * with the map value being the number of votes that park has recievied from the survey results
 * for all parks that have recieved one vote and match the search criteria
 * <p>
 * <p>
 * public void saveSurvey(Survey survey)
 * <p>
 * <p>
 * saves the Survey Oobject in the survey_result table in the database
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with SurveyDao as well as the NPGeekController
 * <p>
 */
@Component
public class SurveyJdbcDao implements SurveyDao{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public SurveyJdbcDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * <p>
	 * public Map<Park,Integer> getVoteCount(String state, int activityNum)
	 * <p>
	 * <p>
	 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
	 * with the map value being the number of votes that park has recievied from the survey results
	 * for all parks that have recieved one vote and match the search criteria
	 * <p>
	 */
	@Override
	public Map<Park,Integer> getVoteCount(String state, String activityLevel) {
		//Create map and populate map by calling on getMap()
		Map<Park,Integer> map = new LinkedHashMap<Park,Integer>();
		if (!state.isEmpty() && !activityLevel.isEmpty()) {
			String selectSurveysForPark = "SELECT survey_result.parkcode, COUNT(*) AS totalvotes FROM survey_result JOIN park on survey_result.parkcode = park.parkcode WHERE survey_result.state = ? AND activitylevel = ?  GROUP BY survey_result.parkcode, park.parkname ORDER BY totalvotes DESC, park.parkname";
			map = getMap(selectSurveysForPark, state, activityLevel);
		}
		else if (!state.isEmpty()) {
			String selectSurveysForPark = "SELECT survey_result.parkcode, COUNT(*) AS totalvotes FROM survey_result JOIN park on survey_result.parkcode = park.parkcode WHERE survey_result.state = ? GROUP BY survey_result.parkcode, park.parkname ORDER BY totalvotes DESC, park.parkname";
			map = getMap(selectSurveysForPark, state);
		}
		else if (!activityLevel.isEmpty()) {
			String selectSurveysForPark = "SELECT survey_result.parkcode, COUNT(*) AS totalvotes FROM survey_result JOIN park on survey_result.parkcode = park.parkcode WHERE activitylevel = ? GROUP BY survey_result.parkcode, park.parkname ORDER BY totalvotes DESC, park.parkname";
			map = getMap(selectSurveysForPark, activityLevel);
		}
		else {
			String selectSurveysForPark = "SELECT survey_result.parkcode, COUNT(*) AS totalvotes FROM survey_result JOIN park on survey_result.parkcode = park.parkcode GROUP BY survey_result.parkcode, park.parkname ORDER BY totalvotes DESC, park.parkname";
			map = getMap(selectSurveysForPark);
		}
		return map;
	}

	/**
	 * <p>
	 public void saveSurvey(Survey survey)
	 * <p>
	 * <p>
	 * saves the Survey Oobject in the survey_result table in the database
	 * <p>
	 */
	@Override
	public void saveSurvey(Survey survey) {
		//Insert Survey Object into survey_result table in database in order to save Survey
		String insertSurveyResults = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
								   + "Values (?,?,?,?)";
		jdbcTemplate.update(insertSurveyResults, survey.getParkCode(), survey.getEmail(),
																	survey.getState(), survey.getActivityLevel());
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
		String selectPark = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectPark, parkcode);
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
	
	/**
	 * <p>
	 * private Map<Park,Integer> getMap(String selectSurveysForPark)
	 * <p>
	 * <p>
	 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
	 * with the map value being the number of votes that park has recievied from the survey results
	 * for all parks that have recieved one vote
	 * <p>
	 */
	private Map<Park,Integer> getMap(String selectSurveysForPark) {
		Map<Park,Integer> map = new LinkedHashMap<Park,Integer>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSurveysForPark);
		while (results.next()) {
			String parkcode = results.getString("parkcode");
			map.put(createPark(parkcode), results.getInt("totalvotes"));
		}
		return map;
	}
	
	/**
	 * <p>
	 * private Map<Park,Integer> getMap(String selectSurveysForPark, String state, int activityNum)
	 * <p>
	 * <p>
	 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
	 * with the map value being the number of votes that park has recievied from the survey results
	 * for all parks that have recieved one vote and match the search criteria
	 * <p>
	 */
	private Map<Park,Integer> getMap(String selectSurveysForPark, String state, String activityLevel) {
		Map<Park,Integer> map = new LinkedHashMap<Park,Integer>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSurveysForPark, state, activityLevel);
		while (results.next()) {
			String parkcode = results.getString("parkcode");
			map.put(createPark(parkcode), results.getInt("totalvotes"));
		}
		return map;
	}
	
	/**
	 * <p>
	 * private Map<Park,Integer> getMap(String selectSurveysForPark, String state)
	 * <p>
	 * <p>
	 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
	 * with the map value being the number of votes that park has recievied from the survey results
	 * for all parks that have recieved one vote and match the search criteria
	 * <p>
	 */
	private Map<Park,Integer> getMap(String selectSurveysForPark, String input) {
		Map<Park,Integer> map = new LinkedHashMap<Park,Integer>();
		if (input.length() == 2) {
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSurveysForPark, input);
			while (results.next()) {
				String parkcode = results.getString("parkcode");
				map.put(createPark(parkcode), results.getInt("totalvotes"));
			}
		}
		else {
			SqlRowSet results = jdbcTemplate.queryForRowSet(selectSurveysForPark, input);
			while (results.next()) {
				String parkcode = results.getString("parkcode");
				map.put(createPark(parkcode), results.getInt("totalvotes"));
			}
		}
		return map;
	}

}
