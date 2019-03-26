package com.techelevator.npgeek.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * Weather Class
 * <p>
 * <p>
 * Containing park code, forecast day number, low temperature in F, high temperature in F,
 * low temperature in C, high temperature in C, forecast description, and day of week
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with Weather JdbcDao as well as the NPGeekController
 * <p>
 */
public class Weather {
	//declare variables and add constraints that follow the constraints of the database
	/**
	 * <p>
	 * Park's Code
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=10)
	private String parkCode;
	/**
	 * <p>
	 * Forecast Day Number
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Range(min=1,max=5)
	private Integer forecastDay;
	/**
	 * <p>
	 * Low Temperature in F
	 * Type Double
	 * <p>
	 */
	@NotNull
	private Double lowTempF;
	/**
	 * <p>
	 * Low Temperature in C
	 * Type Double
	 * <p>
	 */
	@NotNull
	private Double lowTempC;
	/**
	 * <p>
	 * High Temperature in F
	 * Type Double
	 * <p>
	 */
	@NotNull
	private Double highTempF;
	/**
	 * <p>
	 * High Temperature in C
	 * Type Double
	 * <p>
	 */
	@NotNull
	private Double highTempC;
	/**
	 * <p>
	 * Forecast Description
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=100)
	private String forecast;
	/**
	 * <p>
	 * Day of Week
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=20)
	private String day;
	
	/**
	 * <p>
	 * Get Park's Code for Weather
	 * <p>
	 */
	public String getParkCode() {
		return parkCode;
	}
	/**
	 * <p>
	 * Set Park's Code for Weather
	 * <p>
	 */
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	/**
	 * <p>
	 * Get Forecast Day Number
	 * <p>
	 */
	public int getForecastDay() {
		return forecastDay;
	}
	/**
	 * <p>
	 * Set Forecast Day Number
	 * <p>
	 */
	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}
	/**
	 * <p>
	 * Get Low Temperature in F
	 * <p>
	 */
	public double getLowTempF() {
		return lowTempF;
	}
	/**
	 * <p>
	 * Set Low Temperature in F
	 * <p>
	 */
	public void setLowTempF(double lowTempF) {
		this.lowTempF = lowTempF;
	}
	/**
	 * <p>
	 * Get High Temperature in F
	 * <p>
	 */
	public double getHighTempF() {
		return highTempF;
	}
	/**
	 * <p>
	 * Set High Temperature in F
	 * <p>
	 */
	public void setHighTempF(double highTempF) {
		this.highTempF = highTempF;
	}
	/**
	 * <p>
	 * Get Low Temperature in C
	 * <p>
	 */
	public double getLowTempC() {
		return lowTempC;
	}
	/**
	 * <p>
	 * Set Low Temperature in C
	 * <p>
	 */
	public void setLowTempC(double lowTempF) {
		this.lowTempC = (lowTempF - 32)/1.8;
	}
	/**
	 * <p>
	 * Get High Temperature in C
	 * <p>
	 */
	public double getHighTempC() {
		return highTempC;
	}
	/**
	 * <p>
	 * Set High Temperature in C
	 * <p>
	 */
	public void setHighTempC(double highTempF) {
		this.highTempC = (highTempF - 32)/1.8;
	}
	/**
	 * <p>
	 * Get Forecast Description
	 * <p>
	 */
	public String getForecast() {
		return forecast;
	}
	/**
	 * <p>
	 * Set Forecast Description
	 * <p>
	 */
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	/**
	 * <p>
	 * Get Day of Week
	 * <p>
	 */
	public String getDay() {
		return day;
	}
	/**
	 * <p>
	 * Set Day of Week
	 * <p>
	 */
	public void setDay() {
		//Get today's day of week
		DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
		//If forecast day number is 1 then it is Today
		if (forecastDay == 1) {
			day = "Today";
		}
		//If forecast day number is 2 then it is Tomorrow
		else if (forecastDay == 2) {
			day = "Tomorrow";
		}
		//Determine remaining days of week for 3, 4, and 5 using today's day
		else {
			int thisDay = dayOfWeek.getValue() + (forecastDay - 1);
			if (thisDay > 7) {
				thisDay -= 7;
			}
			day = DayOfWeek.of(thisDay).toString();
			day = day.substring(0,1) + day.substring(1).toLowerCase();
		}
	}
	
	public String getWeatherAdvisory() {
		String weatherAdvisory = "";
		if (this.getForecast().equals("sunny")) {
			weatherAdvisory = "Remember your sunblock!";
		} else if (this.getForecast().equals("thunderstorms")) {
			weatherAdvisory = "Seek shelter and avoid hiking on exposed ridges!";
		} else if (this.getForecast().equals("rain")) {
			weatherAdvisory = "Pack rain gear and waterproof shoes!";
		} else if (this.getForecast().equals("snow")) {
			weatherAdvisory = "Pack snowshoes!";
		}
		return weatherAdvisory;
	}
	
	public String getTempAdvisory() {	
		String tempAdvisory = "";
		if (this.getHighTempF() > 75.0) {
			tempAdvisory = "It'll be hot! Bring an extra gallon of water";
		} else if ( (this.getHighTempF() - this.getLowTempF()) > 20.0) {
			tempAdvisory = "Wear breathable layers, large temperature change through the day.";
		} else if (this.getHighTempF() < 20) {
			tempAdvisory = "Be extremely careful in frigid temperatures!";
		}
		
		return tempAdvisory;
	}
}
