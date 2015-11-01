package template.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class GooglePlacesService {
	private static final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?key={KEY}&query={location}";
	private static final String URL2 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key={KEY}&location={latitude},{longitude}&radius=20";
	private static final String KEY = "AIzaSyBxSJwn_0wag2TcejfFLG64oVHTtkuALCo";
	private String location = "";

	public double latitude;
	public double longitude;
	
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	public GooglePlacesService(String location){
		this.location = location;
	}
	
	public GooglePlacesService(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public GooglePlace getPlaceDetails() {
		System.out.println(URL);
		System.out.println(KEY);
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(location);
		
		
		GooglePlaces gps = restTemplate.getForObject(URL,
				  GooglePlaces.class, KEY, location);
		System.out.println(gps);
		GooglePlace gp = gps.getGooglePlaces().get(0);
		System.out.println(gp);
		return gp;
	}
}
