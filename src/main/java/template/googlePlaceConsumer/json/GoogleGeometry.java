/*
 * Filename:    GoogleGeometry.java
 * Description: This holds the geometry of a GooglePlace json object
 */
package template.googlePlaceConsumer.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleGeometry implements Serializable {
	
	@JsonProperty("location")
	private GoogleLocation googleLocation;

	public GoogleLocation getGoogleLocation() {
		return googleLocation;
	}

	public void setGoogleLocation(GoogleLocation googleLocation) {
		this.googleLocation = googleLocation;
	}

}
