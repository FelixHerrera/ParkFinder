/*
 * Filename:    GoogleLocation.java
 * Description: This holds the location of a GooglePlace json object
 */
package template.googlePlaceConsumer.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleLocation implements Serializable {
	@JsonProperty("lat")
	private double latitude;
	@JsonProperty("lng")
	private double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
