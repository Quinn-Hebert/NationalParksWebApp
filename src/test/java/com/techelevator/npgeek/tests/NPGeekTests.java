package com.techelevator.npgeek.tests;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.npgeek.model.*;

//Tests for com.techelevator.npgeek.model*
public class NPGeekTests {

	private static SingleConnectionDataSource dataSource;
	
	/* 
	 * Initialize the DataSource
	 */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	/* 
	 * Rollback changes to database
	 * Close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.getConnection().rollback();
		dataSource.destroy();
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	private ParkDao parkDao = new ParkJdbcDao(dataSource);
	private WeatherDao weatherDao = new WeatherJdbcDao(dataSource);
	private SurveyDao surveyDao = new SurveyJdbcDao(dataSource);
	
	@Test
	public void npgeek_Park_JdbcDao_GetInfoForPark_Test() {
		Park park = parkDao.getInfoForPark("GNP");
		Assert.assertTrue(park != null);
	}
	
	@Test
	public void npgeek_ParkJdbcDao_GetAllParks_Test() {
		List<Park> parksList = parkDao.getAllParks();
		Assert.assertEquals(parksList.size(),10);
	}
	
	@Test
	public void npgeek_WeatherJdbcDao_Test() {
		List<Weather> weatherForecastTest = weatherDao.getFiveDayForecast("GNP");
		Assert.assertEquals(weatherForecastTest.size(),5);
	}
	
	@Test
	public void npgeek_SurveyJdbcDao_Basic_Tests() {
		Survey survey = new Survey();
		survey.setParkCode("GCNP");
		survey.setEmail("testemail@tester.com");
		survey.setState("OH");
		survey.setActivityLevel("2");
		Assert.assertEquals(survey.getParkCode(),"GCNP");
		Assert.assertEquals(survey.getEmail(),"testemail@tester.com");
		Assert.assertEquals(survey.getState(),"OH");
		Assert.assertEquals(survey.getActivityLevel(),"2");
	}
	
	@Test
	public void npgeek_SurveyJdbcDao_Tests() {
		Survey survey1 = new Survey();
		survey1.setParkCode("GCNP");
		survey1.setEmail("testemail@tester.com");
		survey1.setState("OH");
		survey1.setActivityLevel("active");
		Survey survey2 = new Survey();
		survey2.setParkCode("GNP");
		survey2.setEmail("testemail@tester.com");
		survey2.setState("OH");
		survey2.setActivityLevel("sedentary");
		Survey survey3 = new Survey();
		survey3.setParkCode("GTNP");
		survey3.setEmail("testemail@tester.com");
		survey3.setState("AL");
		survey3.setActivityLevel("active");
		surveyDao.saveSurvey(survey1);
		surveyDao.saveSurvey(survey2);
		surveyDao.saveSurvey(survey3);
		Map<Park,Integer> map = surveyDao.getVoteCount("", "");
		Assert.assertEquals(3,map.size());
		map = surveyDao.getVoteCount("OH", "");
		Assert.assertEquals(2,map.size());
		map = surveyDao.getVoteCount("", "active");
		Assert.assertEquals(2,map.size());
		map = surveyDao.getVoteCount("OH", "active");
		Assert.assertEquals(1,map.size());
	}
	
}
