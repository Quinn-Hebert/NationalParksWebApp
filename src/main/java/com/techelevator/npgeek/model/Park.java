package com.techelevator.npgeek.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * Park Class
 * <p>
 * <p>
 * Containing park code, park name, state, acreage, elevation in feet, miles of trail,
 * number of campsites, the year it was founded, an annual visitor count,
 * an inspirational quote, an inspirational quote source, a description,
 * an entry fee amount, and the number of animal species
 * <p>
 * <p>
 * This class was made to interact with com.techelevator.npgeek package utilizing the npgeek sequel database and
 * in use with ParkJdbcDao as well as the NPGeekController
 * <p>
 */
public class Park {
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
	 * Park's Name
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=200)
	private String parkName;
	/**
	 * <p>
	 * Park's State Location
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=30)
	private String state;
	/**
	 * <p>
	 * Park's Acreage Number
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer acreage;
	/**
	 * <p>
	 * Park's Elevation In Feet
	 * Type Integer
	 * <p>
	 */
	@NotNull
	private Integer elevationInFeet;
	/**
	 * <p>
	 * Park's Miles of Hiking Trails
	 * Type Double
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Double milesOfTrail;
	/**
	 * <p>
	 * Park's Number of Campsites
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer campsites;
	/**
	 * <p>
	 * Park's Climate
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=100)
	private String climate;
	/**
	 * <p>
	 * Park's Year Founded
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer yearFounded;
	/**
	 * <p>
	 * Park's Annual Visitor Count
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer annualVisitorCount;
	/**
	 * <p>
	 * Park's Inspirational Quote
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1)
	private String inspirationalQuote;
	/**
	 * <p>
	 * Park's Inspirational Quote Source
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1,max=200)
	private String inspirationalQuoteSource;
	/**
	 * <p>
	 * Park's Description
	 * Type String
	 * <p>
	 */
	@NotBlank
	@Size(min=1)
	private String description;
	/**
	 * <p>
	 * Park's Entry Fee Amount
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer entryFee;
	/**
	 * <p>
	 * Park's Number of Animal Species
	 * Type Integer
	 * <p>
	 */
	@NotNull
	@Min(value=0)
	private Integer numberOfAnimalSpecies;
	
	/**
	 * <p>
	 * Get Park's Code
	 * <p>
	 */
	public String getParkCode() {
		return parkCode;
	}
	/**
	 * <p>
	 * Set Park's Code
	 * <p>
	 */
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	/**
	 * <p>
	 * Get Park's Name
	 * <p>
	 */
	public String getParkName() {
		return parkName;
	}
	/**
	 * <p>
	 * Set Park's Name
	 * <p>
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	/**
	 * <p>
	 * Get Park's State Location
	 * <p>
	 */
	public String getState() {
		return state;
	}
	/**
	 * <p>
	 * Set Park's State Location
	 * <p>
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * <p>
	 * Get Park's Acreage Number
	 * <p>
	 */
	public int getAcreage() {
		return acreage;
	}
	/**
	 * <p>
	 * Set Park's Acreage Number
	 * <p>
	 */
	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}
	/**
	 * <p>
	 * Get Park's Elevation In Feet
	 * <p>
	 */
	public int getElevationInFeet() {
		return elevationInFeet;
	}
	/**
	 * <p>
	 * Set Park's Elevation In Feet
	 * <p>
	 */
	public void setElevationInFeet(int elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
	}
	/**
	 * <p>
	 * Get Park's Miles of Hiking Trails
	 * <p>
	 */
	public double getMilesOfTrail() {
		return milesOfTrail;
	}
	/**
	 * <p>
	 * Set Park's Miles of Hiking Trails
	 * <p>
	 */
	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	/**
	 * <p>
	 * Get Park's Number of Campsites
	 * <p>
	 */
	public int getCampsites() {
		return campsites;
	}
	/**
	 * <p>
	 * Set Park's Number of Campsites
	 * <p>
	 */
	public void setCampsites(int campsites) {
		this.campsites = campsites;
	}
	/**
	 * <p>
	 * Get Park's Climate
	 * <p>
	 */
	public String getClimate() {
		return climate;
	}
	/**
	 * <p>
	 * Set Park's Climate
	 * <p>
	 */
	public void setClimate(String climate) {
		this.climate = climate;
	}
	/**
	 * <p>
	 * Get Park's Year Founded
	 * <p>
	 */
	public int getYearFounded() {
		return yearFounded;
	}
	/**
	 * <p>
	 * Set Park's Year Founded
	 * <p>
	 */
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	/**
	 * <p>
	 * Get Park's Annual Visitors Count
	 * <p>
	 */
	public int getAnnualVisitorCount() {
		return annualVisitorCount;
	}
	/**
	 * <p>
	 * Set Park's Annual Visitors Count
	 * <p>
	 */
	public void setAnnualVisitorCount(int annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}
	/**
	 * <p>
	 * Get Park's Inspirational Quote
	 * <p>
	 */
	public String getInspirationalQuote() {
		return inspirationalQuote;
	}
	/**
	 * <p>
	 * Set Park's Inspirational Quote
	 * <p>
	 */
	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}
	/**
	 * <p>
	 * Get Park's Inspirational Quote Source
	 * <p>
	 */
	public String getInspirationalQuoteSource() {
		return inspirationalQuoteSource;
	}
	/**
	 * <p>
	 * Set Park's Inspirational Quote Source
	 * <p>
	 */
	public void setInspirationalQuoteSource(String inspirationalQuoteSource) {
		this.inspirationalQuoteSource = inspirationalQuoteSource;
	}
	/**
	 * <p>
	 * Get Park's Description
	 * <p>
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * <p>
	 * Set Park's Description
	 * <p>
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * <p>
	 * Get Park's Entry Fee Amount
	 * <p>
	 */
	public int getEntryFee() {
		return entryFee;
	}
	/**
	 * <p>
	 * Set Park's Entry Fee Amount
	 * <p>
	 */
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	/**
	 * <p>
	 * Get Park's Number of Animal Species
	 * <p>
	 */
	public int getNumberOfAnimalSpecies() {
		return numberOfAnimalSpecies;
	}
	/**
	 * <p>
	 * Set Park's Number of Animal Species
	 * <p>
	 */
	public void setNumberOfAnimalSpecies(int numberOfAnimalSpecies) {
		this.numberOfAnimalSpecies = numberOfAnimalSpecies;
	}
	
}
