package template.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
		ArrayList<NationalParkLocation> result = new ArrayList<NationalParkLocation>();
		
		double lat, lon;
		try(FileReader fr = new FileReader("src/main/resources/json/parks-centroids.geojson")) {
			JSONTokener jsonT = new JSONTokener(fr);
			JSONObject obj = new JSONObject(jsonT);
			JSONArray features = obj.optJSONArray("features");
			for (int i=0; i < features.length(); i++) {
				JSONObject jsonLocation = features.getJSONObject(i);
				String type = jsonLocation.getString("type");
				if (!"Feature".equals(type)) {
					continue;
				}
				System.out.println("Here");
				JSONArray coordinates = jsonLocation.getJSONObject("geometry").getJSONArray("coordinates");
				lat = coordinates.getDouble(0);
				lon = coordinates.getDouble(1);
				JSONObject properties = jsonLocation.getJSONObject("properties");
				String name = properties.getString("UNIT_NAME");
				result.add(new NationalParkLocation(name, lon, lat));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
