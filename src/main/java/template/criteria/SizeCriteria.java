package template.criteria;

import template.models.NationalParkLocation;
import template.services.NationalParkLocationServiceImpl;

public class SizeCriteria implements Criteria {
	
	private String size;
	private static final int small_low = 0;
	private static final int small_high = 100;
	private static final int medium_high = 1000;
	// Arbitrarily large value to encompass all parks
	private static final int large_high = 100000;
 
	
	public SizeCriteria(String size) {
		this.size = size;
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		NationalParkLocationServiceImpl.BorderPointLists bpl = npl.getBorders();
		
		return true;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}

}
