package template.models;

public class NationalParkLocation {
	private String name;
	private double longitude;
	private double latitude;
	
	public NationalParkLocation(String name, double latitude, double longitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
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
	
	public String toString() {
		return this.name + ":(" + this.latitude + ", " + this.longitude + ")";
	}

}
