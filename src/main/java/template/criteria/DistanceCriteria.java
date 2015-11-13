package template.criteria;

import template.models.NationalParkLocation;

public class DistanceCriteria implements CriteriaFactory {
	
	private double maxDistance;
	
	public DistanceCriteria(double maxDistance) {
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		// TODO Auto-generated method stub
		return true;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

}
