package template.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import template.criteria.Criteria;
import template.googlePlaceConsumer.json.GooglePlace;
import template.models.NationalParkLocation;
import template.services.GooglePlacesService;
import template.services.NationalParkLocationService;
import template.services.NationalParkLocationServiceImpl;

public class Ranking {
	
	private GooglePlacesService gps;
	
	private final static double DISTANCE_WEIGHT = 0.7;
	private final static double RATING_WEIGHT = 0.3;
	
	private List<NationalParkLocation> parks;
	
	public Ranking(double latitude, double longitude, NationalParkLocationService npls, GooglePlacesService gps, 
			Criteria terrain, Criteria distance, Criteria size) {
		this.gps = gps;
		parks = npls.getAllParkLocations();
		rank(latitude, longitude, terrain, distance, size);
	}
	
	public List<NationalParkLocation> getRanking(){
		return parks;
	}
	
	private boolean rank(double latitude, double longitude, Criteria terrain,
			Criteria distance, Criteria size){
		boolean rank = false;
		HashMap<NationalParkLocation, Double> distanceScore = new HashMap();
		HashMap<NationalParkLocation,Double> ratingScore = new HashMap();
		
		for(NationalParkLocation npl: parks){
			distanceScore.put(npl, npl.distance(latitude, longitude));
		}
		
		for(NationalParkLocation npl: parks){
			ratingScore.put(npl, rating(npl));
		}
		
		// Filter by criteria
		ArrayList<NationalParkLocation> newParks = new ArrayList<NationalParkLocation>();
		for (NationalParkLocation npl : parks) {
			if ((terrain == null || terrain.fitCriteria(npl)) &&
				(distance == null || distance.fitCriteria(npl)) &&
				(size == null || size.fitCriteria(npl))) {
				newParks.add(npl);
			}
		}
		parks = newParks;
		
		Collections.sort(parks, new Comparator<NationalParkLocation>() {

			@Override
			public int compare(NationalParkLocation npl1,
					NationalParkLocation npl2) {
					
				Double distanceScore1 = distanceScore.get(npl1);
				Double distanceScore2 = distanceScore.get(npl2);
				Double ratingScore1 = ratingScore.get(npl1);
				Double ratingScore2 = ratingScore.get(npl2);
				
				Double score1 = distanceScore1 * DISTANCE_WEIGHT + ratingScore1 * RATING_WEIGHT;
				Double score2 = distanceScore2 * DISTANCE_WEIGHT + ratingScore2 * RATING_WEIGHT;
				
				if (score1 > score2){
					return 1;
				}
				
				else if (score1 == score2){
					return 0;
				}
				
				else{
					return -1;
				}
				
			}
			
		});
		
		return rank;
	}
	
	private double rating(NationalParkLocation npl){
		GooglePlace pgp = gps.getPlaceDetails(npl.getName());
		if (pgp == null){
			return 0;
		}
		return pgp.getRating();
	}

	public String toString(){
		return parks.toString();
	}

}
