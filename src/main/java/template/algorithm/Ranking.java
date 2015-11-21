package template.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import template.criteria.Criteria;
import template.models.NationalParkLocation;
import template.services.NationalParkLocationService;
import template.services.NationalParkLocationServiceImpl;

public class Ranking {
	
	private final static double DISTANCE_WEIGHT = 0.7;
	private final static double RATING_WEIGHT = 0.3;
	
	private List<NationalParkLocation> parks;
	
	public Ranking(double latitude, double longitude, NationalParkLocationService npls, 
			Criteria terrain, Criteria distance, Criteria size) {
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
		if (terrain != null) {
			ArrayList<NationalParkLocation> toRemove = new ArrayList<NationalParkLocation>();
			System.out.println("Found terrain criteria");
			for (NationalParkLocation npl : parks) {
				if (terrain.fitCriteria(npl) == false) {
					toRemove.add(npl);
				}
			}
			System.out.println(toRemove.toString());
			for (NationalParkLocation npl : toRemove) {
				parks.remove(npl);
			}
		}
		System.out.println("After terrain removal");
		System.out.println(parks.toString());
		
		if (distance != null) {
			ArrayList<NationalParkLocation> toRemove = new ArrayList<NationalParkLocation>();
			System.out.println("Found distance criteria");
			for (NationalParkLocation npl: parks) {
				if (distance.fitCriteria(npl) == false) {
					toRemove.add(npl);
				}
			}
			System.out.println(toRemove.toString());
			for (NationalParkLocation npl : toRemove) {
				parks.remove(npl);
			}
		}
		System.out.println("After distance removal");
		for (NationalParkLocation npl : parks) {
			System.out.println(npl.toString() + " distance: " + npl.distance(latitude, longitude));
		}
		
		if (size != null) {
			ArrayList<NationalParkLocation> toRemove = new ArrayList<NationalParkLocation>();
			System.out.println("Found size criteria");
			for (NationalParkLocation npl: parks) {
				if (size.fitCriteria(npl) == false) {
					toRemove.add(npl);
				}
			}
			System.out.println(toRemove.toString());
			for(NationalParkLocation npl: toRemove) {
				parks.remove(npl);
			}
		}
		System.out.println("After size removal");
		for (NationalParkLocation npl: parks) {
			System.out.println(npl.toString());
		}
		
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
		//GooglePlacesDBService gps = new GooglePlacesDBService(npl.getName());
		//GooglePlace pgp = gps.getPlaceDetails();
//		String placeId = pgp.getRating();
		//return pgp.getRating();
		return 0;
	}

	public String toString(){
		return parks.toString();
	}

}
