package template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import template.main.GooglePlace;
import template.main.GooglePlaces;



@RestController
public class GooglePlacesService {
	private static final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?key={KEY}&query={location}";
	private static final String URL2 = "https://maps.googleapis.com/maps/api/place/details/json?key={KEY}placeid={placeID}";
	
//	private static final String FELIX_KEY = "AIzaSyBxSJwn_0wag2TcejfFLG64oVHTtkuALCo";
	private static final String JONATHAN_KEY = "AIzaSyBIoKRR5QF4akp1qcGxfJwYTdhy8RTq_Tw";
	private String location = "";
	private String placeId = "";
	
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	public GooglePlacesService(String location){
		this.location = location;
	}
	
	public GooglePlace getPlaceDetails() {
		System.out.println(URL);
		System.out.println(JONATHAN_KEY);
		System.out.println(location);

		GooglePlaces gps = restTemplate.getForObject(URL,
				  GooglePlaces.class, JONATHAN_KEY, location);
		System.out.println(gps);
		GooglePlace gp = gps.getGooglePlaces().get(0);
		System.out.println(gp);
		return gp;
	}
	
}
