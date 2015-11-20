package template.criteria;

import template.models.NationalParkLocation;

public class SizeCriteria implements Criteria {
	
	private String size;
	
	public SizeCriteria(String size) {
		this.size = size;
	}

	@Override
	public boolean fitCriteria(NationalParkLocation npl) {
		// TODO Auto-generated method stub
		return true;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}

}
