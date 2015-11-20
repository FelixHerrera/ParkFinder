package template.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import template.algorithm.Ranking;
import template.criteria.Criteria;
import template.criteria.CriteriaFactory;
import template.googlePlaceConsumer.json.GoogleLocation;
import template.googlePlaceConsumer.json.GooglePlace;
import template.managed.resources.AlloyResourceHttpRequestHandler;

import org.springframework.ui.Model;

import template.models.NationalParkLocation;
import template.services.AlgorithmService;
import template.services.GooglePlacesService;
import template.services.NationalParkLocationService;

@Controller
public class MainController {
	private static final Logger logger = LogManager.getLogger(MainController.class);
	
	@Autowired
	private GooglePlacesService gps;
	@Autowired
	private NationalParkLocationService npls;
	
	@Autowired
	private AlgorithmService as;
	
	@RequestMapping("/")
	public String home() {

		return "index";
	}
	
	@RequestMapping("/map")
	public String map(@RequestParam(required=false) String locationName,
	                  @RequestParam(required=false) String maxDistance, Model model) {

	    model.addAttribute("locationName", locationName);
	    model.addAttribute("maxDistance", maxDistance);
	    
		double latitude;
		double longitude;
		
		GooglePlace gp = gps.getPlaceDetails(locationName);
		GoogleLocation gl = gp.getGoogleGeometry().getGoogleLocation();
		
		logger.debug(gl);
		
		latitude = gl.getLatitude();
		longitude = gl.getLongitude();
		
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		Ranking ranking = as.getRanking(latitude, longitude);
		
		model.addAttribute("ranking", ranking);
	    
		return "map";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(required=true) String locationName,
			             @RequestParam(required=false) String maxDistance, 
			             @RequestParam(required=false) String terrainType,
						 @RequestParam(required=false) String parkSize, Model model){
		
		System.out.println("The following values can be found int \"search\"" +
				" within the MainController, pulled from the client side.");
		System.out.println(locationName);
		System.out.println(maxDistance);
		System.out.println(terrainType);
		System.out.println(parkSize);
		
		GooglePlace gp = gps.getPlaceDetails(locationName);
		GoogleLocation gl = gp.getGoogleGeometry().getGoogleLocation();
		
		logger.debug("Given google location: " + gl);
		
		double latitude = gl.getLatitude();
		double longitude = gl.getLongitude();
		
		CriteriaFactory CFactory = new CriteriaFactory();
		Criteria terrainCriteria = null;
		Criteria sizeCriteria = null;
		Criteria distanceCriteria = null;
		if (!terrainType.equals("NoPreference")) {
			System.out.println("Creating terrain criteria");
			terrainCriteria = CFactory.createCriteria("terrain", terrainType);
		}
		if (!parkSize.equals("NoPreference")) {
			System.out.println("Creating size criteria");
			sizeCriteria = CFactory.createCriteria("size", parkSize);
		}
		if (!maxDistance.equals("0")) {
			System.out.println("Creating distance criteria");
			maxDistance = maxDistance + "," + String.valueOf(latitude) + "," + String.valueOf(longitude);
			distanceCriteria = CFactory.createCriteria("distance", maxDistance);
		}
		
		model.addAttribute("gl", gl);
		Ranking ranking = new Ranking(latitude, longitude, npls, terrainCriteria, distanceCriteria, 
				sizeCriteria);
		List<NationalParkLocation> rankedParks = ranking.getRanking();
		
		model.addAttribute("ranking", rankedParks);
		
		return "search";
	}

	@RequestMapping("/modeltest")
	public String modelTest(Model model) {
		model.addAttribute("hello", "World");
		return "modelTest";
	}

	@RequestMapping("/location")
	public String location(Model model){
		return "location";
	}
	
	@RequestMapping("/locations")
	public String locations(Model model){
		return "locations";
	}
}
