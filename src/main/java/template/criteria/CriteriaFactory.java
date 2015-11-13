package template.criteria;

import template.models.NationalParkLocation;

public interface CriteriaFactory {
	
	public boolean fitCriteria(NationalParkLocation npl);
}
