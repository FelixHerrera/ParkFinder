/*
 * Filename: CriteriaFactory.java
 * Description: Abstract Factory for Criteria classes
 */
package template.criteria;

public class CriteriaFactory {

	public Criteria createCriteria(String criteriaType, String param) {
		if (criteriaType == "terrain") {
			return new TerrainCriteria(param);
		}
		if (criteriaType == "distance") {
			return new DistanceCriteria(param);
		}
		if (criteriaType == "size") {
			return new SizeCriteria(param);
		}
		return null;
	}
}
