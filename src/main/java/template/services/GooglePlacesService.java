package template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import template.googlePlaceConsumer.json.GooglePlace;
import template.googlePlaceConsumer.json.GooglePlaces;

@Service
public class GooglePlacesService {
	private static final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?key={KEY}&query={location}";
	
//	private static final String FELIX_KEY = "AIzaSyBxSJwn_0wag2TcejfFLG64oVHTtkuALCo";
	private static final String JONATHAN_KEY = "AIzaSyBIoKRR5QF4akp1qcGxfJwYTdhy8RTq_Tw";

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	public GooglePlace getPlaceDetails(String locationName) {
		System.out.println(URL);
		System.out.println(JONATHAN_KEY);
		System.out.println(locationName);

		GooglePlaces gps = restTemplate.getForObject(URL,
				  GooglePlaces.class, JONATHAN_KEY, locationName);
		System.out.println(gps);
		GooglePlace gp = gps.getGooglePlaces().get(0);
		System.out.println(gp);
		return gp;
	}
	
}
