package template.criteria;

import template.models.NationalParkLocation;

public class TerrainCriteria implements CriteriaFactory {
	
	private String terrainType; 
	
	public TerrainCriteria(String terrainType) {
		this.terrainType = terrainType;
	}

	public String getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(String terrainType) {
		this.terrainType = terrainType;
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		if (terrainType == npl.getTerrain()){
			return true;
		}
		else {
			return false;
		}
	}

}
