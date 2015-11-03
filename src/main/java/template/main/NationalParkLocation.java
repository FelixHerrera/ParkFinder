package template.main;

import java.util.ArrayList;
import java.util.List;

public class NationalParkLocation {
	private String name;
	private double longitude;
	private double latitude;
	
	public NationalParkLocation(String name, double longitude, double latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public static List<NationalParkLocation> getAllParkLocations(){
		ArrayList<NationalParkLocation> result = new ArrayList();
		result.add(new NationalParkLocation("Glacier National Park", 48.76, -113.79));
		result.add(new NationalParkLocation("Big Thicket National Preserve", 30.47, -94.35));
		result.add(new NationalParkLocation("Arches National Park", 38.73, -109.59));
		result.add(new NationalParkLocation("Rocky Mountain National Park", 40.34, -105.68));
		return result;
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

}
