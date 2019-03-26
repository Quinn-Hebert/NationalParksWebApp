package com.techelevator.npgeek.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDao;
import com.techelevator.npgeek.model.WeatherDao;

@Controller
public class NPGeekController {
	
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private SurveyDao surveyDao;
	
	private List<Park> parksList;
	private Map<String, String> activityMap;
	private Map<String, String> stateMap;
	
	@RequestMapping("/") 
	public String displayHomePage(ModelMap modelMap) {
		populateParksList(modelMap);
		return "homepage";
	}
	
	@RequestMapping(path="/park", method=RequestMethod.GET)
	public String displayParkDetailPage(@RequestParam String id, ModelMap modelMap, HttpSession session) {
		//initialize tempScale
		if (session.getAttribute("tempScale") == null) {
			session.setAttribute("tempScale", "F");
		}
		modelMap.addAttribute("park", parkDao.getInfoForPark(id));
		modelMap.addAttribute("weather", weatherDao.getFiveDayForecast(id));
		return "parkDetail";
	}
	
	@RequestMapping(path="/park", method=RequestMethod.POST)
	public String handleParkDetailPage(@RequestParam String id, @RequestParam(required=false) String tempScaleChange, ModelMap modelMap, HttpSession session) {
		//if button is pushed change tempScale
		if (tempScaleChange != null) {
			session.setAttribute("tempScale", tempScaleChange);
		}
		if (session.getAttribute("tempScale") == null) {
			session.setAttribute("tempScale", "F");
		}
		return "redirect:/park?id="+id+"#weather";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyPage(ModelMap modelMap) {
		if( ! modelMap.containsAttribute("survey")){
			modelMap.put("survey", new Survey());
		}
		populateParksList(modelMap);
		populateMaps(modelMap);
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String handleSurveyPost(@Valid @ModelAttribute Survey survey, BindingResult result,
									RedirectAttributes flash) {
        flash.addFlashAttribute("survey", survey);
		if(result.hasErrors() || parkDao.getInfoForPark(survey.getParkCode()) == null ||
				!stateMap.containsKey(survey.getState()) || !activityMap.containsKey(survey.getActivityLevel())) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
        }
		surveyDao.saveSurvey(survey);
		return "redirect:/favoriteParks";
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
	public String displayFavoriteParksPage(ModelMap modelMap) {
		//initialize state and activity level to default values
		if (modelMap.get("state") == null) {
			modelMap.put("state", "");
		}
		if (modelMap.get("activityLevel") == null) {
			modelMap.put("activityLevel", "");
		}
		populateMaps(modelMap);
		modelMap.addAttribute("surveys", surveyDao.getVoteCount(String.valueOf(modelMap.get("state")), String.valueOf(modelMap.get("activityLevel"))));
		return "favoriteParks";
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.POST)
	public String handleFavoriteParksPage(@RequestParam String stateChosen, @RequestParam String activityLevelChosen,
										RedirectAttributes flash) {
		//set values for state and activity level to search by
		flash.addFlashAttribute("state", stateChosen);
		flash.addFlashAttribute("activityLevel", activityLevelChosen);
		return "redirect:/favoriteParks";
	}
	
	private void populateParksList(ModelMap modelMap) {
		if (parksList == null || parksList.isEmpty()) {
			parksList = parkDao.getAllParks();
		}
		modelMap.addAttribute("parksList", parksList);
	}
	
	private void populateMaps(ModelMap modelMap) {
		if (stateMap == null || stateMap.isEmpty()) {
			stateMap = stateAndAbbreviation();
		}
		if (activityMap == null || activityMap.isEmpty()) {
			activityMap = activityLevelMap();
		}
		modelMap.addAttribute("statesMap", stateMap);
		modelMap.addAttribute("activityLevelMap", activityMap);
	}
	
	private Map<String, String> activityLevelMap() {
		Map<String, String> activityMap = new LinkedHashMap<>();
		activityMap.put("inactive", "Inactive");
		activityMap.put("sedentary", "Sedentary");
		activityMap.put("active", "Active");
		activityMap.put("extremelyactive", "Extremely Active");
		
		return activityMap;
	}
	
	private Map<String, String> stateAndAbbreviation() {
		Map<String, String> stateMap = new LinkedHashMap<>();
		
		stateMap.put("AK", "Alaska");
		stateMap.put("AL", "Alabama");
		stateMap.put("AR", "Arkansas");
		stateMap.put("AZ", "Arizona");
		stateMap.put("CA", "California");
		stateMap.put("CO", "Colorado");
		stateMap.put("CT", "Connecticut");
		stateMap.put("DC", "District of Columbia");
		stateMap.put("DE", "Delaware");
		stateMap.put("FL", "Florida");
		stateMap.put("GA", "Georgia");
		stateMap.put("HI", "Hawaii");
		stateMap.put("IA", "Iowa");
		stateMap.put("ID", "Idaho");
		stateMap.put("IL", "Illinois");
		stateMap.put("IN", "Indiana");
		stateMap.put("KS", "Kansas");
		stateMap.put("KY", "Kentucky");
		stateMap.put("LA", "Louisiana");
		stateMap.put("MA", "Massachusetts");
		stateMap.put("MD", "Maryland");
		stateMap.put("ME", "Maine");
		stateMap.put("MI", "Michigan");
		stateMap.put("MN", "Minnesota");
		stateMap.put("MO", "Missouri");
		stateMap.put("MS", "Mississippi");
		stateMap.put("MT", "Montana");
		stateMap.put("NC", "North Carolina");
		stateMap.put("ND", "North Dakota");
		stateMap.put("NE", "Nebraska");
		stateMap.put("NH", "New Hampshire");
		stateMap.put("NJ", "New Jersey");
		stateMap.put("NM", "New Mexico");
		stateMap.put("NV", "Nevada");
		stateMap.put("NY", "New York");
		stateMap.put("OH", "Ohio");
		stateMap.put("OK", "Oklahoma");
		stateMap.put("OR", "Oregon");
		stateMap.put("PA", "Pennsylvania");
		stateMap.put("PR", "Puerto Rico");
		stateMap.put("RI", "Rhode Island");
		stateMap.put("SC", "South Carolina");
		stateMap.put("SD", "South Dakota");
		stateMap.put("TN", "Tennessee");
		stateMap.put("TX", "Texas");
		stateMap.put("UT", "Utah");
		stateMap.put("VA", "Virginia");
		stateMap.put("VT", "Vermont");
		stateMap.put("WA", "Washington");
		stateMap.put("WI", "Wisconsin");
		stateMap.put("WV", "West Virginia");
		stateMap.put("WY", "Wyoming");
		
		return stateMap;
	}
	
}
