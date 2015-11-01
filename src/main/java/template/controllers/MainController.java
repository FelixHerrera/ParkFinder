package template.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import template.main.GooglePlacesService;

import org.springframework.ui.Model;

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
}
