package template.models;

public class NationalParkLocation {
	private int id;
	private int latCoord;
	private int longCoord;
	private String name;
	private int terrain;
	
	public NationalParkLocation() {}
	
	public NationalParkLocation(int latCoord, int longCoord, String name, int terrain) {
		this.latCoord = latCoord;
		this.longCoord = longCoord;
		this.name = name;
		this.terrain = terrain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLatCoord() {
		return latCoord;
	}

	public void setLatCoord(int latCoord) {
		this.latCoord = latCoord;
	}

	public int getLongCoord() {
		return longCoord;
	}

	public void setLongCoord(int longCoord) {
		this.longCoord = longCoord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTerrain() {
		return terrain;
	}

	public void setTerrain(int terrain) {
		this.terrain = terrain;
	}
}
