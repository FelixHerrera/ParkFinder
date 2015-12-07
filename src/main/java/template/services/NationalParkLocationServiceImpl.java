/*
 * Filename: NationalParkLocationServiceImpl.java
 * Description: Implementation of NationalParkLocationService class to extract data from
 * National Park Service JSON files
 */
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
import org.springframework.stereotype.Service;

import template.models.NationalParkLocation;

@Service
public class NationalParkLocationServiceImpl implements NationalParkLocationService {
	
	public class BorderPointLists {
		private class BorderPoint {
			public double latitude;
			public double longitude;
		};
		public ArrayList<ArrayList<BorderPoint>> points = new ArrayList<ArrayList<BorderPoint>>();
		public double getBorderPointLat(int i, int j) {
			return points.get(i).get(j).latitude;
		}
		public double getBorderPointLon(int i, int j) {
			return points.get(i).get(j).longitude;
		}
		
	};

	public List<NationalParkLocation> getAllParkLocations(){
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
				lon = coordinates.getDouble(0);
				lat = coordinates.getDouble(1);
				JSONObject properties = jsonLocation.getJSONObject("properties");
				String name = properties.getString("UNIT_NAME");
				String terrain = properties.getString("UNIT_TYPE");
				String code = properties.getString("UNIT_CODE");
				NationalParkLocation npl = new NationalParkLocation(name, lat, lon, terrain, code);
				npl.setBorders(getBorders(npl));
				result.add(npl);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (JSONException e) {
			// TODO: log exception
		}
		return result;
	}
	
	public BorderPointLists getBorders(NationalParkLocation npl) {
		BorderPointLists result = new BorderPointLists();
		String fileName = "src/main/resources/json/" + npl.getCode().toLowerCase() + ".geojson";
		double lat,lon;
		try(FileReader fr = new FileReader(fileName)) {
			JSONTokener jsonT = new JSONTokener(fr);
			JSONObject obj = new JSONObject(jsonT);
			/*
			JSONArray features = obj.optJSONArray("features");
			for (int i=0; i < features.length(); i++) {
				JSONObject jsonLocation = features.getJSONObject(i);
				String type = jsonLocation.getString("type");
				if (!"Feature".equals(type)) {
					continue;
				}
			*/
			JSONObject geo = obj.getJSONObject("geometry");
			JSONArray borders = geo.getJSONArray("coordinates");
				//JSONArray borders = jsonLocation.getJSONObject("geometry").getJSONArray("coordinates");
				//
			int i = 0;
				for (int j=0; j < borders.length(); j++) {
					result.points.add(new ArrayList<BorderPointLists.BorderPoint>());
					JSONArray border = borders.getJSONArray(j);
					for(int k=0; k < border.length(); k++) {
						BorderPointLists.BorderPoint aPoint = result.new BorderPoint();
						result.points.get(i).add(aPoint);
						BorderPointLists.BorderPoint p = result.points.get(i).get(j);
						JSONArray point = border.getJSONArray(k);
						p.longitude = point.getDouble(0);
						p.latitude = point.getDouble(1);	
					}
				}
				
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (JSONException e) {
			// TODO: log exception
		}
		return result;
	}
	
}
