package com.techelevator.npgeek.model;

import java.util.regex.Pattern;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * Survey Class
 * <p>
 * <p>
 * Containing park code, an email address, a state of residency, and an activity level
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with SurveyJdbcDao as well as the NPGeekController
 * <p>
 */
public class Survey {
	//declare variables and add constraints that follow the constraints of the database
	/**
	 * <p>
	 * Park's Code
	 * Type String
	 * <p>
	 */
	@NotBlank(message="*")
	@Size(max=10)
	private String parkCode;
	/**
	 * <p>
	 * Email Address
	 * Type String
	 * <p>
	 */
	@NotBlank(message="*")
	@Email(message="* Please enter a valid email.")
	@Size(max=100)
	private String email;
	/**
	 * <p>
	 * State of Residency
	 * Type String
	 * <p>
	 */
	@NotBlank(message="*")
	@Size(min=2,max=2)
	private String state;
	/**
	 * <p>
	 * Activity Level
	 * Type String
	 * <p>
	 */
	@NotBlank(message="*")
	@Size(min=6,max=15)
	private String activityLevel;
	
	//Test if email address is valid
	@AssertTrue(message="* Invalid email")
	public boolean isEmailValid() {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pattern.matcher(email).matches();
	}
	
	/**
	 * <p>
	 * Get Park's Code for Survey
	 * <p>
	 */
	public String getParkCode() {
		return parkCode;
	}
	/**
	 * <p>
	 * Set Park's Code for Survey
	 * <p>
	 */
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	/**
	 * <p>
	 * Get Email Address for Survey
	 * <p>
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * <p>
	 * Set Email Address for Survey
	 * <p>
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * <p>
	 * Get State of Residency for Survey
	 * <p>
	 */
	public String getState() {
		return state;
	}
	/**
	 * <p>
	 * Set State of Residency for Survey
	 * <p>
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * <p>
	 * Get Activity Level for Survey
	 * <p>
	 */
	public String getActivityLevel() {
		return activityLevel;
	}
	/**
	 * <p>
	 * Set Activity Level for Survey
	 * <p>
	 */
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
}
