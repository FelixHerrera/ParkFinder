package template.criteria;

import template.models.NationalParkLocation;

public class DistanceCriteria implements Criteria {
	
	private double maxDistance;
	
	public DistanceCriteria(String maxDistance) {
		this.maxDistance = Double.parseDouble(maxDistance);
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
