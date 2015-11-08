package template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import template.algorithm.Ranking;

@Service
public class AlgorithmService {

	@Autowired
	NationalParkLocationService npls;
	
	public Ranking getRanking(double latitude, double longitude) {
		return new Ranking(latitude, longitude, npls);
	}

	public NationalParkLocationService getNpls() {
		return npls;
	}

	public void setNpls(NationalParkLocationService npls) {
		this.npls = npls;
	}
	
}
