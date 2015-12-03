package template.models;

import template.services.NationalParkLocationServiceImpl;

public class NationalParkLocation {
	private String name;
	private double longitude;
	private double latitude;
	private String terrain;
	private String code;
	private double distance;
	private NationalParkLocationServiceImpl.BorderPointLists borders;
	private final static double RADIUS_OF_EARTH = 3959.0;
	
	public NationalParkLocation(String name, double latitude, double longitude, String terrain, String code) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.terrain = terrain;
		this.code = code;
		this.distance = 0;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public NationalParkLocationServiceImpl.BorderPointLists getBorders() {
		return borders;
	}

	public void setBorders(NationalParkLocationServiceImpl.BorderPointLists borders) {
		this.borders = borders;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	public double getDistance(){
		return distance;
	}
	
	public String toString() {
		return this.name + ":(" + this.latitude + ", " + this.longitude + ")" + " terrain: " + this.terrain;
	}
	
	public double distance(double latitude, double longitude){
		
		double distance = 0.0;
		double parkLat = 0.0;
		double parkLong = 0.0;
		
		latitude = latitude * Math.PI / 180.0;
		longitude = longitude * Math.PI / 180.0;
		
		parkLat = this.getLatitude();
		parkLat = parkLat * Math.PI / 180.0;
		parkLong = this.getLongitude();
		parkLong = parkLong * Math.PI / 180.0;
		

		
		double dlat = latitude - parkLat;
		double dlong = longitude - parkLong;
		
		double dx = dlong * Math.cos((latitude + parkLat)/2.0);
		double dy = dlat;
		distance = RADIUS_OF_EARTH * Math.sqrt(dx*dx + dy*dy);
		
		
//		double a = Math.pow(Math.sin(dlat/2.0),2.0) + Math.cos(parkLat)*Math.cos(latitude)* Math.pow(Math.sin(dlong/2.0),2.0);
//		double c = 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//		distance = 3961.0 * c;
//		distance = Math.sqrt(dlat+dlong);
		this.distance = distance;
		return distance;
	}

}
