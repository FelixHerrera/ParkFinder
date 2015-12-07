/*
 * Filename: Criteria.java
 * Description: Interface declaration for the Criteria class, with the fitCriteria method declaration
 */

package template.criteria;

import template.models.NationalParkLocation;

public interface Criteria {
	
	public boolean fitCriteria(NationalParkLocation npl);
}
