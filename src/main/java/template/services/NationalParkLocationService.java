package template.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import template.models.NationalParkLocation;

public class NationalParkLocationService {

	public static List<NationalParkLocation> getAllParkLocations(){
		ArrayList<NationalParkLocation> result = new ArrayList();
		
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
				JSONArray coordinates = jsonLocation.getJSONObject("geometry").getJSONArray("coordinates");
				lat = coordinates.getDouble(0);
				lon = coordinates.getDouble(1);
				JSONObject properties = jsonLocation.getJSONObject("properties");
				String name = properties.getString("UNIT_NAME");
				result.add(new NationalParkLocation(name, lon, lat));
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (JSONException e) {
			// TODO: log exception
		}
		return result;
	}
	
}
