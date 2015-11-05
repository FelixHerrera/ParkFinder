package template.googlePlaceConsumer.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GooglePlaces {
	
	@JsonProperty("results")
	private ArrayList<GooglePlace> googlePlaces;
	
	public ArrayList<GooglePlace> getGooglePlaces() {
		return googlePlaces;
	}

	public void setGooglePlaces(ArrayList<GooglePlace> googlePlaces) {
		this.googlePlaces = googlePlaces;
	}
	
}
