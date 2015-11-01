package template.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleGeometry {
	
	@JsonProperty("location")
	private GoogleLocation googleLocation;

	public GoogleLocation getGoogleLocation() {
		return googleLocation;
	}

	public void setGoogleLocation(GoogleLocation googleLocation) {
		this.googleLocation = googleLocation;
	}

}
