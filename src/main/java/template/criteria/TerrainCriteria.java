package template.criteria;

import java.util.ArrayList;

import template.models.NationalParkLocation;

public class TerrainCriteria implements Criteria {
	
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
		boolean match = false;
		String parkTerrain = npl.getTerrain();
		ArrayList<String> terrainCategories = new ArrayList<String>();
		if (parkTerrain.equals("Historical Park") || 
				parkTerrain.equals("National Battlefield Park") ||
				parkTerrain.equals("National Historic Trail") ||
				parkTerrain.equals("National Historic Park") ||
				parkTerrain.equals("National Monument and Historic Shrine") ||
				parkTerrain.equals("National Historical Park") ||
				parkTerrain.equals("National Memorial") || 
				parkTerrain.equals("National Historical Site") ||
				parkTerrain.equals("National Heritage Corridor") ||
				parkTerrain.equals("National Battlefield Site") ||
				parkTerrain.equals("International Historic Site") ||
				parkTerrain.equals("National Military Park") ||
				parkTerrain.equals("National Historical Park and Preserve") ||
				parkTerrain.equals("National Historic Site") ||
				parkTerrain.equals("Ecological and Historical Preserve") ||
				parkTerrain.equals("National Battlefield") ||
				parkTerrain.equals("National Historical Reserve")) {
			terrainCategories.add("Historic Sites");
		}
		if (parkTerrain.equals("National Wild and Scenic Riverway") ||
				parkTerrain.equals("National River and Recreation Area") ||
				parkTerrain.equals("National Scenic Riverway") ||
				parkTerrain.equals("National Seashore") ||
				parkTerrain.equals("Wild River") ||
				parkTerrain.equals("National Lakeshore") ||
				parkTerrain.equals("National Scenic River") ||
				parkTerrain.equals("National Recreation River") ||
				parkTerrain.equals("Scenic and Recreational River") ||
				parkTerrain.equals("National River") ||
				parkTerrain.equals("Wild and Scenic River")) {
			terrainCategories.add("Lakes, Rivers, and Seashores");
		}
		if (parkTerrain.equals("National River and Recreation Area") ||
				parkTerrain.equals("Parkway") ||
				parkTerrain.equals("National Preserve") || 
				parkTerrain.equals("National Recreation River") ||
				parkTerrain.equals("National Recreation River") ||
				parkTerrain.equals("National Reserve") ||
				parkTerrain.equals("National Recreation Area") ||
				parkTerrain.equals("National Parkway") ||
				parkTerrain.equals("National Historical Park and Preserve") ||
				parkTerrain.equals("Ecological and Historical Preserve") ||
				parkTerrain.equals("National Historical Reserve") ||
				parkTerrain.equals("Other Designation")) {
			terrainCategories.add("Parkways, Reserves, and Recreation Areas");
		}
		if(parkTerrain.equals("National Historic Trail") ||
				parkTerrain.equals("National Park") ||
				parkTerrain.equals("Park") ||
				parkTerrain.equals("National Monument") ||
				parkTerrain.equals("National Scenic Trail")){
			terrainCategories.add("Outdoors (Mountains, Forests, Trails)");
		}
		for (String category : terrainCategories) {
			if (category.equals(terrainType)) {
				match = true;
			}
		}
		return match;
	}

}
