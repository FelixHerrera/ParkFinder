package template.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GooglePlace {

	@JsonProperty("name")
	private String name;
	@JsonProperty("geometry")
	private GoogleGeometry googleGeometry;
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
