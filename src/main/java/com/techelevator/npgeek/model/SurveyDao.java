package com.techelevator.npgeek.model;

import java.util.Map;

/**
 * <p>
 * Survey Dao Interface
 * <p>
 * <p>
 * Any class that implements this interface requires the implementation of two methods
 * <p>
 * <p>
 * Map<Park,Integer> getVoteCount(String state, int activityNum);
 * <p>
 * <p>
 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
 * with the map value being the number of votes that park has recievied from the survey results
 * for all parks that have recieved one vote and match the search criteria
 * <p>
 * <p>
 * void saveSurvey(Survey survey);
 * <p>
 * <p>
 * saves the Survey Oobject in the survey_result table in the database
 * <p>
 * <p>
 * This interface was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with SurveyJdbcDao as well as the NPGeekController
 * <p>
 */
public interface SurveyDao {
	
	/**
	 * Map<Park,Integer> getVoteCount(String state, int activityNum);
	 * <p>
	 * <p>
	 * returns a map with all of the keys being all of the Park Objects stored in the park tabe in the database
	 * with the map value being the number of votes that park has recievied from the survey results
	 * for all parks that have recieved one vote and match the search criteria
	 * <p>
	 */
	Map<Park,Integer> getVoteCount(String state, String activityLevel);
	/**
	 * void saveSurvey(Survey survey);
	 * <p>
	 * <p>
	 * saves the Survey Oobject in the survey_result table in the database
	 * <p>
	 */
	void saveSurvey(Survey survey);
	
}
