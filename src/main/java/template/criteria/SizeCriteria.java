package template.criteria;

import java.util.ArrayList;

import template.models.NationalParkLocation;
import template.services.NationalParkLocationServiceImpl;
import template.services.NationalParkLocationServiceImpl.BorderPointLists;

public class SizeCriteria implements Criteria {
	
	private String size;
	private static final int small_low = 0;
	private static final int small_high = 50;
	private static final int medium_high = 1000;
	// Arbitrarily large value to encompass all parks
	private static final int large_high = 100000;
 
	
	public SizeCriteria(String size) {
		this.size = size;
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		System.out.println(size);
		NationalParkLocationServiceImpl.BorderPointLists bpl = npl.getBorders();
		int sections = bpl.points.size();
		double area = 0.0;
		double lat1, lon1, lat2, lon2;
		for (int i = 0; i < sections; i++) {
			for (int j = 0; j < bpl.points.get(i).size(); j++) {
				lat1 = bpl.getBorderPointLat(i, j);
				lon1 = bpl.getBorderPointLon(i, j);
				if (j < bpl.points.get(i).size() - 1) {
					lat2 = bpl.getBorderPointLat(i, j+1);
					lon2 = bpl.getBorderPointLon(i, j+1);
				}
				else {
					lat2 = bpl.getBorderPointLat(i,0);
					lon2 = bpl.getBorderPointLon(i,0);
				}
				// Calculate the area based on the triangle formed by (lat1, lon1), (lat2, lon2)
				// and the centroid
				area += 0.5*Math.abs((lat1 - npl.getLatitude())*(lon2 - lon1) - 
						(lat1 - lat2)*(npl.getLongitude() - lon1));
			}
		}
		System.out.println(npl.getName() + ": " + area);
		if (size.equals("Small")) {
			if (area > small_low && area <= small_high) {
				return true;
			}
			else {
				return false;
			}
		}
		if (size.equals("Medium")) {
			if (area > small_high && area <= medium_high) {
				return true;
			}
			else {
				return false;
			}
		}
		if (size.equals("Large")) {
			if (area > medium_high && area <= large_high) {
				return true;
			}
			else {
				return false;
			}
		}
		return true;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}

}
