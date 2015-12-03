package template.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Cache;
import template.googlePlaceConsumer.json.GooglePlace;
import template.googlePlaceConsumer.json.GooglePlaces;

@Service
public class GooglePlacesService {
	private static final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?key={KEY}&query={location}";
	
//	private static final String FELIX_KEY = "AIzaSyBxSJwn_0wag2TcejfFLG64oVHTtkuALCo";
	private static final String JONATHAN_KEY = "AIzaSyBIoKRR5QF4akp1qcGxfJwYTdhy8RTq_Tw";
	//private static final String FELIX_KEY = "AIzaSyA5eYzsn2LHNmUASOhcvqVg6wzgn4KO4BI";
//	private static final String CLARRISA_KEY = "AIzaSyA5eYzsn2LHNmUASOhcvqVg6wzgn4KO4BI";
//	private static final String KRISTEN_KEY = "AIzaSyB6aRJYrWvPlYhuxHQGTlQDTKn-Y4X9_yE";

	@Autowired
	private RestTemplate restTemplate;
	
	public GooglePlace getPlaceDetails(String locationName) {
		CacheManager cm = CacheManager.create();
		Cache c = cm.getCache("getPlaceDetails");
		Element element = c.get(locationName);
		System.out.println(locationName);
		System.out.println(element);
		if (element == null) {
			GooglePlaces gps = restTemplate.getForObject(URL,
					  GooglePlaces.class, JONATHAN_KEY, locationName);
			ArrayList<GooglePlace> intermediate = gps.getGooglePlaces();
			if (intermediate.size() == 0){
				return null;
			}
			GooglePlace gp = intermediate.get(0);
			c.put(new Element(locationName, gp));
			c.flush();
			return gp;
		} else {
			GooglePlace gp = (GooglePlace) element.getObjectValue();
			return gp;
		}

	}
	
}
