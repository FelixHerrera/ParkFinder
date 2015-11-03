package template.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GooglePlace {

	@JsonProperty("name")
	private String name;
	@JsonProperty("geometry")
	private GoogleGeometry googleGeometry;
	@JsonProperty("rating")
	private double rating;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GoogleGeometry getGoogleGeometry() {
		return googleGeometry;
	}
	public void setGoogleGeometry(GoogleGeometry googleGeometry) {
		this.googleGeometry = googleGeometry;
	}

	
	
}
