package template.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Ranking {
	
	private final double DISTANCE_WEIGHT = 0.7;
	private final double RATING_WEIGHT = 0.3;
	
	private List<NationalParkLocation> parks;
	
	public String toString(){
		return parks.toString();
	}
	
	public Ranking(double latitude, double longitude) {
		parks = NationalParkLocation.getAllParkLocations();
		for(NationalParkLocation npl: parks){
			System.out.println(npl.getName());
		}
		rank(longitude, longitude);
		
		
	}
	
	public List<NationalParkLocation> getRanking(){
		for(NationalParkLocation npl: parks){
			System.out.println(npl.getName());
		}
		return parks;
	}
	
	
	private boolean rank(double latitude, double longitude){
		boolean rank = false;
		HashMap<NationalParkLocation, Double> distanceScore = new HashMap();
		HashMap<NationalParkLocation,Double> ratingScore = new HashMap();
		
		for(NationalParkLocation npl: parks){
			distanceScore.put(npl, distance(latitude, longitude, npl));
		}
		
		for(NationalParkLocation npl: parks){
			ratingScore.put(npl, rating(npl));
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
	
	private double distance(double latitude, double longitude, NationalParkLocation npl){
		
		double distance = 0.0;
		double parkLat = 0.0;
		double parkLong = 0.0;
		
		parkLat = npl.getLatitude();
		parkLong = npl.getLongitude();
		
		double dlat = latitude - parkLat;
		double dlong = longitude - parkLong;
		
		double a = Math.pow(Math.sin(dlat/2.0),2.0) + Math.cos(parkLat)*Math.cos(latitude)* Math.pow(Math.sin(dlong/2.0),2.0);
		double c = 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		distance = 3961.0 * c;	
			
		return distance;
	}
	
	private double rating(NationalParkLocation npl){
		//GooglePlacesDBService gps = new GooglePlacesDBService(npl.getName());
		//GooglePlace pgp = gps.getPlaceDetails();
//		System.out.println(pgp.getRating());
//		String placeId = pgp.getRating();
		//return pgp.getRating();
		return 0;
	}

}
