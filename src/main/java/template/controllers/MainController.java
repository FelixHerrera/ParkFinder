package template.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import template.main.GoogleLocation;
import template.main.GooglePlace;
import template.main.GooglePlacesService;

import org.springframework.ui.Model;

import template.main.Ranking;

@Controller
public class MainController {
	@RequestMapping("/")
	public String home() {

		return "index";
	}
	
	@RequestMapping("/map")
	public String map() {

		return "map";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(required=true) String locationName, Model model){
		double latitude;
		double longitude;
		
		GooglePlacesService place = new GooglePlacesService(locationName);
		GooglePlace gp = place.getPlaceDetails();
		GoogleLocation gl = gp.getGoogleGeometry().getGoogleLocation();
		System.out.println(gl);
		latitude = gl.getLatitude();
		longitude = gl.getLongitude();
		
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		Ranking ranking = new Ranking(latitude, longitude);
		
		model.addAttribute("ranking", ranking);
		
		return "search";
	}

	@RequestMapping("/modeltest")
	public String modelTest(Model model) {
		model.addAttribute("hello", "World");
		return "modelTest";
	}


	@RequestMapping("/googletest")
	public String displayjson(Model model){
		GooglePlacesService place = new GooglePlacesService(31.549333, -97.1466695);
		System.out.println(place.getPlaceDetails());
		model.addAttribute("place", place.getPlaceDetails());
		return "googletest";
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
