package template.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import template.algorithm.Ranking;
import template.googlePlaceConsumer.json.GoogleLocation;
import template.googlePlaceConsumer.json.GooglePlace;

import org.springframework.ui.Model;

import template.models.NationalParkLocation;
import template.services.AlgorithmService;
import template.services.GooglePlacesService;

@Controller
public class MainController {
	@Autowired
	private GooglePlacesService gps;
	
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
	    
	    System.out.println(maxDistance);
	    System.out.println(locationName);

		double latitude;
		double longitude;
		
		//GooglePlacesService place = new GooglePlacesService(locationName);
		GooglePlace gp = gps.getPlaceDetails(locationName);
		GoogleLocation gl = gp.getGoogleGeometry().getGoogleLocation();
		System.out.println(gl);
		latitude = gl.getLatitude();
		longitude = gl.getLongitude();
		
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		Ranking ranking = as.getRanking(latitude, longitude);
		
		model.addAttribute("ranking", ranking);
	    
		return "map";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(required=true) String locationName, Model model){
		double latitude;
		double longitude;
		
		//GooglePlacesService place = new GooglePlacesService(locationName);
		GooglePlace gp = gps.getPlaceDetails(locationName);
		GoogleLocation gl = gp.getGoogleGeometry().getGoogleLocation();
		System.out.println(gl);
		latitude = gl.getLatitude();
		longitude = gl.getLongitude();
		
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		Ranking ranking = new Ranking(latitude, longitude);
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
