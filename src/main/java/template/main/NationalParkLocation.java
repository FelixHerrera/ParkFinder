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
		result.add(new NationalParkLocation("New York", 3.0, 1.0));
		result.add(new NationalParkLocation("Phoenix", 2.0, 8.0));
		result.add(new NationalParkLocation("Dallas", 4.0, 9.0));
		result.add(new NationalParkLocation("San Diego", 900.0, -1.0));
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
