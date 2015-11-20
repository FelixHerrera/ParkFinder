package template.services;

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

	@Autowired
	private RestTemplate restTemplate;
	
	@Cacheable(value = "getPlaceDetails")
	public GooglePlace getPlaceDetails(String locationName) {
		CacheManager cm = CacheManager.create();
		Cache c = cm.getCache("getPlaceDetails");
		Element element = c.get(locationName);
		if (element == null) {
			GooglePlaces gps = restTemplate.getForObject(URL,
					  GooglePlaces.class, JONATHAN_KEY, locationName);
			GooglePlace gp = gps.getGooglePlaces().get(0);
			c.put(new Element(locationName, gp));
			c.flush();
			return gp;
		} else {
			GooglePlace gp = (GooglePlace) element.getObjectValue();
			return gp;
		}

	}
	
}
