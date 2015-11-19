package template.criteria;

import template.models.NationalParkLocation;

public class DistanceCriteria implements Criteria {
	
	private double maxDistance;
	private double latitude;
	private double longitude;
	
	public DistanceCriteria(String maxDistance) {
		String[] parts = maxDistance.split(",");
		this.maxDistance = Double.parseDouble(parts[0]);
		this.latitude = Double.parseDouble(parts[1]);
		this.longitude = Double.parseDouble(parts[2]);
		System.out.println(maxDistance);
		System.out.println(latitude);
		System.out.println(longitude);
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		double distance = npl.distance(latitude, longitude);
		if (distance > maxDistance) {
			return false;
		}
		return true;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

}
